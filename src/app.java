import java.io.*;
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
    public static void main(String[] args){
        app app = new app();

        newFile();
        readContacts();
        // Creating filler contacts
        people contact1 = new people("Test","1234567890");
        people contact2 = new people("Jane","0987654321");
        // Converting the object to a string using a method from the constructor class
        contactsList.add(contact1.contactString());
        System.out.println(contactsList);

//        app.deleteContact();
        writeContacts();
//        app.editContact();


    }


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
    }

    // Reformulated to work with the people objects!!
    static List<String> readContacts() {
        List<String> contacts = null;

        try {
            Path addressBook = Paths.get("contacts", "contacts.txt");
            contacts = Files.readAllLines(addressBook);

//            for (String contact : contacts) {
//                System.out.println(contact);
//            }
//                for (int i = 0; i < contacts.size(); i += 1) {
//                    System.out.println((i + 1) + ": " + contacts.get(i));
//                }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return contacts;
    }

    // Writing files; called every time files are re-written OR when user exits
    static void writeContacts(){
        try{
            Path contactsPath = Paths.get("contacts","contacts.txt");
            Files.write(contactsPath, contactsList);
        } catch(IOException ioe){
            ioe.printStackTrace();
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
            int i = current.indexOf(" ");
            String trimmedContact = contact.substring(0, i);
            System.out.println(trimmedContact);
            if (trimmedContact.equals(current)) {
                newContacts.add(newEdit);
                continue;
            }
            newContacts.add(contact);
        }
        Files.write(Paths.get("contacts", "contacts.txt"), newContacts);
    }


//    contacts.add(person);
}


//    static void addContact(){
//
//    }




