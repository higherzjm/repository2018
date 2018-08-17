import re

def   test1():
    text = 'alpha. beta....gamma delta'
    a=re.split('[\. ]+', text)       #使用指定字符作为分隔符进行分隔
    print('a:',a);

def  test2():
    example = 'Bah ShanDong Institute of Business and Technology Big'
    pattern = re.compile(r'\bB\w+\b');#查找以B开头的单词
    a=pattern.findall(example);#方法在字符串指定范围中查找所有符合正则表达式的字符串并以列表形式返回。
    print('a:',a);
    a=pattern.match(example);#方法在字符串开头或指定位置进行搜索，模式必须出现在字符串开头或指定位置
    print('a:',a);
    a=pattern.search(example);#方法在整个字符串或指定范围中进行搜索
    print('a:',a);


def test3():
    text='<a rel="nofollow" href="http://www.laffords.co.uk/shopping_cart.html?a=1">0 Items</a> - &pound;0.00 - ';
    regex = '^(ftp|https|http?):\/\/[\w\-]+(\.[\w\-]+)+([\w\-\.,@?^=%&:\/~\+#]*[\w\-\@?^=%&\/~\+#])?$';
    pattern = re.compile(regex);
    ret = pattern.findall(text);
    print('ret',ret)

def test4():
    regex='^[1-9]\d*$';
    str='1111';
    pattern = re.compile(regex);
    a_match=pattern.match(str);
    print(a_match);#不存在能匹配的子串时将返回None
    print(a_match.group(0))
    a_list=pattern.findall(str);
    print(a_list)

    regex = r'sbc';
    str=r'abc';
    pattern = re.compile(regex);
    a_list = pattern.findall(str);
    print(a_list)
    a_match=pattern.match(str);
    print(a_match);#不存在能匹配的子串时将返回None
    print(a_match.group(0))

#查找指定单独字符串
def test5():
    str = "nike i nike anikeanikenike a nikeanike123a nike aa nike 122 nike *nike nike"
    str="bottoms"
    #p1 = "https*://"  # 看那个星号！
    #p1 = "\snike\s|\snike\W|\Wnike\s"
    p1="\snike$|^nike\s|\snike\s"
    p1="\stoms$|^toms\s|\stoms\s"
    pattern = re.compile(p1)
    a_list=pattern.findall(str);
    print(a_list,a_list.__len__())

    a_match=pattern.match(str);
    print(a_match)


def test6():
    str = "nitk1e i 1 * 2lifke nike aniketnikenikea niket123a"
    p="(?![a - zA - Z] * f)(?=[a - zA - Z] * t)\b[a - zA - Z] +\b";
    p="\W" #匹配任何非单词字符
    pattern = re.compile(p)
    a_list=pattern.findall(str);
    print(a_list,a_list.__len__())
    a_match = pattern.match(str);
    print(a_match)
def  test7():
    str="<a href='https://imgsa.baidu.com/forum/w%3D580/sa/myimage.jpg'>我的图片<span>";
    reg='<.+>';#贪心的
    #reg="href='(.+\.jpg)'";
    pattern = re.compile(reg)
    a_list=pattern.findall(str);
    print(a_list, a_list.__len__())

    reg = '<.+?>';  # 非贪心的
    #reg = "href='(.+?\.jpg)'";
    pattern = re.compile(reg)
    a_list = pattern.findall(str);
    print(a_list, a_list.__len__())

if __name__ == '__main__':
    #test1();
    #test2()
    #test3()
    #test4();
    #test5();
    #test6();
    test7();