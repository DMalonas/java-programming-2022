package model;

import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author DMalonas
 */
public class Customer {

    private String EMAIL_REGEX = "^(.+)@(.+).(.+)$";
    private final String firstName;
    private final String lastName;
    private final String email;


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
        this.firstName = "firstname";
        this.lastName = "lastname";
        this.email = "e@m.l";
    }

    public String getFirstName() {
        return firstName;
    }



    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Customer customer))
            return false;
        return Objects.equals(EMAIL_REGEX, customer.EMAIL_REGEX) && Objects.equals(getFirstName(), customer.getFirstName()) && Objects.equals(getLastName(),
                customer.getLastName()) && Objects.equals(getEmail(), customer.getEmail());
    }

    @Override public int hashCode() {
        return Objects.hash(EMAIL_REGEX, getFirstName(), getLastName(), getEmail());
    }

    @Override public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
