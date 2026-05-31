package net.starbasic.clients.ui;

import net.starbasic.clients.data.*;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private DefaultListModel<Client> listModel;
    private JList<Client> clientsList;
    private ClientsCollection collection;
    private final String DATA_FILE = "clients_data.json";

    public MainWindow() {
        super("Управління клієнтами компанії");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        collection = ClientsCollection.fromJson(DATA_FILE);
        listModel = new DefaultListModel<>();
        for (Client c : collection.getClients()) {
            listModel.addElement(c);
        }

        clientsList = new JList<>(listModel);
        add(new JScrollPane(clientsList), BorderLayout.CENTER);

        JPanel btnPanel = new JPanel();
        JButton btnAdd = new JButton("Додати клієнта");
        JButton btnDel = new JButton("Видалити");
        JButton btnSave = new JButton("Зберегти (JSON)");

        btnPanel.add(btnAdd);
        btnPanel.add(btnDel);
        btnPanel.add(btnSave);
        add(btnPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> showAddDialog());

        btnDel.addActionListener(e -> {
            int selected = clientsList.getSelectedIndex();
            if (selected != -1) {
                listModel.remove(selected);
                collection.getClients().remove(selected);
            } else {
                JOptionPane.showMessageDialog(this, "Оберіть клієнта для видалення");
            }
        });

        btnSave.addActionListener(e -> {
            collection.saveToJson(DATA_FILE);
            JOptionPane.showMessageDialog(this, "Дані успішно збережено у файл " + DATA_FILE);
        });
    }

    private void showAddDialog() {
        JDialog dialog = new JDialog(this, "Новий клієнт", true);
        dialog.setSize(300, 200);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));
        dialog.setLocationRelativeTo(this);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Роздрібний", "Оптовий", "Партнер"});
        JTextField nameField = new JTextField();
        JTextField amountField = new JTextField();

        dialog.add(new JLabel(" Тип клієнта:")); dialog.add(typeCombo);
        dialog.add(new JLabel(" Назва/Ім'я:")); dialog.add(nameField);
        dialog.add(new JLabel(" Сума послуг:")); dialog.add(amountField);

        JButton btnOk = new JButton("Додати");
        JButton btnCancel = new JButton("Скасувати");

        btnOk.addActionListener(e -> {
            try {
                String name = nameField.getText();
                double amount = Double.parseDouble(amountField.getText());
                int typeIdx = typeCombo.getSelectedIndex();

                Client newClient = null;
                if (typeIdx == 0) newClient = new RetailClient(name, amount);
                else if (typeIdx == 1) newClient = new WholesaleClient(name, amount);
                else if (typeIdx == 2) newClient = new PartnerCompany(name, amount, 50.0);

                if (newClient != null) {
                    collection.append(newClient);
                    listModel.addElement(newClient);
                }
                dialog.dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(dialog, "Помилка вводу суми! Введіть число.");
            }
        });

        btnCancel.addActionListener(e -> dialog.dispose());

        dialog.add(btnOk); dialog.add(btnCancel);
        dialog.setVisible(true);
    }
}