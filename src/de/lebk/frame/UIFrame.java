package de.lebk.frame;

import de.lebk.address_book.AddressBook;
import de.lebk.data.DataAccess;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;

/**
 * @author sopaetzel
 */
public class UIFrame extends JFrame {

    private final int initWidth = 1000;
    private final int initHeight = initWidth / 16 * 9;
    private final Dimension initDimension = new Dimension(initWidth, initHeight);

    public UIFrame(String title) throws HeadlessException {
        super(title);

        this.initActionListener();
        this.initFrame();

        this.pack();
        this.setVisible(true);
    }


    private void initFrame() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        this.setJMenuBar(new Menu());
        //this.setPreferredSize(initDimension);
        JTabbedPane tabbedPaneFrame = new JTabbedPane();
        this.setContentPane(tabbedPaneFrame);
        tabbedPaneFrame.add(new MyContactList());
        tabbedPaneFrame.add(new MyContactTable());
    }

    private void initActionListener() {
        JFrame frame = this;
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onCloseOperation(frame);
            }
        });

    }

    private void onCloseOperation(JFrame frame) {
        try {
            DataAccess.getInstance().writeXML(AddressBook.getInstance());
            frame.dispose();
            System.exit(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Wurde keine Datei gewählt? " + e.getMessage());
        } catch (JAXBException e) {
            e.printStackTrace();

        }


    }


}

// inner Classes
class Menu extends JMenuBar {

    private JMenuItem mnuiLoad;
    private JMenuItem mnuiSave;
    private JButton btnCreateContact;

    Menu() {
        JMenu mnuFile = new JMenu("Datei");
        mnuiLoad = new JMenuItem("Laden");
        mnuiSave = new JMenuItem("Sichern");
        btnCreateContact = new JButton("Kontakt anlegen");
        btnCreateContact.setFocusPainted(false);

        mnuFile.add(mnuiLoad);
        mnuFile.add(mnuiSave);
        this.add(mnuFile);
        this.add(Box.createHorizontalGlue());
        this.add(btnCreateContact);
        this.initActionListener();
    }

    private void initActionListener() {

        mnuiLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataAccess.getInstance().readXML();
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
            }
        });

        mnuiSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    DataAccess.getInstance().writeXML(AddressBook.getInstance());
                } catch (JAXBException e1) {
                    e1.printStackTrace();
                }
            }
        });

        btnCreateContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    new NewContactDialog();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}

class NewContactDialog extends JDialog {

    private JTextField txtfFirstName;
    private JTextField txtfLastName;
    private JTextField txtfMail;
    private JTextField txtfCity;
    private JTextField txtfStreet;
    private JFormattedTextField ftxtfBirthDate;
    private JFormattedTextField ftxtfTelephone;
    private JComboBox<Integer> cbxNumber;

    private JButton btnCreateContact;

    NewContactDialog() throws ParseException {
        JPanel pnlDialog = new JPanel();
        pnlDialog.setLayout(new GridLayout(0, 2));
        pnlDialog.setBorder(new EmptyBorder(5, 5, 5, 5));

        this.setModal(true);
        this.setTitle("Neuen Kontakt anlegen");
        this.setContentPane(pnlDialog);

        JLabel lblFirstName = new JLabel("Vorname");
        JLabel lblLastName = new JLabel("Nachname");
        JLabel lblBirthDate = new JLabel("Geburtstag");
        JLabel lblTelephone = new JLabel("Telefonnummer");
        JLabel lblMail = new JLabel("Mailadresse");
        JLabel lblCity = new JLabel("Stadt");
        JLabel lblStreet = new JLabel("Straße");
        JLabel lblNumber = new JLabel("Hausnummer");

        txtfFirstName = new JTextField();
        txtfLastName = new JTextField();
        txtfMail = new JTextField();
        txtfCity = new JTextField();
        txtfStreet = new JTextField();

        ftxtfBirthDate = new JFormattedTextField(new MaskFormatter("##.##.####"));
        ftxtfTelephone = new JFormattedTextField(new MaskFormatter("####-##-##-##-##"));

        cbxNumber = new JComboBox<>(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20});

        btnCreateContact = new JButton("Kontakt anlegen");

//
        pnlDialog.add(lblFirstName);
        pnlDialog.add(txtfFirstName);
//
        pnlDialog.add(lblLastName);
        pnlDialog.add(txtfLastName);
//
        pnlDialog.add(lblBirthDate);
        pnlDialog.add(ftxtfBirthDate);
//
        pnlDialog.add(lblTelephone);
        pnlDialog.add(ftxtfTelephone);
//
        pnlDialog.add(lblMail);
        pnlDialog.add(txtfMail);
//
        pnlDialog.add(lblCity);
        pnlDialog.add(txtfCity);
//
        pnlDialog.add(lblStreet);
        pnlDialog.add(txtfStreet);
//
        pnlDialog.add(lblNumber);
        pnlDialog.add(cbxNumber);
//
        pnlDialog.add(new JLabel());
        pnlDialog.add(btnCreateContact);

        this.pack();
        this.initActionListener();
        this.setResizable(false);
        this.setVisible(true);
    }


    private void initActionListener() {
        btnCreateContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    AddressBook.getInstance().addContact(txtfFirstName.getText(), txtfLastName.getText(), ftxtfBirthDate.getText(), ftxtfTelephone.getText(), txtfMail.getText(), txtfCity.getText(), txtfStreet.getText(), (int) cbxNumber.getSelectedItem());
                    DataAccess.getInstance().writeXML(AddressBook.getInstance());
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
