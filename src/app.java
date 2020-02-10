import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class app {
    private Scanner scanner;

    public app() {
        this.scanner = new Scanner(System.in);
    }

    static void newFile() {
        // CREATING A NEW DIRECTORY

        // Creating directory/file names as strings
        String directory = "contacts";
        String filename = "contacts.txt";

        // Setting path for directory
        Path dataDirectory = Paths.get(directory);
        // Printing absolute path for directory
        System.out.println(dataDirectory.toAbsolutePath());
        // Setting path for file (NOTE: method override in Paths class allows different parameters for files and directory creation)
        Path dataFile = Paths.get(directory, filename);

        // Error handling for file creation
        try {
            // NOTE: If the file already exists, program will run without creating files and WILL NOT run catch block
            // If the directory does not exist, create a new one
            if (Files.notExists(dataDirectory)) {
                Files.createDirectories(dataDirectory);
                System.out.println("Directory created");
            }
            // If file does not exist in the specified directory, create a new one
            if (!Files.exists(dataFile)) {
                Files.createFile(dataFile);
                System.out.println("File created");
            }
            // Handling errors during file creation
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Something went wrong");
        }
    }

    void readWriteContacts() {
        // READING FILES
        // Creating empty list of contacts
        List<String> contacts = null;
        try {
            // Getting addressBook file path (contacts/contacts.txt)
            Path addressBook = Paths.get("contacts", "contacts.txt");
            // Getting all lines from contacts.txt (which should be nothing at first)
            contacts = Files.readAllLines(addressBook);
            // Print each line of the list to the console
//            for (String contact : contacts) {
//                System.out.println(contact);
//            }
            for (int i = 3; i < contacts.size(); i += 1) {
                System.out.println((i + 1) + ": " + contacts.get(i));
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        // WRITING FILES
        try {
            // Getting contacts file
            Path addressBook = Paths.get("contacts", "contacts.txt");
            // Getting contact info from scanner

            // Writing new added contents to the file
//            Files.write(addressBook, contacts);
            System.out.println("Input contact name");
            String name = this.scanner.nextLine();
            System.out.println("Input contact number");
            String number = this.scanner.next();
            Files.write(
                    addressBook,
                    Arrays.asList(name + " | " + number),
                    StandardOpenOption.APPEND
            );
            System.out.println("Done");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

//    static void addContact(){
//
//    }

}
