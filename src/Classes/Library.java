package Classes;

import java.util.ArrayList;
import java.util.Iterator;

public class Library {
    private ArrayList<Reader> users;
    private ArrayList<Book> books;
    private ArrayList<Transaction> transactions;

    public Library(){
        users = new ArrayList<>();
        books = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    // Добавление книги
    public void addBook(Book book){
        if (book != null){
            books.add(book);
            System.out.println("Книга добавлена: " + book.getTitle());
        } else {
            System.out.println("Ошибка: Вы не можете добавить пустую книгу");
        }
    }

    // Добавление читателя
    public void addUser(Reader user) {
        if (user != null) {
            users.add(user);
            System.out.println("Читатель добавлен: " + user.getName());
        } else {
            System.out.println("Вы не можете добавить пустого читателя");
        }
    }

    // Добавление транзакции
    public void addTransaction(Transaction transaction){
        transactions.add(transaction);
    }

    // Получение списка всех книг
    public ArrayList<Book> getAllBooks(){
        return books;
    }

    // Получение спсика всех читателей
    public ArrayList<Reader> getUsers(){
        return users;
    }

    // Получение списка транзакций
    public ArrayList<Transaction> getTransactions(){
        return transactions;
    }

    // Поиск книги по ID
    public Book findBookByIsbn(int isbn){
        for (Book book : books){
            if (book.getIsbn() == isbn){
                return book;
            }
        }
        return null;
    }

    // Поиск читателя по ID
    public Reader findUserById(int id){
        for (Reader user : users){
            if (user.getId() == id){
                return user;
            }
        }
        return null;
    }

    // Удаление книги по ID
    public boolean removeBookByIsbn(int isbn){
        for (Book book : books){
            if (book.getIsbn() == isbn){
                books.remove(book);
                System.out.println("Книга удалена: " + book.getTitle() + " (ISBN: " + book.getIsbn() + ")");
                return true;
            }
        }
        System.out.println("Книга с ISBN " + isbn + " не найдена.");
        return false;
    }

    // Удаление читателя по ID
    public boolean removeUserById(int id) {
        Iterator<Reader> iterator = users.iterator();
        while (iterator.hasNext()) {
            Reader user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
                System.out.println("Пользователь удален");
                return true;
            }
        }
        System.out.println("Не удалось удалить пользователя");
        return false;
    }

    // Регистрация взятия книги
    public boolean checkoutBook(Reader user, Book book){
        if (book.isAvailable()){
            user.setTakenBookISBN(book.getIsbn());
            book.setAvailability(false);
            Transaction transaction = new Transaction(user.getId(), book.getIsbn(), Transaction.TransactionType.BORROWED);
            transactions.add(transaction);
            System.out.println("Книга '" + book.getTitle() + "' выдана читателю " + user.getName());
            return true; // Успешная выдача
        } else {
            System.out.println("Книга '" + book.getTitle() + "' недоступна для выдачи.");
            return false;
        }
    }

    // Регистрация возврата книги
    public boolean returnBook(Reader user, Book book){
        if (!book.isAvailable()){
            user.setTakenBookISBN(0);
            book.setAvailability(true);
            Transaction transaction = new Transaction(user.getId(), book.getIsbn(), Transaction.TransactionType.RETURNED);
            transactions.add(transaction);
            System.out.println("Книга '" + book.getTitle() + "' возвращена читателем " + user.getName());
            return true;
        } else {
            System.out.println("Не удалось возвратить книгу");
            return false;
        }
    }
}
