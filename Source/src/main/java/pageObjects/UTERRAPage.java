package pageObjects;

import domain.Disk;
import org.openqa.selenium.By;
import selenium.CommonDriver;

import java.util.LinkedList;

public class UTERRAPage extends BasePage {
    //ToDo... Remember to manage selectors on this class
    private int disksQuantity;
    private static final String
            url = "http://www.uterra.com/juegos/torre_hanoi.php",
            iframe_name = "menu",
            diskQtySelector_name = "diskno",
            tower1_id = "tower1",
            tower2_id = "tower2",
            tower3_id = "tower3";

    private LinkedList<Disk> disks;

    public UTERRAPage(int disksQuantity) {
        this.disksQuantity=disksQuantity;
        tower1_route=By.id(tower1_id);
        tower2_route=By.id(tower2_id);
        tower3_route=By.id(tower3_id);
        disks = new LinkedList<>();
        for (int i = 0; i <= 8; i++) {
            disks.add(new Disk(i, By.id("disk" + i)));
        }
    }

    @Override
    public void goTo() {
        seleniumUtils.browseTo(url);
    }

    @Override
    public void setDisksQuantity() {
        CommonDriver.webDriver.switchTo().frame(CommonDriver.webDriver.findElement(By.name(iframe_name)));
        seleniumUtils.clickValueOnDropdown(By.name(diskQtySelector_name), disksQuantity, true);
    }

    @Override
    public void makeMove(Disk disk, By tower_route) {
        seleniumUtils.dragAndDrop(disk.getRoute(), tower_route);
    }

}
