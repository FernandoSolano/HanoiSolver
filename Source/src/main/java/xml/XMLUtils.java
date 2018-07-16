package xml;

import domain.SetupDetails;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

import static jdk.nashorn.internal.objects.NativeString.trim;

public class XMLUtils {

    public SetupDetails fetchSetupDetails() {
        try {
            return parseSetupDetailsOnDocument(getDocument());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Document getDocument() throws IOException, SAXException, ParserConfigurationException {
        File file = new File("C:/Users/fsolano/Documents/GitHub/HanoiSolver/SampleSetups/Sample3Disks.xml");
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
                .newInstance();
        DocumentBuilder documentBuilder = null;
        Document document = null;
        documentBuilder = documentBuilderFactory.newDocumentBuilder();
        document = documentBuilder.parse(file);
        return document;
    }

    private SetupDetails parseSetupDetailsOnDocument(Document document) throws XPathExpressionException {
        SetupDetails setupDetails = null;

        XPath xPath = XPathFactory.newInstance().newXPath();
        int disksQty = Integer.parseInt(trim((String) xPath.evaluate("/setup/disks", document, XPathConstants.STRING)));

        Node emailNode = document.getElementsByTagName("email").item(0);
        if (emailNode.getNodeType() == Node.ELEMENT_NODE) {
            Element queryElement = (Element) emailNode;
            //Values retrieval
            NodeList usernameList = queryElement.getElementsByTagName("username");
            NodeList passwordList = queryElement.getElementsByTagName("password");
            NodeList fromList = queryElement.getElementsByTagName("from");
            NodeList toList = queryElement.getElementsByTagName("to");
            //Values Assignment
            String username = usernameList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String password = passwordList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String from = fromList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            String to = toList.item(0).getChildNodes().item(0)
                    .getNodeValue();
            setupDetails = new SetupDetails(disksQty, username, password, from, to);
        }
        return setupDetails;
    }

}
