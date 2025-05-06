#!/usr/bin/python

# hello world
list =["hello", "world"]
print (list)

# 这是一个单行注释
'''
这是一个多行注释
用三引号（单引号组成）构成
'''
"""
这也是一个多行注释
用三引号（双引号组成）构成
"""

# python 对缩进非常严格，注意缩进使用的符号是空格还是 tab，同时要注意缩进的数量，不可以混用或搞错数量
# 缩进相同的一组语句构成一个代码块，我们称之代码组
# 像if、while、def和class这样的复合语句，首行以关键字开始，以冒号( : )结束，该行之后的一行或多行代码构成代码组。
# 我们将首行及后面的代码组称为一个子句(clause)
if True:
    print ("true")
else:
    print ("false")


# python 可以使用单引号(')、双引号(")、三引号(''' 或 """)来表示字符串，其中三引号可以由多行组成
world = 'world'
sentence = "这是一句子"
paragraph = """这是一个段落，
包含了多个🍊"""
print (world, "\n", sentence, "\n", paragraph)
# print 默认换行，需要不换行需要在变量之间加上,

# 函数之间或类的方法之间用空行分隔，表示一段新的代码的开始

# 函数
def printme (param):
    print (param)
# 函数调用
printme("this is a method call")

# 用户输入 & 同一行显示多条语句使用 ; 分隔
# user_input = input("按下 enter 键退出，其他任意键显示...\n"); print (user_input)

# 多个变量赋值
a = b = c = 1
print (a,b,c)
a, b, c = 1,2,"john"
print (a,b,c)

# python 的五个标准类型：Numbers、String、List、Tuple、Dictionary

# Numbers（数字）
var = 10
print (var)
# 不可改变的数据类型，指定一个值时，Number对象被创建，可以使用 del 语句删除对象引用
del var
# print (var) # 执行会报错
# 支持 4 种类型的数字类型：int、long、float、complex

# String（字符串）
var = "this is a string"
print (var)
# 字符串有两种取值顺序：
    # 从左到右索引默认0开始的，最大范围是字符串长度少1
    # 从右到左索引默认-1开始的，最大范围是字符串开头
print (var[0], var[1])
print (var[-1], var[-2])
# 子串获取，采用前闭后开取值，子串会创建新的对象
print (var[5:9])

# List（列表）：使用最频繁的数据类型。列表可以完成大多数集合类的数据结构实现。它支持字符，数字，字符串甚至可以包含列表（即嵌套）。 
# 列表取值顺序、子列表取值规则同字符串
list = [ 'runoob', 786 , 2.23, 'john', 70.2 ]
tinylist = [123, 'john']
 
print (list)               # 输出完整列表
print (list[0])            # 输出列表的第一个元素
print (list[1:3])          # 输出第二个至第三个元素 
print (list[2:])           # 输出从第三个开始至列表末尾的所有元素
print (tinylist * 2)       # 输出列表两次
print (list + tinylist)    # 打印组合的列表
# Python 列表截取可以接收第三个参数，参数作用是截取的步长
letters = ['a' , 'b', 'c', 'd', 'e']
print (letters[0:5:2]) # 步长为 2

# Tuple（元组）：用 () 标识。内部元素用逗号隔开。但是元组不能二次赋值，相当于只读列表
tuple = ( 'runoob', 786 , 2.23, 'john', 70.2 )
tinytuple = (123, 'john')
 
print (tuple)               # 输出完整元组
print (tuple[0])            # 输出元组的第一个元素
# tuple[0] = 1 # 元组不允许更新
print (tuple[1:3])          # 输出第二个至第四个（不包含）的元素 
print (tuple[2:])           # 输出从第三个开始至列表末尾的所有元素
print (tinytuple * 2)       # 输出元组两次
print (tuple + tinytuple)   # 打印组合的元组

# Dictionary（字典）：最灵活的内置数据结构类型，用"{ }"标识。字典由索引(key)和它对应的值value组成。
# 列表是有序的对象集合，字典是无序的对象集合。区别是字典通过键取值，列表通过偏移取值
dict = {}
dict['one'] = "This is one"
dict[2] = "This is two"
 
tinydict = {'name': 'runoob','code':6734, 'dept': 'sales'}
 
 
print (dict['one'])   # 输出键为'one' 的值
print (dict[2])   # 输出键为 2 的值
print (tinydict)   # 输出完整的字典
print (tinydict.keys())   # 输出所有键
print (tinydict.values())    # 输出所有值

# 运算符：算术、比较、赋值、逻辑、位、成员、身份
# 算术运算符：+、-、*、/、%、**(幂)、//(整除)

# 比较：==（对象比较）、!=、<>（同 != python3 已废弃）、>、<、>=、<=
# 所有比较运算符返回1表示真，返回0表示假。这分别与特殊的变量 True 和 False 等价

# 赋值：=、+=、-=、*=、/=、%=、**=、//=

# 位：&、|、^、~、<<、>>

# 逻辑：and、or、not

# 成员：in、not ina = 10
a = 2
b = 20
list = [1, 2, 3, 4, 5 ];
 
if ( a in list ):
   print ("1 - 变量 a 在给定的列表中 list 中")
else:
   print ("1 - 变量 a 不在给定的列表中 list 中")
 
if ( b not in list ):
   print ("2 - 变量 b 不在给定的列表中 list 中")
else:
   print ("2 - 变量 b 在给定的列表中 list 中")

# 身份：is、is not 判断两个标识符是不是引用自同一个对象
a = 20
b = 20
 
if ( a is b ):
   print ("1 - a 和 b 有相同的标识")
else:
   print ("1 - a 和 b 没有相同的标识")
 
if ( a is not b ):
   print ("2 - a 和 b 没有相同的标识")
else:
   print ("2 - a 和 b 有相同的标识")
 
# 修改变量 b 的值
b = 30
if ( a is b ):
   print ("3 - a 和 b 有相同的标识")
else:
   print ("3 - a 和 b 没有相同的标识")
 
if ( a is not b ):
   print ("4 - a 和 b 没有相同的标识")
else:
   print ("4 - a 和 b 有相同的标识")

# 运算符优先级从高到低
# **	指数 (最高优先级)
# ~ + -	按位翻转, 一元加号和减号 (最后两个的方法名为 +@ 和 -@)
# * / % //	乘，除，取模和取整除
# + -	加法减法
# >> <<	右移，左移运算符
# &	位 'AND'
# ^ |	位运算符
# <= < > >=	比较运算符
# <> == !=	等于运算符
# = %= /= //= -= += *= **=	赋值运算符
# is is not	身份运算符
# in not in	成员运算符
# not and or	逻辑运算符 

# 条件语句：if elif else
# 循环语句：while、for、嵌套、break、continue、pass（pass是空语句，是为了保持程序结构的完整性。）
# while
n = 5
i = 1
while(i <= 5):
    print (i)
    i+=1
else:
    print (i)
# for
for letter in "Python":
    print (letter)
fruits = ["banana", "apple", "mango"]
for fruit in fruits:
   print (fruit)
for index in range(len(fruits)): # 使用索引迭代
    print (fruits[index])
for num in range(10, 20): 
    for i in range(2, num): # 嵌套
      if num%i == 0: 
         j = num/i
         print (j)
         break
    else:   # 循环退出后执行
        print ('%d 是一个质数' % num)
# pass
# 输出 Python 的每个字母
for letter in 'Python':
   if letter == 'h':
      pass # 跳过执行当前代码块
      print ('这是 pass 块')
   print ('当前字母 :', letter)

# 日期和时间
import time
# 时间戳单位最适于做日期运算。但是1970年之前的日期就无法以此表示了。太遥远的日期也不行，UNIX和Windows只支持到2038年。
ticks = time.time()
print ("当前时间:", ticks)
# 当前时间。Python函数用一个元组装起来的 9 组数字处理时间
localtime = time.localtime(time.time())
print ("本地时间为 :", localtime)
# 可以根据需求选取各种格式，但是最简单的获取可读的时间模式的函数是asctime():
localtime = time.asctime( time.localtime(time.time()) )
print ("本地时间为 :", localtime)
# 可以使用 time 模块的 strftime 方法来格式化日期，：
localtime = time.localtime()
formatted_time = time.strftime("%Y-%m-%d %H:%M:%S", localtime)
print (formatted_time)
# 日历
import calendar
cal = calendar.month(2016, 1)
print ("以下输出2016年1月份的日历:")
print (cal)

# 函数、模块、文件 I/O、File、异常处理、OS 文件/目录、
# 高级特性：面向对象、正则表达式、CGI编程、MySQL、网络编程、SMTP、多线程、XML 解析、GUI 编程、AI 等
