<%--@elvariable id="page" type="com.suny.utils.Page"--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/16/016
  Time: 17:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>学生信息页面</title>
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
                window.location.href = "${pageContext.request.contextPath}/employee/employeeDetail/?currentPage=" + val;
            });

            $("#selectNo").val("${page.currentPage}");   //跳转不同页后下拉框为当前页页码


            $(".exit").click(function () {
                var userName = $(".userName").text();
                var flag = confirm(userName + "你确定要退出吗？");
                if (!flag) {
                    return false;
                }
//                window.open('', '_self');
//                window.close();
                window.parent.close();

            })

        })
    </script>
    <style>
        #selectByName {
            display: inline-block;
        }

        #btn {
            width: 96px;
            margin-left: 30px;
            padding: 0;
            height: 28px;
            line-height: 28px;
            background: #478de4;
            color: #fff;
            border: 0;
            border-radius: 2px;
        }
    </style>
</head>
<body>

<hr>

<div class="container">
    <form action="${pageContext.request.contextPath}/employee/getByName" method="post" id="selectByName"
          class="form-inline">
        <div class="form-group ">
            <label for="inputName">输入姓名查询</label>
            <input type="text" class="form-control " id="inputName" name="name" placeholder="姓名"
                   value="${value}">
        </div>
        <button type="submit" class="btn btn-default ">查询</button>
    </form>

    <table border="2" class="table table-striped table-hover">

        <thead>
        <tr>
            <td>序号</td>
            <td>姓名</td>
            <td>班级</td>
            <td>电话号码</td>
            <td>寝室</td>
            <td>部长</td>
        </tr>
        </thead>

        <c:if test="${empty page.pageDate}">
            <tr>
                <td colspan="8">成员数据为空</td>
            </tr>
        </c:if>
        <c:if test="${!empty page.pageDate}">
            <tbody>
            <c:forEach items="${page.pageDate}" var="employee">

                <tr>
                    <td>${employee.id}</td>
                    <td>${employee.empName}</td>
                    <td>${employee.className}</td>
                    <td>${employee.tel}</td>
                    <td>${employee.bedroom}</td>
                    <td>${employee.manager.userName}</td>


                </tr>


            </c:forEach>
            </tbody>
        </c:if>


    </table>
    <c:if test="${! empty page}">
        <a href="${pageContext.request.contextPath}/employee/employeeDetail?currentPage=${page.getTopPageNo()}"
           class="btn btn-primary ">首页</a>
        <a href="${pageContext.request.contextPath}/employee/employeeDetail?currentPage=${page.getPrevious()}"
           class="btn btn-success ">上一页</a><a
            href="${pageContext.request.contextPath}/employee/employeeDetail?currentPage=${page.getNext()}"
            class="btn btn-info ">下一页</a>
        <a href="${pageContext.request.contextPath}/employee/employeeDetail?currentPage=${page.getBottom()}"
           class="btn btn-warning">末页</a>
    </c:if>
    <c:if test="${ empty page}">
        <a href="${pageContext.request.contextPath}/employee/employeeDetail?currentPage=1"
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
    <label for="selectNo"></label>
    <select class="selectNo btn btn-default dropdown-toggle" id="selectNo">

        <c:forEach var="i" begin="1" end="${page.totalPage}" step="1">
            <option value="${i}">${i}</option>
        </c:forEach>

    </select>
    页/${page.getTotalPage()}


    <a href="${pageContext.request.contextPath}/file/databaseDownload" class="btn btn-success disabled">下载为Excel数据</a>
</div>
</body>
</html>
