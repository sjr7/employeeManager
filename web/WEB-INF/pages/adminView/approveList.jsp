<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/10/010
  Time: 16:39
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
    <title>考勤审批页面</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <script>
        $(function () {
            $("#pass").click(function () {
                var status = confirm("您是否要同意这条申请?");
                if (!status) {
                    return false;
                }
            })
            $("#deny").click(function () {
                var status = confirm("您是否要拒绝这条申请?");
                if (!status) {
                    return false;
                }
            })

        })

    </script>
</head>

<body>
<div class="container">
    <table border="2" class="table table-striped table-hover">

        <thead>
        <tr>
            <td>考勤序号</td>
            <td>考勤时间</td>
            <td>签到时间</td>
            <td>考勤类型</td>
            <td>考勤人姓名</td>
            <td>申请修改类型</td>
            <td>申请理由</td>
            <td colspan="2">操作</td>
        </tr>
        </thead>

        <c:if test="${empty approveList}">
            <tr>
                <td colspan="8">暂无申请修改考勤信息</td>
            </tr>
        </c:if>
        <c:if test="${!empty approveList}">
            <tbody>
            <c:forEach items="${approveList}" var="a">

                <tr>
                    <td>${a.id}</td>
                    <td>${a.attend.dutyDay}</td>
                    <td>${a.attend.punchTime}</td>
                    <td>${a.attend.attendType.name}</td>
                    <td>${a.attend.employee.username}</td>
                    <td>${a.attendType.name}</td>
                    <td>${a.reason}</td>
                    <td>
                        <a type="button"
                                href="${pageContext.request.contextPath}/approve/checkApprove?result=pass&username=${a.attend.employee.manager.username}&appid=${a.id}"
                                id="pass" class=" btn btn-success btn-lg">同意
                        </a>
                    </td>
                    <td>
                        <a type="button"
                                href="${pageContext.request.contextPath}/approve/checkApprove?result=deny&username=${a.attend.employee.manager.username}&appid=${a.id}"
                                id="deny" class="btn btn-warning btn-lg">拒绝
                        </a>
                    </td>

                </tr>


            </c:forEach>
            </tbody>
        </c:if>


    </table>

</div>
</body>
</html>
