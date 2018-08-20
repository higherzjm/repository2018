<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
<a href="send_jsm_merssagecontroller/sendmsg.do" >jms消息队列调用发送消息</a>&nbsp;
<br/><br/>

</body>
</html>
