using System;

namespace Premiere
{
    /// <summary>
    /// Абстрактний клас User визначає базову поведінку для всіх користувачів системи.
    /// Реалізує прообраз та абстракцію - ключові принципи ООП.
    /// </summary>
    public abstract class User
    {
        // Захищені атрибути - інкапсуляція
        protected string Name { get; set; }
        protected string Email { get; set; }

        /// <summary>
        /// Конструктор для ініціалізації користувача
        /// </summary>
        public User(string name, string email)
        {
            Name = name;
            Email = email;
        }

        /// <summary>
        /// Властивість для отримання імені користувача
        /// </summary>
        public string GetName()
        {
            return Name;
        }

        /// <summary>
        /// Властивість для отримання електронної пошти користувача
        /// </summary>
        public string GetEmail()
        {
            return Email;
        }

        /// <summary>
        /// Абстрактний метод - кожна роль користувача повинна реалізувати свої дії
        /// </summary>
        public abstract void PerformAction();

        /// <summary>
        /// Загальний метод для повідомлення системи про дії користувача
        /// </summary>
        public void NotifySystem(string message)
        {
            Console.WriteLine($"[СИСТЕМА]: {Name} ({Email}) - {message}");
        }
    }
}
