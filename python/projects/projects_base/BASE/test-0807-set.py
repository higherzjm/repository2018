
def testbase1():
    a_set={1,1,2,'a','b','a'};#同一个集合中每个元素都是唯一的,自动去除重复
    print('a_set',a_set);
    a_set.add('c');
    print('a_set',a_set);
    a_set = set(range(8,20,2))
    print('a_set',a_set);
    a=a_set.pop();
    print('a',a);
    print('a_set',a_set);
    a_set.remove(18);
    print('a_set',a_set);
    a_set.clear();
    print('a_set',a_set);

def testbase2():
    print('----------------------使用集合快速提取序列中单一元素-------------')
    import random
    listRandom = [random.choice(range(100)) for i in range(30)];
    print('',listRandom,len(listRandom));
    noRepeat = [];
    for i in listRandom :
         if i not in noRepeat :
             noRepeat.append(i);
    newSet = set(listRandom)
    print('newSet:',newSet,newSet.__len__())


def testbase3():
    a={};
    print('a type:',type(a));#dict
    a={1,2,3,4};
    print('a type:',type(a));#set
    print('a pop:',a.pop());#先进先出
    a.add(5);
    print(a);
    print(5 in a);

    b=set();
    print('b type:',type(b));
    b.add('a');
    print(b);

def testbase4():
    print('--------------无顺序-------pop() 从最底部取并删除-----')
    a_set=set();
    print(a_set.__len__())
    a_set.add(1);
    a_set.add(2);
    a_set.add(11);
    a_set.add(22);
    a_set.add('a');
    a_set.add('b');
    a_set.add('c');
    a_set.add('d');
    print('a_set', a_set)
    print('pop:', a_set.pop())
    print('a_set', a_set)
    #print('a_set index:',a_set[0]);#'set' object does not support indexing
    for x in a_set:
        print(x, end=",")

#testbase1();
#testbase2();
#testbase3();
testbase4();