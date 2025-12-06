package com.videoplatform;

import com.google.inject.Inject;
import java.util.ArrayList;  // Імпортуємо ArrayList
import java.util.List;

public class PremierSystem {
    private List<User> users;
    private List<Content> content;
    private String systemName;
    private VideoService videoService;  // Додаємо залежність для VideoService

    // Закоментуємо старий конструктор
    /*
    @Inject
    public PremierSystem(String systemName, VideoService videoService) {
        this.systemName = systemName;
        this.users = new ArrayList<>(); // Ініціалізація списку користувачів
        this.content = new ArrayList<>(); // Ініціалізація списку контенту
        this.videoService = videoService;
    }
    */

    // Додаємо setter для VideoService
    @Inject
    public void setVideoService(VideoService videoService) {
        this.videoService = videoService;
    }

    // Ініціалізація списків користувачів та контенту через setter
    @Inject
    public void setSystemName(String systemName) {
        this.systemName = systemName;
        this.users = new ArrayList<>();  // Ініціалізація списку користувачів
        this.content = new ArrayList<>();  // Ініціалізація списку контенту
    }

    // Метод для реєстрації користувачів
    public void registerUser(User user) {
        users.add(user);
        System.out.println("[РЕЄСТРАЦІЯ]: Користувач зареєстрований: " + user.getName());
        // Можна додати запис до бази даних
        videoService.saveVideo(new Video(user.getName()));  // Приклад використання VideoService
    }

    // Метод для публікації контенту
    public void publishContent(Content content) {
        this.content.add(content);
        System.out.println("[ПУБЛІКАЦІЯ]: Контент опубліковано: " + content.getTitle());
    }

    // Метод для виведення статистики
    public void printStatistics() {
        System.out.println("Загальна статистика:");
        System.out.println("Користувачів: " + users.size());
        System.out.println("Контенту: " + content.size());
    }

    // Метод для виведення інформації про користувачів
    public void printUsersInfo() {
        System.out.println("Інформація про користувачів:");
        for (User user : users) {
            System.out.println(user.getName() + " (" + user.getEmail() + ")");
        }
    }

    // Метод для виведення інформації про контент
    public void printContentInfo() {
        System.out.println("Інформація про контент:");
        for (Content c : content) {
            System.out.println(c.getTitle());
        }
    }
}