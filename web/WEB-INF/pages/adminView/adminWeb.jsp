<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/1/001
  Time: 18:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@include file="/common/common.jsp"%>--%>

<html>
<head>
    <title>管理员主页</title>
    <frameset rows="85,*,40" frameborder="NO" noresize Borders="NO"
              framespacing="0">

        <frame name="topFrame" frameborder="NO" scrolling="NO" noresize
               Borders="NO" src="${pageContext.request.contextPath}/common/top.jsp" marginwidth="value"
               marginheight="value">

        <frameset rows="*" cols="180,*" border="0" noresize framespacing="2">

            <frame name="menu" src="${pageContext.request.contextPath}/common/menu.jsp" border="0"
                   scrolling="auto" marginwidth="0" leftmargin="0" marginheight="0"
                   APPLICATION="yes">

            <frame name="content" src="${pageContext.request.contextPath}/common/welcome.jsp"
                   frameborder="no" marginwidth="0" marginheight="0" APPLICATION="yes">

        </frameset>

        <frame src="${pageContext.request.contextPath}/common/footer.jsp" name="#" frameborder="NO"
               scrolling="NO" noresize marginwidth="0" marginheight="0" Borders="NO">

    </frameset>
</head>
<body>

</body>
</html>
