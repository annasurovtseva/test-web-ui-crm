package ru.surovtseva.crm.hw6.features;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.surovtseva.crm.hw6.base.BaseTest;
import ru.surovtseva.crm.hw6.common.NavigationBarTabs;
import ru.surovtseva.crm.hw6.common.ProjectsSubmenuButtons;
import ru.surovtseva.crm.hw6.pages.LoginPage;
import ru.surovtseva.crm.hw6.pages.MyProjectsPage;

import static ru.surovtseva.crm.hw6.common.Configuration.*;

@Feature("Проекты")
public class ProjectCRMTest extends BaseTest {

    @DisplayName("Создание проекта")
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
