package com.premiere;

/**
 * Клас Viewer реалізує роль глядача у системі.
 * Демонструє наслідування від User та реалізацію Engageable інтерфейсу.
 */
public class Viewer extends User implements Engageable {
    // Приватні атрибути - інкапсуляція
    private boolean subscribed;
    private int watchedCount;

    /**
     * Конструктор для ініціалізації глядача
     */
    public Viewer(String name, String email) {
        super(name, email);
        this.subscribed = false;
        this.watchedCount = 0;
    }

    /**
     * Реалізація методу для підписки на прем'єру
     */
    @Override
    public void subscribe() {
        this.subscribed = true;
        notifySystem("Підписався на прем'єру");
    }

    /**
     * Реалізація методу для перегляду контенту
     */
    @Override
    public void watchContent(Content content) {
        if (subscribed) {
            content.addView();
            this.watchedCount++;
            notifySystem("Переглядає відео: \"" + content.getTitle() + "\"");
        } else {
            notifySystem("Не може переглядати, не підписаний на прем'єру");
        }
    }

    /**
     * Реалізація методу для коментування контенту
     */
    @Override
    public void commentOnContent(String comment) {
        notifySystem("Залишив коментар: \"" + comment + "\"");
    }

    /**
     * Реалізація абстрактного методу performAction
     */
    @Override
    public void performAction() {
        System.out.println("[ДІЯ ГЛЯДАЧА]: " + name + " взаємодіє з контентом (переглядає, коментує)");
    }

    /**
     * Геттер для перевірки статусу підписки
     */
    public boolean isSubscribed() {
        return subscribed;
    }

    /**
     * Геттер для отримання кількості переглянутих відео
     */
    public int getWatchedCount() {
        return watchedCount;
    }
}
