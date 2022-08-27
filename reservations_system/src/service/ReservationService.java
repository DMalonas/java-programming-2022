package service;

import model.*;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author DMalonas
 */
public class ReservationService {

    private static Map<String, List<Reservation>> reservations;
    private static List<IRoom> rooms;


    public ReservationService() {
        this.rooms = new ArrayList<>();
        reservations = new HashMap<String, List<Reservation>>();
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
        Reservation reservation = new Reservation(customer, r, checkInDate, checkOutDate);
        List<Reservation> reservationsForCustomer = reservations.get(customer.getEmail());
        if (reservationsForCustomer == null) {
            reservationsForCustomer = new ArrayList<>();
        }
        reservationsForCustomer.add(reservation);
        reservations.put(customer.getEmail(), reservationsForCustomer);
        return reservation;
    }

    public Collection<IRoom> findRooms(Date checkInDate, Date checkOutDate) {
        if (checkInDate.equals(checkOutDate) || checkOutDate.before(checkInDate)) {
            System.out.println("\nCheckin date has to be before checkout date");
            return new ArrayList<>();
        }

        //filter all the rooms
        //and if there is no room with the same id reserved for the time given, then keep that room
        //and add it to the list
        //We want the rooms that are in the rooms list but not in freeRoomsForGivenDates
        Collection<IRoom> finalList2 = new ArrayList<>();
        finalList2.addAll(rooms);
        for (int i = 0; i < rooms.size(); i++) {
            List<Reservation> reservationValues = reservations.values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList());
            for (int j = 0; j < reservationValues.size(); j++) {
                IRoom currentRoom = rooms.get(i);
                Reservation currentReservation = reservationValues.get(j);
                IRoom currentReservedRoom = currentReservation.getRoom();
                if (currentReservedRoom.getId() == currentRoom.getId()) {
                    boolean  datesDontOverlap = isBefore(checkInDate, currentReservation.getGetCheckOutDate())
                            || isAfter(checkOutDate, currentReservation.getCheckInDate());
                    if (datesDontOverlap == false) {
                        finalList2.remove(currentRoom);
                    }
                }
            }
        }
        return finalList2;

//        List<IRoom> finalList = new ArrayList<>();


//        List<IRoom> freeRoomsForGivenDates = new ArrayList<>();
//        for (Reservation reservation : reservations.values()) {
//            Date reservationCheckInDate = reservation.getCheckInDate();
//            Date reservationCheckoutDate = reservation.getGetCheckOutDate();
//            boolean isBefore = isBefore(checkInDate, reservationCheckoutDate);
//            if (isBefore || isAfter(checkOutDate, reservationCheckInDate)) {
//                freeRoomsForGivenDates.add(reservation.getRoom());
//            }
//        }

//        List<IRoom> freeRoomsForGivenDatesThatHoldAtLeastOneReservation = reservations.values().stream()
//                .filter(r -> {
//                    boolean  datesOverlap = isBefore(checkInDate, r.getGetCheckOutDate())
//                            || isAfter(checkOutDate, r.getCheckInDate());
//                    if (!datesOverlap) {
//                        return true;
//                    }
//                    return false;
//                }).map(e -> e.getRoom()).collect(toList());



//        numbers1.stream()
//                .flatMap(i ->
//                        numbers2.stream()
//                                .filter(j -> (i + j) % 3 == 0)
//                                .map(j -> new int[]{i, j})
//                )
//                .collect(toList());

        /**
         * Loop through reservations, if date overlap is found remove from rooms
//         */
//        return reservations.values().stream().filter(reservation -> {
//                                    rooms.stream().anyMatch(room -> {
//                                        boolean datesOverlap = isBefore(checkInDate, reservation.getGetCheckOutDate())
//                                                || isAfter(checkOutDate, reservation.getCheckInDate());
//                                        if (!datesOverlap) {
//                                            return true;
//                                        }
//                                        boolean requestedRoomIsReservedForCertainDates = room.getId() == reservation.getRoom().getId();
//                                        if (requestedRoomIsReservedForCertainDates) {
//                                            return false;
//                                        }
//                                        return true;
//                                    })
//
//        return reservations.values().stream().flatMap(r -> rooms.stream().filter(room -> {
//            IRoom reservedRoom = r.getRoom();
//            boolean datesOverlap = isBefore(checkInDate, r.getGetCheckOutDate())
//                    || isAfter(checkOutDate, r.getCheckInDate());
//            if (!datesOverlap) {
//                return true;
//            }
//            boolean requestedRoomIsReservedForCertainDates = room.getId() == reservedRoom.getId();
//            if (requestedRoomIsReservedForCertainDates) {
//                return false;
//            }
//            return true;
//        })).collect(Collectors.toList());


//        List<IRoom> collect = rooms.stream().flatMap(room -> reservations.values().stream()
//                        .filter(reservation -> {
//                            IRoom reservedRoom = reservation.getRoom();
//                            boolean datesOverlap = isBefore(checkInDate, reservation.getGetCheckOutDate())
//                                    || isAfter(checkOutDate, reservation.getCheckInDate());
//                            if (!datesOverlap) {
//                                return true;
//                            }
//                            boolean requestedRoomIsReservedForCertainDates = room.getId() == reservedRoom.getId();
//                            if (requestedRoomIsReservedForCertainDates) {
//                                return false;
//                            }
//                            return true;
//                        })
//                        .map(reservation -> reservation.getRoom())
//                )
//                .collect(toList());
//        return collect;
    }

    private boolean isBefore(Date checkInDate, Date reservationCheckoutDate) {
        return checkInDate.after(reservationCheckoutDate) ? true : false;
    }

    private boolean isAfter(Date checkOutDate, Date reservationCheckInDate) {
        return checkOutDate.before(reservationCheckInDate)  ? true : false;
    }

    public Collection<Reservation> getCustomersReservation(Customer customer) {
        for (Map.Entry<String, List<Reservation>> entry : reservations.entrySet()) {
            String key = entry.getKey();
            List<Reservation> value = entry.getValue();
            if (key.equals(customer.getEmail())) {
                return value;
            }
        }
        return new ArrayList<>();
    }

    public void printAllReservations() {
        reservations.forEach((key, value) -> {
            System.out.println(key + ": " + value);
        });
    }

    public Map<String, List<Reservation>> getAllReservations() {
        return reservations;
    }

    public List<IRoom> getAllRooms() {
        return this.rooms;
    }
}
