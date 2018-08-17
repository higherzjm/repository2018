import urllib
from bs4 import BeautifulSoup

#字符串转为utf-8 utf-16 gbk编码
def str_encode(str):
    encode_utf_8=str.encode('utf-8');
    encode_gbk=str.encode('gbk');
    print('encode_gbk', encode_gbk);
    decode_gbk = encode_gbk.decode(encoding='gbk', errors='strict');  # 解密gbk编码的字符
    print('decode_gbk', decode_gbk);
    encode_utf_16=str.encode('utf-16');
    print('encode_utf_16', encode_utf_16);
    decode_utf_16 = encode_utf_16.decode(encoding='utf-16', errors='strict');  # 解密uutf-16编码的字符
    print('decode_utf_16', decode_utf_16);
    return encode_utf_8;
#从utf-8 utf-16 gbk编码转码到字符串
def str_decode(encode):
    decodestr=BeautifulSoup(encode, 'html.parser', from_encoding='utf-8');
    print('decode_utf_8--BeautifulSoup', decodestr);
    str=encode.decode(encoding='utf-8',errors='strict');#解密utf-8编码的字符
    return  str;


str='江津区企业大全|江津区企业黄页|江津区企业名录-第1页-中国网库';
str='江津';
encode=str_encode(str);
print('encode_utf_8', encode,type(encode));
decode=str_decode(encode);
print('decode_utf_8---decode', decode);

print('ascii:',ascii(str));#ascii
print('chr:',chr(97),chr(98));#Unicode编码为x的字符