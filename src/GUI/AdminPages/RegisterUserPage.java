package GUI.AdminPages;

import Classes.Library;
import Classes.Reader;
import GUI.AdminPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterUserPage extends JFrame{

    public RegisterUserPage(Library library){
        setTitle("Регистрация пользователя");

        setSize(400, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        mainPanel.setBackground(new Color(240, 240, 240));
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

        // Поле ввода имени
        JLabel nameLabel = new JLabel("Имя: ");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField nameField = new JTextField();
        nameField.setFont(new Font("Arial", Font.PLAIN, 14));


        // Поле для ввода возраста
        JLabel ageLabel = new JLabel("Возраст:");
        ageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        JTextField ageField = new JTextField();
        ageField.setFont(new Font("Arial", Font.PLAIN, 14));


        // Кнопка вернуться назад
        JButton backButton = new JButton("Назад");
        backButton.setFont(new Font("Arial", Font.BOLD, 14));
        backButton.setBackground(new Color(100, 150, 200)); // Голубой фон
        backButton.setForeground(Color.WHITE); // Белый текст
        backButton.setFocusPainted(false); // Убираем обводку при фокусе
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPage(library);
            }
        });

        // Кнопка отправить
        JButton submitButton = new JButton("Отправить");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(new Color(100, 150, 200)); // Голубой фон
        submitButton.setForeground(Color.WHITE); // Белый текст
        submitButton.setFocusPainted(false); // Убираем обводку при фокусе
        submitButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Отступы внутри кнопки

        // Отправка действия при нажатии на кнопку
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String ageText = ageField.getText();

                if (name.isEmpty() || ageText.isEmpty()){
                    JOptionPane.showMessageDialog(RegisterUserPage.this, "Заполните все поля!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    int age = Integer.parseInt(ageText);

                    //Создание пользователя
                    Reader newUser = new Reader(name, age);
                    library.addUser(newUser);

                    JOptionPane.showMessageDialog(RegisterUserPage.this, "Пользователь успешно зарегистрирован!");
                    nameField.setText(""); // Очистка поля имени
                    ageField.setText(""); // Очистка поля возраста

                } catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(RegisterUserPage.this, "Возраст должен быть числом!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        mainPanel.add(nameLabel);
        mainPanel.add(nameField);
        mainPanel.add(ageLabel);
        mainPanel.add(ageField);
        mainPanel.add(backButton);
        mainPanel.add(submitButton);

        getContentPane().add(mainPanel);
        setVisible(true);
    }
}
