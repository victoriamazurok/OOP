namespace Premiere
{
    /// <summary>
    /// Інтерфейс IModeratable визначає поведінку для модерації контенту.
    /// Демонструє принцип поліморфізму та реалізацію інтерфейсів.
    /// </summary>
    public interface IModeratable
    {
        /// <summary>
        /// Метод для блокування спаму
        /// </summary>
        void BlockSpam(string content);

        /// <summary>
        /// Метод для модерації чату
        /// </summary>
        void ModerateChat(string message);

        /// <summary>
        /// Метод для видалення неприпустимих коментарів
        /// </summary>
        void DeleteInappropriateComment(string comment);
    }
}
