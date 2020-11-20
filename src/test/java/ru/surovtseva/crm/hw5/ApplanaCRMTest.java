package ru.surovtseva.crm.hw5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.TimeUnit;

public class ApplanaCRMTest {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL= "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest";
    private static final String USER_PASSWORD = "Student2020!";
    private static final String firsPageTitle = "//h1[@class='oro-subtitle' and contains(.,'Панель инструментов')]";
    private static final String createContactButton = "//a[@title='Создать контактное лицо']";
    private static final String createProjectButton = "//a[@title='Создать проект']";
    private static final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    private static final String messageBox = "//div[contains (@class, 'alert-success')]";
    private static final String messageText = "//div[@class='message']";
    private static final String orgChosenProject = "//div[@class='company-container']/div/a/span[@class='select2-arrow']";
    private static final String orgInputProject = "//input[@class='select2-input select2-focused']";
    private static final String orgResultProject = "//div[@class='select2-result-label']";
    private static final String projectMenu = "//li[@class='dropdown']/a[contains(., 'Проекты')]";
    private static final String projectSubmenu = "//li[@data-route='crm_project_my']/a";
    private static final String projectPageTitle = "//h1[@class='oro-subtitle' and contains(.,'Мои проекты')]";
    private static final String orgResultContact = "//div[@class='select2-result-label']";
    private static final String orgInputContact = "//input[contains (@class,'select2-input')]";
    private static final String orgChosenContact = "//span[@class='select2-arrow']";
    private static final String menuContractor = "//ul[contains(@class,'nav-multilevel')]/li[contains(.,'Контрагенты')]";
    private static final String submenuContact = "//li[@data-route='crm_contact_index']/a";
    private static final String contactPageTitle = "//h1[@class='oro-subtitle']";
    private static final String contactPersonsTable = "//table";
    private static final String lastName = "Андреев";
    private static final String firstName = "Андрей";
    private static final String organizationName = "104";
    private static final String organizationValue = "113";
    private static final String jobTitle = "менеджер";
    private static final String projectName = "Test_project_20201120_1_saa";
    private static final String mainContact = lastName+" "+firstName;
    private static final String projectCurator = "Карпов Руслан";
    private static final String projectDirector = "Авласёнок Денис";
    private static final String projectManager = "Амелин Владимир";

    @BeforeAll
    static void setUpWebDriverManager() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        setUpDriverSession();
        login();
    }

    @Test
    void createContactTest() {
        authorisation();
        pageContactPersonsIsOpen();
        pageCreateIsOpen("Создать контактное лицо",
                createContactButton,
                "Открыта страница Создать контактное лицо");
        contactFormFieldsAreFill();
        saveButtonIsClick("Контактное лицо сохранено");
    }

    @Nested
    class whenContactCreated {
        @Test
        void createProjectTest() {
            authorisation();
            pageMyProjectIsOpen();
            pageCreateIsOpen("Создать проект",
                    createProjectButton,
                    "Открыта страница Создать проект");
            projectFormFieldsAreFill();
            saveButtonIsClick("Проект сохранен");
        }
     }

    @AfterEach
    void tearDown(){
        if(driver !=null){
            driver.quit();
        }
    }

// **** Методы теста Создание проекта ***

    private void projectFormFieldsAreFill() {
        //Поле Наименование
        WebElement fieldProjectName = driver.findElement(By.name("crm_project[name]"));
        fieldProjectName.sendKeys(projectName);

        //Поле Организация
        WebElement fieldOrganisation = driver.findElement(By.name("crm_project[company]"));
        new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgChosenProject))));
        driver.findElement(By.xpath(orgChosenProject)).click();

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgInputProject))));
        driver.findElement(By.xpath(orgInputProject)).sendKeys(organizationName);

        new WebDriverWait(driver, 5).
                until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgResultProject))));
        driver.findElement(By.xpath(orgInputProject)).sendKeys(Keys.ENTER);

        //Поле Основное контактное лицо
        Select fieldMainContact = new Select(driver.findElement(By.name("crm_project[contactMain]")));
        fieldMainContact.selectByVisibleText(mainContact);

        //Поле Подразделение
        Select fieldBusinessUnit = new Select(driver.findElement(By.name("crm_project[businessUnit]")));
        fieldBusinessUnit.selectByValue("1");

        //Поле Куратор проекта
        Select fieldBProjectCurator = new Select(driver.findElement(By.name("crm_project[curator]")));
        fieldBProjectCurator.selectByVisibleText(projectCurator);

        //Поле Руководитель проекта
        Select fieldBProjectDirector = new Select(driver.findElement(By.name("crm_project[rp]")));
        fieldBProjectDirector.selectByVisibleText(projectDirector);

        //Поле Менеджер проекта
        Select fieldBProjectManager = new Select(driver.findElement(By.name("crm_project[manager]")));
        fieldBProjectManager.selectByVisibleText(projectManager);

        Assertions.assertAll(
                ()-> assertThat(fieldProjectName.getAttribute("value").equals(projectName)).
                        as("Поле Наименование заполнено").isTrue(),
                ()-> assertThat(fieldOrganisation.getAttribute("value").equals(organizationValue)).
                        as("Поле Организация заполнено").isTrue(),
                ()-> assertThat(fieldMainContact.getFirstSelectedOption().getText().equals(mainContact)).
                        as("Поле Основное контактное лицо").isTrue(),
                ()-> assertThat(fieldBusinessUnit.getFirstSelectedOption().getAttribute("value").equals("1")).
                        as("Поле Подразделение").isTrue(),
                ()-> assertThat(fieldBProjectCurator.getFirstSelectedOption().getText().equals(projectCurator)).
                        as("Поле Куратор проекта").isTrue(),
                ()-> assertThat(fieldBProjectDirector.getFirstSelectedOption().getText().equals(projectDirector)).
                        as("Поле Руководитель проекта").isTrue(),
                ()-> assertThat(fieldBProjectManager.getFirstSelectedOption().getText().equals(projectManager)).
                        as("Поле Менеджер проекта").isTrue()
        );
    }

    private void pageMyProjectIsOpen() {
        navigationThroughTheMenu(projectMenu,projectSubmenu);

        new WebDriverWait(driver, 3).
                until(ExpectedConditions.textToBe(By.xpath(projectPageTitle), "Все Мои проекты"));

        WebElement createButton = driver.findElement(By.xpath(createProjectButton));
        assertThat(createButton.isDisplayed()).as("Присутствует кнока Создать Проект").isTrue();
     }

// *** Методы теста Создание контакта ***

    private void contactFormFieldsAreFill() {
        //Поле Фамилия
        WebElement fieldLastName = driver.findElement(By.name("crm_contact[lastName]"));
        fieldLastName.sendKeys(lastName);

        //Поле Имя
        WebElement fieldFirstName = driver.findElement(By.name("crm_contact[firstName]"));
        fieldFirstName.sendKeys(firstName);

        WebElement fieldOrganisation = driver.findElement(By.name("crm_contact[company]"));
        driver.findElement(By.xpath(orgChosenContact)).click();
        driver.findElement(By.xpath(orgInputContact)).sendKeys(organizationName);
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgResultContact))));
        driver.findElement(By.xpath(orgInputContact)).sendKeys(Keys.ENTER);

        WebElement fieldJobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        fieldJobTitle.sendKeys(jobTitle);

        Assertions.assertAll(
                ()-> assertThat(fieldLastName.getAttribute("value").equals(lastName)).
                        as("Поле Фамилия заполнено").isTrue(),
                ()-> assertThat(fieldFirstName.getAttribute("value").equals(firstName)).
                        as("Поле Имя заполнено").isTrue(),
                ()-> assertThat(fieldOrganisation.getAttribute("value").equals(organizationValue)).
                        as("Поле Организация заполнено").isTrue(),
                ()-> assertThat(fieldJobTitle.getAttribute("value").equals(jobTitle)).
                        as("Поле Должность заполнено").isTrue()
        );
    }

    private void pageContactPersonsIsOpen() {
        navigationThroughTheMenu(menuContractor,submenuContact);

        WebElement createButton = driver.findElement(By.xpath(createContactButton));
        WebElement contactsTable = driver.findElement(By.xpath(contactPersonsTable));

        new WebDriverWait(driver, 3).
                until(ExpectedConditions.textToBe(By.xpath(contactPageTitle), "Все Контактные лица"));

        Assertions.assertAll(
                ()-> assertThat(createButton.isDisplayed()).
                        as("Присутствует кнока Создать контактное лицо").isTrue(),
                ()-> assertThat(contactsTable.isDisplayed()).
                        as("Присутствует Таблица контактных лиц").isTrue()
        );
    }

//*** Общие методы ***

    /*
     Метод принимает menu = локатор пункта основного меню
                     submenu = локатор пункта подменю
    */
    private void navigationThroughTheMenu(String menu, String submenu){
        Actions action = new Actions(driver);
        WebElement MENU = driver.findElement(By.xpath(menu));
        action.moveToElement(MENU);

        WebElement SUBMENU = driver.findElement(By.xpath(submenu));
        action.moveToElement(SUBMENU);
        action.click().build().perform();
    }

    /*
    Метод принимает title = заголовок, который нужно найти на странице
                    createButton = локатор кнопки Создать...
                    assertDescription = описание для проверки
    */
    private void pageCreateIsOpen(String title,String createButton, String assertDescription){
        String createPageTitle = "//h1[@class='user-name' and contains(.,'"+title+"')]";
        driver.findElement(By.xpath(createButton)).click();
        WebElement pageTitle = driver.findElement(By.xpath(createPageTitle));
        assertThat(pageTitle.isDisplayed()).as(assertDescription).isTrue();
    }

    //Метод принимает Text = текст ожидаемого сообщения
    private void saveButtonIsClick(String text) {
        driver.findElement(By.xpath(saveButton)).click();
        WebElement messageSuccess = driver.findElement(By.xpath(messageBox));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(messageSuccess));
        WebElement textOfMessage = driver.findElement(By.xpath(messageText));
        Assertions.assertEquals(text, textOfMessage.getText());
    }

    private void authorisation() {
        WebElement firstPage = driver.findElement(By.xpath(firsPageTitle));
        Assertions.assertTrue(firstPage.isDisplayed());
    }

    private void login() {
        driver.get(LOGIN_PAGE_URL);
        driver.manage().window().maximize();
        WebElement loginInput = driver.findElement(By.name("_username"));
        loginInput.sendKeys(USER_LOGIN);
        WebElement passwordInput = driver.findElement(By.name("_password"));
        passwordInput.sendKeys(USER_PASSWORD);
        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }

    private static void setUpDriverSession() {
        driver = new ChromeDriver();
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }
}
