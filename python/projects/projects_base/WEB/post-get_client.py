from pip._vendor import requests


'''
r = requests.get("http://vipmerchant.paytos.com/CubeICardMerchantConsole/NewUI/dataProvider/channellit_monitor.jsp?token=WOMGFBBWW")
print(r.text)

postdata = { 'token':'WOMGFBBWW' }
r = requests.post("http://vipmerchant.paytos.com/CubeICardMerchantConsole/NewUI/dataProvider/channellit_monitor.jsp",data=postdata)
print(r.text);
'''

postdata = { 'a':'abc123456','b':'张三' }
r = requests.post("http://192.168.1.41:8080/hello",data=postdata)
print(r.text);

r = requests.get("http://127.0.0.1:8080/hello2?a=1234555&b='李四'")
print(r.text);