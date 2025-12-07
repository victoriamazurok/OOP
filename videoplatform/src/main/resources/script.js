document.addEventListener('DOMContentLoaded', () => {
    fetch('/paychecks')
        .then(response => response.json())
        .then(paychecks => {
            const tableBody = document.querySelector('#paychecks-table tbody');
            tableBody.innerHTML = ''; // Очищаємо старі дані, якщо вони є

            if (paychecks.length === 0) {
                const emptyRow = document.createElement('tr');
                emptyRow.innerHTML = `<td colspan="3" class="empty-state">Немає даних</td>`;
                tableBody.appendChild(emptyRow);
            } else {
                paychecks.forEach((paycheck, index) => {
                    const row = document.createElement('tr');
                    row.innerHTML = `
                        <td>${index + 1}</td>
                        <td>${paycheck.amount}</td>
                        <td>${paycheck.date || 'Невідомо'}</td> <!-- додаємо дату або "Невідомо" -->
                    `;
                    tableBody.appendChild(row);
                });
            }
        })
        .catch(error => {
            console.error('Error fetching paychecks:', error);
            const tableBody = document.querySelector('#paychecks-table tbody');
            tableBody.innerHTML = `<tr><td colspan="3" class="empty-state">Помилка при завантаженні даних</td></tr>`;
        });
});
