import urllib.request
from bs4 import BeautifulSoup
from datetime import datetime
import threading

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
    '''
    user_agent = 'Mozilla/5.0 (compatible; MSIE5.5; Windows NT)'
    headers = {'User-Agents':user_agent}
    timeout = 2
    '''
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
        # name = gs.replace("'","")
        # name = pymysql.escape_string(gs)
        # name = pymysql.escape_sequence(gs)
        try:
            db(href, gs,city)
        except:
            continue
        # print(href,name)

companylist=[];
def db(href, gs,city):
    dt = datetime.now();
    data = (city, gs, href, dt)
    print('data:', data);
    companylist.append(gs);
    print('company size:',companylist.__len__());



if __name__ == '__main__':
    threads = []
    t1 = threading.Thread(target=chongqing)  # 无参数的情况
    threads.append(t1)
    for t in threads:
        # t.setDaemon(True)
        t.start()
