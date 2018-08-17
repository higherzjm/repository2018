import itertools
#生成器包->惰性求职
horses = [1, 2, 3, 4]
races = itertools.permutations(horses)
print(races)
print('----------------------')
print(list(races))
print(races)
