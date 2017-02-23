package de.lebk.frame;

import de.lebk.address_book.AddressBook;
import de.lebk.address_book.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.Iterator;

/**
 * @author sopaetzel
 */
public class MyContactList extends JPanel implements ListSelectionListener {

    JList myLittleList;
    JScrollPane scrollPaneList;

    public MyContactList() {
        this.setLayout(new GridLayout(0, 2));
        this.setName("Kontaktliste");
        this.add(scrollPaneList);
        this.add(new MyContactDetails());
    }


    private void initComponent() {
        myLittleList = new JList();
        scrollPaneList = new JScrollPane();

        myLittleList.setModel(getMyModel());

        scrollPaneList.setViewportView(myLittleList);
    }

    private DefaultListModel getMyModel() {
        DefaultListModel model = (DefaultListModel) myLittleList.getModel();

        //for Each
//        for(Contact contact : AddressBook.getInstance().getContacts()){
//
//        }

        Iterator<Contact> contactIterator = AddressBook.getInstance().getContacts().iterator();
        Contact contact;
        while (contactIterator.hasNext()) {
            contact = contactIterator.next();

            model.addElement(contact);
        }
        return model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {

    }
}
