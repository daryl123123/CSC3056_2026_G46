package model;

public class User {



	//Setting variables
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phoneNumber;


	// Constructor 
	public User(String userName, String password, String firstName, String lastName, String phoneNumber) {
	    this.userName = userName;
	    this.password = password;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.phoneNumber = phoneNumber; 
	}


	// Getters 
	public String getUserName() {
	    return this.userName; 
	}

	public String getPassword() {
	    return this.password; 
	}

	public String getFirstName() {
	    return this.firstName; 
	}

	public String getLastName() {
	    return this.lastName; 
	}

	public String getPhoneNumber() {
	    return this.phoneNumber; 
	}

	// Setters 
	public void setUserName(String userName) {
	    if (userName == null || userName.isEmpty()) {
	        System.err.println("Username cannot be empty");
	        return;
	    }
	    this.userName = userName;
	}

	public void setPassword(String password) {
	    if (password == null || password.length() < 8) {
	        System.err.println("Password must be at least 8 characters long");
	        return;
	    }
	    this.password = password;
	}

	public void setFirstName(String firstName) {
	    if (firstName == null || firstName.isEmpty()) {
	        System.err.println("First name cannot be empty!");
	        return;
	    }
	    this.firstName = firstName; 
	}

	public void setLastName(String lastName) {
	    if (lastName == null || lastName.isEmpty()) {
	        System.err.println("Last name cannot be empty!");
	        return;
	    }
	    this.lastName = lastName;
	}

	public void setPhoneNumber(String phoneNumber) {
	    this.phoneNumber = phoneNumber;
	}

	@Override
public String toString() {
    // %-15s ensures the username always takes 15 spaces, keeping everything aligned
    return String.format("Username: %-15s | Name: %-10s %-12s | Phone: %-15s", 
                         userName, firstName, lastName, phoneNumber);
}


	}

