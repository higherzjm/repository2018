<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <script src="style/js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <title>Title</title>
    <script type="text/javascript">

        function velocity_base() {
            window.location.href="velocitycontrol/velocity_base.do";

        }

        function  velocity_table() {
            window.location.href="velocitycontrol/velocity_table.do";
        }
    </script>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
<input type="button" onclick="velocity_base()" value="基本测试"/>&nbsp;
<input type="button" onclick="velocity_table()" value="table测试"/>&nbsp;

</body>
</html>
