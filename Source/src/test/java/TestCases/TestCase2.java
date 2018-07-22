package TestCases;

import domain.SetupDetails;
import mail.MailService;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pageObjects.MenaPage;
import xml.XMLUtils;

public class TestCase2 {
    private XMLUtils xmlUtils;
    private SetupDetails setupDetails;
    private MenaPage page;
    private MailService mailService;
    private int numberOfMoves;
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
        //Load XML and Webdriver settings and browse to page
        xmlUtils = new XMLUtils();
        mailService = new MailService();
        setupDetails = xmlUtils.fetchSetupDetails(path3);
        page = new MenaPage(setupDetails.getDisksQty());
        System.out.println("Finished loading resources...");
        page.goTo();
    }

    @Test
    public void play() {
        //Main Execution
        System.out.println("Test execution...");
        page.setDisksQuantity();
        numberOfMoves = page.play();
    }

    @AfterTest
    public void end() {
        //Close browser and send email
        System.out.println("Test finished...");
        mailService.sendNew(setupDetails, page.takeScreenshot(), numberOfMoves);
        page.close();
    }

}
