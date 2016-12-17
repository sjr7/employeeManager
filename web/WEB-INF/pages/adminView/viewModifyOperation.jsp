<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/17/017
  Time: 19:48
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
    <title>修改学生信息</title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}/script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script>
        $(function () {
            $(window).bind("beforeunload", function () {
                return "您想跳转到别的窗口吗？";
            });
            $("#id").blur(function () {
                var val = this.value;
                val = $.trim(val);
                this.value = val;
                var reg = /^[0-9]*$/;
                if (!reg.test(val)) {
                    alert("学号只运行输入数字");
                }
            });
            $("#userName").blur(function () {
                var val = this.value;
                val = $.trim(val);
                this.value = val;
                var reg = /[\u4E00-\u9FA5\uF900-\uFA2D]/;
                if (!reg.test(val)) {
                    alert("姓名只能为汉字");
                }
            });
            $("#bedroom").blur(function () {
                var val = this.value;
                val = $.trim(val);
                this.value = val;
                var reg = /^[0-9]\#\d{3}$/;   //匹配第一个为0-9当中的一个，、#为普通字符#，\d为允许出现数字,{3}为出现3次
                if (!reg.test(val)) {
                    alert("寝室号样例为  4#608  ，按照自身寝室号修改");
                }
            });
            $("#tel").blur(function () {
                var val = this.value;
                val = $.trim(val);
                this.value = val;
                var reg = /^1[0-9]{10}$/;
                if (!reg.test(val)) {
                    alert("电话号码为中国大陆普通11位电话号码");
                }
            });
            $("#submit").click(function () {
                var flag = confirm("你确定要提交修改吗?");
                if (!flag) {
                    return false;
                }
            })
        })
    </script>
</head>
<body>
<form action="${pageContext.request.contextPath}/employee/modifyOperation/${employee.id}" method="post" role="form">
    <%--<input type="hidden" value="${student.num}" name="num">--%>
    <div class="form-group" style="width: 400px;margin: 0 auto">
        <input type="hidden" class="form-control " id="id" name="id" value="${employee.id}" required/>
        <br>
        <label>登陆账号</label>
        <input type="text" class="form-control " id="account" name="account" value="${employee.account}"
               required/><br>


        <label>姓名</label>
        <input type="text" class="form-control " id="userName" name="userName" value="${employee.username}"
               required/><br>



        <label>班级</label>
        <input type="text" class="form-control " id="className" name="className" value="${employee.className}"
               required/><br>


        <label>电话</label>
        <input type="text" class="form-control " id="tel" name="tel" value="${employee.tel}" required/><br>

        <label>寝室号</label>
        <input type="text" class="form-control " id="bedroom" name="bedroom" value="${employee.bedroom}" required/><br>

        <input class="btn btn-warning btn-lg " id="submit" type="submit" value="提交"/>
        <a href="${pageContext.request.contextPath}/employee/manageStudentList" class="btn btn-primary btn-lg index">返回学生信息列表</a>
    </div>
</form>
</body>
</html>
