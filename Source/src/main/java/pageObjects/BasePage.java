package pageObjects;

import domain.Disk;
import selenium.SeleniumUtils;

import java.io.File;

public abstract class BasePage {
    SeleniumUtils seleniumUtils = new SeleniumUtils();

    public abstract void goTo();

    public abstract void setDisksQuantity(int disksQuantity);

    public abstract void play();

    public abstract void makeMove(Disk disk, String tower);

    public void close() {
        seleniumUtils.close();
    }

    public File takeScreenshot(){
        return seleniumUtils.capture();
    }

}
