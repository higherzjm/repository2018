
import pickle  #使用pickle模块读写入二进制文件
import struct #使用struct模块读写入二进制文件
def fileWrite_struct_binary(str):
    n = 1300000000
    x = 96.45
    b = True
    sn = struct.pack('if?', n, x, b)  # 序列化
    print('sn',sn,sn.__len__())
    f = open('F:\learn\Python\info\myfile_struct_binary.txt', 'wb')
    f.write(sn)  # 写入字节串
    print(str.encode(),str.encode().__len__())
    f.write(str.encode())  # 字符串直接编码为字节串写入
    f.close()


def fileRead_struct_binary(length):
    f = open('F:\learn\Python\info\myfile_struct_binary.txt', 'rb')
    sn = f.read(9)
    tu = struct.unpack('if?', sn)
    print('tu',tu)
    n, x, bl = tu
    print('n=', n)
    print('x=', x)
    print('bl=', bl)
    s = f.read(65).decode()
    f.close()
    print('s=', s)



def fileWrite_pickle_binary(str):
    with open('F:\learn\Python\info\myfile_binary.txt', 'wb') as f:
        try:
            pickle.dump(len(str), f)  # 表示后面将要写入的数据个数
            pickle.dump(str, f)
        except:
            print('写文件异常!')
        finally:
            f.close();

def fileRead_pickle_binary():
    with open('F:\learn\Python\info\myfile_binary.txt', 'rb') as f:
        n = pickle.load(f)  # 读出文件的数据个数
        if n>0:
            x = pickle.load(f)
            print('x',x)



def fileWrite(str):
    try:
        with open('F:\learn\Python\info\myfile.txt','w') as fp:   #不存改文件会自动创建 a 追加模式，不覆盖文件中原有内容
            fp.write(str)
    except:
        print('write file failure');
    else:
        print('write file success');
    finally:
        fp.close();

def fileRead():
    try:
        filepath='F:/tuofu2017/learn/Python/files/askopenfile.txt'
        with open(filepath,"r") as f:
            str=f.read();
            print('str:',str);
    except Exception as e:
        print('不存在该文具或读取异常',e);
    else:
        print('read success');
    finally:
        print('close file')
        #f.close();


str = 'Hello world\n文本文件的读取方法\n文本文件的写入方法\n';

#fileWrite(str);
fileRead();

str = 'Hello world文本文件的读取方法文本文件的写入方法';
#fileWrite_pickle_binary(str)
#fileRead_pickle_binary();

#ffileWrite_struct_binary(str);
#ffileRead_struct_binary(str.__len__());