package com.videoplatform;

/**
 * Інтерфейс Engageable визначає поведінку для взаємодії користувачів з контентом.
 * Демонструє принцип поліморфізму.
 */
public interface Engageable {
    /**
     * Метод для підписки на контент
     */
    void subscribe();

    /**
     * Метод для перегляду контенту
     */
    void watchContent(Content content);

    /**
     * Метод для коментування контенту
     */
    void commentOnContent(String comment);
}
