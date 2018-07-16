package pageObjects;

import selenium.SeleniumUtils;

public abstract class BasePage {
    SeleniumUtils seleniumUtils = new SeleniumUtils();

    public abstract void goTo();

    public abstract void setDisksQuantity(int disksQuantity);

    public abstract void play();

    public void close() {
        seleniumUtils.close();
    }

}
