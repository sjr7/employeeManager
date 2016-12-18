<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/14/014
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>查看详情</title>

    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
</head>
<body>

<table border="1" class="table table-striped">
    <tr>
        <th>序号</th>
        <td>${employee.id}</td>
    </tr>

    <tr>
        <th> 姓名</th>
        <td> ${employee.username}</td>
    </tr>
    <tr>
        <th>班级</th>
        <td> ${employee.className}</td>
    </tr>
    <tr>
        <th>联系方式</th>
        <td> ${employee.tel}</td>
    </tr>
    <tr>
        <th>寝室号</th>
        <td> ${employee.bedroom}</td>
    </tr>
    <%--<tr>--%>
        <%--<th>电话</th>--%>
        <%--<td>${student.tel}</td>--%>
    <%--</tr>--%>
    <%--<tr>--%>
        <%--<th>寝室号</th>--%>
        <%--<td>${student.bedroom}</td>--%>
    <%--</tr>--%>


</table>
<a href="${pageContext.request.contextPath}/employee/employeeDetail" class="btn btn-primary btn-lg">返回学生信息列表</a>

</body>
</html>
