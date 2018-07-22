package mail;

import domain.SetupDetails;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class MailService {
    private static Session session;
    private SetupDetails setupDetails;
    private File screenshotFile;
    private int numberOfMoves;

    public void sendNew(SetupDetails setupDetails, File screenshotFile, int numberOfMoves) {
        this.setupDetails = setupDetails;
        this.screenshotFile = screenshotFile;
        this.numberOfMoves = numberOfMoves;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");
        session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(setupDetails.getUsername(), setupDetails.getPassword());
                    }
                });
        try {
            Transport.send(setMessage());
            System.out.println("Email sent with no errors...");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Message setMessage() throws MessagingException, IOException {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(setupDetails.getFrom()));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(setupDetails.getTo()));
        message.setSubject("Detalle de juego");

        Multipart multipart = new MimeMultipart();

        MimeBodyPart bodyContentPart = new MimeBodyPart();
        bodyContentPart.setContent(getBodyContentContent(), "text/html; charset=utf-8");
        multipart.addBodyPart(bodyContentPart);

        MimeBodyPart attachmentPart = new MimeBodyPart();
        attachmentPart.attachFile(screenshotFile);
        multipart.addBodyPart(attachmentPart);

        message.setContent(multipart);

        return message;
    }

    private String getBodyContentContent() {
        Format formatter = new SimpleDateFormat("EEEE d 'de' MMMM 'del' yyyy 'a las' h:mma", new Locale("es", "CR"));
        String date = formatter.format(new Date());
        String top = "<html>" +
                "<head>" +
                "    <style type=text/css>" +
                "        body {" +
                "            width: 80%;" +
                "            margin-left: auto;" +
                "            margin-right: auto;" +
                "            font-family: \"Trebuchet MS\", Helvetica, sans-serif;" +
                "        }" +
                "    </style>" +
                "</head>";
        String content = "<body>" +
                "    <h2>Resultados del juego</h2>" +
                "    <p>" +
                "        <b>Fecha: </b>" + date +
                "    </p>" +
                "    <p>" +
                "        <b>Cantidad de discos:</b> " + setupDetails.getDisksQty() + "</p>" +
                "    <p>" +
                "        <b>Total de movimientos:</b> " + numberOfMoves + "</p>" +
                "</body>";
        String bottom =
                "</html>";
        return top + content + bottom;
    }
}