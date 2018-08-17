#内置函数

def test_basefuc():
    a='aa11';
    b='aa11';
    print('id:',id(a),id(b));#内存地址
    print('ascii:',ascii('严'));#ascii
    print('chr:',chr(97),chr(98));#Unicode编码为x的字符
    print('bin:',bin(99)) #二进制串
    print('ord:',ord('严'))
    print('a hash:',hash(a));#hash
    print(int(5/3));#int

#惰性求值
def test_zip():
    a=[1,2,3,4];
    b=['a','b','c'];
    c=['朱' ,'王','邱','兰']
    d=zip(a,b,c);
    print('zip:',d);
    a_list=list(d);
    print('a_list:',a_list)
    x = zip('abcd','1234','朱王邱兰')
    x=list(x);
    print('x:',x)
    print(x[0][2]);

def test_range():
   a_list=list(range(22, 0, -2))
   print('a_list:',a_list)
   a_list = list(range(10, 100, 3))
   print('a_list:', a_list)

def test_filter():
    seq = ['foo', 'x41', '?!', '***','acs','12345','朱王邱兰']
    def func(x):
        return x.isalnum()  # 测试是否为字母或数字

    myfilter=filter(func, seq)  # 返回filter对象
    a_list=list(myfilter);
    print('a_list:',a_list);

def test_map():
    def add1(v):  # 单参数函数
        return v*v
    a_list=list(map(add1, range(10)))
    print('a_list:',a_list);
    def add2(x, y):  # 可以接收2个参数的函数
        return x + y;
    a_list==list(map(add2, range(5), range(5, 10)))
    print('a_list:',a_list);
    aList = list(map(lambda x: x ** 2, range(1, 10, 2)));
    print('lambda alist:', aList);

    li = [11, 22, 33]
    sl = [1, 2, 3]
    new_list = list(map(lambda a, b: a + b, li, sl));  # 遍历序列，对序列中每个元素进行操作，最终获取新的序列。
    print('lambda new_list:', new_list);

#惰性求值
def test_enumerateenumerate():
    a_list=list(enumerate('abcd'));
    print('a_list:', a_list);
    a=[1,2,3,4,'a','b','c','d']; # or set
    for i in enumerate(a):
        print(i,end=';')

    print()
    a = [1, 2, 3, 4, 'a', 'b', 'c', 'd'];  # or set
    for x,y in enumerate(a):
        print(x,':',y,end=',');
    print()
    for index, value in enumerate(range(10, 15)):  # 枚举range对象中的元素
        print((index, value), end=' ')


def test_sorted():
    x = ['aaaa', 'bc', 'd', 'b', 'ba','f11111','ccc']
    a_list=sorted(x, key=lambda item: (len(item), item))
    print('a_list:',a_list);
    x.sort(key=lambda x: len(str(x)),reverse=True);#倒序
    print('a_list:', x);
    # 先按长度排序，长度一样的正常排序
def test_reversed():
    x = ['aaaa', 'bc', 'd', 'b', 'ba', 'f11111', 'ccc'];
    print('a_list:', x);
    a_list=list(reversed(x));
    print('a_list:', a_list);
def test_isinstance_type():
    print(type(3));
    print(isinstance([3], list));

def test_max_min():
    x = ['21','1234','9']
    print(max(x),min(x));#长度排序
    print(max(x, key=len));

#函数可以查看指定模块中包含的所有成员或者指定对象类型所支持的操作
def test_dir_help():
    print(dir(list));
    print(help(list));
#test_basefuc();
#test_zip();
#test_range();
#test_filter();
#test_map();
#test_enumerateenumerate();
#test_sorted();
#test_reversed();
#test_isinstance_type();
#test_max_min();
test_dir_help();