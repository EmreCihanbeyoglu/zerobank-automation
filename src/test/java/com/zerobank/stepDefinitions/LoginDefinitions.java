package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Map;

public class LoginDefinitions {
    @Given("navigate login page")
    public void navigate_login_page() {
        LoginPage loginPage = new LoginPage();
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        wait.until(ExpectedConditions.visibilityOf(loginPage.passwordTextBox));
    }

    @When("enter valid credentials")
    public void enter_valid_credentials() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @When("enter credentials and click button")
    public void enter_credentials_and_click_button(Map<String, String> creentials) {
        LoginPage loginPage = new LoginPage();
        loginPage.typeUserName(creentials.get("username"));
        loginPage.typePassword(creentials.get("password"));
        loginPage.clickLoginButton();
    }


    @Then("Login error message {string} should be displayed")
    public void loginErrorMessageShouldBeDisplayed(String expectedErrorMessage) {
        LoginPage loginPage = new LoginPage();
        String actualErrorMessage = loginPage.getMessageContent();
        System.out.println("actualErrorMessage = " + actualErrorMessage);
        Assert.assertEquals(expectedErrorMessage, actualErrorMessage);
    }
}
