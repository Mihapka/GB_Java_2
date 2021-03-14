package Lesson_7.serverside.interfaces;

import java.util.ArrayList;
import java.util.List;

public interface AuthService {
    void start();
    String getNickByLoginPass(String login, String pass);
    void stop();
}

