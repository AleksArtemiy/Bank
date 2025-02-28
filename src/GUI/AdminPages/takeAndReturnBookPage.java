package GUI.AdminPages;

import Classes.Library;
import Classes.Reader;
import Classes.Book;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class takeAndReturnBookPage extends JFrame {
    public takeAndReturnBookPage(Library library) {

        setTitle("Взятие и возврат книги");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Поле для ввода ID читателя
        JLabel readerIdLabel = new JLabel("Введите ID читателя:");
        readerIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField readerIdField = new JTextField();
        readerIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Поле для ввода ISBN книги
        JLabel bookIsbnLabel = new JLabel("Введите ISBN книги:");
        bookIsbnLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField bookIsbnField = new JTextField();
        bookIsbnField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Кнопка "Взять книгу"
        JButton takeBookButton = new JButton("Взять книгу");
        takeBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        takeBookButton.setBackground(new Color(100, 150, 200));
        takeBookButton.setForeground(Color.WHITE);
        takeBookButton.setFocusPainted(false);
        takeBookButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Кнопка "Вернуть книгу"
        JButton returnBookButton = new JButton("Вернуть книгу");
        returnBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        returnBookButton.setBackground(new Color(100, 150, 200));
        returnBookButton.setForeground(Color.WHITE);
        returnBookButton.setFocusPainted(false);
        returnBookButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(200, 100, 100)); // Красный цвет
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Добавление компонентов на панель
        mainPanel.add(readerIdLabel);
        mainPanel.add(readerIdField);
        mainPanel.add(bookIsbnLabel);
        mainPanel.add(bookIsbnField);
        mainPanel.add(takeBookButton);
        mainPanel.add(returnBookButton);
        mainPanel.add(backButton);

        // Обработка нажатия на кнопку "Взять книгу"
        takeBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int readerId = Integer.parseInt(readerIdField.getText());
                    int bookIsbn = Integer.parseInt(bookIsbnField.getText());

                    Reader reader = library.findUserById(readerId);
                    Book book = library.findBookByIsbn(bookIsbn);

                    if (reader != null && book != null) {
                        boolean success = library.checkoutBook(reader, book);
                        if (success) {
                            JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Книга успешно выдана!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Книга недоступна для выдачи.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Читатель или книга не найдены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Введите корректные ID и ISBN (числа).", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Обработка нажатия на кнопку "Вернуть книгу"
        returnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int readerId = Integer.parseInt(readerIdField.getText());
                    int bookIsbn = Integer.parseInt(bookIsbnField.getText());

                    Reader reader = library.findUserById(readerId);
                    Book book = library.findBookByIsbn(bookIsbn);

                    if (reader != null && book != null) {
                        boolean success = library.returnBook(reader, book);
                        if (success) {

                            JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Книга успешно возвращена!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Ошибка при возврате книги.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Читатель или книга не найдены.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(takeAndReturnBookPage.this, "Введите корректные ID и ISBN (числа).", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Обработка нажатия на кнопку "Назад"
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new AdminPage(library); // Открыть окно администратора
            }
        });

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }
}