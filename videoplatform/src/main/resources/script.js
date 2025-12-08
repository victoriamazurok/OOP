/**
 * Динамічна URL API для роботи як локально, так і з GitHub Codespace
 * Якщо запускається локально (localhost), використовується http://localhost:8080
 * Якщо запускається в Codespace, замініть на URL вашого Codespace
 */
const API_URL = window.location.hostname.includes('localhost') 
    ? 'http://localhost:8080'
    : window.location.origin;

/**
 * Завантажує дані про видео з сервера та відображає їх у таблиці
 */
document.addEventListener('DOMContentLoaded', () => {
    loadVideos();
});

/**
 * Завантажує всі видео з API
 */
function loadVideos() {
    const tableBody = document.querySelector('#tableBody');
    const totalCountElement = document.querySelector('#totalCount');
    const messageElement = document.querySelector('#message');

    // Очищуємо повідомлення та таблицю
    messageElement.textContent = '';
    tableBody.innerHTML = '';

    fetch(`${API_URL}/videos`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(videos => {
            console.log('Отримано видео:', videos.length);

            if (!videos || videos.length === 0) {
                const emptyRow = document.createElement('tr');
                emptyRow.innerHTML = `<td colspan="2" class="empty-state">Немає даних</td>`;
                tableBody.appendChild(emptyRow);
                totalCountElement.textContent = '0';
                return;
            }

            // Додаємо кожне видео у таблицю
            videos.forEach((video, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td class="text-center">${index + 1}</td>
                    <td>${escapeHtml(video.title || 'Невідомо')}</td>
                `;
                tableBody.appendChild(row);
            });

            // Оновлюємо кількість записів
            totalCountElement.textContent = videos.length;

            // Показуємо повідомлення про успіх
            messageElement.textContent = `Успішно завантажено ${videos.length} видео`;
            messageElement.className = 'message success';
        })
        .catch(error => {
            console.error('Помилка при завантаженні видео:', error);

            const emptyRow = document.createElement('tr');
            emptyRow.innerHTML = `<td colspan="2" class="empty-state">Помилка при завантаженні даних</td>`;
            tableBody.appendChild(emptyRow);

            // Показуємо повідомлення про помилку
            messageElement.textContent = 'Помилка при завантаженні даних: ' + error.message;
            messageElement.className = 'message error';
            totalCountElement.textContent = '0';
        });
}

/**
 * Екранує HTML символи для безпеки
 * @param {string} text текст для екранування
 * @returns {string} екранований текст
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}
