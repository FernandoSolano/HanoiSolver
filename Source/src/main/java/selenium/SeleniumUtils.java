package selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class SeleniumUtils {

    public void browseTo(String url) {
        CommonDriver.webDriver.get(url);
    }

    public void click(By element) {
        CommonDriver.webDriver.findElement(element).click();
    }

    public void clickValueOnDropdown(By element, int index, boolean byVisibleText) {
        Select dropdown = new Select(CommonDriver.webDriver.findElement(element));
        if(byVisibleText) {
            dropdown.selectByVisibleText(index + "");
        }else{
            dropdown.selectByValue(index+"");
        }
    }

    public void dragAndDrop(By elementToDrag, By targetElement) {
        WebElement element = CommonDriver.webDriver.findElement(elementToDrag);
        WebElement target = CommonDriver.webDriver.findElement(targetElement);
        (new Actions(CommonDriver.webDriver)).dragAndDrop(element, target).perform();
    }

    public void close() {
        CommonDriver.webDriver.close();
    }

    public File capture() {
        WebDriver augmentedDriver = new Augmenter().augment(CommonDriver.webDriver);
        return ((TakesScreenshot) augmentedDriver).
                getScreenshotAs(OutputType.FILE);
    }
}
