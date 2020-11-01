package com.zerobank.pages;

import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class LoginPage {

    public LoginPage(){
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(id = "user_login")
    public WebElement userNameTextBox;

    @FindBy(id = "user_password")
    public WebElement passwordTextBox;

    @FindBy(xpath = "//input[@value='Sign in']")
    public WebElement signInButton;


    public void login(){
        String username = ConfigurationReader.getValue("username");
        String password = ConfigurationReader.getValue("password");

        userNameTextBox.sendKeys(username);
        passwordTextBox.sendKeys(password);
        signInButton.click();
        BrowserUtils.waitFor(2);
    }

    public void typeUserName(String username){
        if (!username.isEmpty()){
            userNameTextBox.sendKeys(username);
        }
    }

    public void typePassword(String password){
        if (!password.isEmpty()){
            passwordTextBox.sendKeys(password);
        }

    }

    public void clickLoginButton(){
        signInButton.click();
    }

    public String getMessageContent(){
        WebElement message = Driver.get().findElement(By.cssSelector(".alert.alert-error"));
        return message.getText();
    }

}
