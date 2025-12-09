using System;

namespace Premiere
{
    /// <summary>
    /// Клас Author реалізує роль автора у системі.
    /// Демонструє наслідування від User та мультиплексну поведінку.
    /// </summary>
    public class Author : User
    {
        // Приватні атрибути - інкапсуляція
        private int PublishedCount { get; set; }
        private string Channel { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації автора
        /// </summary>
        public Author(string name, string email, string channel) : base(name, email)
        {
            Channel = channel;
            PublishedCount = 0;
        }

        /// <summary>
        /// Метод для планування прем'єри
        /// </summary>
        public void SchedulePremiere(Content content)
        {
            NotifySystem($"Спланував прем'єру: \"{content.GetTitle()}\" на каналі \"{Channel}\"");
        }

        /// <summary>
        /// Метод для запуску чату під час прем'єри
        /// </summary>
        public void StartChat(string topic)
        {
            NotifySystem($"Запустив чат на тему: \"{topic}\"");
        }

        /// <summary>
        /// Метод для подання апеляції на рішення модератора
        /// </summary>
        public void SubmitAppeal(string reason)
        {
            NotifySystem($"Подав апеляцію на модеративне рішення: \"{reason}\"");
        }

        /// <summary>
        /// Метод для публікації контенту
        /// </summary>
        public void PublishContent(Content content)
        {
            PublishedCount++;
            Console.WriteLine($"[ПУБЛІКАЦІЯ]: Автор \"{Name}\" опублікував контент: \"{content.GetTitle()}\"");
        }

        /// <summary>
        /// Реалізація абстрактного методу PerformAction
        /// </summary>
        public override void PerformAction()
        {
            Console.WriteLine($"[ДІЯ АВТОРА]: {Name} планує прем'єру та керує контентом");
        }

        /// <summary>
        /// Метод для отримання назви каналу
        /// </summary>
        public string GetChannel()
        {
            return Channel;
        }

        /// <summary>
        /// Метод для отримання кількості опублікованого контенту
        /// </summary>
        public int GetPublishedCount()
        {
            return PublishedCount;
        }
    }
}
