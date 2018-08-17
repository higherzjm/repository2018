# -*- coding: UTF-8 -*-
#服务端
import socket
s = socket.socket() #生成套接字
host = socket.gethostname() #获取本机地址,或直接用127.0.0.1
port = 25365 #端口
s.bind((host,port)) #绑定地址到套接字,这里参数使用元组
s.listen(5) #最大连接数
conn, addr = s.accept() #与客户端连接线路

while True:
    try:
        print(addr,'等待中.....')
        client_data = conn.recv(1024)  #接收消息
        print("客户端:",client_data)
        send_data = client_data.upper()#处理接受到的消息
        conn.send(send_data)#发送消息
    except Exception:
        break #异常直接退出
conn.close()#关闭连接

