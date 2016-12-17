<%--
  Created by IntelliJ IDEA.
  User: 孙建荣
  Date: 2016/11/11/011
  Time: 12:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="${pageContext.request.contextPath}/script/jquery-1.7.2.js"></script>
    <script>
        function submitExcel(){
            var ExcelFile=$("#ExcelFile").val();
            if(ExcelFile == ""){
                alert("请选择要上传的文件");
                return false;
            }
            if(ExcelFile.indexOf('.xlsx') == -1){
                alert("你选择的格式不正确");
                return false;
            }
            $("#ExcelFile").submit();

        }

    </script>
    <title>上传学生资料</title>
</head>
<body>
    <h1>请注意，不要随意上传Excel文件，按照模板的格式进行编辑，然后再上传</h1>

      <a href="downloadUploadExcelTemplate">下载模板</a>
      <a href="ExcelSQLDate">下载学生数据</a>


    <form action="uploadDatabase" enctype="multipart/form-data" method="post">
        <input type="file" name="file" id="ExcelFile">
        <input type="submit" value="上传学生资料" onclick="submitExcel()">

    </form>

    <form action="uploadAccountDatabase" enctype="multipart/form-data" method="post">
        <input type="file" name="file" id="UserFile">
        <input type="submit" value="上传用户资料" onclick="submitExcel()">

    </form>

</body>
</html>
