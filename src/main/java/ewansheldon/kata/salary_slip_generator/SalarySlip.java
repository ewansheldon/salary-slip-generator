package ewansheldon.kata.salary_slip_generator;

public class SalarySlip {
    private String id;
    private String name;
    private String gross;
    private String nIContributions;
    private String taxFreeAllowance;
    private String taxableIncome;
    private String taxPayable;

    public SalarySlip(
            String id, String name, String gross, String nIContributions,
            String taxFreeAllowance, String taxableIncome, String taxPayable
    ) {
        this.id = id;
        this.name = name;
        this.gross = gross;
        this.nIContributions = nIContributions;
        this.taxFreeAllowance = taxFreeAllowance;
        this.taxableIncome = taxableIncome;
        this.taxPayable = taxPayable;
    }

    public String getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGross() {
        return gross;
    }

    public String getNationalInsuranceContributions() {
        return nIContributions;
    }

    public String getTaxFreeAllowance() {
        return taxFreeAllowance;
    }

    public String getTaxableIncome() {
        return taxableIncome;
    }

    public String getTaxPayable() {
        return taxPayable;
    }
}
