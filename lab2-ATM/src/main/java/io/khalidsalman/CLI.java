package io.khalidsalman;

import java.util.Arrays;
import java.util.Scanner;


public class CLI {
    public static ATM atm = new ATM();

    public void createUser(Scanner scanner){
        System.out.println("Let's add a new user and account");
        System.out.println("PRESS ENTER to continue");
        scanner.nextLine();
        System.out.println("enter userID and Pin to continue");
//        scanner.nextLine();
        System.out.printf("Last name:   ");
        String last = scanner.next().toString();
        System.out.printf("First :  ");
        String first = scanner.next().toString();
        System.out.printf("please select a 4 digit pin: ");
        int pin = Integer.parseInt(scanner.next().toString());
        System.out.printf("would you like to open a SAVINGS account? [y/n]  ");
        String savingsPref = scanner.next().toString();
        System.out.printf("would you like to open a CHECKING account? [y/n]  ");
        String checkingPref = scanner.next().toString();
        String overdraftPref="";
        if(checkingPref.equalsIgnoreCase("y")){
            System.out.printf("would you like to enable OVERDRAFT on checking? [y/n]  ");
            overdraftPref = scanner.next();
        }
        System.out.printf("would you like to open a INVESTMENT account? [y/n]  ");
        String InvestmentPref = scanner.next().toString();

        Savings savings = null;
        Checking checking = null;
        Investment investment = null;
        if(savingsPref.equalsIgnoreCase("y")){
            System.out.println("ok! and how much would you like to deposit in SAVINGS");
            double amountS = scanner.nextInt();
            savings = new Savings(0.7, amountS);
        }
        if(checkingPref.equalsIgnoreCase("y")){
            System.out.println("ok! and how much would you like to deposit in CHECKING");
            double amountC = scanner.nextInt();
            if(overdraftPref.equalsIgnoreCase("y")){
                checking = new Checking(savings, amountC);
            }
        }

        if(InvestmentPref.equalsIgnoreCase("y")){
            System.out.println("ok! and how much would you like to deposit in INVESTMENT");
            double amountI = scanner.nextInt();
            investment = new Investment(amountI);
        }
        System.out.println("OK! Lets get you set up!");
        User newUser=null;
        try {
            Thread.sleep(1000);
            System.out.printf("Loading");
            for(int i=0;i<17; i++){
                System.out.printf(".");
                Thread.sleep(500);
            }
            System.out.printf(" :-)\n");
            Thread.sleep(2000);
            System.out.println("registering User");

            Thread.sleep(2000);
            System.out.println("this may take a few minutes");
            Thread.sleep(800);
            System.out.println("we appreciate your patience");
            Thread.sleep(5000);
            System.out.println("adding accounts to user account "+first.toUpperCase()+" "+last.toUpperCase());



            Thread.sleep(2000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        newUser = new User(first, last, pin);
        atm.addUser(newUser);
        if(savings != null) newUser.addAccount((Account)savings);
        if(checking != null) newUser.addAccount((Account)checking);
        if(investment != null) newUser.addAccount((Account)investment);

        System.out.println("\n\nokay! your account is all set up! \n\n " );
        System.out.println("-----------------------------------------");
        System.out.println("|   your userID is: "+newUser.getUserID().toUpperCase()+"             |");
        System.out.println("|   And your user pin is: "+newUser.getPin()+"          |");
        System.out.println("-----------------------------------------");

    }
    public boolean transactions(Scanner scanner){
        boolean res = true;
        System.out.println("please select an action.\n");
        System.out.println(Arrays.asList(Action.values()).toString().substring(1,Arrays.asList(Action.values()).toString().length()-1));
//        scanner.nextLine();
        String action="";
        try{
            action = scanner.nextLine();

        }catch (NullPointerException e){
            System.out.println("type a command to continue");
        }
        String[] actionStr = action.toString().split(" ");
//        System.out.println(action);
        Action comm = null;
        String accountID= null;
        String toAccountID = null;
        int amount = 0;
        switch(actionStr.length) {
            case 4:
              try{
                  toAccountID = actionStr[3];
              }catch (Exception e){
                  System.out.println("that is not avaiable as a command");
              }
            case 3:
               try{
                   accountID = actionStr[2];
                   amount = Integer.parseInt(actionStr[1]);
               }catch (Exception e){
                   System.out.println("that is not an available command");
               }
            case 1:
                try {
                    comm = Action.valueOf(actionStr[0].toUpperCase());

                } catch (Exception e) {
                    System.out.println("That is not an available command.");
                    System.out.println(Arrays.asList(Action.values()).toString().substring(1, Arrays.asList(Action.values()).toString().length() - 1));
                }
                break;
        }
          User user = atm.getLoggedIn();
       try{
           switch(comm){
               case DEPOSIT:
//                   try{
                       user.getAccounts().get(accountID).credit(amount);
//                   }catch (Exception e){
//                       System.out.println("did you type in the correct account ID?"+ Arrays.asList(atm.getLoggedIn().getAccounts().keySet()).toString().substring(1, Arrays.asList(atm.getLoggedIn().getAccounts().keySet()).toString().length()-1));
//                   }
                   res = true;
                   break;
               case WITHDRAWL:
                   try{
                       user.getAccounts().get(accountID).debt(amount);
                   }catch (Exception e){
                       System.out.println("did you type in the correct account ID?"+ Arrays.asList(atm.getLoggedIn().getAccounts().keySet()).toString().substring(1, Arrays.asList(atm.getLoggedIn().getAccounts().keySet()).toString().length()-1));
                   }
                   res = true;
                   break;
               case BALANCE:
                   System.out.println(user.listAccts());
                   res = true;
                   break;
               case TRANSFER:
                   user.getAccounts().get(accountID).debt(amount);
                   user.getAccounts().get(toAccountID).credit(amount);
                   res = true;
                   break;
               case OPEN:
                   System.out.println("which type of account?");
                    String type = scanner.nextLine();
                   System.out.println("how much do you want to deposit?");
                   double initialAmount = scanner.nextDouble();
                   if(type.toLowerCase() == "checking"){
                       Checking newChecking = new Checking();
                       newChecking.setBalance(initialAmount);
                       user.addAccount(newChecking);
                   }else if(type.toLowerCase() == "investment"){
                       Investment newInvestment = new Investment(initialAmount);
                       user.addAccount(newInvestment);
                   }else{
                       Savings newSavings = new Savings();
                       newSavings.setBalance(initialAmount);
                       user.addAccount(newSavings);
                   }
                   res = true;
                   break;
               case CLOSE:
                   System.out.println("which accound would you like to close?");
                   String acctID = scanner.nextLine();
                   user.getAccounts().remove(acctID);
                   break;
               case LOG:
                   System.out.println("enter an account ID to view its transactions");
                   acctID = scanner.nextLine();
                   user.getAccounts().get(acctID).printTransactions();
                   res = true;
                   break;
               case LOGOUT:
                   res = false;
                   break;
           }
       }catch(NullPointerException e){
           System.out.println("");
       }
        return res;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CLI cli = new CLI();
System.out.println("       .----------------. .----------------. .----------------. .----------------. .----------------. .----------------. .-----------------. ");
System.out.println("       | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | ");
System.out.println("       | | _____  _____ | | |  _________   | | |   _____      | | |     ______   | | |     ____     | | | ____    ____ | | |   _________  | | ");
System.out.println("       | ||_   _||_   _|| | | |_   ___  |  | | |  |_   _|     | | |   .' ___  |  | | |   .'    `.   | | ||_   \\  /   _|| | |  |_   ___  | | | ");
System.out.println("       | |  | | /\\ | |  | | |   | |_  \\_|  | | |    | |       | | |  / .'   \\_|  | | |  /  .--.  \\  | | |  |   \\/   |  | | |    | |_  \\_| | | ");
System.out.println("       | |  | |/  \\| |  | | |   |  _|  _   | | |    | |   _   | | |  | |         | | |  | |    | |  | | |  | |\\  /| |  | | |    |  _|  _  | | ");
System.out.println("       | |  |   /\\   |  | | |  _| |___/ |  | | |   _| |__/ |  | | |  \\ `.___.'\\  | | |  \\  `--'  /  | | | _| |_\\/_| |_ | | |   _| |___/ | | | ");
System.out.println("       | |  |__/  \\__|  | | | |_________|  | | |  |________|  | | |   `._____.'  | | |   `.____.'   | | ||_____||_____|| | |  |_________| | | ");
System.out.println("       | |              | | |              | | |              | | |              | | |              | | |              | | |              | | ");
System.out.println("       | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | ");
System.out.println("       '----.----------------.-.----------------.-------------' '----------------' '----------------' '----------------' '------------------' ");
System.out.println("        | .--------------. | .--------------. | ");
System.out.println("        | |  _________   | | |     ____     | | ");
System.out.println("        | | |  _   _  |  | | |   .'    `.   | | ");
System.out.println("        | | |_/ | | \\_|  | | |  /  .--.  \\  | | ");
System.out.println("        | |     | |      | | |  | |    | |  | | ");
System.out.println("        | |    _| |_     | | |  \\  `--'  /  | | ");
System.out.println("        | |   |_____|    | | |   `.____.'   | | ");
System.out.println("        | |              | | |              | | ");
System.out.println("        | '--------------' | '--------------' | ");
System.out.println(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .----------------.  .-----------------. .----------------. " );
System.out.println(        "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |");
System.out.println(        "| |      __      | || |     ______   | || |              | || |     ______   | || |     ____     | || | _____  _____ | || | ____  _____  | || |  _________   | |");
System.out.println(        "| |     /  \\     | || |   .' ___  |  | || |              | || |   .' ___  |  | || |   .'    `.   | || ||_   _||_   _|| || ||_   \\|_   _| | || | |  _   _  |  | |");
System.out.println(        "| |    / /\\ \\    | || |  / .'   \\_|  | || |    ______    | || |  / .'   \\_|  | || |  /  .--.  \\  | || |  | |    | |  | || |  |   \\ | |   | || | |_/ | | \\_|  | |");
System.out.println(        "| |   / ____ \\   | || |  | |         | || |   |______|   | || |  | |         | || |  | |    | |  | || |  | '    ' |  | || |  | |\\ \\| |   | || |     | |      | |");
System.out.println(        "| | _/ /    \\ \\_ | || |  \\ `.___.'\\  | || |              | || |  \\ `.___.'\\  | || |  \\  `--'  /  | || |   \\ `--' /   | || | _| |_\\   |_  | || |    _| |_     | |");
System.out.println(        "| ||____|  |____|| || |   `._____.'  | || |              | || |   `._____.'  | || |   `.____.'   | || |    `.__.'    | || ||_____|\\____| | || |   |_____|    | |");
System.out.println(        "| |              | || |              | || |              | || |              | || |              | || |              | || |              | || |              | |");
System.out.println(        "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |");
System.out.println(        " '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'  '----------------'");
System.out.println("        | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | .--------------. | ");
System.out.println("        | |  ________    | | |  _______     | | |      __      | | |     ______   | | | _____  _____ | | |   _____      | | |      __      | | ");
System.out.println("        | | |_   ___ `.  | | | |_   __ \\    | | |     /  \\     | | |   .' ___  |  | | ||_   _||_   _|| | |  |_   _|     | | |     /  \\     | | ");
System.out.println("        | |   | |   `. \\ | | |   | |__) |   | | |    / /\\ \\    | | |  / .'   \\_|  | | |  | |    | |  | | |    | |       | | |    / /\\ \\    | |");
System.out.println("        | |   | |    | | | | |   |  __ /    | | |   / ____ \\   | | |  | |         | | |  | '    ' |  | | |    | |   _   | | |   / ____ \\   | | ");
System.out.println("        | |  _| |___.' / | | |  _| |  \\ \\_  | | | _/ /    \\ \\_ | | |  \\ `.___.'\\  | | |   \\ `--' /   | | |   _| |__/ |  | | | _/ /    \\ \\_ | | ");
System.out.println("        | | |________.'  | | | |____| |___| | | ||____|  |____|| | |   `._____.'  | | |    `.__.'    | | |  |________|  | | ||____|  |____|| | ");
System.out.println("        | |              | | |              | | |              | | |              | | |              | | |              | | |              | | ");
System.out.println("        | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | '--------------' | ");
System.out.println("        .----------------. .----------------. .-----------------..----------------. '----------------' '----------------' '----------------' ");
System.out.println("        | .--------------. | .--------------. | .--------------. | .--------------. | ");
System.out.println("        | |   ______     | | |      __      | | | ____  _____  | | |  ___  ____   | | ");
System.out.println("        | |  |_   _ \\    | | |     /  \\     | | ||_   \\|_   _| | | | |_  ||_  _|  | | ");
System.out.println("        | |    | |_) |   | | |    / /\\ \\    | | |  |   \\ | |   | | |   | |_/ /    | | ");
System.out.println("        | |    |  __'.   | | |   / ____ \\   | | |  | |\\ \\| |   | | |   |  __'.    | | ");
System.out.println("        | |   _| |__) |  | | | _/ /    \\ \\_ | | | _| |_\\   |_  | | |  _| |  \\ \\_  | | ");
System.out.println("        | |  |_______/   | | ||____|  |____|| | ||_____|\\____| | | | |____||____| | | ");
System.out.println("        | |              | | |              | | |              | | |              | |");
System.out.println("        | '--------------' | '--------------' | '--------------' | '--------------' | ");
System.out.println("        '----------------' '----------------' '----------------' '------------------' ");


        System.out.println("                  .ooo0OOOoo.      ");
 System.out.println("                oOOOOOOOOOOOOOo       ");
 System.out.println("             oOO`             `OO       ");
 System.out.println("         ____oOO  ====   ====  OOo____       ");
 System.out.println("        \\   OO       ! !.---.  OO   /       ");
 System.out.println("         \\  OO   <0> ! !!<0>!  OO  /       ");
 System.out.println("          \\ Oo       ! !'---'  oO /       ");
 System.out.println("            \\o       \\_/       o/       ");
 System.out.println("            .' _______________ '.       ");
 System.out.println("          ,'   :   V     V   :   '.       ");
 System.out.println("        ,'      -_         _-      '.       ");
 System.out.println("      ,'          \\oOOOOOo/         '.       ");
 System.out.println("    ,'               OOOOO             '.       ");
 System.out.println("    -----------     \\OOO/     -----------       ");
 System.out.println("                     \\O/    \n\n subsidiary of the HANDRO UMBRELLA CORPORATION\n");
       try{
           Thread.sleep(2000);
           System.out.println("         WELL SUCK YOUR BUCKS\n");
           Thread.sleep(2000);
           System.out.println("         also....wutang 4EVA.");
       }catch(InterruptedException ex) {
           Thread.currentThread().interrupt();
       }

        System.out.println("\n\nWelcome to your local ZipCode Wilmington ATM \n PRESS ENTER to continue");
        scanner.nextLine();
        boolean users = true;
        do{
            System.out.println("\n ============================== \n         NEW  ACCOUNT\n ==============================");
            cli.createUser(scanner);
            System.out.printf("would you like to create anymore users before logging in? [y/n]  ");
            String moreUsers = scanner.next().toString();
            if(moreUsers.equalsIgnoreCase("n")){
                System.out.println("\n\n\n");
                users = !users;

            }
        }while(users);

        boolean exit = false;
        do{
            boolean log = false;
            while(!log){
                System.out.println("\n ============================== \n        LOGIN\n ==============================");
                System.out.printf("userid: ");
                String loginID = scanner.next().toString().toLowerCase();
                System.out.printf("   user entered %s\n", loginID);
                System.out.printf("pin: ");
                int thePin = Integer.parseInt(scanner.next().toString());
                log = atm.login(loginID.toLowerCase(), thePin);
                scanner.nextLine();
                while(log){
                    log = cli.transactions(scanner);
                }
                System.out.printf("would you like to do something else before loging out? [y/n]");
                String answer = scanner.next();
                if(answer.equalsIgnoreCase("n")) log = !log;
            }

            System.out.printf("would you like to do something else before exiting the program? [y/n]");
            String answer = scanner.next();
            if(answer.equalsIgnoreCase("n")) exit = !exit;

        }while(!exit);

    }
}
