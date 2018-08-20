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
        function httpSessionAttributeListener() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/httpSessionAttributeListener_addsession.do",
                data : "",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });

            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/httpSessionAttributeListener_newsession.do",
                data :"",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });
        }

        function servletrequestwrapper() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/servletrequestwrapper.do",
                data : {studentname:"张三"},
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });
        }
        function servletresponsewrapper() {
            $.ajax( {
                type : "POST",//(默认: "GET") 请求方式 ("POST" 或 "GET")，
                url : "MainController/servletresponsewrapper.do",
                data :"",
                async: false,
                dataType : "text",
                success : function(data) {
                    alert("success："+data)

                } ,
                error:function(data){
                    alert("error："+data)
                }
            });
        }
        function Servlet_returntext() {
            var url = "servlet_returntext.do?schoolClass=高三6班";
            $.get(url,null,function(data){
                alert("success："+data);
            });
        }
        function Servlet_returnpage() {
            window.location.href="servlet_returnpage.do";

        }
        function Servlet_redirectpage() {
            window.location.href="Servlet_redirectpage.do";

        }
    </script>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
<input type="button" onclick="Servlet_returntext()" value="servlet返回文本数据"/>&nbsp;
<input type="button" onclick="Servlet_returnpage()" value="servlet返回servlet页面"/>&nbsp;
<input type="button" onclick="Servlet_redirectpage()" value="servlet返回jsp页面"/>&nbsp;<br/><br/>
<br/><br/>
<input type="button" onclick="httpSessionAttributeListener()" value="Listener->httpSessionAttributeListener"/>&nbsp;
<input type="button" onclick="servletrequestwrapper()" value="filter->servletrequestwrapper"/>&nbsp;
<input type="button" onclick="servletresponsewrapper()" value="filter->servletresponsewrapper"/>&nbsp;
<br/><br/>

</body>
</html>
