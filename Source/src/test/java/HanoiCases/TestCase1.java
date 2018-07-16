package HanoiCases;

import domain.SetupDetails;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.UTERRAPage;
import xml.XMLUtils;

public class TestCase1 {
    private XMLUtils xmlUtils;
    private SetupDetails setupDetails;
    private UTERRAPage uterraPage;

    @BeforeTest
    public void load() {
        //Cargar parámetros del XML, Webdriver e iniciar página
        xmlUtils = new XMLUtils();
        setupDetails = xmlUtils.fetchSetupDetails();
        uterraPage = new UTERRAPage();
        System.out.println("Finished loading resources...");
        uterraPage.goTo();
    }

    @Test
    public void play() {
        //Ejecución principal
        System.out.println("Test execution...");
        uterraPage.setDisksQuantity(setupDetails.getDisksQty());
        uterraPage.play();
    }

    @AfterTest
    public void end() {
        //Cerrar navegador y enviar correo
        //Detalles de correo: cantidad de movimientos realizados, cantidad de discos y un screenshot
        System.out.println("Test finished...");
        uterraPage.close();
    }

}
