package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
