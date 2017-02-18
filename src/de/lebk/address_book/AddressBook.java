package de.lebk.address_book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sopaetzel
 */
@XmlRootElement
public class AddressBook {

    private static AddressBook instance = null;
    private String title = "Mein kleines Adressbuch";

    @XmlElementWrapper(name = "contacts")
    @XmlElement(name = "contact")
    private List<Contact> contacts = new ArrayList<>();

    private AddressBook() {
    }


    public static AddressBook getInstance() {
        if (instance == null) {
            instance = new AddressBook();
        }
        return instance;

    }

    public void addContact(String firstName, String lastName, String birthDate, byte age) {
        contacts.add(new Contact());
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }


    @XmlAttribute
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContact(int index) {
        return contacts.get(index);
    }

    //TODO return the Contact which equals a clicked "Firstname and Lastname" combo

}
