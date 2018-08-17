# encoding:UTF-8
import random


class Test:
    def __init__(self):  # 构造方法可能会被派生类继承
        pass

    def yield_test1(self, n):  # 返回迭代，后面的内容可以正常执行
            for i in range(n):
                yield self.call(i)
                print("i=", i)
                # 做一些其它的事情
            print("do something.")
            print("end.")

    @staticmethod
    def call(i):
        return i * 2;

    @staticmethod
    def yield_test2():  # 一次性放回迭代器数据格式
        for i in range(10):
            yield i;

    @staticmethod
    def return_test():  # 返回第一个值
        for i in range(10):
            return i;

   #测随机数
    def getrandomnum(self):
        all_UserAgents = [
            # safari 5.1 – Windows
            'Mozilla/5.0 (Windows; U; Windows NT 6.1; en-us) AppleWebKit/534.50 (KHTML, like Gecko) Version/5.1 Safari/534.50',
            'Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0',  # IE9
            'Mozilla/5.0 (Windows NT 6.1; rv:2.0.1) Gecko/20100101 Firefox/4.0.1',  # Firefox 4.0.1 – Windows
            'Opera/9.80 (Windows NT 6.1; U; en) Presto/2.8.131 Version/11.11',  # Opera 11.11 – Windows
            'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; 360SE)',  # 360浏览器
            'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; Maxthon 2.0)',  # 傲游（Maxthon）
            'Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1; The World)',  # 世界之窗（The World） 3.x
            # chrome
            'Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_0) AppleWebKit/535.11 (KHTML, like Gecko) Chrome/17.0.963.56 Safari/535.11'
            ];
        for x in range(0,10):
            randomnum = random.randint(0, all_UserAgents.__len__())
            print('UserAgent:',all_UserAgents[randomnum])

if __name__ == '__main__':
    test=Test();
    #print(set(test.yield_test1(1000)));
    #print(list(test.yield_test2()));
    # print(test.return_test());
    test.getrandomnum()

