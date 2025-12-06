package com.premiere;

/**
 * Абстрактний клас User визначає базову поведінку для всіх користувачів системи.
 * Реалізує прообраз та абстракцію - ключові принципи ООП.
 */
public abstract class User {
    // Захищені атрибути - інкапсуляція
    protected String name;
    protected String email;

    /**
     * Конструктор для ініціалізації користувача
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Геттер для отримання імені користувача
     */
    public String getName() {
        return name;
    }

    /**
     * Геттер для отримання електронної пошти користувача
     */
    public String getEmail() {
        return email;
    }

    /**
     * Абстрактний метод - кожна роль користувача повинна реалізувати свої дії
     */
    public abstract void performAction();

    /**
     * Загальний метод для повідомлення системи про дії користувача
     */
    public void notifySystem(String message) {
        System.out.println("[СИСТЕМА]: " + name + " (" + email + ") - " + message);
    }
}
