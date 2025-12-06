package com.premiere;

/**
 * Інтерфейс Moderatable визначає поведінку для модерації контенту.
 * Демонструє принцип поліморфізму та реалізацію інтерфейсів.
 */
public interface Moderatable {
    /**
     * Метод для блокування спаму
     */
    void blockSpam(String content);

    /**
     * Метод для модерації чату
     */
    void moderateChat(String message);

    /**
     * Метод для видалення неприпустимих коментарів
     */
    void deleteInappropriateComment(String comment);
}
