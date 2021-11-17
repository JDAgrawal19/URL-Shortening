URL Hashing Service
---
Here Objective is to create short URLs for Long URL with UTM tracking parameters.
like utm_source, utm_target etc.
---
System Requirements
1. should have a click tracking system with dashboard where one can check
how many times a URL is clicked, segregation with different UTM parameters.
2. Make Shortened URL privacy aware.

---

Architecture
---
1. DataBase -- first database should be selected,so our system may have million of 
users so the amount of data will be high. So we can choose any NoSQL
   database here. Because if we use SQL then we have to do sharding. 
   And Also we will have millions of reads and writes, so it is easier to scale NOSQL.
2. Queue System with ElasticSearch -- whenever a URL is opened,it can be registered
as an event, So kafka consumer can consume it and do the processing and send it to Elastic Search.
   and we can use Kibana for analytics and Dashboard Purposes. Kafka can be also 
   scaled very well, so if million of users are hitting the URL we can store them all in queue.
   this way data wont be lost.Because we can make system eventually consistent.
   As analytics is seen by us not by users.
3. 

-----------------------------

Technology Used
1. Spring Boot 
2. Kafka
3. Elastic Search
4. MongoDb

   