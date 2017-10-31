# -*- coding: utf-8 -*-
from selenium import webdriver
import platform
import time
import re, ConfigParser
import sys,logging
from selenium.webdriver.common.action_chains import ActionChains

logging.basicConfig(level=logging.DEBUG, format='%(asctime)s %(filename)s[line:%(lineno)d] %(levelname)s %(message)s',
datefmt='%a, %d %b %Y %H:%M:%S',filename='auto_order.log',filemode='w')
logging.debug('test log info........')


class order(object):

    def __init__(self):
        system_name=platform.system()
        if system_name == 'Windows':
            self.driver=webdriver.PhantomJS()
        else:
            print 'env os-linux'
            phantomjs_dir = '/home/upload/phantomjs/phantomjs-1.9.7-linux-x86_64/bin/phantomjs'
            self.driver=webdriver.PhantomJS(phantomjs_dir, service_args=['--ssl-protocol=any'])
        self._m_username = "hanpoyang@hotmail.com"
        self._m_password = "haton142401"
        self._ali_username = "*******"
        self._ali_password = "*******"
        self._m_mealtype = u"大"
        self._parse_config()

    def login(self):
        self.driver.get("https://www.4008-517-517.cn")
        time.sleep(3)
        user_name_box = self.driver.find_element_by_name("userName")
        user_name_box.send_keys(self._m_username)
        self.driver.find_element_by_name("password").send_keys(self._m_password)
        obj = self.driver.find_element_by_css_selector(".btn-submit")
        obj.click()
        print '正在登录中>>>'
        time.sleep(3)
        source = re.findall(u'韩涛', self.driver.page_source)
        if source and source[0] == u'韩涛':
            print '登录成功'
        else:
            print '登录失败,退出程序'
            sys.exit();


    def find_page(self):
        _meal_type_ = self._m_mealtype
        confirm_button=self.driver.find_element_by_id("countdownmenuswitchtimer-title")
        try:
            self.driver.find_element_by_link_text("再次下单".decode("utf-8")).click()
        except:
            print '再次下单'.decode('utf-8')
        time.sleep(2)
        self.driver.find_element_by_link_text("开始订餐".decode("utf-8")).click()
        time.sleep(2)
        try:
            self.driver.find_element_by_link_text("再次下单".decode("utf-8")).click()
        except:
            print '再次下单'.decode('utf-8')
        linktxt=self.driver.find_element_by_css_selector(".primary-menu-item-target").text
        if linktxt == '早餐菜单'.decode("utf-8"):
            self.driver.get("https://www.4008-517-517.cn/cn/menu.html?daypartId=1")
        self.driver.find_element_by_xpath("//ul[@class='secondary-menu']/li[2]/a").click()
        time.sleep(2)

    def make_order(self):
        _meal_type_ = self._m_mealtype
        cards=self.driver.find_elements_by_xpath("//div[@class='product-card product-card--standard']/div")
        for card in cards:
            if card.find_element_by_tag_name("h5").text == "巨无霸".decode("utf-8"):
                print(card.find_element_by_tag_name("h5").text)
                burg_link=card.find_element_by_tag_name("a")
                burg_link.click()
        time.sleep(5)
        rows=self.driver.find_elements_by_xpath("//tbody/tr/td/h4")
        _index=0
        _pos=0
        for row in rows:
            if row.text.find(_meal_type_.decode("utf-8") + '套餐'.decode("utf-8")) > -1:
                _pos=_index
                break
            _index+=1
        buttons=self.driver.find_elements_by_xpath("//button[@class='btn btn-increase action-increase btn-black']")
        buttons[_pos].click()
        time.sleep(2)

        itemlists=self.driver.find_elements_by_xpath("//a[@data-toggle='modal' and @class='action-change']")

        for i in (0, len(itemlists)):
            if itemlists[i].text == '修改'.decode('utf-8'):
                itemlists[i].click()
                break;
        time.sleep(2)
        orderitems=self.driver.find_elements_by_xpath('//label[@class="radio-label"]')
        # 大可乐
        print(len(orderitems))
        for item in orderitems:
            if item.text == "可口可乐（#{meal_type}）".decode('utf-8').replace('#{meal_type}', _meal_type_.decode('utf-8')):
                item.click()
        self.driver.find_element_by_xpath("//div[@class='modal-footer text-center']/button").click()

        js_scripts = 'var buttons = document.getElementsByTagName("button"); var result=false; for(var i = 0; i < buttons.length; i++){var button = buttons[i];if(button.innerHTML.indexOf("添加产品到购物车") > -1){result=true;button.click();return result;}}'
        js_result = self.driver.execute_script(js_scripts)
        print '保存到购物车：'+ str(js_result)
        time.sleep(3);

    def confirm_and_pay(self):
        self.driver.find_element_by_link_text("立即结账".decode("utf-8")).click();
        time.sleep(2)
        self.driver.find_element_by_xpath("//button[@data-associated-form]").click()
        time.sleep(2)
        self.save_screen();
        self.driver.find_element_by_xpath("//label[@for='form_order_payment_type_alipay']").click()
        time.sleep(2)
        self.driver.find_element_by_id('confirmBtn').click()
        time.sleep(10)
        self.save_screen()

    def login_alipay(self):
        _alipay_username_ = self._ali_username
        _alipay_password_ = self._ali_password
        print u'准备登陆支付宝>>>'
        self.driver.find_element_by_link_text("< 登录账户付款".decode("utf-8")).click();
        time.sleep(2)
        self.driver.find_element_by_id("J_tLoginId").send_keys(_alipay_username_)
        self.driver.find_element_by_id("payPasswd_rsainput").send_keys(_alipay_password_)
        self.save_screen('./alipay_screen_01.jpg')
        time.sleep(2)
        self.driver.find_element_by_id("J_newBtn").click()
        time.sleep(10)
        self.save_screen('./alipay_screen_02.jpg')

    def pay(self):
        _alipay_password_ = self._ali_password
        action=ActionChains(self.driver)
        self.driver.find_element_by_xpath("//div[@class='sixDigitPassword']").click()
        action.key_up(_alipay_password_).perform()
        time.sleep(1)
        #self.driver.find_element_by_id('J_authSubmit').click()
        print u'已支付成功！'
        self.save_screen('./alipay_screen_03.jpg')


    def save_screen(self,file_name = './default_screen.jpg'):
        self.driver.maximize_window()
        self.driver.save_screenshot(file_name)

    #解析参数
    def _parse_config(self):
        cp=ConfigParser.SafeConfigParser()
        cp.read('sys.conf')
        self._m_username = cp.get("mcdonald", "username")
        self._m_password = cp.get("mcdonald", "password")
        self._m_mealtype = cp.get("mcdonald", "mealtype")

        self._ali_username = cp.get("alipay", "username")
        self._ali_password = cp.get("alipay", "password")

    def __del__(self):
        self.driver.close()
        self.driver.quit()



if __name__ == "__main__":
    order = order()
    order.login();
    order.find_page();
    order.make_order()
    order.confirm_and_pay();
    order.login_alipay();
    order.pay()
