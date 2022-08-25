package api;

import model.Customer;
import model.IRoom;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.List;

/**
 * @author DMalonas
 */
public class AdminResource {
    private CustomerService customerService;
    private ReservationService reservationService;


    public AdminResource() {
        this.customerService = new CustomerService();
        this.reservationService = new ReservationService();
    }
    public Customer getCustomer(String email) {
        return new Customer();
    }

    public void addRoom(List<IRoom> rooms) {
        reservationService.addRoom(rooms);
    }

    public Collection<IRoom> getAllRooms() {
        return reservationService.getAllRooms();
    }

    public Collection<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    public void displayAllReservations() {
        reservationService.printAllReservations();
    }
}
