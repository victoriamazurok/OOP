package com.videoplatform;

import java.util.List;
import java.util.ArrayList;

/**
 * Модель Payroll - представляє зарплатні дані.
 * Має композиційний зв'язок з класом Paycheck.
 */
public class Payroll {
    
    private List<Paycheck> paychecks;

    public Payroll() {
        this.paychecks = new ArrayList<>();
    }

    public Payroll(List<Paycheck> paychecks) {
        this.paychecks = paychecks;
    }

    /**
     * Повертає всі квитанції про зарплату
     * @return список квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        return this.paychecks;
    }

    /**
     * Додає квитанцію про зарплату
     * @param paycheck квитанція для додавання
     */
    public void addPaycheck(Paycheck paycheck) {
        this.paychecks.add(paycheck);
    }

    /**
     * Видаляє квитанцію про зарплату
     * @param paycheck квитанція для видалення
     */
    public void removePaycheck(Paycheck paycheck) {
        this.paychecks.remove(paycheck);
    }

    /**
     * Повертає кількість квитанцій
     * @return кількість квитанцій
     */
    public int getPaycheckCount() {
        return this.paychecks.size();
    }
}
