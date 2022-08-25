package service;

import model.Customer;
import model.IRoom;
import model.Reservation;
import model.Room;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author DMalonas
 */
public class ReservationService {

    private static Map<String, Reservation> reservations;
    private static List<IRoom> rooms;


    public ReservationService() {
        this.rooms = new ArrayList<>();
        reservations = new HashMap<>();
    }

    public void addRoom(List<IRoom> rooms) {
        this.rooms = rooms;
    }

    public IRoom getRoom(String roomId) {
        return rooms.stream().filter(room -> {
            return room.getId() == Integer.parseInt(roomId);
        }).findAny().get();
    }

    public Reservation reserveARoom(Customer customer, IRoom room, Date checkInDate, Date checkOutDate) {
        Reservation reservation = new Reservation(customer, room, checkInDate, checkOutDate);
        reservations.put(customer.getEmail(), reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
//        List<IRoom> availableRooms = new ArrayList<>();
//        for (int i = 0; i < reservations.size(); i++) {
//            Reservation reservation = reservations.get(i);
//            Date currentCheckInDate = reservation.getCheckInDate();
//            Date currentCheckOutDate = reservation.getGetCheckOutDate();
//            if (currentCheckOutDate.before(checkInDate) || currentCheckInDate.after(checkOutDate)) {
//                availableRooms.add()
//            }
//        }
        List<IRoom> freeRoomsForGivenDates = reservations.entrySet().stream().filter(element -> {
            Reservation r = element.getValue();
            long checkInDateTime = checkInDate.getTime();
            long checkOutDateTime = checkOutDate.getTime();
            return r.getGetCheckOutDate().getTime() < checkInDateTime || r.getCheckInDate().getTime() > checkOutDateTime;
        }).map(e -> e.getValue().getRoom()).collect(Collectors.toList());
        return freeRoomsForGivenDates;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        List<Reservation> customerReservations = reservations.entrySet().stream().filter(element -> {
            return element.getValue().getCustomer().getEmail().equals(customer.getEmail());
        }).map(e -> e.getValue()).collect(Collectors.toList());
        return customerReservations;
    }

    public void printAllReservations() {
        reservations.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }

    public Map<String, Reservation> getAllReservations() {
        return reservations;
    }

    public List<IRoom> getAllRooms() {
        return this.rooms;
    }
}
