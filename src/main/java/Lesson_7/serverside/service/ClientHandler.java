package Lesson_7.serverside.service;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private MyServer myServer;
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(MyServer myServer, Socket socket) {
        try {
            this.myServer = myServer;
            this.socket = socket;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
            this.name = "";
            new Thread(() -> {
                try {
                    authentication();
                    readMsg();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    public void authentication() throws IOException {
        while (true) {
            String str = dis.readUTF();
            if (str.startsWith("/auth")) {
                String[] parts = str.split("\\s");
                String nick = myServer.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if (!nick.isEmpty()) {
                    if (!myServer.isNickBusy(nick)) {
                        sendMsg(" " + nick + " Авторизовался" + "\n");
                        name = nick;
                        myServer.sentMsgToClient(name + " зашел в чат" + "\n");
                        myServer.subscribe(this);
                        return;
                    } else {
                        sendMsg("Учетная запись " + name + " уже используется" + "\n");
                    }
                } else {
                    sendMsg("Неверные логин/пароль" + "\n");
                }
            }
        }
    }

    public void readMsg() throws IOException {
        while (true) {
            String msgFromClient = dis.readUTF();
            System.out.println("от " + name + ": " + msgFromClient);
            if (msgFromClient.equals("/q")) {
                return;
            }
            myServer.sentMsgToClient(name + ": " + msgFromClient + "\n");
        }
    }

    public void sendMsg(String msg) {

            try {
                dos.writeUTF(msg);
            } catch (IOException e) {
                e.printStackTrace();
        }
    }

    public void closeConnection() {
        myServer.unsubscribe(this);
        myServer.sentMsgToClient(" вышел из чата" + "\n");
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
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

