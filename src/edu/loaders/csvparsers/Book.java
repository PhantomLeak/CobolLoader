package edu.loaders.csvparsers;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Book {
  protected long isbn = 0;
  protected String title = "unknown";
  protected String author = "unknown";
  protected int pages = 0;
  protected String editor = "none";

  // List of optional genres to choose from.
  private String genre = "";
  private final Set<String> GENRES = new HashSet<>(Arrays.asList("fantasy", "non-fiction",
      "manga", "instructional", "scifi", "folktales", "romance", "high-fantasy", "mystery"));

  // Setting internal price equal to zero
  private int internalPrice = 0;

  // Setting constructors
  public Book(long isbn, String title, String author, int pages, String editor,
      String genre, double price) {
    this.isbn = isbn;
    this.title = title;
    this.author = author;
    this.pages = pages;
    this.editor = editor;
    setGenre(genre);
    setPrice(price);
  }

  // Calling constructors
  public Book(String[] csvLine) {
    isbn = Long.parseLong(csvLine[0]);
    title = csvLine[1].substring(1, csvLine[1].length()-1);
    author = csvLine[2].substring(1, csvLine[2].length()-1);
    pages = Integer.parseInt(csvLine[3]);
    editor = csvLine[4].substring(1, csvLine[4].length()-1);
    setGenre(csvLine[5].substring(1, csvLine[5].length()-1));
    setPrice(Double.parseDouble(csvLine[6]));
  }

  public Book() {

  }

  public String getAuthor() { return author; }

  public int getInternalPrice() {
    return internalPrice;
  }
  
  // Equation used to find the price of the book
  public double getPrice() {
    return internalPrice / 100.0;
  }

  // Setting the price of book.
  public void setPrice(double price) {
    this.internalPrice = (int) (price * 100);
  }

  // Setting Genre, if genre not chosen, it selects "other"
  public void setGenre(String genre) {
    if (GENRES.contains(genre)) {
      this.genre = genre;
    } else {
      this.genre = "other";
    }
  }

  // Displaying the given information.
  @Override
  public String toString() {
    return isbn + ",'" + title + "','"
        + author + "'," + pages
        + ",'" + editor + "','"
        + genre + "'," + getPrice() + "\n";
  }

  public String getTitle() { return title;  }
}
