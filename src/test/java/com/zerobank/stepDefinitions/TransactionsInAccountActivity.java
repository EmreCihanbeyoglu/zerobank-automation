package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountActivityPage;
import com.zerobank.pages.AccountSummaryPage;
import com.zerobank.pages.FindTransactionsPage;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.text.ParseException;
import java.util.*;

public class TransactionsInAccountActivity {

    @Given("the user accesses the Find Transactions tab")
    public void the_user_accesses_the_Find_Transactions_tab() {

        new LoginPage().login();
        new AccountSummaryPage().navigateBetweenPages("Account Activity");
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        accountActivityPage.navigateToTab("Find Transactions");

    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        findTransactionsPage.typeIntoFromDateTextBox(fromDate);
        findTransactionsPage.typeIntoToDateTextBox(toDate);
    }

    @When("clicks search")
    public void clicks_search() {
        new FindTransactionsPage().clickFindButton();
    }

    @Then("results table should only show transactions dates between {string} to  {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDateAsString, String toDateAsString) throws ParseException {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> dateListAsString = findTransactionsPage.getTransactionsDates();
        Assert.assertTrue(findTransactionsPage.isInDateRange(fromDateAsString, toDateAsString, dateListAsString));
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> dateListAsString = findTransactionsPage.getTransactionsDates();
        Assert.assertTrue(findTransactionsPage.isFromMostRecentDate(dateListAsString));
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String specifiedDate) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> dateListAsString = findTransactionsPage.getTransactionsDates();
        System.out.println(dateListAsString);
        Assert.assertFalse(dateListAsString.contains(specifiedDate));
    }


    @When("the user enters description {string}")
    public void the_user_enters_description(String description) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        findTransactionsPage.typeIntoDescriptionTextBox(description);
    }

    @Then("results table should only show descriptions containing {string}")
    public void results_table_should_only_show_descriptions_containing(String specifiedText) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> descriptionList = findTransactionsPage.getTransactionsDescriptions();
        boolean result = findTransactionsPage.doesDescriptionContainSpecifiedText(descriptionList, specifiedText);
        Assert.assertTrue(result);
    }

    @Then("results table should not show descriptions containing {string}")
    public void results_table_should_not_show_descriptions_containing(String specifiedText) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> descriptionList = findTransactionsPage.getTransactionsDescriptions();
        boolean result = findTransactionsPage.doesDescriptionNOTContainSpecifiedText(descriptionList, specifiedText);
        Assert.assertTrue(result);
    }


    @Then("results table should show at least one result under Deposit")
    public void results_table_should_show_at_least_one_result_under_Deposit() {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> depositList = findTransactionsPage.getTransactionsDeposits();
        Assert.assertTrue(findTransactionsPage.getNumberOfDepositTableRow(depositList) > 0);
    }

    @Then("results table should show at least one result under Withdrawal")
    public void results_table_should_show_at_least_one_result_under_Withdrawal() {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> withdrawalList = findTransactionsPage.getTransactionsWithDrawals();
        Assert.assertTrue(findTransactionsPage.getNumberOfWithDrawalTableRow(withdrawalList) > 0);
    }

    @When("user selects type {string}")
    public void user_selects_type(String option) {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        findTransactionsPage.selectOptionFromDropDown(option);
        findTransactionsPage.clickFindButton();
    }

    @Then("results table should show no result under Withdrawal")
    public void results_table_should_show_no_result_under_Withdrawal() {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> withdrawalList = findTransactionsPage.getTransactionsWithDrawals();
        System.out.println(withdrawalList.size());
        Assert.assertEquals(0, findTransactionsPage.getNumberOfWithDrawalTableRow(withdrawalList));
    }

    @Then("results table should show no result under Deposit")
    public void results_table_should_show_no_result_under_Deposit() {
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();
        List<String> depositList = findTransactionsPage.getTransactionsDeposits();
        Assert.assertEquals(0, findTransactionsPage.getNumberOfDepositTableRow(depositList));
    }


}
