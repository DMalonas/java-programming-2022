package api;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;
import service.CustomerService;
import service.ReservationService;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author DMalonas
 */
public class HotelResource {

    private CustomerService customerService;
    private ReservationService reservationService;
    public HotelResource() {
        this.customerService = new CustomerService();
        this.reservationService = new ReservationService();
    }

    public Customer getCustomer(String email) {
        return this.customerService.getCustomer(email);
    }

    public void createACustomer(String email, String firstName, String lastName) {
        this.customerService.addCustomer(email, firstName, lastName);
    }

    public IRoom getRoom(String roomNumber) {
        return new Room();
    }


    public Reservation bookARoom(String customerEmail, IRoom room, Date checkInDate, Date checkOutDate) {
        Customer customer = customerService.getCustomer(customerEmail);
        Reservation reservation = reservationService.reserveARoom(customer, room, checkInDate, checkOutDate);
        return reservation;
    }

    public Collection<Reservation> getCustomersReservations(String customerEmail) {
        Customer customer = customerService.getCustomer(customerEmail);
        if (customer != null) {
            return reservationService.getCustomersReservation(customer);
        }
        return null;
    }

    public Collection<IRoom> findARoom(Date checkIn, Date checkOut) {
        Collection<IRoom> rooms = reservationService.findRooms(checkIn, checkOut);
        return rooms;
    }
}
