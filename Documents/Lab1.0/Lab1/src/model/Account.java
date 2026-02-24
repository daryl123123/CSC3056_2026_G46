package model;

import java.util.Date;

public class Account {
    private String accountNumber;
    private String usernameOfAccountHolder;
    private String accountType;
    private Date accountOpeningDate;
    
    public Account(String accountNumber, String usernameOfAccountHolder, String accountType, Date accountOpeningDate) {
        // Defensive Constructor: Routes all initialization through validation setters
        setAccountNumber(accountNumber);
        setUsernameOfAccountHolder(usernameOfAccountHolder);
        setAccountType(accountType);
        setAccountOpeningDate(accountOpeningDate);
    }
    
    // Validated Setters
    public void setAccountNumber(String accountNumber) {
        // Validation: Ensures account number follows the XXXX-XXXX format
        if (accountNumber == null || !accountNumber.matches("\\d{4}-\\d{4}")) {
            System.err.println("Error: Account number must follow the format 'XXXX-XXXX'.");
            return;
        }
        this.accountNumber = accountNumber;
    }

    public void setUsernameOfAccountHolder(String usernameOfAccountHolder) {
        if (usernameOfAccountHolder == null || usernameOfAccountHolder.trim().isEmpty()) {
            System.err.println("Error: Username cannot be empty.");
            return;
        }
        this.usernameOfAccountHolder = usernameOfAccountHolder;
    }

    public void setAccountType(String accountType) {
        // Validation: Restricts input to valid categories
        if (accountType == null || (!accountType.equalsIgnoreCase("Standard") && !accountType.equalsIgnoreCase("Saving"))) {
            System.err.println("Error: Invalid Account Type. Must be 'Standard' or 'Saving'.");
            return;
        }
        this.accountType = accountType;
    }

    public void setAccountOpeningDate(Date accountOpeningDate) {
        // Validation: Prevents physically impossible "future" dates
        if (accountOpeningDate == null || accountOpeningDate.after(new Date())) {
            System.err.println("Error: Opening date cannot be in the future.");
            return;
        }
        this.accountOpeningDate = accountOpeningDate;
    }

    // Getters
    public String getAccountNumber() { return accountNumber; }
    public String getUsernameOfAccountHolder() { return usernameOfAccountHolder; }
    public String getAccountType() { return accountType; }
    public Date getAccountOpeningDate() { return accountOpeningDate; }

    @Override
    public String toString() {
        return String.format("%-10s| %-30s| %-10s| %-15s", 
            accountNumber, usernameOfAccountHolder, accountType, 
            (accountOpeningDate != null ? accountOpeningDate.toString() : "N/A"));
    }
}