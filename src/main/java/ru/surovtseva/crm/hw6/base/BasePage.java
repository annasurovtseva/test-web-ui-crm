package ru.surovtseva.crm.hw6.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait30second;
    protected WebDriverWait wait10second;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait10second = new WebDriverWait(driver, 10);
        this.wait30second = new WebDriverWait(driver, 30);
        PageFactory.initElements(driver, this);
    }

    public NavigationBar getPageNavigation() {
        return new NavigationBar(driver);
    }
}
