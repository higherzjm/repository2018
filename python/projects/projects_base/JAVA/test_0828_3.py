import sys
print("脚本名：", sys.argv[0]);

def myfunc(arg1,arg2):
    if arg1=='张三':
        print('我是张三吗');
    else:
        print('i am ',arg1);

    print('i am ', arg2);


myfunc(sys.argv[1],sys.argv[2]);