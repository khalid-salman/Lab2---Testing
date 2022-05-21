package io.khalidsalman;

public interface Transaction {

    String credit(double amount);
    String debt(double amount);
    String transfer(Account acct, double amount);

}
