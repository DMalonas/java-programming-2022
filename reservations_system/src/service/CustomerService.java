package service;

import model.Customer;
import model.IRoom;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DMalonas
 */
public class CustomerService {
    private static Map<String, Customer> customers;

    public CustomerService() {
        this.customers = new HashMap<>();

    }
    public void addCustomer(String email, String firstName, String lastName) {
        Customer customer = new Customer(email, firstName, lastName);
        customers.put(customer.getEmail(), customer);
    }

    public Customer getCustomer(String customerEmail) {
        return customers.get(customerEmail);
    }

    public Collection<Customer> getAllCustomers() {
        return customers.values().stream().collect(Collectors.toList());
    }
}
