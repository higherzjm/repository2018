# -*- coding: UTF-8 -*-
#客户端
import socket
s = socket.socket() #生成套接字
host = socket.gethostname() #获取本机地址,或直接用127.0.0.1
port = 25365 #端口
s.connect((host,port)) #绑定地址到套接字,这里参数使用元组

while True:
    send_data = input("=>:").strip() #等待客户端输入
    if len(send_data) == 0: continue #防止服务端死循环
    #客户端发送消息
    s.sendall(bytes(send_data,encoding='utf-8'))#转换为字节
    #接受服务端返回数据
    server_reply = s.recv(1024)
    print(str(server_reply,encoding="utf-8"))#转为字符串输出
s.close()