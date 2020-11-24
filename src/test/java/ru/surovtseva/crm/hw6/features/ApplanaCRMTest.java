package ru.surovtseva.crm.hw6.features;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.surovtseva.crm.hw6.base.BaseTest;
import ru.surovtseva.crm.hw6.common.ContractorsSubmenuButtons;
import ru.surovtseva.crm.hw6.common.NavigationBarTabs;
import ru.surovtseva.crm.hw6.common.ProjectsSubmenuButtons;
import ru.surovtseva.crm.hw6.pages.ContactPersonsPage;
import ru.surovtseva.crm.hw6.pages.LoginPage;
import ru.surovtseva.crm.hw6.pages.MyProjectsPage;

import static ru.surovtseva.crm.hw6.common.Configuration.*;

public class ApplanaCRMTest extends BaseTest {

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

    @Nested
    class WhenContactCreated{
        @Test
        void createProjectTest(){
            MyProjectsPage myProjectsPage = (MyProjectsPage) new LoginPage(driver)
                    .authorisation(USER_LOGIN, USER_PASSWORD)
                    .checkPageTitle()
                    .getPageNavigation()
                    .moveCursorToNavigationTab(NavigationBarTabs.PROJECTS)
                    .clickSubMenuButton(ProjectsSubmenuButtons.MY_PROJECTS);

            myProjectsPage
                    .checkMyProjectsPage()
                    .clickOnButtonCreate()
                    .checkPageTitle()
                    .fillFormFields(PROJECT_NAME, ORGANISATION_NAME, MAIN_CONTACT, BUSINESS_UNIT,
                            PROJECT_CURATOR, PROJECT_DIRECTOR, PROJECT_MANAGER)
                    .checkProjectFormFieldsAreFill(PROJECT_NAME, ORGANISATION_VALUE, MAIN_CONTACT, BUSINESS_UNIT,
                            PROJECT_CURATOR, PROJECT_DIRECTOR, PROJECT_MANAGER)
                    .clickOnButtonSave()
                    .checkMessage(PROJECT_CREATED_MESSAGE);
        }
    }
}
