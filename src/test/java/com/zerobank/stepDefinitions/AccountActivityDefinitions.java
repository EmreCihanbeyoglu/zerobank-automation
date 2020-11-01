package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class AccountActivityDefinitions {

    @Then("dropdown option should be {string}")
    public void dropdown_option_should_be(String expectedSelectedOption) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        String actualSelectedOption = accountActivityPage.getSelectedOption();
        Assert.assertEquals(expectedSelectedOption, actualSelectedOption);
    }

    @Then("dropdown options should be followings:")
    public void dropdown_options_should_be_followings(List<String> expectedOptionList) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> actualOptionList = accountActivityPage.getAllAccountTypeOptions();
        Assert.assertEquals(expectedOptionList, actualOptionList);

    }

    @Then("transaction table columns are followings:")
    public void transaction_table_columns_are_followings(List<String> expectedColumnNameList) {
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        List<String> actualColumnNameList = accountActivityPage.getColumnNames();
        Assert.assertEquals(expectedColumnNameList, actualColumnNameList);
    }


    @When("navigate to {string} page")
    public void navigateToPage(String pageName) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        accountSummaryPage.navigateBetweenPages(pageName);
    }
}
