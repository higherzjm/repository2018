


def test_base1():
    s="apple,peach,banana,pear"
    li=s.split(",");
    print('split type',type(li));

    for str2 in li:
        print('split str',str2)

    #partition()和rpartition()方法以指定字符串为分隔符将原字符串分隔为3部分，
        # 即分隔符之前的字符串、分隔符字符串和分隔符之后的字符串,
    s = "apple,peach,banana,pear";
    li=s.partition(',');
    print('partition:',li);

    li=["apple", "peach", "banana", "pear"];
    sep=':';
    s=sep.join(li)
    print('join:',s)


    s="中国，中国";
    s2=s.replace("中国", "中华人民共和国");
    print('replace:',s2)

    s = " abc  "
    print('删除空白字符',s.__len__());
    s2 = s.strip()
    print('删除空白字符:',s2.__len__(),s2)


    a='ahss1223fgsajj';
    print('成员判断','3f' in a);

    a=11111112
    print(type(a))

    b=str(a);#转为字符串类型
    print(type(b))

    a='abc123字符串类型.txt';
    print('前四位:'+a[:4]);
    print('去掉后面四位留下的字符串:'+a[:-4]);

    a='古力娜扎图片大全'
    print('去掉后面四位留下的字符串:'+a[:-4]);

def str_format():
    print('------------------------------------------字符串格式化-------------')
    a='%s'%[1, 2, 3];
    print('a:',a);
    a='%s'%123;
    print('a',a);


    s = "What is Your Name?"
    print('返回小写字符串',s.lower());
                       #
    'what is your name?'
    print('返回大写字符串',s.upper());


    sstr='1_www.baidu.com_www.baidu122.com';

    print(sstr.split('_')[0]);
    print(sstr.split('_')[1]);
    print(sstr.split('_')[2]);
def test_len():
    print('-----------------------------len-----------------')
    a='F:/tuofu2017/learn/Python/files/askopenfile.txt';
    print('__len__',a.__len__());
    print('len',len(a));
def test_find():
    print('------------------------------------------------find 截取字符串-------------')
    s = "applepeachbananapeachpear"
    # find()和rfind方法分别用来查找一个字符串在另一个字符串指定范围（默认是整个字符串）
    # 中首次和最后一次出现的位置，如果不存在则返回-1
    # index()和rindex()方法用来返回一个字符串在另一个字符串指定范围中首次和最后一次出现的位置，
    # 如果不存在则抛出异常；
    print('find:', s.find("peach"));
    print('rfind:', s.rfind("peach"))
    print('index:', s.index("peach"));

    a='F:/tuofu2017/learn/Python/files/askopenfile.xlsx';
    print('find',a.find('.'));
    print('截取字符串',a[3:7]);#第三位开始，到第6位
    print('截取.号后面的',a[a.find('.')+1:a.__len__()])
    print('截取最后一位', a[a.__len__()-1:a.__len__()])

def test_st_byte():
    print('-----------------------------str-------byte---------------------------------')
    a='abc123字符串类型';
    e1=a.encode('utf-8');#字符串转byte
    e2=bytes(a,encoding="utf-8");
    print('e1',e1);
    print('e2',e2)
    s1=str(e1, encoding = "utf-8");#byte转字符串
    s2=str(e2, encoding = "utf-8")
    s3=e1.decode(encoding='utf-8',errors='strict')
    print('s1',s1);
    print('s2',s2)
    print('s3',s3)


def test_strip():
    print('------------------------------------------strip 移除字符串头尾指定的字符-------------')

    a='[jjjjjjjjj[kkkkkkkkkkkkkkkkkk[';
    a=a.strip('[');
    print(a);

    str="http://boutiquepole/stars.top/";
    str=str.strip("http://");
    print('str:',str);

def test_int():
    a=32;
    b=a/3;
    print(b);
    print(int(b))
    c=66%33
    print(c);
    print(int(c))
#test_int();
test_base1();
#test_find();
#test_len();
#test_st_byte();
#test_strip();