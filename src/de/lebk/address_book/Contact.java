package de.lebk.address_book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @author sopaetzel
 */
public class Contact {

    // Personal Info
    String firstName;
    String lastName;
    String birthDate;
    int age;


    // Contact Info
    String telephone;
    String mail;
    String city;
    String street;
    int houseNumber;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String birthDate, String telephone, String mail, String city, String street, int houseNumber) throws ParseException {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = getAge();
        this.telephone = telephone;
        this.mail = mail;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return this.getFullName();
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public int getAge() throws ParseException {
        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Date date = df.parse(birthDate);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);

        int currentDate = new GregorianCalendar().get(Calendar.DATE);
        int birthDate = calendar.get(Calendar.DATE);

        age = (currentDate - birthDate);
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }
}
