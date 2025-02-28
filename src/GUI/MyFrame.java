package GUI;

import Classes.Library;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame {
    private Library library;

    public MyFrame() {
        library = new Library();

        // Установка заголовка окна
        setTitle("Красивый интерфейс");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Главная панель с BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240)); // Светло-серый фон
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20)); // Отступы вокруг панели

        // Верхняя панель с приветствием
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(200, 220, 255)); // Голубой фон
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1)); // Граница
        JLabel welcomeLabel = new JLabel("Добро пожаловать в библиотеку!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(new Color(50, 50, 50)); // Темно-серый текст
        topPanel.add(welcomeLabel);

        // Центральная панель с текстом
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Внутренние отступы
        JLabel infoLabel = new JLabel("<html><div style='text-align: center;'>Здесь вы можете найти книги, журналы и другие материалы.<br>Используйте кнопки ниже для навигации.</div></html>");
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        infoLabel.setForeground(new Color(80, 80, 80)); // Серый текст
        centerPanel.add(infoLabel);

        // Нижняя панель с кнопками
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));
        bottomPanel.setBackground(new Color(240, 240, 240)); // Светло-серый фон

        // Кнопка "Администратор"
        JButton adminButton = createStyledButton("Администратор", new Color(100, 150, 200));
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPage(library);
            }
        });

        // Кнопка "Пользователь"
        JButton userButton = createStyledButton("Пользователь", new Color(150, 200, 100));
        userButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(MyFrame.this, "Вы вошли как пользователь!");
            }
        });

        // Добавление кнопок на нижнюю панель
        bottomPanel.add(adminButton);
        bottomPanel.add(userButton);

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }

    // Метод для создания стилизованной кнопки
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false); // Убираем обводку при фокусе
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Отступы внутри кнопки
        return button;
    }
}