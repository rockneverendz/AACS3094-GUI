public class Loan {
    private double annualInterestRate;
    private int numberOfYears;
    private double loanAmount;
    private java.util.Date loanDate;

    public Loan() {
    }

    public Loan(double annualInterestRate, int numberOfYears, double loanAmount) throws IllegalArgumentException {
        if (annualInterestRate <= 0 || numberOfYears <= 0 || loanAmount <= 0)
            throw new IllegalArgumentException();
        else
        {
            this.annualInterestRate = annualInterestRate;
            this.numberOfYears = numberOfYears;
            this.loanAmount = loanAmount;
        }
            loanDate = new java.util.Date();
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) throws IllegalArgumentException{
        if (annualInterestRate <= 0) throw new IllegalArgumentException();
        else this.annualInterestRate = annualInterestRate;
    }

    public int getNumberOfYears() {
        return numberOfYears;
    }

    public void setNumberOfYears(int numberOfYears)throws IllegalArgumentException {
        if (numberOfYears <= 0) throw new IllegalArgumentException();
        else this.numberOfYears = numberOfYears;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public void setLoanAmount(double loanAmount)throws IllegalArgumentException {
        if (loanAmount <= 0) throw new IllegalArgumentException();
        else this.loanAmount = loanAmount;
    }

    /**
     * Find monthly payment
     */
    public double getMonthlyPayment() {
        double monthlyInterestRate = annualInterestRate / 1200;
        return loanAmount * monthlyInterestRate / (1 - (Math.pow(1 / (1 + monthlyInterestRate), numberOfYears * 12)));
    }

    /**
     * Find total payment
     */
    public double getTotalPayment() {
        return getMonthlyPayment() * numberOfYears * 12;
    }

    /**
     * Return loan date
     */
    public java.util.Date getLoanDate() {
        return loanDate;
    }
}
