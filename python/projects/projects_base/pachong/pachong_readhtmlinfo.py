print('---------------------------读取网页信息----------')

import urllib.request ;
import zlib
#url='https://www.baidu.com/';
#url='http://www.qq.com/'
#uopen = urllib.request.urlopen(url);
#rbytes=uopen.read();
#decompressed_data = zlib.decompress(rbytes ,16+zlib.MAX_WBITS)
#text = decompressed_data.decode('utf8')
#print(text)
#print(uopen.info())

#fp = urllib.request.urlopen('http://www.sina.com')
#mybytes = fp.read()
#decompressed_data = zlib.decompress(mybytes ,16+zlib.MAX_WBITS)
#text = decompressed_data.decode('utf8')
#print(text)
#print(text)
#print(fp.info())

url='http://www.baidu.com/'
def contentSubUrl(content):
    links=[];
    return  links;
def getHtml(url):
    page=urllib.request.urlopen(url);
    html=page.read().decode(encoding='utf-8',errors='strict');
    print(type(html))
    return html;
content=getHtml(url);
print(content.index('把百度设为主页'))
links=contentSubUrl(content);
print(links)



