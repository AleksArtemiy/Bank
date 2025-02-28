package Classes;

public class Reader {
    private String name;
    private int age;
    private int id;
    private int isbnTakedBook;

    private static int unicID = 1;

    public Reader(String name, int age){
        this.name = name;
        this.age = age;
        this.id = unicID;
        unicID++;
        this.isbnTakedBook = 0;
    }

    //Геттеры
    public String getName() {
        return name;
    }

    public int getId(){
        return id;
    }

    public int getAge(){
        return age;
    }

    public int getIsbnTakedBook(){
        return isbnTakedBook;
    }

    // Сеттеры
    public void setTakenBookISBN(int isbn){
        this.isbnTakedBook = isbn;
    }

    @Override
    public String toString() {
        return "Имя: " + name +
                " Возраст: " + age +
                " ID: " + id +
                " ISBN книги: " + isbnTakedBook;
    }
}
