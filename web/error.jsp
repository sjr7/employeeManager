<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/10/010
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>错误页面</title>
</head>
<body>
<meta http-equiv="refresh" content="60;url=${pageContext.request.contextPath}/" property="">
<!-- content="60，即60秒后返回主页，可根据需要修改或者删除这段代码 -->
<link href="${pageContext.request.contextPath}/css/error.css" rel="stylesheet" type="text/css"/>
<!-- 代码 开始 -->
<div id="container"><img class="png" src="${pageContext.request.contextPath}/images/error/404.png"/> <img
        class="png msg" src="${pageContext.request.contextPath}/images/error/404_msg.png"/>

    <p><a href="${pageContext.request.contextPath}/" >
        <img class="png"
             src="${pageContext.request.contextPath}/images/error/404_to_index.png"/></a>
    </p>
</div>
<div id="cloud" class="png"></div>
<!-- 代码 结束 -->

</body>
</html>
