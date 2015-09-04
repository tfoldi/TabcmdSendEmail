package com.tableausoftware.tabcmd.commands;


import com.tableausoftware.tabcmd.http.HttpRequest;
import com.tableausoftware.tabcmd.session.Session;
import java.util.List;
import org.apache.commons.cli.CommandLine;

public class SendEmail extends Command {

    public String getName() {
        return "sendmail";
    }

    public String getUsage()
    {
        return "tabcmd sendmail -t RECIPIENT -f FROM --server SMTP --";
    }

    public String getShortDescription()
    {
        return "Send email from command line using SMTP server.";
    }

    public String getDescription()
    {
        return getShortDescription();

    }

    public boolean needsSession()
    {
        return false;
    }

    public HttpRequest execute(Session session, CommandLine commandLine, List<String> remainingArgs)
    {

        System.out.print("Hey Ho!");
        return null;
    }
}
