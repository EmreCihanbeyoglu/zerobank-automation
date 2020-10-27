package com.zerobank.pages;

import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
    }
}
