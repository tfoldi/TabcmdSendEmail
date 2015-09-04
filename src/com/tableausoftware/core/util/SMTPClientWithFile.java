package com.tableausoftware.core.util;

import com.google.common.base.Preconditions;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;


public class SMTPClientWithFile extends com.tableausoftware.core.util.SMTPClient {
    public SMTPClientWithFile(String user, String password, String smtpHost, Integer port, boolean sslEnabled) {
        super(user, password, smtpHost, port, sslEnabled);
    }

    /**
     * Sends a fancy email with attachment using Tableau's SMTPClient class
     *
     * @param to       coma separated list of recipients
     * @param from     the guy's address in form field
     * @param subject  subject
     * @param textBody the body, sent in UTF-8 format
     * @param file     File to attache
     * @throws MessagingException
     */
    public void sendMailWithFile(String to, String from, String subject, String textBody, String file)
            throws MessagingException {
        Preconditions.checkArgument((to != null) && (to.length() > 0), "there must be at least one 'to' address");

        InternetAddress[] toAddresses = InternetAddress.parse(to);
        InternetAddress fromAddress = new InternetAddress(from);

        Session session = setupSession(fromAddress);
        MimeMessage message = new MimeMessage(session);

        message.setFrom(fromAddress);
        message.setRecipients(Message.RecipientType.TO, toAddresses);
        message.setSubject(subject, "UTF-8");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        // Now set the actual message
        messageBodyPart.setText(textBody, "UTF-8");

        // Create a multipart message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        messageBodyPart = new MimeBodyPart();
        String filename = file;
        DataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);

        // Send the complete message parts
        message.setContent(multipart);

        sendMessage(session, message);
    }
}
