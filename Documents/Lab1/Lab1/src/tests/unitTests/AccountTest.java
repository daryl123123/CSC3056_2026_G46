package tests.unitTests;

import java.util.Date;
import model.Account;

public class AccountTest {

    public static void main(String[] args) {
        testRobustConstructors();
    }

    public static void testRobustConstructors() {
        // Parallel arrays for 5 test scenarios
        String[] testAccountNumbers = {"5495-1234", "INVALID", "3333-4444", "5555-6666", "7777-8888"};
        String[] testUsernames = {"mike", "alice", "", "charlie", "eva"};
        String[] testAccountTypes = {"Standard", "Premium", "Saving", "Business", "Joint"};
        
        // Setup dates: TC5 uses a future date (Year 2099) to trigger validation failure
        Date futureDate = new Date(System.currentTimeMillis() + 86400000000L); 
        Date[] testOpeningDates = {new Date(), new Date(), new Date(), new Date(), futureDate};

        System.out.println("-----------------------");
        System.out.println("Starting Robust Account test cases...");

        for (int i = 0; i < 5; i++) {
            Account testAccount = new Account(testAccountNumbers[i], testUsernames[i], testAccountTypes[i], testOpeningDates[i]);

            String tcName = "TC" + (i+1) + "-RobustValidation";
            
            // INTENTIONAL FAIL if/else for TC2 (Invalid Format Check)
            if (i == 1) {
                // We expect the account number to be null because the setter should have rejected "INVALID"
                if (testAccount.getAccountNumber() == null) {
                    TestUtils.printTestFailed(tcName + " (Intentional Fail: System correctly blocked invalid format)");
                } else {
                    TestUtils.printTestPassed(tcName);
                }
            } 
            // INTENTIONAL FAIL assertion for TC5 (Future Date Check)
            else if (i == 4) {
                // If the robust logic worked, the date should be NULL or rejected. 
                // This assertion intentionally checks for the future date to trigger a failure message.
                assert testAccount.getAccountOpeningDate() == testOpeningDates[i] : tcName + " (Intentional Fail: System blocked future date)";
                TestUtils.printTestFailed(tcName + " (Intentional Fail: System blocked future date)");
            }
            // Standard Pass Cases (TC1, TC3, TC4)
            else {
                if (testAccount.getAccountNumber() != null && testAccount.getAccountNumber().equals(testAccountNumbers[i])) {
                    TestUtils.printTestPassed(tcName);
                } else {
                    TestUtils.printTestFailed(tcName);
                }
                assert testAccount.getAccountNumber().equals(testAccountNumbers[i]) : tcName + " failed";
            }

            System.out.println("toString(): " + testAccount.toString());
            System.out.println();
        }
    }
}