def test_base1():
    a_tuple = ('a', 'b', 'mpilgrim', 'z', 'example');
    print('ruple',a_tuple,'element 1:',a_tuple[2]);

    a_list=[1,2,3,'a','b','c','朱','剑'];
    print('a_list:',a_list);
    a_tuple=tuple(a_list);#列表转为元组
    print('a_tuple:',a_tuple);

    x,y,z=map(str,range(2,8,2));#系列解包，左边的个数要等于右边的个数
    print('x:',x,'--y:',y,'--z:',z);
    x,y,z='a',1,'b';
    print('x:',x,'--y:',y,'--z:',z);

def test_base2():
    print('-------------------序列解包遍历多个序列---------------------')
    a_list=[1,2,3,4];
    b_list=['a','b','c','d'];
    for a ,b in zip(a_list,b_list):
        print((a,b),end=' '); # end=' ' 空格不换行


def test_map():
    print('--------------------------map 对象-----------------------------')
    print('')
    a=map(str, range(3));
    print('a',a);
    a1=tuple(a);
    print(a1);
    a2=list(a);
    print(a2);#惰性求职，访问了就没了

def test_shengchengqituidaoshi():
    print('------------------生成器推导式-----------------------')

    g = ((i+2)**2 for i in range(10))     #创建生成器对象
    print('g',g)
    g1=tuple(g);
    print('g1',g1);
    g2=list(g);
    print('g2',g2);#惰性求职，访问了就没了

    g = ((i+2)**2 for i in range(10))  #使用for循环直接迭代生成器对象中的元素
    for item in g:
        print(item,end=' ');


test_base1();
test_base2();
test_map();
test_shengchengqituidaoshi();