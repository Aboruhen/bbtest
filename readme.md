# Getting Started

to run application
`mvn clean install jetty:run`

Swagger endpoint
`http://localhost:8080/swagger-ui.html`

##Api Docs
`api-docs.json` is an Open API.

### End points

to get transactions

`curl -X GET "http://localhost:8080/banks/rbs/accounts/savings-kids-john/transactions" -H "accept: */*"`

to get filtered transactions by transaction type

`curl -X GET "http://localhost:8080/banks/rbs/accounts/savings-kids-john/transactions?transactionType=SANDBOX_TAN" -H "accept: */*"`

get count transactions

`curl -X GET "http://localhost:8080/banks/rbs/accounts/savings-kids-john/transactions/count" -H "accept: */*"`

get count filtered transactions

`curl -X GET "http://localhost:8080/banks/rbs/accounts/savings-kids-john/transactions/count?transactionType=SANDBOX_TAN" -H "accept: */*"`


## Logging
for logging logback framework is used
configure file: spring-logback.xml

