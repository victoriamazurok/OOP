namespace Premiere
{
    /// <summary>
    /// Інтерфейс IEngageable визначає поведінку для взаємодії користувачів з контентом.
    /// Демонструє принцип поліморфізму.
    /// </summary>
    public interface IEngageable
    {
        /// <summary>
        /// Метод для підписки на контент
        /// </summary>
        void Subscribe();

        /// <summary>
        /// Метод для перегляду контенту
        /// </summary>
        void WatchContent(Content content);

        /// <summary>
        /// Метод для коментування контенту
        /// </summary>
        void CommentOnContent(string comment);
    }
}
