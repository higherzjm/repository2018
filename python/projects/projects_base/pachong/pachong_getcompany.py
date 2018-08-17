import urllib.request
import pymysql.cursors
from bs4 import BeautifulSoup
from datetime import datetime
import threading
# 上海
def shanghai():
    for i in range(1,1710):
        url = "http://shop.99114.com/list/area/101109_%s" %i
        print (url)
        html_doc = HtmlDownload(url)
        parser(html_doc,'上海')
        #print (html_doc)

def tianjin():
    for i in range(1, 539):
        url = "http://shop.99114.com/list/area/101102_%s" %i
        print (url)
        html_doc = HtmlDownload(url)
        parser(html_doc,'天津')

def chongqing():
    for i in range(1, 354):
        url = "http://shop.99114.com/list/area/101122_%s" %i
        print (url)
        html_doc = HtmlDownload(url)
        if html_doc=='':
            continue;
        parser(html_doc,'重庆')

def HtmlDownload(url):
    if url is None:
        return None
    user_agent = 'Mozilla/5.0 (compatible; MSIE5.5; Windows NT)'
    headers = {'User-Agents':user_agent}
    timeout = 2
    try:
     r = urllib.request.urlopen(url, timeout=5);
    except:
      print('异常再执行')
      r = urllib.request.urlopen(url, timeout=10);
    if r.code == 200:
        try:
          return r.read()
        except:
         return ''


def parser(html_cont,city):
    soup = BeautifulSoup(html_cont, 'html.parser', from_encoding='utf-8')
    #print (soup.select(".cony_div"))
    #print ('*******************************')
    #print(soup.find(class_='cony_div').find_all('a'))
    #print('*******************************')
    #print (soup)
    for mulu in soup.find(class_='cony_div').find_all('a'):
        href ='http://shop.99114.com'+ mulu.get('href')
        gs = mulu.string
        print('gs:',gs);
        # name = gs.replace("'","")
        # name = pymysql.escape_string(gs)
        # name = pymysql.escape_sequence(gs)
        try:
             db(href, gs,city)
        except:
            continue
        # print(href,name)

companylist=[];
def db2(href, gs,city):
    dt = datetime.now();
    data = (city, gs, href, dt)
    print('data:', data);
    companylist.append(gs);
    print('company size:',companylist.__len__());
def db(href, gs,city):
    connect = pymysql.Connect(
        host='127.0.0.1',
        port=3306,
        user='root',
        passwd='Abc123456',
        db='python',
        charset='utf8'
    )
    cursor = connect.cursor()
    sql = "INSERT INTO companys (city,name, url,cdate) VALUES ( '%s','%s', '%s', '%s' )"
    dt = datetime.now();
    data = (city,gs, href,dt)
    print('data:', data);
    cursor.execute(sql % data)
    connect.commit()


if __name__ == '__main__':
    threads = []
    # t1 = threading.Thread(target=chongqing, args=(u'爱情买卖',))#有参数的情况
    t1 = threading.Thread(target=chongqing)  # 无参数的情况
    threads.append(t1)
    t2 = threading.Thread(target=shanghai)
    threads.append(t2)
    t3 = threading.Thread(target=tianjin)
    threads.append(t3);
    for t in threads:
        # t.setDaemon(True)
        t.start()
