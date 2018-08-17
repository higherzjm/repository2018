# -*- coding: UTF-8 -*-
import  web;
import xlrd
import xlwt
urls = (
    '/Index','Index',
    '/upload','Upload',
    '/uploadxls', 'Uploadxls',
)#路由

render = web.template.render('template')

class Index:
    def GET(self):#函数名时请求方式
        return render.index()

class Upload:
    def POST(self):
        info = web.input(file = {})#接收数据
        filename = info['file'].filename;
        file = info['file'].file;
        print('filename',filename)
        print('file', file)
        thisfile = file.read()
        return thisfile

class Uploadxls:
    def POST(self):
        info = web.input();
        datas = info['datas'];
        print('getdatas', datas);
        return datas

app = web.application(urls, globals())

if __name__ == '__main__':#入口函数判断
    app.run()

#'Server.py 127.0.0.1:8000'