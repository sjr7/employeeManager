<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/2/002
  Time: 22:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生信息管理页面</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script>
        $(function () {
            $(".selectNo").change(function () {
                var val = this.value;
                val = $.trim(val);
                this.value = val;

                var reg = /^\d+$/g;
                if (!reg.test(val)) {
                    alert("页码不合法");
                    this.value = "";
                    return;
                }
                var pageNo = parseInt(val);
                if (pageNo < 0 || pageNo > parseInt("${page.totalPage}")) {
                    alert("页码不合法");
                    this.value = "";
                    return;
                }
                window.location.href = "${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=" + val;
            });

            $("#selectNo").val("${page.currentPage}");   //跳转不同页后下拉框为当前页页码

            /* $(".delete").click(function () {

                 var flag = confirm("你确定要删除这个学生的信息吗");
                 if (!flag) {
                     return false;
                 }
             });*/
            $("#submit").click(function () {
                var file = $("#ExcelFile").val();
                if (file == "") {
                    alert("请选择要上传的文件");
                    return false;
                }
                if (file.indexOf('.xlsx') == -1) {
                    alert("你选择的格式不正确");
                    return false;
                }
                $("#ExcelFile").submit();
            })

        })
    </script>
</head>
<body>


<hr>

<div class="container">
    <table border="2" class="table table-striped table-hover">

        <thead>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>班级</td>
            <td>部门</td>
            <td colspan="3">操作</td>
        </tr>
        </thead>

        <c:if test="${empty employeeList}">
            <tr>
                <td colspan="4">成员数据为空</td>
            </tr>

        </c:if>
        <c:if test="${!empty employeeList}">
            <tbody>
            <c:forEach items="${employeeList}" var="employee">

                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.empName}</td>
                    <td>${employee.className}</td>
                    <td>${employee.manager.dept}</td>
                    <td><a class="btn btn-info" id="updateEmployee" onclick="editEmployee(${employee.id})">修改</a>
                    </td>
                        <%--<td><a onclick="delConfirm()" href="${pageContext.request.contextPath}/employee/delete/${employee.id}"--%>
                    <td><a onclick="delConfirm()" class="btn btn-danger delete">删除</a></td>
                        <%--<td><a href="${pageContext.request.contextPath}/ViewEmployee/${s.num}" class="btn btn-success">查看</a>--%>
                    </td>


                </tr>


            </c:forEach>
            </tbody>
        </c:if>


    </table>
    <c:if test="${! empty page}">
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getTopPageNo()}"
           class="btn btn-primary ">首页</a>
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getPrevious()}"
           class="btn btn-success ">上一页</a><a
            href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getNext()}"
            class="btn btn-info ">下一页</a>
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getBottom()}"
           class="btn btn-warning ">末页</a>
    </c:if>
    <c:if test="${ empty page}">
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=1"
           class="btn btn-primary ">首页</a>
        <a href="#"
           class="btn btn-success ">上一页</a>
        <a href="#"
           class="btn btn-info ">下一页</a>
        <a href="#"
           class="btn btn-warning ">末页</a>
    </c:if>

    总记录数${page.totalCount}

    当前
    <label for="selectNo"></label><select class="selectNo btn btn-default dropdown-toggle" id="selectNo">

    <c:forEach var="i" begin="1" end="${page.totalPage}" step="1">
        <option value="${i}">${i}</option>
    </c:forEach>

</select>
    页/${page.getTotalPage()}

    <button class="btn btn-warning " onclick="addEmployee()">添加学生数据</button>

    <form action="${pageContext.request.contextPath}/file/uploadDatabase" enctype="multipart/form-data" method="post">
        <input type="file" name="file" id="ExcelFile">
        <input type="submit" value="上传学生资料" id="submit">

    </form>

    <!-- 信息删除确认 -->
    <div class="modal fade" id="delModel">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">×</span></button>
                    <h4 class="modal-title">提示信息</h4>
                </div>
                <div class="modal-body">
                    <p>您确认要删除吗？</p>
                </div>
                <div class="modal-footer">
                    <input type="hidden" id="url"/>
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <a class="btn btn-success" data-dismiss="modal">确定</a>
                </div>
            </div>
        </div>
    </div>

    <%--修改资料拟态框--%>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true" style="height: 500px">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title" id="myModalLabel">修改数据</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label style="display: none" for="id">id</label>
                        <input type="hidden" class="form-control " id="id" name="id" required/>
                    </div>

                    <div class="form-group">
                        <label for="account">登陆账号</label>
                        <input type="text" class="form-control " id="account" name="account" required/><br>
                    </div>

                    <div class="form-group">
                        <label for="empName">姓名</label>
                        <input type="text" class="form-control " id="empName" name="empName" required/><br>
                    </div>


                    <div class="form-group">
                        <label for="className">班级</label>
                        <input type="text" class="form-control " id="className" name="className" required/><br>
                    </div>


                    <div class="form-group">
                        <label for="tel">电话</label>
                        <input type="text" class="form-control " id="tel" name="tel" required/><br>
                    </div>

                    <div class="form-group">
                        <label for="bedroom">寝室号</label>
                        <input type="text" class="form-control " id="bedroom" name="bedroom" required/><br>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="valid('update')">确定修改</button>
                </div>
            </div>
        </div>
    </div>

    <%--添加信息框--%>
    <div class="modal fade" id="addEmployeeModalLabel" tabindex="-1" role="dialog"
         aria-labelledby="addEmployeeModalLabel"
         aria-hidden="true" style="height: 500px">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">添加数据</h4>
                </div>
                <div class="modal-body">
                    <div class="form-group">
                        <label style="display: none" for="id">id</label>
                        <input type="hidden" class="form-control " id="id_" name="id" required/>
                    </div>


                    <div class="form-group">
                        <label for="empName">姓名</label>
                        <input type="text" class="form-control " id="empName_" name="empName" required title=""/><br>
                    </div>


                    <div class="form-group">
                        <label for="className">班级</label>
                        <input type="text" class="form-control " id="className_" name="className" required
                               title=" "/><br>
                    </div>

                    <div class="form-group">
                        <label for="teacher">班主任</label>
                        <input type="text" class="form-control " id="teacher" name="teacher" required title=" "/><br>
                    </div>


                    <div class="form-group">
                        <label for="tel">电话</label>
                        <input type="text" class="form-control " id="tel_" name="tel" required title=""/><br>
                    </div>

                    <div class="form-group">
                        <label for="bedroom">寝室号</label>
                        <input type="text" class="form-control " id="bedroom_" name="bedroom" required title=" "/><br>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="valid('add')">提交更改</button>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
    $(function () {
        /*$("#id,#id_").blur(function () {
            var val = this.value;
            val = $.trim(val);
            this.value = val;
            var reg = /^[0-9]*$/;
            if (!reg.test(val)) {
                alert("学号只运行输入数字");
            }
        });
        $("#empName,#empName").blur(function () {
            var val = this.value;
            val = $.trim(val);
            this.value = val;
            var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
            if (!reg.test(val)) {
                alert("姓名只能为汉字");
            }
        });
        $("#sex,#sex_").blur(function () {
            var val = this.value;
            val = $.trim(val);
            this.value = val;
            reg = /[\u4E00-\u9FA5]/;
            if (!reg.test(val)) {
                alert("性别只能为男或者女");
            }
        });
        $("#bedroom,#bedroom_").blur(function () {
            var val = this.value;
            val = $.trim(val);
            this.value = val;
            var reg = /^[0-9]\#\d{3}$/;   //匹配第一个为0-9当中的一个，、#为普通字符#，\d为允许出现数字,{3}为出现3次
            if (!reg.test(val)) {
                alert("寝室号样例为  4#608  ，按照自身寝室号修改");
            }
        });
        $("#tel,#tel_").blur(function () {
            var val = this.value;
            val = $.trim(val);
            this.value = val;
            var reg = /^1[0-9]{10}$/;
            if (!reg.test(val)) {
                alert("电话号码为中国大陆普通11位电话号码");
            }
        });*/


    });


    $("#submit").click(function () {
        var flag = confirm("你确定要提交吗?");
        if (!flag) {
            return false;
        }
        var adress = $("#employee_adress").value();
        var id = $("#employee_id").value();
        var name = $("#employee_name").value();
        var sex = $("#employee_sex").value();
        var room = $("#employee_room").value();
        var employeetel = $("#employee_tel").value();
        var parenttel = $("#Parents_tel").value();
    })

    function delConfirm(employeeId) {
        $("#delModel").modal({
            show: true,
            backdrop: false
        });

    }

    function editEmployee(employeeId) {
        $.ajax({
            url: "${pageContext.request.contextPath}/employee/" + employeeId, success: function (employee) {
                $("#id").val(employee.id);
                $("#account").val(employee.account);
                $("#userName").val(employee.userName);
                $("#className").val(employee.className);
                $("#tel").val(employee.tel);
                $("#bedroom").val(employee.bedroom);
                $('#myModal').modal({
                    show: true,
                    backdrop: false
                });
            }
        });

    }

    function updateEmployeeAction() {
        console.log('updateAction');
        $('#myModal').modal({
            show: false,
            backdrop: false
        });
    }

    function addEmployee() {
        $('#addEmployeeModalLabel').modal({
            show: true,
            backdrop: false
        });

    }

    function addEmployeeAction() {
        console.log('addAction');
        $.ajax({
            url: "${pageContext.request.contextPath}/employee/add", success: function (employee) {
                $("#id_").val(employee.id);
                $("#account_").val(employee.account);
                $("#userName_").val(employee.userName);
                $("#className_").val(employee.className);
                $("#teacher").val(employee.teacher);
                $("#tel_").val(employee.tel);
                $("#bedroom_").val(employee.bedroom);
                $('#myModal_').modal({
                    show: false,
                    backdrop: false
                });
            }
        });

    }


    function valid(type) {


        if (type === 'add') {
            addEmployeeAction();
        } else if (type === 'update') {
            updateEmployeeAction();
        }

    }


</script>
</body>
</html>
