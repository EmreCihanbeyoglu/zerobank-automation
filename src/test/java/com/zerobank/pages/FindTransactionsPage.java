package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FindTransactionsPage extends BasePage{
    WebDriverWait wait;
    public FindTransactionsPage(){
        PageFactory.initElements(Driver.get(), this);
        wait = new WebDriverWait(Driver.get(), 10);
    }

    @FindBy(xpath = "//input[@id='aa_fromDate']")
    public WebElement fromDateTextBox;

    @FindBy(xpath = "//input[@id='aa_toDate']")
    public WebElement toDateTextBox;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement findButton;

    @FindBy(id = "aa_description")
    public WebElement descriptionTextBox;

    @FindBy(id = "aa_type")
    public WebElement typeDropDown;

    public void selectOptionFromDropDown(String option){
        Select select = new Select(typeDropDown);
        select.selectByVisibleText(option);
    }

    public void typeIntoFromDateTextBox(String fromDate){
        //System.out.println(fromDateTextBox.getAttribute("innerHTML"));
/*        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("arguments[0].value='" +  fromDate + "'", fromDateTextBox);*/
        fromDateTextBox.clear();
        fromDateTextBox.sendKeys(fromDate);
    }

    public void typeIntoToDateTextBox(String toDate){
        toDateTextBox.clear();
        toDateTextBox.sendKeys(toDate);
/*        JavascriptExecutor jse = (JavascriptExecutor) Driver.get();
        jse.executeScript("arguments[0].value='" +  toDate + "'", toDateTextBox);*/
    }

    public void typeIntoDescriptionTextBox(String description){
        descriptionTextBox.clear();
        descriptionTextBox.sendKeys(description);
    }


    public void fillOutDatesAndClick(String fromDate, String toDate){
        typeIntoFromDateTextBox(fromDate);
        typeIntoToDateTextBox(toDate);
        findButton.click();

    }

    public List<WebElement> getTransactionsDataFromTable(){
        List<WebElement> transactionsList = Driver.get().findElements(By.cssSelector("#filtered_transactions_for_account>table>tbody>tr"));
        return transactionsList;
    }

    public List<String> getTransactionsDates(){
        List<String> dateList = new ArrayList<>();
        int totalRowNumber = getTotalNumberOfRowsInTableAfterSearch();
        for (int i = 1; i <= totalRowNumber; i++) {
            String locator = "//div[@id='filtered_transactions_for_account']//tbody//tr[" + i + "]//td[1]";
            String newDate = Driver.get().findElement(By.xpath(locator)).getText();
            dateList.add(newDate);
        }
        return dateList;
    }

    public int getTotalNumberOfRowsInTableAfterSearch(){
        return Driver.get().findElements(By.cssSelector("#filtered_transactions_for_account>table>tbody>tr")).size();
    }

    public void clickFindButton(){
        findButton.click();
        //till table shows up
        BrowserUtils.waitFor(1);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#filtered_transactions_for_account>table")));
    }

    public List<String> getTransactionsDescriptions(){
        List<String> descriptionList = new ArrayList<>();
        int totalRowNumber = getTotalNumberOfRowsInTableAfterSearch();
        for (int i = 1; i <= totalRowNumber; i++) {
            String locator = "//div[@id='filtered_transactions_for_account']//tbody//tr[" + i + "]//td[2]";
            String newDate = Driver.get().findElement(By.xpath(locator)).getText();
            descriptionList.add(newDate);
        }
        return descriptionList;
    }

    public boolean doesDescriptionContainSpecifiedText(List<String> descriptionList, String specifiedText){

        for (String description : descriptionList) {
            if (!description.contains(specifiedText.toUpperCase())){
                return false;
            }
        }
        return true;
    }

    public boolean doesDescriptionNOTContainSpecifiedText(List<String> descriptionList, String specifiedText){
        for (String description : descriptionList) {
            if (description.contains(specifiedText.toUpperCase())){
                return false;
            }
        }
        return true;
    }

    public List<Date> convertDatesInListFromString(List<String> dateListAsString) throws ParseException {
        List<Date> dateListAsDate = new ArrayList<>();
        for (String dateAsString : dateListAsString) {
            dateListAsDate.add(new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString));
        }

        return dateListAsDate;
    }

    public Date convertDateFromString(String dateAsString) throws ParseException {
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateAsString);
        return date;
    }

    public boolean isInDateRange(String fromDateAsString, String toDateAsString, List<String> dateListAsString) throws ParseException {

        Date fromDate = convertDateFromString(fromDateAsString);
        Date toDate = convertDateFromString(toDateAsString);
        List<Date> dateList = convertDatesInListFromString(dateListAsString);

        for (Date date : dateList) {
            if ((date.compareTo(fromDate) >= 0) && (date.compareTo(toDate) <= 0)){
                continue;
            }else{
                return false;
            }
        }
        return true;


    }

    public List<String> sortDatesAsString(List<String> dateList){
        List<String> tempDateList = new ArrayList<>(dateList);
        Collections.sort(tempDateList);
        return tempDateList;
    }

    public boolean isFromMostRecentDate(List<String> dateListAsString){

        List<String> copyOfList = new ArrayList<>();

        for (String s : dateListAsString) {
            copyOfList.add(s);
        }
        Collections.sort(copyOfList);
        int size = dateListAsString.size();
        for (int i = size - 1; i >= 0 ; i--) {
            if (!dateListAsString.get(i).equals(copyOfList.get(size - i - 1))){
                return false;
            }
        }
        return true;
    }

    public List<String> getTransactionsDeposits(){
        List<String> depositList = new ArrayList<>();
        int totalRowNumber = getTotalNumberOfRowsInTableAfterSearch();
        for (int i = 1; i <= totalRowNumber; i++) {
            String locator = "//div[@id='filtered_transactions_for_account']//tbody//tr[" + i + "]//td[3]";
            String newDeposit = Driver.get().findElement(By.xpath(locator)).getText();
            if (!newDeposit.equals("")){
                depositList.add(newDeposit);
            }
        }
        return depositList;

    }

    public int getNumberOfDepositTableRow(List<String> depositList){
        return depositList.size();
    }

    public List<String> getTransactionsWithDrawals(){
        List<String> withdrawList = new ArrayList<>();
        int totalRowNumber = getTotalNumberOfRowsInTableAfterSearch();
        for (int i = 1; i <= totalRowNumber; i++) {
            String locator = "//div[@id='filtered_transactions_for_account']//tbody//tr[" + i + "]//td[4]";
            String newWithdrawal = Driver.get().findElement(By.xpath(locator)).getText();
            if (!newWithdrawal.equals("")){
                withdrawList.add(newWithdrawal);
            }
        }
        return withdrawList;
    }

    public int getNumberOfWithDrawalTableRow(List<String> withdrawList){
       return withdrawList.size();
    }


}
