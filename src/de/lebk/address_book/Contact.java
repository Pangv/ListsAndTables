package de.lebk.address_book;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.GregorianCalendar;

/**
 * @author sopaetzel
 */
public class Contact {

    // Personal Info
    String firstName;
    String lastName;
    String birthDate;
    byte age;


    // Contact Info
    String telephone;
    String mail;
    String city;
    String street;
    String houseNumber;

    public Contact() {
    }

    public Contact(String firstName, String lastName, String birthDate, byte age, String telephone, String mail, String city, String street, String houseNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.age = age;
        this.telephone = telephone;
        this.mail = mail;
        this.city = city;
        this.street = street;
        this.houseNumber = houseNumber;
    }

    @Override
    @XmlAttribute(name = "attr")
    public String toString() {
        return this.getFullName() + " " + getAge();
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

    public byte getAge() {
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

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
