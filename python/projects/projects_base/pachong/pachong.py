# crawl.py
# 网络爬虫

from os import makedirs, unlink, sep
from os.path import dirname, exists, isdir, splitext
import urllib.request
from urllib.parse import urlparse
from sys import argv
import html.parser as h


#解析html
class MyHTMLParser(h.HTMLParser):
    def __init__(self):
        h.HTMLParser.__init__(self)
        self.links = []

    def handle_starttag(self, tag, attrs):
        # print "Encountered the beginning of a %s tag" % tag
        if tag == "a":
            if len(attrs) == 0:
                pass
            else:
                for (variable, value) in attrs:
                    if variable == "href":
                        if value != 'javascript:;' and value !='/':
                            self.links.append(value)

# 解析web页面
class Retriever:
    def __init__(self, url):
        self.url = url
        self.file = self.filename(url)

    # 根据url创建文件夹目录
    def filename(self, url, deffile = 'index.html'):
        parseedurl = urlparse(url, 'http:', 0)
        path = parseedurl[1] + parseedurl[2]
        ext = splitext(path)
        if ext[1] == '':
            if path[-1] == '':
                path += '/' + deffile
            if path[-1] == '/':
                path += deffile
        elif ext[1] == '.html':
            index  = str(path).rfind('/')
            path = path[:index] + path[index:-5] + '.html'
        else:
            path += '/' + deffile
        # url处理
        path = path.replace('"', "")
        if path.startswith('http://'):
            path = str(path)[7:]
        # 生成文件
        ldir = dirname('d://'+path)
        print('file path, %s' % ldir)
        # if sep != '/':
        #     ldir = ldir.replace('/', sep)
        if not isdir(ldir):
            if exists(ldir):
                unlink(ldir)
            else:
                makedirs(ldir)
        return 'd://'+path

    # 下载文件
    def download(self):
        print('download url %s file %s ' % (self.url, self.file))
        r = urllib.request.urlretrieve(self.url, self.file)
        return r

    # 获取所有链接
    def parseAndGetLinks(self):
        p = MyHTMLParser()
        htmlsource = urllib.request.urlopen(self.url).read()
        try:
            p.feed(htmlsource.decode('utf-8'))
        except UnicodeDecodeError:
            p.feed(htmlsource.decode('gb2312'))
        finally:
            p.close()
        # htmlsource = urllib.request.urlopen(self.url).read(200000)
        # linkslist = re.findall('[^/s]http://(.*?)[\s*\"]', htmlsource.decode('utf-8'))
        return p.links

class Crawler:
    count = 0

    def __init__(self, url):
        self.q = [url]
        self.seen = []
        self.dom = urlparse(url)[1]

    def getPage(self, url):
        #处理双引号
        if str(url).startswith("\""):
            url = url[1:]
        if str(url).endswith("\""):
            url = url[:-1]
        print('todo url, '+ url)
        r = Retriever(url)
        retval = r.download()
        if retval[0] == '*':
            print(retval+'---------- skip')
            return
        Crawler.count +=1
        self.seen.append(url)

        links = r.parseAndGetLinks()
        for eachlink in links:
            # 不是连接
            if eachlink[:4] != 'http' and eachlink.find('://') == -1:
                eachlink = urllib.request.urljoin(url,eachlink)

            # 跳过邮件
            if eachlink.find('mailto:') != -1:
                print('skip mailto, %s' % eachlink)
                continue
            # 跳过静态资源
            if eachlink.find('png') != -1:
                print('skip png, %s' % eachlink)
                continue

            if eachlink.find('js') != -1:
                print('skip js, %s' % eachlink)
                continue

            if eachlink.find('gif') != -1:
                print('skip gif, %s' % eachlink)
                continue

            if eachlink not in self.seen:
                # 不在同一一级域名
                if eachlink.find(self.dom) == -1:
                    pass
                    #print('not in this domain, %s' % eachlink)
                else:
                    if eachlink not in self.q:
                        print('add into q, %s' % eachlink)
                        self.q.append(eachlink)
                    else:
                        pass
                        #print('alreay in queue, %s ' % eachlink)
            else:
                print('alreay in processing, %s ' % eachlink)

    def go(self):
        while self.q:
            url = self.q.pop()
            self.getPage(url)
        else:
            print('结束')

def main():
    if len(argv) > 1:
        url = argv[1]
    else:
        # url = input('enter url: ')
        url = 'http://www.baidu.com'
        #url='http://www.paytos.com/';
        if not url:
            return
        robot = Crawler(url)
        robot.go()

if __name__ == '__main__':
        main()
