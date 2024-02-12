import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

// This class contains all the methods for the program's inputs and outputs, including the user menu
public class BookStoreWindowIO
{

    private static boolean booksAdded = false;
    private static boolean done = false;
    private static Scanner s;


    public static void main(String[] args) {

        s = new Scanner(System.in);
        welcomeMessage();

        // Main program loop which returns to the menu and reads user input until user quits
        while (!done) {

            printMenu();
            String response = s.nextLine();

            // Checks user input and executes the corresponding option
            switch (response) {
                case "q":
                    quit();
                    break;

                case "1":
                    enterName();
                    break;

                case "2":
                    readFile();
                    break;

                case "3":
                    printSummary();
                    break;

                case "4":
                    printStats();
                    break;

                case ":)":
                    easterEgg();
                    break;

                // Error message if user input is invalid
                default:
                    System.out.println("'" + response + "' won't work here - please enter either '1', '2', '3', '4', or 'q'");
                    blankLine();

            }
        }
    }

    // Instantiating the BookStoreWindow with a placeholder name
    private static BookStoreWindow bookStoreWindow = new BookStoreWindow("No name set! - please enter a name using option 1");

    // Prints opening welcome message
    private static void welcomeMessage()
    {
        System.out.println("Welcome!");
        blankLine();
        System.out.println("Please select one of the options below by entering an option number (or enter 'q' to quit):");
    }

    // Line break generator to make the UI a bit more readable
    private static void blankLine()
    {
        System.out.println();
    }

    // Prints the options menu
    private static void printMenu()
    {
        System.out.println("1. Enter the name of the book store");
        System.out.println("2. Read information on books from the 'books.csv' file and add them to the window");
        System.out.println("3. Print a summary of the book store and all books in the window");
        System.out.println("4. Print statistics on the books, including the highest value book, oldest book, and average value of all books displayed");
    }

    // Quits the program
    private static void quit()
    {
        System.out.println("Bye for now!");
        done = true;
    }

    // Option 1 - Allows user to set Bookstore name
    private static void enterName()
    {
        System.out.println("Please enter the name of the bookstore below:");
        String nameIn = s.nextLine();
        bookStoreWindow.setName(nameIn);
        System.out.println("Name saved!");
        nextOption();
    }

    // Option 2 - Checks if Arraylist is empty, adds books from CSV if so
    private static void readFile()
    {
        if (!booksAdded) {
            try {
                File csv = new File("books.csv");

                Scanner fileReader = new Scanner(csv);

                fileReader.nextLine();

                while (fileReader.hasNext()) {
                    String[] split = fileReader.nextLine().split(","); // Splitting the CSV file into a string array

                    Book b = new Book(split[0], split[1], Integer.parseInt(split[2]), Double.parseDouble(split[3])); // Defining the book fields using the indices of the array

                    bookStoreWindow.addBooks(b); // Adding books to the ArrayList
                }

                booksAdded = true;
                System.out.println("Books successfully added to the window!");

            } catch (FileNotFoundException error) // File not found error
            {
                System.out.println("File 'books.csv' not found in this directory");
            }
        }
        else // This stops the user from duplicating the contents of the ArrayList
        {
            System.out.println("Books already added!");
        }
        nextOption();
    }

    // Option 3 - Checks if ArrayList is empty, prints summary if not
    private static void printSummary()
    {
        System.out.println("Book store name: " + bookStoreWindow.getName());
        if (noBooks())
        {
            noBooksError();
        }
        else {
            printBooks();
            nextOption();
        }
    }

    // Option 4 - Checks if ArrayList is empty, prints statistics if not
    private static void printStats()
    {
        if (noBooks())
        {
            noBooksError();
        }
        else
        {
            System.out.println("The statistics on highest value, oldest book, and average value of the books:");
            System.out.println("Highest value book: " + bookStoreWindow.getMaxValueBook().getTitle() + " (" + GBP(bookStoreWindow.getMaxValueBook().getValue()) + ")");
            System.out.println("Oldest book: " + bookStoreWindow.getOldestBook().getTitle() + " (published " + bookStoreWindow.getOldestBook().getYear() + ")");
            System.out.println("Average value of all books: " + GBP(bookStoreWindow.getAverageValue()));
            nextOption();
        }
    }

    // Prints a line break and instructs the user to choose next option
    private static void nextOption()
    {
        blankLine();
        System.out.println("Please select another option (or enter 'q' to quit):");
    }

    // Prints the contents of the ArrayList to the console
    private static void printBooks()
    {
        ArrayList<Book> books = bookStoreWindow.getBooks();
        for (Book b1 : books)
        {
            System.out.println(b1);
        }
    }

    // Determines if the ArrayList is empty or not and returns a boolean value
    private static boolean noBooks()
    {
        ArrayList<Book> books = bookStoreWindow.getBooks();
        return (books.isEmpty());
    }

    // Error message which is thrown if books haven't been added yet
    private static void noBooksError()
    {
        System.out.println("No books displayed! - please add books using option 2");
        blankLine();
    }

    // Converts double value into a string in Pounds Sterling, rounded to 2 decimal places
    private static String GBP(Double value)
    {
        return "£" + String.format("%.2f",value);
    }

    // A hidden feature
    private static void easterEgg()
    {
        System.out.println(
                "      __...--~~~~~-._   _.-~~~~~--...__\n" +
                "    //   I love      `V'               \\\\ \n" +
                "   //    books!       |    (and java)   \\\\ \n" +
                "  //__...--~~~~~~-._  |  _.-~~~~~~--...__\\\\ \n" +
                " //__.....----~~~~._\\ | /_.~~~~----.....__\\\\\n" +
                "====================\\\\|//====================\n" + // ASCII art by Donovan Bake, found at: https://www.asciiart.eu/books/books
                "                    `---`");
        blankLine();
        nextOption();
    }
}