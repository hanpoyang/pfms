from selenium import webdriver

driver=webdriver.PhantomJS()
driver.get("https://www.4008-517-517.cn")
print driver.error_handler
driver.save_screenshot('./test_snap.png')
driver.close()
driver.quit()