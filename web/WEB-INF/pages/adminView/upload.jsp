<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/10/010
  Time: 22:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>网盘文件管理页面</title>
</head>
<body>
     <h1>文件上传页面</h1>
   <form  action="upload" method="post" enctype="multipart/form-data">

     <input type="file" name="file">
     <input type="submit" value="上传">
   </form>

</body>
</html>
