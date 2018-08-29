package com.nau.utils;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import java.io.File;
import java.io.IOException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

public class EmailUtility {
    public static void sendEmailWithAttachment(String host, String port,
                                               final String userName, final String password, String toAddress,
                                               String subject, String message, byte[] attachedFile)
            throws AddressException, MessagingException {

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);

        if (attachedFile != null) {

            MimeBodyPart attachmentPart = new MimeBodyPart();

            try {
                DataSource ds = new ByteArrayDataSource(attachedFile, "application/octet-stream");
                attachmentPart = new MimeBodyPart();
                attachmentPart.setDataHandler(new DataHandler(ds));
            }
            catch (Exception e) {
               e.printStackTrace();
            }

            attachmentPart.setFileName("data.zip");
            multipart.addBodyPart(attachmentPart);

        }

        msg.setContent(multipart);

        Transport.send(msg);
    }
}
