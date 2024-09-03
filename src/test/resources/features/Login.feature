Feature: Login with correct credentials

@Sanity @Regression
Scenario: Login with correct credentials
Given the User navigates to login page
When User enters email as "testtest@gh.com" and password as "testasd"
And the User clicks on Login button
Then the User should be redirected to the MyAccount Page.
And the User clicks on the logout 

@Sanity
Scenario Outline: Login Data driven
Given the User navigates to login page
When User enters email as "<email>" and password as "<password>"
And the User clicks on Login button
Then the User should be redirected to the MyAccount Page.
Examples:
|email           | password |
|testtest@gh.com |	testasd |
|test2@gh.com	   | abctest  |
|abc123@gmail.com|	test@123|
|test3@gma.com	 |abctest2  |

   
   @Sanity
  Scenario Outline: Login Data Driven Excel
    Given the User navigates to login page
    Then the user should be redirected to the MyAccount Page by passing email and password with excel row "<row_index>"
    Examples: 
      | row_index |
      |         1 |
      |         2 |
      |         3 |
      |         4 |
  

