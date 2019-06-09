**Retail Service**
This project is a spring boot application which awards reward points to customer based on the amount spent 
in the transaction records. This project has one service endpoint which takes in customer transaction 
records and gives out Reward response for each customer.

**Business Rules for Reward earnings:**
A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent
over $50 in each transaction
(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).

Given a record of every transaction during a three month period, calculate the reward points earned for each 
customer per month and total.

**How to run the app:**
Assuming Java, Maven, Curl or Postman installed on your machine. Clone the project on your preferred IDE.
run -> clean install spring-boot:run
OR
Run the application directly after running mvn install

**Application Context path:** 
http://localhost:8080/retail

**Rewards Endpoint:** http://localhost:8080/retail/rewards
**Endpoint Type:** POST
**Endpoint Content type:** application/json

**Sample Request Body:**
   `{  
    "retailCustomers":[  
       {  
          "customerId":1234567,
          "retailTransaction":[  
            {  
                "amountSpent":120.00,
                "period":"2019-08-17T12:34.123"
             },
                        {  
                "amountSpent":11.00,
                "period":"2019-06-27T16:34.123"
             },
                        {  
                "amountSpent":60.00,
                "period":"2019-08-07T08:34.123"
             }
          ]
       },
             {  
          "customerId":9876544,
          "retailTransaction":[  
            {  
                "amountSpent":20.00,
                "period":"2019-01-07T12:34.123"
             },
                        {  
                "amountSpent":80.00,
                "period":"2019-01-07T16:34.123"
             },
                        {  
                "amountSpent":140.00,
                "period":"2019-04-21T08:34.123"
             }
          ]
       }
    ]
   }`
   
**Sample Response:**
`    {
       "rewardResponses": [
           {
               "customerId": 1234567,
               "pointsEarned": [
                   {
                       "transactionMonth": "Jun",
                       "transactionPointsEarned": 0
                   },
                   {
                       "transactionMonth": "Aug",
                       "transactionPointsEarned": 100
                   }
               ],
               "totalPointsEarned": 100
           },
           {
               "customerId": 9876544,
               "pointsEarned": [
                   {
                       "transactionMonth": "Jan",
                       "transactionPointsEarned": 30
                   },
                   {
                       "transactionMonth": "Apr",
                       "transactionPointsEarned": 130
                   }
               ],
               "totalPointsEarned": 160
           }
       ]
    }
`

**Notes:**

**Authors:**
Surya