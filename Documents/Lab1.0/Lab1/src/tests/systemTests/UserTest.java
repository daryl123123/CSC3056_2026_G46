package tests.systemTests;

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

            // Test getUsername Pass or Fail (TC1 -> Test Case 1)
            if (testUser.getUserName().equals(testUserNames[i])) {
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getUserName passed" + TestUtils.TEXT_COLOR_RESET);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getUserName failed" + TestUtils.TEXT_COLOR_RESET);
            }

            // Test getPassword pass or fail
            if(testUser.getPassword().equals(testPasswords[i])){
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getPassword passed" + TestUtils.TEXT_COLOR_GREEN);
            } else {
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getPassword failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Test getFirstName pass or fail
            if(testUser.getFirstName().equals(testFirstNames[i])){
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getFirstName passed" + TestUtils.TEXT_COLOR_GREEN);
            } else{
                System.err.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getFirstName failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Test getLastName pass or fail
            if(testUser.getLastName().equals(testLastNames[i])){
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getLastName passed" + TestUtils.TEXT_COLOR_GREEN); 
            } else{
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getLastName failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Test getPhoneNumber pass or fail
            if(testUser.getPhoneNumber().equals(testPhoneNumbers[i])){
                System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC" + (i+1) + "-getPhoneNumber passed" + TestUtils.TEXT_COLOR_GREEN); 
            } else{
                System.out.println(TestUtils.TEXT_COLOR_RED + "TC" + (i+1) + "-getPhoneNumber failed" + TestUtils.TEXT_COLOR_RED);
            }

            // Print toString output
            System.out.println("toString(): " + testUser.toString());

            // Add a blank line between test cases
            System.out.println();
        }
}

}