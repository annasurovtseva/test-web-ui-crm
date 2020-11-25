package ru.surovtseva.crm.hw6.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.crm.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class MyProjectsPage extends BasePage {
    public MyProjectsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='oro-subtitle' and contains(.,'Мои проекты')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//a[@title='Создать проект']")
    private WebElement buttonCreateProject;

    @FindBy(xpath = "//div[contains (@class, 'alert-success')]")
    private WebElement messageBox;

    @Step("Открыта страница 'Мои Проекты'")
    public MyProjectsPage checkPageTitle() {
        wait10second.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.isDisplayed()).as("Открыта страница Все мои проекты").isTrue();
        return this;
    }

    @Step ("Присутствует кнока 'Создать проект'")
    public MyProjectsPage checkButtonCreateOnPage() {
        wait10second.until(ExpectedConditions.visibilityOf(buttonCreateProject));
        assertThat(buttonCreateProject.isDisplayed()).as("Присутствует кнока Создать проект").isTrue();
        return this;
    }

    @Step("Проверка страницы Мои проекты")
    public MyProjectsPage checkMyProjectsPage() {
        checkPageTitle();
        checkButtonCreateOnPage();
        return this;
    }

    @Step ("Появилось уведомление 'Проект сохранен'")
    public MyProjectsPage checkMessage(String messageText) {
        wait10second.until(ExpectedConditions.visibilityOf(messageBox));
        assertThat(messageBox.getText().contains(messageText)).isTrue();
        return this;
    }

    @Step ("Нажата кнопка 'Создать проект'")
    public CreateProjectPage clickOnButtonCreate() {
        wait10second.until(ExpectedConditions.visibilityOf(buttonCreateProject));
        buttonCreateProject.click();
        return new CreateProjectPage(driver);
    }
}
