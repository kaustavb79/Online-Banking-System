package bank.connect_dao;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {
    //call method to send mail 
    public static void sendMail(String host,String port,String sender_mail,String sender_mail_pass,String reciever_mail,String subject,String message) throws AddressException,MessagingException, UnsupportedEncodingException{
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true"); 
        Authenticator auth = new Authenticator() {
            @Override
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender_mail, sender_mail_pass);
            }
        }; 
        Session session = Session.getInstance(properties, auth);        
        Message msg = new MimeMessage(session); 
        msg.setFrom(new InternetAddress(sender_mail,"bank_online"));
        InternetAddress[] toAddresses = { new InternetAddress(reciever_mail) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(message,"text/html");
        Transport.send(msg);
        System.out.println("Mail Sent to : "+reciever_mail);
    }
}
