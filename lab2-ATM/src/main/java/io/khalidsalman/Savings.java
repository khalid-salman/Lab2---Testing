package io.khalidsalman;

public class Savings extends Account {
    private double interest = 0.5;

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public Savings(){
        super();
    }
    public Savings(double intRate){
        this();
        this.setInterest(intRate);

    }
    public Savings(double intRate, double amount){
        this();
        super.setBalance(amount);
        this.interest = intRate;
    }


    public Double annualInterest(int years){
        double newBal = this.getBalance()*Math.pow((1+(this.getInterest()/365)), 365*years);
        return newBal;
    }

}
