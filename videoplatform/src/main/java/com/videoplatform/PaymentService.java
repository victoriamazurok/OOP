package com.videoplatform;

import java.util.List;
import java.util.ArrayList;

public class PaymentService {

    // Метод для отримання всіх записів про зарплату
    public List<Paycheck> getAllPaychecks() {
        List<Paycheck> paychecks = new ArrayList<>();
        paychecks.add(new Paycheck("John Doe", 5000.0));
        paychecks.add(new Paycheck("Jane Smith", 5500.0));
        paychecks.add(new Paycheck("Bob Johnson", 4500.0));
        return paychecks;
    }
}
