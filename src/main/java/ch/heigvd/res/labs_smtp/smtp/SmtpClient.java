package ch.heigvd.res.labs_smtp.smtp;

import ch.heigvd.res.labs_smtp.config.Config;
import ch.heigvd.res.labs_smtp.model.mail.Person;
import ch.heigvd.res.labs_smtp.model.prank.Prank;

import java.io.*;
import java.net.Socket;

public class SmtpClient implements ISmtpClient {

    private Config config;
    private Socket socket;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;

    private static final String LINE_RETURN = "\r\n";
    private static final String EHLO_CMD = "EHLO ";
    private static final String FROM_CMD = "MAIL FROM: ";
    private static final String RCPT_CMD = "RCPT TO: ";
    private static final String DATA_CMD = "DATA" + LINE_RETURN;
    private static final String FROM_DATA = "From: ";
    private static final String TO_DATA = "To: ";
    private static final String CC_DATA = "Cc: ";
    private static final String BCC_DATA = "Bcc: ";
    private static final String SUBJECT_DATA = "Subject: ";
    private static final String END_DATA = "." + LINE_RETURN;
    private static final String QUIT_CMD = "QUIT";


    public SmtpClient(Config config) {
        this.config = config;
    }

    public void sendMail(Prank prank) throws IOException {
        socket = new Socket(config.getServer(), config.getPort());
        printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true );
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        System.out.println(bufferedReader.readLine()); //read the welcome message and display it

        //EHLO command
        System.out.println("print : ");
        printWriter.print(EHLO_CMD + "local" + LINE_RETURN);
        String line = bufferedReader.readLine();
        System.out.println("line : " + line);

        //read all server responses following EHLO command
        while (line.startsWith("250-")) {
            line = bufferedReader.readLine();
            System.out.println(line);
        }

        //FROM command
        printWriter.print(FROM_CMD + "<" + prank.getSender() + ">" + LINE_RETURN);
        System.out.println(bufferedReader.readLine());

        //RCPT command
        for (Person person : prank.getVictimeRecipients()) {
            printWriter.print(RCPT_CMD + "<" + person.getAddress() + ">" + LINE_RETURN);
            System.out.println(bufferedReader.readLine());
        }

        //if the prank include cc
        if (prank.getCc() != null) {
            for (Person person : prank.getCc()) {
                printWriter.print(RCPT_CMD + "<" + person.getAddress() + ">" + LINE_RETURN);
                System.out.println(bufferedReader.readLine());
            }
        }

        //if the prank include bcc
        if (prank.getBcc() != null) {
            for (Person person : prank.getBcc()) {
                printWriter.print(RCPT_CMD + "<" + person.getAddress() + ">" + LINE_RETURN);
                System.out.println(bufferedReader.readLine());
            }
        }

        //starting the DATA command
        printWriter.print(DATA_CMD);
        line = bufferedReader.readLine();
        System.out.println(line);

        //from information
        printWriter.print(FROM_DATA + prank.getSender().getAddress() + LINE_RETURN);

        //to information(s)
        String text = TO_DATA;
        boolean comma = false;
        for (Person person : prank.getVictimeRecipients()) {
            if (comma) text += ", " ;
            text += person.getAddress();
            comma = true;
        }
        printWriter.print(text + LINE_RETURN);


        //cc information(s)
        if (prank.getCc() != null) {
            text = CC_DATA;
            comma = false;
            for (Person person : prank.getCc()) {
                if (comma) text += ", " ;
                text += person.getAddress();
                comma = true;
            }
            printWriter.print(text + LINE_RETURN);
        }

        //cc information(s)
        if (prank.getBcc() != null) {
            text = BCC_DATA;
            comma = false;
            for (Person person : prank.getBcc()) {
                if (comma) text += ", " ;
                text += person.getAddress();
                comma = true;
            }
            printWriter.print(text + LINE_RETURN);
        }

        //subject information
        printWriter.print(SUBJECT_DATA + prank.getSubject() + LINE_RETURN + LINE_RETURN);

        //body text
        printWriter.print(prank.getBody() + LINE_RETURN);
        printWriter.print(END_DATA);
        line = bufferedReader.readLine();
        System.out.println(line);
        printWriter.print(QUIT_CMD + LINE_RETURN);
        printWriter.close();
        bufferedReader.close();
        socket.close();
    }
}

