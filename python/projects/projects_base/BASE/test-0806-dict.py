def test_base():
    a_dict={'a':1,'b':2,'c':'3'};
    print('a_dict:',a_dict);

    keys=list(('a','b','c','d'));
    values=[1,2,3,4];
    a_dict=zip(keys,values);
    print('a_dict:',a_dict);
    a_dict=dict(a_dict);
    print('a_dict:',a_dict);
    print('a_dict--a:',a_dict['a']);
    print('a_dict--a:',a_dict.get('a'));
    print('健列表:', a_dict.keys());
    print('是否包含健：',a_dict.keys().__contains__('a'),a_dict.keys().__contains__('aa'));
    print('items:',a_dict.items());

def test_base2():
    a_dict=dict();
    a_dict.update({'a':1,'b':2});
    print(a_dict);
def test_jiebao():
    print('-----------------系列解包用法--------------')
    a_dict = {'a': 1, 'b': 2, 'c': '3'};
    for key,value in a_dict.items():
        a_dict = {'a': 1, 'b': 2, 'c': '3'};
        print(key,value,end=' ');

def test_dcitupdate():
    a_dict = {'a': 1, 'b': 2, 'c': '3'};
    print('a_dict:', a_dict);
    print('------------update方法新增键值对------------')
    a_dict.update({'e':5,'f':6});
    print('a_dict:',a_dict);
    print('dict index e:',a_dict.get('e'));


def test_dictcomprehensions():
    print('--------------------------------字典推导式------')
    a_dict={x:str(x+1) for x in range(2,20,3)};
    print('a_dict:',a_dict);

    x=['aa','bb','cc'];
    y=[11,22,33];
    a_dict={i:j for i,j in zip(x,y)}
    print('a_dict:',a_dict);

def test_complextype():
    print('----------------值是集合的情况----------------')
    a_set={1,2,3,4};
    a_set2={'无法访问'};
    a_dict=dict();
    a_dict.update({'a':a_set,'b':a_set2});
    print(a_dict);
    a_set.add(5)
    print(a_dict['a']);
    a_set=a_dict['a'];
    a_set.add(6); a_set.add(7);a_set.add(2);
    print(a_dict['a']);
    print('pop():',set(a_dict['b']).pop()!='无法访问');
    print(a_dict.get('c')==None)#假如没有这个key,这种方式打印出来会是None

def test_readelement():
    a_dict = {x: str(x + 1) for x in range(2, 20, 3)};
    for k,v in a_dict.items():
        print('k:',k,'v:',v);

test_base();
#test_base2();
#test_dcitupdate();
#test_dictcomprehensions();
#test_base();
test_complextype();
#test_readelement();