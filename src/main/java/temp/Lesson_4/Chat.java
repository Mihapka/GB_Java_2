package Lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Chat extends JFrame {
    private JTextField field;
    private JTextArea jTextArea;
    private JButton button;

    public Chat() {
        setTitle("Simple chat");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jTextArea = new JTextArea();
        jTextArea.setLineWrap(true);
        jTextArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane, BorderLayout.CENTER);

        JPanel bottomJp = new JPanel();
        bottomJp.setLayout(new BorderLayout());

        addTextField();
        bottomJp.add(field, BorderLayout.CENTER);

        addButton();
        bottomJp.add(button, BorderLayout.SOUTH);
        jPanel.add(bottomJp, BorderLayout.SOUTH);
        add(jPanel);

        setVisible(true);
        field.grabFocus();
    }

    private void sendMessage() {
        if (!field.getText().isEmpty()) {
            jTextArea.append(field.getText() + "\n");
            field.setText("");
            field.grabFocus();
        }
    }

    private void addTextField() {
        field = new JTextField();
        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }

    private void addButton() {
        button = new JButton("Отправить");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage();
            }
        });
    }
}