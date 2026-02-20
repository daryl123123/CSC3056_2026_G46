package tests.unitTests;

import model.User;
import utils.TestUtils;

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

            if (testUser.getUserName().equals(testUserNames[i])) {
                TestUtils.printTestPassed("TC" + (i+1) + "-getUserName passed");
            } else {
                TestUtils.printTestFailed("TC" + (i+1) + "-getUserName failed");
            }

            if(testUser.getPassword().equals(testPasswords[i])){
                TestUtils.printTestPassed("TC" + (i+1) + "-getPassword passed");
            } else {
                TestUtils.printTestFailed("TC" + (i+1) + "-getPassword failed");
            }

            if(testUser.getFirstName().equals(testFirstNames[i])){
                TestUtils.printTestPassed("TC" + (i+1) + "-getFirstName passed");
            } else{
                TestUtils.printTestFailed("TC" + (i+1) + "-getFirstName failed");
            }

            if(testUser.getLastName().equals(testLastNames[i])){
                TestUtils.printTestPassed("TC" + (i+1) + "-getLastName passed");
            } else{
                TestUtils.printTestFailed("TC" + (i+1) + "-getLastName failed");
            }

            if(testUser.getPhoneNumber().equals(testPhoneNumbers[i])){
                TestUtils.printTestPassed("TC" + (i+1) + "-getPhoneNumber passed");
            } else{
                TestUtils.printTestFailed("TC" + (i+1) + "-getPhoneNumber failed");
            }

            System.out.println("toString(): " + testUser.toString());
            System.out.println();
        }
    }
}
