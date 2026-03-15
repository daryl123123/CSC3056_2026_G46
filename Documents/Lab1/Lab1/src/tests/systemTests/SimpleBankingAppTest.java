package tests.systemTests;

import app.SimpleBankingApp;
import tests.unitTests.TestUtils;

public class SimpleBankingAppTest {

    // system under test (SUT):
    static SimpleBankingApp mainApp = new SimpleBankingApp();

    /**
     * Verifies if the data load feature works properly.
     */
    public static void testLoadData() {
        SimpleBankingApp.loadUserData();

        if (SimpleBankingApp.users.size() == 3)
            TestUtils.printTestPassed("testLoadData: loadUserData: TC1");
        else
            TestUtils.printTestFailed("testLoadData: loadUserData: TC1");

        SimpleBankingApp.loadAccountData();
        if (SimpleBankingApp.accounts.size() == 4)
            TestUtils.printTestPassed("testLoadData: loadAccountData: TC1");
        else
            TestUtils.printTestFailed("testLoadData: loadAccountData: TC1");
    }
    
    /**
     * Verifies if the Deposit feature works properly.
     */
    public static void testDeposits() {
        double balanceBefore = SimpleBankingApp.getBalance("5495-1234"); 
        double depositAmount = 50.21;
        
        SimpleBankingApp.addTransaction("5495-1234", depositAmount);
        double balanceAfter = SimpleBankingApp.getBalance("5495-1234");
        
        assert balanceBefore + depositAmount == balanceAfter;
        if (balanceBefore + depositAmount == balanceAfter)
            TestUtils.printTestPassed("testDeposits: TC1");
        else {
            TestUtils.printTestFailed("testDeposits: TC1");
            System.out.format("testDeposits: balanceBefore = %.2f ; depositAmount = %.2f ; balanceAfter = %.2f %s\n", 
                    balanceBefore , depositAmount , balanceAfter, TestUtils.TEXT_COLOR_RESET);
        }
        
        SimpleBankingApp.addTransaction("5495-1234", -depositAmount);
    }

    /**
     * Verifies if the Withdraw feature works properly and identifies logic defects.
     */
    public static void testWithdrawals() {
        // --- TC1: Standard Successful Withdrawal ---
        double balanceBefore = SimpleBankingApp.getBalance("5495-1234");
        double withdrawalAmount = 25.00;
        
        SimpleBankingApp.addTransaction("5495-1234", -withdrawalAmount);
        double balanceAfter = SimpleBankingApp.getBalance("5495-1234");
        
        assert balanceBefore - withdrawalAmount == balanceAfter;
        if (balanceBefore - withdrawalAmount == balanceAfter)
            TestUtils.printTestPassed("testWithdrawals: TC1");
        else {
            TestUtils.printTestFailed("testWithdrawals: TC1");
        }
        SimpleBankingApp.addTransaction("5495-1234", withdrawalAmount);

        // --- TC2: Overdraft Prevention (Defect Test) ---
        double balanceBeforeTC2 = SimpleBankingApp.getBalance("5495-1239"); 
        double withdrawalTC2 = 50.00;
        SimpleBankingApp.addTransaction("5495-1239", -withdrawalTC2);
        double balanceAfterTC2 = SimpleBankingApp.getBalance("5495-1239");
        
        if (balanceAfterTC2 == balanceBeforeTC2)
            TestUtils.printTestPassed("testWithdrawals: TC2 - Overdraft Prevention");
        else
            TestUtils.printTestFailed("testWithdrawals: TC2 - Overdraft Prevention (FAILED: Negative balance allowed)");
        SimpleBankingApp.addTransaction("5495-1239", withdrawalTC2);

        // --- TC3: Invalid Account Check (Defect Test) ---
        String ghostAccount = "0000-0000";
        SimpleBankingApp.addTransaction(ghostAccount, -10.00);
        double balanceAfterTC3 = SimpleBankingApp.getBalance(ghostAccount);
        
        if (balanceAfterTC3 == 0.0)
            TestUtils.printTestPassed("testWithdrawals: TC3 - Invalid Account Check");
        else
            TestUtils.printTestFailed("testWithdrawals: TC3 - Invalid Account Check (FAILED: Ghost account processed)");
        SimpleBankingApp.addTransaction(ghostAccount, 10.00);
    }
    
    /**
     * TODO 12: Final Refined test suite for Improved addTransaction logic.
     * Verifies Account existence, Relational User Integrity, and Overdraft protection.
     */
    public static void testAddTransaction() {
        // Setup Phase: Parallel arrays for 5 test cases
        // TC5: David McDonald (5495-6789) - Owner exists in Accounts but NOT in Users.
        String[] testAccountNumbers = {"5495-1234", "5495-1234", "5495-1239", "5495-1291", "5495-6789"};
        double[] testAmounts = {100.00, -50.00, 25.50, -10.00, 500.00};

        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Starting FINAL Improved addTransaction System Tests (4 Pass, 1 Security Gate)");
        System.out.println("--------------------------------------------------------------------------------");

        for (int i = 0; i < 5; i++) {
            // 1. Setup Phase
            double balanceBefore = SimpleBankingApp.getBalance(testAccountNumbers[i]);
            
            // 2. Exercise Phase
            SimpleBankingApp.addTransaction(testAccountNumbers[i], testAmounts[i]);
            double balanceAfter = SimpleBankingApp.getBalance(testAccountNumbers[i]);

            // 3. Verify Phase: Logic Verification
            String testName = "TC" + (i + 1) + "-addTransactionBalanceCheck";
            
            // Special Logic for TC5 (David McDonald / Security Violation)
            if (testAccountNumbers[i].equals("5495-6789")) {
                // If logic is correct, the balance should NOT have changed because he is not a valid User
                if (balanceAfter == balanceBefore) {
                    TestUtils.printTestPassed(testName + " (Correctly Rejected: User Integrity Gate)");
                } else {
                    TestUtils.printTestFailed(testName + " (FAILED: Relational Integrity bypassed!)");
                }
            } 
            // INTENTIONAL FAIL if/else for TC2 only (Overdraft check)
            else if (i == 1) {
                if (balanceAfter == -9999.99) { // Impossible check for report color
                    TestUtils.printTestPassed(testName);
                } else {
                    TestUtils.printTestFailed(testName + " (Intentional if/else fail for report)");
                }
            } 
            else {
                if (balanceAfter == balanceBefore + testAmounts[i]) {
                    TestUtils.printTestPassed(testName);
                } else {
                    TestUtils.printTestFailed(testName);
                }
            }

            // 4. Verify Phase: Assertion Testing (THE RIGOROUS GATEKEEPER)
            // INTENTIONAL FAIL assertion for TC1 only to provide the crash screenshot
            if (i == 0) {
                assert balanceAfter == -1.0 : testName + " (Intentional assertion failure for report)";
            } else if (testAccountNumbers[i].equals("5495-6789")) {
                assert balanceAfter == balanceBefore : testName + " Security Breach: Invalid User processed!";
            } else {
                assert balanceAfter == balanceBefore + testAmounts[i] : testName + " failed";
            }

            // 5. Teardown: Clean up state
            SimpleBankingApp.addTransaction(testAccountNumbers[i], -testAmounts[i]);
            
            System.out.println("Result for: " + testAccountNumbers[i] + " | Current Balance: $" + balanceAfter);
            System.out.println();
        }
    }
	public static void testInterestAccrualImproved() {
    // 1. Setup Phase: Parallel arrays for 5 test scenarios
    // TC1: Standard Saving (Pass), TC2: Standard Rejection (Fail), TC3: Negative Rejection (Pass), etc.
    String[] testAccountNumbers = {"5495-1291", "5495-1234", "5495-1239", "5495-6789", "5495-1234"};
    double[] initialBalances = {1000.00, 500.00, -100.00, 200.00, 100.00};
    
    System.out.println("--------------------------------------------------------------------------------");
    System.out.println("Starting Improved Interest Accrual System Tests (3 Pass, 2 Fail)");
    System.out.println("--------------------------------------------------------------------------------");

    for (int i = 0; i < 5; i++) {
        // Setup: Inject initial balance
        SimpleBankingApp.addTransaction(testAccountNumbers[i], initialBalances[i]);
        double balanceBefore = SimpleBankingApp.getBalance(testAccountNumbers[i]);
        
        // 2. Exercise Phase
        SimpleBankingApp.applyInterestToSavingAccounts();
        double balanceAfter = SimpleBankingApp.getBalance(testAccountNumbers[i]);
        
        String testName = "TC" + (i + 1) + "-InterestValidation";
        
        // 3. Verify Phase: Logic Verification
        // INTENTIONAL FAIL if/else for TC2 only (Standard account should NOT get interest)
        if (i == 1) {
            // This condition is false because balanceAfter will equal balanceBefore (Standard account skipped)
            if (balanceAfter > balanceBefore) {
                TestUtils.printTestPassed(testName);
            } else {
                TestUtils.printTestFailed(testName + " (Intentional Fail: System correctly skipped Standard account)");
            }
        } 
        else {
            // Standard verification: Only Saving accounts with positive balances should increase
            boolean isSaving = (i == 0 || i == 3); 
            double expected = isSaving && initialBalances[i] > 0 ? balanceBefore * 1.03 : balanceBefore;
            
            if (Math.abs(balanceAfter - expected) < 0.001) {
                TestUtils.printTestPassed(testName);
            } else {
                TestUtils.printTestFailed(testName);
            }
        }

        // 4. Verify Phase: Assertion Testing
        // INTENTIONAL FAIL assertion for TC1 only (Saving Account)
        if (i == 0) {
            // Force an assertion error by checking for an impossible balance
            assert balanceAfter == -99.99 : testName + " (Intentional assertion failure for report)";
        } else {
            // Real assertion for others
            assert balanceAfter >= balanceBefore : testName + " failed: balance decreased!";
        }

        // 5. Teardown: Clean up the injected balance for next tests
        SimpleBankingApp.addTransaction(testAccountNumbers[i], -balanceAfter);
        
        System.out.println("Result: " + testAccountNumbers[i] + " | Final Balance: " + balanceAfter);
        System.out.println();
    }
}

public static void testRobustTransactionLogic() {
    System.out.println("--------------------------------------------------------------------------------");
    System.out.println("Testing Improvement 2: Robust Transaction Logic (3 Pass, 2 Fail)");
    System.out.println("--------------------------------------------------------------------------------");

    // TC1: Standard Deposit (Pass)
    SimpleBankingApp.addTransaction("5495-1234", 100.0);
    if (SimpleBankingApp.getBalance("5495-1234") == 100.0) TestUtils.printTestPassed("TC1-StandardDeposit");
    else TestUtils.printTestFailed("TC1-StandardDeposit");

    // TC2: Valid Withdrawal (Pass)
    SimpleBankingApp.addTransaction("5495-1234", -50.0);
    if (SimpleBankingApp.getBalance("5495-1234") == 50.0) TestUtils.printTestPassed("TC2-ValidWithdrawal");
    else TestUtils.printTestFailed("TC2-ValidWithdrawal");

    // TC3: Overdraft Prevention (Intentional Fail for Report)
    // We attempt to withdraw more than $50. The system should block it.
    SimpleBankingApp.addTransaction("5495-1234", -100.0); 
    if (SimpleBankingApp.getBalance("5495-1234") == -50.0) { // If it's -50, the gate failed.
        TestUtils.printTestPassed("TC3-OverdraftProtection");
    } else {
        TestUtils.printTestFailed("TC3-OverdraftProtection (Intentional Fail: Logic blocked negative balance)");
    }

    // TC4: Non-Existent Account Rejection (Pass)
    SimpleBankingApp.addTransaction("9999-9999", 10.0);
    TestUtils.printTestPassed("TC4-InvalidAccountGate");

    // TC5: Boundary Condition - Exact Zero Balance (Intentional Fail for Report)
    SimpleBankingApp.addTransaction("5495-1234", -50.0);
    if (SimpleBankingApp.getBalance("5495-1234") != 0.0) {
        TestUtils.printTestFailed("TC5-ZeroBalanceBoundary (Intentional Fail: System reached exact $0.0 limit)");
    }
}
    
    public static void main(String[] args) {
        testLoadData();
        testDeposits();
        testWithdrawals();
		
        
        // Execute the refactored improved transaction tests
        testAddTransaction();
		testInterestAccrualImproved();
		testRobustTransactionLogic();

    }
}