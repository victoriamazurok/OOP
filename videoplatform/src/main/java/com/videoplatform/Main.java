package com.videoplatform;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.videoplatform.webserver.VideoplatformWebView;

public class Main {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ІМІТАЦІЙНИЙ ПРОТОТИП: Система управління відео");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // Створення інжектора Guice з модулем
        Injector injector = Guice.createInjector(new VideoplatformModule());

        // Запускаємо веб-сервер для перегляду
        runWebMode(injector);

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("  ВЕБ-СЕРВЕР ЗАПУЩЕНО НА ПОРТУ 8080");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    private static void runWebMode(Injector injector) {
        // Отримуємо екземпляр веб-вигляду та запускаємо сервер на порту 8080
        VideoplatformWebView webView = injector.getInstance(VideoplatformWebView.class);
        webView.start(8080);
    }
}
