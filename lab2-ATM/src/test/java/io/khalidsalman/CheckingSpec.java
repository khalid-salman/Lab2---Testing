package io.khalidsalman;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CheckingSpec {

    Checking checking;
    Savings savings;
    double amount;
    double withdraw;
    double err = 0.01;
    @Before
    public void Sandbox(){
        amount = 500;
        savings = new Savings(0.3, 20000);
        checking = new Checking(savings, amount);
        withdraw = 600;
    }

    @Test
    public void coverOverdraftCheckingTest(){
        double balance = checking.getBalance();
        double newBalance =balance - withdraw;
        checking.setBalance(newBalance);
        checking.coverOverdraft(withdraw);
        double actualBalance = checking.getBalance();
        double expectedbalance = 50;
        assertEquals("the balance should now be 50", expectedbalance, actualBalance, err);

    }

    @Test
    public void coverOverdraftSavingsTest(){
        checking.coverOverdraft(withdraw);
        double expectedSavings = 20000 + amount - withdraw - 50;
        double actualSavings = savings.getBalance();
        assertEquals("the savings should be "+expectedSavings, expectedSavings, actualSavings, err);

    }

    @Test
    public void ChangeCheckingStatusTest(){
        checking.changeStatus("closed");
        Status actualStatus = checking.getAccountStatus();
        Status expectedStatus = Status.CLOSED;
        assertEquals("the status should be: "+expectedStatus,expectedStatus,actualStatus);
    }

    @Test
    public void creditTest(){
        double checkingBal = checking.getBalance();
        double creditamount = 40;
        double expectedbal = checkingBal+creditamount;

        checking.credit(creditamount);
        double err = 0.01;
        double actualbal = checking.getBalance();
        assertEquals("the balance should now be "+expectedbal,expectedbal,actualbal,err);
    }

//    @Test
//    public void debtTest(){
//        double checkingBal = checking.getBalance();
//        double debtamount = 40;
//        double expectedbal = checkingBal-debtamount;
//
//        checking.debt(debtamount);
//        double err = 0.01;
//        double actualbal = checking.getBalance();
//        assertEquals("the balance should now be "+expectedbal,expectedbal,actualbal,err);
//    }

    @Test
    public void transferTest(){
        double checkingBal = checking.getBalance();
        double transferAmount = 40;
        double expectedbal = checkingBal+transferAmount;

        double savingsBal = savings.getBalance();
        double expectedBal = savingsBal-transferAmount;

        savings.transfer(checking, transferAmount);

        double err = 0.01;
        double actualCheckingbal = checking.getBalance();
        double actualSavingsBal = savings.getBalance();

        boolean cond = 2*transferAmount == Math.abs(savingsBal-actualSavingsBal)+Math.abs(checkingBal-actualCheckingbal);
        assertTrue("the difference in ammount between savings and checking should be 80",cond);

    }
}
