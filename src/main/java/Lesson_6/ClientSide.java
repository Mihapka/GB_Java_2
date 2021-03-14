//package Lesson_6;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.WindowAdapter;
//import java.awt.event.WindowEvent;
//import java.io.*;
//import java.net.Socket;
//
//public class ClientSide extends JFrame {
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
//    public ClientSide() {
//
//        prepareGUI();
//
//        try {
//            openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void main(String[] args) {
//
//        SwingUtilities.invokeLater(() -> {
//
//            new ClientSide();
//        });
//    }
//
//    public void prepareGUI() {
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
//    public void openConnection() throws IOException {
//
//        socket = new Socket(SERVER_ADDR, SERVER_PORT);
//        dis = new DataInputStream(socket.getInputStream());
//        dos = new DataOutputStream(socket.getOutputStream());
//
//        new Thread(() -> {
//            while (true) {
//                try {
//                    if (!dis.readUTF().isEmpty()) {
//                        String serverMessage = dis.readUTF();
//                        if (serverMessage.equals("/q")) {
//                            break;
//                        }
//                        chatArea.append(serverMessage);
//                        System.out.println(serverMessage);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//    }
//
//    public void sendMessageToServer() {
//
//        if (!msgInputField.getText().trim().isEmpty()) {
//            try {
//                String messageToServer = msgInputField.getText();
//                if (messageToServer.equals("/q")) {
//                    dos.writeUTF("/q");
//                    closeConnection();
//                    return;
//                }
//                dos.writeUTF(messageToServer);
//                msgInputField.setText("");
//                msgInputField.grabFocus();
//            } catch (IOException e) {
//                e.printStackTrace();
//                JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
//            }
//        }
//    }
//
//    public void terminalMessage() {
//        try {
//            String terminalMessage = new BufferedReader(new InputStreamReader(System.in)).readLine();
//            if (terminalMessage.equals("/q")) {
//                dos.writeUTF("/q");
//                closeConnection();
//                return;
//            }
//            dos.writeUTF(terminalMessage);
//        } catch (IOException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Ошибка отправки сообщения");
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