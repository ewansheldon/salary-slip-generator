package ewansheldon.kata.salary_slip_generator;

import java.text.DecimalFormat;

public class SalarySlipGenerator {
    private static final String CURRENCY_FORMAT = "£###,##0.00";
    private static final double NI_THRESHOLD = 8_060;
    private static final int HIGHER_INCOME_RATE_THRESHOLD = 43_000;
    private static final double TAX_THRESHOLD = 11_000;
    private static final double ANNUAL_NI_CONTRIBUTION_PERCENTAGE = 0.12;
    private static final double HIGHER_ANNUAL_NI_CONTRIBUTION_PERCENTAGE = 0.02;
    private static final double TAX_RATE = 0.2;
    private static final double HIGHER_TAX_RATE = 0.4;
    private double salary;

    public SalarySlip generateFor(Employee employee) {
        salary = employee.getSalary();
        return new SalarySlip(
                employee.getId(), employee.getName(),
                currencyFormat(calculateGross()), currencyFormat(calculateNationalInsurance()),
                currencyFormat(calculateTaxFreeAllowance()), currencyFormat(calculateTaxableIncome()),
                currencyFormat(calculateTaxPayable())
        );
    }

    private double calculateGross() {
        return salary / 12;
    }

    private double calculateNationalInsurance() {
        return basicNIContribution() + higherNIContribution();
    }

    private double higherNIContribution() {
        return Math.max(salary - HIGHER_INCOME_RATE_THRESHOLD, 0) * higherNIContributionRate();
    }

    private double basicNIContribution() {
        return amountAboveNIThreshold() * monthlyNIContributionRate();
    }

    private double higherNIContributionRate() {
        return HIGHER_ANNUAL_NI_CONTRIBUTION_PERCENTAGE / 12;
    }

    private double monthlyNIContributionRate() {
        return ANNUAL_NI_CONTRIBUTION_PERCENTAGE / 12;
    }

    private double amountAboveNIThreshold() {
        return Math.max(Math.min(salary, HIGHER_INCOME_RATE_THRESHOLD) - NI_THRESHOLD, 0);
    }

    private double calculateTaxableIncome() {
        return calculateGross() - calculateTaxFreeAllowance();
    }

    private double calculateTaxPayable() {
        return taxPayableStandardRate() + taxPayableHigherRate();
     }

    private double taxPayableStandardRate() {
        return (calculateTaxableIncome() - taxableHigherRate()) * TAX_RATE;
    }

    private double taxPayableHigherRate() {
        return taxableHigherRate() * HIGHER_TAX_RATE;
    }

    private double taxableHigherRate() {
        return Math.max(salary - HIGHER_INCOME_RATE_THRESHOLD, 0) / 12;
    }

    private double calculateTaxFreeAllowance() {
        return getAnnualTaxFreeAllowance() / 12;
    }

    private double getAnnualTaxFreeAllowance() {
        return TAX_THRESHOLD - Math.max(salary - 100_000, 0) / 2;
    }

    private String currencyFormat(double amount) {
        DecimalFormat currencyFormatter = new DecimalFormat(CURRENCY_FORMAT);
        return currencyFormatter.format(amount);
    }
}
