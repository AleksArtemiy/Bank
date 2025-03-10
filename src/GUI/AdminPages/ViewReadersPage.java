package GUI.AdminPages;

import Classes.Book;
import Classes.Library;
import Classes.Reader;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ViewReadersPage extends JFrame {

    public ViewReadersPage(Library library) {
        setTitle("Список читателей");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Верхняя панель с заголовком и кнопкой "Назад"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel titleLabel = new JLabel("Список читателей");
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
            dispose();
            new AdminPage(library);
        });

        topPanel.add(backButton, BorderLayout.WEST);

        // Центральная панель с таблицей
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"Уникальный ID", "Имя", "Книга"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        // Загрузка данных из объекта Users
        for (Reader user : library.getUsers()) {
            Book book = library.findBookByIsbn(user.getIsbnTakedBook());
            String bookTitle = (book != null) ? book.getTitle() : "Нет книги"; // Проверка на null
            model.addRow(new Object[]{user.getId(), user.getName(), bookTitle});
        }


        JTable readersTable = new JTable(model);
        readersTable.setFont(new Font("Arial", Font.PLAIN, 14));
        readersTable.setRowHeight(30);
        readersTable.setSelectionBackground(new Color(200, 220, 255));
        readersTable.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(readersTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setVisible(true);
    }
}