package com.suny.controller;

import com.suny.entity.Employee;
import com.suny.service.EmployeeService;
import com.suny.utils.ExcelUtils;
import com.suny.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * 操作成员信息
 * 孙建荣
 * 2016/11/14 12:33
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeServiceImpl;


    /**
     * 成员签到
     *
     * @param request  用户的request请求
     * @param response 服务器对用户的response响应
     * @throws IOException 向客户端响应的时候可能发生的异常
     */
    @RequestMapping("/punch")
    public void punch(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        String username = (String) request.getSession().getAttribute("username");   //获取要签到的用户名
        int status = employeeServiceImpl.punch(username);    //  得到一个状态码，进行判断各种结果
        switch (status) {
            case 0:
                printWriter.write("null");
                break;             // 发生了错误，不能进行签到
            case 1:
                printWriter.write("notPunch");
                break;             // 可以签到，但是还没有签到
            case 2:
                printWriter.write("repeatPunch");
                break;             // 已经签到了，重复签到
            case 3:
                printWriter.write("successPunch");
                break;             //  签到成功
        }
        printWriter.close();           //关闭输出流
    }

    /**
     * 成员在个人中心查看个人信息请求
     *
     * @param modelMap 包含属性的map
     * @return 返回查看个人信息页面
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Employee employee = employeeServiceImpl.getById(id);    //通过当前用户的id进行查询当前用户的信息
        modelMap.addAttribute("employee", employee);   //保存成员要查看的个人信息
        return "/pages/userView/viewEmployee";
    }


    /**
     * 提交修改成员信息操作
     *
     * @param id       用户对应的主键标示id
     * @param employee 一个包含属性的employee对象集合
     * @return 操作经过处理后重定向到成员信息管理页面
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Integer id, Employee employee) {
        employeeServiceImpl.update(employee, id);                       //把要修改的成员信息页面提交给service
        return "redirect:/manageEmployeeAction/manageEmployee";      //重定向到成员管理页面
    }

    /**
     * 进入修改成员页面
     *
     * @param id       要修改的用户主键标识id
     * @param modelMap 包含属性的模型
     * @return 进入对应成员的修改资料页面
     */
    @RequestMapping(value = "updatePage/{id}")
    public String updatePage(@PathVariable("id") Integer id, ModelMap modelMap) {
        Employee employee = employeeServiceImpl.getById(id);           //获取要修改的成员信息
        modelMap.addAttribute("employee", employee);             //把获取到的成员信息放到modelMap里面
        return "pages/adminView/update";               //进入修改成员信息页面
    }

    /**
     * 删除成员操作
     *
     * @param id 要删除的成员主键标识id
     * @return 操作成功后重定向到成员管理页面
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Integer id) {
        employeeServiceImpl.del(id);            //传入要删除的成员的id
        return "redirect:/employee/manageEmployeeList";
    }

    /**
     * 增加成员数据操作
     *
     * @param employee 包含成员信息的集合
     * @return 重定向到成员管理页面
     */
    @RequestMapping("/add")
    public String add(Employee employee) {
        employeeServiceImpl.add(employee);      //把employee对象传到数据操作层进行保存
        return "redirect:/employee/manageEmployeeList";
    }




    /**
     * 管理员查看某个成员详情页面
     *
     * @param id 要查看的成员的主键标识id
     * @return 查看成员属性的页面
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Employee personDetail(@PathVariable("id") int id) {
        return employeeServiceImpl.getById(id);
    }

    /**
     * 使用关键字进行查询
     *
     * @param request  request请求，包含用户请求信息
     * @param modelMap 包含属性的模型
     * @return 通过关键字进行查询成员信息后的页面
     * @throws UnsupportedEncodingException 抛出不知道编码的异常
     */
    @RequestMapping("/getByName")
    public String getByName(@RequestParam(value = "currentPage", defaultValue = "1") String stringCurPage,
                            @RequestParam(value = "pageCount", defaultValue = "5", required = false) String stringPageCount,
                            @RequestParam(value = "name") String username,
                            HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        Integer curPage = Integer.valueOf(stringCurPage);
        Integer pageCount = Integer.valueOf(stringPageCount);
        if (curPage == 0) {        //默认为第一页
            curPage = 1;
        }
        request.setCharacterEncoding("UTF-8");     //设置编码
        Page page = employeeServiceImpl.getByName(username, pageCount, curPage);   //返回分页后的page对象
        modelMap.addAttribute("value", username);     //添加name到modelMap中
        modelMap.addAttribute("page", page);     //添加page对象到modelMpa
        return "pages/adminView/employeeList";
    }

    /**
     * 访问成员信息管理页面
     *
     * @param request  request请求，包含用户请求信息
     * @param modelMap 包含数据的模型
     * @return 带分页数据后的成员信息管理页面
     */
    @RequestMapping("/manageEmployeeList")
    public String manageEmployee(HttpServletRequest request, ModelMap modelMap) {
        Page page = new Page();
        int pageCount = 5;                                //设置每页显示的行数
        String currentPage = request.getParameter("stringCurPage");    //获取当前页
        if (currentPage == null || currentPage.equals("0")) {      //设置默认页为1
            currentPage = "1";
        }
        if (Integer.valueOf(currentPage) > employeeServiceImpl.getMaxPage(pageCount)) {   //当前页超过最大页时设置当前页为最大页
            page = employeeServiceImpl.getAll(employeeServiceImpl.getMaxPage(pageCount), pageCount);
        } else {
            page = employeeServiceImpl.getAll(Integer.valueOf(currentPage), pageCount);   //获取分页数据
        }
        modelMap.addAttribute("page", page);         //添加page对象到modelMap中
        List employeeList = page.getPageDate();       //获取page对象中的分页数据
        modelMap.addAttribute("employeeList", employeeList);   //把分页数据集合保存到session中
        return "pages/adminView/manageEmployee";
    }

    /**
     * 查看成员信息页面
     *
     * @param modelMap 向页面传送参数的集合
     * @return 进入管理成员信息页面
     */
    @RequestMapping("/employeeDetail")
    public String employee_view(@RequestParam(value = "currentPage", defaultValue = "1") String stringCurPage,
                                @RequestParam(value = "pageCount", defaultValue = "5", required = false) String stringPageCount,
                                ModelMap modelMap) {

        Page page;
        if (stringCurPage.equals("0")) {         //如果当前页为空或者为0的话默认为第1页
            stringCurPage = "1";
        }
        Integer curPage = Integer.valueOf(stringCurPage);
        Integer pageCount = Integer.valueOf(stringPageCount);
        if (curPage > employeeServiceImpl.getMaxPage(pageCount)) {   //如果当前页大于总页数则跳转到最大分页数
            page = employeeServiceImpl.getAll(employeeServiceImpl.getMaxPage(pageCount), pageCount);
        } else {
            page = employeeServiceImpl.getAll(curPage, pageCount);     //把当前页跟每页行数传到service里面进行分页查询
        }
        modelMap.addAttribute("page", page);            //添加分页对象到modelMap里面

        return "pages/adminView/employeeList";      //进行成员信息列表页面
    }

    /**
     * 下载数据库中的成员信息，然后保存到excel表格中
     *
     * @param request  用户请求相关
     * @param response 服务器对用户请求的响应
     * @throws IOException 抛出IO流异常
     */
    @RequestMapping("/downloadExcel")
    public void downloadExcel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 如果文件名有中文的话，进行URL编码，让中文正常显示
         response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
         */
        String fileName = System.currentTimeMillis() + ".xlsx";                                      //系统生成的文件名
        String FileDir = request.getServletContext().getRealPath("/files/" + fileName);
        List list = employeeServiceImpl.getAllStudentDetails();//构建一个Excel文件
        ExcelUtils.buildExcel(FileDir, list);                                              //传入service
        String dataDirectory = request.getServletContext().getRealPath("/files");           //获取要下载的文件文件目录
        Path file = Paths.get(dataDirectory, fileName);                                     //获取一个文件
        if (Files.exists(file)) {                                                      //如果文件存在的情况下
            response.setContentType("application/octet-stream");                        //响应请求添加类型
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);    //添加响应头，发送给客户端的文件名
            try {
                Files.copy(file, response.getOutputStream());
            } catch (IOException ignored) {
            }
        }
    }


}









