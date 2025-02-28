package Classes;

public class Book {
    private String title;
    private String author;
    private String edition;
    private int isbn;
    private String genre;
    private boolean availability;

    private static int unicISBN = 1;

    public Book(String title, String author, String edition, String genre, int isbn) {
        this.title = title;
        this.author = author;
        this.edition = edition;
        this.genre = genre;
        this.isbn = unicISBN;
        unicISBN++;
        this.availability = true;
    }

    //Геттеры
    public int getIsbn(){
        return isbn;
    }

    public String getGenre(){
        return genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor(){
        return author;
    }

    public String getEdition(){
        return edition;
    }

    public boolean isAvailable() {
        return availability;
    }

    //Сеттер
    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    // Вывод книги
    @Override
    public String toString() {
        return "Книга: " + title +
                ", Автор: " + author +
                ", Издание: " + edition +
                ", Жанр: " + genre +
                ", ISBN: " + isbn +
                ", Доступность: " + (availability ? "Доступна" : "Не доступна");
    }
}
