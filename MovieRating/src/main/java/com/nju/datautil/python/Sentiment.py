# -*- coding:utf-8 -*-
from aip import AipNlp
import pymysql
import json

#lalalala
APP_ID = ''
API_KEY = ''
SECRET_KEY = ''
client = AipNlp(APP_ID, API_KEY, SECRET_KEY)

#创建数据库连接
conn=pymysql.connect(host='139.199.179.137',user='root',passwd='123456',db='Movie',charset='utf8mb4')
cursor=conn.cursor()
userIdList=[]
# cursor.execute('select distinct uid from douban_comments')
# results=cursor.fetchall()
# for row in results:
#     userIdList.append(row[0])
# # cursor.execute("insert into user(userId) VALUES ('" + userIdList[0] + "')")
# for id in userIdList:
#     cursor.execute("insert into user(userId) VALUES ('"+id+"')")
#     conn.commit()
index=0
cursor.execute('select userId from user where likes="" and dislikes="";')
results=cursor.fetchall()
for row in results:
    userIdList.append(row[0])
for user in userIdList:
    cursor.execute("select doubanId,content,uid from douban_comments where uid='"+user+"'")
    movies=cursor.fetchall()
    for i in range(len(movies)):
        index+=1
        content=movies[i][1]
        print(str(index)+" "+movies[i][2]+" "+content)
        senti=client.sentimentClassify(content)
        pcn=(senti['items'][0]).get('positive_prob')
        if(pcn>=0.5):
            # good
            # print("update user set likes=concat(likes,'"+str(movies[i][0])+",') where userId='"+movies[i][2]+"'")
            cursor.execute("update user set likes=concat(likes,'"+str(movies[i][0])+",') where userId='"+movies[i][2]+"'")
        else:
            cursor.execute("update user set dislikes=concat(dislikes,'" + str(movies[i][0]) + ",') where userId='" + movies[i][2] + "'")
        conn.commit()

cursor.close()
conn.close()