package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.BasePage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.en.Then;
import io.cucumber.java.en_old.Ac;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import java.util.List;

public class AccountSummaryContentDefinitions {


    @Then("the title should be {string}")
    public void the_title_should_be(String expectedTitle) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        String actualTitle = BasePage.getTitle();
        Assert.assertEquals(expectedTitle, actualTitle);
    }

    @Then("the account types should be such as")
    public void the_account_types_should_be_such_as(List<String> expectedAccountTypes) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualAccountTypeList = accountSummaryPage.getAccountTypes();
        Assert.assertEquals(expectedAccountTypes, actualAccountTypeList);
    }

    @Then("the column types under {string} should be such as")
    public void the_column_types_under_should_be_such_as(String accountName, List<String> expectedColumnNames) {
        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        List<String> actualColumnNames =  accountSummaryPage.getColumnNames(accountName);
        Assert.assertEquals(expectedColumnNames,actualColumnNames);
    }



}
