package com.tableausoftware.tabcmd.commands;

import com.tableausoftware.tabcmd.http.HttpRequest;
import com.tableausoftware.tabcmd.session.Session;
import com.tableausoftware.core.util.SMTPClientWithFile;

import javax.mail.MessagingException;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;


public class SendEmail extends Command {

    public String getName() {
        return "sendmail";
    }

    public String getUsage() {
        return "tabcmd sendmail [options]";
    }

    public String getShortDescription() {
        return "Send email from command line using SMTP server.";
    }

    public String getDescription() {
        return getShortDescription();
    }

    public boolean needsSession() {
        return false;
    }

    public HttpRequest execute(Session session, CommandLine commandLine, List<String> remainingArgs) {
        int port = 25;

        if (commandLine.getOptionValue("smtp-port") != null)
            port = Integer.parseInt(commandLine.getOptionValue("smtp-port"));

        try {
            SMTPClientWithFile client = new SMTPClientWithFile(
                    commandLine.getOptionValue("smtp-user"),
                    commandLine.getOptionValue("smtp-passw"),
                    commandLine.getOptionValue("smtp-host"),
                    port,
                    true);

            client.sendMailWithFile(
                    commandLine.getOptionValue("to"),
                    commandLine.getOptionValue("from"),
                    commandLine.getOptionValue("subject"),
                    commandLine.getOptionValue("body"),
                    commandLine.getOptionValue("file"));

        } catch (MessagingException ex) {
            m_logger.debug(ex);
            throw new RuntimeException(ex);
        }
        return null;
    }


    public Options getOptions(Options options) {
        OptionBuilder.withLongOpt("smtp-host");
        OptionBuilder.withDescription("SMTP Server's address");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("SMTPSERVER");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("to");
        OptionBuilder.withDescription("Recipient addresses, separated by semicolon (;) character");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("TO_EMAIL");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("from");
        OptionBuilder.withDescription("From address");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("FROM_EMAIL");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("subject");
        OptionBuilder.withDescription("Subject of the email");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("TO_EMAIL");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("file");
        OptionBuilder.withDescription("File to include");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("FILENAME");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("body");
        OptionBuilder.withDescription("Body Text");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(true);
        OptionBuilder.withArgName("EMAIL_TEXT_BODY");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("smtp-port");
        OptionBuilder.withDescription("SMTP Server's port for incoming communication");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(false);
        OptionBuilder.withArgName("PORT");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("smtp-user");
        OptionBuilder.withDescription("User name for SMTP authentication");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(false);
        OptionBuilder.withArgName("USER");
        options.addOption(OptionBuilder.create());

        OptionBuilder.withLongOpt("smtp-passw");
        OptionBuilder.withDescription("SMTP User's password");
        OptionBuilder.hasArg();
        OptionBuilder.isRequired(false);
        OptionBuilder.withArgName("PASSWORD");
        options.addOption(OptionBuilder.create());

        return options;
    }
}
