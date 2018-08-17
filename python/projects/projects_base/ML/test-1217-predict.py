
import pandas as pd
from sklearn.ensemble import RandomForestRegressor
import numpy as np

data = pd.read_csv('E:/tuofu2018/learn/Python/files/csv/m9000.csv', encoding='gbk')
data.head()
len(data)
data['ret1'] = (data['收盘价'] - data['收盘价'].shift(1)) / data['收盘价'].shift(1)
data['ret'] = data['ret1'].shift(-1)
del data['ret1']
data = data[:len(data) - 1]
data['ret']
data = data.fillna(0)
df_train = data.ix[::, list(range(7, 31))]
df_target = data.ix[::, 31]
value = []
predict_ret = []
for i in range(len(df_train)):
    if i > 300:
        rf = RandomForestRegressor()
        train = np.array(df_train[i - 300:i])
        # print(train)
        target = np.array(df_target[i - 300:i])
        rf.fit(train, target)  # 进行模型的训练
        aaa = np.array(df_train.ix[i, ::])
        aab = list(df_train.ix[i, ::])

        a = rf.predict(aaa)
        predict_ret.append(float(a))
        b = df_target.ix[i, ::]

        if float(a) * float(b) >= 0:
            value.append(1)
        else:
            value.append(0)

value
count = 0
for i in range(len(value)):
    if value[i] == 1:
        count = count + 1
len(value)
print(count / len(value))
data.ret.plot()
data.ret.mean()
predict_ret = pd.Series(predict_ret)
d1 = pd.DataFrame(data['ret'], )
d1
d1.to_csv('预测.csv')
predict_ret