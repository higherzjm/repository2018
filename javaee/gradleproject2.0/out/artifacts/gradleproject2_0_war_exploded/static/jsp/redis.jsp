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
        function sendmsg() {
            console.info("redis  发送消息")
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "send_redis_merssagecontroller/sendmsg.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert(data)

                } ,
                error:function(data){
                    console.log('error:'+data);
                }
            });
        }

    </script>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
<input type="button" value="redis消息队列调用发送消息" onclick="sendmsg()"/>&nbsp;
<br/><br/>

</body>
</html>
