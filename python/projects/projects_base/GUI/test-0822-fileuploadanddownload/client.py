# -*- coding: UTF-8 -*-
from tkinter import *
import tkinter.filedialog
import tkinter.messagebox
from pip._vendor import requests
import xlrd

def Upload():
    print('upload')
    selectFileName = tkinter.filedialog.askopenfilename(title='选择文件')  # 选择文件

    r = requests.post('http://127.0.0.1:8080/upload', files={'file': open(selectFileName, 'rb')})
    print(r.content.decode(encoding='gbk', errors='strict'));
    setText =r.content.decode(encoding='gbk', errors='strict')
    print(setText.__class__)
    e1.delete(0, END)
    e1.insert(0, setText)

def Upload2():
    print('upload')
    selectFileName = tkinter.filedialog.askopenfilename(title='选择文件')  # 选择文件

    r = requests.post('http://127.0.0.1:8080/upload', files={'file': open(selectFileName, 'r')})
    setText =r.content
    print('setText', setText)
    setText=setText.decode(encoding='utf-8', errors='strict')
    print('setText',setText)
    e1.delete(0, END)
    e1.insert(0, setText)
def Upload_excel():
    print('uploadexcel')
    selectFileName = tkinter.filedialog.askopenfilename(title='选择文件')  # 选择文件
    filetyoe=selectFileName[selectFileName.find('.')+1:selectFileName.__len__()];
    if filetyoe!='xls':
        tkinter.messagebox.showerror('警告',message='请上传xls文件类型');
        return
    workbook= xlrd.open_workbook(selectFileName);
    datas=readexceldatas(workbook);
    print('send datas length',datas.__len__());
    datas=str(datas).encode('utf-8');
    postdata = {'datas':datas};
    r = requests.post('http://127.0.0.1:8080/uploadxls',data=postdata)
    setText =r.content.decode(encoding='utf-8', errors='strict')
    print('retdats', type(setText));
    lastmername=parseStr(setText);
    e1.delete(0, END)
    e1.insert(0, lastmername)
    r.close();
def Download():
    link = e1.get();
    print(link)
    path = tkinter.filedialog.asksaveasfilename();
    print('path',path);
    with open(path, 'w') as f:
        f.write(link)


def  readexceldatas(TC_workbook):
    all_sheets_list = TC_workbook.sheet_names()
    print("All sheets name in File:", all_sheets_list, all_sheets_list.__len__())
    length = all_sheets_list.__len__();
    allsheets = [];
    allheaders = [];
    alldatas = [];
    for x in range(0, length):
        allsheets.append(x);
        mysheet = TC_workbook.sheet_by_index(x);
        for rownum in range(0, mysheet.nrows):
            if rownum == 0:
                allheaders.append(mysheet.row_values(rownum));
            if rownum != 0:
                alldatas.append(mysheet.row_values(rownum));
    return alldatas;
def parseStr(str):
    i = 1;
    list = [];
    while i == 1:
        str = str.replace('\'', '').replace('\"', '').strip(']').strip('[');
        if str.find('[') > -1:
            list0 = str[0:str.find(']')].split(',');
            list.append(list0);
            list1 = str[str.rfind('[') + 1:str.__len__()].split(',');
            list.append(list1);
            str = str[str.find('['):str.rfind(']') + 1];
        else:
            if str.__len__() <= 1:
                break;
            i = 0;
            list0 = str.split(',');
            list.append(list0);

    print('list size', list.__len__());
    lastmername=list[list.__len__() - 1][0];
    print('lastmername',lastmername )
    return lastmername;




root = Tk()
root.title('Download')
root.geometry('380x120+800+300')

e1 = Entry(root, width=50)
e1.grid(row=0, columnspan=3)

btn1 = Button(root, text=' 上传txt ', command=Upload).grid(row=1, column=0,padx=20)
btn1 = Button(root, text=' 上传txt2 ', command=Upload2).grid(row=1, column=1)
btn2 = Button(root, text=' 下载txt ', command=Download).grid(row=1, column=2,padx=20)
btn3 = Button(root, text=' 上传excel ', command=Upload_excel).grid(row=2, column=0,padx=20,pady=20)
btn4 = Button(root, text=' 下载exel ', command=Download).grid(row=2, column=1,pady=20)
mainloop()