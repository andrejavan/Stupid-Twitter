# -*- coding: utf-8 -*-
"""
Created on Mon Apr 26 01:21:08 2021

@author: afjav
"""
from selenium import webdriver
from selenium.webdriver.common.by import By
import time
LIMIT = 1500
handle = "benshapiro"
driver = webdriver.Chrome("C:/Users/afjav/OneDrive/Desktop/chromedriver.exe")
driver.get("https://twitter.com/" + handle)

f = open("C:/Users/afjav/eclipse-workspace/chatbot/src/chatbot/"+handle+".txt","w")

time.sleep(2)
twitset = set()
last_position = driver.execute_script('return window.pageYOffset;')
numTweets=0

while numTweets<LIMIT:
    articles = driver.find_elements(By.TAG_NAME, 'article')

    for article in articles[-15:]:
        try:
            t = article.find_element(By.XPATH, ".//div[1]/div[1]/div[1]/div[2]/div[2]/div[2]/div[1]/div[1]")
            if t not in twitset:
                twitset.add(t.text)
                f.write(t.text+" END ")
                numTweets+=15
        except:
            continue
    driver.execute_script('window.scrollTo(0, document.body.scrollHeight);')
    time.sleep(.1)
    curr_position = driver.execute_script('return window.pageYOffset;')
    if curr_position - last_position < 2:
        break
    else:
        last_position = curr_position
    

f.close()
#for t in twits:
#    print(t)