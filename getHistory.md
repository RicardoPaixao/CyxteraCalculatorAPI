#CyxteraCalculatorAPI

** AddOperator **
----
retrieves al the requests made on a diven session

* **URL**

/CyxteraCalculator/webresources/web.history/{sessionId}

*  **URL Params**

	sessionId is the id of the session whose history is beeing retrieved.

   **Required:**
 
   `sessionId=[integer]`

* **Method:**
  
GET  

* **Success Response:**
  
  * **Code:** 200 <br />
  **Content:** 
`<histories>
    <history>
        <createdAt>2018-10-12T06:13:41-05:00</createdAt>
        <entry>Transaction=createSession Value=11 SessionID=11</entry>
        <id>4</id>
        <sessionId>11</sessionId>
    </history>
    <history>
        <createdAt>2018-10-12T06:13:57-05:00</createdAt>
        <entry>Transaction=createOperator Value=2.0 SessionID=11</entry>
        <id>5</id>
        <sessionId>11</sessionId>
    </history>
    <history>
        <createdAt>2018-10-12T06:13:57-05:00</createdAt>
        <entry>Transaction=createOperator Value=2.0 SessionID=11</entry>
        <id>6</id>
        <sessionId>11</sessionId>
    </history>
    <history>
        <createdAt>2018-10-12T06:13:58-05:00</createdAt>
        <entry>Transaction=createOperator Value=2.0 SessionID=11</entry>
        <id>7</id>
        <sessionId>11</sessionId>
    </history>
    <history>
        <createdAt>2018-10-12T06:14:07-05:00</createdAt>
        <entry>Transaction=executeOperation Value=potenciación SessionID=11</entry>
        <id>8</id>
        <sessionId>11</sessionId>
    </history>
    <history>
        <createdAt>2018-10-12T06:14:11-05:00</createdAt>
        <entry>Transaction=executeOperation Value=suma SessionID=11</entry>
        <id>9</id>
        <sessionId>11</sessionId>
    </history>
</histories>`