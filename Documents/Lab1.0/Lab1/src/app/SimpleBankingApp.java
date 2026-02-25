package app;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

import model.Account;
import model.Transaction;
import model.User;

public class SimpleBankingApp {
    public static Vector<User> users = new Vector<User>();
    public static Vector<Account> accounts = new Vector<Account>();
    public static Vector<Transaction> transactions = new Vector<Transaction>();
    
    public static void loadUserData() {
        User aUser = new User("mike", "my_passwd", "Mike", "Smith", "07771234567");
        users.add(aUser);
        
        aUser = new User("james.cameron@gmail.com", "angel", "James", "Cameron", "07777654321");
        users.add(aUser);
        
        aUser = new User("julia.roberts@gmail.com", "change_me", "Julia", "roberts", "07770123456");
        users.add(aUser); 
    }
    
    public static void printAllUsers() {
        System.out.println("There are: " + users.size() + " users in the system.");    
        System.out.println(String.format("%-25s| %-15s| %-15s| %-15s| %-15s", 
                "username", "password", "firstName", "lastName", "mobileNumber"));
        System.out.println("-------------------------------------------------------------------------------------------");
        for (int i = 0; i < users.size(); i++) 
            System.out.println(users.get(i).toString());    
        System.out.println();
    }
    
    public static void loadAccountData() {
        Account anAccount;
        try {
            anAccount = new Account("5495-1234", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
            accounts.add(anAccount);
            
            anAccount = new Account("5495-1239", "mike", "Standard", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2020"));
            accounts.add(anAccount);

            anAccount = new Account("5495-1291", "mike", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("21/07/2019"));
            accounts.add(anAccount);

            anAccount = new Account("5495-6789", "David.McDonald@gmail.com", "Saving", new SimpleDateFormat("dd/MM/yyyy").parse("20/08/2019"));
            accounts.add(anAccount);

        } catch (ParseException e) {            
            e.printStackTrace();
        }  
    }
    
    public static void printAllAccounts() {
        System.out.println("There are: " + accounts.size() + " accounts in the system.");
        System.out.println(String.format("%-10s| %-30s| %-10s| %-15s| %-15s", 
                "Account #", "usernameOfAccountHolder", "type", "openingDate", "Balance"));
        System.out.println("--------------------------------------------------------------------------------");
        
        for (int i = 0; i < accounts.size(); i++) {
            System.out.println(accounts.get(i).toString() + "| $" + getBalance(accounts.get(i).getAccountNumber()));
        }
        System.out.println();
    }
    
  public static void addTransaction(String accountNumber, double amount) {
    // First, we must validate if the account exists in the system registry
    boolean accountExists = false;
    for (Account account : accounts) {
        if (account.getAccountNumber().equals(accountNumber)) {
            accountExists = true;
            break;
        }
    }

    // If the account number is not found, we reject the transaction immediately
    if (!accountExists) {
        System.err.println("Transaction Rejected: The account " + accountNumber + " was not found in our records.");
        return;
    }

    // Second, we implement overdraft protection to prevent negative balances
    if (amount < 0) {
        double currentBalance = getBalance(accountNumber);
        if (currentBalance + amount < 0) {
            System.err.println("Transaction Rejected: Insufficient funds in account " + accountNumber);
            System.err.println("Current Balance: $" + currentBalance + " | Attempted Withdrawal: $" + Math.abs(amount));
            return;
        }
    }

    // Only if both validations pass do we create the transaction and add it to the vector
    Transaction aTransaction = new Transaction(accountNumber, amount, Calendar.getInstance().getTime());
    transactions.add(aTransaction);
    System.out.println("Transaction successfully processed for account " + accountNumber);
}
    
    public static double getBalance(String accountNumber) {
        double balance = 0.0;
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAccountNumber().equals(accountNumber)) {
                // Add this transaction amount to the running account balance.
                //getAmount() returns a positive value for deposits, and a negative value for withdrawals.
                balance += transactions.get(i).getAmount();
            }
        }
        return balance;
    }

    public static void applyInterestToSavingAccounts() {
    System.out.println("\n--- Processing Monthly Interest (3%) ---");
    for (Account account : accounts) {
        // Validation Gate: Only for Saving accounts with positive balances
        if (account.getAccountType().equalsIgnoreCase("Saving")) {
            double currentBalance = getBalance(account.getAccountNumber());
            if (currentBalance > 0) {
                double interest = currentBalance * 0.03;
                addTransaction(account.getAccountNumber(), interest);
                System.out.format("Interest of $%.2f applied to Saving Account: %s\n", 
                                  interest, account.getAccountNumber());
            }
        }
    }
}

/**
 * New Feature: Multi-Currency Balance Reporter
 * Displays all account balances converted into USD, EUR, and GBP.
 */
public static void displayBalancesInMultiCurrency() {
    // Exchange rates as defined in the enhancement requirements
    double USD_TO_EUR = 0.92;
    double USD_TO_GBP = 0.79;

    System.out.println("\n====================================================================");
    System.out.println("Global Multi-Currency Balance Report");
    System.out.println("====================================================================");
    System.out.format("%-12s | %-12s | %-12s | %-12s\n", "Account #", "USD (Base)", "EUR (0.92)", "GBP (0.79)");
    System.out.println("--------------------------------------------------------------------");

    for (Account account : accounts) {
        String accNum = account.getAccountNumber();
        double balanceUSD = getBalance(accNum); // Leverages existing logic for data integrity
        
        double balanceEUR = balanceUSD * USD_TO_EUR;
        double balanceGBP = balanceUSD * USD_TO_GBP;

        System.out.format("%-12s | $%-11.2f | €%-11.2f | £%-11.2f\n", 
                          accNum, balanceUSD, balanceEUR, balanceGBP);
    }
    System.out.println("====================================================================\n");
}

    
    
    public static void main(String[] args) {
        loadUserData();
        printAllUsers();
        
        loadAccountData();
        System.out.println("Accounts: initial state, after loading...");
        printAllAccounts();
        
        addTransaction("5495-1234", -50.21);
        System.out.println("Account: after the 1st addTransaction function call...");
        printAllAccounts();
        
        addTransaction("5495-1234", 520.00);
        addTransaction("9999-1111", 21.00); 
        applyInterestToSavingAccounts(); 
        displayBalancesInMultiCurrency();
        
        System.out.println("Account: after the 2nd/3rd addTransaction function calls...");
        printAllAccounts();
    }
}