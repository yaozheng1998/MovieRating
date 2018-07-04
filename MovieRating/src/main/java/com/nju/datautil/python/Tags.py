# -*- coding:utf-8 -*-
from pyhanlp import *
import pymysql

#创建数据库连接
conn=pymysql.connect(host='139.199.179.137',user='root',passwd='123456',db='Movie',charset='utf8mb4')
cursor=conn.cursor()
movieList=[]
cursor.execute('select doubanId from movieInfo')
results=cursor.fetchall()
for movie in results:
    movieList.append(movie[0])
for movieId in movieList:
    tags=[]
    allContent=[]
    # print("select content from douban_comments where doubanId="+str(movieId))
    cursor.execute("select content from douban_comments where doubanId="+str(movieId))
    reviews=cursor.fetchall()
    for review in reviews:
        allContent.append(review[0])
        #对review[0]进行标签化处理，加入到tags中
        if(len(allContent)!=0):
            singleList=HanLP.extractKeyword(review[0],5)
            for index in range(len(singleList)):
                tags.append(singleList[index])
    if(len(tags)!=0 and movieId!=26877106):
        print(str(movieId))
        print(tags)
        cursor.execute("update movieInfo set tag=\""+tags.__str__()+"\" where doubanId='"+str(movieId)+"'")
        conn.commit()
cursor.close()
conn.close()