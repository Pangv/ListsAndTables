package de.lebk.frame;

import javax.swing.*;
import java.awt.*;

/**
 * @author sopaetzel
 */
public class MyContactTable extends JPanel {

    public MyContactTable() {
        this.setLayout(new GridLayout(0, 2));
        this.setName("Kontakttabelle");
        this.add(new JButton("Hier die Tabelle platzieren"));
        this.add(new MyContactDetails());
    }

    /* TODO add a basic corpus for the table-creation; add table fill
     */
}
