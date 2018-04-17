package ch.heigvd.res.labs_smtp.smtp;

import ch.heigvd.res.labs_smtp.model.prank.Prank;

import java.io.IOException;

public interface ISmtpClient {
    abstract void sendMail(Prank prank) throws IOException;
}
