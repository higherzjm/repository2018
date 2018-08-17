def test_enumerate():
    a_list=['a', 'b', 'mpilgrim', 'z', 'example']
    for i,v in enumerate(a_list):
        print('列表的第', i+1, '个元素是：', v)

    a_list=('a', 'b', 'c', 'd', 1,2)
    for i,v in enumerate(a_list):
        print('元组的第', i+1, '个元素是：', v);


    a_list={'a':1,'b':2,'c':3}
    for i,v in enumerate(a_list):
        print('字典的第', i+1, '个元素是：', v,':',a_list[v])

def test_range():
    for i in range(100, 103):
        #这里是序列解包的用法
        bai, shi, ge = map(int, str(i));
        print('bai:',bai);
        print('shi:', shi);
        print('ge:', ge);
        if ge**3 + shi**3 + bai**3 == i:
            print(i);


def test_map():
    a,b,c,d=map(str,'hjmf');
    print('a',a);
    print('b',b);
    print('c',c);
    print('d',d)

def test_whilecircle(i):
    while i>100:
        i=i-1;
        print('i:',i);
    else:
        print('结束');



def test_break():
    i=0;
    while i<100:
         i=i+1;
         print('ii=',i);
         if i==50:
             break;


def test_andor():
    if 1==1 and 2==3:
        print('and true');
    else:
        print('and false')

    if 1==2 or 3==3:
        print('or true');
    else:
        print('or false')
if __name__ == '__main__':
    #test_whilecircle(100000);
    #test_break()
    #test_enumerate();
    #test_range()
    #test_map();
    test_andor();