package GUI.AdminPages;

import Classes.Library;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class deleteBookAndReaderPage extends JFrame {

    public deleteBookAndReaderPage(Library library) {

        setTitle("Удаление книги и читателя");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Поле для ввода ID книги
        JLabel bookIdLabel = new JLabel("Введите ISBN книги:");
        bookIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField bookIdField = new JTextField();
        bookIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Кнопка удаления книги
        JButton deleteBookButton = new JButton("Удалить книгу");
        deleteBookButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteBookButton.setBackground(new Color(100, 150, 200));
        deleteBookButton.setForeground(Color.WHITE);
        deleteBookButton.setFocusPainted(false);
        deleteBookButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Поле для ввода ID читателя
        JLabel readerIdLabel = new JLabel("Введите ID читателя:");
        readerIdLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField readerIdField = new JTextField();
        readerIdField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Кнопка удаления читателя
        JButton deleteReaderButton = new JButton("Удалить читателя");
        deleteReaderButton.setFont(new Font("Arial", Font.BOLD, 14));
        deleteReaderButton.setBackground(new Color(100, 150, 200));
        deleteReaderButton.setForeground(Color.WHITE);
        deleteReaderButton.setFocusPainted(false);
        deleteReaderButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(200, 100, 100)); // Красный цвет
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        // Добавление компонентов на панель
        mainPanel.add(bookIdLabel);
        mainPanel.add(bookIdField);
        mainPanel.add(deleteBookButton);
        mainPanel.add(new JLabel()); // Пустая ячейка для выравнивания
        mainPanel.add(readerIdLabel);
        mainPanel.add(readerIdField);
        mainPanel.add(deleteReaderButton);
        mainPanel.add(backButton);

        // Обработка нажатия на кнопку "Удалить книгу"
        deleteBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int isbn = Integer.parseInt(bookIdField.getText());
                    boolean isRemoved = library.removeBookByIsbn(isbn);
                    if (isRemoved) {
                        JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Книга успешно удалена!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Книга с таким ISBN не найдена.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Введите корректный ISBN (число).", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Обработка нажатия на кнопку "Удалить читателя"
        deleteReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int id = Integer.parseInt(readerIdField.getText());
                    boolean isRemoved = library.removeUserById(id);
                    if (isRemoved) {
                        JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Читатель успешно удален!", "Успех", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Читатель с таким ID не найден.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(deleteBookAndReaderPage.this, "Введите корректный ID (число).", "Ошибка", JOptionPane.ERROR_MESSAGE);
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