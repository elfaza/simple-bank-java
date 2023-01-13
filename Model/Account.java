package Model;

public class Account {
    private int pin;
    private int accountNumber;
    private String name = "";
    private float balance = 0;
    private boolean isAuthenticated = false;
    private int passwordAttempt = 0;

    public Account(int defaultPin, String defaultName, int defaultAccountNumber) {
        pin = defaultPin;
        name = defaultName;
        accountNumber = defaultAccountNumber;
    }

    public int getPin() {
        return pin;
    }
    
    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int inputAccountNumber) {
        accountNumber = inputAccountNumber;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String inputName) {
        name = inputName;
    }

    public float getBalance() {
        return balance;
    }
    public void setBalance(float inputBalance) {
        balance = inputBalance;
    }

    public boolean getAuthenticated() {
        return isAuthenticated;
    }
    public void setAuthenticated(boolean inputAuthenticated) {
        isAuthenticated = inputAuthenticated;
    }

    public int getPasswordAttempt() {
        return passwordAttempt;
    }
    public void setPasswordAttempt(int inputPasswordAttempt) {
        passwordAttempt = inputPasswordAttempt;
    }
}
