package model;

import java.util.regex.Pattern;

/**
 * @author DMalonas
 */
public class Customer {

    private String EMAIL_REGEX = "^(.+)@(.+).(.+)$";
    private String firstName;
    private String lastName;
    private String email;

    public Customer(String email, String firstName, String lastName) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        if (!pattern.matcher(email).matches()) {
            try {
                throw new IllegalAccessException("Invalid email: " + email);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Customer() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
