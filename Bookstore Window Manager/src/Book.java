// This class contains fields for the 'Book' object, as well as getter methods and a toString override
public class Book
{

    // Fields for the Book class
    private String id;
    private String title;
    private int year;
    private double value;

    //Constructor
    public Book(String id, String title, int year, double value)
    {
        this.id = id;
        this.title = title;
        this.year = year;
        this.value = value;
    }

    //Getters for the Book fields
    public String getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public int getYear()
    {
        return year;
    }

    public double getValue()
    {
        return value;
    }

    // toString override which formats book value to 2 decimal places
    public String toString()
    {
        return "Book ID: " + id + ", Title: " + title + ", Year published: " + year + ", Value: £" + String.format("%.2f",value);
    }

}