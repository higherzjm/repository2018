#堆、栈、队列

print('----------------------------堆----------------------------------')
import heapq                    #heapq和random是Python标准库
import random
data = list(range(10))
print('data:',data);
a=random.choice(data)             #随机选择一个元素
print('a:',a);
random.shuffle(data)            #随机打乱顺序
print('data:',data);
heap=[];
for n in data:                  #建堆
    heapq.heappush(heap,n)
print('建堆:',heap);
print('type',type(heap))
heapq.heappush(heap,0.5)        #入堆，自动重建
print('入堆，自动重建:',heap);
heapq.heappop(heap)             #出堆，自动重建
print('出堆，自动重建:',heap);


myheap = [1,2,3,5,7,8,9,4,10,333]
heapq.heapify(myheap)             #建堆
print('myheap',myheap);
print('type',type(myheap))
a=heapq.heapreplace(myheap,6)       #弹出最小元素，同时插入新元素
print('a',a);
print('myheap',myheap);
a=heapq.nlargest(3, myheap)         #返回前3个最大的元素
print('返回前3个最大的元素',a);
a=heapq.nsmallest(3, myheap)        #返回前3个最小的元素
print('返回前3个最小的元素',myheap);


print('----------------------------列表----先进先出------------------------------')
import queue        #queue是Python标准库
q = queue.Queue()
q.put(111)            #入队
q.put(1)
q.put(211)
q.put('qqq')
print('type:',type(q));
print('q',q.queue)
a=q.get()             #出队
print('a',a);
print('q',q.queue)
q.get()
print('q',q.queue)


print('----------------------------栈 -先进后出-----------------------------------')
myStack = []
myStack.append(3)
myStack.append(5)
myStack.append(7)
print(myStack);
print(myStack.pop());
print(myStack.pop());
print(myStack.pop());

