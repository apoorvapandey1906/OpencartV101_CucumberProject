package stepDefinitions;

import java.util.Map;

import org.testng.Assert;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;

public class RegistrationSteps {
	
	HomePage hp = new HomePage(BaseClass.getDriver());;
	AccountRegistrationPage regPage = new AccountRegistrationPage(BaseClass.getDriver());;
	
	@Given("the User navigates to Account registration page")
	public void the_user_navigates_to_account_registration_page() {
	   BaseClass.getLogger().info("User is navigating to Account registration page");   
	   
	   hp.clickMyAccount();
	   hp.clickRegister();
	}
	
	@When("User enters the details in below fields")
	public void user_enters_the_details_in_below_fields(io.cucumber.datatable.DataTable dataTable) {
		Map<String,String> dataMap = dataTable.asMap(String.class,String.class);
	
		regPage.enterFirstName(dataMap.get("firstName"));
		regPage.enterLastName(dataMap.get("lastName"));
		regPage.enterEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		regPage.enterTelephone(dataMap.get("telephone"));
		regPage.enterPassword(dataMap.get("password"));
		regPage.enterConfirmPassword(dataMap.get("password"));
		
	}
	
	@When("the User selects the privacy policy")
	public void the_user_selects_the_privacy_policy() {
	   regPage.clickcheckBoxAgree();
	}
	
	@When("the User clicks on Continue button")
	public void the_user_clicks_on_continue_button() {
	   regPage.clickContinue();
	}
	
	@Then("User should get created successfully")
	public void user_should_get_created_successfully() {
	   String message = regPage.getConfirmationMessage();
	   Assert.assertEquals(message, "Your Account Has Been Created!");
	}



}
