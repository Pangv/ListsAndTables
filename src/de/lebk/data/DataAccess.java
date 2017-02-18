package de.lebk.data;

import de.lebk.address_book.AddressBook;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author sopaetzel
 */
public class DataAccess {

    private static DataAccess instance = null;
    private static JAXBContext jaxbContext;

    private final String PATH = System.getProperty("user.home");
    private final File ADDRESS_BOOK_XML = openFile();

    private DataAccess() {

    }

    public static DataAccess getInstance() throws JAXBException {
        if (instance == null) {
            instance = new DataAccess();
            jaxbContext = JAXBContext.newInstance(AddressBook.class);
        }
        return instance;
    }


    public void writeXML(Object element) throws JAXBException {
        System.out.println("writing");
        Marshaller marshaller = jaxbContext.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(element, ADDRESS_BOOK_XML);
    }

    public AddressBook readXML() throws JAXBException {
        System.out.println("reading... ");
        StreamSource xml = new StreamSource(ADDRESS_BOOK_XML);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        JAXBElement<AddressBook> rootElement = unmarshaller.unmarshal(xml, AddressBook.class);
        return rootElement.getValue();
    }


    private File openFile() throws NullPointerException {
        File directory = new File(PATH);
        File file = null;

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(directory);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Adressbuch", "xml"));
        jFileChooser.showSaveDialog(null);

        if (!jFileChooser.getSelectedFile().exists()) {
            try {
                file = chooseFile(jFileChooser);
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Das XML konnte nicht erstellt werden", "JAXB Fehler", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            file = jFileChooser.getSelectedFile();
        }
        return file;
    }

    private File chooseFile(JFileChooser jFileChooser) throws IOException {
        FileWriter fw;
        //noinspection ResultOfMethodCallIgnored
        jFileChooser.getSelectedFile().createNewFile();
        File file = jFileChooser.getSelectedFile();
        fw = new FileWriter(file);
        fw.write("<addressBook></addressBook>");
        fw.close();

        return file;
    }


}
