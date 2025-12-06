package com.videoplatform;

import com.google.inject.Inject; // Додаємо імпорт для @Inject

/**
 * Клас Moderator реалізує роль модератора у системі.
 * Демонструє наслідування від User та реалізацію Moderatable інтерфейсу.
 */
public class Moderator extends User implements Moderatable {
    // Приватні атрибути - інкапсуляція
    private int blockedCount;
    private String department;

    /**
     * Конструктор для ініціалізації модератора
     */
    @Inject // Додаємо анотацію @Inject для конструктора
    public Moderator(String name, String email, String department) {
        super(name, email);
        this.department = department;
        this.blockedCount = 0;
    }

    /**
     * Реалізація методу для блокування спаму
     */
    @Override
    public void blockSpam(String content) {
        this.blockedCount++;
        notifySystem("Заблокував спам: \"" + content + "\"");
    }

    /**
     * Реалізація методу для модерації чату
     */
    @Override
    public void moderateChat(String message) {
        System.out.println("[МОДЕРАЦІЯ ЧАТУ]: " + name + " перевіряє повідомлення: \"" + message + "\"");
    }

    /**
     * Реалізація методу для видалення неприпустимих коментарів
     */
    @Override
    public void deleteInappropriateComment(String comment) {
        notifySystem("Видалив неприпустимий коментар: \"" + comment + "\"");
    }

    /**
     * Реалізація абстрактного методу performAction
     */
    @Override
    public void performAction() {
        System.out.println("[ДІЯ МОДЕРАТОРА]: " + name + " модерує контент та чат");
    }

    /**
     * Геттер для отримання назви відділу модератора
     */
    public String getDepartment() {
        return department;
    }

    /**
     * Геттер для отримання кількості заблокованого контенту
     */
    public int getBlockedCount() {
        return blockedCount;
    }
}
