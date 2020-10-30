package com.zerobank.pages;

import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PayBillsPage extends BasePage{

    public String getMessageContent(){
        WebElement message = Driver.get().findElement(By.id("alert_content"));
        return message.getText();
    }


}
