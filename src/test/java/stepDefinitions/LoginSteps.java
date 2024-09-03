package stepDefinitions;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import utilities.DataReader;

public class LoginSteps {
	
	HomePage hp = new HomePage(BaseClass.getDriver());;
	LoginPage lp = new LoginPage(BaseClass.getDriver());
	MyAccountPage ap = new MyAccountPage(BaseClass.getDriver());
	
	List<HashMap<String,String>> dataMap ;

	@Given("the User navigates to login page")
	public void the_user_navigates_to_login_page() {
		BaseClass.getLogger().info("User is navigating to login page");
		//taking the driver instance form the static getDriver() of BaseClass, hence no need to make an object of BaseClass or extend BaseClass to use it 

		
		hp.clickMyAccount();
		hp.clickLogin();
	}

	@When("User enters email as {string} and password as {string}")
	public void user_enters_email_as_and_password_as(String email, String password) {
		BaseClass.getLogger().info("User is entering username & Password");
        
        lp.enterEmail(email);
        lp.enterPassword(password);
		
	}

	@When("the User clicks on Login button")
	public void the_user_clicks_on_login_button() {
		lp.clickLogin();
		BaseClass.getLogger().info("User is clicking on Login button");
	}

	@Then("the User should be redirected to the MyAccount Page.")
	public void the_user_should_be_redirected_to_the_my_account_page() {
		boolean targetPage = ap.isMyAccountPageExists();
	    Assert.assertEquals(targetPage, true);
	}
	
	@Then("the User clicks on the logout")
	public void the_user_clicks_on_the_logout() {
	   ap.clickLogout();
	   BaseClass.getLogger().info("User logs out from the application.");
	}

	// -------Data driven testing method
	@Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
	public void the_user_should_be_redirected_to_the_my_account_page_by_passing_email_and_password_with_excel_row(String rows) {
	   try {
		   dataMap = DataReader.data(System.getProperty("user.dir")+"\\testData\\opencart_loginTestData.xlsx", "sheet1");
	   }catch (IOException e) {
		e.printStackTrace();
	   }
	   
	   //since in test , we have started giving rows from 1 and in a hashmap ,index starts from 0.
	   int index = Integer.parseInt(rows)-1;
	   String email = dataMap.get(index).get("username");
	   String password = dataMap.get(index).get("password");
	   String expectedResult = dataMap.get(index).get("res");
	   
	   lp.enterEmail(email);
       lp.enterPassword(password);
       lp.clickLogin();
       BaseClass.getLogger().info("User has entered details and clicked on loginbutton");

       try {
       boolean targetPage = ap.isMyAccountPageExists();
       BaseClass.getLogger().info("target page=>"+targetPage);
       if(expectedResult.equalsIgnoreCase("valid")) {
    	   if(targetPage==true) {
    		   ap.clickLogout();
    		   Assert.assertTrue(true);
    	   }else {
    		   Assert.assertTrue(false);
    	   }
       } else if (expectedResult.equalsIgnoreCase("invalid")) {
    	   if(targetPage==true) {
    		   ap.clickLogout();
    		   Assert.assertTrue(false);
    	   }else {
    		   Assert.assertTrue(true);
    	   }
       }
       }catch (Exception e) {
    	   e.printStackTrace();
       }
	   
	}


}
