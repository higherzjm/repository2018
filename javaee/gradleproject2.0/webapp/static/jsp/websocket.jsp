<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+request.getServerName() + ":" + request.getServerPort() + path + "/style/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <script src="js/jquery.min.js" type="text/javascript" charset="utf-8"></script>
    <script type="text/javascript">
        $(function() {
            $("#btnConnection").click(function() {
                //实现化WebSocket对象，指定要连接的服务器地址与端口
                var host='ws://localhost:8080/gradleproject1.0/websocket';
                var socket;
                if ('WebSocket' in window) {
                    socket = new WebSocket(host);
                } else if ('MozWebSocket' in window) {
                    socket = new MozWebSocket(host);
                } else {
                    alert('提示', '当前浏览器不支持websocket，请更换浏览器');
                    return;
                } //打开事件
                socket.onopen = function() {
                    alert("Socket 已打开");
                    socket.send("这是来自客户端的消息" + location.href + new Date());
                };
                //获得消息事件
                socket.onmessage = function(msg) {
                    alert(msg.data);
                };
                //关闭事件
                socket.onclose = function() {
                    alert("Socket已关闭");
                };
                //发生了错误事件
                socket.onerror = function() {
                    alert("发生了错误");
                }
            });

            $("#btnSend").click(function() {
                socket.send("这是来自客户端的消息" + location.href + new Date());
            });

            $("#btnClose").click(function() {
                socket.close();
            });
        });

        var websocket = new WebSocket('ws://localhost:8080/gradleproject1.0/webSocketServer/bing');

        websocket.onopen = function (evnt) {
            document.getElementById('messages').innerHTML
                = 'Connection established';
        };
        websocket.onmessage = function (evnt) {
            document.getElementById('messages').innerHTML
                += '<br />' + event.data;
        };
        websocket.onerror = function (evnt) {
            alert('发生了错误:'+event)
        };
        websocket.onclose = function (evnt) {
            alert('关闭:'+event)
        };

        function start() {
            websocket.send('hello');
            return false;
        }
    </script>
</head>
<body style="text-align: left;padding: 10px 0 0 10px">
  <input  type="button"  value="websever发送消息" style="background-color: red;color: white;size: 20px" id="btnConnection"/><br/><br/>
  <input type="button" value="Start" onclick="start()"/>
  <div id="messages"></div>

</body>
</html>
