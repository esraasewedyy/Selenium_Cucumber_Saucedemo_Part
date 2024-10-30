package com.saucedemo.steps;

import com.saucedemo.pages.*;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import com.saucedemo.utils.ConfigReader;
import com.saucedemo.utils.ContextStepDefination;
import com.saucedemo.utils.DriverFactory;

public class LoginSteps {


    ContextStepDefination ContextStepDef;


    public LoginSteps(ContextStepDefination ContextStepDef) {
        this.ContextStepDef=ContextStepDef;
    }

        @Before
    public void setup() {
            ContextStepDef.driver = DriverFactory.getDriver();
            ContextStepDef.P01_LoginPage = new P01_LoginPage(ContextStepDef.driver);
            ContextStepDef.P02_ProductsPage = new P02_ProductsPage(ContextStepDef.driver);
            ContextStepDef.P03_CartPage = new P03_CartPage(ContextStepDef.driver);
            ContextStepDef.P04_CheckoutPage = new P04_CheckoutPage(ContextStepDef.driver);
            ContextStepDef.P05_OverviewPage = new P05_OverviewPage(ContextStepDef.driver);
            ContextStepDef.P06_CompletePage = new P06_CompletePage(ContextStepDef.driver);
    }

    @After
    public void tearDown() {
        DriverFactory.quitDriver();
    }


    //navigate to login page
    @Given("I am on the SauceDemo login page")
    public void iAmOnTheSauceDemoLoginPage() {
        ContextStepDef.driver.get(ConfigReader.getProperty("url"));
    }

    @When("I enter {string} username {string} and {string} password {string}")
    public void iEnterInvalidUsernameAndPassword(String username_scenario, String username,String password_scenario, String password) {
        ContextStepDef.P01_LoginPage.enterUsername(username);
        ContextStepDef. P01_LoginPage.enterPassword(password);
    }
//==========================================================
    @When("I enter valid username {string} and valid password {string}")
    public void iEnterValidUsernameAndValidPassword(String username, String password) {
        ContextStepDef.P01_LoginPage.enterUsername(username);
        ContextStepDef.P01_LoginPage.enterPassword(password);
    }

//    @When("I enter username {string} and password {string}")
//    public void iEnterUsernameAndPassword(String username, String password) {
//        P01_LoginPage.enterUsername(username);
//        P01_LoginPage.enterPassword(password);
//    }
    //==========================================================



    @When("I click the login button")
    public void iClickTheLoginButton() {
        ContextStepDef.P01_LoginPage.clickLoginButton();
    }

    @Then("I should see an error message with error message is {string}")
    public void iShouldSeeAnErrorMessage(String expectedErrorMessage) {
        Assert.assertEquals(ContextStepDef.P01_LoginPage.getErrorMessage(),expectedErrorMessage, "Error message should be displayed");
    }



    @Then("I should be logged in successfully and navigated to the products page")
    public void iShouldBeLoggedInSuccessfullyAndNavigatedToTheProductsPage() {
        Assert.assertTrue(ContextStepDef.P02_ProductsPage.isOnProductsPage(), "Should be on products page");
    }


}