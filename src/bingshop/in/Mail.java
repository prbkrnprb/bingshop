package bingshop.in;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {

    private String from;  // GMail user name (just the part before "@gmail.com")
    private String pass; // GMail password

    public Mail() {
        from = "no-reply@bingshop.in";
        pass = "bingMail";
    }

    public void sendFromGMail(String[] to, String[] cc,String subject, String body) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            InternetAddress[] ccAddress = new InternetAddress[cc.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            
            for( int i = 0; i < cc.length; i++ ) {
                ccAddress[i] = new InternetAddress(cc[i]);
            }

            for( int i = 0; i < ccAddress.length; i++) {
                message.addRecipient(Message.RecipientType.CC, ccAddress[i]);
            }

            message.setSubject(subject);
            message.setText(body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}