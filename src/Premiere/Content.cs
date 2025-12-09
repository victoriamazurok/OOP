using System;

namespace Premiere
{
    /// <summary>
    /// Клас Content відображає контент (відео) у системі.
    /// Демонструє концепцію об'єкта з атрибутами та методами.
    /// </summary>
    public class Content
    {
        // Приватні атрибути - інкапсуляція
        private string Title { get; set; }
        private string Description { get; set; }
        private string Author { get; set; }
        private int ViewCount { get; set; }
        private int CommentCount { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації контенту
        /// </summary>
        public Content(string title, string description, string author)
        {
            Title = title;
            Description = description;
            Author = author;
            ViewCount = 0;
            CommentCount = 0;
        }

        /// <summary>
        /// Метод для отримання назви контенту
        /// </summary>
        public string GetTitle()
        {
            return Title;
        }

        /// <summary>
        /// Метод для отримання опису контенту
        /// </summary>
        public string GetDescription()
        {
            return Description;
        }

        /// <summary>
        /// Метод для отримання імені автора
        /// </summary>
        public string GetAuthor()
        {
            return Author;
        }

        /// <summary>
        /// Метод для отримання кількості переглядів
        /// </summary>
        public int GetViewCount()
        {
            return ViewCount;
        }

        /// <summary>
        /// Метод для отримання кількості коментарів
        /// </summary>
        public int GetCommentCount()
        {
            return CommentCount;
        }

        /// <summary>
        /// Метод для відображення контенту
        /// </summary>
        public void Display()
        {
            Console.WriteLine("┌─────────────────────────────────┐");
            Console.WriteLine($"│ КОНТЕНТ: {Title}");
            Console.WriteLine($"│ Опис: {Description}");
            Console.WriteLine($"│ Автор: {Author}");
            Console.WriteLine($"│ Переглядів: {ViewCount} | Коментарів: {CommentCount}");
            Console.WriteLine("└─────────────────────────────────┘");
        }

        /// <summary>
        /// Метод для збільшення лічильника переглядів
        /// </summary>
        public void AddView()
        {
            ViewCount++;
            Console.WriteLine($"[КОНТЕНТ]: Переглід відео \"{Title}\" - всього переглядів: {ViewCount}");
        }

        /// <summary>
        /// Метод для додавання коментаря
        /// </summary>
        public void AddComment(string viewer, string comment)
        {
            CommentCount++;
            Console.WriteLine($"[КОМЕНТАР]: {viewer} прокоментував: \"{comment}\"");
        }
    }
}
