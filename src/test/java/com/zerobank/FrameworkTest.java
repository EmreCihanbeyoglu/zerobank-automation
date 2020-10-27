package com.zerobank;

import com.zerobank.pages.*;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.bs.A;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.util.*;

public class FrameworkTest {

    public static void main(String[] args) throws ParseException {
        WebDriver driver = Driver.get();

        driver.get(ConfigurationReader.getValue("url"));
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage();
        loginPage.login();
        BrowserUtils.waitFor(3);

        AccountSummary accountSummary = new AccountSummary();
        //System.out.println(accountSummary.getUserName());
        //accountSummary.clickOnLink("Savings");

/*        //System.out.println("accountSummary.getActivePageTab() = " + accountSummary.getActivePageTab());
        accountSummary.navigateBetweenPages("Account Activity");
        //System.out.println(Driver.get().getCurrentUrl());
        AccountActivity accountActivity = new AccountActivity();
        System.out.println("accountActivity.getActivePageTab() = " + BasePage.getActivePageTab());
        System.out.println("accountActivity.getSelectedOption() = " + accountActivity.getSelectedOption());*/

        accountSummary.navigateBetweenPages("Account Activity");
        AccountActivity accountActivity = new AccountActivity();
        //accountActivity.navigateBetweenPages("Find Transactions");
        accountActivity.navigateToTab("Find Transactions");
        FindTransactions findTransactions = new FindTransactions();



        findTransactions.fillOutDatesAndClick("2012-09-01", "2012-09-06");
        //System.out.println(findTransactions.fromDateTextBox.getAttribute("innerHTML"));
        BrowserUtils.waitForVisibility(findTransactions.transactionsTable, 10);
        System.out.println(BrowserUtils.getElementsText(findTransactions.getTransactionsDataFromTable()));


        List<String> dateListAsString = findTransactions.getTransactionsDates();
        System.out.println(findTransactions.isInDateRange("2012-09-01", "2012-09-06", dateListAsString));
/*
        List<String> list = new ArrayList<>(Arrays.asList("2020-01-15", "2019-02-09", "2021-04-01")) ;
        System.out.println(list);
        System.out.println("*************************");
        System.out.println(findTransactions.sortDatesAsString(list));*/
        BasePage.logOut();

/*        FindTransactions findTransactions = new FindTransactions();*/

/*        Date fromDate = findTransactions.convertDateFromString("2020-10-01");
        Date toDate = findTransactions.convertDateFromString("2022-11-15");*/
/*        List<String> dateListAsString = new ArrayList<>(Arrays.asList("2020-11-04", "2020-12-19", "2021-09-04"));

        List<Date> dateList = findTransactions.convertDatesInListFromString(dateListAsString);
        System.out.println("**********************************************************''''");
        System.out.println(findTransactions.isInDateRange("2020-10-01", "2022-11-15", dateListAsString));*/

        Driver.closeDriver();
    }

}
