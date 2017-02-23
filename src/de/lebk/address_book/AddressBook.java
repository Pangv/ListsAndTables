package de.lebk.address_book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author sopaetzel
 */
@XmlRootElement
public class AddressBook {

    private static AddressBook instance = null;

    private String title;

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

    public static void setInstance(AddressBook instance) {
        AddressBook.instance = instance;
    }

    public void addContact(String firstName, String lastName, String birthDate, String telephone, String mail, String city, String street, int streetNumber) throws ParseException {
        contacts.add(new Contact(firstName, lastName, birthDate, telephone, mail, city, street, streetNumber));
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



}
