package ru.netology;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Month;

public class Calculator {

    // ! без учета высокосного года (всегда делим на 365) - первая версия для упрощения...
    static final BigDecimal CONST_FOR_PROC = new BigDecimal(36500);

    public BigDecimal calcMonthPayment(BigDecimal sum, BigDecimal rest, int period, BigDecimal rate, int days){

        BigDecimal sumDebt = sum.divide(new BigDecimal(period),2, RoundingMode.HALF_UP);
        if (rest.compareTo(sumDebt) < 0) sumDebt = rest;
        BigDecimal sumProcent = rest.multiply(rate).multiply(new BigDecimal(days)).divide(CONST_FOR_PROC,2, RoundingMode.HALF_UP);
        //System.out.println("sumDebt: " + sumDebt);
        //System.out.println("sumProcent: " + sumProcent);

        return sumDebt.add(sumProcent);
    }

    public BigDecimal calcFullSum(BigDecimal sum, int period, BigDecimal rate, Month month){

        int num = month.getValue();
        BigDecimal result = BigDecimal.ZERO;
        BigDecimal rest = sum;
        BigDecimal sumDebt = sum.divide(new BigDecimal(period),2, RoundingMode.HALF_UP);
        for (int i=1; i<=period; i++){
            result = result.add(calcMonthPayment(sum, rest, period, rate, getCountDays(num)));
            rest = rest.subtract(sumDebt);
            num = num < 12 ? num+1 : 1;
            //System.out.println("result: " + result);
            //System.out.println("=== rest: " + rest);
        }

        return result;
    }

    public BigDecimal calcOverpayment(BigDecimal sum, BigDecimal fullSum){

        return fullSum.subtract(sum);
    }

    private int getCountDays(int value) {
        return value == 2 ? 28 : (value == 4 || value == 6 || value == 9 || value == 11) ? 30 : 31;
    }
}

