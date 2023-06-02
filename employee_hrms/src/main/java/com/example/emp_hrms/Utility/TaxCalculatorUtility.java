package com.example.emp_hrms.Utility;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class TaxCalculatorUtility {
    public static long calculateDaysBetween(LocalDate date1, LocalDate date2) {
        return ChronoUnit.DAYS.between(date1, date2);
    }

    public static double calculateEmployeeCess(LocalDate joinDate, Double salary) {
        double cess = 0;
        double income = employeeTotalSalary(joinDate, salary);
        if (income > 2500000) {
            cess = (income - 2500000) * 0.02;
        }

        return Math.round(cess);
    }

    public static double calculateEmployeeTax(LocalDate joinDate, Double salary) {
        double income = employeeTotalSalary(joinDate, salary);
        double tax = 0;
        if (income <= 250000) {
            tax = 0;
        } else if (income <= 500000) {
            tax = (income - 250000) * 0.05;
        } else if (income <= 1000000) {
            tax = 250000 * 0.05 + (income - 500000) * 0.10;
        } else {
            tax = 250000 * 0.05 + 500000 * 0.10 + (income - 1000000) * 0.20;
        }
        return Math.round(tax);
    }

    public static double employeeTotalSalary(LocalDate joinDate, Double salary) {
        int currentYear = Year.now().getValue();
        LocalDate currentFinancialDate = LocalDate.of(currentYear + 1, 3, 31);
        long days = calculateDaysBetween(joinDate, currentFinancialDate);
        double perDaySalary = salary / 365;
        return Math.round(days * perDaySalary);
    }


}
