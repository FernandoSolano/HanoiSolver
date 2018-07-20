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

    public UTERRAPage() {
        disks = new LinkedList<>();
        for (int i = 0; i <= 8; i++) {
            disks.add(new Disk(i, "disk" + i));
        }
    }

    @Override
    public void goTo() {
        seleniumUtils.browseTo(url);
    }

    @Override
    public void setDisksQuantity(int disksQuantity) {
        this.disksQuantity = disksQuantity;
        CommonDriver.webDriver.switchTo().frame(CommonDriver.webDriver.findElement(By.name(iframe_name)));
        seleniumUtils.clickValueOnDropdown(By.name(diskQtySelector_name), disksQuantity, true);
    }

    @Override
    public void play() {
        /*if (disksQuantity % 2 == 0) {
            //Primer movimiento a torre auxiliar*/
        makeMove(disks.get(1), tower3_id);
        makeMove(disks.get(2), tower2_id);
        makeMove(disks.get(1), tower2_id);
        makeMove(disks.get(3), tower3_id);
        makeMove(disks.get(1), tower1_id);
        makeMove(disks.get(2), tower3_id);
        makeMove(disks.get(1), tower3_id);
        /*} else {
            //Primer movimiento a torre destino
        }*/
    }

    @Override
    public void makeMove(Disk disk, String tower) {
        seleniumUtils.dragAndDrop(By.id(disk.getRoute()), By.id(tower));
    }

}
