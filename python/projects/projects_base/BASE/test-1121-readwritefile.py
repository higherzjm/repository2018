import urllib

import requests


def readfile():
    # 打开文件
    fo = open("F:/tuofu2017/learn/Python/files/txt/readfile.txt", "r+")
    print("文件名为: ", fo.name)

    line = fo.read(10)
    print("读取的字符串: %s" % (line))

    # 关闭文件
    fo.close()

def writefile():
    # 打开文件
    fo = open("F:/tuofu2017/learn/Python/files/txt/writefile.txt", "r+")
    print("文件名: ", fo.name)

    str = "6:www.runoob.com"
    # 在文件末尾写入一行
    fo.seek(0, 2)
    line = fo.write(str)

    # 读取文件所有内容
    fo.seek(0, 0)
    for index in range(6):
        line = next(fo)
        print("文件行号 %d - %s" % (index, line))

    # 关闭文件
    fo.close()

def readfile2():
    file_path = 'F:/tuofu2017/learn/Python/files/txt/readfile.txt'
    with open(file_path, 'rb') as fo:  #二进制方式打开，读出来的字符是二进制编码
        txt = fo.read()
        print('ret:', txt)
        print('ret:', txt.decode(encoding='gbk', errors='strict')) #解码
        fo.close();

def writefile2():

    file_path='F:/tuofu2017/learn/Python/files/txt/writefile2.txt'
    with open(file_path, 'w+') as fo:  # 该种打开方式，如果不存在改文件，系统会自动创建
        fo.write('2122122sss张三啊啊啊啊啊')
        fo.close();

    file_path = 'F:/tuofu2017/learn/Python/files/txt/writefile3.txt'
    with open(file_path, 'wb') as fo:  # 二进制的方式存入，文件打开还是正常字符串
        fo.write('2122122sss张三啊啊啊啊啊'.encode('utf-8'))
        fo.close();

def writeimagefile():
    file_path = 'F:/tuofu2017/learn/Python/files/iamges/01.jpg'
    image_path='http://mm.chinasareview.com/wp-content/uploads/2016a/05/18/01.jpg'
    with open(file_path, 'wb') as handle:
        headers = {'user-agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1941.0 Safari/537.36'}
        response = requests.get(image_path, stream=True,headers=headers)
        print(response.status_code)
        if response.status_code==200:
            for block in response.iter_content(1024):
                if not block:
                    break

                handle.write(block)
    print('done')
def writeimagefile2():
    file_path = 'F:/tuofu2017/learn/Python/files/iamges/02.jpg'
    image_path='http://mm.chinasareview.com/wp-content/uploads/2016a/05/18/02.jpg'
    opener = urllib.request.build_opener()
    opener.addheaders = [('User-Agent',
            'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1941.0 Safari/537.36')]
    urllib.request.install_opener(opener)
    urllib.request.urlretrieve(image_path,file_path)
    print('done')
if __name__ == '__main__':
    #readfile()
    #writefile()
    #readfile2();
    #writefile2()
    writeimagefile()
    writeimagefile2()