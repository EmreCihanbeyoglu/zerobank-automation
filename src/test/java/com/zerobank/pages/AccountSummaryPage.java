package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class AccountSummaryPage extends BasePage{

    @FindBy(linkText = "Savings")
    public WebElement savingsLink;

    @FindBy(linkText = "Brokerage")
    public WebElement brokerageLink;

    @FindBy(linkText = "Checking")
    public WebElement checkingLink;

    @FindBy(linkText = "Credit Card")
    public WebElement creditCardLink;

    @FindBy(linkText = "Loan")
    public WebElement loanLink;

    @FindBy(className = "board-header")
    public List<WebElement> accountTypes;

    @FindBy(xpath = "//h2[text()='Credit Accounts']/following-sibling::div[1]//thead//th")
    public List<WebElement> creditAccountsColumnNames;

    @FindBy(xpath = "//h2[text()='Cash Accounts']/following-sibling::div[1]//thead//th")
    public List<WebElement> cashAccountsColumnNames;

    @FindBy(xpath = "//h2[text()='Investment Accounts']/following-sibling::div[1]//thead//th")
    public List<WebElement> investmentAccountsColumnNames;

    @FindBy(xpath = "//h2[text()='Loan Accounts']/following-sibling::div[1]//thead//th")
    public List<WebElement> loanAccountsColumnNames;


    public List<String> getAccountTypes(){
        return BrowserUtils.getElementsText(accountTypes);
    }

    public List<String> getColumnNames(String accountName){
        List<String> columnsNames = null;
        switch (accountName){
            case "Credit Accounts":
                columnsNames = BrowserUtils.getElementsText(creditAccountsColumnNames);
                break;
            case "Cash Accounts":
                columnsNames = BrowserUtils.getElementsText(cashAccountsColumnNames);
                break;
            case "Investment Accounts":
                columnsNames = BrowserUtils.getElementsText(investmentAccountsColumnNames);
                break;
            case "Loan Accounts":
                columnsNames = BrowserUtils.getElementsText(loanAccountsColumnNames);
                break;
        }

        return columnsNames;
    }


    public void clickOnLink(String linkName){
        switch (linkName){
            case "Savings":
                savingsLink.click();
                break;
            case "Brokerage":
                brokerageLink.click();
                break;
            case "Checking":
                checkingLink.click();
                break;
            case "Credit Card":
                creditCardLink.click();
                break;
            case "Loan":
                loanLink.click();
                break;
        }
    }



}
