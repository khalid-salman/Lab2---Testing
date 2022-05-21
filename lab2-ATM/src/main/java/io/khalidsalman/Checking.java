package io.khalidsalman;


public class Checking extends Account{

    private Savings linkedSavings;
    private boolean overDraftProtection = true;

    public Savings getLinkedSavings() {
        return linkedSavings;
    }

    public void setLinkedSavings(Savings linkedSavings) {
        this.linkedSavings = linkedSavings;
    }



    public void coverOverdraft(double amount){
        if(super.isOverdraft(amount) && overDraftProtection){
            double debt = amount + 50 - this.getBalance();
            double newSavings = linkedSavings.getBalance() - debt;
            linkedSavings.setBalance(newSavings);
            this.setBalance(50);
        }
    }

    public Checking(){
        super();

    }
    public Checking(Savings account, double amount){
        super(amount);
        setLinkedSavings(account);
    }
}
