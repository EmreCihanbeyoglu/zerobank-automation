package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.AddNewPayeePage;
import com.zerobank.pages.LoginPage;
import com.zerobank.pages.PayBillsPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Map;

public class AddNewPayeeDefinitions {

    @Given("Navigate {string} tab")
    public void navigate_tab(String tabName) {
        new LoginPage().login();
        new AccountSummaryPage().navigateBetweenPages("Pay Bills");
        PayBillsPage payBillsPage = new PayBillsPage();
        payBillsPage.navigateBetweenPages(tabName);
        WebDriverWait wait = new WebDriverWait(Driver.get(),10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//label[@class='control-label'][@for='np_new_payee_name']")));
    }

    @Given("creates new payee using following information")
    public void creates_new_payee_using_following_information(Map<String, String> payeeInformation) {
        AddNewPayeePage addNewPayeePage = new AddNewPayeePage();
        addNewPayeePage.payeeNameTextBox.sendKeys(payeeInformation.get("Payee Name"));
        addNewPayeePage.payeeAddressTextBox.sendKeys(payeeInformation.get("Payee Address"));
        addNewPayeePage.accountTextBox.sendKeys((payeeInformation.get("Account")));
        addNewPayeePage.payeeDetailsTextBox.sendKeys((payeeInformation.get("Payee details")));
        addNewPayeePage.addButton.click();
    }

    @Then("message {string} should be displayed")
    public void message_should_be_displayed(String expectedContent) {
        PayBillsPage payBillsPage = new PayBillsPage();
        String actualContent = payBillsPage.getMessageContent();
        Assert.assertEquals(expectedContent, actualContent);

    }

}
