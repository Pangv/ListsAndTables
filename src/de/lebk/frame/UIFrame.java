package de.lebk.frame;

import de.lebk.address_book.AddressBook;
import de.lebk.data.DataAccess;

import javax.swing.*;
import javax.xml.bind.JAXBException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author sopaetzel
 */
public class UIFrame extends JFrame {


    public UIFrame(String title) throws HeadlessException {
        super(title);
        this.setJMenuBar(new Menu());

        this.pack();
        this.setVisible(true);
    }


    private void initFrame() {
        JTabbedPane tabbedPaneFrame = new JTabbedPane();


        this.setContentPane(tabbedPaneFrame);
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
            if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(frame, "Wollen Sie Ihre Änderungen vor dem Schließen speichern?", "Ungespeicherte Änderungen", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) {
                DataAccess.getInstance().writeXML(AddressBook.getInstance());
                System.out.println("Closing with saving");
            } else {
                System.out.println("Closing without saving");
            }
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

    }

}

class NewContactDialog extends JDialog {

    public NewContactDialog() {
        this.setModal(true);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JButton btnCreateContact = new JButton("Kontakt anlegen");


        this.add(btnCreateContact);

    }
}


