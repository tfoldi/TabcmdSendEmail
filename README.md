# Tableau Tabcmd: sendmail command

Send email with file attachment from Tableau's tabcmd.

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
