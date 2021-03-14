package Lesson_7.clientside;

import Lesson_6.ClientSide;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.Socket;

public class EchoClient extends JFrame
//{
//
//    private final String SERVER_ADDR = "localhost";
//    private final int SERVER_PORT = 8181;
//
//    private JTextField msgInputField;
//    private JTextArea chatArea;
//
//    private Socket socket;
//    private DataInputStream dis;
//    private DataOutputStream dos;
//
//    private boolean isAuthorised;
//
//    public EchoClient() {
//
//        try {
//            openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        prepareGUI();
//    }
//
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(ClientSide::new);
//    }
//
//    public boolean isAuthorised() {
//        return isAuthorised;
//    }
//
//    public void setAuthorised(boolean authorised) {
//        isAuthorised = authorised;
//    }
//
//    private void prepareGUI() {
//
//        setBounds(600, 300, 500, 500);
//        setTitle("Клиент");
//        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        chatArea = new JTextArea();
//        chatArea.setEditable(false);
//        chatArea.setLineWrap(true);
//        add(new JScrollPane(chatArea), BorderLayout.CENTER);
//
//        JPanel bottomPanel = new JPanel(new BorderLayout());
//        JButton sendButton = new JButton("Отправить");
//        bottomPanel.add(sendButton, BorderLayout.EAST);
//        msgInputField = new JTextField();
//        add(bottomPanel, BorderLayout.SOUTH);
//        bottomPanel.add(msgInputField, BorderLayout.CENTER);
//
//        sendButton.addActionListener(e -> {
//            sendMessageToServer();
//        });
//
//        msgInputField.addActionListener(e -> {
//            sendMessageToServer();
//        });
//
//
//        addWindowListener(new WindowAdapter() {
//            @Override
//            public void windowClosing(WindowEvent e) {
//                super.windowClosing(e);
//                try {
//                    dos.writeUTF("/q");
//                    closeConnection();
//                } catch (IOException exc) {
//                    exc.printStackTrace();
//                }
//            }
//        });
//        setVisible(true);
//    }
//
//    private void openConnection() throws IOException {
//
//        socket = new Socket(SERVER_ADDR, SERVER_PORT);
//        dis = new DataInputStream(socket.getInputStream());
//        dos = new DataOutputStream(socket.getOutputStream());
//        setAuthorised(false);
//        Thread thread = new Thread(() -> {
//            try {
//                while (true) {
//                    String serverMsg = dis.readUTF();
//                    if (serverMsg.equals("/authok")) {
//                        setAuthorised(true);
//                        chatArea.append("");
//                        chatArea.append(serverMsg);
//                        break;
//                    }
//
//                }
//                while (true) {
//                    String serverMsg = dis.readUTF();
//                    if (serverMsg.equals("/q")) {
//                        break;
//                    }
//                    chatArea.append(serverMsg);
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            closeConnection();
//        });
//        thread.setDaemon(true);
//        thread.start();
//    }
//
//    private void sendMessageToServer() {
//
//        if (!msgInputField.getText().trim().isEmpty()) {
//            try {
//                String msgToServer = msgInputField.getText();
//                dos.writeUTF(msgToServer);
//                if (!msgToServer.equals("/q")) {
//                    chatArea.append(msgToServer + "ZZZZZ" + "\n");
//                    return;
//                }
//                msgInputField.setText("");
//                msgInputField.grabFocus();
//            } catch (IOException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
//            }
//        }
//    }
//
//    private void closeConnection() {
//
//        try {
//            dos.flush();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            dis.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            dos.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}

{

    private final static String IP_ADDRESS = "localhost"; //127.0.0.1 ip address
    private final static int SERVER_PORT = 8081;

    private JTextField msgInputField;
    private JTextArea chatArea;

    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private boolean isAuthorized;

    public EchoClient() {
        try {
            connection();
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
        prepareGUI();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EchoClient();
        });
    }

    public boolean isAuthorized() {
        return isAuthorized;
    }

    public void setAuthorized(boolean authorized) {
        isAuthorized = authorized;
    }

    private void connection() throws IOException {
        socket = new Socket(IP_ADDRESS, SERVER_PORT);
        dis = new DataInputStream(socket.getInputStream());
        dos = new DataOutputStream(socket.getOutputStream());
        setAuthorized(false);
        Thread thread = new Thread(() -> {
            try {
                while (true) {
                    String serverMessage = dis.readUTF();
                    if (serverMessage.startsWith("/authok")) {
                        setAuthorized(true);
                        chatArea.append(serverMessage + "\n");
                        break;
                    }
                    chatArea.append(serverMessage + "\n");
                }
                while (true) {
                    String serverMessage = dis.readUTF();
                    if (serverMessage.equals("/q")) {
                        break;
                    }
                    chatArea.append(serverMessage + "\n");
                }
            } catch (IOException ignored) {
                ignored.printStackTrace();
            }
            closeConnection();
        });
        //thread.setDaemon(true);
        thread.start();
    }

    private void sendMessageToServer() {
        if (!msgInputField.getText().trim().isEmpty()) {
            try {
                String messageToServer = msgInputField.getText();
                dos.writeUTF(messageToServer);
                msgInputField.setText("");
            } catch (IOException ignored) {
            }
        }
    }

    private void prepareGUI() {

        setBounds(600, 300, 500, 500);
        setTitle("Клиент");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        chatArea = new JTextArea();
        chatArea.setEditable(false);
        chatArea.setLineWrap(true);
        add(new JScrollPane(chatArea), BorderLayout.CENTER);


        JPanel bottomPanel = new JPanel(new BorderLayout());
        JButton btnSendMsg = new JButton("Отправить");
        bottomPanel.add(btnSendMsg, BorderLayout.EAST);
        msgInputField = new JTextField();
        add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(msgInputField, BorderLayout.CENTER);

        btnSendMsg.addActionListener(e -> {
            sendMessageToServer();
        });

        msgInputField.addActionListener(e -> {
            sendMessageToServer();
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                try {
                    dos.writeUTF("/q");
                } catch (IOException ignored) {
                }
            }
        });

        setVisible(true);
    }

    private void closeConnection() {

        try {
            dos.flush();
        } catch (IOException ignored) {
        }

        try {
            dis.close();
        } catch (IOException ignored) {
        }

        try {
            dos.close();
        } catch (IOException ignored) {
        }

        try {
            socket.close();
        } catch (IOException ignored) {
        }
    }
}
