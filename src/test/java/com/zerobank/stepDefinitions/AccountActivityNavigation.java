package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.BasePage;
import com.zerobank.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class AccountActivityNavigation{

    @Given("the user is logged in")
    public void the_user_is_logged_in() {
        new LoginPage().login();
    }

    @When("the user clicks on {string} link on the Account Summary page")
    public void the_user_clicks_on_link_on_the_Account_Summary_page(String linkName) {
        new AccountSummaryPage().clickOnLink(linkName);
    }


    @Then("the {string} page should be displayed")
    public void the_page_should_be_displayed(String expectedActivePageTab) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        String actualActivePageTab = BasePage.getActivePageTab();
        Assert.assertEquals(expectedActivePageTab, actualActivePageTab);
    }


    @Then("Account drop down should have {string} selected")
    public void account_drop_down_should_have_selected(String expectedDropDownSelectedOption) {
        String actualSelectedOption = new AccountActivityPage().getSelectedOption();
        Assert.assertEquals(expectedDropDownSelectedOption,actualSelectedOption);
    }

}
