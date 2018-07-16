package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class SeleniumUtils {

    public void browseTo(String url) {
        CommonDriver.webDriver.get(url);
    }

    public void click(By element) {
        CommonDriver.webDriver.findElement(element).click();
    }

    public void clickValueOnDropdown(By element, int index) {
        Select dropdown = new Select(CommonDriver.webDriver.findElement(element));
        dropdown.selectByVisibleText(index + "");
    }

    public void dragAndDrop(By elementToDrag, By targetElement) {
        WebElement element = CommonDriver.webDriver.findElement(elementToDrag);
        WebElement target = CommonDriver.webDriver.findElement(targetElement);
        (new Actions(CommonDriver.webDriver)).dragAndDrop(element, target).perform();
        //new Actions(CommonDriver.webDriver).clickAndHold(element).dragAndDrop(element, target).release().perform();
        /*Actions actions = new Actions(CommonDriver.webDriver);
        actions.clickAndHold(element);
        actions.moveToElement(target);
        actions.release();
        actions.perform();*/
        /*Actions actions = new Actions(CommonDriver.webDriver);
        actions.clickAndHold(element).moveToElement(target).release().perform();
        Action drag = actions.build();
        drag.perform();*/
    }

    public void close() {
        CommonDriver.webDriver.close();
    }

}
