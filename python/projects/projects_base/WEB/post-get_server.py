import web

#http://127.0.0.1:8080/hello
#http://127.0.0.1:8080/hello2
urls = ('/hello', 'hello1',
        '/hello2', 'hello2',
        )



class hello1(object):
    def POST(self):
        info = web.input(file={})  # 接收数据
        a=info['a'];
        b=info['b'];
        print(a,b)
        return a+'---hello world1-----'+b;

class hello2(object):
    def GET(self):
        info = web.input()  # 接收数据
        a = info['a'];
        b = info['b'];
        return a+'---hello world2-----'+b;


if __name__ == "__main__":
    app = web.application(urls, globals())
    app.run()