package TestCases;

import domain.SetupDetails;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.MenaPage;
import xml.XMLUtils;

public class TestCase2 {
    private XMLUtils xmlUtils;
    private SetupDetails setupDetails;
    private MenaPage page;
    private static final String
            path1 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample1Disks.xml",
            path2 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample2Disks.xml",
            path3 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample3Disks.xml",
            path4 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample4Disks.xml",
            path5 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample5Disks.xml",
            path6 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample6Disks.xml",
            path7 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample7Disks.xml",
            path8 = "C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample8Disks.xml";

    @BeforeTest
    public void load() {
        //Cargar parámetros del XML, Webdriver e iniciar página
        xmlUtils = new XMLUtils();
        setupDetails = xmlUtils.fetchSetupDetails(path4);
        page = new MenaPage(setupDetails.getDisksQty());
        System.out.println("Finished loading resources...");
        page.goTo();
    }

    @Test
    public void play() {
        //Ejecución principal
        System.out.println("Test execution...");
        page.setDisksQuantity(setupDetails.getDisksQty());
        page.play();
    }

    @AfterTest
    public void end() {
        //Cerrar navegador y enviar correo
        //Detalles de correo: cantidad de movimientos realizados, cantidad de discos y un screenshot
        System.out.println("Test finished...");
        //page.takeScreenshot();
        //page.close();
    }

}
