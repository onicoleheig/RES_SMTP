package ch.heigvd.res.labs_smtp;

import ch.heigvd.res.labs_smtp.config.Config;
import ch.heigvd.res.labs_smtp.model.prank.Prank;
import ch.heigvd.res.labs_smtp.model.prank.PrankGenerator;
import ch.heigvd.res.labs_smtp.smtp.ISmtpClient;
import ch.heigvd.res.labs_smtp.smtp.SmtpClient;
import com.sun.tools.corba.se.idl.InvalidArgument;

import java.io.IOException;
import java.util.Collections;
public class MailRobot {
    public static void main(String[] args) {
        Config config = new Config("/Users/lionelnanchen/Documents/HEIG-VD/Semestre 4/RES/Laboratoire/LaboSMTP/Teaching-HEIGVD-RES-2018-Labo-SMTP-master/RES_LaboSMTP/src/main/resources/", "config.txt"); //"../resources/"
        PrankGenerator prankGenerator = null;

        try {
            prankGenerator = new PrankGenerator(config, config.getNbGroups());
        } catch (InvalidArgument invalidArgument) {
            invalidArgument.printStackTrace();
        }

        ISmtpClient smtpClient = new SmtpClient(config);

        Collections.shuffle(prankGenerator.getPranks());

        for(Prank prank : prankGenerator.getPranks()) {
            try {
                smtpClient.sendMail(prank);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
