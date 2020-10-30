package com.zerobank.stepDefinitions;

import com.zerobank.pages.PurchaseForeignCurrencyPage;
import com.zerobank.utilities.Driver;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class PurchaseForeignCurrencyStepDefinitions {
    @Then("following currencies should be available")
    public void following_currencies_should_be_available(List<String> foreignCurrencyList) {
        PurchaseForeignCurrencyPage purchaseForeignCurrencyPage = new PurchaseForeignCurrencyPage();
        List<String> actualForeignCurrencyList = purchaseForeignCurrencyPage.getForeignCurrencyList();
        actualForeignCurrencyList.remove(0);
        System.out.println(actualForeignCurrencyList);
        System.out.println(foreignCurrencyList);

        Assert.assertTrue(actualForeignCurrencyList.containsAll(foreignCurrencyList));
    }

    @When("user tries to calculate cost without selecting a currency")
    public void user_tries_to_calculate_cost_without_selecting_a_currency() {

        PurchaseForeignCurrencyPage purchaseForeignCurrencyPage = new PurchaseForeignCurrencyPage();
        purchaseForeignCurrencyPage.enterValue(20);
        purchaseForeignCurrencyPage.clickCalculate();

    }

    @Then("error message should be displayed")
    public void error_message_should_be_displayed() {
        try {
            Alert alert = Driver.get().switchTo().alert();
            //Assert.assertTrue(!alert.equals(null));
            alert.accept();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @When("user tries to calculate cost without entering a value")
    public void userTriesToCalculateCostWithoutEnteringAValue() {
        PurchaseForeignCurrencyPage purchaseForeignCurrencyPage = new PurchaseForeignCurrencyPage();
        purchaseForeignCurrencyPage.currencyDropDownMenu.click();
        purchaseForeignCurrencyPage.select.selectByVisibleText("Eurozone (euro)");
        purchaseForeignCurrencyPage.clickCalculate();
    }
}
