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
public class MyContactList extends JPanel {

    JList myLittleList;
    JScrollPane scrollPaneList;

    MyContactDetails myContactDetails = new MyContactDetails();

    public MyContactList() {
        this.setLayout(new GridLayout(0, 2));
        this.setName("Kontaktliste");

        this.initComponent();

        this.add(scrollPaneList);
        this.add(myContactDetails);
    }


    private void initComponent() {
        myLittleList = new JList();
        scrollPaneList = new JScrollPane();

        myLittleList.setModel(getMyModel());

        scrollPaneList.setViewportView(myLittleList);


        myLittleList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                Contact contact =  (Contact) myLittleList.getSelectedValue();
                try {
                    myContactDetails.fillDetails(contact.getFullName(), contact.getBirthDate(), (contact.getAge() + ""),
                            contact.getTelephone(), contact.getMail(),
                            contact.getCity(), contact.getStreet() + " " + contact.getHouseNumber());
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Also wirklich du weißt doch, dass man Exception nicht wirft....");
                }

            }
        });

    }

    private DefaultListModel getMyModel() {
        DefaultListModel model = new DefaultListModel();



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


}
