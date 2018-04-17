package ch.heigvd.res.labs_smtp.model.mail;

/**
 * This classe represent the message part of the mail (the subject and body content of the mail)
 *
 * @author: Nanchen Lionel, Nicole Olivier
 *
 * @date: 14.04.2018
 */
public class Message {

    private String from;
    private String[] to;
    private String[] cc;
    private String[] bcc;
    private String subject;
    private String body;

    public Message(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Subject:" + subject + "\nBody:" + body;
    }
}
