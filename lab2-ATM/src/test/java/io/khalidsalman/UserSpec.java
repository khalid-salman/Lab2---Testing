package io.khalidsalman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

public class UserSpec {

    User newUser;
    Checking checkAcct;
    Savings saveAcct;
    Investment invAcct;
    double ammount;
    @Before
    public void Sandbox(){
        newUser = new User("Alex", "London", 1234);
        checkAcct = new Checking();
        saveAcct = new Savings();
        ammount = 50000;
        invAcct = new Investment(ammount);
        checkAcct.setBalance(ammount);
        saveAcct.setBalance(ammount);

    }

    @Test
    public void AddSavingsAcctTest(){
        String accountID = saveAcct.getAccountID();
        newUser.addAccount(saveAcct);
        HashMap<String ,Account> accounts = newUser.getAccounts();
        String returnedAccountID="";
        for(Map.Entry accountEntry : accounts.entrySet()){
            if(accountID == accounts.get(accountEntry.getKey()).getAccountID()){
                returnedAccountID =  accounts.get(accountEntry.getKey()).getAccountID();
            }
        }
        assertEquals("should have returned the same ID", accountID, returnedAccountID);
    }


    @Test
    public void AddCheckingAcctTest(){
        String accountID = checkAcct.getAccountID();
        newUser.addAccount((Account)checkAcct);
        HashMap<String, Account> accounts = newUser.getAccounts();
        String returnAccountID="";
        for(Map.Entry accountEntry : accounts.entrySet()){
            if(accountID == accounts.get(accountEntry.getKey()).getAccountID()) returnAccountID = accounts.get(accountEntry.getKey()).getAccountID();
        }
        assertEquals("should have returned the account id for checking", accountID, returnAccountID);
    }

    @Test
    public void AddInvestmentAcctTest(){
        String accountID = invAcct.getAccountID();
        System.out.println();
        newUser.addAccount((Account)invAcct);
        HashMap<String, Account> accounts = newUser.getAccounts();
        String returnAccountID="";
        for(Map.Entry accountEntry : accounts.entrySet()){
            if(accountID == accounts.get(accountEntry.getKey()).getAccountID()) returnAccountID = accounts.get(accountEntry.getKey()).getAccountID();
        }
        assertEquals("should have returned the account id for checking", accountID, returnAccountID);
    }
}
