import os
from selenium import webdriver
import time
class seleniumtest():
 def __init__(self):
  print('初始化')

 def bbsautologin(self,UserName,PassWord,URL):
   self.driver = webdriver.Chrome()
   self.driver.get(URL)
   self.driver.find_element_by_xpath("//*[@id='ls_username']").send_keys(UserName)
   self.driver.find_element_by_xpath("//*[@id='ls_password']").send_keys(PassWord)
   self.driver.find_element_by_xpath("//*[@id='lsform']/div/div[1]/table/tbody/tr[2]/td[3]/button/em").click()

 def baiducloudautouploaddata(self,UserName,PassWord,URL):
   self.driver = webdriver.Chrome()
   self.driver.get(URL)
   self.driver.find_element_by_xpath('/html/body/div[1]/div[3]/div[6]/div/div[6]/div[2]/a').click()
   self.driver.find_element_by_xpath('/html/body/div[1]/div[3]/div[6]/div/div[3]/form/p[5]/input').send_keys(UserName)
   self.driver.find_element_by_xpath('/html/body/div[1]/div[3]/div[6]/div/div[3]/form/p[6]/input').send_keys(PassWord)
   self.driver.find_element_by_xpath('/html/body/div[1]/div[3]/div[6]/div/div[3]/form/p[9]/input').click()
   #设置思考时间
   time.sleep(30)
   sreach_window = self.driver.current_window_handle # 此行代码用来定位当前页面
   self.driver.find_element_by_xpath('//*[@id="h5Input0"]').click()
   os.system(r"D:\Program Files\autoit-tools\myau3.exe")

 def cloud360autouploaddata(self, UserName, PassWord, URL):
    self.driver = webdriver.Firefox()
    self.driver.get(URL)
    self.driver.find_element_by_xpath("//*[@id='quc_account_381322590']").send_keys(UserName)
    self.driver.find_element_by_xpath("//*[@id='quc_password_381322591']").send_keys(PassWord)
    self.driver.find_element_by_xpath("//*[@id='login']/div/div[1]/form/p[5]/input").click()
if __name__ == '__main__':
    seleniumtestobject=seleniumtest()
    #seleniumtestobject.bbsautologin('higherzjm','Abc123456','http://www.fubbs.cn/forum.php')
    #seleniumtestobject.baiducloudautouploaddata('higherzjm', 'abc@123456', 'https://pan.baidu.com/')
    seleniumtestobject.cloud360autouploaddata('higherzjm', 'Abc123456', 'https://eyun.360.cn/')