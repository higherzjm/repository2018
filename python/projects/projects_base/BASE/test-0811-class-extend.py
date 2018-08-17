
class Car:
    def __init__(self,param1):self.value=param1+'哈哈';
    def infor(self):
        print(" This is a car "+self.value);


class Root:
    __total = 0;
    def __init__(self, v):    #构造方法
        self.__value = v
        Root.__total += 1

    def show(self):           #普通实例方法
        print('self.__value:', self.__value)
        print('Root.__total:', Root.__total)

    @classmethod              #修饰器，声明类方法
    def classShowTotal(cls):  #类方法
        print(cls.__total)
     #修饰器，声明静态方法
    @staticmethod
    def staticShowTotal():    #静态方法
        print(Root.__total);

class Test0813:
    param1='101'
    R=0;
    def __init__(self,r):
        self.param2 = r;
        Test0813.param1=self.param1+r;
    def method1(self,r2):
        print('param1:',self.param1);
        print('param2:', self.param2+r2);

print('-------------------继承机制----------------------')
class A(object):
    def __init__(self):  # 构造方法可能会被派生类继承
        self.__private('子类传参')
        self.public('子类传参')

    def __private(self,agy):  # 私有方法在派生类中不能直接访问
        print('__private() method in A:',agy)

    def public(self,agy):  # 公开方法在派生类中可以直接访问，也可以被覆盖
        print('public() method in A:',agy)

class B(A):  # 类B没有构造方法，会继承基类的构造方法

    def __private(self,agy):  # 这不会覆盖基类的私有方法
        print('__private() method in B',agy)

    def public(self,agy):  # 覆盖了继承自A类的公开方法public
        print('public() method in B',agy);


print('-------------------多态原理与实现----------------------')
class Animal(object):      #定义基类
    def show(self):
        print('I am an animal.');
class Cat(Animal):         #派生类，覆盖了基类的show()方法
    def show(self):
        print('I am a cat.');
class Dog(Animal):         #派生类
    def show(self):
        print('I am a dog.');
class Tiger(Animal):       #派生类
    def show(self):
        print('I am a tiger.');
class Test(Animal):        #派生类，没有覆盖基类的show()方法
    pass;


if __name__ == '__main__':
    #class 1
    '''
    car = Car('开车');
    car.infor()
    print('测试一个对象是否为某个类的实例:', isinstance(car, Car));
    '''
    # class 2
    '''
    r = Root(3);  # 成员不存在，允许添加
    r.classShowTotal()  # 通过对象来调用类方法
    r.staticShowTotal()  # 通过对象来调用静态方法
    r.show();
    
    rr = Root(5)
    Root.classShowTotal()  # 通过类名调用类方法
    Root.staticShowTotal()  # 通过类名调用静态方法

    # Root.show()    #试图通过类名直接调用实例方法，失败
    Root.show(rr)  # 通过类名调用实例方法时为self参数显式传递对象名(对象名称)
    Root.show(r)  # 但是可以通过这种方法来调用方法并访问实例成员
    '''
    # class 3
    '''
    Test0813.param1 = '119';
    test0813 = Test0813('呼叫');
    test0813.param1 = '120'
    test0813.method1('110')
    '''
    # class 4 继承机制
    b = B();
    #b.__private('实现类传参');#私有方法不能调用
    b.public('实现类传参');
    print('dir(b):', dir(b));
    
    # class 5  -多态原理与实现-
    '''
    x = [item2() for item2 in (Animal, Cat, Dog, Tiger, Test)];
    for item in x:  # 遍历基类和派生类对象并调用show()方法
        item.show()
    '''