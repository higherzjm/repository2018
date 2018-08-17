from datetime import datetime
dt = datetime.now()
print(dt,type(dt))
print  ('时间：(%Y-%m-%d %H:%M:%S): ' , dt.strftime( '%Y-%m-%d %H:%M:%S ' ) );
print  ('时间：(%Y-%m-%d %H:%M:%S %f): ' , dt.strftime( '%Y-%m-%d %H:%M:%S %f' ) );
print   ('时间：(%Y-%m-%d %H:%M:%S %p): ' , dt.strftime( '%y-%m-%d %I:%M:%S %p' )  );
print  ( '星期缩写%%a: %s '  % dt.strftime( '%a' )  );
print   ('星期全拼%%A: %s '  % dt.strftime( '%A' )  );
print   ('月份缩写%%b: %s '  % dt.strftime( '%b' )  );
print   ('月份全批%%B: %s '  % dt.strftime( '%B' )  );
print   ('日期时间%%c: %s '  % dt.strftime( '%c' )  );
print   ('今天是这周的第%s天 '  % dt.strftime( '%w' )  );
print   ('今天是今年的第%s天 '  % dt.strftime( '%j' )  );
print   ('今周是今年的第%s周 '  % dt.strftime( '%U' ) );
print  ( '今天是当月的第%s天 '  % dt.strftime( '%d' ));
