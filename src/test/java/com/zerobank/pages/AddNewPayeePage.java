package com.zerobank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewPayeePage extends BasePage{


    @FindBy(id = "np_new_payee_name")
    public WebElement payeeNameTextBox;


    @FindBy(id = "np_new_payee_address")
    public WebElement payeeAddressTextBox;



    @FindBy(id = "np_new_payee_account")
    public WebElement accountTextBox;

    @FindBy(id = "np_new_payee_details")
    public WebElement payeeDetailsTextBox;


    @FindBy(id = "add_new_payee")
    public WebElement addButton;



}
