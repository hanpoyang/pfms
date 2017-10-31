# -*- coding: utf-8 -*-

from T51JobCenter import T51JobCenter
from jobs_cjol import jobs_cjol
import argparse
from pymongo import MongoClient
import datetime

def archive_view():
    now=datetime.datetime.now()
    timestr=now.strftime('%Y-%m-%d %H:%M:%S')
    conn=MongoClient('192.168.0.99', 27017)
    db=conn.mydb  # 连接mydb数据库，没有则自动创建
    my_set=db.jobs_hotviews
    my_set.remove({})
    my_set=db.jobs_51job
    rs = my_set.find({'name':'jobs_51job'})
    view_num = rs[0]['view_num']
    view_date=rs[0]['company_list'][0]['view_date']
    view_time=rs[0]['company_list'][0]['view_time']

    save_data = {
        'name' :  'jobs_51job_hot_view',
        'last_view_num' : view_num,
        'last_view_datetime': view_date + ' '+ view_time,
        'access_datetime' : timestr
    }
    my_set=db.jobs_hotviews
    my_set.save(save_data)
    my_set = db.jobs_cjol
    rs = my_set.find({'name' : 'jobs_cjol'})
    view_num = rs[0]['view_num']
    view_datetime=rs[0]['company_list'][0]['view_datetime']
    save_data = {
        'name': 'jobs_cjol_hot_view',
        'last_view_num': view_num,
        'last_view_datetime': view_datetime,
        'access_datetime': timestr
    }

    my_set = db.jobs_hotviews
    my_set.save(save_data)
    conn.close()

if __name__ == '__main__':

    parser=argparse.ArgumentParser(description='apply functions')
    parser.add_argument('-f', '--function',
                        help='which functions', default='01')
    options=parser.parse_args()
    function=options.function
    cjol=jobs_cjol()
    t51job = T51JobCenter()
    if function == '01':
        archive_view()
        t51job.read()
        cjol.job_seeker()
    else:
        t51job.refresh_resume()
        cjol.refresh()