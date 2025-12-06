package com.premiere;

/**
 * Клас Content відображає контент (відео) у системі.
 * Демонструє концепцію об'єкта з атрибутами та методами.
 */
public class Content {
    // Приватні атрибути - інкапсуляція
    private String title;
    private String description;
    private String author;
    private int viewCount;
    private int commentCount;

    /**
     * Конструктор для ініціалізації контенту
     */
    public Content(String title, String description, String author) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.viewCount = 0;
        this.commentCount = 0;
    }

    /**
     * Геттер для отримання назви контенту
     */
    public String getTitle() {
        return title;
    }

    /**
     * Геттер для отримання опису контенту
     */
    public String getDescription() {
        return description;
    }

    /**
     * Геттер для отримання імені автора
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Геттер для отримання кількості переглядів
     */
    public int getViewCount() {
        return viewCount;
    }

    /**
     * Геттер для отримання кількості коментарів
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * Метод для відображення контенту
     */
    public void display() {
        System.out.println("┌─────────────────────────────────┐");
        System.out.println("│ КОНТЕНТ: " + title);
        System.out.println("│ Опис: " + description);
        System.out.println("│ Автор: " + author);
        System.out.println("│ Переглядів: " + viewCount + " | Коментарів: " + commentCount);
        System.out.println("└─────────────────────────────────┘");
    }

    /**
     * Метод для збільшення лічильника переглядів
     */
    public void addView() {
        this.viewCount++;
        System.out.println("[КОНТЕНТ]: Переглід відео \"" + title + "\" - всього переглядів: " + viewCount);
    }

    /**
     * Метод для додавання коментаря
     */
    public void addComment(String viewer, String comment) {
        this.commentCount++;
        System.out.println("[КОМЕНТАР]: " + viewer + " прокоментував: \"" + comment + "\"");
    }
}
