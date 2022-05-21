package io.khalidsalman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ATMSpec {
    ATM atm;
    User newUser, secondUser;
    Checking checking, checking2;
    double amount;
    String expectedID;
    String expectedID2;

    @Before
    public void Sandbox(){
        amount = 50000;
        atm = new ATM();
        newUser = new User("Alex", "London", 1234);
        checking = new Checking();
        checking.setBalance(amount);
        newUser.addAccount(checking);
        expectedID = newUser.getUserID();
        atm.addUser(newUser);

        secondUser = new User("steve", "believe", 2222);
        checking2 = new Checking();
        checking2.setBalance(amount+500);
        secondUser.addAccount(checking2);
        expectedID2 = secondUser.getUserID();
        atm.addUser(secondUser);
    }

    @Test
    public void addUserTest(){

        String storedID="";
        for(User user: atm.getUsers()){
            if(expectedID == user.getUserID()) storedID = user.getUserID();
        }
        assertEquals("the id stored should be: "+expectedID,expectedID,storedID);
    }

    @Test
    public void AuthTest(){
        String loggedInOne="";
        String logggedInTwo="";
        String userID= newUser.getUserID();
        int userPin = newUser.getPin();
        atm.login(userID, userPin);
        loggedInOne = atm.getLoggedIn().getLast();
        System.out.println(loggedInOne);
        atm.logout();

        String userID2 = secondUser.getUserID();
        int userPin2 = secondUser.getPin();
        atm.login(userID2, userPin2);
        logggedInTwo = atm.getLoggedIn().getLast();
        System.out.println(logggedInTwo);
        atm.logout();
        assertTrue("the two surnames should be different", loggedInOne != logggedInTwo);
    }
}
