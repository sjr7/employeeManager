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
<html>
<head>
    <title></title>
    <!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
    <script src="${pageContext.request.contextPath}/script/jquery-3.1.1.min.js"></script>
    <!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
    <script src="${pageContext.request.contextPath}//script/bootstrap.min.js"></script>
    <!-- 新 Bootstrap 核心 CSS 文件 -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}//css/bootstrap.min.css">
    <style>
        /* 定义模态对话框外面的覆盖层样式 */
        #modal-overlay {
            visibility: hidden;
            position: absolute;   /* 使用绝对定位或固定定位  */
            left: 0px;
            top: 0px;
            width:100%;
            height:100%;
            text-align:center;
            z-index: 1000;
            background-color: #333;
            opacity: 0.5;   /* 背景半透明 */
        }
        /* 模态框样式 */
        .modal-data{
            width:300px;
            margin: 100px auto;
            background-color: #fff;
            border:1px solid #000;
            padding:15px;
            text-align:center;
        }
    </style>

    <script>

        function overlay(){
            var e1 = document.getElementById('modal-overlay');
            e1.style.visibility =  (e1.style.visibility == "visible"  ) ? "hidden" : "visible";
        }
    </script>

</head>
<body>
<%--<form action="#" method="post">--%>
    <%--<label>原始密码</label>--%>
    <%--<input id="oldPassword" name="oldPassword">--%>
    <%--<label>修改密码</label>--%>
    <%--<input id="newPassword" name="newPassword">--%>
    <%--<input type="submit" value="修改">--%>

<%--</form>--%>
    <div id="modal-overlay">
        <%--<form action="#" method="post">--%>
            <%--<label>原始密码</label>--%>
            <%--<input id="oldPassword" name="oldPassword">--%>
            <%--<label>修改密码</label>--%>
            <%--<input id="newPassword" name="newPassword">--%>
            <%--<input type="submit" value="修改">--%>

        <%--</form>--%>
        <div class="modal-data">
            <p>一个很简单的模态对话框 </p>
            <p>点击<a onclick="overlay()" href="">这里</a>关闭</p>
        </div>
    </div>

    <a href="#" onclick="overlay()">点击查看</a>



<h2>创建模态框（Modal）</h2>
<!-- 按钮触发模态框 -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">开始演示模态框</button>
<!-- 模态框（Modal） -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">模态框（Modal）标题</h4>
            </div>
            <div class="modal-body">在这里添加一些文本</div>

                <form role="form" >
                    <div class="form-group col-xs-7">
                        <label for="oldPassword">原始密码</label>
                        <input type="password" class="form-control" id="oldPassword" placeholder="请输入原始密码">
                    </div>
                    <div class="form-group col-xs-7">
                        <label for="newPassword">新密码</label>
                        <input type="text" class="form-control" id="newPassword" placeholder="请输入新密码">
                    </div>

                    <%--<button type="submit" class="btn btn-default">点击修改</button>--%>

                    <div class="modal-footer">
                        <%--<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>--%>
                        <button type="submit" class="btn btn-primary">提交更改</button>
                    </div>
                </form>



        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>

</body>
</html>
