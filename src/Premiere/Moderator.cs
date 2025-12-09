using System;

namespace Premiere
{
    /// <summary>
    /// Клас Moderator реалізує роль модератора у системі.
    /// Демонструє наслідування від User та реалізацію IModeratable інтерфейсу.
    /// </summary>
    public class Moderator : User, IModeratable
    {
        // Приватні атрибути - інкапсуляція
        private int BlockedCount { get; set; }
        private string Department { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації модератора
        /// </summary>
        public Moderator(string name, string email, string department) : base(name, email)
        {
            Department = department;
            BlockedCount = 0;
        }

        /// <summary>
        /// Реалізація методу для блокування спаму
        /// </summary>
        public void BlockSpam(string content)
        {
            BlockedCount++;
            NotifySystem($"Заблокував спам: \"{content}\"");
        }

        /// <summary>
        /// Реалізація методу для модерації чату
        /// </summary>
        public void ModerateChat(string message)
        {
            Console.WriteLine($"[МОДЕРАЦІЯ ЧАТУ]: {Name} перевіряє повідомлення: \"{message}\"");
        }

        /// <summary>
        /// Реалізація методу для видалення неприпустимих коментарів
        /// </summary>
        public void DeleteInappropriateComment(string comment)
        {
            NotifySystem($"Видалив неприпустимий коментар: \"{comment}\"");
        }

        /// <summary>
        /// Реалізація абстрактного методу PerformAction
        /// </summary>
        public override void PerformAction()
        {
            Console.WriteLine($"[ДІЯ МОДЕРАТОРА]: {Name} модерує контент та чат");
        }

        /// <summary>
        /// Метод для отримання назви відділу модератора
        /// </summary>
        public string GetDepartment()
        {
            return Department;
        }

        /// <summary>
        /// Метод для отримання кількості заблокованого контенту
        /// </summary>
        public int GetBlockedCount()
        {
            return BlockedCount;
        }
    }
}
