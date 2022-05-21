package io.khalidsalman;

import java.util.*;


public class User {

    private HashMap<String, Account> accounts = new HashMap();
    private String first;
    private String last;
    private int pin;
    private String userHash = "U"+UUID.randomUUID().toString();
    private String userID = userHash.substring(userHash.length()-7);

    public User(String first, String last, int pin){
        this.first = first;
        this.last = last;
        this.pin = pin;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void addAccount(Account acct){
        this.accounts.put(acct.getAccountID() ,acct);
    }

    /*
    getters and setters below
     */


    public HashMap<String, Account> getAccounts() {
        return accounts;
    }

//    public void setAccounts(ArrayList<Account> accounts) {
//        this.accounts = accounts;
//    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public String getUserHash() {
        return userHash;
    }

    public void setUserHash(String userHash) {
        this.userHash = userHash;
    }

    public String listAccts(){
        StringBuilder msg= new StringBuilder();
        for(Map.Entry account : getAccounts().entrySet()){
            String type = account.getClass().getSimpleName();
            msg.append("ID: "+ getAccounts().get(account.getKey()).getAccountID());
            msg.append("\ntype: "+getAccounts().get(account.getKey()).getClass().getSimpleName());
            msg.append("\n Amount: ".toUpperCase()).append(getAccounts().get(account.getKey()).getBalance()).append("0\n\n");
        }
        System.out.println(msg.toString());
        return "";
    }

}
