package io.khalidsalman;

import java.util.ArrayList;


public class ATM {

    private ArrayList<User> users = new ArrayList<User>();
    private User loggedIn;
    private boolean session = false;




    public boolean isSession() {
        return session;
    }

    public void setSession(boolean session) {
        this.session = session;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public User getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(User loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user){
        this.users.add(user);
    }

    public boolean login(String userID, int pin){

        String msg="your user Identification number or pin is invalid";
        if(session){
            msg = this.getLoggedIn()+" is already Logged into a session";
            System.out.println(msg);
            return session;
        }

        for(User user: users){

            if(user.getUserID().equalsIgnoreCase(userID)){
                if(pin == user.getPin()){
                    this.setLoggedIn(user);
                    msg = user.getFirst()+" "+user.getLast()+" is now logged in this current session";
                    session = !session;
                }else{
                    msg = "your password seems to be invalid. try again.";
                }
            }else{
                msg = "there does not seem to be a userID under that name";

            }
        }
        System.out.println(msg);
        return session;
    }

    public String logout(){
        if(!session){
            return "already Logged out";
        }
        session = !session;
        return "exited session, user is now logged out.";
    }



}
