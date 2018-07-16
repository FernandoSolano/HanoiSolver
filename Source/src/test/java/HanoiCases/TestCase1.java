package HanoiCases;

import domain.SetupDetails;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import xml.XMLUtils;

public class TestCase1 {
    private XMLUtils xmlUtils;
    private SetupDetails setupDetails;

    @BeforeTest
    public void load(){
        //Cargar parámetros del XML, Webdriver e iniciar página
        xmlUtils = new XMLUtils();
        setupDetails= xmlUtils.fetchSetupDetails();
        System.out.println("Finished loading resources...");

    }

    @Test
    public void play(){
        //Ejecución principal
        System.out.println("Prueba");
    }

    @AfterTest
    public void end(){
        //Cerrar navegador y enviar correo
        //Detalles de correo: cantidad de movimientos realizados, cantidad de discos y un screenshot
        System.out.println("Después");
    }

}
