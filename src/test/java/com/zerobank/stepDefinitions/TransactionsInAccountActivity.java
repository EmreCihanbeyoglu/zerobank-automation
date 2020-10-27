package com.zerobank.stepDefinitions;

import com.zerobank.pages.AccountActivity;
import com.zerobank.pages.AccountSummary;
import com.zerobank.pages.FindTransactions;
import com.zerobank.pages.LoginPage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
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
        new AccountSummary().navigateBetweenPages("Account Activity");
        AccountActivity accountActivity = new AccountActivity();
        accountActivity.navigateToTab("Find Transactions");

    }

    @When("the user enters date range from {string} to {string}")
    public void the_user_enters_date_range_from_to(String fromDate, String toDate) {
        FindTransactions findTransactions = new FindTransactions();
        findTransactions.typeIntoFromDateTextBox(fromDate);
        findTransactions.typeIntoToDateTextBox(toDate);
    }

    @When("clicks search")
    public void clicks_search() {
        new FindTransactions().findButton.click();
        BrowserUtils.waitFor(1);
    }

    @Then("results table should only show transactions dates between {string} to  {string}")
    public void results_table_should_only_show_transactions_dates_between_to(String fromDateAsString, String toDateAsString) throws ParseException {
        FindTransactions findTransactions = new FindTransactions();
        List<String> dateListAsString = findTransactions.getTransactionsDates();
/*        List<String> dateListAsString = new ArrayList<>(Arrays.asList("2020-11-04", "2020-12-19", "2021-09-04"));
        List<Date> dateList = findTransactions.convertDatesInListFromString(dateListAsString);
        Assert.assertTrue(findTransactions.isInDateRange("2020-10-01", "2022-11-15", dateListAsString));*/
        Assert.assertTrue(findTransactions.isInDateRange(fromDateAsString, toDateAsString, dateListAsString));
    }

    @Then("the results should be sorted by most recent date")
    public void the_results_should_be_sorted_by_most_recent_date() {
        FindTransactions findTransactions = new FindTransactions();
        List<String> dateListAsString = findTransactions.getTransactionsDates();
        Assert.assertTrue(findTransactions.isFromMostRecentDate(dateListAsString));
    }

    @Then("the results table should only not contain transactions dated {string}")
    public void the_results_table_should_only_not_contain_transactions_dated(String specifiedDate) {
        FindTransactions findTransactions = new FindTransactions();
        List<String> dateListAsString = findTransactions.getTransactionsDates();
        System.out.println(dateListAsString);
        Assert.assertFalse(dateListAsString.contains(specifiedDate));
    }

}
