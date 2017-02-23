package de.lebk.frame;

import de.lebk.address_book.AddressBook;
import de.lebk.address_book.Contact;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.ParseException;
import java.util.Iterator;

/**
 * @author sopaetzel
 */
public class MyContactTable extends JPanel implements ListSelectionListener {


    JTable tblContacts;
    JScrollPane scrollPaneContacts;

    MyContactDetails myDetailsPanel = new MyContactDetails();

    public MyContactTable() {
        this.setLayout(new GridLayout(0, 2));
        this.setName("Kontakttabelle");
        this.initComponent();

    }


    private void initComponent() {


        MyDefaultTableModel myDefaultTableModel = new MyDefaultTableModel(new Object[][]{}, new Object[]{"Vorname", "Nachname"});
        tblContacts = new JTable(myDefaultTableModel);

        tblContacts.setModel(contactDefaultTableModel());
        tblContacts.setAutoCreateRowSorter(true);
        tblContacts.getSelectionModel().addListSelectionListener(this);

        scrollPaneContacts = new JScrollPane();
        scrollPaneContacts.setViewportView(tblContacts);

        this.add(scrollPaneContacts);
        this.add(myDetailsPanel);
    }

    private MyDefaultTableModel contactDefaultTableModel() {
        MyDefaultTableModel model = (MyDefaultTableModel) tblContacts.getModel();

        Iterator<Contact> contactIterator = AddressBook.getInstance().getContacts().iterator();
        Contact contact;
        while (contactIterator.hasNext()) {
            contact = contactIterator.next();
            model.addRow(new Object[]{contact.getFirstName(), contact.getLastName()});
        }

        return model;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int row = tblContacts.getSelectedRow();
        if (row >= 0) {
            MyDefaultTableModel myDefaultTableModel = (MyDefaultTableModel) tblContacts.getModel();
            Contact contact = (Contact) myDefaultTableModel.getContact(tblContacts.getSelectedRow());
            try {
                myDetailsPanel.fillDetails(contact.getFullName(), contact.getBirthDate(), (contact.getAge() + ""), contact.getTelephone(), contact.getMail(), contact.getCity(), contact.getStreet() + " " + contact.getHouseNumber());
            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(this, "Also wirklich du weißt doch, dass man Exception nicht wirft....");
            }
        }
    }
}

class MyDefaultTableModel extends DefaultTableModel {

    MyDefaultTableModel(Object[][] data, Object[] columnNames) {
        super(data, columnNames);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }


    public Object getContact(int row) {

        Iterator<Contact> contactIterator = AddressBook.getInstance().getContacts().iterator();
        Contact contact;

        while (contactIterator.hasNext()) {
            contact = contactIterator.next();
            if ((getFirstName(row) + " " + getLastName(row)).equalsIgnoreCase(contact.getFullName())) {
                return contact;
            }
        }
        return -100;
    }


    public String getFirstName(int row) {
        String firstName = ((String) getValueAt(row, 0));
        return firstName;
    }

    public String getLastName(int row) {
        String lastName = ((String) getValueAt(row, 1));
        return lastName;
    }
}