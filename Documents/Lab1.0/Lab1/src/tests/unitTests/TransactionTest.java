package tests.systemTests;

import java.util.Date;

import model.Transaction;
import utils.TestUtils;

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

            // Test getAccountNumber Pass or Fail (TC1)
            if (testTransaction.getAccountNumber().equals(testAccountNumbers[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getAccountNumber passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getAccountNumber failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Test getAmount pass or fail
            if (testTransaction.getAmount() == testAmounts[i]) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getAmount passed" + TestUtils.TEXT_COLOR_GREEN);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getAmount failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Test getTransactionDate pass or fail
            if (testTransaction.getTransactionDate().equals(testTransactionDates[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getTransactionDate passed" + TestUtils.TEXT_COLOR_GREEN);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getTransactionDate failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Print toString output
            System.out.println("toString(): " + testTransaction.toString());
        }
    }
}
