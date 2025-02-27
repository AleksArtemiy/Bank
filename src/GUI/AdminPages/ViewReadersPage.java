package GUI.AdminPages;

import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewReadersPage extends JFrame {

    public ViewReadersPage() {
        // Установка заголовка окна
        setTitle("Список читателей");

        // Установка размеров окна
        setSize(800, 600);

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
        JLabel titleLabel = new JLabel("Список читателей");
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
                new AdminPage(); // Открыть окно администратора
            }
        });

        // Добавление кнопки "Назад" в верхнюю панель (слева)
        topPanel.add(backButton, BorderLayout.WEST);

        // Центральная панель с таблицей читателей
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Внутренние отступы

        // Заголовки колонок таблицы
        String[] columnNames = {"Уникальный ID", "Имя", "Книга"};

        // Пример данных для таблицы
        Object[][] data = {
                {1, "Иван Иванов", "Война и мир"},
                {2, "Мария Петрова", "1984"},
                {3, "Алексей Сидоров", "Мастер и Маргарита"},
                {4, "Елена Кузнецова", "Преступление и наказание"}
        };

        // Модель таблицы
        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        // Создание таблицы
        JTable readersTable = new JTable(model);
        readersTable.setFont(new Font("Arial", Font.PLAIN, 14)); // Шрифт таблицы
        readersTable.setRowHeight(30); // Высота строк
        readersTable.setSelectionBackground(new Color(200, 220, 255)); // Цвет выделения строки
        readersTable.setGridColor(Color.LIGHT_GRAY); // Цвет сетки таблицы

        // Добавление таблицы в JScrollPane для прокрутки
        JScrollPane scrollPane = new JScrollPane(readersTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }

    public static void main(String[] args) {
        // Запуск приложения
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewReadersPage();
            }
        });
    }
}