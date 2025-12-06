package com.premiere;

/**
 * Клас Author реалізує роль автора у системі.
 * Демонструє наслідування від User та мультиплексну поведінку.
 */
public class Author extends User {
    // Приватні атрибути - інкапсуляція
    private int publishedCount;
    private String channel;

    /**
     * Конструктор для ініціалізації автора
     */
    public Author(String name, String email, String channel) {
        super(name, email);
        this.channel = channel;
        this.publishedCount = 0;
    }

    /**
     * Метод для планування прем'єри
     */
    public void schedulePremiere(Content content) {
        notifySystem("Спланував прем'єру: \"" + content.getTitle() + "\" на каналі \"" + channel + "\"");
    }

    /**
     * Метод для запуску чату під час прем'єри
     */
    public void startChat(String topic) {
        notifySystem("Запустив чат на тему: \"" + topic + "\"");
    }

    /**
     * Метод для подання апеляції на рішення модератора
     */
    public void submitAppeal(String reason) {
        notifySystem("Подав апеляцію на модеративне рішення: \"" + reason + "\"");
    }

    /**
     * Метод для публікації контенту
     */
    public void publishContent(Content content) {
        this.publishedCount++;
        System.out.println("[ПУБЛІКАЦІЯ]: Автор \"" + name + "\" опублікував контент: \"" + content.getTitle() + "\"");
    }

    /**
     * Реалізація абстрактного методу performAction
     */
    @Override
    public void performAction() {
        System.out.println("[ДІЯ АВТОРА]: " + name + " планує прем'єру та керує контентом");
    }

    /**
     * Геттер для отримання назви каналу
     */
    public String getChannel() {
        return channel;
    }

    /**
     * Геттер для отримання кількості опублікованого контенту
     */
    public int getPublishedCount() {
        return publishedCount;
    }
}
