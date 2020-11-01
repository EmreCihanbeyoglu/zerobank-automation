package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriverBuilder;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PayBillsPage extends BasePage{

/*    public String getMessageContent(){
        WebElement message = Driver.get().findElement(By.id("alert_content"));
        return message.getText();
    }*/


    public String getActiveSubTab(){
        List<WebElement> subTabList = Driver.get().findElements(By.cssSelector("div[id^='ui-tabs-']"));
        String activeSubTabId = null;
        String classAttributeContent =null;
        for (WebElement subTab : subTabList) {
            classAttributeContent = subTab.getAttribute("class");
            if (!classAttributeContent.contains("hide")){
                activeSubTabId = subTab.getAttribute("id");
            }
        }
        return activeSubTabId;
    }

    public void navigateToSubTab(String tabName){
        WebElement tab = Driver.get().findElement(By.linkText(tabName));
        tab.click();
        WebDriverWait wait = new WebDriverWait(Driver.get(), 10);
        String subTabLocatorAsId = getActiveSubTab();
        String formLocator = "//div[@id='" + subTabLocatorAsId + "']//form";
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(formLocator)));
    }


}
