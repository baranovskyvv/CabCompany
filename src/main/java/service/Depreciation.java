package service;

import java.time.LocalDate;
import java.time.Period;

//амортизация(линейная) по пробегу
public class Depreciation {

    //рассчитывает стоимость амортизации в месяц
    private double calculateMonthlyCharge(double price, int numberOfMonths) {
        return price / numberOfMonths;
    }

    //рассчитывает период от даты эксплуатации до момента прорверки
    private int calculatePeriod(LocalDate dateOfProduction) {
        LocalDate now = LocalDate.now();
        Period period = Period.between(now, dateOfProduction);
        return Math.abs(period.getMonths() + period.getYears() * 12);
    }

    //сумма амортизации за отработанный период
    private double sumDepreciation(int period, double monthlyCharge) {
        return period * monthlyCharge;
    }

    //остаточная стоимость
    private double calculateResidualValue(double price, double sumDepreciation) {
        double rv = price - sumDepreciation;
        return rv <= 0 ? 0:rv;
    }


    public double calculateDepreciation(double price, int numberOfMonths, LocalDate dateOfProduction) {
        double monthlyCharge = calculateMonthlyCharge(price, numberOfMonths);
        int period = calculatePeriod(dateOfProduction);
        return calculateResidualValue(price, sumDepreciation(period, monthlyCharge));


    }

}
