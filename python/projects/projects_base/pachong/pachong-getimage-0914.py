import urllib.request
import re
#py抓取页面图片并保存到本地

#获取页面信息
def getHtml(url):
    html = urllib.request.urlopen(url).read()
    return html

#通过正则获取图片
def getImg(html):
    reg = 'src="(.+?\.jpg)" pic_ext'
    imgre = re.compile(reg)
    imglist = re.findall(imgre,html)
    return imglist

html = getHtml("http://tieba.baidu.com/p/2460150866")

list=getImg(html.decode())

#循环把图片存到本地
x = 0
for imgurl in list:
    print('imgurl:',imgurl)
    print(x)
    urllib.request.urlretrieve(imgurl,'F:/tuofu2017/learn/Python/files/iamges/img-%s.jpg'% x)
    x+=1

print("done")