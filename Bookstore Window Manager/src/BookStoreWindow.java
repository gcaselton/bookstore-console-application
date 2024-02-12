import java.util.ArrayList;

// This class contains getters and setters for the bookstore name, as well as a collections class to store books, and methods to calculate the book statistics
public class BookStoreWindow
{

    // Fields for the BookStoreWindow class
    private ArrayList<Book> books = new ArrayList<>();
    private String name;

    // Constructor
    public BookStoreWindow(String name)
    {
        this.name = name;
    }

    // Getter for the name
    public String getName()
    {
        return name;
    }

    // Setter for the name
    public void setName(String name)
    {
        this.name = name;
    }

    // Method for adding books to the ArrayList
    public void addBooks(Book newBook)
    {
        {
            books.add(newBook);
        }
    }

    // Getter for the ArrayList of books
    public ArrayList<Book> getBooks()
    {
        return books;
    }

    // Method for finding and returning highest value book
    public Book getMaxValueBook()
    {
        Book maxValueBook = books.get(0);

        for (Book b : books){
            if (b.getValue() > maxValueBook.getValue()){
                maxValueBook = b;
            }
        }
        return maxValueBook;
    }

    // Method for finding and returning the oldest book
    public Book getOldestBook()
    {
        Book oldestBook = books.get(0);

        for(Book b : books){
            if (b.getYear() < oldestBook.getYear()){
                oldestBook = b;
            }
        }
        return oldestBook;
    }

    // Method for calculating and returning the average value of all books
    public Double getAverageValue()
    {
        double sumValues = 0;

        for (Book b : books)
        {
            sumValues += b.getValue();
        }

        return sumValues/books.size();
    }

}