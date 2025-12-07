package com.videoplatform;

import java.util.List;
import java.util.ArrayList;

/**
 * Сервіс для роботи з зарплатними даними.
 * Читає дані з бази даних та повертає їх контролеру.
 */
public class PaymentService {

    /**
     * Повертає всі квитанції про зарплату з бази даних
     * @return список всіх квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        List<Paycheck> paychecks = new ArrayList<>();
        // Дані з бази даних (в реальному застосунку це буде читатися з БД)
        paychecks.add(new Paycheck("John Doe", 5000.0));
        paychecks.add(new Paycheck("Jane Smith", 5500.0));
        paychecks.add(new Paycheck("Bob Johnson", 4500.0));
        paychecks.add(new Paycheck("Alice Williams", 6000.0));
        paychecks.add(new Paycheck("Michael Brown", 5800.0));
        return paychecks;
    }
}
