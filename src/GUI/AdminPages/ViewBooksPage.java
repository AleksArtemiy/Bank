package GUI.AdminPages;

import Classes.Book;
import Classes.Library;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewBooksPage extends JFrame {

    private Library library;
    private DefaultTableModel model;
    private JTable booksTable;

    public ViewBooksPage(Library library) {
        this.library = library;

        setTitle("Список книг");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Верхняя панель с заголовком и кнопкой "Назад"
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(200, 220, 255));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

        JLabel titleLabel = new JLabel("Список книг");
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

        // Панель для поиска
        JPanel searchPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        searchPanel.setBackground(new Color(240, 240, 240));
        searchPanel.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel searchLabel = new JLabel("Поиск по жанру или автору:");
        searchLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField searchField = new JTextField();
        searchField.setFont(new Font("Arial", Font.PLAIN, 14));

        JButton searchButton = new JButton("Поиск");
        searchButton.setFont(new Font("Arial", Font.BOLD, 14));
        searchButton.setBackground(new Color(100, 150, 200));
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);
        searchButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JButton resetButton = new JButton("Сбросить");
        resetButton.setFont(new Font("Arial", Font.BOLD, 14));
        resetButton.setBackground(new Color(200, 100, 100)); // Красный цвет
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        searchPanel.add(searchLabel);
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        searchPanel.add(resetButton);

        // Центральная панель с таблицей
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        String[] columnNames = {"ISBN", "Название", "Автор", "Издательство", "Жанр"};
        model = new DefaultTableModel(columnNames, 0);

        // Загрузка данных из объекта Books
        loadBooksData(null);

        booksTable = new JTable(model);
        booksTable.setFont(new Font("Arial", Font.PLAIN, 14));
        booksTable.setRowHeight(30);
        booksTable.setSelectionBackground(new Color(200, 220, 255));
        booksTable.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(booksTable);
        centerPanel.add(scrollPane, BorderLayout.CENTER);

        // Обработка нажатия на кнопку "Поиск"
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String searchText = searchField.getText().trim();
                loadBooksData(searchText);
            }
        });

        // Обработка нажатия на кнопку "Сбросить"
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchField.setText("");
                loadBooksData(null);
            }
        });

        // Добавление панелей на главную панель
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(searchPanel, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.SOUTH);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    /**
     * Загружает данные о книгах в таблицу.
     *
     * @param searchText Текст для поиска (жанр или автор). Если null, загружаются все книги.
     */
    private void loadBooksData(String searchText) {
        model.setRowCount(0); // Очистка таблицы

        for (Book book : library.getAllBooks()) {
            // Проверка, соответствует ли книга критериям поиска
            if (searchText == null || searchText.isEmpty() ||
                    book.getAuthor().toLowerCase().contains(searchText.toLowerCase()) ||
                    book.getGenre().toLowerCase().contains(searchText.toLowerCase())) {
                model.addRow(new Object[]{
                        book.getIsbn(),
                        book.getTitle(),
                        book.getAuthor(),
                        book.getEdition(),
                        book.getGenre()
                });
            }
        }
    }
}