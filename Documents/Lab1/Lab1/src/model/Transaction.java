package model;


import java.util.Date;

public class Transaction {

    private String accountNumber;
    private double amount;
    private Date transactionDate;

    //Default Constructor 
    public Transaction(){

    }

    //Constrcutor 
    public Transaction(String accountNumber, double amount, Date transactionDate){
        this.accountNumber = accountNumber;
        this.amount = amount; 
        this.transactionDate = transactionDate; 
    }


    //Setters

    public void setAccountNumber(String accountNumber) {
        if (accountNumber == null || accountNumber.isEmpty()) {
            System.err.println("Account number cannot be empty");
            return;
        }
        this.accountNumber = accountNumber;
    }


    public void setAmount(double amount) {
        if (amount <= 0) {
            System.err.println("Amount must be greater than zero");
            return;
        }
        this.amount = amount;
    }


    public void setTransactionDate(Date transactionDate) {
        if (transactionDate == null) {
            System.err.println("Transaction date cannot be null");
            return;
        }
        this.transactionDate = transactionDate;
    }


    //Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }


    // toString method

    @Override
    public String toString() {
        return String.format("%-10s| $%-10.2f| %tF", accountNumber, amount, transactionDate);
    }
    

    //Force git commit to update the file in the repository

}

