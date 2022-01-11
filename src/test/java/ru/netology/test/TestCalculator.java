package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.Calculator;

import java.math.BigDecimal;
import java.time.Month;

public class TestCalculator {

    @Test
    public void testMonthPayment() {
        Calculator calculator = new Calculator();
        BigDecimal sum = new BigDecimal("1000000");
        int period = 60;
        BigDecimal rate = new BigDecimal("8.5");
        int days = 30;
        BigDecimal sumExpectResult = new BigDecimal("23652.97");

        BigDecimal sumCalcResult = calculator.calcMonthPayment(sum, sum, period, rate, days);
        Assertions.assertTrue(sumExpectResult.compareTo(sumCalcResult) == 0);
    }

    @Test
    public void testFullSum() {
        Calculator calculator = new Calculator();
        BigDecimal sum = new BigDecimal("1000000");
        int period = 60;
        BigDecimal rate = new BigDecimal("8.5");
        BigDecimal sumExpectResult = new BigDecimal("1215857.27");

        BigDecimal sumCalcResult = calculator.calcFullSum(sum, period, rate, Month.JANUARY);
        Assertions.assertTrue(sumExpectResult.compareTo(sumCalcResult) == 0);
    }

    @Test
    public void testSumOverpayment() {
        Calculator calculator = new Calculator();
        BigDecimal sum = new BigDecimal("1000000");
        int period = 60;
        BigDecimal rate = new BigDecimal("8.5");
        BigDecimal sumExpectResult = new BigDecimal("215857.27");

        BigDecimal sumCalcResult = calculator.calcOverpayment(sum, calculator.calcFullSum(sum, period, rate, Month.JANUARY));
        System.out.println("sumCalcResult: " + sumCalcResult);
        Assertions.assertTrue(sumExpectResult.compareTo(sumCalcResult) == 0);
    }
}
