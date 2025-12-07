package com.videoplatform;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.videoplatform.webserver.PayrollWebView;

public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ІМІТАЦІЙНИЙ ПРОТОТИП: Система управління прем'єрами");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Створення інжектора Guice з модулем
        Injector injector = Guice.createInjector(new VideoplatformModule());

        // Використання інжектора для отримання екземпляра PremierSystem
        PremierSystem system = injector.getInstance(PremierSystem.class);

        // Реєстрація користувачів
        Viewer viewer1 = injector.getInstance(Viewer.class);
        Viewer viewer2 = injector.getInstance(Viewer.class);
        system.registerUser(viewer1);
        system.registerUser(viewer2);

        Author author = injector.getInstance(Author.class);
        system.registerUser(author);

        Moderator moderator = injector.getInstance(Moderator.class);
        system.registerUser(moderator);

        // Публікація контенту
        Content video1 = injector.getInstance(Content.class);
        Content video2 = injector.getInstance(Content.class);
        system.publishContent(video1);
        system.publishContent(video2);
        author.publishContent(video1);
        author.publishContent(video2);

        // Взаємодія глядачів з контентом
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

        // Модерація контенту
        System.out.println("\n[СЦЕНА 5]: Модерація контенту\n");

        moderator.moderateChat("Це спам?");
        moderator.blockSpam("Купіть дешево!");
        moderator.deleteInappropriateComment("Неприпустимий коментар");

        // Виведення статистики
        system.printStatistics();
        system.printUsersInfo();
        system.printContentInfo();

        // Запуск веб-сервера
        runWebMode(injector);

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ПРОГРАМА УСПІШНО ЗАВЕРШЕНА");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    private static void runWebMode(Injector injector) {
        // Отримуємо екземпляр веб-вигляду та запускаємо сервер на порту 8080
        PayrollWebView webView = injector.getInstance(PayrollWebView.class);
        webView.start(8080);
    }
}
