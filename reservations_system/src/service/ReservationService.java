package service;

import model.*;

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
        IRoom r = new Room(room.getId(), room.getRoomPrice(), room.getRoomNumber(), room.getRoomType(), false);
//        rooms.stream().filter(rm -> rm.getId() != r.getId()).collect(Collectors.toList());
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId() == room.getId()) {
                rooms.add(i, r);
                break;
            }
        }
        Reservation reservation = new Reservation(customer, r, checkInDate, checkOutDate);
        reservations.put(customer.getEmail(), reservation);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate.equals(checkOutDate) || checkOutDate.before(checkInDate)) {
            System.out.println("\nCheckin date has to be before checkout date");
            return new ArrayList<>();
        }
        List<IRoom> finalList = new ArrayList<>();


        List<IRoom> freeRoomsForGivenDates = new ArrayList<>();
        for (Reservation reservation : reservations.values()) {
            Date reservationCheckInDate = reservation.getCheckInDate();
            Date reservationCheckoutDate = reservation.getGetCheckOutDate();
            boolean isBefore = isBefore(checkInDate, reservationCheckoutDate);
            if (isBefore || isAfter(checkOutDate, reservationCheckInDate)) {
                freeRoomsForGivenDates.add(reservation.getRoom());
            }
        }

        List<IRoom> freeRoomsForGivenDates2 = reservations.entrySet().stream()
                .filter(element -> {
                    Reservation r = element.getValue();
                    boolean b = isBefore(checkInDate, r.getGetCheckOutDate())
                            || isAfter(checkOutDate, r.getCheckInDate());
                    return b;
                }).map(e -> e.getValue().getRoom()).collect(Collectors.toList());

        //We want the rooms that are in rooms but not in freeRoomsForGivenDates
        for (int i = 0; i < rooms.size(); i++) {
            boolean found = false;
            for (int j = 0; j < freeRoomsForGivenDates.size(); j++) {
                if (freeRoomsForGivenDates.get(j).getId() == rooms.get(i).getId()) {
                    IRoom iRoom = rooms.get(i);
                    int id = iRoom.getId();
                    String roomNumber = iRoom.getRoomNumber();
                    Double roomPrice = iRoom.getRoomPrice();
                    RoomType roomType = iRoom.getRoomType();
                    rooms.set(i, new Room(id, roomPrice, roomNumber, roomType, true));
                }
            }
        }
        return rooms;
    }

    private boolean isBefore(Date checkInDate, Date reservationCheckoutDate) {
        return checkInDate.after(reservationCheckoutDate) ? true : false;
    }

    private boolean isAfter(Date checkOutDate, Date reservationCheckInDate) {
        return checkOutDate.before(reservationCheckInDate)  ? true : false;
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
