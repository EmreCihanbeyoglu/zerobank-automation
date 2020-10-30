package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.jsoup.Connection;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class PurchaseForeignCurrencyPage extends BasePage {

    public Select select;

    public PurchaseForeignCurrencyPage(){
        select = new Select(currencyDropDownMenu);
    }

    @FindBy(id = "pc_currency")
    public WebElement currencyDropDownMenu;

    @FindBy(id = "pc_amount")
    public WebElement amountOfMoneyTextBox;

     @FindBy(id = "pc_calculate_costs")
    public WebElement calculateButton;

    public List<String> getForeignCurrencyList(){

        currencyDropDownMenu.click();
        List<WebElement> options =  select.getOptions();
        List<String> optionsTextList = BrowserUtils.getElementsText(options);
        return optionsTextList;
    }

    public void enterValue(int amount){
        amountOfMoneyTextBox.sendKeys(String.valueOf(amount));
    }

    public void clickCalculate(){
        calculateButton.click();
    }


}
