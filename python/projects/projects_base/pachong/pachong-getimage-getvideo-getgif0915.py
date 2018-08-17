import urllib.request
import re
#py抓取页面图片并保存到本地

#获取页面信息
def gethtml(url):
    headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'};
    req = urllib.request.Request(url=url, headers=headers)
    html = urllib.request.urlopen(req).read()
    return html

#通过正则获取图片
def getimg(html):
    reg = 'src="(.+?\.jpg)"'
    imgre = re.compile(reg)
    imglist = re.findall(imgre,html)
    print('imglist',imglist)
    return imglist

def getvedio(html):
    reg = 'src="(.+?\.mp4)"'
    imgre = re.compile(reg)
    Vediolist = re.findall(imgre,html)
    print('Vediolist',Vediolist)
    return Vediolist
def getgif(html):
    reg = 'src="(.+?\.gif)"'
    imgre = re.compile(reg)
    giflist = re.findall(imgre,html)
    print('giflist',giflist)
    return giflist
def test_getimage(url):
    html = gethtml(url)
    content=html.decode(encoding='gbk', errors='strict');
    list=getimg(content)
    #循环把图片存到本地
    x = 0
    for imgurl in list:
        imgurl.replace("data-src=", "");
        #imgurl="http:"+imgurl;
        print('imgurl:', imgurl)
        print(x)
        urllib.request.urlretrieve(imgurl,'F:/tuofu2017/learn/Python/files/iamges/img-%s.jpg'% x)
        x+=1

    print("done")

def test_getvedio(url):
    html = gethtml(url)
    content=html.decode(encoding='UTF-8', errors='strict');
    list=getvedio(content)
    #循环把图片存到本地
    x = 0
    for vediourl in list:
        print('vediourl:', vediourl)
        print(x)
        urllib.request.urlretrieve(vediourl,'F:/tuofu2017/learn/Python/files/vedio/vedio-%s.mp4'% x)
        x+=1

    print("done")
def test_getgif(url):
    html = gethtml(url)
    content=html.decode(encoding='UTF-8', errors='strict');
    list=getgif(content)
    #循环把图片存到本地
    x = 0
    for gifurl in list:
        print('gifurl:', gifurl)
        print(x)
        urllib.request.urlretrieve(gifurl,'F:/tuofu2017/learn/Python/files/gif/gif-%s.gif'% x)
        x+=1

    print("done")
if __name__ == '__main__':
    url="https://www.1688.com/chanpin/-C3C0CDBC206973.html";
    test_getimage(url);

    url='http://www.thepaper.cn/newsDetail_forward_1866860'
    url='http://www.thepaper.cn/channel_26916'
    #test_getvedio(url);
    url = 'http://www.thepaper.cn/channel_26916'
    #test_getgif(url);