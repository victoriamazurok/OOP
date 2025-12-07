/**
 * Динамічна URL API для роботи як локально, так і з GitHub Codespace
 * Якщо запускається локально (localhost), використовується http://localhost:8080
 * Якщо запускається в Codespace, замініть на URL вашого Codespace
 * Приклад: https://ubiquitous-fiesta-jj9jjx9wrr3qv7g-8080.app.github.dev
 */
const API_URL = window.location.hostname.includes('localhost') 
    ? 'http://localhost:8080'
    : window.location.origin;

/**
 * Завантажує дані про квитанції з сервера та відображає їх у таблиці
 */
document.addEventListener('DOMContentLoaded', () => {
    loadPaychecks();
});

/**
 * Завантажує всі квитанції про зарплату з API
 */
function loadPaychecks() {
    const tableBody = document.querySelector('#paychecks-table tbody');
    const totalCountElement = document.querySelector('#totalCount');
    const messageElement = document.querySelector('#message');

    // Очищуємо повідомлення та таблицю
    messageElement.textContent = '';
    tableBody.innerHTML = '';

    fetch(`${API_URL}/paychecks`)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(paychecks => {
            console.log('Отримано квитанцій:', paychecks.length);

            if (!paychecks || paychecks.length === 0) {
                const emptyRow = document.createElement('tr');
                emptyRow.innerHTML = `<td colspan="3" class="empty-state">Немає даних</td>`;
                tableBody.appendChild(emptyRow);
                totalCountElement.textContent = '0';
                return;
            }

            // Додаємо кожну квитанцію у таблицю
            paychecks.forEach((paycheck, index) => {
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td class="text-center">${index + 1}</td>
                    <td>${escapeHtml(paycheck.name || 'Невідомо')}</td>
                    <td class="text-right">${formatCurrency(paycheck.amount)}</td>
                `;
                tableBody.appendChild(row);
            });

            // Оновлюємо кількість записів
            totalCountElement.textContent = paychecks.length;

            // Показуємо повідомлення про успіх
            messageElement.textContent = `Успішно завантажено ${paychecks.length} квитанцій`;
            messageElement.className = 'message success';
        })
        .catch(error => {
            console.error('Помилка при завантаженні квитанцій:', error);

            const emptyRow = document.createElement('tr');
            emptyRow.innerHTML = `<td colspan="3" class="empty-state">Помилка при завантаженні даних</td>`;
            tableBody.appendChild(emptyRow);

            // Показуємо повідомлення про помилку
            messageElement.textContent = 'Помилка при завантаженні даних: ' + error.message;
            messageElement.className = 'message error';
            totalCountElement.textContent = '0';
        });
}

/**
 * Форматує число як валюту
 * @param {number} amount сума
 * @returns {string} відформатована сума з символом грн.
 */
function formatCurrency(amount) {
    if (typeof amount !== 'number') {
        return 'Невалідна сума';
    }
    return new Intl.NumberFormat('uk-UA', {
        style: 'currency',
        currency: 'UAH',
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    }).format(amount);
}

/**
 * Екранює HTML символи для безпеки
 * @param {string} text текст для екранування
 * @returns {string} екранований текст
 */
function escapeHtml(text) {
    const div = document.createElement('div');
    div.textContent = text;
    return div.innerHTML;
}
