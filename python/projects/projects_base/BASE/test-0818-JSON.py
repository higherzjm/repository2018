import json

# Python 字典类型转换为 JSON 对象
data1 = {
    'no' : 1,
    'name' : 'Runoob',
    'url' : 'http://www.runoob.com'
}

json_str = json.dumps(data1);#转码
print ("Python 原始数据：", repr(data1))
print ("dict JSON 对象：", json_str)

# 将 JSON 对象转换为 Python 字典
data2 = json.loads(json_str)#解码
print ("data2['name']: ", data2['name'])
print ("data2['url']: ", data2['url'])

a_list=[1,2,3,4,'a','b','c'];
print(type(a_list))
json_str = json.dumps(a_list);#转码
data2 = json.loads(json_str)#解码
print ("JSON loads：", data2,type(data2));
print ("list JSON 对象：", json_str,type(json_str));

a_tuple=(1,2,3,4,'a','b','c');
print(type(a_tuple))
json_str = json.dumps(a_tuple);#转码
data2 = json.loads(json_str)#解码
print ("JSON loads：", data2,type(data2));
print ("tuple JSON 对象：", json_str,type(json_str))