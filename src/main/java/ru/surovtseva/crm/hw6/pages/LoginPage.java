package ru.surovtseva.crm.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.surovtseva.crm.hw6.base.BasePage;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "input[name='_username']")
    private WebElement loginInput;
    @FindBy(css ="input[name='_password']")
    private WebElement passwordInput;
    @FindBy(css = "button[name='_submit']")
    private WebElement loginButton;


    public LoginPage enterLogin(String login) {
        loginInput.sendKeys(login);
        return this;
    }
    public LoginPage enterPassword(String password) {
        passwordInput.sendKeys(password);
        return this;
    }
    public HomePage clickLoginButton() {
        loginButton.click();
        return new HomePage(driver);
    }

    @Step ("Авторизация")
    public HomePage authorisation(String userLogin, String userPassword) {
        enterLogin(userLogin);
        enterPassword(userPassword);
        clickLoginButton();
        return new HomePage(driver);
    }
}
