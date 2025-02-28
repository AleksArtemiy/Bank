package Classes;

import java.time.LocalDateTime;

public class Transaction {
    private int transactionID;
    private int readerID;
    private int bookIsbn;
    private LocalDateTime transactionDate;
    private TransactionType transactionType;

    private static int unicID = 1;

    public enum TransactionType{
        BORROWED,
        RETURNED
    }

    public Transaction(int readerID, int bookIsbn, TransactionType transactionType){
        this.transactionID = unicID;
        unicID++;
        this.readerID = readerID;
        this.bookIsbn = bookIsbn;
        this.transactionDate = LocalDateTime.now();
        this.transactionType = transactionType;
    }

    // Геттеры
    public int getTransactionId() {
        return transactionID;
    }

    public int getReaderId() {
        return readerID;
    }

    public int getBookId() {
        return bookIsbn;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionID +
                ", readerId=" + readerID +
                ", bookId=" + bookIsbn +
                ", transactionDate=" + transactionDate +
                ", transactionType=" + transactionType +
                '}';
    }
}
