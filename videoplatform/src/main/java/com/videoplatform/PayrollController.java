package com.videoplatform;

import com.google.inject.Inject;
import java.util.List;

/**
 * Контролер для обробки запитів про зарплату.
 * Взаємодіє з PaymentService для отримання даних з бази.
 */
public class PayrollController {

    private final PaymentService paymentService;

    @Inject
    public PayrollController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Повертає список всіх квитанцій про зарплату
     * @return список квитанцій
     */
    public List<Paycheck> getAllPaychecks() {
        return paymentService.getAllPaychecks();
    }
}
