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
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="../script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="../script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="../css/bootstrap.min.css">
    <script>
        $(function () {
            document.oncontextmenu = function () {
                return false
            };
            $("#exit").click(function () {
                window.parent.location.href = "${pageContext.request.contextPath}/";
                var username = $("#username").text();
                var flag = confirm(username + "你确定要退出吗？");
                if (!flag) {
                    return false;
                }
            });
        })
    </script>
    <style>
        body {
            border-right: solid 1px #666;
            padding: 6px;
            background: #f2f2f2;
        }

        #nav a {
            font-size: 14px;
            border-left: 4px solid #478de4;
            text-align: center;
            display: block;
            height: 56px;
            border-left: 4px solid transparent;
            line-height: 56px;
            text-decoration: none;
            color: #4c4c4c;
            margin-top: 10px
        }

        #nav a:link {
            /*超链接正常状态下的样式*/
            color: #030999; /*文字颜色*/
            text-decoration: none; /*无下划线*/
        }

        #nav a:hover {
            /*鼠标经过超链接时的样式*/
            color: #06c; /*文字颜色*/
            text-decoration: no-underline; /*下划线*/
            /*background-color: #269abc;*/
        }

        #nav a:visited {
            /*访问过超链接时的样式*/
            color: #333333; /*文字颜色*/
            text-decoration: none; /*无下划线*/
        }

        #nav a:focus {
            /*点击后的样式*/
            /*color: #06c;*/
            border-left: 4px solid #478de4;
            background: #e1e4e5;
            color: #478de4;
        }

        .menu_list {
            display: block;
            height: 56px;
            border-left: 4px solid transparent;
            line-height: 56px;
            text-decoration: none;
            color: #4c4c4c;
        }

        .icon.view {
            height: 36px;
            vertical-align: middle;
        }

        /*#userView a{*/
        /*margin-top: 10px;*/
        /*font-size: 16px;*/
        /*}*/
        #usericon {
            width: auto;
            text-align: center;
        }

        #showname {
            padding-top: 10px;
            font-size: 20px;
            text-align: center;
        }

        #click {
            clear: both;
            padding-top: 15px;
        }

        #click a {
            color: #4c4c4c;
            text-decoration: none;
            font-size: 17px;
        }
    </style>
</head>
<body>
<div id="nav">
    <%--<h2>所有类目</h2>--%>
    <a href="${pageContext.request.contextPath}/employee/studentDetail" target="content" class="menu_list">
        <img src="${pageContext.request.contextPath}/images/view.png" class="icon view"/>
        <span>查看学生信息</span>
    </a>
    <a href="${pageContext.request.contextPath}/employee/manageStudentList" target="content" class="menu_list">
        <img src="${pageContext.request.contextPath}/images/manage.png" class="icon view"/>
        <span>学生信息管理</span>
    </a>
    <a href="${pageContext.request.contextPath}/attend/attendRecord" target="content" class="menu_list">
        <img src="${pageContext.request.contextPath}/images/attend.png" class="icon view"/>
        <span>学生考勤情况</span>
    </a>
    <a href="${pageContext.request.contextPath}/approve/approveList" target="content" class="menu_list">
        <img src="${pageContext.request.contextPath}/images/attend.png" class="icon view"/>
        <span>考勤记录审批</span>
    </a>

</div>
<hr>
<div id="userView">
    <div>
        <div id="usericon">
            <img src="${pageContext.request.contextPath}/images/user.png">
        </div>
        <div id="showname">
            <span> 管理员</span>
            <span id="username">${username}</span>
        </div>
    </div>

    <div id="click">
        <a href="${pageContext.request.contextPath}/password/viewChangePassword" target="content"
           id="changePassword">修改密码</a>
        <a href="${pageContext.request.contextPath}/user/Logout" id="exit">安全退出</a>
    </div>
</div>
</body>

</html>
