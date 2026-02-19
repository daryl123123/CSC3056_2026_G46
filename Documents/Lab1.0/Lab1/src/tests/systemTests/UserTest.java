package tests.systemTests;

import model.User;
import utils.TestUtils;

public class UserTest {

    public static void main(String[] args) {

        testConstructors();

    }

    public static void testConstructors() {

        //setup
        String testUserName = "Daryl1234";
        String testPassword = "password123";
        String testFirstName = "Daryl";
        String testLastName = "O'Connell";
        String testPhoneNumber = "07608756789";

        //Create user instance
        User testUser = new User(testUserName, testPassword, testFirstName, testLastName, testPhoneNumber);



        //Automation Testing for User
        System.out.println("-----------------------");
        System.out.println("Starting test cases...");


    //Test getUsername Pass or Fail (TC1 -> Test Case 1)
    if (testUser.getUserName().equals(testUserName)) {
        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getUserName passed" + TestUtils.TEXT_COLOR_RESET);
    } else {
        System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getUserName failed" + TestUtils.TEXT_COLOR_RESET);
    }

    //Test getPassword pass or fail
    if(testUser.getPassword().equals(testPassword)){
        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getPassword passed" + TestUtils.TEXT_COLOR_GREEN);

    } else {
        System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getPassword failed" + TestUtils.TEXT_COLOR_RED);
    }

    //Test getFirtsName pass or fail
    if(testUser.getFirstName().equals(testFirstName)){
        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getFirstName passed" + TestUtils.TEXT_COLOR_GREEN);
    } else{
        System.err.println(TestUtils.TEXT_COLOR_RED + "TC1-getFirstName failed" + TestUtils.TEXT_COLOR_RED);
    }
    
    //Test getLastName pass or fail
    if(testUser.getLastName().equals(testLastName)){
        System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getLastName passed" + TestUtils.TEXT_COLOR_GREEN); 
    } else{
        System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-getLastName failed" + TestUtils.TEXT_COLOR_RED);
    }

    //Test getPhoneNumber pass or fail
    if(testUser.getPhoneNumber().equals(testPhoneNumber)){
       System.out.println(TestUtils.TEXT_COLOR_GREEN + "TC1-getPhoneNumber passed" + TestUtils.TEXT_COLOR_GREEN); 
    } else{
        System.out.println(TestUtils.TEXT_COLOR_RED + "TC1-");
    }
}

}

