import threading
import urllib.request
import pymysql.cursors

#SELECT  p.BRAND_NAME  from  mer_brand_list p
import re

import time
from bs4 import BeautifulSoup


class DbTest:
    connect=''
    cursor='';
    brands=[];
    existbrands=[];
    allurlsforlevel=set();
    allurls=set();
    def __init__2(self):
        connect = pymysql.Connect(
            host='192.168.1.14',
            port=3306,
            user='root',
            passwd='root',
            #passwd='Abc123456',
            db='platform',
            charset='utf8'
        )
        self.cursor = connect.cursor();
    def queryBrands(self,sql):
        datas=self.cursor.execute(sql);
        self.cursor.close()
        info = self.cursor.fetchmany(datas)
        for ii in info:
            #print(type(ii))
            self.brands.append(ii[0]);

    def httprequert(this):
        while this.allurlsforlevel.__len__()!=0:
            splits =this.allurlsforlevel.pop().split('_');
            url = splits[1];
            level = splits[0]
            url=this.parseUrl(url);
            print('thread name:',threading.current_thread().getName())
            #print('level',level,'url',url);
            try:
                page = urllib.request.urlopen(url,timeout=15);
                content = page.read().decode(encoding='utf-8', errors='strict');
                this.parseContent(content,level);
            except  Exception as e:
               print(url,'请求发生异常',e)

    def parseUrl(self,url):
        if url.find('http://')==-1:
            url='http://'+url;
        return  url;

    def parseContent(self,content,level):
        #print('----------解析content--------');
        for brand in self.brands:
            if content.lower().find(brand.lower())!=-1 and brand not in self.existbrands:self.existbrands.append(brand);
        level=int(level);
        if level<3:
          self.getsubUrls(content,level);


    def getsubUrls(self,content,level):
        soup = BeautifulSoup(content, 'html.parser')
        level=level+1;
        for mulu in soup.find_all('a'):
            suburl=mulu.get('href');
            if suburl in  self.allurls:
                continue;
            self.allurls.add(suburl);
            suburl=str(level)+'_'+suburl;
            self.allurlsforlevel.add(suburl);
        #self.httprequert();


if __name__ == '__main__':
    datest=DbTest();
    #datest.queryBrands("SELECT  p.BRAND_NAME  from  mer_brand_list p");
    datest.brands = ['golden goose', 'Abercrombie & Fitch', 'ABUS', 'adidas', 'Affliction', 'Alexander McQueen',
                     'allure', 'Alviero Martini', 'Anime', 'Arc Tertx', 'Arena', 'Asics', 'Atlanta', 'babyliss',
                     'Bailey', 'Balenciaga', 'Bape', 'Barbour ', 'BCBG Max Azria ', 'Beats Electronics', 'Belstaff',
                     'Benefit', 'Bikini', 'Birkenstock', 'BMC', 'BMW', 'Bobbi Brown', 'Bogner', 'BOSE',
                     'Bottega Veneta', 'Bruno', 'buckyballs', 'Burberry', 'BUSCEMI', 'Bvlgari', 'Calvin Klein',
                     'Camper', 'Canada Goose ', 'Cartier', 'casadei ', 'Celine', 'Chan Luu', 'Chanel',
                     'Charlotte Olympia', 'Chaussures', 'Chloe', 'Christian Louboutin', 'Clarisonics', 'Clarks ',
                     'Coach', 'Coast', 'Columbia', 'Converses', 'Cove Sgg', 'Cufflinks', 'Cycling jerseys',
                     'DamierAzur', 'Daniel Hechter', 'DCSHOECOUSA', 'Desigual', 'Diadora ', 'Diesel ', 'Dior', 'DKNY',
                     'Dolce & Gabbana', 'DSQUARED2', 'Dunnhil', 'DUVETICA', 'DVD光碟', 'Ecco', 'Ed Hardy', 'Emilio Pucci',
                     'Ermenegildo Xegna', 'Fendi', 'Fitflop', 'Francesco Smalto', 'franck muller', 'Franklin',
                     'Frauen Birkenstock', 'fred perry', 'Frey wille', 'FRYE', 'furla', 'Genfoyxjh', 'GHD头发拉直器',
                     'GHI头发拉直器', 'Gianmarco Lorenzi ', 'Giorgio Armani', 'Giuseppe Zanotti', 'Givenchy',
                     'Gorra Monster', 'Goyard', 'G-star', 'Gucci', 'Guess', 'Hello kitty', 'Hermes ', 'Herve Leger',
                     'hervele', 'Hirvi Knuchles', 'Hogan', 'Hollister', 'hublot ', 'Hugo Boss', 'Hunter',
                     'Insanity DVD健身', 'iphone 手机壳', 'Isabel ', 'Jack Wolfskin', 'Jakker Danmonk', 'Jaquetas Portugal',
                     'Jean Courcel', 'Jeffrey', 'Jeremy Scott', 'Jimmy Choo', 'Jordan', 'Juicy Couture', 'Jules Verne ',
                     'kappa', 'Karen Millen', 'Kate Spade', 'Kenneth Cole', 'Kenneth Samantha', 'Kenzo', 'Kigurumi',
                     'Kipling Bags', 'La femme', 'La Martina Hombres ', 'LACE', 'Lacoste', 'Lancel', 'Lancome',
                     'Lanvin', 'Last King', 'Lavin', 'Le Coq Sportif ', 'Lee', 'lentes carrera', 'Levis',
                     'LinkS Of London', 'LOEWE', 'LongChamp', 'Longines', 'Louis Vuitton', 'Luis Morais', 'Lululemon',
                     'Luxottica', 'M.A.C', 'Mango', 'Manolo Blahnik', 'Marc Jacobs', 'MaxMara', 'MAYARI系列', 'MBT',
                     'MCM', 'MIA', 'MichaelKors', 'Mickey', 'Miracurl头发卷曲机', 'Miu Miu', 'Mizuno Wave', 'MLB', 'MMA',
                     'Moncler', 'Monster', 'Montblanc ', 'montres', 'Moolecole', 'Moose Kunckles', 'Moschino',
                     'Mulberry', 'Nacobaby婴儿用品', 'NBA ', 'new balance', 'New Era', 'NFL', 'NFL帽子', 'NHL', 'Nike',
                     'Nobis', 'Nobrand', 'North face', 'Nudie', 'Oakley', 'OBEY', 'OPP', 'pandora', 'parajumpers',
                     'patek philippe', 'patrick mohr', 'Paul Smith', 'PEUTEREY', 'Pierre Cardin', 'Pietro Santo',
                     'Piolo Wetsonj', 'Play Boy', 'Police', 'Polo', 'Porter', 'Power Balance', 'Prada',
                     'Proenza Schouler', 'pronovias', 'Puma', 'r4i烧录卡', 'rado', 'Ralph Lauren', 'Rayban',
                     'Red Valentino', 'RED WING', 'reebok', 'Reed Krakoff', 'Roberto Cavalli', 'Roger Vivier', 'Rolex',
                     'Salomon', 'Salvatore Ferragamo', 'Samsung', 'saucony', 'sherri hill', 'Similar Love', 'SMS Audio',
                     'software', 'sophia webster', 'Spy Targa', 'Spyder', 'Stella McCartney', 'Stone IsLand',
                     'Stuart Weitzman ', 'Supra', 'Supreme', 'SWAROVSKI', 'Swatch', 'thomas sabo', 'Tiffany',
                     'Timberland', 'Tissot', 'Tods', 'Tommy Hifiger', 'Toms', 'Tory Burch', 'True Religion',
                     'TRX运动健身器材', 'TSFUL ', 'TUNGSTEN', 'UGG', 'Under Armour', 'Urban Decay', 'Valentino',
                     'Vanessa bruno', 'Vans', 'Versace', 'Vibram', 'VICTORIA S SECRET', 'Vince', 'visvim',
                     'Vivienne Westwood', 'Wellensteyn', 'whole ', 'Woolrich', 'Wrangler', 'Yamaha',
                     'Yves Saint Laurent', 'Zara', 'Zippo', '棒球帽', '包包', '保健品', '北面夹克外套', '床上用品', '瓷器', '灯具', '电子产品',
                     '电子书', '儿童床', '儿童床上用品', '儿童连体衣和成人体衣', '儿童玩具', '飞机模型', '钢笔', '花洒水龙头', '滑板', '化妆品', '婚纱礼服', '激光器',
                     '家居装饰', '假发', '假花', '减肥胶囊', '减肥药', '健身服', '健身用具', '模型', '魔兽金币', '男士内衣', '男鞋', '男装', '牛仔裤', '女士内衣',
                     '女鞋', '女装', '皮鞋皮具', '汽车配件', '器械', '钱包', '情趣内衣', '球鞋', '球衣', '日用品', '手表', '手电筒', '手工艺品', '手机干扰器',
                     '手机壳', '数据线/电子设备', '太极服', '太阳镜', '陶瓷工艺品', '贴纸', '童装', '网络教程', '网盘', '围巾', '纹身器械', '无品牌耳机', '无品牌服饰',
                     '无品牌饰品', '鲜花', '鸭舌帽', '烟草', '眼镜', '腰带', '野外用具', '婴儿用品', '泳衣', '游戏币', '游戏机配件', '瑜伽服', '玉石饰品', '造纸',
                     '帐篷', '珠宝', '自行车配件', '自行车专用运动服', '综合站', 'dddddd', 'daaaaa', 'fff'];

    print(datest.brands)
    url='1_http://www.laffords.co.uk/';
    datest.allurlsforlevel.add(url);
    datest.allurls.add(url.split('_')[1]);
    url="1_http://www.cappottiamperedonna2017.com/";
    datest.allurlsforlevel.add(url);
    datest.allurls.add(url.split('_')[1]);
    url="1_www.cappottiamperedonna2017.com";
    datest.allurlsforlevel.add(url);
    datest.allurls.add(url.split('_')[1]);

    #datest.httprequert()

    threads = []
    # t1 = threading.Thread(target=chongqing, args=(u'爱情买卖',))#有参数的情况
    t1 = threading.Thread(target=datest.httprequert)  # 无参数的情况
    t1.setName("thread 1");
    threads.append(t1)
    t2 = threading.Thread(target=datest.httprequert)
    t2.setName("thread 2");
    threads.append(t2)
    t3 = threading.Thread(target=datest.httprequert)
    t3.setName("thread 3");
    threads.append(t3);
    t4 = threading.Thread(target=datest.httprequert)
    t4.setName("thread 4");
    threads.append(t4);
    t5 = threading.Thread(target=datest.httprequert)
    t5.setName("thread 5");
    threads.append(t5);
    for t in threads:
    # t.setDaemon(True)
       t.start();


    time.sleep(10)
    while 1==1:
        if datest.allurlsforlevel.__len__()==0:
            print('existbrands',datest.existbrands)
            print('allurls',datest.allurls.__len__())
            print('allurlsforlevel',datest.allurlsforlevel.__len__())
            break;
