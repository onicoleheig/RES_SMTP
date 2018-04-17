package ch.heigvd.res.labs_smtp;

import ch.heigvd.res.labs_smtp.config.Config;
import ch.heigvd.res.labs_smtp.model.prank.Prank;
import ch.heigvd.res.labs_smtp.model.prank.PrankGenerator;
import ch.heigvd.res.labs_smtp.smtp.ISmtpClient;
import ch.heigvd.res.labs_smtp.smtp.SmtpClient;

import java.io.IOException;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MailRobot {
    private static final Logger LOG = Logger.getLogger(Config.class.getName());

    public static void main(String[] args) {
        Config config = new Config("/config.txt"); //"../resources/"
        PrankGenerator prankGenerator = null;

        try {
            prankGenerator = new PrankGenerator(config, config.getNbGroups());
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
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
