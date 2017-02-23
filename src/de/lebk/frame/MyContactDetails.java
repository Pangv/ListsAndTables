package de.lebk.frame;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * @author sopaetzel
 */
public class MyContactDetails extends JPanel {


    JLabel lblFullName;
    JLabel lblBirthDate;
    JLabel lblAge;

    JLabel lblTelephone;
    JLabel lblMail;
    JLabel lblCity;
    JLabel lblStreetAddress; // street & street number

    JTextField txtfTelephone;
    JTextField txtfMail;
    JTextField txtfCity;
    JTextField txtfStreetAddress;

    CompoundBorder compoundBorder = new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new LineBorder(Color.lightGray));

    public MyContactDetails() {
        initPanel();

    }

    public void initPanel() {
        this.setLayout(new BorderLayout());
        JPanel pnlDetail = new JPanel();
        pnlDetail.setLayout(new GridLayout(0, 2));
        pnlDetail.setBorder(new EmptyBorder(5, 5, 5, 5));

        lblFullName = new JLabel();
        lblBirthDate = new JLabel();
        lblAge = new JLabel();
        //---
        lblTelephone = new JLabel("Telefonnummer");
        lblMail = new JLabel("Mailadresse");
        lblCity = new JLabel("Stadt");
        lblStreetAddress = new JLabel("Straße / Hausnummer");

        txtfTelephone = new JTextField();
        txtfMail = new JTextField();
        txtfCity = new JTextField();
        txtfStreetAddress = new JTextField();

        txtfTelephone.setBorder(compoundBorder);
        txtfMail.setBorder(compoundBorder);
        txtfCity.setBorder(compoundBorder);
        txtfStreetAddress.setBorder(compoundBorder);

        txtfTelephone.setEnabled(false);
        txtfMail.setEnabled(false);
        txtfCity.setEnabled(false);
        txtfStreetAddress.setEnabled(false);


        pnlDetail.add(this.lblFullName);
        pnlDetail.add(new JLabel(""));
        pnlDetail.add(lblBirthDate);
        pnlDetail.add(lblAge);
        pnlDetail.add(lblTelephone);
        pnlDetail.add(txtfTelephone);
        pnlDetail.add(lblMail);
        pnlDetail.add(txtfMail);
        pnlDetail.add(lblCity);
        pnlDetail.add(txtfCity);
        pnlDetail.add(lblStreetAddress);
        pnlDetail.add(txtfStreetAddress);


        this.add(pnlDetail, BorderLayout.CENTER);


    }


    public void fillDetails(String fullName, String birthDate, String age, String telephone, String mail, String city, String streetAddress) {
        this.lblFullName.setText(fullName);
        this.lblBirthDate.setText(birthDate);
        this.lblAge.setText(age);
        this.txtfTelephone.setText(telephone);
        this.txtfMail.setText(mail);
        this.txtfStreetAddress.setText(streetAddress);


    }
}
