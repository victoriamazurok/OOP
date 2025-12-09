using System;

namespace Premiere
{
    /// <summary>
    /// Клас Program є точкою входу в програму.
    /// Демонструє практичне використання всіх створених класів та інтерфейсів.
    /// Реалізує сценарії взаємодії користувачів з системою.
    /// </summary>
    public class Program
    {
        public static void Main(string[] args)
        {
            Console.WriteLine("═══════════════════════════════════════════════════════");
            Console.WriteLine("  ІМІТАЦІЙНИЙ ПРОТОТИП: Система управління прем'єрами");
            Console.WriteLine("═══════════════════════════════════════════════════════\n");

            // Ініціалізація системи
            PremierSystem system = new PremierSystem("YouTube Premier");

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 1: Реєстрація користувачів
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 1]: Реєстрація користувачів\n");

            // Створення та реєстрація глядачів
            Viewer viewer1 = new Viewer("Іван Петренко", "ivan.petrenko@email.com");
            Viewer viewer2 = new Viewer("Марія Сидоренко", "maria.sydorenko@email.com");

            system.RegisterUser(viewer1);
            system.RegisterUser(viewer2);

            // Створення та реєстрація автора
            Author author = new Author("Максим Лисенко", "maksym.lysenko@email.com", "Мій Канал");
            system.RegisterUser(author);

            // Створення та реєстрація модератора
            Moderator moderator = new Moderator("Олена Кравченко", "olena.kravchenko@email.com", "Content Moderation");
            system.RegisterUser(moderator);

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 2: Публікація контенту
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 2]: Публікація контенту\n");

            Content video1 = new Content(
                "Вступ до C# OOP",
                "Легкий вступ до об'єктно-орієнтованого програмування на C#",
                "Максим Лисенко"
            );

            Content video2 = new Content(
                "Практичні приклади ООП",
                "Практичні приклади використання класів, інтерфейсів та спадкування",
                "Максим Лисенко"
            );

            system.PublishContent(video1);
            system.PublishContent(video2);

            author.PublishContent(video1);
            author.PublishContent(video2);

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 3: Планування прем'єри
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 3]: Планування прем'єри\n");

            author.SchedulePremiere(video1);
            author.StartChat("Обговорення основ ООП");

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 4: Взаємодія глядачів з контентом
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 4]: Взаємодія глядачів з контентом\n");

            // Глядач 1 - підписується та переглядає
            viewer1.Subscribe();
            viewer1.WatchContent(video1);
            viewer1.CommentOnContent("Дуже цікавий матеріал! Дякую!");
            video1.AddComment(viewer1.GetName(), "Дуже цікавий матеріал! Дякую!");

            Console.WriteLine();

            // Глядач 2 - підписується та переглядає інший контент
            viewer2.Subscribe();
            viewer2.WatchContent(video2);
            viewer2.WatchContent(video1);
            viewer2.CommentOnContent("Чекаю продовження!");
            video2.AddComment(viewer2.GetName(), "Чекаю продовження!");

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 5: Модерація контенту
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 5]: Модерація контенту\n");

            moderator.ModerateChat("Це спам?");
            moderator.BlockSpam("Купіть дешево!");
            moderator.DeleteInappropriateComment("Неприпустимий коментар");

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 6: Дії користувачів через поліморфізм
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 6]: Демонстрація поліморфізму\n");

            User[] allUsers = { viewer1, viewer2, author, moderator };
            Console.WriteLine("── ВСІ КОРИСТУВАЧІ ВИКОНУЮТЬ СВОЇ ДІЇ ──");
            foreach (User user in allUsers)
            {
                user.PerformAction();
            }

            // ─────────────────────────────────────────────────────────────
            // СЦЕНА 7: Апеляція модератора
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[СЦЕНА 7]: Апеляція на модеративне рішення\n");

            author.SubmitAppeal("Мій контент було помилково заблоковано");

            // ─────────────────────────────────────────────────────────────
            // ЗАВЕРШЕННЯ: Статистика системи
            // ─────────────────────────────────────────────────────────────
            Console.WriteLine("\n[ЗАВЕРШЕННЯ]: Статистика системи\n");

            system.PrintStatistics();
            system.PrintUsersInfo();
            system.PrintContentInfo();

            Console.WriteLine("═══════════════════════════════════════════════════════");
            Console.WriteLine("  ПРОГРАМА УСПІШНО ЗАВЕРШЕНА");
            Console.WriteLine("═══════════════════════════════════════════════════════");
        }
    }
}
