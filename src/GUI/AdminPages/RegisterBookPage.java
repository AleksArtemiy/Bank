package GUI.AdminPages;

import Classes.Book;
import Classes.Library;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterBookPage extends JFrame {
    public RegisterBookPage(Library library) {

        // Установка заголовка окна
        setTitle("Регистрация книги");

        // Установка размеров окна
        setSize(500, 400);

        // Установка операции закрытия окна
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Главная панель с BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Светло-серый фон
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Отступы вокруг панели

        // Верхняя панель с заголовком
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(200, 220, 255)); // Голубой фон
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Граница

        // Заголовок
        JLabel titleLabel = new JLabel("Регистрация книги");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50)); // Темно-серый текст
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Выравнивание по центру
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Кнопка "Назад"
        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(100, 150, 200)); // Голубой фон
        backButton.setForeground(Color.WHITE); // Белый текст
        backButton.setFocusPainted(false); // Убираем обводку при фокусе
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Отступы внутри кнопки

        // Добавление действия при нажатии на кнопку "Назад"
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new AdminPage(library); // Открыть окно администратора
            }
        });

        // Добавление кнопки "Назад" в верхнюю панель (слева)
        topPanel.add(backButton, BorderLayout.WEST);

        // Центральная панель с формой регистрации
        JPanel centerPanel = new JPanel(new GridLayout(5, 2, 10, 10)); // 4 строки, 2 столбца
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Внутренние отступы

        // Поле для ввода названия книги
        JLabel nameLabel = new JLabel("Название:");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Поле для ввода автора
        JLabel authorLabel = new JLabel("Автор:");
        authorLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField authorField = new JTextField();
        authorField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Поле для ввода издания
        JLabel editionLabel = new JLabel("Издание:");
        editionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField editionField = new JTextField();
        editionField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Поле для ввода жанра
        JLabel genreLabel = new JLabel("Жанр:");
        genreLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField genreField = new JTextField();
        genreField.setFont(new Font("Arial", Font.PLAIN, 14));

        // Кнопка "Зарегистрировать"
        JButton submitButton = new JButton("Зарегистрировать");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(100, 150, 200)); // Голубой фон
        submitButton.setForeground(Color.WHITE); // Белый текст
        submitButton.setFocusPainted(false); // Убираем обводку при фокусе
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Отступы внутри кнопки

        // Добавление действия при нажатии на кнопку "Зарегистрировать"
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Получаем значения
                String title = nameField.getText();
                String author = authorField.getText();
                String edition = editionField.getText();
                String genre = genreField.getText();

                // Проверка на пустыые поля
                if (title.isEmpty() || author.isEmpty() || edition.isEmpty() || genre.isEmpty()) {
                    JOptionPane.showMessageDialog(RegisterBookPage.this, "Все поля должны быть заполнены!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Создание новой книги
                Book newBook = new Book(title, author, edition, genre, 0);

                library.addBook(newBook);

                nameField.setText("");
                authorField.setText("");
                editionField.setText("");
                genreField.setText("");

                JOptionPane.showMessageDialog(RegisterBookPage.this, "Книга успешно зарегистрирована!", "Успех", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Добавление элементов на центральную панель
        centerPanel.add(nameLabel);
        centerPanel.add(nameField);
        centerPanel.add(authorLabel);
        centerPanel.add(authorField);
        centerPanel.add(editionLabel);
        centerPanel.add(editionField);
        centerPanel.add(genreLabel);
        centerPanel.add(genreField);
        centerPanel.add(new JLabel()); // Пустая ячейка для выравнивания
        centerPanel.add(submitButton);

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }
}