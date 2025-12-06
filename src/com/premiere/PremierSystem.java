package com.premiere;

import java.util.ArrayList;
import java.util.List;

/**
 * Клас PremierSystem керує системою прем'єр та взаємодією користувачів.
 * Демонструє принцип агрегації та композиції.
 * Також реалізує простий Singleton патерн для управління користувачами.
 */
public class PremierSystem {
    // Приватні атрибути - інкапсуляція
    private List<User> users;
    private List<Content> content;
    private String systemName;

    /**
     * Конструктор для ініціалізації системи
     */
    public PremierSystem(String systemName) {
        this.systemName = systemName;
        this.users = new ArrayList<>();
        this.content = new ArrayList<>();
    }

    /**
     * Метод для реєстрації користувача у системі
     */
    public void registerUser(User user) {
        users.add(user);
        System.out.println("[СИСТЕМА]: Користувач \"" + user.getName() + "\" успішно зареєстровано");
    }

    /**
     * Метод для публікації контенту
     */
    public void publishContent(Content newContent) {
        content.add(newContent);
        System.out.println("[СИСТЕМА]: Контент \"" + newContent.getTitle() + "\" опублікований");
    }

    /**
     * Метод для отримання всіх користувачів
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Метод для отримання всього контенту
     */
    public List<Content> getAllContent() {
        return new ArrayList<>(content);
    }

    /**
     * Метод для відображення статистики системи
     */
    public void printStatistics() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     СТАТИСТИКА СИСТЕМИ \"" + systemName + "\"     ║");
        System.out.println("╠════════════════════════════════════════╣");
        System.out.println("║ Зареєстрованих користувачів: " + users.size());
        System.out.println("║ Опублікованого контенту: " + content.size());
        System.out.println("╚════════════════════════════════════════╝\n");
    }

    /**
     * Метод для виведення інформації про користувачів
     */
    public void printUsersInfo() {
        System.out.println("\n── ІНФОРМАЦІЯ ПРО КОРИСТУВАЧІВ ──");
        for (User user : users) {
            System.out.println("• " + user.getName() + " (" + user.getEmail() + ")");
            if (user instanceof Viewer) {
                Viewer viewer = (Viewer) user;
                System.out.println("  Тип: Глядач | Переглядів: " + viewer.getWatchedCount());
            } else if (user instanceof Author) {
                Author author = (Author) user;
                System.out.println("  Тип: Автор | Канал: " + author.getChannel() + " | Опублікованого: " + author.getPublishedCount());
            } else if (user instanceof Moderator) {
                Moderator moderator = (Moderator) user;
                System.out.println("  Тип: Модератор | Відділ: " + moderator.getDepartment() + " | Заблокованого: " + moderator.getBlockedCount());
            }
        }
        System.out.println();
    }

    /**
     * Метод для виведення інформації про контент
     */
    public void printContentInfo() {
        System.out.println("\n── ІНФОРМАЦІЯ ПРО КОНТЕНТ ──");
        for (Content c : content) {
            c.display();
        }
    }
}
