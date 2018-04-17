package ch.heigvd.res.labs_smtp.model.prank;

import ch.heigvd.res.labs_smtp.model.mail.Group;
import ch.heigvd.res.labs_smtp.model.mail.Message;
import ch.heigvd.res.labs_smtp.model.mail.Person;

import java.util.ArrayList;

public class Prank {
    Message msg;
    Group group;

    public Prank(Group groupe, Message msg) {
        this.group = groupe;
        this.msg = msg;
    }

    public Person getSender() {
        return group.getSender();
    }

    public ArrayList<Person> getVictimeRecipients() {
        return group.getMembers();
    }

    public String getSubject() {
        return msg.getSubject();
    }

    public String getBody() {
        return msg.getBody();
    }

    public ArrayList<Person> getCc() {
        return group.getCc();
    }

    public ArrayList<Person> getBcc() {
        return group.getBcc();
    }

    @Override
    public String toString() {
        return group + "\n" + msg;
    }
}
