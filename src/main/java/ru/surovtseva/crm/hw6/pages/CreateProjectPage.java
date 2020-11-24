package ru.surovtseva.crm.hw6.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.surovtseva.crm.hw6.base.BasePage;

import static org.assertj.core.api.Assertions.assertThat;

public class CreateProjectPage extends BasePage {
    public CreateProjectPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//h1[@class='user-name' and contains(.,'Создать проект')]")
    private WebElement pageTitle;

    @FindBy(css = "input[name='crm_project[name]']")
    private WebElement fieldProjectName;

    @FindBy(css = "input[name='crm_project[company]']")
    private WebElement fieldOrganisation;

    @FindBy(xpath = "//div[@class='company-container']/div/a/span[@class='select2-arrow']")
    private WebElement projectOrgChosen;

    @FindBy(xpath = "//input[@class='select2-input select2-focused']")
    private WebElement projectOrgInput;

    @FindBy(xpath = "//div[@class='select2-result-label']")
    private WebElement projectOrgResult;

    @FindBy(css = "select[name='crm_project[contactMain]']")
    private WebElement selectMainContact;
    protected Select fieldMainContact = new Select(selectMainContact);

    @FindBy(css = "select[name='crm_project[businessUnit]']")
    private WebElement selectBusinessUnit;
    private Select fieldBusinessUnit = new Select(selectBusinessUnit);

    @FindBy(css = "select[name='crm_project[curator]']")
    private WebElement selectProjectCurator;
    private Select fieldProjectCurator = new Select(selectProjectCurator);

    @FindBy(css = "select[name='crm_project[rp]']")
    private WebElement selectProjectDirector;
    private Select fieldProjectDirector = new Select(selectProjectDirector);

    @FindBy(css = "select[name='crm_project[manager]']")
    private WebElement selectProjectManager;
    private Select fieldProjectManager = new Select(selectProjectManager);

    @FindBy(xpath = "//button[contains(.,'Сохранить и закрыть')]")
    private WebElement buttonSave;

    public CreateProjectPage enterProjectName(String projectName) {
        fieldProjectName.sendKeys(projectName);
        return this;
    }

    public CreateProjectPage enterOrganisationName(String organisationName) {
        wait10second.until(ExpectedConditions.visibilityOf(projectOrgChosen));
        projectOrgChosen.click();

        wait10second.until(ExpectedConditions.visibilityOf(projectOrgInput));
        projectOrgInput.sendKeys(organisationName);

        wait10second.until(ExpectedConditions.visibilityOf(projectOrgResult));
        projectOrgInput.sendKeys(Keys.ENTER);
        return this;
    }

    public CreateProjectPage enterMainContact(String mainContact) {
        fieldMainContact.selectByVisibleText(mainContact);
        return this;
    }

    public CreateProjectPage enterBusinessUnit(String businessUnit) {
        fieldBusinessUnit.selectByVisibleText(businessUnit);
        return this;
    }

    public CreateProjectPage enterProjectCurator(String projectCurator) {
        fieldProjectCurator.selectByVisibleText(projectCurator);
        return this;
    }

    public CreateProjectPage enterProjectDirector(String projectDirector) {
        fieldProjectDirector.selectByVisibleText(projectDirector);
        return this;
    }

    public CreateProjectPage enterProjectManager(String projectManager) {
        fieldProjectManager.selectByVisibleText(projectManager);
        return this;
    }

    public CreateProjectPage fillFormFields(String projectName,String organisationName,
                                            String mainContact,String businessUnit,
                                            String projectCurator,String projectDirector,
                                            String projectManager) {
        enterProjectName(projectName);
        enterOrganisationName(organisationName);
        enterMainContact(mainContact);
        enterBusinessUnit(businessUnit);
        enterProjectCurator(projectCurator);
        enterProjectDirector(projectDirector);
        enterProjectManager(projectManager);
        return this;
    }

    public MyProjectsPage clickOnButtonSave() {
        buttonSave.click();
        return new MyProjectsPage(driver);
    }

    public CreateProjectPage checkPageTitle() {
        assertThat(pageTitle.isDisplayed()).as("Открыта страница Создать проект").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldProjectNameIsFill(String projectName) {
        assertThat(fieldProjectName.getAttribute("value").equals(projectName)).
                as("Поле Наименование заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldOrganisationIsFill(String organizationValue) {
        assertThat(fieldOrganisation.getAttribute("value").equals(organizationValue)).
                as("Поле Организация заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldMainContactIsFill(String mainContact) {
        assertThat(fieldMainContact.getFirstSelectedOption().getText().equals(mainContact)).
                as("Поле Основное контактное лицо заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldBusinessUnitIsFill(String businessUnit) {
        assertThat(fieldBusinessUnit.getFirstSelectedOption().getText().equals(businessUnit)).
                as("Поле Подразделение заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldProjectCuratorIsFill(String projectCurator) {
        assertThat(fieldProjectCurator.getFirstSelectedOption().getText().equals(projectCurator)).
                as("Поле Куратор проекта заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldProjectDirectorIsFill(String projectDirector) {
        assertThat(fieldProjectDirector.getFirstSelectedOption().getText().equals(projectDirector)).
                as("Поле Руководитель проекта заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkFieldProjectManagerIsFill(String projectManager) {
        assertThat(fieldProjectManager.getFirstSelectedOption().getText().equals(projectManager)).
                as("Поле Менеджер проекта заполнено").isTrue();
        return this;
    }

    public CreateProjectPage checkProjectFormFieldsAreFill(String projectName,String organisationValue,
                                                           String mainContact,String businessUnit,
                                                           String projectCurator,String projectDirector,
                                                           String projectManager) {
        checkFieldProjectNameIsFill(projectName);
        checkFieldOrganisationIsFill(organisationValue);
        checkFieldMainContactIsFill(mainContact);
        checkFieldBusinessUnitIsFill(businessUnit);
        checkFieldProjectCuratorIsFill(projectCurator);
        checkFieldProjectDirectorIsFill(projectDirector);
        checkFieldProjectManagerIsFill(projectManager);
        return this;
    }
}
