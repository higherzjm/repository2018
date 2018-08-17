print('------------------------------函数--------------------')
def fib(n):
    a, b = 0, 1
    while a < n:
        print(a, end=' ')
        a, b = b, a + b
    print()




print('------------------------------ambdaambda表达式命名函数--------------------')
def lambdafunc():
    f = lambda x, y, z: x+y+z        #可以给lambda表达式起名字
    a=f(3,4,5)
    print('a:',a)


    L = [(lambda x: x**2), (lambda x: x**3), (lambda x: x**4)]
    print(L[0](2),L[1](2),L[2](2))

#有返回值的函数
def function1(x):
    print('1111111111111111');
    return  x+100;


#带参数的函数，且有默认值
def funparams(a=None,b='1'):
    print(a,b);


if __name__ == '__main__':
    #print('有返回值的函数:', function1(200));
    #lambdafunc();

    '''
    fib(1000)
    fib.x = 3;
    print('fib.x:', fib.x);
    '''
    funparams(2);