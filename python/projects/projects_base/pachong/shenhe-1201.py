#!/usr/bin/python env
# -*- coding: utf-8 -*-

import requests
import sys

# KEYS = ['fghkbvgv', 'nike', 'adidas']
KEYS = ['Golf', 'DVD', 'LuLulemon', 'Clouds', 'Disk', 'pill', 'medicine', 'tiffany', 'toms', 'Chanel',
            'Abercrombie', 'Fitch', 'hollister', 'GUCCI', 'cigarette', 'louis vuitton', 'GHD', 'GHI', 'Rolex', 'omega',
            'NINTENDO', 'NDSI', 'R4I', 'watch', 'SEXY LINGERIE', 'monsoon', 'celine', 'Birkenstock', 'Birki', 'Birkens',
            'red bull', 'dyson', 'reloj']

class ZenCartSpider:
    def __init__(self, host):
        self.host = host
        # self.search_url = '/advanced_search_result.html?main_page=advanced_search_result&search_in_description=1&keyword={}'
        self.search_url ='/index.php?main_page=advanced_search_result&search_in_description=1&keyword={}'

    def search(self):
        for key in KEYS:
            url = self.host + self.search_url.format(key)
            respones = requests.get(url, allow_redirects=False)
            if(respones.status_code==302):
                print("NO:"+key)
            else:
                print("YES:"+key)
            pass


# spider = ZenCartSpider('http://www.cardiffbusiness360.co.uk')
if __name__ == '__main__':
    # pass
    # chaxun('http://www.amirsecurity.fr')
    # wangzhan()
    url = sys.argv[1]
    spider = ZenCartSpider(url)
    spider.search()
