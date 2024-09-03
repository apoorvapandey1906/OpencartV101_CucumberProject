Feature: Account registration

 @Regression
Scenario: Successful account registration
Given the User navigates to Account registration page
When User enters the details in below fields
    |firstName	| John     |
    |lastName   | Kennedy  |
    |telephone  | 12345678 |
    |password   | test@123 |
And the User selects the privacy policy
And the User clicks on Continue button
Then User should get created successfully