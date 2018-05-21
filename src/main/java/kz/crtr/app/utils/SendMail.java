//package kz.crtr.skd.utils;
//
//import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
///**
// *
// * @author User
// */
//public class SendMail {
//
//    public static void sendMail(String to_email_address, String text) {
//
//        final String mailUsername = "bkudaibergenov@gmail.com";
//        final String mailPassword = "Ghjuhfvvf";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(mailUsername, mailPassword);
//                    }
//                });
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("bkudaibergenov@gmail.com"));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to_email_address));
//            message.setSubject("Ситема контроля доступа к информационным системам и информационным ресурсам");
//            message.setText(text);
//            Transport.send(message);
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
