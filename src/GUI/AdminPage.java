package GUI;

import Classes.Library;
import GUI.AdminPages.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AdminPage extends JFrame {

    public AdminPage(Library library) {
        // Установка заголовка окна
        setTitle("Панель администратора");

        // Установка размеров окна
        setSize(800, 600);

        // Установка операции закрытия окна
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Закрыть только это окно

        // Главная панель с BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Светло-серый фон
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Отступы вокруг панели

        // Верхняя панель с заголовком
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(200, 220, 255)); // Голубой фон
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Граница

        // Заголовок
        JLabel titleLabel = new JLabel("Панель администратора");
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
                new MyFrame(); // Открыть главное окно
            }
        });

        // Добавление кнопки "Назад" в верхнюю панель (слева)
        topPanel.add(backButton, BorderLayout.WEST);

        // Центральная панель с кнопками функций
        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10)); // 4 строки, 1 столбец
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Внутренние отступы

        // Кнопка "Зарегистрировать Читателя"
        JButton registerReaderButton = createStyledButton("Зарегистрировать Читателя", new Color(100, 150, 200));
        registerReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new RegisterUserPage(library); // Открыть окно регистрации читателя
            }
        });

        // Кнопка "Зарегистрировать книгу"
        JButton registerBookButton = createStyledButton("Зарегистрировать книгу", new Color(150, 200, 100));
        registerBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new RegisterBookPage(library); // Открыть окно регистрации книги
            }
        });

        // Кнопка "Просмотр списка читателей"
        JButton readersListButton = createStyledButton("Просмотр списка читателей", new Color(200, 100, 150));
        readersListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new ViewReadersPage(library); // Открыть окно просмотра читателей
            }
        });

        //Кнопка "Просмотр списка читателей"
        JButton bookListButton = createStyledButton("Просмотр списка книг", new Color(0x933FA1));
        bookListButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new ViewBooksPage(library); // Открыть окно просмотра читателей
            }
        });

        //Кнопка "Удаление книг и читателей"
        JButton deleteBookAndReaderButton = createStyledButton("Удаление книг или читателя", new Color(0x66A13F));
        deleteBookAndReaderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new deleteBookAndReaderPage(library); // Открыть окно просмотра читателей
            }
        });

        // Кнопка "Взять и вернуть книгу"
        JButton takeAndReturnBookButton = createStyledButton("Взять или вернуть книгу", new Color(0xB1A83F));
        takeAndReturnBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new takeAndReturnBookPage(library); // Открыть окно просмотра читателей
            }
        });

        // Кнопка "Транзакции"
        JButton transactionButton = createStyledButton("Транзакции", new Color(0xA7ABF9));
        transactionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Закрыть текущее окно
                new ViewTransactionsPage(library); // Открыть окно просмотра читателей
            }
        });

        // Добавление кнопок на центральную панель
        centerPanel.add(registerReaderButton);
        centerPanel.add(registerBookButton);
        centerPanel.add(readersListButton);
        centerPanel.add(bookListButton);
        centerPanel.add(deleteBookAndReaderButton);
        centerPanel.add(takeAndReturnBookButton);
        centerPanel.add(transactionButton);

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }

    // Метод для создания стилизованной кнопки
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Убираем обводку при фокусе
        button.setBorder(BorderFactory.createEmptyBorder(15, 25, 15, 25)); // Отступы внутри кнопки
        return button;
    }
}