import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Model.Account;

public class MainActivity {
    static final Scanner scanner = new Scanner(System.in);

    static List<Account> accountList =  new ArrayList<Account>();
    static Account currentAccount = new Account(0, "", 0);

    public static void main(String[] args) {
        prepareAccount();
        loginPage();
        scanner.close();
    }

    public static void prepareAccount() {
        accountList.add(new Account(123, "Faza", 123456));
        accountList.add(new Account(321, "Aldi", 7891011));
        accountList.add(new Account(456, "Jesse", 121314));
        accountList.add(new Account(654, "Renaldo", 161718));
    }

    public static void loginPage() {
        boolean isLoggedIn = false;
        int attemptPassword = 0;

        while(!isLoggedIn && attemptPassword < 3) {
            int inputPin = showInput();

            for(Account account : accountList) {
                if(account.getPin() == inputPin) {
                    currentAccount = account;
                    currentAccount.setPasswordAttempt(0);
                    currentAccount.setAuthenticated(true);
                    isLoggedIn = true;
                }
            }

            if(isLoggedIn) {
                startBank();
                break;
            } else {
                System.out.println("Wrong pin, please try again");
                attemptPassword++;
            }
        }
    }

    public static int showInput() {
        try {
            System.out.print("Please input pin: ");
            
            int inputPin = Integer.parseInt(scanner.next());

            return inputPin;
        } catch (Exception e) {
            System.out.println("Invalid input format. Input must be a number");
            return showInput();
        }
    }
    
    public static void startBank() {
        boolean isJustStart = true;
        boolean isFinished = false;
        String toContinue = "";
        int option = 0;

        while(!isFinished) {
            if(isJustStart || toContinue.equals("y")) {
                if(isJustStart) {
                    System.out.println("==================================================");
                    System.out.println("============= Welcome " + currentAccount.getName() +  " to MEF bank. ===============");
                    System.out.println("==================================================");
                }

                isJustStart = false;
                toContinue = "";

                System.out.println();
                System.out.println("Please choose your options:");

                System.out.println();
                System.out.println("1. Show Balance");
                System.out.println("2. Deposit");
                System.out.println("3. Withdraw");
                System.out.println("4. Transfer");
                System.out.println("5. Change Account");
                System.out.println("6. Exit");
                System.out.println();
                System.out.print("Enter the number: ");
                option = scanner.nextInt();
                System.out.println();
    
                if(option == 1) {
                    showBalance();
                } else if (option == 2) {
                    depositBalance();
                } else if (option == 3) {
                    withdrawBalance();
                } else if (option == 4) {
                    transferBalance();
                } else {
                    currentAccount.setAuthenticated(false);

                    if(option == 5) {
                        loginPage();
                        break;
                    } else {
                        isFinished = true;
                    }
                }

                System.out.println();
            } else {
                if(toContinue.equals("n")) {
                    isFinished = true;
                } else {
                    System.out.print("Do you want to continue? (y/n): ");
                    toContinue = scanner.next();
                }
            }
        }

        if(isFinished) {
            System.out.println("");
            System.out.println("==================================================");
            System.out.println("============ Thank you " + currentAccount.getName() + ", see you soon. ============");
            System.out.println("============ Always support MEF bank! ============");
            System.out.println("==================================================");
            System.out.println();
        }
    }

    public static void showBalance() {
        float balance = currentAccount.getBalance();

        System.out.println("Your current balance is: " + balance);
    }
    
    public static void depositBalance() {
        float balance = currentAccount.getBalance();
        
        System.out.println("Your balance is: " + balance);
        System.out.print("Enter the amount: ");

        currentAccount.setBalance(balance += scanner.nextFloat());

        System.out.println("Your current balance is: " + balance);
    }
    
    public static void withdrawBalance() {
        float balance = currentAccount.getBalance();
        float amount = 0;
        
        if(balance > 0) { 
            System.out.println("Your balance is: " + balance);
            System.out.print("Enter the amount: ");
            amount = scanner.nextFloat();

            if(amount > balance) {
                System.out.print("Insufficient amount. ");
            } else {
                currentAccount.setBalance(balance -= amount);
            }
        } else {
            System.out.print("You cannot withdraw. ");
        }

        System.out.println("Your current balance is: " + balance);
    }
    
    public static void transferBalance() {
        float balance = currentAccount.getBalance();
        float amount = 0;
        int accountNumber = 0;
        boolean isAccountFound = false;
        Account transferredAccount = new Account(0, null, 0);
        
        if(balance > 0) { 
            System.out.print("Enter account number: ");
            accountNumber = scanner.nextInt();

            for(Account account : accountList) {
                if(account.getAccountNumber() == accountNumber && account.getPin() != currentAccount.getPin()) {
                    transferredAccount = account;
                    isAccountFound = true;
                }
            }

            if(isAccountFound) {
                System.out.println("Your balance is: " + balance);
                System.out.print("Enter the amount: ");
                amount = scanner.nextFloat();
    
                if(amount > balance) {
                    System.out.print("Insufficient amount. ");
                } else {
                    transferredAccount.setBalance(transferredAccount.getBalance() + amount);
                    currentAccount.setBalance(balance -= amount);
                }
            }
        } else {
            System.out.print("You cannot transfer. ");
        }

        if(balance > 0 && !isAccountFound) {
            System.out.println("Account not found.");
        } else {
            System.out.println("Your current balance is: " + balance);
        }
    }
}