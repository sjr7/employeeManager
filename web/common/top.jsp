<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/1/001
  Time: 19:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<html>
<head>
    <title></title>
    <style>
        body{
            border-bottom: solid 1px #666;
        }
        #banner {
            background: url("${pageContext.request.contextPath}/images/banner.jpg") no-repeat;
            width: 100%;
            height: 100%;
            background-size: cover;
        }
        /*#title {*/
            /*position: absolute;*/
            /*height: auto;*/
            /*font-size: 33px;*/
            /*margin: 0 auto;*/
            /*color: #484848;*/
            /*font-weight: bold;*/
            /*font-family: "YaHei Consolas Hybrid", Consolas, "微软雅黑", "Meiryo UI", "Malgun Gothic", "Segoe UI", "Trebuchet MS", Helvetica, Monaco, courier, monospace;*/
        /*}*/
    </style>

</head>
<body>
<div id="banner">
</div>

</body>
</html>
