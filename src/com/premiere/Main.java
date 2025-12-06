package com.premiere;

/**
 * Клас Main є точкою входу в програму.
 * Демонструє практичне використання всіх створених класів та інтерфейсів.
 * Реалізує сценарії взаємодії користувачів з системою.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ІМІТАЦІЙНИЙ ПРОТОТИП: Система управління прем'єрами");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Ініціалізація системи
        PremierSystem system = new PremierSystem("YouTube Premier");

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 1: Реєстрація користувачів
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 1]: Реєстрація користувачів\n");

        // Створення та реєстрація глядачів
        Viewer viewer1 = new Viewer("Іван Петренко", "ivan.petrenko@email.com");
        Viewer viewer2 = new Viewer("Марія Сидоренко", "maria.sydorenko@email.com");

        system.registerUser(viewer1);
        system.registerUser(viewer2);

        // Створення та реєстрація автора
        Author author = new Author("Максим Лисенко", "maksym.lysenko@email.com", "Мій Канал");
        system.registerUser(author);

        // Створення та реєстрація модератора
        Moderator moderator = new Moderator("Олена Кравченко", "olena.kravchenko@email.com", "Content Moderation");
        system.registerUser(moderator);

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 2: Публікація контенту
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 2]: Публікація контенту\n");

        Content video1 = new Content(
            "Вступ до Java OOP",
            "Легкий вступ до об'єктно-орієнтованого програмування на Java",
            "Максим Лисенко"
        );

        Content video2 = new Content(
            "Практичні приклади ООП",
            "Практичні приклади використання класів, інтерфейсів та спадкування",
            "Максим Лисенко"
        );

        system.publishContent(video1);
        system.publishContent(video2);

        author.publishContent(video1);
        author.publishContent(video2);

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 3: Планування прем'єри
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 3]: Планування прем'єри\n");

        author.schedulePremiere(video1);
        author.startChat("Обговорення основ ООП");

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 4: Взаємодія глядачів з контентом
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 4]: Взаємодія глядачів з контентом\n");

        // Глядач 1 - підписується та переглядає
        viewer1.subscribe();
        viewer1.watchContent(video1);
        viewer1.commentOnContent("Дуже цікавий матеріал! Дякую!");
        video1.addComment(viewer1.getName(), "Дуже цікавий матеріал! Дякую!");

        System.out.println();

        // Глядач 2 - підписується та переглядає інший контент
        viewer2.subscribe();
        viewer2.watchContent(video2);
        viewer2.watchContent(video1);
        viewer2.commentOnContent("Чекаю продовження!");
        video2.addComment(viewer2.getName(), "Чекаю продовження!");

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 5: Модерація контенту
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 5]: Модерація контенту\n");

        moderator.moderateChat("Це спам?");
        moderator.blockSpam("Купіть дешево!");
        moderator.deleteInappropriateComment("Неприпустимий коментар");

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 6: Дії користувачів через поліморфізм
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 6]: Демонстрація поліморфізму\n");

        User[] allUsers = { viewer1, viewer2, author, moderator };
        System.out.println("── ВСІ КОРИСТУВАЧІ ВИКОНУЮТЬ СВОЇ ДІЇ ──");
        for (User user : allUsers) {
            user.performAction();
        }

        // ─────────────────────────────────────────────────────────────
        // СЦЕНА 7: Апеляція модератора
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[СЦЕНА 7]: Апеляція на модеративне рішення\n");

        author.submitAppeal("Мій контент було помилково заблоковано");

        // ─────────────────────────────────────────────────────────────
        // ЗАВЕРШЕННЯ: Статистика системи
        // ─────────────────────────────────────────────────────────────
        System.out.println("\n[ЗАВЕРШЕННЯ]: Статистика системи\n");

        system.printStatistics();
        system.printUsersInfo();
        system.printContentInfo();

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ПРОГРАМА УСПІШНО ЗАВЕРШЕНА");
        System.out.println("═══════════════════════════════════════════════════════");
    }
}
