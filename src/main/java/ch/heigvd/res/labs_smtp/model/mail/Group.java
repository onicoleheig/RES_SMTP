package ch.heigvd.res.labs_smtp.model.mail;

import java.util.ArrayList;

public class Group {
    private Person sender;
    private ArrayList<Person> cc;
    private ArrayList<Person> bcc;
    private ArrayList<Person> members = new ArrayList();

    public Group(Person sender, ArrayList<Person> members) {
        this.sender = sender;
        this.members = members;
    }

    public Person getSender() {
        return sender;
    }

    public void addMembers(Person person) {
        members.add(person);
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public ArrayList<Person> getCc() {
        return cc;
    }

    public void setCc(ArrayList<Person> cc) {
        this.cc = cc;
    }

    public ArrayList<Person> getBcc() {
        return bcc;
    }

    public void setBcc(ArrayList<Person> bcc) {
        this.bcc = bcc;
    }

    @Override
    public String toString() {
        String s = "From:" + sender.getAddress();
        if (members != null) {
            s += "\nTO:";
            for (Person p : members) s += p.getAddress() + ",";
        }
        if (cc != null) {
            s += "\nCC:";
            for (Person p : cc) s += p.getAddress() + ",";
        }
        if (bcc != null) {
            s += "\nBCC:";
            for (Person p : bcc) s += p.getAddress() + ",";
        }
        return s;
    }
}
