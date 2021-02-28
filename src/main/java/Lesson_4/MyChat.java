package Lesson_4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyChat extends JFrame {

    private JTextField jTextField;
    private JTextArea jTextArea;
    private JButton button;

    public MyChat() {
        setTitle("Second try");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BorderLayout());

        jTextArea = new JTextArea();
        jTextArea.setEnabled(false);
        jTextArea.setLineWrap(true);
        JScrollPane jScrollPane = new JScrollPane(jTextArea);
        jPanel.add(jScrollPane, BorderLayout.CENTER);

        JPanel bottomJPanel = new JPanel();
        bottomJPanel.setLayout(new BorderLayout());

        addTextField();
        bottomJPanel.add(jTextField, BorderLayout.NORTH);

        addButton();
        bottomJPanel.add(button, BorderLayout.SOUTH);
        jPanel.add(bottomJPanel, BorderLayout.SOUTH);
        add(jPanel);

        setVisible(true);
        jTextField.grabFocus();
    }

    private void sendMessage() {
        if (!jTextField.getText().isEmpty()) {

            jTextArea.append(jTextField.getText() + "\n");
            jTextField.setText("");
            jTextField.grabFocus();
        }
    }

    private void addTextField() {
        jTextField = new JTextField();
        jTextField.addActionListener(new ActionListener() {
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