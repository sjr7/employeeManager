<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/14/014
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆页面</title>
</head>
<body>

     <form action="UserLogin" method="post">
       序号:<input type="text" name="num" id="num">
       学生姓名:<input type="text" name="Student_name" id="Student_name">
       <input type="submit" value="登陆">

     </form>


</body>
</html>
