import cv2
im = cv2.imread("E:/tuofu2018/learn/Python/files/iamges/img-0.jpg")
imGray = cv2.imread('E:/tuofu2018/learn/Python/files/iamges/img-1.jpg',cv2.CV_8SC1)
cv2.imshow("WinName",im)
cv2.imshow("Gray WinName",imGray)
cv2.waitKey(7000);#空值就一直挂着
