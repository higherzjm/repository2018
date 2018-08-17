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
    httpTimeOut=10;
    ucindex=0;
    all_UserAgents=['Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
                    'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11',
                    'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1',
                    'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)',
                    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/536.11 (KHTML, like Gecko) Chrome/20.0.1132.11 TaoBrowser/2.0 Safari/536.11',
                    'Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0',
                    'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/30.0.1599.101 Safari/537.36'
                    ];
    existurlbrands =dict();
    allurlsforlevel=set();
    usrlsforsecondlevel=set();
    usrlsforthirdlevel = set();
    allurlsforlevel2 = set();
    allurls=set();
    allsuperurls=set();
    def __init__(self):
        self.keywords = ['Birkenstock', 'Birkens', 'red bull', 'Birki', 'ndsi', 'timberland', 'toms', 'monsoon',
                  'abercrombie', 'watch', 'sexy lingerie', 'lululemon', 'ghd', 'ghi', 'nintendo', 'DVD',
                  'GOLF', 'hollister ', 'fitch', 'omega', 'rolex', 'celine', 'louis vuitton', 'tiffany',
                  'gucci', 'chanel','Clouds','Disk','cigarette'];
        self.keywords=['dyson']
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
            try:
                headers=this.all_UserAgents[this.ucindex];
                #print('headers:',headers)
                headers = {'User-Agent':headers};

                req = urllib.request.Request(url=url, headers=headers)
                page = urllib.request.urlopen(req,timeout=this.httpTimeOut);
                content = page.read().decode(encoding='utf-8', errors='strict');
                this.parseContent(content,level,superurl);
            except  Exception as e:
                url = url.strip("http://");
                if int(runnum)==1:
                    print(url, 'httprequert--第一次请求发生异常', e)
                    this.usrlsforsecondlevel.add('1' + '@' +url+ '@' +url+'@2');
                # elif int(runnum)==2:
                #     print(url, 'httprequert--第二次请求发生异常', e)
                #     this.usrlsforthirdlevel.add('1' + '@' + url + '@' + url + '@3');
                # else:
                #     print(url, 'httprequert--第三次请求发生异常', e)
                #     list = [];
                #     list.append('无法访问');
                #     this.existurlbrands.update({superurl: list});

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
        #writefile="F:/learn/Python/info/results.xls"
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
    datest.ucindex=0;
    readpath="F:/tuofu2017/learn/Python/files/excel/网站.xls";
    #readpath="F:/learn/Python/info/网站.xls"
    datest.getWebsitesByExcel(readpath);

    print('website num:',datest.allurls.__len__());
    mainthreads = []
    n=1.1;
    n=3;
    for i in range(1,int(datest.allurls.__len__()/n)+2):
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
    waitsecond=600;
    while x==1:
        #print('allurlsforlevel num:',datest.allurlsforlevel.__len__())
        if datest.allurlsforlevel.__len__()==0:
            if y==0:
               time.sleep(waitsecond)
            y=1;
            if datest.usrlsforsecondlevel.__len__()!=0:
                for urlforsecondlevel in datest.usrlsforsecondlevel:
                    datest.allurlsforlevel.add(urlforsecondlevel);
                datest.ucindex=1;
                datest.httpTimeOut=15;
                datest.usrlsforsecondlevel.clear();
                secondruntimethread = []
                for i in range(1, int(datest.allurlsforlevel.__len__() / n) + 2):
                    t = threading.Thread(target=datest.httprequert)  # 无参数的情况
                    t.setName("main thread:" + str(i));
                    secondruntimethread.append(t)

                print('secondruntime threads num:', secondruntimethread.__len__());
                for t in secondruntimethread:
                    t.start();

            if datest.allurlsforlevel.__len__() == 0:
                time.sleep(int(waitsecond/2));
                if datest.usrlsforthirdlevel.__len__()!=0:
                    for usrlforthirdlevel in datest.usrlsforthirdlevel:
                        datest.allurlsforlevel.add(usrlforthirdlevel);
                    datest.ucindex = 2;
                    datest.httpTimeOut=20;
                thirdruntimethread = []
                datest.usrlsforthirdlevel.clear();
                for i in range(1, int(datest.allurlsforlevel.__len__() / n) + 2):
                    t = threading.Thread(target=datest.httprequert)  # 无参数的情况
                    t.setName("main thread:" + str(i));
                    thirdruntimethread.append(t)

                print('thirdruntimethread threads num:', thirdruntimethread.__len__());
                for t in thirdruntimethread:
                    t.start();

                time.sleep(int(waitsecond/3));
                if datest.allurlsforlevel.__len__() == 0  and datest.existurlbrands.__len__()==datest.allsuperurls.__len__():
                    print('allurls size:',datest.allurls.__len__());
                    datest.saveResultINexcel(datest);
                    x = 2;
                    print('end')
                    datest.allurlsforlevel.clear();

    endtime = datetime.now();
    print('endtime:', endtime);