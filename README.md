# fetchrewards-takehome-Shardul
In this coding assignment we are able to reward point and spend points for rewards.

# Assumptions
- We are assuming that there will be No negative transaction of a payer before adding it to the transaction history
- We are not allowing any user to spend in negative points or if the request for the spend points is more than the actual total Points

# Fetch Rewards Coding Exercise - Backend Software Engineering

The coding exercise includes 
- The ability for Fetch Rewards participating companies to reward points to consumers.
- The ability for consumers to spend points awarded to them.

## Prerequisites
- Java 1.8 (https://www.oracle.com/java/technologies/javase/javase-jdk8-downloads.html)
- Maven (https://maven.apache.org/download.cgi)

## Running the code
- Go to the home directory of the project and run 

```
mvn spring-boot:run
```

## REST API

REWARD POINTS

`POST /saveTransaction`

```
curl -d  '{ "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }' -H 'Content-Type: application/json' localhost:8080/saveTransaction
```

SPEND POINTS

`PUT /spendPoints`

```
curl -d  '{ "points": 5000 }' -H 'Content-Type: application/json' -X PUT localhost:8080/spendPoints
```

GET ALL TRANSACTIONS

`GET /getAllTransactions`

```
curl -v  localhost:8080/getAllTransactions
```
## Reference on sending REST API calls
- https://www.baeldung.com/curl-rest

## Project Future scope
- We need to improve the responses as currently all of the responses are in string format
- We can perform some optimization on on fetching and storing of the data from the database
- We can support this system for multiple user as a additional feature
- Need to add test cases for the project
