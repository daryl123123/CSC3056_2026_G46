package tests.unitTests;

import java.util.Date;

import model.Account;
<<<<<<< HEAD
=======

>>>>>>> a5db6d214edddf046b1fe4836b2d8ac94850515a

public class AccountTest {

    public static void main(String[] args) {
        testConstructors();
    }

    public static void testConstructors() {

        // Multiple test cases
        String[] testAccountNumbers = {"5495-1234", "1111-2222", "3333-4444", "5555-6666", "7777-8888"};
        String[] testUsernames = {"mike", "alice", "bob", "charlie", "eva"};
        String[] testAccountTypes = {"Standard", "Premium", "Student", "Business", "Joint"};
        Date[] testOpeningDates = {new Date(), new Date(System.currentTimeMillis()-86400000L), new Date(System.currentTimeMillis()-172800000L), new Date(System.currentTimeMillis()-259200000L), new Date(System.currentTimeMillis()-345600000L)};

        System.out.println("-----------------------");
        System.out.println("Starting Account test cases...");

        for (int i = 0; i < 5; i++) {
            Account testAccount = new Account(testAccountNumbers[i], testUsernames[i], testAccountTypes[i], testOpeningDates[i]);

            // Test getAccountNumber
            String testName1 = "TC" + (i+1) + "-getAccountNumber";
            if (testAccount.getAccountNumber().equals(testAccountNumbers[i])) {
                TestUtils.printTestPassed(testName1);
            } else {
                TestUtils.printTestFailed(testName1);
            }

            // Test getUsernameOfAccountHolder
            String testName2 = "TC" + (i+1) + "-getUsernameOfAccountHolder";
            if (testAccount.getUsernameOfAccountHolder().equals(testUsernames[i])) {
                TestUtils.printTestPassed(testName2);
            } else {
                TestUtils.printTestFailed(testName2);
            }

            // Test getAccountType
            String testName3 = "TC" + (i+1) + "-getAccountType";
            if (testAccount.getAccountType().equals(testAccountTypes[i])) {
                TestUtils.printTestPassed(testName3);
            } else {
                TestUtils.printTestFailed(testName3);
            }

            // Test getAccountOpeningDate
            String testName4 = "TC" + (i+1) + "-getAccountOpeningDate";
            if (testAccount.getAccountOpeningDate().equals(testOpeningDates[i])) {
                TestUtils.printTestPassed(testName4);
            } else {
                TestUtils.printTestFailed(testName4);
            }

            // Print toString output
            System.out.println("toString(): " + testAccount.toString());
        }
    }
}