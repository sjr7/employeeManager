<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/12/5/005
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <title>修改密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script>
        $(function () {
            $("#submit").click(function () {
                var newPassword = $("#newPassword").val();
                var oldPassword = $("#oldPassword").val();
                var repeatPassword = $("#repeatPassword").val();
                $.ajax({
                    type: "post",
                    url: "${pageContext.request.contextPath}/password/changePasswordByAjax?newPassword=" + newPassword + "&oldPassword=" + oldPassword + "&repeatPassword=" + repeatPassword,
                    success: function (result) {
                        if (result === "true") {
                            alert("密码修改成功")
                        }
                        else if (result === "false") {
                            alert("密码修改中途出错");
                        }
                    }
                });
            })
        })

    </script>
</head>
<body>

<div class="container">

    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/password/changePassword">

        <div class="form-group">
            <label for="oldPassword" class="col-sm-2 control-label">原始密码</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="oldPassword" name="oldPassword" placeholder="请输入原始密码">
            </div>
        </div>

        <div class="form-group">
            <label for="newPassword" class="col-sm-2 control-label">请输入新密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="newPassword" name="newPassword" placeholder="请输入新密码">
            </div>
        </div>

        <div class="form-group">
            <label for="repeatPassword" class="col-sm-2 control-label">请再次输入新密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="repeatPassword" name="repeatPassword"
                       placeholder="请再次输入新密码">
            </div>
        </div>


        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
        </div>
    </form>


</div>
</body>
</html>
