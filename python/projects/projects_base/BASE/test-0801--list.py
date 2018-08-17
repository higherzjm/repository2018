import time
#list
def testbase():
    lst=[1,2,3,4,5];
    print(lst[-2],lst[-1]) #-1倒数第一个 -2 倒数第二个
    print(type(lst));
    lst.append(6);
    lst.insert(2,100);
    print('lst',lst);
    lst.reverse()
    print('reverse:',lst);
    lst.sort(key=None, reverse=False);
    print('sort:',lst);

    print('lst:',lst);
    print('pop:',lst.pop());
    print('pop 2:',lst.pop(2));
    print('index 1:',lst[1]);
    print('lst:', lst);


    a_list = list((3,5,7,9,11));
    print('a_list:',a_list);
    if 3  in a_list:
        print('3 in a_list');
    a_list=list(range(1,100,2));
    print('a_list:',a_list);
    a_list=list('hello world');
    print('a_list:',a_list);



    print('---------------------------------先进先出---有顺序----pop() 从最顶部取并删除----------------------------------')
    a_list=[];
    a_list.append(1);a_list.append(2);
    a_list.append(11);a_list.append(22);
    a_list.append('a');a_list.append('b');
    a_list.append('c');a_list.append('d');
    print('a_list',a_list)
    print('pop:',a_list.pop())
    print('a_list', a_list)
    for x in a_list:
        print(x,end=",")

def append_plus():
    #  比较+ 和 append
    print('比较+ 和 append  ------------------------------------------------')
    result = []
    start = time.time()
    for i in range(50, 100000, 2):
        result = result + [i];
    print('reslt:', result);
    print('+:',len(result), ',', time.time() - start)

    result = []
    start = time.time()
    for i in range(50, 100000, 2):
        result.append(i);
    print('reslt:', result);
    print('append:',len(result), ',', time.time() - start)
#合并解压
def testduoxingqiuzhi():
    print('----------------------------------------惰性求值----------------------')
    a=[1,2,3,4];
    b=['a','b','c'];
    d = [11, 22, 33, 44];
    c=zip(a,b,d);#惰性求值，访问了就没了
    print('c',c);
    print('c',(1,'a',11) in c);
    for a,b,d in c:
        print(a,b,d, end=';');

    print('')
    print('惰性求值c:',list(c));


def testqiepian():
    aList = [3, 24, 5, 26, 7,222, 9, 11, 13, 15, 17,111]
    print(aList[::2]);
    print(aList[5:8:2]);
    print('----------------------------------------切片，浅复制----------------------')
    aList = [3, 5, 7]
    bList = aList[::];
    print(aList==bList);#值一样
    print(id(aList),id(bList),id(aList)==id(bList));#地址不一样
    print('----------------------------------------sort----------------------')
    aList.sort(key = lambda x:len(str(x)));#按字符串长度排序
    print(aList);


def testenumerate():
    print('----------------------------------------enumerate----------------------')
    aList = [3, 24, 5, 26, 7, 222, 9, 11, 13, 15, 17, 111]
    for item in enumerate(aList):
        print(item);

    print(aList);


def liebiaotuidaoshi():
    print('-------------------------------------2017-08-03 列表推导式-------------------------');
    aList = [x*x for x in range(10)];
    print('alist:',aList);

    aList = list(map(lambda x: x**2, range(2,10,2)));
    print('lambda alist:',aList);

    li = [11, 22, 33]
    sl = [1, 2, 3]
    new_list = list(map(lambda a, b: a + b, li, sl));#遍历序列，对序列中每个元素进行操作，最终获取新的序列。
    print('lambda new_list:',new_list);


    li = [11, 22, 33,35]
    new_list = list(filter(lambda arg: arg > 22, li));#对于序列中的元素进行筛选，最终获取符合条件的序列
    print('lambda new_list:',new_list);

    li = [11, 22, 33]


    print('使用列表推导式实现嵌套列表的平铺');
    vec = [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
    result = [];
    for elem in vec:
       for num in elem:
           result.append(num);
    print('result:',result);

    print('过滤不符合条件的元素');
    aList = [-1,-4,6,7.5,-2.3,9,-11];
    result=[i for i in aList if i>0];
    print('result:',result);


    print('使用列表推导式实现矩阵转置');
    matrix = [ [1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]];
    result=[[row[i] for row in matrix] for i in range(4)]
    print('result:',result);

    print('列表推导式中可以使用函数或复杂表达式');
    def f(v):
        if v%2 == 0:
            v = v**2
        else:
            v = v+1
        return v;
    result=[f(v) for v in [2, 3, 4, -1] if v>0];
    print('result:',result);


def zijihe():
    print('------------------------------------------------------子集合  2017-08-24------------------------------')
    a_list=[1,2,3,4,['a','b','c','d']];
    print(type(a_list),a_list[4][0]);
    print('---------')
    a_list=[];
    a_list.append(11);
    a_list.append(22);
    a_list.append('dd');
    sub_list=['a','b','c'];
    a_list.append(sub_list);
    print(a_list);
    print('---------')
    sub_list1=['a','b','c'];
    sub_list2=[1,2,3];
    super_list=[];
    super_list.append(sub_list1);
    super_list.append(sub_list2);
    print('super_list',super_list);
    print('super_list',super_list[1][1]);

    sub_list21=[];
    sub_list21.append(1);
    sub_list21.append(2);
    sub_list21.append(3);
    sub_list22=[];
    sub_list22.append('a');
    sub_list22.append('b');
    sub_list22.append('c');
    super_list2=[];
    super_list2.append(sub_list21);
    super_list2.append(sub_list22);
    print('super_list2',super_list2);
    print('super_list2 index',super_list2[1][1]);



def testlist2str():
    print('----------------list转str --------------------------')
    a_list=['1','2','a','b','d'];
    print(a_list);
    print('a_list type ',type(a_list))
    a_str=','.join(a_list);
    print(a_str);
    print('a_str type ',type(a_str))

def teststr2list1():
    print('-----------------str转list 1--------------------')
    a_str="1,2,3,4,5";
    print(type(a_str),a_str[0]);
    a_list=a_str.split(',');
    print('a_list',a_list)
    print(type(a_list),a_list[0],a_list[4]);

    a_str="[1,2,3,4,5,['a','b',['aa','bb'],'c'],6,'d']";
    print(type(a_str),a_str[0]);
    a_str2=a_str.strip(']').strip('[');
    print('a_str2',a_str2)
    print(a_str2.find('['),a_str2.rfind(']'),a_str2.find(']'))
    if a_str2.find('[')!=-1:
      a_sub1=a_str2[0:a_str2.find('[')-1]
      a_sub2=a_str2[a_str2.rfind(']')+2:a_str2.__len__()]
      a_sub3 = a_str2[a_str2.find('[') + 1:a_str2.rfind(']') - 1];
      print('a_sub1', a_sub1)
      print('a_sub2', a_sub2)
      print('a_substr3', a_sub3)
      if a_sub3.find('[') != -1:
          a_sub3_sub1 = a_sub3[0:a_sub3.find('[') - 1]
          a_sub3_sub2 = a_sub3[a_sub3.rfind(']') + 2:a_sub3.__len__()]
          a_sub3_sub3 = a_sub3[a_sub3.find('[') + 1:a_sub3.rfind(']') - 1];
          print('a_sub3_sub1', a_sub3_sub1)
          print('a_sub3_sub2', a_sub3_sub2)
          print('a_sub3_sub3', a_sub3_sub3)

    sub_list1=a_sub3_sub3.replace('\'','').split(',');
    sub_list10=a_sub3_sub1.replace('\'','').split(',');
    sub_list11=a_sub3_sub2.replace('\'','').split(',');
    sub_list2=a_sub3.replace('\'','').split(',');
    sub_list20=a_sub1.replace('\'','').split(',');
    sub_list21=a_sub2.replace('\'','').split(',');
    list1=[];
    for a in sub_list10:
      list1.append(a);
    list1.append(sub_list1);
    for a in sub_list11:
      list1.append(a);
    list2=[];
    for a in sub_list20:
        list2.append(a);
    list2.append(list1);
    for a in sub_list21:
        list2.append(a);

    print('a_str',a_str)
    print('str转list',list2)
    print('str转list index:',list2[5][2][1]);


def teststr2list2():
    print('-----------------str转list 2--------------------')
    str = "[[1,2,3,4,5],[11,22,33,44,55],[111,222,333,444,555],[1111,2222,3333,4444,5555],[11111,22212,33333,44444,55555]]]"
    #str="[[1,2,3,4,5],[11,22,33,44,55],[111,222,333,444,555],[1111,2222,3333,4444,5555]]"
    i=1;
    list=[];
    while i==1:
        str = str.replace('\'', '').replace('\"', '').strip(']').strip('[');
        if str.find('[')>-1:
            list0=str[0:str.find(']')].split(',');
            list.append(list0);
            list1=str[str.rfind('[')+1:str.__len__()].split(',');
            list.append(list1);
            str=str[str.find('['):str.rfind(']')+1];
        else:
            if str.__len__()<=1:
                break;
            i=0;
            list0=str.split(',');
            list.append(list0);

    print('list',list.__len__())
    print('list index',list[list.__len__()-1][0])

testbase();
#liebiaotuidaoshi();
#testduoxingqiuzhi();
#testqiepian();
#testenumerate();
#testlist2str();
#teststr2list1();
#teststr2list2();
#append_plus();
