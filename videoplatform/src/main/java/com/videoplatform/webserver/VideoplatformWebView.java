package com.videoplatform.webserver;

import com.google.inject.Inject;
import com.videoplatform.VideoplatformController;
import com.videoplatform.Video;
import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Веб-вигляд для відображення даних про відео
 * Реалізує REST API та обслуговує статичні файли HTML/CSS/JS
 */
public class VideoplatformWebView {

    private static final Logger logger = LoggerFactory.getLogger(VideoplatformWebView.class);
    private final VideoplatformController videoplatformController;
    private Javalin app;

    @Inject
    public VideoplatformWebView(VideoplatformController videoplatformController) {
        this.videoplatformController = videoplatformController;
    }

    /**
     * Запускає веб-сервер на вказаному порту
     * @param port порт для запуску сервера
     */
    public void start(int port) {
        app = Javalin.create().start(port);

        logger.info("Веб-сервер запущено на порту: {}", port);

        // Маршрут для отримання всіх відео
        app.get("/videos", ctx -> {
            try {
                List<Video> videos = videoplatformController.getAllVideos();
                ctx.json(videos);
                logger.info("Запит на отримання відео. Повернено {} записів", videos.size());
            } catch (Exception e) {
                logger.error("Помилка при отриманні відео", e);
                ctx.status(500).json(new ErrorResponse("Помилка при отриманні даних"));
            }
        });

        // Маршрут для перевірки здоров'я сервера
        app.get("/health", ctx -> {
            ctx.json(new HealthResponse("OK"));
            logger.debug("Запит на перевірку здоров'я сервера");
        });

        // Маршрут для головної сторінки
        app.get("/", ctx -> {
            try {
                String html = loadResourceFile("index.html");
                ctx.contentType("text/html").result(html);
            } catch (Exception e) {
                ctx.status(404).result("Not Found");
            }
        });

        // Маршрут для CSS
        app.get("/style.css", ctx -> {
            try {
                String css = loadResourceFile("style.css");
                ctx.contentType("text/css").result(css);
            } catch (Exception e) {
                ctx.status(404).result("Not Found");
            }
        });

        // Маршрут для JavaScript
        app.get("/script.js", ctx -> {
            try {
                String js = loadResourceFile("script.js");
                ctx.contentType("application/javascript").result(js);
            } catch (Exception e) {
                ctx.status(404).result("Not Found");
            }
        });

        logger.info("API маршрути налаштовані успішно");
    }

    /**
     * Завантажує файл ресурсу з класпасу
     * @param resourceName назва ресурсу
     * @return вміст файлу
     */
    private String loadResourceFile(String resourceName) {
        try {
            String content = new String(Files.readAllBytes(
                Paths.get(getClass().getClassLoader().getResource(resourceName).getPath())
            ), "UTF-8");
            return content;
        } catch (Exception e) {
            logger.warn("Не вдалося завантажити ресурс як файл: {}. Спроба завантажити як classpath ресурс.", resourceName);
            try {
                java.util.Scanner scanner = new java.util.Scanner(
                    getClass().getClassLoader().getResourceAsStream(resourceName)
                ).useDelimiter("\\A");
                return scanner.hasNext() ? scanner.next() : "";
            } catch (Exception e2) {
                logger.warn("Остаточна невдача при завантаженні ресурсу: {}. Використовується вмісний за замовчуванням.", resourceName);
                // Повертаємо базовий HTML/CSS/JS як fallback
                if ("index.html".equals(resourceName)) {
                    return getDefaultHtml();
                } else if ("style.css".equals(resourceName)) {
                    return getDefaultCss();
                } else if ("script.js".equals(resourceName)) {
                    return getDefaultJs();
                }
                throw new RuntimeException("Resource not found: " + resourceName, e);
            }
        }
    }

    /**
     * Зупиняє веб-сервер
     */
    public void stop() {
        if (app != null) {
            app.stop();
            logger.info("Веб-сервер зупинено");
        }
    }

    /**
     * Повертає HTML за замовчуванням
     */
    private String getDefaultHtml() {
        return "<!DOCTYPE html>\n" +
            "<html lang=\"uk\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Система управління відео</title>\n" +
            "    <link rel=\"stylesheet\" href=\"style.css\">\n" +
            "</head>\n" +
            "<body>\n" +
            "    <div class=\"container\">\n" +
            "        <h1>Список видео</h1>\n" +
            "        <p class=\"subtitle\">Всі доступні видео на платформі</p>\n" +
            "        <div id=\"message\" class=\"message\"></div>\n" +
            "        <div class=\"table-container\">\n" +
            "            <table id=\"videos-table\">\n" +
            "                <thead>\n" +
            "                    <tr>\n" +
            "                        <th>№</th>\n" +
            "                        <th>Назва видео</th>\n" +
            "                    </tr>\n" +
            "                </thead>\n" +
            "                <tbody id=\"tableBody\">\n" +
            "                    <tr>\n" +
            "                        <td colspan=\"2\" class=\"empty-state\">Завантаження даних...</td>\n" +
            "                    </tr>\n" +
            "                </tbody>\n" +
            "            </table>\n" +
            "        </div>\n" +
            "        <div class=\"footer\">\n" +
            "            <p>Всього видео: <span id=\"totalCount\">0</span></p>\n" +
            "        </div>\n" +
            "    </div>\n" +
            "    <script src=\"script.js\"></script>\n" +
            "</body>\n" +
            "</html>";
    }

    /**
     * Повертає CSS за замовчуванням
     */
    private String getDefaultCss() {
        return "* {margin: 0; padding: 0; box-sizing: border-box;}\n" +
            "body {font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;\n" +
            "background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);min-height: 100vh;padding: 20px;}\n" +
            ".container {max-width: 1200px;margin: 0 auto;background-color: #ffffff;border-radius: 12px;\n" +
            "box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3);padding: 40px;}\n" +
            "h1 {text-align: center;color: #333;margin-bottom: 10px;font-size: 2.5em;}\n" +
            ".subtitle {text-align: center;color: #666;margin-bottom: 30px;font-size: 1.1em;}\n" +
            "table {width: 100%;border-collapse: collapse;background-color: #ffffff;}\n" +
            "th {background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);color: white;padding: 16px;text-align: left;font-weight: 600;}\n" +
            "td {padding: 14px 16px;border-bottom: 1px solid #e0e0e0;}\n" +
            ".empty-state {text-align: center;color: #999;font-style: italic;padding: 40px 16px;}\n" +
            ".footer {text-align: right;margin-top: 30px;padding-top: 20px;border-top: 1px solid #e0e0e0;color: #666;}\n" +
            "#totalCount {font-weight: bold;color: #667eea;font-size: 1.1em;}";
    }

    /**
     * Повертає JavaScript за замовчуванням
     */
    private String getDefaultJs() {
        return "const API_URL = window.location.hostname.includes('localhost') \n" +
            "    ? 'http://localhost:8080'\n" +
            "    : window.location.origin;\n" +
            "\n" +
            "document.addEventListener('DOMContentLoaded', () => {loadVideos();});\n" +
            "\n" +
            "function loadVideos() {\n" +
            "  const tableBody = document.querySelector('#videos-table tbody');\n" +
            "  const totalCountElement = document.querySelector('#totalCount');\n" +
            "  const messageElement = document.querySelector('#message');\n" +
            "\n" +
            "  messageElement.textContent = '';\n" +
            "  tableBody.innerHTML = '';\n" +
            "\n" +
            "  fetch(`${API_URL}/videos`)\n" +
            "    .then(response => response.json())\n" +
            "    .then(videos => {\n" +
            "      if (!videos || videos.length === 0) {\n" +
            "        tableBody.innerHTML = '<tr><td colspan=\"2\" class=\"empty-state\">Немає даних</td></tr>';\n" +
            "        return;\n" +
            "      }\n" +
            "      videos.forEach((video, index) => {\n" +
            "        const row = document.createElement('tr');\n" +
            "        row.innerHTML = '<td>' + (index + 1) + '</td><td>' + video.title + '</td>';\n" +
            "        tableBody.appendChild(row);\n" +
            "      });\n" +
            "      document.querySelector('#totalCount').textContent = videos.length;\n" +
            "      messageElement.textContent = 'Успішно завантажено ' + videos.length + ' видео';\n" +
            "      messageElement.className = 'message success';\n" +
            "    })\n" +
            "    .catch(error => {\n" +
            "      console.error('Помилка:', error);\n" +
            "      tableBody.innerHTML = '<tr><td colspan=\"2\" class=\"empty-state\">Помилка при завантаженні</td></tr>';\n" +
            "      messageElement.textContent = 'Помилка при завантаженні даних: ' + error.message;\n" +
            "      messageElement.className = 'message error';\n" +
            "    });\n" +
            "}";
    }

    /**
     * Клас для відповіді про помилку
     */
    public static class ErrorResponse {
        public String message;

        public ErrorResponse(String message) {
            this.message = message;
        }
    }

    /**
     * Клас для відповіді про стан здоров'я
     */
    public static class HealthResponse {
        public String status;

        public HealthResponse(String status) {
            this.status = status;
        }
    }
}
