#CyxteraCalculatorAPI

** ExecuteOperation **
----
executes a mathematical operation. there needs to be at least two operators in the stack in order for this requesto to function.
After execution, the request's result is added back to the operator stack, an the used operators are removed.

* **URL**

CyxteraCalculator/webresources/web.operations/executeOperation
* **Method:**
  
POST  

* **Data Params**

`<operations>
    <sessionId>11</sessionId>
    <operation>suma</operation>
</operations>`

Operation must be either 'suma', 'resta', 'multiplicación', 'división' or 'potenciación'.

* **Success Response:**
  
  * **Code:** 200 <br />
  **Content:** 
`<operators>
    <id>6</id>
    <sessionId>11</sessionId>
    <value>6.0</value>
</operators>`

* **Error Response:**

  If Operation is not 'suma', 'resta', 'multiplicación', 'división' or 'potenciación'.
  OR
  there arent at least 2 itens on the operator stack

  * **Code:** 403