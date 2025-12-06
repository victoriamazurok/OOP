package com.videoplatform;

import com.google.inject.Inject; // Додаємо імпорт для @Inject

import java.util.ArrayList;
import java.util.List;

public class Content {
    private String title;
    private String description;
    private int viewCount;
    private List<String> comments; // Список коментарів

    /**
     * Конструктор для ініціалізації контенту
     */
    @Inject // Додаємо анотацію @Inject для конструктора
    public Content(String title, String description) {
        this.title = title;
        this.description = description;
        this.viewCount = 0; // Початкове значення
        this.comments = new ArrayList<>(); // Ініціалізація списку коментарів
    }

    public void addView() {
        viewCount++;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    // Доданий метод для додавання коментарів
    public void addComment(String name, String comment) {
        comments.add(name + ": " + comment); // Додаємо коментар з іменем користувача
        System.out.println("Коментар додано: " + name + ": " + comment);
    }

    // Геттер для отримання коментарів
    public List<String> getComments() {
        return comments;
    }
}
