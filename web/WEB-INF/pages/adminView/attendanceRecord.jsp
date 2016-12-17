<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/8/008
  Time: 13:07
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
    <title>考勤记录信息页面</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">

    <script>
        $(function () {


            $("#selectNo").change(function () {
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
                window.location.href = "${pageContext.request.contextPath}/attend/attendRecord/?currentPage=" + val;
            });

            $("#selectNo").val("${page.currentPage}");   //跳转不同页后下拉框为当前页页码
        })
    </script>
</head>

<body>
<div class="container">
    输入姓名查询 &nbsp;&nbsp;&nbsp;
    <form action="${pageContext.request.contextPath}/attend/getByName" method="post" id="selectByName">
        <label>
            <input type="text" name="name" value="${value}">
        </label>
        <input type="submit" value="查询" id="btn">
    </form>
    <table border="2" class="table table-striped table-hover">

        <thead>
        <tr>
            <td>考勤序号</td>
            <td>考勤时间</td>
            <td>签到时间</td>
            <td>考勤类型</td>
            <%--<td>家长电话</td>--%>
            <td>考勤人姓名</td>
        </tr>
        </thead>

        <c:if test="${empty attendRecordList}">
            <tr>
                <td colspan="8">考勤数据为空</td>
            </tr>
        </c:if>
        <c:if test="${!empty attendRecordList}">
            <tbody>
            <c:forEach items="${attendRecordList}" var="a">

                <tr>
                    <td>${a.id}</td>
                    <td>${a.dutyDay}</td>
                    <td>${a.punchTime}</td>
                    <td>${a.attendType.name}</td>
                    <td>${a.employee.username}</td>


                </tr>


            </c:forEach>
            </tbody>
        </c:if>


    </table>
    <c:if test="${! empty page}">
        <a href="${pageContext.request.contextPath}/attend/attendRecord?currentPage=${page.getTopPageNo()}"
           class="btn btn-primary btn-lg">首页</a>
        <a href="${pageContext.request.contextPath}/attend/attendRecord?currentPage=${page.getPrevious()}"
           class="btn btn-success btn-lg">上一页</a><a
            href="${pageContext.request.contextPath}/attend/attendRecord?currentPage=${page.getNext()}"
            class="btn btn-info btn-lg">下一页</a>
        <a href="${pageContext.request.contextPath}/attend/attendRecord?currentPage=${page.getBottom()}"
           class="btn btn-warning btn-lg">末页</a>
    </c:if>
    <c:if test="${ empty page}">
        <a href="${pageContext.request.contextPath}/attend/attendRecord?currentPage=1"
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
    <label for="selectNo"></label>
    <select class="selectNo btn btn-default dropdown-toggle" id="selectNo">

        <c:forEach var="i" begin="1" end="${page.totalPage}" step="1">
            <option value="${i}">${i}</option>
        </c:forEach>

    </select>
    页/${page.getTotalPage()}


</div>
</body>
</html>
