package io.khalidsalman;

import java.util.Random;

public class Investment extends Account{
    Random randy = new Random();

    public void returnOnInv(double years){
        setBalance(this.getBalance()*(1+0.3)*years);

    }

    public Investment(double amount){
        super(amount);
    }
}
