package tests.systemTests;

import java.util.Date;

import model.Account;
import utils.TestUtils;

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

            // Test getAccountNumber Pass or Fail (TC1)
            if (testAccount.getAccountNumber().equals(testAccountNumbers[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getAccountNumber passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getAccountNumber failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Test getUsernameOfAccountHolder Pass or Fail (TC2)
            if (testAccount.getUsernameOfAccountHolder().equals(testUsernames[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getUsernameOfAccountHolder passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getUsernameOfAccountHolder failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Test getAccountType Pass or Fail (TC3)
            if (testAccount.getAccountType().equals(testAccountTypes[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getAccountType passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getAccountType failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Test getAccountOpeningDate Pass or Fail (TC4)
            if (testAccount.getAccountOpeningDate().equals(testOpeningDates[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getAccountOpeningDate passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getAccountOpeningDate failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Print toString output
            System.out.println("toString(): " + testAccount.toString());
        }
    }
}