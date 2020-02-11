import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class app {


    // Stores contact objects
    private static List<people> contactObjects = new ArrayList<>();
    // Stores contacts objects as list
    private static List<String> contactsList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    // Paris- I moved the main here because it was getting difficult to manage the variables above and the methods in a separate file. We can refactor later if we want to make it cleaner!
    public static void main(String[] args) throws IOException {
        app app = new app();

        newFile();
        // Creating filler contacts
        people contact1 = new people("Test", "1234567890");
        people contact2 = new people("Jane", "0987654321");
        // Converting the object to a string using a method from the constructor class
//        contactsList.add(contact1.contactString());
        contactObjects.add(contact1);
        System.out.println("Contact objects: " + contactObjects);

        // Sets empty list equal to lines of strings in txt file at app start
        contactsList = txtToString();

//        stringToObject();

        int choice = 6;
        do {
            System.out.println();
            System.out.println("What would you like to do?");
            System.out.println("");
            System.out.println("\t1 - View contacts");
            System.out.println("\t2 - Add new contacts");
            System.out.println("\t3 - Search a contact by name");
            System.out.println("\t4 - Delete a contact by name");
            System.out.println("\t5 - Edit a contact by name or number");
            System.out.println("\t6 - Exit");

            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException nfe) {
//                continue;
                break;
            }

            switch (choice) {
                case 1:
                    readContacts();
                    break;
                case 2:
                    app.addContact();
                    break;
                case 3:
                    searchContacts();
                    break;
//                case 4:
////                    deleteContact();
//                    break;
//                case 5:
                default:
                   break;
            }

        } while (choice != 6);
        System.out.println("Goodbye");





//        app.deleteContact();
//        app.writeContacts();
//        app.editContact();


    }


    void addContact() throws IOException {
        String nameInput;
        String numberInput;
        System.out.println("Enter contact Name: ");
        nameInput = scanner.next();
        System.out.println("Enter contact Number: ");
        numberInput = scanner.next();
        people newContact = new people(nameInput, numberInput);
        contactObjects.add(newContact);
        contactsList.add(newContact.contactString());
        Files.write(
                Paths.get("contacts", "contacts.txt"),
                Arrays.asList(newContact.contactString()),
                StandardOpenOption.APPEND
        );
        System.out.println("Contact objects: " + contactObjects);
        System.out.println("Contact list: " + contactsList);
    }//done//


    static void newFile() {

        String directory = "contacts";
        String filename = "contacts.txt";
        Path dataDirectory = Paths.get(directory);
        Path dataFile = Paths.get(directory, filename);

        try {
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
                System.out.println("Directory created");
            }
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
                System.out.println("File created");
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Something went wrong");
        }
    }//done//

    // Reformulated to work with the people objects!!
    static void readContacts() {
        List<String> contacts = null;

        try {
            Path addressBook = Paths.get("contacts", "contacts.txt");
            contacts = Files.readAllLines(addressBook);
            for (String contact : contacts) {
                String[] contactArray = contact.split(" | ");
                System.out.println(Arrays.toString(contactArray));
                people contactObj = new people(contactArray[0], contactArray[2]);
                contactObjects.add(contactObj);
            }
            System.out.println(".txt file:" + contacts);
            System.out.println("Contact objects: " + contactObjects);
            System.out.println("Contact list: " + contactsList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }//done//

    // txt file to strings
    static List<String> txtToString() {
        List<String> contacts = null;

        try {
            Path addressBook = Paths.get("contacts", "contacts.txt");
            contacts = Files.readAllLines(addressBook);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contacts;
    }

//    static void stringToObject(){
//        List<String> tempStringList = txtToString();
//        String listStrings = String.join(",", tempStringList);
//        System.out.println("List of strings: " + listStrings);
//        String[] listArray = listStrings.split(",");
//        System.out.println("List array: " + Arrays.toString(listArray));
////        String[] joinedArray = ;
//        //split and join//
//    }


        // Writing files; called every time files are re-written OR when user exits
    static void writeContacts() {
        try {
            Path contactsPath = Paths.get("contacts", "contacts.txt");
            Files.write(contactsPath, contactsList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }//done//

//    static void searchContacts() throws IOException {
//        System.out.println("Enter the name you would like to search: ");
//        String name = scanner.next();
//        Paths.get("contacts", "contacts.txt");
//
//            for (people contact: contactObjects) {
//                if (name == contact.getName() )
//                System.out.println(contact.contactString());
//            }
//        writeContacts();
//}

    static void searchContacts() throws IOException {
        System.out.println("Enter the name you would like to search: ");
        String name = scanner.nextLine();

        Path addressBook = Paths.get("contacts", "contacts.txt");
        contactsList = Files.readAllLines(addressBook);
//        System.out.println(contactObjects);//watch this//
        for (people contact : contactObjects) {
            System.out.println(contact.toString());
            System.out.println("pending");
            if (contact.getName().equals(name)) {
                System.out.println(contact.contactString());
            }
        }
    }
//    // WRITING FILES from people class using objects//
//    void writeContacts() throws IOException {
//        contacts = null;
//        contacts.add(person);
//        try {
//            // Getting contacts file
//            Path addressBook = Paths.get("contacts", "contacts.txt");
//            // Getting contact info from scanner
//
//            // Writing new added contents to the file
////            Files.write(addressBook, contacts);
//            System.out.println("Input contact name");
//            String name = this.scanner.nextLine();
//            System.out.println("Input contact number");
//            String number = this.scanner.next();
//            Files.write(
//                    addressBook,
//                    Arrays.asList(name + " | " + number),
//                    StandardOpenOption.APPEND
//            );
//            System.out.println("Done");
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }

    //delete Contact//
    void deleteContact() throws IOException {
        File inputFile = new File("contacts.txt");
        File tempFile = new File("tempContact.txt");
        BufferedReader reader = new BufferedReader(new FileReader(String.valueOf(inputFile)));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        System.out.println("Enter a name: ");
        String lineToRemove = this.scanner.next();
        String currentLine;
        while ((currentLine = reader.readLine()) != null) {

            try {
                // trim newline when comparing with lineToRemove

                if (currentLine == lineToRemove) continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        writer.close();
        reader.close();
//            boolean successful = tempFile.renameTo(new File("contacts.txt"));
//        System.out.println(successful);
    }


    String userInput = "";


    void editContact() throws IOException {
        List<String> newContacts = new ArrayList<>();
        List<String> contacts = Files.readAllLines(Paths.get("contacts", "contacts.txt"));
        System.out.println("Enter a name you would like to change");
        String current = this.scanner.next();
        System.out.println("Enter new name ");
        String newEdit = this.scanner.next();
        for (String contact : contacts) {
            int i = current.indexOf("");
            String trimmedContact = contact.substring(0, i);
            System.out.println(trimmedContact);
            if (trimmedContact.equals(current)) {
                newContacts.add(newEdit);
                continue;
            }
            newContacts.add(contact);

            String userInput = "";


//    void editContact() throws IOException {
//        List<String> newContacts = new ArrayList<>();
//        List<String> contacts = Files.readAllLines(Paths.get("contacts", "contacts.txt"));
//        System.out.println("Enter a name you would like to change");
//        String current = this.scanner.next();
//        System.out.println("Enter new name ");
//        String newEdit = this.scanner.next();
//        for (String contact : contacts) {
//            int i = current.indexOf(" ");
//            String trimmedContact = contact.substring(0, i);
//            System.out.println(trimmedContact);
//            if (trimmedContact.equals(current)) {
//                newContacts.add(newEdit);
//                continue;
//
//            }
//            newContacts.add(contact);
//        }
//        Files.write(Paths.get("contacts", "contacts.txt"), newContacts);
//    }


//    contacts.add(person);


//    static void addContact(){
//
//    }


        }
    }
}