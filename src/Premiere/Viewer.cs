using System;

namespace Premiere
{
    /// <summary>
    /// Клас Viewer реалізує роль глядача у системі.
    /// Демонструє наслідування від User та реалізацію IEngageable інтерфейсу.
    /// </summary>
    public class Viewer : User, IEngageable
    {
        // Приватні атрибути - інкапсуляція
        private bool Subscribed { get; set; }
        private int WatchedCount { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації глядача
        /// </summary>
        public Viewer(string name, string email) : base(name, email)
        {
            Subscribed = false;
            WatchedCount = 0;
        }

        /// <summary>
        /// Реалізація методу для підписки на прем'єру
        /// </summary>
        public void Subscribe()
        {
            Subscribed = true;
            NotifySystem("Підписався на прем'єру");
        }

        /// <summary>
        /// Реалізація методу для перегляду контенту
        /// </summary>
        public void WatchContent(Content content)
        {
            if (Subscribed)
            {
                content.AddView();
                WatchedCount++;
                NotifySystem($"Переглядає відео: \"{content.GetTitle()}\"");
            }
            else
            {
                NotifySystem("Не може переглядати, не підписаний на прем'єру");
            }
        }

        /// <summary>
        /// Реалізація методу для коментування контенту
        /// </summary>
        public void CommentOnContent(string comment)
        {
            NotifySystem($"Залишив коментар: \"{comment}\"");
        }

        /// <summary>
        /// Реалізація абстрактного методу PerformAction
        /// </summary>
        public override void PerformAction()
        {
            Console.WriteLine($"[ДІЯ ГЛЯДАЧА]: {Name} взаємодіє з контентом (переглядає, коментує)");
        }

        /// <summary>
        /// Метод для перевірки статусу підписки
        /// </summary>
        public bool IsSubscribed()
        {
            return Subscribed;
        }

        /// <summary>
        /// Метод для отримання кількості переглянутих відео
        /// </summary>
        public int GetWatchedCount()
        {
            return WatchedCount;
        }
    }
}
