import os
import re  # 正则表达式库
import sys
import time
import chardet
import datetime
import tkFileDialog
import tkMessageBox
from   Tkinter import *
filename = ""


def get_file():
    global filename
    # 创建文件对话框,只打开txt类型文件
    filename = tkFileDialog.askopenfilename(filetypes=[("text file", "*.txt")])
    var.set(filename)


def convert_result():
    tkMessageBox.showinfo("转换结果", u"CAN数据解密成功")


# 获取高位
def get_H(tmp):
    return (tmp & 0xF0) >> 4


# 获取低位
def get_L(tmp):
    return tmp & 0x0F


# 拼接成一个字节 8bits
def get_all_byte(H, L):  # 注意优先级+高于<<高于&
    return ((L & 0x0F) + ((H & 0x0F) << 4))


# 拼接一个半字 16bits
def get_half_word(byteH, byteL):
    return (((byteH & 0xFF) << 8) + (byteL & 0xFF))


# 拼接一个字 32bits H->L
def get_one_word(byte3, byte2, byte1, byte0):
    ha_wd1 = ((byte3 & 0xFF) << 8) + (byte2 & 0xFF)
    ha_wd0 = ((byte1 & 0xFF) << 8) + (byte0 & 0xFF)
    return (ha_wd1 << 16) + ha_wd0


# 数据解密
def data_unlock(buff):
    ##-------------拆分数据-----------##

    FrontCon_data0_L = get_L(buff[0])
    FrontCon_data0_H = get_H(buff[0])
    FrontCon_data1_L = get_L(buff[1])
    FrontCon_data1_H = get_H(buff[1])

    FrontCon_data2_L = get_L(buff[2])
    FrontCon_data2_H = get_H(buff[2])
    FrontCon_data3_L = get_L(buff[3])
    FrontCon_data3_H = get_H(buff[3])

    FrontCon_data4_L = get_L(buff[4])
    FrontCon_data4_H = get_H(buff[4])
    FrontCon_data5_L = get_L(buff[5])
    FrontCon_data5_H = get_H(buff[5])

    FrontCon_data6_L = get_L(buff[6])
    FrontCon_data6_H = get_H(buff[6])
    FrontCon_data7_L = get_L(buff[7])
    FrontCon_data7_H = get_H(buff[7])

    ##-------------解码数据-----------##

    Center_data0_L = FrontCon_data0_L
    Center_data0_H = FrontCon_data0_H
    Center_data1_L = FrontCon_data1_L
    Center_data1_H = FrontCon_data1_H

    Center_data2_L = FrontCon_data2_L ^ Center_data0_L
    Center_data2_H = FrontCon_data2_H ^ Center_data0_L
    Center_data3_L = FrontCon_data3_L ^ Center_data0_L
    Center_data3_H = FrontCon_data3_H ^ Center_data0_L

    Center_data4_L = FrontCon_data4_L ^ Center_data0_L
    Center_data4_H = FrontCon_data4_H ^ Center_data0_L
    Center_data5_L = FrontCon_data5_L ^ Center_data0_L
    Center_data5_H = FrontCon_data5_H ^ Center_data0_L

    Center_data6_L = FrontCon_data6_L ^ Center_data0_L
    Center_data6_H = FrontCon_data6_H ^ Center_data0_L
    Center_data7_L = FrontCon_data7_L ^ Center_data0_L
    Center_data7_H = FrontCon_data7_H ^ Center_data0_L

    OverCon_data7_L = Center_data0_L
    OverCon_data0_L = Center_data0_H
    OverCon_data7_H = Center_data1_L
    OverCon_data1_L = Center_data1_H

    OverCon_data4_L = Center_data2_L
    OverCon_data2_L = Center_data2_H
    OverCon_data4_H = Center_data3_L
    OverCon_data3_L = Center_data3_H

    OverCon_data0_H = Center_data4_L
    OverCon_data1_H = Center_data4_H
    OverCon_data2_H = Center_data5_L
    OverCon_data3_H = Center_data5_H

    OverCon_data5_L = Center_data6_L
    OverCon_data6_L = Center_data6_H
    OverCon_data5_H = Center_data7_L
    OverCon_data6_H = Center_data7_H

    buff[0] = get_all_byte(OverCon_data0_H, OverCon_data0_L)
    buff[1] = get_all_byte(OverCon_data1_H, OverCon_data1_L)
    buff[2] = get_all_byte(OverCon_data2_H, OverCon_data2_L)
    buff[3] = get_all_byte(OverCon_data3_H, OverCon_data3_L)

    buff[4] = get_all_byte(OverCon_data4_H, OverCon_data4_L)
    buff[5] = get_all_byte(OverCon_data5_H, OverCon_data5_L)
    buff[6] = get_all_byte(OverCon_data6_H, OverCon_data6_L)
    buff[7] = get_all_byte(OverCon_data7_H, OverCon_data7_L)

    # 校验
    FrontCon_data0_L = (OverCon_data0_L + OverCon_data0_H + OverCon_data1_L + OverCon_data1_H +
                        OverCon_data2_L + OverCon_data2_H + OverCon_data3_L + OverCon_data3_H + OverCon_data4_L +
                        OverCon_data4_H + OverCon_data5_L + OverCon_data5_H + OverCon_data6_L + OverCon_data6_H)

    # print 'Front_0L',hex(FrontCon_data0_L),'Over_7H',hex(OverCon_data7_H)
    # 注意C语言为公用体，累计会导致溢出，故只取低4bit
    if FrontCon_data0_L & 0x0F == OverCon_data7_H & 0x0F:
        return 1
    else:
        return 0


def unlock_can_data():
    global filename
    # filename = r'C:\Users\smart\Desktop\cal_money\raw_can_data.txt'
    # filename = 'C:/Users/smart/Desktop/摇床0等待CAN数据/0820_hunlunfh.txt'
    dir_idx = filename.rfind('/')  # 双斜杠表示查找'\',防止转义
    dir = filename[:dir_idx + 1]
    print
    'file name:', filename, 'dir:', dir

    new_file = dir + 'unlock_can_data.txt'
    new_f = open(new_file, 'w')

    cur_time = '当前时间:' + time.strftime("%Y-%m-%d %H:%M:%S", time.localtime()) + '\n'
    new_f.write('源文件' + filename + '\n')
    new_f.write(cur_time)
    f = open(filename, 'r')
    for line in f:
        new_line = line
        # print line.strip()
        tmp = line.strip()
        cmd_id = 0
        can_data = []
        # 从后向前查找0x08
        if tmp.rfind('0x08') != -1:
            # print 'data',tmp[tmp.rfind('0x08')+11:],'index:',tmp[tmp.rfind('0x08')+11]
            start_idx = tmp.rfind('0x08') + 11
            end_idx = len(tmp)
            for i in range(8):
                can_data.append(eval('0x' + tmp[start_idx:start_idx + 3]))
                start_idx += 3
                # print 'd0 to d7',can_data

            # 查找命令ID
            if tmp.find('0x') != -1:
                start_idx = tmp.find('0x')
                # 判断是否是伺服设备ID('44')
                if tmp[start_idx + 8:start_idx + 10] == '44':
                    cmd_id = eval(tmp[start_idx:start_idx + 10]) >> 11
                    # print 'cmd id:',hex(cmd_id)

                    # 处理各种ID的数据
                    if cmd_id == 0x03:
                        if data_unlock(can_data) == 1:  # 数据正确，解密成功
                            qei = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                            spd = can_data[4]
                            ctl = can_data[5]
                            if qei > 0x7FFFFFFF:
                                qei = 0xFFFFFFFF + 1 - qei
                                new_line = new_line.strip() + '(' + 'qei:' + '-' + str(qei) + ' spd:' + str(
                                    spd) + ' ctl:' + str(ctl) + ')' + '\n'
                            else:
                                new_line = new_line.strip() + '(' + 'qei:' + str(qei) + ' spd:' + str(
                                    spd) + ' ctl:' + str(ctl) + ')' + '\n'
                        else:
                            print
                            'unlock failed.'

                    elif cmd_id == 0x04:
                        if data_unlock(can_data) == 1:  # 数据正确，解密成功
                            set_qei = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                            if set_qei > 0x7FFFFFFF:
                                set_qei = 0xFFFFFFFF + 1 - set_qei
                                new_line = new_line.strip() + '(' + 'set_qei:' + '-' + str(set_qei) + ')' + '\n'
                            else:
                                new_line = new_line.strip() + '(' + 'set_qei:' + str(set_qei) + ')' + '\n'
                        else:
                            print
                            'unlock failed.'

                    elif cmd_id == 0x08:
                        qei = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        spd = can_data[4]
                        ctl = can_data[5]
                        if qei > 0x7FFFFFFF:
                            qei = 0xFFFFFFFF + 1 - qei
                            new_line = new_line.strip() + '(' + 'qei:' + '-' + str(qei) + ' spd:' + str(
                                spd) + ' ctl:' + str(ctl) + ')' + '\n'
                        else:
                            new_line = new_line.strip() + '(' + 'qei:' + str(qei) + ' spd:' + str(spd) + ' ctl:' + str(
                                ctl) + ')' + '\n'

                    elif cmd_id == 0x09:
                        set_qei = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        if set_qei > 0x7FFFFFFF:
                            set_qei = 0xFFFFFFFF + 1 - set_qei
                            new_line = new_line.strip() + '(' + 'set_qei:' + '-' + str(set_qei) + ')' + '\n'
                        else:
                            new_line = new_line.strip() + '(' + 'set_qei:' + str(set_qei) + ')' + '\n'

                    elif cmd_id == 0x100:
                        spd = get_half_word(can_data[1], can_data[0])  # H->L
                        spd1 = get_half_word(can_data[3], can_data[2])
                        pls = get_half_word(can_data[5], can_data[4])
                        pls1 = get_half_word(can_data[7], can_data[6])
                        if (pls > 0x7FFF) and (pls1 > 0x7FFF):
                            pls = 0xFFFF + 1 - pls
                            pls1 = 0xFFFF + 1 - pls1
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' spd1:' + str(
                                spd1) + ' pls:' + '-' + str(pls) + ' pls1:' + '-' + str(pls1) + ')' + '\n'
                        elif pls > 0x7FFF:
                            pls = 0xFFFF + 1 - pls
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' spd1:' + str(
                                spd1) + ' pls:' + '-' + str(pls) + ' pls1:' + str(pls1) + ')' + '\n'

                        elif pls1 > 0x7FFF:
                            pls1 = 0xFFFF + 1 - pls1
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' spd1:' + str(
                                spd1) + ' pls:' + str(pls) + ' pls1:' + '-' + str(pls1) + ')' + '\n'
                        else:
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' spd1:' + str(
                                spd1) + ' pls:' + str(pls) + ' pls1:' + str(pls1) + ')' + '\n'
                    elif cmd_id == 0x101:
                        nexspd = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        nexpls = get_one_word(can_data[7], can_data[6], can_data[5], can_data[4])
                        new_line = new_line.strip() + '(' + 'nexspd:' + str(nexspd) + ' nexpls:' + str(
                            nexpls) + ')' + '\n'
                    elif cmd_id == 0x102:
                        startpos = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        endpos = get_one_word(can_data[7], can_data[6], can_data[5], can_data[4])
                        new_line = new_line.strip() + '(' + 'startpos:' + str(startpos) + ' endpos:' + str(
                            endpos) + ')' + '\n'
                    elif cmd_id == 0x103:
                        limL = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        limR = get_one_word(can_data[7], can_data[6], can_data[5], can_data[4])
                        if limL > 0x7FFFFFFF:
                            limL = 0xFFFFFFFF + 1 - limL
                            new_line = new_line.strip() + '(' + 'limL:' + '-' + str(limL) + ' limR:' + str(
                                limR) + ')' + '\n'
                        else:
                            new_line = new_line.strip() + '(' + 'limL:' + str(limL) + ' limR:' + str(limR) + ')' + '\n'
                    elif cmd_id == 0x110:
                        spd = get_one_word(can_data[3], can_data[2], can_data[1], can_data[0])
                        pls = get_one_word(can_data[7], can_data[6], can_data[5], can_data[4])
                        if pls > 0x7FFFFFFF:
                            pls = 0xFFFFFFFF + 1 - pls
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' pls:' + '-' + str(
                                pls) + ')' + '\n'
                        else:
                            new_line = new_line.strip() + '(' + 'spd:' + str(spd) + ' pls:' + str(pls) + ')' + '\n'
                    else:
                        new_line = new_line.strip() + '\n'
        else:
            pass
        new_f.write(new_line.decode('gbk'))
        # print 'unlock finished.\n'
    f.close()
    new_f.close()
    convert_result()  # 弹出消息，解密成功


if __name__ == '__main__':
    reload(sys)
    sys.setdefaultencoding('utf-8')
    # 创建根窗口
    root = Tk()
    root.title("CAN数据解密")
    root.geometry("400x200+450+210")  # width x height;起始坐标

    frame = Frame(root)
    frame.pack()

    frm_L = Frame(frame)
    var = StringVar()
    Label(frm_L, text="").pack()
    Entry(frm_L, textvariable=var, bd=1).pack(expand=1)
    Label(frm_L, text="").pack()
    frm_L.pack()

    frm_R = Frame(frame)
    Button(frm_R, text="打开文件", command=get_file, height=2, width=8).pack(side=LEFT)
    Button(frm_R, text="解密", command=unlock_can_data, height=2, width=10).pack(side=RIGHT)
    frm_R.pack()

    frm_D = Frame(root)
    Button(frm_D, text="退出", command=frm_R.quit, height=2, width=8, bg="#B0D060").pack()
    frm_D.pack(side=BOTTOM)

    root.mainloop()