import threading
import urllib.request
import time
from bs4 import BeautifulSoup
from datetime import datetime
import xlrd
from openpyxl import Workbook
import re
lock = threading.Lock();
class DbTest:
    connect=''
    cursor='';
    keywords=[];
    existurlbrands =dict();
    allurlsforlevel=set();
    allurlsforlevel2 = set();
    allurls=set();
    allsuperurls=set();
    def __init__(self):
        self.keywords = ['Birkenstock', 'Birkens', 'red bull', 'Birki', 'ndsi', 'timberland', 'toms', 'monsoon',
                  'abercrombie', 'watch', 'sexy lingerie', 'lululemon', 'ghd', 'ghi', 'nintendo', 'DVD',
                  'GOLF', 'hollister ', 'fitch', 'omega', 'rolex', 'celine', 'louis vuitton', 'tiffany',
                  'gucci', 'chanel','Clouds','Disk','cigarette'];
    def getWebsitesByExcel(self,path):
        TC_workbook = xlrd.open_workbook(path)
        mysheet = TC_workbook.sheet_by_index(0);
        for rownum in range(1, mysheet.nrows):
            url=mysheet.row_values(rownum)[0];
            if url!=None and url!='':
                self.allurls.add(url)
                self.allsuperurls.add(url)
                #跑的层数@跑的网址@根网址@跑的次数
                self.allurlsforlevel.add('1' + '@' +url + '@' +url+'@'+'1');
    def httprequert(this):
        while this.allurlsforlevel.__len__()!=0:
            try:
                splits =this.allurlsforlevel.pop().split('@');
            except Exception as e:
                print('e',e);
                continue;
            level = splits[0]
            url = splits[1];
            superurl = splits[2]
            runnum = splits[3]
            url=this.parseUrl(url);
            print('thread name:',threading.current_thread().getName(),':',url)
            httpTimeOut=3;
            if int(level)==1:
                httpTimeOut = 8;
            try:

                #Safari
                headers={'User-Agent':
                             'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50'}
                #IE 9.0
                headers = {'User-Agent':'Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0)'};

                #Opera
                headers = {'User-Agent':'Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11'};
                #360浏览器
                #headers = {'User-Agent':'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)'};
                #Firefox
                #headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'};

                #Chrome
                #headers = {'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome'};

                req = urllib.request.Request(url=url, headers=headers)
                page = urllib.request.urlopen(req,timeout=httpTimeOut);
                content = page.read().decode(encoding='utf-8', errors='strict');
                this.parseContent(content,level,superurl);
            except  Exception as e:
                if int(runnum)<2:
                    this.allurlsforlevel.add('1' + '@' +url + '@' + superurl +'@'+str(int(runnum)+1));
                    print('无法访问，再跑一次')
                    continue;
                if int(level)==1:
                    list=[];
                    list.append('无法访问');
                    this.existurlbrands.update({superurl:list});
                    this.allurlsforlevel2.add('1' + '@' +url+ '@' +url);
                print(url,'httprequert--请求发生异常',e)
                continue;

    def parseUrl(self,url):
        if url.find('http://')==-1 and url.find('https://')==-1:
            url='http://'+url;

        if url[url.__len__()-1:url.__len__()]!='/':
            url=url+'/';
        return  url;

    def parseContent(self,content,level,superurl):
        if self.existurlbrands.get(superurl) == None:
            existbrands =[];
        else:
            existbrands=self.existurlbrands.get(superurl);

        for keyword in self.keywords:
            p1 = "\s"+keyword+"$|^"+keyword+"\s|\s"+keyword+"\s"
            pattern = re.compile(p1)
            a_list = pattern.findall(content.lower());
            if a_list.__len__()!=0 and keyword not in existbrands:
                print('存在敏感关键字:', keyword);
                existbrands.append(keyword);

        self.existurlbrands.update({superurl: existbrands});
        level=int(level);
        if level<1:
          self.getsubUrls(content,level,superurl);


    def getsubUrls(self,content,level,superurl):
        lock.acquire();  # 线程锁
        suburls=set();
        try:
            soup = BeautifulSoup(content, 'html.parser')
            level=level+1;
            for mulu in soup.find_all('a'):
                suburl=mulu.get('href');
                if suburl==None:
                    continue;
                if suburl in  self.allurls:
                    continue;
                if suburl.find('.')==-1:
                    continue;
                suburls.add(suburl);
                self.allurls.add(suburl);
                suburl=str(level)+'@'+suburl+'@'+superurl+'@3';
                self.allurlsforlevel.add(suburl);


        except Exception as e:
            print('解析子网址异常 e:',e);

        print('suburls',suburls);
        subthreads = []
        for i in range(1, int(suburls.__len__() / 1.1) + 2):
            t = threading.Thread(target=datest.httprequert)  # 无参数的情况
            t.setName(superurl+"----suburls_thread:" + str(i));
            subthreads.append(t)

        print('suburls_thread num:', subthreads.__len__());
        for t in subthreads:
            # t.setDaemon(True)
            t.start();
        lock.release();  # 释放锁
    def saveResultINexcel(self,datest):
        headers=list();
        headers.append('网址');
        headers.append('分析状态');
        headers.append('分析备注');

        alldatas=list();
        for k, v in datest.existurlbrands.items():
            data=list();
            data.append(k);
            if list(v).__len__() > 0:
                if list(v)[0] == '无法访问':
                    analyze_status = '无法访问';
                    analyze_remark = '无法访问';
                else:
                    analyze_status = '分析不通过';
                    str = '';
                    for a in v:
                        if str == '':
                            str = a;
                        else:
                            str = str + ',' + a;
                    analyze_remark = str;
            else:
                analyze_status = '分析通过';
                analyze_remark = '不存关键字';

            data.append(analyze_status);
            data.append(analyze_remark);
            alldatas.append(data);


        writefile = 'F:/tuofu2017/learn/Python/files/results.xls';
        wb = Workbook()
        ws = wb.worksheets[0];
        ws.append(headers);
        for data in alldatas:
            ws.append(list(data));
        wb.save(writefile);
        print('do success')

if __name__ == '__main__':
    begintime = datetime.now();
    print('begintime:',begintime);
    datest=DbTest();
    readpath="F:/tuofu2017/learn/Python/files/网站.xls"
    datest.getWebsitesByExcel(readpath);

    print('website num:',datest.allurls.__len__());
    waitsecond = 120;
    mainthreads = []
    for i in range(1,int(datest.allurls.__len__()/1.1)+2):
        t = threading.Thread(target=datest.httprequert)  # 无参数的情况
        t.setName("main thread:"+str(i));
        mainthreads.append(t)

    print('main threads num:', mainthreads.__len__());
    for t in mainthreads:
    # t.setDaemon(True)
       t.start();

    newexistkeywords=dict();
    x=1;
    y=0;
    while x==1:
        if datest.allurlsforlevel.__len__()==0:
            if y==0:
               time.sleep(waitsecond)
            y=1;
            if datest.allurlsforlevel.__len__() == 0 and datest.existurlbrands.__len__()==datest.allsuperurls.__len__():
                print('allurls size:',datest.allurls.__len__());
                datest.saveResultINexcel(datest);
                x = 2;
                print('end')
                datest.allurlsforlevel.clear();

    endtime = datetime.now();
    print('endtime:', endtime);