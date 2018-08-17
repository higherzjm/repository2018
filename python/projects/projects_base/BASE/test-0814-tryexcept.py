
for i in range(1,100,2):
    if i==11 :
        try:
         i=2/0;
        except Exception as e:
            print('i', e)
            continue
    print('i',i)