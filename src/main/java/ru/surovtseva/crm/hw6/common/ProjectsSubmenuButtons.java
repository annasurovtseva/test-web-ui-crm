package ru.surovtseva.crm.hw6.common;

import org.openqa.selenium.By;
import ru.surovtseva.crm.hw6.base.SubmenuButtons;

public enum ProjectsSubmenuButtons implements SubmenuButtons {
    MY_PROJECTS("//li[@data-route='crm_project_my']/a");

    private final By by;

    ProjectsSubmenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
