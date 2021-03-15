package Lesson_7.clientside;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.TimerTask;

public class EchoClient extends JFrame {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8181;

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private boolean isAuthorised;

    public boolean isAuthorised() {
        return isAuthorised;
    }

    public EchoClient() {

        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EchoClient::new);

    }

    public void setAuthorised(boolean authorised) {
        isAuthorised = authorised;
    }

    public void openConnection() throws IOException {

        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        setAuthorised(false);
        Thread thread = new Thread(() -> {

            new Thread(new Runnable() {
                public void run() {
                    while(true) {
                        try {
                            Thread.sleep(120000); // 120 секунды в милисекундах
                            closeConnection();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            try {
                while (true) {
                    String serverMsg = dis.readUTF();
                    if (serverMsg.startsWith("/authok")) {
                        setAuthorised(true);
                        chatArea.append("Вы авторизовались" + "\n");
                        break;
                    }
                    chatArea.append(serverMsg + "\n");
                }
                while (true) {
                    String serverMsg = dis.readUTF();
                    if (serverMsg.equals("/q")) {
                        break;
                    }
                    chatArea.append(serverMsg + "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            closeConnection();
        });
        thread.setDaemon(true);
        thread.start();
    }

    public void prepareGUI() {

        setBounds(200, 300, 300, 300);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton sendButton = new JButton("Отправить");
        bottomPanel.add(sendButton, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);

        sendButton.addActionListener(e -> {
            sendMsgToServer();
        });

        msgInputField.addActionListener(e -> {
            sendMsgToServer();
        });


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    dos.writeUTF("/q");
                    closeConnection();
                } catch (IOException exc) {
                    exc.printStackTrace();
                }
            }
        });
        setVisible(true);
    }

    public void sendMsgToServer() {

        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                String msgToServer = msgInputField.getText();
                dos.writeUTF(msgToServer);
                if (!msgToServer.equals("/q")) {
                    msgInputField.setText("");
                    return;
                }
                msgInputField.setText("");
                msgInputField.grabFocus();
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
                msgInputField.setText("");
            }
        }
    }

    public void closeConnection() {

        try {
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            dos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}