package Lesson_6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) {

        Socket socket = null;
        try (ServerSocket serverSocket = new ServerSocket(8181)) {
            System.out.println("Сервер запущен, ожидаем подключения...");
            socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    try {
                        String serserMessage = new BufferedReader(new InputStreamReader(System.in)).readLine();
                        dos.writeUTF("\n" + "Сервер сказал: " + serserMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

            while (true) {

                String clientMessage = dis.readUTF();
                System.out.println("Клиент сказал: " + clientMessage);

                if (clientMessage.equals("/q")) {
                    closeConnection(socket, dis, dos);
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeConnection(Socket socet, DataInputStream dis, DataOutputStream dos) {

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
        try {
            socet.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
