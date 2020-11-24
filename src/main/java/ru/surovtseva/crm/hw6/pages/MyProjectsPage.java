package ru.surovtseva.crm.hw6.pages;

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

    public MyProjectsPage checkPageTitle() {
        assertThat(pageTitle.isDisplayed()).as("Открыта страница Все мои проекты").isTrue();
        return this;
    }

    public MyProjectsPage checkButtonCreateOnPage() {
        assertThat(buttonCreateProject.isDisplayed()).as("Присутствует кнока Создать проект").isTrue();
        return this;
    }

    public MyProjectsPage checkMyProjectsPage() {
        checkPageTitle();
        checkButtonCreateOnPage();
        return this;
    }

    //Проверка: Видим уведомление Проект сохранен
    public MyProjectsPage checkMessage(String messageText) {
        wait10second.until(ExpectedConditions.visibilityOf(messageBox));
        assertThat(messageBox.getText().contains(messageText)).isTrue();
        return this;
    }

    public CreateProjectPage clickOnButtonCreate() {
        buttonCreateProject.click();
        return new CreateProjectPage(driver);
    }
}
