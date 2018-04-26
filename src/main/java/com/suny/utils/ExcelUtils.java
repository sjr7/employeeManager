package com.suny.utils;

import com.suny.entity.Employee;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    /**
     * 下载学生数据模板
     *
     * @param fileDir 要生成的模板的文件地址
     * @throws IOException 抛出IO流异常
     */
    public static void buildExcel(String fileDir, List employeeList) throws IOException {
        Workbook workbook = new XSSFWorkbook();                                    //创建一个表格
        Sheet sheet1 = workbook.createSheet("students");                          //创建一张工作表
        setTitles(sheet1);                                                       //设置标题行
        setStudentDetails(sheet1, employeeList);                                                    //填充数值
        FileOutputStream fileOutputStream = new FileOutputStream(fileDir);         //输出文件
        workbook.write(fileOutputStream);                                        //写入文件
        fileOutputStream.close();                                                //关闭
    }

    /**
     * 填充表格数据
     *
     * @param sheet1 Excel数据表
     */
    public static void setStudentDetails(Sheet sheet1, List employeeList) {
        Cell cell;
        for (int i = 0; i < employeeList.size(); i++) {
            //"序列号","姓名","班级","电话号码","寝室号","部门编号"
            Row row1 = sheet1.createRow(i + 1);        //因为前面已经创建了标题行，所以从第2行开始
            Employee employee = (Employee) employeeList.get(i);       //从第一个开始读取
            //序列号
            cell = row1.createCell(0);
            cell.setCellValue("" + i);
            //中间空缺账号密码，为不提供下载信息
            //成员姓名
            cell = row1.createCell(3);
            cell.setCellValue(employee.getUsername());
            //所在班级
            cell = row1.createCell(4);
            cell.setCellValue(employee.getClassName());
            //电话号码
            cell = row1.createCell(5);
            cell.setCellValue(employee.getTel());
            //寝室号
            cell = row1.createCell(6);
            cell.setCellValue(employee.getBedroom());
            //部门名字
            cell = row1.createCell(7);
            cell.setCellValue(employee.getManager().getDept());
        }
    }

    //保存标题数组，用于构建下载数据库数据时Excel表格的标题
    private final static String[] titles = new String[]{"序列号", "姓名", "班级", "电话号码", "寝室号", "部门编号"};

    /**
     * 设置表格标题
     *
     * @param sheet1 传入的要设置的Excel数据库表
     */
    public static void setTitles(Sheet sheet1) {

        Row row1 = sheet1.createRow(0);    //创建一行
        //循环读取插入数据
        for (int i = 0; i < titles.length; i++) {
            Cell cell = row1.createCell(i);     //创建每一个小格子
            cell.setCellValue(titles[i]);     //为每一个小格子设置值
        }
    }


    /**
     * 读取Excel学生数据
     *
     * @param file 要读取的数据的Excel数据表格
     * @throws IOException 抛出Io流异常
     */
    public static List getExcelData(File file) throws IOException {
        //此为poi的核心对象，通过构造方法中的InputStream生成HSSWorkbook对象。
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(file));
        //这里得到第一张表格(sheet)，因为只有一张表在这里
        XSSFSheet sheetAt = workbook.getSheetAt(0);
        //创建一个student集合来存放每一行的student对象
        List<Employee> employeeList = new ArrayList<>();
//        for (Row row : sheetAt) {       //使用增强for循环遍历
        for (int i = 1; i <= sheetAt.getLastRowNum(); i++) {  //获取Excel中第一张表格sheetAt最大的行数
            Row row = sheetAt.getRow(i);    //迭代Excel数据的每一行row,第1行，第2行......
            //数据库中的主键，采用自动生成的方式，所以留空
            // Integer value1 = Integer.valueOf(row.getCell(0).getStringCellValue());
            //传入getCellValue方法中进行判断取出来的值是什么类型的
            //"序列号", "姓名", "班级", "电话号码", "寝室号", "部门编号"
            String value1 = RandomIDUtils.generateShortUuid();
            //密码数据库默认为123456，留空
            String value3 = getCellValue(row.getCell(3)); //姓名
            String value4 = getCellValue(row.getCell(4)); //班级
            String value5 = getCellValue(row.getCell(5)); //电话号码
            String value6 = getCellValue(row.getCell(6)); //寝室号
            //传入参数顺序为 ：序列号，账号，密码，姓名，班级，电话号码，寝室号，部门名字，管理员id，员工类型，传参数到employee对象
            Employee employee = new Employee(value1, "123456", value3, value4, value5, value6);
            employeeList.add(employee);                           //把遍历的每一行学生数据添加到一个student集合中
        }
        return employeeList;
    }


    /**
     * 判断Excel中取出数据类型
     *
     * @param cell 每一行的数据
     * @return 判断取出的类型，并进行相应的转换
     */
    @SuppressWarnings("deprecation")
    public static String getCellValue(Cell cell) {
        switch (cell.getCellType()) {
            //取出来的值是String类型的时候
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            //取出来的值是数字的时候
            case Cell.CELL_TYPE_NUMERIC:
                //是日期字符型的时候
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue() + "";
                    //否则就是数字
                } else {
                    // 这种就是格式化数字    return cell.getNumericCellValue()+"";
                    DecimalFormat df = new DecimalFormat("0");
                    return df.format(cell.getNumericCellValue());
                }
                //取出来的值是布尔值的时候
            case Cell.CELL_TYPE_BOOLEAN:
                return cell.getBooleanCellValue() + "";
            //取出来的值是Formula的时候
            case Cell.CELL_TYPE_FORMULA:
                return cell.getCellFormula() + "";
        }
        return null;
    }
}
