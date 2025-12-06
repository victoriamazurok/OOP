package com.videoplatform;

import com.google.inject.Inject;
import java.util.ArrayList;  // Додаємо імпорт ArrayList
import java.util.List;

public class PremierSystem {
    private List<User> users;
    private List<Content> content;
    private String systemName;

    @Inject
    public PremierSystem(String systemName) {
        this.systemName = systemName;
        this.users = new ArrayList<>(); // Ініціалізація списку користувачів
        this.content = new ArrayList<>(); // Ініціалізація списку контенту
    }

    // Додаємо методи, яких не вистачає
    public void registerUser(User user) {
        users.add(user);
        System.out.println("[РЕЄСТРАЦІЯ]: Користувач зареєстрований: " + user.getName());
    }

    public void publishContent(Content content) {
        this.content.add(content);
        System.out.println("[ПУБЛІКАЦІЯ]: Контент опубліковано: " + content.getTitle());
    }

    public void printStatistics() {
        System.out.println("Загальна статистика:");
        System.out.println("Користувачів: " + users.size());
        System.out.println("Контенту: " + content.size());
    }

    public void printUsersInfo() {
        System.out.println("Інформація про користувачів:");
        for (User user : users) {
            System.out.println(user.getName() + " (" + user.getEmail() + ")");
        }
    }

    public void printContentInfo() {
        System.out.println("Інформація про контент:");
        for (Content c : content) {
            System.out.println(c.getTitle());
        }
    }
}
