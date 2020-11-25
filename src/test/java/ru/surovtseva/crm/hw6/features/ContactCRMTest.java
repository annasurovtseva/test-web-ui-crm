package ru.surovtseva.crm.hw6.features;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.surovtseva.crm.hw6.base.BaseTest;
import ru.surovtseva.crm.hw6.common.ContractorsSubmenuButtons;
import ru.surovtseva.crm.hw6.common.NavigationBarTabs;
import ru.surovtseva.crm.hw6.pages.ContactPersonsPage;
import ru.surovtseva.crm.hw6.pages.LoginPage;

import static ru.surovtseva.crm.hw6.common.Configuration.*;

@Feature("Контрагенты")
public class ContactCRMTest extends BaseTest {
    @DisplayName("Создание контактного лица")
    @Test
    void createContactTest() {
       ContactPersonsPage contactPersonsPage = (ContactPersonsPage) new LoginPage(driver)
                .authorisation(USER_LOGIN, USER_PASSWORD)
                .checkPageTitle()
                .getPageNavigation()
                .moveCursorToNavigationTab(NavigationBarTabs.CONTRACTORS)
                .clickSubMenuButton(ContractorsSubmenuButtons.CONTACT_PERSONS);

        contactPersonsPage
                .checkContactPersonsPage()
                .clickOnButtonCreate()
                .checkPageTitle()
                .fillFormFields(LAST_NAME,FIRST_NAME, ORGANISATION_NAME, JOB_TITLE)
                .checkContactFormFieldsAreFill(LAST_NAME,FIRST_NAME, ORGANISATION_VALUE, JOB_TITLE)
                .clickOnButtonSave()
                .checkMessage(CONTACT_CREATED_MESSAGE);
    }
}
