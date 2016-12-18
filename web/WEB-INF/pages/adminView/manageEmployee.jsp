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

            $(".delete").click(function () {

                var flag = confirm("你确定要删除这个学生的信息吗");
                if (!flag) {
                    return false;
                }
            });
            $("#submit").click(function(){
                var file=$("#ExcelFile").val();
                if(file == ""){
                    alert("请选择要上传的文件");
                    return false;
                }
                if(file.indexOf('.xlsx') == -1){
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
            <tr><td colspan="4">成员数据为空</td></tr>

        </c:if>
        <c:if test="${!empty employeeList}">
            <tbody>
            <c:forEach items="${employeeList}" var="a">

                <tr>
                    <td>${a.id}</td>
                    <td>${a.username}</td>
                    <td>${a.className}</td>
                    <td>${a.manager.dept}</td>
                    <td><a href="${pageContext.request.contextPath}/employee/viewModifyOperation/${a.id}" class="btn btn-info" >修改</a>
                    </td>
                    <td><a href="${pageContext.request.contextPath}/employee/deleteOperation/${a.id}"
                           class="btn btn-danger delete">删除</a></td>
                    <%--<td><a href="${pageContext.request.contextPath}/ViewEmployee/${s.num}" class="btn btn-success">查看</a>--%>
                    </td>


                </tr>


            </c:forEach>
            </tbody>
        </c:if>


    </table>
    <c:if test="${! empty page}">
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getTopPageNo()}"
           class="btn btn-primary btn-lg">首页</a>
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getPrevious()}"
           class="btn btn-success btn-lg">上一页</a><a
            href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getNext()}"
            class="btn btn-info btn-lg">下一页</a>
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=${page.getBottom()}"
           class="btn btn-warning btn-lg">末页</a>
    </c:if>
    <c:if test="${ empty page}">
        <a href="${pageContext.request.contextPath}/employee/manageEmployeeList?currentPage=1"
           class="btn btn-primary btn-lg">首页</a>
        <a href="#"
           class="btn btn-success btn-lg">上一页</a>
        <a href="#"
           class="btn btn-info btn-lg">下一页</a>
        <a href="#"
           class="btn btn-warning btn-lg">末页</a>
    </c:if>

    总记录数${page.totalCount}

    当前
    <label for="selectNo"></label><select class="selectNo btn btn-default dropdown-toggle" id="selectNo">

        <c:forEach var="i" begin="1" end="${page.totalPage}" step="1">
            <option value="${i}">${i}</option>
        </c:forEach>

    </select>
    页/${page.getTotalPage()}

    <a href="${pageContext.request.contextPath}/employee/viewAddOperation" class="btn btn-warning btn-lg">添加学生数据</a>

    <form action="${pageContext.request.contextPath}/file/uploadDatabase" enctype="multipart/form-data" method="post">
        <input type="file" name="file" id="ExcelFile">
        <input type="submit" value="上传学生资料" id="submit">

    </form>
</div>
</body>
</html>
