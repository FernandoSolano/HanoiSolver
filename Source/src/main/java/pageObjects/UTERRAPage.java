package pageObjects;

import org.openqa.selenium.By;
import selenium.CommonDriver;

public class UTERRAPage extends BasePage {
    //ToDo... Remember to manage selectors on this class
    private int disksQuantity;
    private static final String
            url = "http://www.uterra.com/juegos/torre_hanoi.php",
            iframe_name = "menu",
            diskQtySelector_name = "diskno",
            disk1_id = "disk1",
            disk2_id = "disk2",
            disk3_id = "disk3",
            disk4_id = "disk4",
            disk5_id = "disk5",
            disk6_id = "disk6",
            disk7_id = "disk7",
            disk8_id = "disk8",
            tower1_id = "tower1",
            tower2_id = "tower2",
            tower3_id = "tower3";

    @Override
    public void goTo() {
        seleniumUtils.browseTo(url);
    }

    @Override
    public void setDisksQuantity(int disksQuantity) {
        this.disksQuantity = disksQuantity;
        CommonDriver.webDriver.switchTo().frame(CommonDriver.webDriver.findElement(By.name(iframe_name)));
        seleniumUtils.clickValueOnDropdown(By.name(diskQtySelector_name), disksQuantity);
    }

    @Override
    public void play() {
        if (disksQuantity % 2 == 0) {
            //Primer movimiento a torre auxiliar
            seleniumUtils.dragAndDrop(By.id(disk1_id), By.id(tower3_id));
        } else {
            //Primer movimiento a torre destino
        }
    }

}
