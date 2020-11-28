# bank-account-kata


#### Create client
POST : "api/clients" 

{
   "firstname" : "" ,
   "lastname" : "" ,
}

#### Make a deposit in my account
POST : "api/accounts" 

{
   "name" : "" ,
   "clientId" : "" ,
   "amount" : "" ,
}

#### Make a withdrawal from my account
POST : "api/operations" 

{
   "accountId" : "" ,
   "date" : "" ,
   "label" : "" ,
   "amount" : "" ,
}

#### See the history (operation, date, amount, balance)  of my operations  
GET : "api/operations?accountId=1&startOperationDate=2019-01-30T06:01:46&endOperationDate=2020-01-30T06:01:46" 


# Building

## What do you need
- Java 8
- Docker (and Compose)

## Launch tests
```bash
$ docker-compose -f docker/docker-compose.yml up -d
$ mvn clean install
```