package ru.surovtseva.crm.hw6.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import ru.surovtseva.crm.hw6.common.NavigationBarTabs;

public class NavigationBar extends BasePage {
    public NavigationBar(WebDriver driver) {
        super(driver);
    }

    public SubMenu moveCursorToNavigationTab(NavigationBarTabs tab) {
        Actions actions = new Actions(driver);
        actions
                .moveToElement(driver.findElement(tab.getBy()))
                .build()
                .perform();

        switch (tab) {
            case CONTRACTORS:
                return new ContractorSubmenu(driver);
            case PROJECTS:
                return new ProjectsSubmenu(driver);
            default:
                throw new IllegalArgumentException
                        ("Another tabs currently not implemented");
        }
    }
}


