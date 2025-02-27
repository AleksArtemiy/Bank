package Classes;

import java.util.ArrayList;

public class Books {
    private ArrayList<Book> Books = new ArrayList<Book>();

    public Books(){
        Books = new ArrayList<>();
    }

    public void addBook(Book book){
        if (book != null){
            Books.add(book);
            System.out.println("Книга добавлена: " + book.getTitle());
        } else {
            System.out.println("Ошибка: Вы не можете добавить пустую книгу");
        }
    }
}
