package ru.surovtseva.crm.hw6.common;

import org.openqa.selenium.By;
import ru.surovtseva.crm.hw6.base.SubmenuButtons;

public enum ContractorsSubmenuButtons implements SubmenuButtons {
    CONTACT_PERSONS("//li[@data-route='crm_contact_index']/a");

    private final By by;

    ContractorsSubmenuButtons(String xpath) {
        this.by = By.xpath(xpath);
    }

    public By getBy() {
        return by;
    }
}
