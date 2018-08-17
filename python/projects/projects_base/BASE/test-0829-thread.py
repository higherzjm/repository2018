import threading

import time


def mythread():
    for i in range(1,100000,2):
        print(threading.current_thread().getName(),'i:',i);


#锁
lock = threading.Lock();
def lockthread():
    lock.acquire();#线程锁
    print('thread name:',threading.current_thread().getName());
    time.sleep(2);
    lock.release();#释放锁



#Event 线程同步
event = threading.Event()
def Eventsthread_client():
    #: 一个客户端线程等待flag被设定
    Eventsthread_server();
    event.wait()
    print('thread name:', threading.current_thread().getName());
    print('do things')

def Eventsthread_server():
    time.sleep(5);
    #: 服务端线程设置或者清除flag
    event.set()
    event.clear();

semaphore = threading.BoundedSemaphore(2);
#信号量
def Semaphoresthread():
    print('count 1:', semaphore._value)
    semaphore.acquire()  #: counter减小
    print('count 1:',semaphore._value)
    print('thread name:', threading.current_thread().getName());
    time.sleep(10);
    semaphore.release() #: counter增大
    print('count 2:', semaphore._value)
if __name__ == '__main__':
    threads = []
    # t1 = threading.Thread(target=chongqing, args=(u'爱情买卖',))#有参数的情况
    for i in range(0,10):
        t1 = threading.Thread(target=Semaphoresthread)  # 无参数的情况
        t1.setName("thread "+str(i));
        threads.append(t1)
    for t in threads:
        # t.setDaemon(True)
        t.start()

