import urllib.request
from datetime import datetime

import pymysql.cursors

#SELECT  p.BRAND_NAME  from  mer_brand_list p
import re

from bs4 import BeautifulSoup


class DbTest:
    connect=''
    cursor='';
    connect2 = ''
    cursor2 = '';
    brands=[];
    def __init__(self):
        #-----------database 1--------
        self.connect = pymysql.Connect(
            host='192.168.1.14',
            port=3306,
            user='root',
            passwd='root',
            #passwd='Abc123456',
            db='platform',
            charset='utf8'
        )
        self.cursor =self.connect.cursor();


       #-----------database 2--------------
        self.connect2 = pymysql.Connect(
            host='127.0.0.1',
            port=3306,
            user='root',
            passwd='Abc123456',
            db='platform',
            charset='utf8'
        )
        self.cursor2 = self.connect2.cursor()
    def queryBrands(self,sql):
        datas=self.cursor.execute(sql);
        self.cursor.close()
        info = self.cursor.fetchmany(datas)
        for ii in info:
            self.brands.append(ii[0]);
    def insertsql(self,sql):
        dt = datetime.now();
        data = ('test', 'test', 'test', dt)
        self.cursor2.execute(sql % data)
        self.connect2.commit()
    def updatesql(self,sql,params):
        self.cursor2.execute(sql % params)
        self.connect2.commit()

if __name__ == '__main__':
    datest=DbTest();
    datest.queryBrands("SELECT  p.URL  from  platform.mer_site p  where p.CHECK_STATE='1' and DATE_FORMAT(p.CREATE_TIME,'%Y%m%d')='20170831'");
    for url in datest.brands:
        print('url:',url);


    sql = "INSERT INTO companys (city,name, url,cdate) VALUES ( '%s','%s', '%s', '%s' )"
    #datest.insertsql(sql)

    params=('1','不存关键字','devipmall.com');
    sql="UPDATE  platform.mer_site m  set m.analyze_status=%s ,m.analyze_remark=%s where m.URL=%s";
    datest.updatesql(sql,params);
