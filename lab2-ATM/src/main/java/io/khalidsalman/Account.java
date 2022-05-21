package io.khalidsalman;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;


abstract public class Account implements Transaction{

    Locale locale = new Locale("en", "US");
    private NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);

    private String AccountHash = UUID.randomUUID().toString();

    private String accountID = getAccountHash().substring(getAccountHash().length()-8);

    private ArrayList<String> transactions = new ArrayList<String>();

    public ArrayList<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<String> transactions) {
        this.transactions = transactions;
    }

    private Status accountStatus = Status.OPEN;

    private double balance=0;

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String credit(double amount){
        String msg="";
        if(this.getAccountStatus() == Status.OPEN){
            this.setBalance(this.getBalance()+amount);
            this.logTransaction("+", amount);
            msg ="credited your acount: "+amount;
        }
        return msg;
    }

    public String debt(double amount){
        String msg="";
        if(this.getAccountStatus() == Status.OPEN && !isOverdraft(amount)){
            this.setBalance(this.getBalance()-amount);
            this.logTransaction("-", amount);
            msg="your account has been debited "+amount;
        }
        return msg;
    }

    public String transfer(Account acct, double amount){
        String msg ="";
        if(this.getAccountStatus() == Status.OPEN && !isOverdraft(amount)){
            this.debt(amount);
            acct.credit(amount);
            msg="transfered funds FROM AccountID: "+acct.getAccountHash()+"\n"+"transfered funds TO AccountID: "+acct.getAccountHash();
        }
        return msg;
    }

    public Status getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(Status accountStatus) {
        this.accountStatus = accountStatus;
    }



    public void changeStatus(String status){
        setAccountStatus(Status.valueOf(status.toUpperCase()));
    }

    public String getAccountHash() {
        return AccountHash;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setAccountHash(String accountHash) {
        AccountHash = accountHash;
    }

    public boolean isOverdraft(double amount){
        return amount > this.getBalance();
    }

    public void logTransaction(String action, double amount){

        StringBuilder transaction = new StringBuilder();
        transaction.append(new Date()+" ");
       transaction.append(action+" "+currencyFormat.format(amount)+"\n");
        getTransactions().add(transaction.toString());
    }
    public void printTransactions(){
        StringBuilder log = new StringBuilder();
        log.append("TRANSACTION LOG FOR ");
        log.append(getClass().getSimpleName()).append("\n");

        for(String transaction : getTransactions()){
            log.append(transaction);
        }
        System.out.println(log.toString());
    }
    public Account(){}

    public Account(double amount){
        this.setBalance(amount);
    }

}
