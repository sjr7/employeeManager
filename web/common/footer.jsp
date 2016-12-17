<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/1/001
  Time: 19:36
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
            border-top: solid 1px #666;
            font-size: 15px;
            font-family: "YaHei Consolas Hybrid", Consolas, "微软雅黑", "Meiryo UI", "Malgun Gothic", "Segoe UI", "Trebuchet MS", Helvetica, Monaco, courier, monospace;
            color: #245269;
            font-weight:bold;
            scroll:no;
        }
        #footer{
            text-align: center;
            padding: 6px;

        }

    </style>
</head>
<body >
  <div id="footer">
     <span id="footer_text">这是一个学生信息管理系统  by Suny</span>
  </div>
</body>
</html>




