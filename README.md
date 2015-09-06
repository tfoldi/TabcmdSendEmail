# Tableau Tabcmd: sendmail command

Send email with file attachment from Tableau's tabcmd.

## Options

After installation just type `tabcmd help sendmail` to obtain command line options:

    c:\Program Files\Tableau\Tableau Server\9.0\extras\Command Line Utility>tabcmd h
    elp sendmail
    Tableau Server Command Line Utility -- 9000.15.0304.1722

    Send email from command line using SMTP server.

    tabcmd sendmail [options]

    Command options:
           --body <EMAIL_TEXT_BODY>    Body Text
           --file <FILENAME>           File to include
           --from <FROM_EMAIL>         From address
           --smtp-host <SMTPSERVER>    SMTP Server's address
           --smtp-passw <PASSWORD>     SMTP User's password
           --smtp-port <PORT>          SMTP Server's port for incoming
                                       communication
           --smtp-user <USER>          User name for SMTP authentication
           --subject <TO_EMAIL>        Subject of the email
           --to <TO_EMAIL>             Recipient addresses, separated by comma
                                       (,) character

## Usage

To send email with file attachement use the following commmand line

    c:\Program Files\Tableau\Tableau Server\9.0\extras\Command Line Utility> tabcmd sendmail 
      --smtp-host smtp.gmail.com --smtp-user tfoldi@domain.net --smtp-passw <your password> 
      --body "lololo" --file "<path to file>" --from tfoldi@domain.net --to tfoldi@domain.net 
      --subject hello

The `--smtp-user`, `--smtp-passw` and `--port` command line options are optional. 

## Installation

Practically you need to copy the two class files into `app-tabcmd-latest-jar.jar`, located in the `lib` folder.

 
## Disclaimer 

Code is absolutley unsupported and it has nothing to do with tableau software.  Only publicly available interfaces were used from other libraries. 
The code is unlicensed (public domain).
