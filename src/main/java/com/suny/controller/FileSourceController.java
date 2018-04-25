package com.suny.controller;


import com.suny.service.impl.FileSourceService;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件操作相关控制层
 * 孙建荣
 * 2016/11/10 22:15
 */
@Controller
@RequestMapping("/file")
public class FileSourceController implements ServletContextAware {

    @Autowired
    private FileSourceService fileSourceService;      //自动装配

    public void setServletContext(ServletContext servletContext) {

    }

    /**
     *  下载数据库中的成员信息，然后保存到excel表格中
     * @param request  用户请求相关
     * @param response   服务器对用户请求的响应
     * @throws IOException   抛出IO流异常
     */
    @RequestMapping("/databaseDownload")
    public void databaseDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /**
         * 如果文件名有中文的话，进行URL编码，让中文正常显示
         response.addHeader("Content-Disposition","attachment; filename=" + URLEncoder.encode(fileName, "UTF-8"));
         */
        String fileName = System.currentTimeMillis() + ".xlsx";                                      //系统生成的文件名
        String FileDir = request.getServletContext().getRealPath("/files/" + fileName);             //构建一个Excel文件
        fileSourceService.buildExcel(FileDir);                                              //传入service
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

    /**
     * 下载Excel数据文件模板,给管理员一个数据上传的模板，在模板的基础上进行修改
     * @param response   服务器对用户请求的响应
     * @param request    用户的请求相关
     */
    @RequestMapping("downloadExcelTemplate")
    public void downloadExcelTemplate(HttpServletResponse response, HttpServletRequest request) {
        String dataDirectory = request.getServletContext().getRealPath("/files");               //获取要下载的文件文件目录
        Path file = Paths.get(dataDirectory, "student.xlsx");                               //获取一个文件
        if (Files.exists(file)) {                                                      //如果文件存在的情况下
            response.setContentType("application/octet-stream");                     //响应请求添加类型
            String fileName = "student.xlsx";                                                   //发送给客户端的文件名
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);          //添加响应头
            try {
                Files.copy(file, response.getOutputStream());                            //使用Files类中的copy方法
            } catch (IOException ignored) {
            }
        }
    }

    /**
     * 进行上传学生Excel数据的页面
     * @return   上传Excel数据的页面
     */
    @RequestMapping(value = "viewUploadExcel")
    public String viewUploadExcel() {
        return "/pages/adminView/UploadExcel";        //返回到上传Excel数据页面
    }

    /**
     * 对表单上传的Excel表格进行解析，并把成员数据保存到数据库中
     * @param multipartFile    上传的文件
     * @return    上传成功的页面
     * @throws IOException    抛出处理IO流异常
     */
    @RequestMapping(value = "uploadDatabase", method = RequestMethod.POST)
    public String uploadDatabase(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        CommonsMultipartFile Cfile = (CommonsMultipartFile) multipartFile;        //获取表单上传的文件
        DiskFileItem diskFileItem = (DiskFileItem) Cfile.getFileItem();                //获取文件条目
        File file = diskFileItem.getStoreLocation();                                 //获取文件数据
        fileSourceService.getExcelData(file);                           //把文件数据传送到处理Excel数据的service
        return "success";
    }


}












