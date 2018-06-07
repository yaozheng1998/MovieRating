# -*- coding:utf-8 -*-
'''根据时光网"正在售票"和"正在热映"获得电影列表，然后根据列表爬取对应的评论信息'''
import requests
from bs4 import BeautifulSoup
import pymysql
from datetime import datetime
import operator
import itertools
import re

obj = itertools.cycle(['“','”'])
_obj = lambda x: next(obj)

header={'User-Agent':'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36',}
#创建数据库连接
conn=pymysql.connect(host='139.199.179.137',user='root',passwd='123456',db='Movie',charset='utf8mb4')
cursor=conn.cursor()
movieNameList=[]
try:
    cursor.execute('select movieName from mtime')
    results=cursor.fetchall()
    for row in results:
        movieNameList.append(row[0])
except:
    print("Error: unable to fetch data")

for movieName in movieNameList:
    response=requests.get('http://maoyan.com/query?kw='+movieName+'&type=0',headers=header).content
    # f=open(response,'r',encoding='UTF-8')
    # response=f.read()
    soup=BeautifulSoup(response,'html.parser')
    # f.close()
    idURL=soup.find(name='div',attrs={"class":"channel-detail movie-item-title"}).a['href']
    movieId=idURL[7:]

    score=soup.find(name='div',attrs={"class":"channel-detail channel-detail-orange"})
    list=score.find_all('i')
    if len(list)==0:
        final_score='-1'
    else:
        final_score=list[0].string+list[1].string

    url='http://maoyan.com'+idURL
    response2=requests.get(url,headers=header).content
    #request获得url信息
    # f2=open(response2,'r',encoding='UTF-8')
    # info=f2.read()
    soup2=BeautifulSoup(response2,'html.parser')
    commentWrap=soup2.find(name='div',attrs={"class":"comment-list-container"})
    commentList=commentWrap.find_all(attrs={"class":'comment-container '})

    cursor.execute('insert into maoyan VALUES ('+movieId+',"'+movieName+'",'+final_score+')')
    print('insert into maoyan VALUES ('+movieId+',"'+movieName+'",'+final_score+')')

    for comment in commentList:
        username=comment.find(attrs={'class':'name'}).string
        commentTime = comment.find(attrs={'class': 'time'})['title']
        date = datetime.strptime(commentTime, "%Y-%m-%d %H:%M:%S")

        commentScore=comment.ul['data-score']
        content=comment.find(attrs={'class':'comment-content'}).string
        # escape(content,{'"':'&quot;'}) #ENT_COMPAT
        content=re.sub(r"['\"]", _obj, content)

        avatarURL=comment.img['src']
        commentCount=comment.find(attrs={'class':'num'}).string
        # print(username,content,'\n')

        cursor.execute('insert into maoyan_comments(movieNo,username,avatar,commentTime,commentText,commentUp,userRate) VALUES (' + movieId + ',"'+username+'","'+avatarURL+'","'+commentTime+'","'+content+'",'+commentCount+','+commentScore+')')

    # f2.close()
    conn.commit()

cursor.close()
conn.close()
