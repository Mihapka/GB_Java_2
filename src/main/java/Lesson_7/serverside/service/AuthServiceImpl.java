package Lesson_7.serverside.service;

import Lesson_7.serverside.interfaces.AuthService;

import java.util.ArrayList;
import java.util.List;

public class AuthServiceImpl implements Lesson_7.serverside.interfaces.AuthService {


    private List<Entry> entries;

    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }

    public AuthServiceImpl() {

        entries = new ArrayList<>();
        entries.add(new Entry("a", "a", "a"));
        entries.add(new Entry("b", "b", "b"));
        entries.add(new Entry("c", "c", "c"));
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {

        for (Entry e : entries) {
            if (e.login.equals(login) && e.pass.equals(pass)) return e.nick;
        }
        return "";
    }

    private class Entry {

        private String login;
        private String pass;
        private String nick;

        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }
}
