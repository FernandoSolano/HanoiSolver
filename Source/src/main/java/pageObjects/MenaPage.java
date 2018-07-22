package pageObjects;

import domain.Disk;
import org.openqa.selenium.By;

public class MenaPage extends BasePage {
    //ToDo... Remember to manage selectors on this class
    private static final String
            url = "https://mena.com.mx/gonzalo/juegos/hanoi/hanoi_js_1.html",
            diskQtySelector_name = "dificultad",
            tower1_xpath = "//tr[2]/td[1]/a",
            tower2_xpath = "//tr[2]/td[2]/a",
            tower3_xpath = "//tr[2]/td[3]/a";

    public MenaPage(int disksQuantity) {
        super();
        this.disksQuantity = disksQuantity;
        tower1_route = By.xpath(tower1_xpath);
        tower2_route = By.xpath(tower2_xpath);
        tower3_route = By.xpath(tower3_xpath);
        for (int i = 0; i <= disksQuantity; i++) {
            disks.add(new Disk(i, By.xpath("//a[img[@src='img/" + i + ".gif']]")));
        }
        for (int i = disksQuantity; i > 0; i--) {
            tower1.push(disks.get(i));
        }
    }

    @Override
    public void goTo() {
        seleniumUtils.browseTo(url);
    }

    @Override
    public void setDisksQuantity() {
        seleniumUtils.clickValueOnDropdown(By.name(diskQtySelector_name), disksQuantity, false);
    }

    @Override
    public void makeMove(Disk disk, By tower_route) {
        /*synchronized (CommonDriver.webDriver) {
            try {
                CommonDriver.webDriver.wait(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        seleniumUtils.click(disk.getRoute());
        seleniumUtils.click(tower_route);
    }

}
