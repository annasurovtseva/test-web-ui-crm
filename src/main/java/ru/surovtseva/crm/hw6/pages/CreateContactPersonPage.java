package ru.surovtseva.crm.hw6.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.surovtseva.crm.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateContactPersonPage extends BasePage {
    public CreateContactPersonPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='user-name' and contains(.,'Создать контактное лицо')]")
    private WebElement pageTitle;

    @FindBy(css = "input[name='crm_contact[lastName]']")
    private WebElement fieldLastName;

    @FindBy(css = "input[name='crm_contact[firstName]']")
    private WebElement fieldFirstName;

    @FindBy(css = "input[name='crm_contact[jobTitle]']")
    private WebElement fieldJobTitle;

    @FindBy(css = "input[name='crm_contact[company]'")
    private WebElement fieldOrganisation;

    @FindBy(xpath = "//span[@class='select2-arrow']")
    private WebElement contactOrgChosen;

    @FindBy(xpath = "//input[contains (@class,'select2-input')]")
    private WebElement contactOrgInput;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement contactOrgResult;

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    private WebElement buttonSave;


    public CreateContactPersonPage enterLastName(String lastName) {
        fieldLastName.sendKeys(lastName);
        return this;
    }

    public CreateContactPersonPage enterFirstName(String firstName) {
        fieldFirstName.sendKeys(firstName);
        return this;
    }

    public CreateContactPersonPage enterJobTitle(String jobTitle) {
        fieldJobTitle.sendKeys(jobTitle);
        return this;
    }
    public CreateContactPersonPage enterOrganisationName(String organisationName) {
        contactOrgChosen.click();
        contactOrgInput.sendKeys(organisationName);
        wait10second.until(ExpectedConditions.visibilityOf(contactOrgResult));
        contactOrgInput.sendKeys(Keys.ENTER);
        return this;
    }

    public ContactPersonsPage clickOnButtonSave() {
        buttonSave.click();
        return new ContactPersonsPage(driver);
    }

    public CreateContactPersonPage checkFieldFirstNameIsFill(String firstName) {
        assertThat(fieldFirstName.getAttribute("value").equals(firstName)).
                as("Поле Имя заполнено").isTrue();
        return this;
    }

    public CreateContactPersonPage checkFieldLastNameIsFill(String lastName) {
        assertThat(fieldLastName.getAttribute("value").equals(lastName)).
                as("Поле Фамилия заполнено").isTrue();
        return this;
    }
    public CreateContactPersonPage checkFieldJobTitleIsFill(String jobTitle) {
        assertThat(fieldJobTitle.getAttribute("value").equals(jobTitle)).
                as("Поле Должность заполнено").isTrue();
        return this;
    }
    public CreateContactPersonPage checkFieldOrganisationIsFill(String organizationValue) {
        assertThat(fieldOrganisation.getAttribute("value").equals(organizationValue)).
                as("Поле Организация заполнено").isTrue();
        return this;
    }

    public CreateContactPersonPage fillFormFields(String lastName,String firstName,String organizationName,String jobTitle) {
        enterLastName(lastName);
        enterFirstName(firstName);
        enterOrganisationName(organizationName);
        enterJobTitle(jobTitle);
        return this;
    }

    public CreateContactPersonPage checkContactFormFieldsAreFill(String lastName,String firstName,String organizationValue,String jobTitle) {
        checkFieldLastNameIsFill(lastName);
        checkFieldFirstNameIsFill(firstName);
        checkFieldOrganisationIsFill(organizationValue);
        checkFieldJobTitleIsFill(jobTitle);
        return this;
    }

    public CreateContactPersonPage checkPageTitle() {
        assertThat(pageTitle.isDisplayed()).as("Открыта страница Создать контактное лицо").isTrue();
        return this;
    }
}
