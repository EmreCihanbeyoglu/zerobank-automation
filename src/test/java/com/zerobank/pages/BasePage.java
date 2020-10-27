package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import javax.swing.*;

public abstract class BasePage {

    public BasePage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(xpath = "(//a[@class='dropdown-toggle'])[2]")
    public static WebElement user;

    @FindBy(id = "logout_link")
    public static WebElement logOut;

    @FindBy(css = "li[id$='_tab'][class='active']")
    public static WebElement activePageTab;

    public String getUserName(){
        return user.getText();
    }

    public static void logOut(){
        BrowserUtils.waitFor(2);
        BrowserUtils.clickWithJS(user);
        BrowserUtils.clickWithJS(logOut);
    }

    public void navigateBetweenPages(String tab){
        Actions actions = new Actions(Driver.get());
        WebElement pageTab = Driver.get().findElement(By.linkText(tab));
        actions.moveToElement(pageTab).pause(200).click().perform();

        BrowserUtils.waitFor(3);
    }

    public static String getActivePageTab(){
        return activePageTab.getText();
    }


}
