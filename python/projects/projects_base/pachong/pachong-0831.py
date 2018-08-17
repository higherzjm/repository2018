import threading
import urllib.request
import time
import pymysql.cursors
from bs4 import BeautifulSoup
from datetime import datetime

class DbTest:
    connect=''
    cursor='';
    brands=[];
    existurlbrands =dict();
    allurlsforlevel=set();
    allurlsforlevel2 = set();
    allurls=set();
    def __init__(self):
        self.brands = ['Birkenstock', 'Birkens', 'red bull', 'Birki', 'ndsi', 'timberland', 'toms', 'monsoon',
                  'abercrombie', 'watch', 'sexy lingerie', 'lululemon', 'ghd', 'ghi', 'nintendo', 'DVD',
                  'GOLF', 'hollister ', 'fitch', 'omega', 'rolex', 'celine', 'louis vuitton', 'tiffany',
                  'gucci', 'chanel'];
        self.connect = pymysql.Connect(
            #host='192.168.1.14',
            host='127.0.0.1',
            #host='192.168.4.4',
            port=3306,
            user='root',
            #user='paytosWrite',
            passwd='Abc123456',
            #passwd='Tuofu@123456',
            db='platform',
            charset='utf8'
        )
        self.cursor = self.connect.cursor();
    def queryBrands(self,sql):
        datas=self.cursor.execute(sql);
        self.cursor.close()
        info = self.cursor.fetchmany(datas)
        for ii in info:
            #print(type(ii))
            self.brands.append(ii[0]);
    def queryWebsites(self,sql):
        datas=self.cursor.execute(sql);
        querywebsites = self.cursor.fetchmany(datas)
        for querywebsite in querywebsites:
            self.allurls.add(querywebsite[0])
            self.allurlsforlevel.add('1'+'@'+querywebsite[0]+'@'+querywebsite[0]);
    def httprequert(this):
        while this.allurlsforlevel.__len__()!=0:
            splits =this.allurlsforlevel.pop().split('@');
            url = splits[1];
            level = splits[0]
            superurl = splits[2]
            url=this.parseUrl(url);
            print('thread name:',threading.current_thread().getName(),':',url)
            #print('level',level,'url',url);
            pttpTimeOut=2;
            if int(level)==1:
                pttpTimeOut = 5;
            try:
                headers = {'User-Agent': 'User-Agent:Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36'}
                #headers = {'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0'};
                req = urllib.request.Request(url=url, headers=headers)
                page = urllib.request.urlopen(req,timeout=pttpTimeOut);
                content = page.read().decode(encoding='utf-8', errors='strict');
                this.parseContent(content,level,superurl);
            except  Exception as e:
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

        for brand in self.brands:
            if content.lower().find(brand.lower())!=-1 and brand not in existbrands:
                print('存在敏感关键字:',brand);
                existbrands.append(brand);


        self.existurlbrands.update({superurl: existbrands});
        level=int(level);
        if level<1:
          self.getsubUrls(content,level,superurl);


    def getsubUrls(self,content,level,superurl):
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
                self.allurls.add(suburl);
                suburl=str(level)+'@'+suburl+'@'+superurl;
                self.allurlsforlevel.add(suburl);
        except Exception as e:
            print('解析子网址异常 e:',e);

    def updateAnalyzeinfo(self,sql,parmas):
        try:
            print('sql:',sql);
            print('parmas:', parmas);
            self.cursor.execute(sql % parmas);
            self.connect.commit();
        except Exception as e:
            print('e:',e)
    def runrepeat(self,datest):
        for urls2 in datest.allurlsforlevel2:
            datest.allurlsforlevel.add(urls2);

        x=1.1;
        if datest.allurlsforlevel2.__len__()==1:
            x=1;
        threads = []
        for i in range(1, int(datest.allurlsforlevel.__len__() / x) + 1):
            try:
                t = threading.Thread(target=datest.httprequert)  # 无参数的情况
                t.setName("thread2: " + str(i));
                threads.append(t)
            except Exception as e:
                print('e:',e)

        print('threads num:', threads.__len__());
        for t in threads:
            # t.setDaemon(True)
            t.start();

        while 1 == 1:
            if datest.allurlsforlevel.__len__() == 0:
                time.sleep(10)
                if datest.allurlsforlevel.__len__() == 0:
                    print('existbrands', datest.existurlbrands)
                    print('existbrands', datest.existurlbrands.keys())
                    print('allurls', datest.allurls.__len__())
                    print('allurlsforlevel', datest.allurlsforlevel.__len__())
                    for k, v in datest.existurlbrands.items():
                        sql = "UPDATE  platform.mer_site m  set m.analyze_status='%s' ,m.analyze_remark='%s',m.analyze_time='%s' where m.URL='%s' ";
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
                            analyze_remark = '不存关键字'
                        dt = datetime.now();
                        params = (analyze_status, analyze_remark, dt, k);
                        try:
                            datest.updateAnalyzeinfo(sql, params);
                        except Exception as e:
                            print('e:', e)
                            continue;

                    datest.cursor.close();
                    break;


if __name__ == '__main__':
    datest=DbTest();
    #datest.queryBrands("SELECT  p.BANBRAND  from  CUBE_BANBRAND p  where p.TYPE='0'");
    '''
    datest.brands=['Birkenstock', 'Birkens', 'red bull', 'Birki','ndsi', 'timberland', 'toms', 'monsoon',
                   'abercrombie', 'watch', 'sexy lingerie', 'lululemon', 'ghd', 'ghi','nintendo', 'DVD',
                   'GOLF', 'hollister ', 'fitch','omega', 'rolex', 'celine', 'louis vuitton', 'tiffany',
                   'gucci', 'chanel']
    '''
    datest.queryWebsites("SELECT  p.URL  from  platform.mer_site p  where p.CHECK_STATE='1' and DATE_FORMAT(p.CREATE_TIME,'%Y%m%d')='20170913'");

    print('website num:',datest.allurls.__len__());

    threads = []
    for i in range(1,int(datest.allurls.__len__()/1.1)+2):
        t = threading.Thread(target=datest.httprequert)  # 无参数的情况
        t.setName("thread1:"+str(i));
        threads.append(t)

    print('threads num:', threads.__len__());
    for t in threads:
    # t.setDaemon(True)
       t.start();

    newexistkeywords=dict();
    x=1;
    while x==1:
        #print('allurlsforlevel length:',datest.allurlsforlevel.__len__())
        if datest.allurlsforlevel.__len__()==0:
            time.sleep(1200)
            if datest.allurlsforlevel.__len__() == 0:
                    print('existbrands',datest.existurlbrands)
                    print('existbrands', datest.existurlbrands.keys())
                    print('allurls',datest.allurls.__len__())
                    print('allurlsforlevel',datest.allurlsforlevel.__len__())
                    for k,v in datest.existurlbrands.items():
                        sql="UPDATE  platform.mer_site m  set m.analyze_status='%s' ,m.analyze_remark='%s',m.analyze_time='%s' where m.URL='%s' ";
                        if list(v).__len__() > 0:
                            if list(v)[0]=='无法访问':
                                analyze_status = '无法访问';
                                analyze_remark = '无法访问';
                            else:
                                analyze_status = '分析不通过';
                                str='';
                                for a in v:
                                    if str=='':
                                        str=a;
                                    else:
                                        str=str+','+a;
                                analyze_remark=str;
                        else:
                            analyze_status='分析通过';
                            analyze_remark='不存关键字'
                        dt = datetime.now();
                        params=(analyze_status,analyze_remark,dt,k);
                        try:
                            datest.updateAnalyzeinfo(sql,params);
                        except Exception as e:
                            print('e:',e)
                            continue;

                    x = 2;
                    datest.allurlsforlevel.clear();
                    #datest.runrepeat(datest);
                    #datest.cursor.close();
                  # print('newexistkeywords:',newexistkeywords);