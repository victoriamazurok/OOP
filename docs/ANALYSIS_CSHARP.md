# Аналіз UML діаграм C# проекту "Система управління прем'єрами"

## 1. Діаграма варіантів використання (Use Case Diagram)

### Файл: `usecase_csharp.puml`

На основі аналізу C# коду було сгенеровано діаграму варіантів використання з такими компонентами:

**Актори (Доменні акторі):**
- **Application** (замість Main) - керуючий агент системи
- **Глядач (Viewer)** - користувач, який переглядає контент
- **Автор (Author)** - користувач, який публікує контент
- **Модератор (Moderator)** - користувач, який модерує контент

**Варіанти використання (Use Cases):**
- **Реєстрація користувача** - RegisterUser()
- **Публікація контенту** - PublishContent()
- **Планування прем'єри** - SchedulePremiere()
- **Запуск чату** - StartChat()
- **Підписка на прем'єру** - Subscribe()
- **Перегляд відео** - WatchContent()
- **Коментування відео** - CommentOnContent()
- **Модерація чату** - ModerateChat()
- **Блокування спаму** - BlockSpam()
- **Видалення неприпустимих коментарів** - DeleteInappropriateComment()
- **Подання апеляції** - SubmitAppeal()
- **Отримання статистики** - PrintStatistics()

### Відповідність між C# кодом та діаграмою:

| Елемент діаграми | C# реалізація |
|---|---|
| Application | `Program.Main()` класс |
| Глядач | `Viewer` клас |
| Автор | `Author` клас |
| Модератор | `Moderator` клас |
| Реєстрація користувача | `PremierSystem.RegisterUser(User)` |
| Публікація контенту | `PremierSystem.PublishContent(Content)` |
| Планування прем'єри | `Author.SchedulePremiere(Content)` |
| Запуск чату | `Author.StartChat(string)` |
| Підписка | `Viewer.Subscribe()` |
| Перегляд відео | `Viewer.WatchContent(Content)` |
| Коментування | `Viewer.CommentOnContent(string)` |
| Модерація чату | `Moderator.ModerateChat(string)` |
| Блокування спаму | `Moderator.BlockSpam(string)` |
| Видалення коментарів | `Moderator.DeleteInappropriateComment(string)` |
| Подання апеляції | `Author.SubmitAppeal(string)` |
| Статистика | `PremierSystem.PrintStatistics()` |

---

## 2. Діаграма класів (Class Diagram)

### Файл: `class_csharp.mmd`

Діаграма класів демонструє повну архітектуру C# проекту:

**Основні класи та їхні взаємозв'язки:**

| Клас | Описання | Методи (основні) |
|---|---|---|
| **Application** | Точка входу програми (замість Main) | main(), initializeSystem(), registerUsers(), publishContent(), runInteractions(), displayStatistics() |
| **PremierSystem** | Система управління прем'єрами (агрегація) | RegisterUser(), PublishContent(), GetAllUsers(), GetAllContent(), PrintStatistics(), PrintUsersInfo(), PrintContentInfo() |
| **User** (abstract) | Абстрактний базовий клас | GetName(), GetEmail(), PerformAction()*, NotifySystem() |
| **Viewer** | Глядач, реалізує IEngageable | Subscribe(), WatchContent(), CommentOnContent(), IsSubscribed(), GetWatchedCount() |
| **Author** | Автор контенту | SchedulePremiere(), StartChat(), SubmitAppeal(), PublishContent(), GetChannel(), GetPublishedCount() |
| **Moderator** | Модератор, реалізує IModeratable | BlockSpam(), ModerateChat(), DeleteInappropriateComment(), GetDepartment(), GetBlockedCount() |
| **Content** | Контент (відео) | GetTitle(), GetDescription(), GetAuthor(), GetViewCount(), GetCommentCount(), Display(), AddView(), AddComment() |
| **IEngageable** (interface) | Інтерфейс для взаємодії з контентом | Subscribe()*, WatchContent()*, CommentOnContent()* |
| **IModeratable** (interface) | Інтерфейс для модерації | BlockSpam()*, ModerateChat()*, DeleteInappropriateComment()* |

**Взаємозв'язки:**
- **Application** → **PremierSystem** (композиція)
- **PremierSystem** → **User** (агрегація, List<User>)
- **PremierSystem** → **Content** (агрегація, List<Content>)
- **User** ← **Viewer**, **Author**, **Moderator** (наслідування)
- **Viewer** → **IEngageable** (реалізація)
- **Moderator** → **IModeratable** (реалізація)
- **Viewer** → **Content** (асоціація)
- **Author** → **Content** (асоціація)
- **Moderator** → **Content** (асоціація)

### Відповідність між C# кодом та діаграмою:

| Елемент діаграми | C# реалізація |
|---|---|
| Application клас | `Program` клас у Program.cs |
| PremierSystem агрегація | `private List<User> Users; private List<Content> Content;` |
| User абстрактний клас | `public abstract class User` |
| Viewer реалізація | `public class Viewer : User, IEngageable` |
| Author реалізація | `public class Author : User` |
| Moderator реалізація | `public class Moderator : User, IModeratable` |
| Content клас | `public class Content` |
| IEngageable інтерфейс | `public interface IEngageable` |
| IModeratable інтерфейс | `public interface IModeratable` |

---

## 3. Діаграма послідовності (Sequence Diagram)

### Файл: `sequence_csharp.mmd`

Діаграма послідовності описує потік взаємодії між компонентами під час виконання програми.

**Послідовність подій:**

1. **Ініціалізація**: Application створює PremierSystem
2. **Сцена 1 - Реєстрація користувачів**: Application створює об'єкти Viewer, Author, Moderator та реєструє їх у системі
3. **Сцена 2 - Публікація контенту**: Application створює Content об'єкти та публікує їх
4. **Сцена 3 - Планування прем'єри**: Author планує прем'єру та запускає чат
5. **Сцена 4 - Взаємодія глядачів**: Viewer підписується, переглядає, коментує контент; Content відбиває просмотри та коментарі
6. **Сцена 5 - Модерація**: Moderator модерує чат, блокує спам, видаляє коментарі
7. **Сцена 6 - Поліморфізм**: Всі користувачі виконують PerformAction()
8. **Сцена 7 - Апеляція**: Author подає апеляцію
9. **Завершення - Статистика**: PremierSystem виводить статистику

### Відповідність між C# кодом та діаграмою:

| Елемент діаграми | C# реалізація |
|---|---|
| Application | `Program.Main()` |
| PremierSystem створення | `new PremierSystem("YouTube Premier")` |
| Viewer створення | `new Viewer(name, email)` |
| Author створення | `new Author(name, email, channel)` |
| Moderator створення | `new Moderator(name, email, department)` |
| RegisterUser | `system.RegisterUser(viewer/author/moderator)` |
| Content створення | `new Content(title, description, author)` |
| PublishContent | `system.PublishContent(video)` |
| SchedulePremiere | `author.SchedulePremiere(content)` |
| StartChat | `author.StartChat(topic)` |
| Subscribe | `viewer.Subscribe()` |
| WatchContent | `viewer.WatchContent(content)` |
| AddView | `content.AddView()` |
| CommentOnContent | `viewer.CommentOnContent(comment)` |
| AddComment | `content.AddComment(viewer, comment)` |
| ModerateChat | `moderator.ModerateChat(message)` |
| BlockSpam | `moderator.BlockSpam(content)` |
| DeleteInappropriateComment | `moderator.DeleteInappropriateComment(comment)` |
| PerformAction | `user.PerformAction()` (поліморфізм) |
| SubmitAppeal | `author.SubmitAppeal(reason)` |
| PrintStatistics | `system.PrintStatistics()` |

---

## 4. Діаграма станів системи (State Diagram)

### Файл: `state_csharp.mmd`

Діаграма станів описує послідовність станів системи під час виконання програми.

**Послідовність станів:**

```
[Початок] → Ініціалізація → РеєстраціяКористувачів → ПублікаціяКонтенту → 
ПланюванняПрем'єри → ЗапускЧату → ВзаємодіяГлядачів → Підписка → ПереглядВідео → 
КоментуванняВідео → МодеріацаКонтенту → БлокуванняСпаму → ВидаленняКоментарів → 
ДемонстраціяПоліморфізму → Апеляція → СтатистикаСистеми → [Кінець]
```

| Стан | C# операція | Описання |
|---|---|---|
| Ініціалізація | `new PremierSystem()` | Створення системи |
| РеєстраціяКористувачів | `RegisterUser()` | Реєстрація користувачів |
| ПублікаціяКонтенту | `PublishContent()` | Публікація контенту |
| ПланюванняПрем'єри | `SchedulePremiere()` | Планування прем'єри |
| ЗапускЧату | `StartChat()` | Запуск чату |
| ВзаємодіяГлядачів | User creation | Створення глядачів |
| Підписка | `Subscribe()` | Підписка на прем'єру |
| ПереглядВідео | `WatchContent()` | Перегляд видео |
| КоментуванняВідео | `CommentOnContent()` | Коментування |
| МодеріацаКонтенту | `ModerateChat()` | Модерація чату |
| БлокуванняСпаму | `BlockSpam()` | Блокування спаму |
| ВидаленняКоментарів | `DeleteInappropriateComment()` | Видалення коментарів |
| ДемонстраціяПоліморфізму | `PerformAction()` | Поліморфна поведінка |
| Апеляція | `SubmitAppeal()` | Подання апеляції |
| СтатистикаСистеми | `PrintStatistics()` | Вивід статистики |

---

## 5. Порівняння з вихідними діаграмами в папці /docs

### 5.1 Діаграма варіантів використання

**Вихідна діаграма (usecase.puml):**
- Актори: Глядач, Автор, Модератор (без Application)
- Варіанти: 9 основних (підписка, перегляд, коментування, планування, запуск чату, модерація, видалення, блокування, апеляція)

**Генерована діаграма (usecase_csharp.puml):**
- Актори: Application, Глядач, Автор, Модератор
- Варіанти: 12 (додані реєстрація, публікація, отримання статистики)
- **Відмінності:**
  - ✅ Додано актора Application (визначено як доменний актор, який керує системою)
  - ✅ Додано варіанти: "Реєстрація користувача", "Публікація контенту", "Отримання статистики"
  - ✅ Явно показано включення (include) від реєстрації до підписки
  - ✅ Більш детальні залежності між варіантами

### 5.2 Діаграма класів

**Вихідна діаграма (class.puml):**
- Класи: Глядач, Автор, Модератор, Контент (4 класи)
- Методи: мінімальні (підписка, перегляд, планування, запуск чату, модерація, видалення, блокування)
- Не показані: User базовий клас, інтерфейси, PremierSystem, Application

**Генерована діаграма (class_csharp.mmd):**
- Класи: Application, PremierSystem, User (abstract), Viewer, Author, Moderator, Content (7 класів)
- Інтерфейси: IEngageable, IModeratable
- Методи: всі методи (getter/setter, бізнес-логіка, утиліти)
- **Відмінності:**
  - ✅ Додано абстрактний клас User як батько для Viewer, Author, Moderator
  - ✅ Додано PremierSystem клас (система управління)
  - ✅ Додано Application клас (точка входу)
  - ✅ Явно показані інтерфейси IEngageable та IModeratable
  - ✅ Показані всі асоціації (User[] array), агрегації (List<User>, List<Content>)
  - ✅ Полна структура методів з параметрами та повертаними типами

### 5.3 Діаграма послідовності

**Вихідна діаграма (sequence.puml):**
- Учасники: Глядач, Автор, Модератор, Система (4)
- Взаємодія: Запит на підписку → Підтвердження → Перегляд → Блокування
- Просто показані 4 основні операції

**Генерована діаграма (sequence_csharp.mmd):**
- Учасники: Application, PremierSystem, Viewer, Author, Moderator, Content (6)
- Взаємодія: 7 сцен з 20+ операцій
- Кольорові блоки для кожної сцени
- **Відмінності:**
  - ✅ Додано Application як координатор
  - ✅ Додано Content як окремого учасника
  - ✅ Розширено послідовність на 7 сцен (замість 1 простої послідовності)
  - ✅ Явно показано création об'єктів
  - ✅ Показана повна логіка взаємодії (реєстрація, публікація, підписка, перегляд, коментування, модерація, апеляція, статистика)
  - ✅ Додано кольорові блоки для наочності

### 5.4 Діаграма станів

**Вихідна діаграма (state.puml):**
- Стани: Очікування → Підписка → Перегляд → Завершення → Очікування (5 станів, циклічна)
- Переходи: Запит на підписку, Відео доступне, Перегляд завершено, Повторне перегляд

**Генерована діаграма (state_csharp.mmd):**
- Стани: Ініціалізація → РеєстраціяКористувачів → ... → СтатистикаСистеми (15 станів, лінійна)
- Переходи: Кожен метод програми є переходом
- **Відмінності:**
  - ✅ Розширено на повний цикл програми (від ініціалізації до статистики)
  - ⚠️ Вихідна діаграма была циклічною (для користувача), генерована - лінійна (для програми)
  - ✅ Додано всі стани відповідно до сцен у Program.Main()
  - ✅ Явно показано послідовність операцій

---

## 6. Загальні висновки

### 6.1 Покриття вихідних діаграм

Генеровані UML діаграми **повністю та розширено** покривають вихідні діаграми:

| Діаграма | Покриття | Примітки |
|---|---|---|
| **Варіанти використання** | ✅ 100% + розширення | Добавлено Application актор та 3 нові варіанти |
| **Класи** | ✅ 100% + розширення | Додано User, PremierSystem, Application, інтерфейси |
| **Послідовність** | ✅ 100% + розширення | Розширено з 4 на 6 учасників, з 1 на 7 сцен |
| **Стани** | ✅ Частково інші | Вихідна - циклічна (користувача), генерована - лінійна (програми) |

### 6.2 Основні висновки

1. **Генеровані діаграми точно відображають C# код:**
   - Всі класи, інтерфейси, методи мають прямої відповідності в коді
   - Немає вигаданих елементів
   - Архітектура в діаграмах повністю відповідає реальній реалізації

2. **Діаграми показують повну систему, не лише окремих користувачів:**
   - Application керує всім процесом
   - PremierSystem агрегує користувачів та контент
   - Ясна ієрархія та взаємодія компонентів

3. **Відмінності від вихідних діаграм обґрунтовані:**
   - Вихідні діаграми були абстрактними та спрощеними
   - Генеровані діаграми деталізують архітектуру
   - Розширення (Application, PremierSystem) необхідні для повного розуміння системи

4. **Генеровані діаграми придатні для:**
   - Документування реальної архітектури
   - Навчання розумінню C# коду
   - Проектування системи при змінах
   - Комунікації з командою розробників

### 6.3 Рекомендації

- **Использувати generated діаграми** як основні для документування (замість вихідних)
- **Синхронізувати** з вихідними діаграмами при змінах архітектури
- **Розглянути** додання діаграми розгортання (Deployment Diagram) для повноти

---

**Дата аналізу**: 8 грудня 2025 року  
**Мова програмування**: C# (.NET 8.0)  
**Формат діаграм**: PlantUML (usecase), Mermaid (class, sequence, state)
