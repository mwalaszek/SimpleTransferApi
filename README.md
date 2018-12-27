# SimpleTransferApi
Simple bank transfer REST application.

## Technologies/libs: 

* Java 8
* embedded Jetty server
* Jersey RESTful Web Services
* JUnit 
* Mockito

## Usage
Application has following REST endpoints: 

* ```GET http://localhost:8080/api/accounts```

  > List all accounts


* ```GET http://localhost:8080/api/accounts/history/PL10105000997603123456789123```

  > Bank transfer history for account with IBAN: PL10105000997603123456789123
      
* ```POST http://localhost:8080/api/transfers```
  ```json 
  {
      "sourceAccountIban": "DE75512108001245126199", 
      "destinationAccountIban": "PL10105000997603123456789123",
      "value": 50,
       "sourceSystemId": "tetst" 
  }
  ``` 
  
  > Create and execute new bank transfer
