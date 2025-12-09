using System;
using System.Collections.Generic;

namespace Premiere
{
    /// <summary>
    /// Клас PremierSystem керує системою прем'єр та взаємодією користувачів.
    /// Демонструє принцип агрегації та композиції.
    /// Також реалізує простий Singleton патерн для управління користувачами.
    /// </summary>
    public class PremierSystem
    {
        // Приватні атрибути - інкапсуляція
        private List<User> Users { get; set; }
        private List<Content> Content { get; set; }
        private string SystemName { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації системи
        /// </summary>
        public PremierSystem(string systemName)
        {
            SystemName = systemName;
            Users = new List<User>();
            Content = new List<Content>();
        }

        /// <summary>
        /// Метод для реєстрації користувача у системі
        /// </summary>
        public void RegisterUser(User user)
        {
            Users.Add(user);
            Console.WriteLine($"[СИСТЕМА]: Користувач \"{user.GetName()}\" успішно зареєстровано");
        }

        /// <summary>
        /// Метод для публікації контенту
        /// </summary>
        public void PublishContent(Content newContent)
        {
            Content.Add(newContent);
            Console.WriteLine($"[СИСТЕМА]: Контент \"{newContent.GetTitle()}\" опублікований");
        }

        /// <summary>
        /// Метод для отримання всіх користувачів
        /// </summary>
        public List<User> GetAllUsers()
        {
            return new List<User>(Users);
        }

        /// <summary>
        /// Метод для отримання всього контенту
        /// </summary>
        public List<Content> GetAllContent()
        {
            return new List<Content>(Content);
        }

        /// <summary>
        /// Метод для відображення статистики системи
        /// </summary>
        public void PrintStatistics()
        {
            Console.WriteLine("\n╔════════════════════════════════════════╗");
            Console.WriteLine($"║     СТАТИСТИКА СИСТЕМИ \"{SystemName}\"     ║");
            Console.WriteLine("╠════════════════════════════════════════╣");
            Console.WriteLine($"║ Зареєстрованих користувачів: {Users.Count}");
            Console.WriteLine($"║ Опублікованого контенту: {Content.Count}");
            Console.WriteLine("╚════════════════════════════════════════╝\n");
        }

        /// <summary>
        /// Метод для виведення інформації про користувачів
        /// </summary>
        public void PrintUsersInfo()
        {
            Console.WriteLine("\n── ІНФОРМАЦІЯ ПРО КОРИСТУВАЧІВ ──");
            foreach (User user in Users)
            {
                Console.WriteLine($"• {user.GetName()} ({user.GetEmail()})");
                
                if (user is Viewer viewer)
                {
                    Console.WriteLine($"  Тип: Глядач | Переглядів: {viewer.GetWatchedCount()}");
                }
                else if (user is Author author)
                {
                    Console.WriteLine($"  Тип: Автор | Канал: {author.GetChannel()} | Опублікованого: {author.GetPublishedCount()}");
                }
                else if (user is Moderator moderator)
                {
                    Console.WriteLine($"  Тип: Модератор | Відділ: {moderator.GetDepartment()} | Заблокованого: {moderator.GetBlockedCount()}");
                }
            }
            Console.WriteLine();
        }

        /// <summary>
        /// Метод для виведення інформації про контент
        /// </summary>
        public void PrintContentInfo()
        {
            Console.WriteLine("\n── ІНФОРМАЦІЯ ПРО КОНТЕНТ ──");
            foreach (Content c in Content)
            {
                c.Display();
            }
        }
    }
}
