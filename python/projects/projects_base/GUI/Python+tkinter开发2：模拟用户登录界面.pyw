import tkinter
import tkinter.messagebox
import tkinter.filedialog

# 创建应用程序窗口
from datetime import datetime

import xlwt

root = tkinter.Tk()
root.title('test')
root.geometry('380x170+400+300');#widthxheight+x+y.
# 在窗口上创建标签组件
labelName = tkinter.Label(root,
                          text='用户名:',
                          justify=tkinter.RIGHT,
                          width=80)
labelName.place(x=10, y=5, width=80, height=20)

# 创建字符串变量和文本框组件，同时设置关联的变量
varName = tkinter.StringVar(root, value='')
entryName = tkinter.Entry(root,
                          width=80,
                          textvariable=varName)
entryName.place(x=100, y=5, width=80, height=20)

labelPwd = tkinter.Label(root,
                         text='密码:',
                         justify=tkinter.RIGHT,
                         width=80)
labelPwd.place(x=10, y=30, width=80, height=20)

# 创建密码文本框
varPwd = tkinter.StringVar(root, value='')
entryPwd = tkinter.Entry(root,
                         show='*',
                         width=80,
                         textvariable=varPwd)
entryPwd.place(x=100, y=30, width=80, height=20)

# 登录按钮事件处理函数
def login():
    # 获取用户名和密码
    name = entryName.get()
    pwd = entryPwd.get()
    if name=='' or pwd=='':
        tkinter.messagebox.showinfo(title='sorry',message='name and pwd is not null');
        return None;
    if name=='admin' and pwd=='123456':
        tkinter.messagebox.showinfo(title='恭喜',
                                    message='登录成功！')
    else:
        tkinter.messagebox.showerror('警告',
                                     message='用户名或密码错误')
# 创建按钮组件，同时设置按钮事件处理函数
buttonOk = tkinter.Button(root,
                          text='登入',
                          command=login)
buttonOk.place(x=10, y=70, width=50, height=20)

# 取消按钮的事件处理函数
def cancel():
    #清空用户输入的用户名和密码
    varName.set('')
    varPwd.set('')
buttonCancel = tkinter.Button(root,
                              text='取消',
                              command=cancel )
buttonCancel.place(x=75, y=70, width=50, height=20)

def close():
    root.destroy();
closeBut = tkinter.Button(root,
                              text='关闭',
                              command=close )
closeBut.place(x=140, y=70, width=50, height=20)

def uploadFile():
    #'F:\tuofu2017\learn\Python\files\梅花三弄.mp3'.set(tkinter.filedialog.askopenfilename());
    file = tkinter.filedialog.askopenfile()
    if not file:
        return
    print('file',file);
    str = file.read();
    print('str:', str);
    file.close();
uploadBut = tkinter.Button(root,
                              text='上传文件',
                              command=uploadFile )
uploadBut.place(x=200, y=70, width=80, height=20);

def saveFile():
    try:
        with open('myfile.txt', 'w') as fp:  # 不存改文件会自动创建 a 追加模式，不覆盖文件中原有内容
            fp.write('aaaaaaa')
    except:
        print('write file failure');
    else:
        print('write file success');
    finally:
        fp.close();
    file = tkinter.filedialog.asksaveasfile()
    print('file',file);

saveFileBut = tkinter.Button(root,
                              text='另存为文件',
                              command=saveFile )
saveFileBut.place(x=290, y=70, width=80, height=20)
#启动消息循环
root.mainloop()
