package com.zerobank;

import com.zerobank.pages.*;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebDriver;

import java.text.ParseException;
import java.util.List;

public class FrameworkTest {

    public static void main(String[] args) throws ParseException {
        WebDriver driver = Driver.get();

        driver.get(ConfigurationReader.getValue("url"));
        driver.manage().window().maximize();

        LoginPage loginPage = new LoginPage();
        loginPage.login();
        BrowserUtils.waitFor(3);

        AccountSummaryPage accountSummaryPage = new AccountSummaryPage();
        //System.out.println(accountSummary.getUserName());
        //accountSummary.clickOnLink("Savings");

/*        //System.out.println("accountSummary.getActivePageTab() = " + accountSummary.getActivePageTab());
        accountSummary.navigateBetweenPages("Account Activity");
        //System.out.println(Driver.get().getCurrentUrl());
        AccountActivity accountActivity = new AccountActivity();
        System.out.println("accountActivity.getActivePageTab() = " + BasePage.getActivePageTab());
        System.out.println("accountActivity.getSelectedOption() = " + accountActivity.getSelectedOption());*/

        accountSummaryPage.navigateBetweenPages("Account Activity");
        AccountActivityPage accountActivityPage = new AccountActivityPage();
        //accountActivity.navigateBetweenPages("Find Transactions");
        accountActivityPage.navigateToTab("Find Transactions");
        FindTransactionsPage findTransactionsPage = new FindTransactionsPage();

        findTransactionsPage.findButton.click();
        BrowserUtils.waitFor(2);

        List<String> depositList = findTransactionsPage.getTransactionsDeposits();
        System.out.println("*********depost at least one***********'");
        System.out.println(depositList);
        //System.out.println(findTransactionsPage.atLeastOneDepositTableRow(depositList));

        List<String> withdrwaList = findTransactionsPage.getTransactionsWithDrawals();
        System.out.println("*********withdraw at least one***********'");
        System.out.println(withdrwaList);
        //System.out.println(findTransactionsPage.atLeastOneWithDrawalTableRow(withdrwaList));


        BrowserUtils.waitFor(2);

        Driver.closeDriver();
    }

}
