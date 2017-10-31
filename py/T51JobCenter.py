# -*- coding: gb2312 -*-

import requests
import requests.packages.urllib3
import re
from pymongo import MongoClient
import argparse
import datetime


class T51JobCenter(object):

    def __init__(self, username=None, password=None):

        self.headers = {
            'Accept':'text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8',
            'Referer': 'http://www.51job.com/',
            'Upgrade - Insecure - Requests': '1',
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0',
            'ContentType': 'text/html; charset=utf-8',
            'Accept-Encoding': 'gzip, deflate, sdch',
            'Accept-Language': 'zh-CN,zh;q=0.8',
            'Connection': 'keep-alive',
            'Cookie' : 'guid=15031947215769910058; _ujz=MTE0NjU1OTU1MA%3D%3D; ps=us%3DATgFbwJjASlVNQtnUjNQfQU0BzdTYgV%252BBzNWMQgyBHhdZFQ6UDIKPwVlXzoKa1VhUWRXYwYxBmJdaQIsC3QCWgFkBS8CJw%253D%253D%26%7C%26needv%3D0; slife=resumeguide%3D1%26%7C%26lowbrowser%3Dnot%26%7C%26lastlogindate%3D20171024%26%7C%26; 51job=cuid%3D114655955%26%7C%26cusername%3Dphone_13751132206%26%7C%26cpassword%3D%26%7C%26cname%3D%25BA%25AB%25CC%25CE%26%7C%26cemail%3Dhanpoyang%2540hotmail.com%26%7C%26cemailstatus%3D3%26%7C%26cnickname%3D%26%7C%26ccry%3D.0IMrpmf.z3ts%26%7C%26cconfirmkey%3Dha0Zef8isK8A.%26%7C%26cresumeids%3D.0klp0MP..z%252FI%257C.0jGI6Ey4umt.%257C%26%7C%26cautologin%3D1%26%7C%26cenglish%3D0%26%7C%26sex%3D0%26%7C%26cnamekey%3Dhac1nDVzQpluE%26%7C%26to%3DDjdSOANlCjoHY100UDxTZVNlAn8AdlZrUjtWag9gUwwPNgVtD2pQY1AwCWQEYgQ1UWBUZVZmASgGMVw7AD5VZQ41Uj0DaQo6B25dMQ%253D%253D%26%7C%26'
        }

        self.cookies = {

        }

        self.sess = requests.session()

        self._user_name = username
        self._pass_word = password
        self._resume_center_url = 'http://i.51job.com/userset/resume_browsed.php?lang=c'
        self._view_resume_num = 0
        self._viewed_company_num = 0

    def read(self):
        resp = self.sess.request('get', self._resume_center_url, {}, {}, self.headers, self.cookies);
        resp.encoding = 'gbk'
        content = resp.text
        # 分析企业浏览数
        pattern = re.compile('<div class="warn">.*?</div>', re.S)
        stat_info_html = pattern.findall(content)
        pattern = re.compile('<span class="c_orange">(\d+)</span>')
        result = re.findall(pattern, content)
        self._view_resume_num = result[0]
        self._viewed_company_num = result[1]
        # 分析浏览的公司列表
        pattern = re.compile(u'<!-- 已购买不为空 -->.*?<!-- 分页控件 -->', re.S)
        company_list_html = pattern.findall(content)
        pattern = re.compile(r'<div class="e qy">.*?(<div class="h1">.*?</div>.*?<div class="h2">.*?</div>)', re.S)
        result = re.findall(pattern, content)
        company_view_list = [];
        now=datetime.datetime.now()
        timestr=now.strftime('%Y-%m-%d %H:%M:%S')
        if result:
            for item in result:
                pattern_str = u'<a.*?title="(.*?)".*?href="(.*?)".*?<span.*?>(.*?)</span>.*?<span.*?>(.*?)</span>'
                pattern = re.compile(pattern_str, re.S)
                match_result = re.findall(pattern, item)
                company_name = match_result[0][0]
                company_url = match_result[0][1]
                view_date = match_result[0][2]
                view_time = match_result[0][3]
                company_object = {
                    'company_name' : company_name,
                    'company_url' : company_url,
                    'view_date' : view_date,
                    'view_time' : view_time
                }
                company_view_list.append(company_object)
        save_object = {
            'name':'jobs_51job',
            'view_num' : self._view_resume_num,
            'viewed_num' : self._viewed_company_num,
            'company_list': company_view_list,
            'access_datetime': timestr
        }
        self.save_db(save_object)


    def save_db(self, data):
        conn=MongoClient('192.168.0.99', 27017)
        db=conn.mydb  # 连接mydb数据库，没有则自动创建
        my_set=db.jobs_51job
        my_set.remove({})
        my_set.save(data)
        conn.close()

    def refresh_resume(self):
        refresh_url = 'http://i.51job.com/resume/ajax/refresh_resume.php?0.399711851663876&jsoncallback=jQuery1830748338145710868_1508942146692&ReSumeID=350484795&lang=c&_=1508942209897'
        resp = self.sess.request('get', refresh_url, {}, {}, self.headers, self.cookies)
        print resp.text


def main(function_order):
    jobsite = T51JobCenter()
    if function_order == '01':
        jobsite.refresh_resume();
    else:
        jobsite.read();


if __name__ == '__main__':
    parser=argparse.ArgumentParser(description='apply functions')
    parser.add_argument('-f', '--function',
                        help='which functions', default='02')
    options=parser.parse_args()
    function = options.function
    main(function)

