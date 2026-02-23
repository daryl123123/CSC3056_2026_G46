package tests.unitTests;

import model.User;
public class UserTest {

    public static void main(String[] args) {
        testConstructors();
    }

    public static void testConstructors() {
        // Multiple test cases
        String[] testUserNames = {"Daryl1234", "AliceW", "BobM", "CharlieZ", "Eva99"};
        String[] testPasswords = {"password123", "alicePass!", "bobSecret", "charliePwd", "evaKey"};
        String[] testFirstNames = {"Daryl", "Alice", "Bob", "Charlie", "Eva"};
        String[] testLastNames = {"O'Connell", "Williams", "Miller", "Zimmerman", "Evans"};
        String[] testPhoneNumbers = {"07608756789", "07123456789", "07234567890", "07345678901", "07456789012"};

        System.out.println("-----------------------");
        System.out.println("Starting User test cases...");

        for (int i = 0; i < 5; i++) {
            User testUser = new User(testUserNames[i], testPasswords[i], testFirstNames[i], testLastNames[i], testPhoneNumbers[i]);

            String testName1 = "TC" + (i+1) + "-getUserName";
            // Intentionally fail if/else for TC2 only
            if (i == 1) {
                if (testUser.getUserName().equals("FAIL-CASE")) {
                    TestUtils.printTestPassed(testName1);
                } else {
                    TestUtils.printTestFailed(testName1); // This will always fail for TC2
                }
            } else {
                if (testUser.getUserName().equals(testUserNames[i])) {
                    TestUtils.printTestPassed(testName1);
                } else {
                    TestUtils.printTestFailed(testName1);
                }
            }

            String testName2 = "TC" + (i+1) + "-getPassword";
            if(testUser.getPassword().equals(testPasswords[i])){
                TestUtils.printTestPassed(testName2);
            } else {
                TestUtils.printTestFailed(testName2);
            }

            String testName3 = "TC" + (i+1) + "-getFirstName";
            if(testUser.getFirstName().equals(testFirstNames[i])){
                TestUtils.printTestPassed(testName3);
            } else{
                TestUtils.printTestFailed(testName3);
            }

            String testName4 = "TC" + (i+1) + "-getLastName";
            if(testUser.getLastName().equals(testLastNames[i])){
                TestUtils.printTestPassed(testName4);
            } else{
                TestUtils.printTestFailed(testName4);
            }

            String testName5 = "TC" + (i+1) + "-getPhoneNumber";
            if(testUser.getPhoneNumber().equals(testPhoneNumbers[i])){
                TestUtils.printTestPassed(testName5);
            } else {
                TestUtils.printTestFailed(testName5);
            }

            // Intentionally fail the password assertion for TC1 only
            if (i == 0) {
                assert testUser.getPassword().equals("WRONGPASS") : "Password does not match for " + testName2 + " (intentional fail)";
            } else {
                assert testUser.getPassword().equals(testPasswords[i]) : "Password does not match for " + testName2;
            }

            // Assert statements for the remaining fields
            assert testUser.getFirstName().equals(testFirstNames[i]) : "First name does not match for " + testName3;
            assert testUser.getLastName().equals(testLastNames[i]) : "Last name does not match for " + testName4;
            assert testUser.getPhoneNumber().equals(testPhoneNumbers[i]) : "Phone number does not match for " + testName5;

            System.out.println("toString(): " + testUser.toString());
            System.out.println();
        }
    }
}
