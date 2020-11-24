package ru.surovtseva.crm.hw6.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.surovtseva.crm.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy (xpath = "//h1[@class='oro-subtitle' and contains(.,'Панель инструментов')]")
    private WebElement pageTitle;

    public HomePage checkUrl(String baseUrl) {
        assertThat(driver.getCurrentUrl()).isEqualTo(baseUrl);
        return this;
    }

    public HomePage checkPageTitle() {
        assertThat(pageTitle.isDisplayed());
        return this;
    }
}
