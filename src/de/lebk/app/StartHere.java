package de.lebk.app;

import de.lebk.address_book.AddressBook;
import de.lebk.address_book.Contact;
import de.lebk.data.DataAccess;
import de.lebk.frame.UIFrame;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.text.ParseException;
import java.util.Iterator;

/**
 * @author sopaetzel
 */
public class StartHere {


    public static void main(String[] args) {

        JOptionPane.showMessageDialog(null, "Wähle den Speicherpunkt deines Adressbuchs");
        try {
            AddressBook.setInstance(DataAccess.getInstance().readXML());


            // Just For Tests
            Iterator<Contact> contactIterator = AddressBook.getInstance().getContacts().iterator();
            Contact contact;
            while (contactIterator.hasNext()) {
                contact = contactIterator.next();
                System.out.println(contact.getFullName());
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        AddressBook.getInstance().setTitle("Mein Lieblingsadressbuch");
        new UIFrame(AddressBook.getInstance().getTitle());


    }

}
