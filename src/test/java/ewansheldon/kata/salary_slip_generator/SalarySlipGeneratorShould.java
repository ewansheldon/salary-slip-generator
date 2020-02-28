package ewansheldon.kata.salary_slip_generator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalarySlipGeneratorShould {
    public static final String ID = "12345";
    public static final String NAME = "John J Doe";
    private SalarySlipGenerator salarySlipGenerator;

    @BeforeEach
    void setUp() {
        salarySlipGenerator = new SalarySlipGenerator();
    }

    @Test
    void add_id_name_and_calculated_monthly_pay_to_salary_slip() {
        SalarySlip salarySlip = getSalarySlip(5_000);

        assertEquals(ID, salarySlip.getID());
        assertEquals(NAME, salarySlip.getName());
        assertEquals("£416.67", salarySlip.getGross());
    }

    @Test
    void add_national_insurance_contribution_to_salary_slip() {
        SalarySlip salarySlip = getSalarySlip(9_060);

        assertEquals(ID, salarySlip.getID());
        assertEquals(NAME, salarySlip.getName());
        assertEquals("£755.00", salarySlip.getGross());
        assertEquals("£10.00", salarySlip.getNationalInsuranceContributions());
    }

    @Test
    void add_tax_info_to_salary_slip() {
        SalarySlip salarySlip = getSalarySlip(12_000);

        assertEquals(ID, salarySlip.getID());
        assertEquals(NAME, salarySlip.getName());
        assertEquals("£1,000.00", salarySlip.getGross());
        assertEquals("£39.40", salarySlip.getNationalInsuranceContributions());
        assertEquals("£916.67", salarySlip.getTaxFreeAllowance());
        assertEquals("£83.33", salarySlip.getTaxableIncome());
        assertEquals("£16.67", salarySlip.getTaxPayable());
    }

    @Test
    void add_tax_above_higher_rate() {
        SalarySlip salarySlip = getSalarySlip(45_000);

        assertEquals(ID, salarySlip.getID());
        assertEquals(NAME, salarySlip.getName());
        assertEquals("£3,750.00", salarySlip.getGross());
        assertEquals("£352.73", salarySlip.getNationalInsuranceContributions());
        assertEquals("£916.67", salarySlip.getTaxFreeAllowance());
        assertEquals("£2,833.33", salarySlip.getTaxableIncome());
        assertEquals("£600.00", salarySlip.getTaxPayable());
    }

//    @Test
//    void reduce_taxable_allowance_for_high_earners() {
//        SalarySlip salarySlip = getSalarySlip(101_000);
//
//        assertEquals(ID, salarySlip.getID());
//        assertEquals(NAME, salarySlip.getName());
//        assertEquals("£8,416.67", salarySlip.getGross());
//        assertEquals("£446.07", salarySlip.getNationalInsuranceContributions());
//        assertEquals("£875.00", salarySlip.getTaxFreeAllowance());
//        assertEquals("£7,541.67", salarySlip.getTaxableIncome());
//        assertEquals("£2,483.33", salarySlip.getTaxPayable());
//    }

    private SalarySlip getSalarySlip(int annualSalary) {
        Employee employee = createEmployee(annualSalary);
        return salarySlipGenerator.generateFor(employee);
    }

    private Employee createEmployee(int i) {
        return new Employee(ID, NAME, i);
    }
}
