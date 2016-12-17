package com.suny.controller.impl;

import com.suny.entity.Employee;
import com.suny.service.impl.EmployeeService;
import com.suny.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
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
    private EmployeeService employeeService;


    private Page page;


    public Page getPage() {
        return page;
    }

    public void setPage(Page<Employee> page) {
        this.page = page;
    }

    /**
     * 成员在个人中心查看个人信息请求
     * @param modelMap   包含属性的map
     * @return    返回查看个人信息页面
     */
    @RequestMapping("viewUserDetail/{id}")
    public String viewUserDetail(@PathVariable("id")Integer id,ModelMap modelMap){
        Employee employee=employeeService.getById(id);    //通过当前用户的id进行查询当前用户的信息
        modelMap.addAttribute("employee",employee);   //保存成员要查看的个人信息
        return "/pages/userView/studentDetail";
    }


    /**
     * 提交修改成员信息操作
     * @param id 用户对应的主键标示id
     * @param employee    一个包含属性的employee对象集合
     * @return    操作经过处理后重定向到成员信息管理页面
     */
    @RequestMapping(value = "/modifyOperation/{id}", method = RequestMethod.POST)
    public String modifyOperation(@PathVariable("id") Integer id, Employee employee) {
        employeeService.modifyOperation(employee, id);                       //把要修改的成员信息页面提交给service
        return "redirect:/manageStudentAction/manageStudent";      //重定向到成员管理页面
    }

    /**
     * 进入修改成员页面
     * @param id   要修改的用户主键标识id
     * @param modelMap    包含属性的模型
     * @return     进入对应成员的修改资料页面
     */
    @RequestMapping(value = "viewModifyOperation/{id}")
    public String viewModifyOperation(@PathVariable("id") Integer id, ModelMap modelMap) {
        Employee employee = employeeService.getById(id);           //获取要修改的成员信息
        modelMap.addAttribute("employee", employee);             //把获取到的成员信息放到modelMap里面
        return "/pages/adminView/viewModifyOperation";               //进入修改成员信息页面
    }

    /**
     *  删除成员操作
     * @param id   要删除的成员主键标识id
     * @return    操作成功后重定向到成员管理页面
     */
    @RequestMapping(value = "/deleteOperation/{id}", method = RequestMethod.GET)
    public String deleteOperation(@PathVariable("id") Integer id) {
        employeeService.deleteOperation(id);            //传入要删除的成员的id
        return "redirect:/employee/manageStudentList";
    }

    /**
     * 增加成员数据操作
     * @param employee  包含成员信息的集合
     * @return      重定向到成员管理页面
     */
    @RequestMapping("/addOperation")
    public String addOperation(Employee employee) {
        employeeService.addOperation(employee);      //把employee对象传到数据操作层进行保存
        return "redirect:/employee/manageStudentList";
    }

    /**
     * 进入增加成员数据界面
     * @return   增加成员信息表单页面
     */
    @RequestMapping("/viewAddOperation")
    public String viewAddOperation() {
        return "pages/adminView/addStudent";
    }


    /**
     * 查看某个成员详情页面
     * @param id   要查看的成员的主键标识id
     * @param modelMap    包含数据的模型
     * @return    查看成员属性的页面
     */
    @RequestMapping("/viewStudentDetail/{id}")
    public String viewStudentDetail(@PathVariable("id") Integer id, ModelMap modelMap) {
        Employee employee = employeeService.getById(id);     //传递id给service
        modelMap.addAttribute("employee", employee);        //把查询到的值的数据传递给前端页面
        return "/pages/adminView/viewStudent";
    }

    /**
     * 使用关键字进行查询
     * @param request  request请求，包含用户请求信息
     * @param modelMap    包含属性的模型
     * @return    通过关键字进行查询成员信息后的页面
     * @throws UnsupportedEncodingException   抛出不知道编码的异常
     */
    @RequestMapping("/getByName")
    public String getByName(HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        int pageCount = 5;                //设置每一页行数
        String currentPage = request.getParameter("currentPage");   //获取当前页
        if (currentPage == null || currentPage.equals("0")) {        //默认为第一页
            currentPage = "1";
        }
        request.setCharacterEncoding("UTF-8");     //设置编码
        String username = request.getParameter("name");     //获取请求参数
        Page page = employeeService.getByName(username, pageCount, Integer.valueOf(currentPage));   //返回分页后的page对象
        List employeeList = page.getPageDate();    //获取分页数据
        modelMap.addAttribute("employeeList", employeeList);    //添加查询的数据到modelMap中
        modelMap.addAttribute("value", username);     //添加name到modelMap中
        modelMap.addAttribute("page", page);     //添加page对象到modelMpa
        return "pages/adminView/studentsList";
    }

    /**
     * 访问成员信息管理页面
     * @param request  request请求，包含用户请求信息
     * @param modelMap   包含数据的模型
     * @return   带分页数据后的成员信息管理页面
     */
    @RequestMapping("/manageStudentList")
    public String manageStudent(HttpServletRequest request, ModelMap modelMap) {
        int pageCount = 5;                                //设置每页显示的行数
        String currentPage = request.getParameter("currentPage");    //获取当前页
        if (currentPage == null || currentPage.equals("0")) {      //设置默认页为1
            currentPage = "1";
        }
        if (Integer.valueOf(currentPage) > employeeService.getMaxPage(pageCount)) {   //当前页超过最大页时设置当前页为最大页
            page = employeeService.getAll(employeeService.getMaxPage(pageCount), pageCount);
        } else {
            page = employeeService.getAll(Integer.valueOf(currentPage), pageCount);   //获取分页数据
        }
        modelMap.addAttribute("page", page);         //添加page对象到modelMap中
        List employeeList = page.getPageDate();       //获取page对象中的分页数据
        modelMap.addAttribute("employeeList", employeeList);   //把分页数据集合保存到session中
        return "pages/adminView/manageStudent";
    }

    /**
     * 查看成员信息页面
     * @param modelMap  向页面传送参数的集合
     * @param request   页面发来的请求
     * @return   进入管理成员信息页面
     */
    @RequestMapping("/studentDetail")
    public String student_view(ModelMap modelMap, HttpServletRequest request) {
        int pageCount = 5;                                           //分页每页显示的行数
        String currentPage = request.getParameter("currentPage");       //获取当前页页码
        if (currentPage == null || currentPage.equals("0")) {         //如果当前页为空或者为0的话默认为第1页
            currentPage = "1";
        }
        if (Integer.valueOf(currentPage) > employeeService.getMaxPage(pageCount)) {   //如果当前页大于总页数则跳转到最大分页数
            page = employeeService.getAll(employeeService.getMaxPage(pageCount), pageCount);
        } else {
            page = employeeService.getAll(Integer.valueOf(currentPage), pageCount);     //把当前页跟每页行数传到service里面进行分页查询
        }
        modelMap.addAttribute("page", page);            //添加分页对象到modelMap里面
        List employeeList = page.getPageDate();
        modelMap.addAttribute("employeeList", employeeList);

        return "pages/adminView/studentsList";      //进行成员信息列表页面
    }








}









