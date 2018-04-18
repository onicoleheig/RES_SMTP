package ch.heigvd.res.labs_smtp.model.prank;

import ch.heigvd.res.labs_smtp.model.mail.Group;
import ch.heigvd.res.labs_smtp.model.mail.Person;
import ch.heigvd.res.labs_smtp.config.Config;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PrankGenerator {
    private Config config;
    private ArrayList<Prank> pranks;
    private Random rand = new Random();
    private static final Logger LOG = Logger.getLogger(Config.class.getName());

    public PrankGenerator(Config config, int nbGroups) {
        this.config = config;

        if(nbGroups < 1 || config.getNbGroups() < 1) {
            LOG.log(Level.SEVERE, "nbGroups can't be lower than 1");
        }
        pranks = new ArrayList<Prank>();

        for(int i = 0; i < nbGroups; ++i) {
            Collections.shuffle(config.getMessages());
            int nbPersons = rand.nextInt(config.getPersons().size() - 2) + 2;
            pranks.add(new Prank(groupeGenerator(nbPersons), config.getMessages().get(0)));
        }
    }

    public ArrayList<Prank> getPranks() {
        return pranks;
    }

    private Group groupeGenerator(int size) {
        ArrayList<Person> receivers = new ArrayList<Person>();
        ArrayList<Person> persons = config.getPersons();
        Collections.shuffle(persons);
        Person sender = persons.get(0);

        for(int i = 1; i < size; ++i) {
            receivers.add(config.getPersons().get(i));
        }
        return new Group(sender, receivers);
    }
}
