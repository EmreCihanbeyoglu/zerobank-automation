package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import io.cucumber.java.de.Wenn;
import io.cucumber.java.eo.Se;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AccountActivityPage extends BasePage{

    private Select select;

    public AccountActivityPage(){
        select = new Select(accountSelectionDropDown);
    }

    @FindBy(id = "aa_accountId")
    public WebElement accountSelectionDropDown;

    @FindBy(linkText = "Find Transactions")
    public WebElement findTransactionsTab;

    @FindBy(linkText = "Show Transactions")
    public WebElement showTransactionsTab;

    @FindBy(css = ".table.table-condensed.table-hover th")
    public List<WebElement> columnsNames;

    public List<String> getColumnNames(){
        return BrowserUtils.getElementsText(columnsNames);
    }

    public String getSelectedOption(){
        return select.getFirstSelectedOption().getText();
    }


    public List<String> getAllAccountTypeOptions(){
        return BrowserUtils.getElementsText(select.getOptions());
    }


    //asagidakini kullanmama gerek kalmiuor - BasePage icindeki isimi goruyor...
    public void navigateToTab(String tabName){
        Actions actions = new Actions(Driver.get());

        if (tabName.equals("Find Transactions")){
            actions.moveToElement(findTransactionsTab).pause(200).click().perform();
            BrowserUtils.waitFor(2);
        }else if(tabName.equals("Show Transactions")){
            actions.moveToElement(showTransactionsTab).pause(200).click().perform();
            BrowserUtils.waitFor(2);
        }

    }


}
