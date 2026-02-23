package tests.unitTests;

import java.util.Date;

import model.Transaction;
<<<<<<< HEAD
=======

>>>>>>> a5db6d214edddf046b1fe4836b2d8ac94850515a

public class TransactionTest {

    public static void main(String[] args) {
        testConstructors();
    }

    public static void testConstructors() {
        // Multiple test cases
        String[] testAccountNumbers = {"5495-1234", "1111-2222", "3333-4444", "5555-6666", "7777-8888"};
        double[] testAmounts = {100.50, 200.00, 50.75, 999.99, 0.01};
        Date[] testTransactionDates = {new Date(), new Date(System.currentTimeMillis()-86400000L), new Date(System.currentTimeMillis()-172800000L), new Date(System.currentTimeMillis()-259200000L), new Date(System.currentTimeMillis()-345600000L)};

        System.out.println("-----------------------");
        System.out.println("Starting Transaction test cases...");

        for (int i = 0; i < 5; i++) {
            Transaction testTransaction = new Transaction(testAccountNumbers[i], testAmounts[i], testTransactionDates[i]);

            // Test getAccountNumber
            String testName1 = "TC" + (i+1) + "-getAccountNumber";
            if (testTransaction.getAccountNumber().equals(testAccountNumbers[i])) {
                TestUtils.printTestPassed(testName1);
            } else {
                TestUtils.printTestFailed(testName1);
            }

            // Test getAmount
            String testName2 = "TC" + (i+1) + "-getAmount";
            if (testTransaction.getAmount() == testAmounts[i]) {
                TestUtils.printTestPassed(testName2);
            } else {
                TestUtils.printTestFailed(testName2);
            }

            // Test getTransactionDate
            String testName3 = "TC" + (i+1) + "-getTransactionDate";
            if (testTransaction.getTransactionDate().equals(testTransactionDates[i])) {
                TestUtils.printTestPassed(testName3);
            } else {
                TestUtils.printTestFailed(testName3);
            }

            // Print toString output
            System.out.println("toString(): " + testTransaction.toString());
        }
    }
}
