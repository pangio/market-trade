
# Market Trade - Realtime Charts

# Message Consumption
The endpoint handle the POST requests asynchronously via the java.util.concurrent.Callable class.

The TradeMessages are stored into MongoDB which is schema-less and easy to scale out.

In regards to performance, the endpoint was load tested with [Apache jMeter] (http://jmeter.apache.org/)
making 1000 concurrent requests on a local env.

# Message Processor
The TradeMessageController uses the StatisticService, which pull data from MongoDB and creates the statistics.

StatisticService sends data through websockets from the server to the client in realtime via 2 channels.

Channel '/init' broadcasts the initial data to render the Charts right after the connection of the client (browser) to the socket.

Channel 'topic/charts' broadcasts new Statistics right after the creation of every TradeMessage.

# Message Frontend
The front-end draws the Charts built with Google Visualization API to display data in realtime.


# Tech Stack
*  Java 1.8
*  Spring Boot /Spring MVC
*  Mongo (noSql db)
*  Gradle (build)
*  Websocket/Stomp (realtime messages)
*  Thymeleaf (template engine)
*  Google Visualization API (realtime charts)
*  TwitterBootstrap (css)
*  jUnit Mockito (testing)

# Prerequisites
* jdk 1.8
* mongo 3 - visit [mongodb.org](https://www.mongodb.org/)
* gradle 2.3 - visit [gradle.org](https://gradle.org/)

# Setup
* Clone the repo ```https://github.com/pangio/market-trade```
* Build ```./gradlew clean compile```
* Run  ```./gradlew bootRun```

# Endpoints / Http Methods


* Returns the list of all Trade Messages.

GET: ```http://localhost:8080/trades```

* Finds a Trade Message by id.

GET: ```http://localhost:8080/trades/{tradeMessageId}```

* Creates a new Trade Message.

POST: ```http://localhost:8080/trades```

JSON body example:
```
{
    "userId" : "134256",
    "currencyFrom" : "EUR",
    "currencyTo" : "GBP",
    "amountSell" : 1000,
    "amountBuy" : 4338,
    "rate" : 5.43,
    "timePlaced" : "14-JAN-15 10:27:44",
    "originatingCountry" : "FR"
}
```

# Tests
* Under the test source folder find the suite com.cf.markettrade.AllTests.java

# Statistics - Realtime Charts
Just browse [http://localhost:8080/statistics](http://localhost:8080/statistics)

# Screenshots

![Alt text](/screenshots/cf_currencies.png?raw=true "Currency Chart")
![Alt text](/screenshots/cf_buy_sell.png?raw=true "Buy/Sell Chart")
![Alt text](/screenshots/cf_error.png?raw=false "Error notification")


# NOTES
* Content Type should be set to ```application/json``` .
* Mongo sholud be running on ```localhost``` with the default values as ```port=27017``` and ```dbpath=/data/db```

