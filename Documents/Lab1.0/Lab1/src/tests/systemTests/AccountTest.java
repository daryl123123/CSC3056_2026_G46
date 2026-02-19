package tests.systemTests;

import java.util.Date;

import model.Account;
import utils.TestUtils;

public class AccountTest {

    public static void main(String[] args) {
        testConstructors();
    }

    public static void testConstructors() {

        // setup
        String testAccountNumber = "5495-1234";
        String testUsername = "mike";
        String testAccountType = "Standard";
        Date testOpeningDate = new Date(); // Current date for testing

        // Create account instance 
        Account testAccount = new Account(testAccountNumber, testUsername, testAccountType, testOpeningDate);

        // Automation Testing for Account
        System.out.println("-----------------------");
        System.out.println("Starting Account test cases...");

        // Test getAccountNumber Pass or Fail (TC1)
        if (testAccount.getAccountNumber().equals(testAccountNumber)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getAccountNumber passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getAccountNumber failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Test getUsernameOfAccountHolder Pass or Fail (TC2)
        if (testAccount.getUsernameOfAccountHolder().equals(testUsername)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC2-getUsernameOfAccountHolder passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC2-getUsernameOfAccountHolder failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Test getAccountType Pass or Fail (TC3)
        if (testAccount.getAccountType().equals(testAccountType)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC3-getAccountType passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC3-getAccountType failed" + TestUtils.TEXT_COLOR_RESET);
        }

        // Test getAccountOpeningDate Pass or Fail (TC4)
        if (testAccount.getAccountOpeningDate().equals(testOpeningDate)) {
            System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC4-getAccountOpeningDate passed" + TestUtils.TEXT_COLOR_RESET);
        } else {
            System.out.println(TestUtils.TEXT_COLOR_RED + "TC4-getAccountOpeningDate failed" + TestUtils.TEXT_COLOR_RESET);
        }
    }
}