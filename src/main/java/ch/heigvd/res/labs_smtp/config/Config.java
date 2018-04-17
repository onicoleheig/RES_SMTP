package ch.heigvd.res.labs_smtp.config;

import ch.heigvd.res.labs_smtp.model.mail.Message;
import ch.heigvd.res.labs_smtp.model.mail.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Config {
    private static final Logger LOG = Logger.getLogger(Config.class.getName());
    private static final String LINE_RETURN = "\r\n";
    private String server;
    private int port;
    private int nbGroups;
    private String addressesFile;
    private String messagesFile;
    private String folder;

    private final String SUBJECT_TAG = "Subject:";
    private final String MESSAGE_TAG = "Message:";
    private final String SEPARATOR_TAG = "-.-";

    private ArrayList<Person> persons = new ArrayList<Person>();
    private ArrayList<Message> messages = new ArrayList<Message>();

    public Config(String file) {
        this.folder = folder;
        BufferedReader bufferedReader = null;

        //lecture du fichier file dans le dossier folder
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(file), "UTF8"));
            server = bufferedReader.readLine();
            port = Integer.parseInt(bufferedReader.readLine());
            nbGroups = Integer.parseInt(bufferedReader.readLine());
            addressesFile = "/" + bufferedReader.readLine();
            messagesFile = "/" + bufferedReader.readLine();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String address = "";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(addressesFile), "UTF8"));
            while ((address = bufferedReader.readLine()) != null) {
                persons.add(new Person(address));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String subject = "";
        String body = "";
        String line = "";
        String tmp = "";
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(Config.class.getResourceAsStream(messagesFile), "UTF8"));
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith(SUBJECT_TAG)) {
                    subject = bufferedReader.readLine();
                } else if (line.startsWith(MESSAGE_TAG)) {
                    while(!(tmp = bufferedReader.readLine()).equals(SEPARATOR_TAG)) {
                        body += tmp + LINE_RETURN;
                    }
                    messages.add(new Message(subject, body));
                    subject = "";
                    body = "";
                    tmp = "";
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String getServer() {
        return server;
    }

    public int getPort() {
        return port;
    }

    public int getNbGroups() {
        return nbGroups;
    }

    public ArrayList<Person> getPersons() {
        return persons;
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

}
