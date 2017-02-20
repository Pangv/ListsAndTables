package de.lebk.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author sopaetzel
 */
public class MyContactList extends JPanel {

    public MyContactList() {
        this.setLayout(new GridLayout(0, 2));
        this.setName("Kontaktliste");
        this.add(new JButton("Hier die Liste platzieren"));
        this.add(new MyContactDetails());
    }

    // TODO add a basic corpus for the list-creation
}
