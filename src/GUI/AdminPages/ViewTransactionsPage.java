package GUI.AdminPages;

import Classes.Library;
import Classes.Transaction;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewTransactionsPage extends JFrame {

    public ViewTransactionsPage(Library library) {
        setTitle("Список транзакций");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Верхняя панель с заголовком и кнопкой "Назад"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel titleLabel = new JLabel("Список транзакций");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(50, 50, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(100, 150, 200));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.addActionListener(e -> {
            dispose(); // Закрыть текущее окно
            new AdminPage(library); // Открыть окно администратора
        });

        topPanel.add(backButton, BorderLayout.WEST);

        // Центральная панель с таблицей
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Заголовки колонок таблицы
        String[] columnNames = {"ID транзакции", "Тип транзакции", "ID читателя", "ISBN книги", "Дата"};

        // Модель таблицы
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Загрузка данных из объекта Library
        for (Transaction transaction : library.getTransactions()) {
            model.addRow(new Object[]{
                    transaction.getTransactionId(),
                    transaction.getTransactionType(),
                    transaction.getReaderId(),
                    transaction.getBookId(),
                    transaction.getTransactionDate()
            });
        }

        // Создание таблицы
        JTable transactionsTable = new JTable(model);
        transactionsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        transactionsTable.setRowHeight(30);
        transactionsTable.setSelectionBackground(new Color(200, 220, 255));
        transactionsTable.setGridColor(Color.LIGHT_GRAY);

        // Добавление таблицы в JScrollPane для прокрутки
        JScrollPane scrollPane = new JScrollPane(transactionsTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // Добавление главной панели на окно
        getContentPane().add(mainPanel);

        // Отображение окна
        setVisible(true);
    }
}