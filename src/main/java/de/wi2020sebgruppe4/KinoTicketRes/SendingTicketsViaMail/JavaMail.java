package de.wi2020sebgruppe4.KinoTicketRes.SendingTicketsViaMail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class JavaMail {

    private static Message prepareMessage(Session session, String myAccount, String empfaenger){
        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myAccount));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(empfaenger));
            message.setSubject("Buchungsbestätigung Kinoticket");

            //message.setText("Das ist eine Test Mail. Hat es geklappt?");


            // Multipart-Message ("Wrapper") erstellen
            Multipart multipart = new MimeMultipart();
            // Body-Part setzen:
            BodyPart messageBodyPart = new MimeBodyPart();
            // Textteil des Body-Parts
            messageBodyPart.setText("Lieber Kunde, \n \nvielen Dank für Ihre Buchung! \n \n" +
                    "Viel Spaß wünscht Ihnen Gruppe 4");
            // Body-Part dem Multipart-Wrapper hinzufügen
            multipart.addBodyPart(messageBodyPart);
            // Message fertigstellen, indem sie mit dem Multipart-Content ausgestattet wird
            message.setContent(JavaMailHtml.getJavaMailHtml(), "text/html");

            return message;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void sendMail(String empfaenger) throws Exception{

        Properties properties = new Properties();
        properties.put("mail.smtp.auth",  "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccount = "noreply.kinores@gmail.com";
        String myPassword = "buwni4-nixvoc-xydjUt";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccount, myPassword);
            }
        });

        // Message-Objekt erzeugen und senden!

        Message message = prepareMessage(session, myAccount, empfaenger);
        Transport.send(message); // E-Mail senden!
    }
}
