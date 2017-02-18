package de.lebk.app;

import de.lebk.address_book.AddressBook;
import de.lebk.address_book.Contact;
import de.lebk.frame.UIFrame;

/**
 * @author sopaetzel
 */
public class StartHere {

    public static void main(String[] args) {

        AddressBook.getInstance().setTitle("Mein Lieblingsadressbuch");

        Contact c1 = new Contact("Fritz", "Lang", "01.01.1998", new Byte("20"), "123 123 123 123", "fritz@lang.de", "Muenster", "Bahnhof Straße", "2");
        Contact c2 = new Contact("Thomas", "Mann", "01.01.1858", new Byte("100"), "987 988 998 556", "thomas@mann.de", "Stuttgart", "Bahnhof Allee", "42");

        AddressBook.getInstance().addContact(c1);
        AddressBook.getInstance().addContact(c2);

        new UIFrame(AddressBook.getInstance().getTitle());

    }

}
