# -*- coding: utf-8 -*-
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
import time
import requests
import requests.packages.urllib3
from pymongo import MongoClient
import demjson
import platform
import datetime
import re


class jobs_cjol(object):
    def __init__(self):
        self.sess = requests.Session()
        system_name = platform.system()
        #判断系统，Linux默认会报异常
        if system_name == 'Windows':
            self.driver=webdriver.PhantomJS()
        else:
            phantomjs_dir = '/home/upload/phantomjs/phantomjs-1.9.7-linux-x86_64/bin/phantomjs'
            self.driver=webdriver.PhantomJS(phantomjs_dir)

        self.login_page = 'http://www.cjol.com/jobseekers/login.aspx?ReturnUrl=http://www.cjol.com/jobseekers/Default.aspx';
        self.job_seeker_url = 'http://www.cjol.com/jobseekers/Default.aspx'
        self.view_history = 'http://www.cjol.com/jobseekers/JobOpportunity/ViewCompanyList.aspx'
        self.headers = {
            'Accept':'application/json, text/javascript, */*; q=0.01',
            'Referer': 'http://www.cjol.com/jobseekers/JobOpportunity/ViewCompanyList.aspx',
            'User-Agent': 'Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.221 Safari/537.36 SE 2.X MetaSr 1.0',
            'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8',
            'Accept-Encoding': 'gzip, deflate, sdch',
            'Accept-Language': 'zh-CN,zh;q=0.8',
            'Connection': 'keep-alive'
        }

        self.cookie = {}
        self.view_num = 0

    def login(self):
        self.driver.get(self.login_page)
        username_box = self.driver.find_element_by_id('txtUserName')
        username_box.send_keys('***********')
        username_box.send_keys(Keys.TAB)

        password_box = self.driver.find_element_by_id('txtPassword')
        password_box.send_keys('**********')
        self.driver.find_element_by_id('btnLogin_input').click()
        time.sleep(5)
        cookies=self.driver.get_cookies()
        for cookie in cookies:
            self.cookie[cookie[u'name']]=cookie[u'value']

    def job_seeker(self):
        self.login()
        self.driver.get(self.job_seeker_url)
        #print self.driver.page_source
        view_num_link = self.driver.find_element_by_id('ctl00_ContentPlaceHolder1_my_view_count')
        self.view_num = view_num_link.text
        company_list = []
        #print view_num
        now = datetime.datetime.now()
        timestr = now.strftime('%Y-%m-%d %H:%M:%S')
        resp=self.sess.request('post', self.view_history,
                               {'FN': 'ListPageView_ViewCompanyList', 'pageNo': '1', 'pageSize': '10'}, {},
                               self.headers, self.cookie);
        html = resp.text
        pattern=re.compile(
            r'<div class=\\"cinfo_viewlist\\"><a href=\\"(.*?)\\" class=\\"link_viewcname ellipsis\\".*?>(.*?)</a>.*?<span>(\d{4}-\d{1,2}-\d{1,2}\s\d{1,2}:\d{1,2})</span>',
            re.S)
        match_result = re.findall(pattern, html)
        print match_result
        for item in match_result:
            company_url = item[0]
            company_name = item[1]
            view_datetime = item[2];
            company_list.append({
                'company_name': company_name,
                'company_url': company_url,
                'view_datetime': view_datetime
            })


        save_object={
            'name': 'jobs_cjol',
            'view_num': self.view_num,
            'company_list': company_list,
            'access_datetime': timestr
        }

        self.save_db(save_object)


    def refresh(self):
        self.login()
        print self.cookie
        refresh_pre_url = 'http://www.cjol.com/jobseekers/Service/JobseekerV7API.ashx?jsoncallback=jQuery17205124697185245999_1508975021706&Action=CheckLogin&_=1508975025363'
        refresh_url = 'http://www.cjol.com/JobSeekers/Service/JobseekerV7API.ashx?Action=RefreshResume&_=1508974548239'
        resp = self.sess.request('get', refresh_pre_url, {}, {},self.headers, self.cookie)
        resp = self.sess.request('get', refresh_url, {}, {}, self.headers, self.cookie)
        json_data = demjson.encode(resp.text)



    def save_db(self, data):
        conn=MongoClient('192.168.0.99', 27017)
        db=conn.mydb  # 连接mydb数据库，没有则自动创建
        my_set=db.jobs_cjol
        my_set.remove({})
        my_set.save(data)
        conn.close()

    def __del__(self):
        self.driver.close()
        self.driver.quit()


if __name__ == '__main__':
    cjol = jobs_cjol()
    cjol.job_seeker()
