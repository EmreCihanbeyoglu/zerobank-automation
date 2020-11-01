package com.zerobank.stepDefinitions;

import com.zerobank.pages.BasePage;
import com.zerobank.utilities.BrowserUtils;
import com.zerobank.utilities.ConfigurationReader;
import com.zerobank.utilities.Driver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {
    @Before
    public void setup(){
        //System.out.println("ConfigurationReader.getValue(\"url\") = " + ConfigurationReader.getValue("url"));
        WebDriver driver = Driver.get();
        driver.get(ConfigurationReader.getValue("url"));
        Driver.get().manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    @After
    public void tearDown(Scenario scenario){
        if (scenario.isFailed()){
            final byte[] screenshot = ((TakesScreenshot) Driver.get()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", "screenshot");
        }
        String currentUrl = Driver.get().getCurrentUrl();
        String loginErrorUrl = "http://zero.webappsecurity.com/login.html?login_error=true";

        BrowserUtils.waitFor(1);
        Driver.closeDriver();
    }

}
