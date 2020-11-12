package ru.surovtseva.crm.hw3;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class CreateContact {
    private static WebDriver driver;
    private static final String LOGIN_PAGE_URL= "https://crm.geekbrains.space/user/login";
    private static final String USER_LOGIN = "Applanatest";
    private static final String USER_PASSWORD = "Student2020!";
    private static final String expensesMenu = "//ul[contains(@class,'nav-multilevel')]/li[contains(.,'Контрагенты')]";
    private static final String expensesSubmenu = "//li[@data-route='crm_contact_index']/a";
    private static final String createButton = "//a[@title='Создать контактное лицо']";
    private static final String orgChosen = "//span[@class='select2-chosen']";
    private static final String orgInput = "//input[contains (@class,'select2-input')]";
    private static final String orgResult = "//div[@class='select2-result-label']";
    private static final String saveButton = "//button[contains(.,'Сохранить и закрыть')]";
    private static final String message = "//div[contains (@class, 'alert-success')]";


    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver.exe");
        driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        login();

        //click on Контрагенты - Контактные лица menu
        Actions actionContractor =new Actions(driver);
        WebElement contractorMenu = driver.findElement(By.xpath(expensesMenu));
        actionContractor.moveToElement(contractorMenu).perform();

        driver.findElement(By.xpath(expensesSubmenu)).click();

        //click on button Создать Контактное лицо
        driver.findElement(By.xpath(createButton)).click();

        //Заполнение полей формы

        //Поле Фамилия: заполнение, проверка
        WebElement fieldLastName = driver.findElement(By.name("crm_contact[lastName]"));
        System.out.println("Поле Фамилия заполнено: " + !fieldLastName.getAttribute("value").isEmpty());
        fieldLastName.sendKeys("Антонов");
        System.out.println("Поле Фамилия заполнено: " + !fieldLastName.getAttribute("value").isEmpty());
        System.out.println("------------------------");

        //Поле Имя: заполнение, проверка
        WebElement fieldFirstName = driver.findElement(By.name("crm_contact[firstName]"));
        System.out.println("Поле Имя заполнено: " + !fieldFirstName.getAttribute("value").isEmpty());
        fieldFirstName.sendKeys("Семен");
        System.out.println("Поле Имя заполнено: " + !fieldFirstName.getAttribute("value").isEmpty());
        System.out.println("------------------------");

        //Поле Организация: заполнение, проверка
        WebElement fieldOrganisation = driver.findElement(By.name("crm_contact[company]"));
        System.out.println("Поле Организация заполнено: " + !fieldOrganisation.getAttribute("value").isEmpty());

        driver.findElement(By.xpath(orgChosen)).click();
        driver.findElement(By.xpath(orgInput)).sendKeys("18");
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(orgResult))));
        driver.findElement(By.xpath(orgInput)).sendKeys(Keys.ENTER);

        System.out.println("Поле Организация заполнено: " + !fieldOrganisation.getAttribute("value").isEmpty());
        System.out.println("------------------------");

        //Поле Должность: заполнение, проверка
        WebElement fieldJobTitle = driver.findElement(By.name("crm_contact[jobTitle]"));
        System.out.println("Поле Должность заполнено: " + !fieldJobTitle.getAttribute("value").isEmpty());
        fieldJobTitle.sendKeys("менеджер");
        System.out.println("Поле Должность заполнено: " + !fieldJobTitle.getAttribute("value").isEmpty());
        System.out.println("------------------------");

        //click on button Сохранить и закрыть
        driver.findElement(By.xpath(saveButton)).click();

        //Проверка: Отображается сообщение 'Контактное лицо сохранено'
        WebElement messageSuccess = driver.findElement(By.xpath(message));
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOf(messageSuccess));
        System.out.println("Отображается сообщение 'Контактное лицо сохранено': " + messageSuccess.isDisplayed());

        tearDown();
    }

    private static void login() {
        driver.get(LOGIN_PAGE_URL);

        driver.manage().window().maximize();

        WebElement loginInput = driver.findElement(By.name("_username"));
        loginInput.sendKeys(USER_LOGIN);

        WebElement passwordInput = driver.findElement(By.name("_password"));
        passwordInput.sendKeys(USER_PASSWORD);

        WebElement loginButton = driver.findElement(By.xpath(".//button[@name='_submit']"));
        loginButton.click();
    }

    private static void tearDown() {
        System.out.println();
        System.out.println("Test is completed!");
        driver.quit();
    }
}
