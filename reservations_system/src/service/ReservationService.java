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

    private Date checkInDate;
    private Date checkoutDate;


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
//        Collection<IRoom> finalList2 = new ArrayList<>();
//        finalList2.addAll(rooms);
//        for (int i = 0; i < rooms.size(); i++) {
//            List<Reservation> reservationValues = reservations.values().stream()
//                    .flatMap(List::stream)
//                    .collect(Collectors.toList());
//            for (int j = 0; j < reservationValues.size(); j++) {
//                IRoom currentRoom = rooms.get(i);
//                Reservation currentReservation = reservationValues.get(j);
//                IRoom currentReservedRoom = currentReservation.getRoom();
//                if (currentReservedRoom.getId() == currentRoom.getId()) {
//                    boolean  datesDontOverlap = isBefore(checkInDate, currentReservation.getGetCheckOutDate())
//                            || isAfter(checkOutDate, currentReservation.getCheckInDate());
//                    if (datesDontOverlap == false) {
//                        finalList2.remove(currentRoom);
//                    }
//                }
//            }
//        }
//
//        if (finalList2.size() == 0) {
//            finalList2.addAll(rooms);
//            System.out.println("\nNo free rooms on these dates\nAlternative options:\n");
//            for (int i = 0; i < rooms.size(); i++) {
//                List<Reservation> reservationValues = reservations.values().stream()
//                        .flatMap(List::stream)
//                        .collect(Collectors.toList());
//                for (int j = 0; j < reservationValues.size(); j++) {
//                    IRoom currentRoom = rooms.get(i);
//                    Reservation currentReservation = reservationValues.get(j);
//                    IRoom currentReservedRoom = currentReservation.getRoom();
//                    if (currentReservedRoom.getId() == currentRoom.getId()) {
//                        boolean  datesDontOverlap = isBefore(DateUtil.addDays(checkInDate, 7), currentReservation.getGetCheckOutDate())
//                                || isAfter(DateUtil.addDays(checkOutDate, 7), currentReservation.getCheckInDate());
//                        if (datesDontOverlap == false) {
//                            finalList2.remove(currentRoom);
//                        }
//                    }
//                }
//            }
//        }
//        return finalList2;

        // A room is available, if there are NO reservations for the room overlapping with the requested dates.
        // A room is available, if there are NO reservations for the room overlapping with the requested dates.
        this.checkInDate = checkInDate;
        this.checkoutDate = checkOutDate;
//        Arrays.asList(1, 2, 3).stream().noneMatch(i ->i > 5);
//        Arrays.asList(1, 2, 3).stream().filter(i -> i % 2 == 0).collect(toList());
        List<IRoom> collect = rooms.stream().filter(room -> {
            return reservations.values().stream().flatMap(List::stream).filter(r -> r.getRoom().getId() == room.getId()).noneMatch(r -> {
                Date rCheckInDate = r.getCheckInDate();
                Date rCheckOutDate = r.getGetCheckOutDate();
                boolean datesDontOverlap = isBefore(checkInDate, rCheckOutDate)
                        || isAfter(checkOutDate, rCheckInDate);
                if (datesDontOverlap == false) { // overlap
                    return true;
                }
                return false;
            });
        }).collect(toList());

        this.checkInDate = DateUtil.addDays(checkInDate, 7);
        this.checkoutDate = DateUtil.addDays(checkOutDate, 7);

        if (collect.size() == 0) {
            collect = rooms.stream().filter(room -> {
                return reservations.values().stream().flatMap(List::stream).filter(r -> r.getRoom().getId() == room.getId()).anyMatch(r -> {
                    Date rCheckInDate = r.getCheckInDate();
                    Date rCheckOutDate = r.getGetCheckOutDate();
                    boolean datesDontOverlap = isBefore(this.checkInDate, rCheckOutDate)
                            || isAfter(this.checkoutDate, rCheckInDate);
                    if (datesDontOverlap) { // doesnt overlap
                        return true;
                    }
                    return false;
                });
            }).collect(toList());
        }
        return collect;

//        List<IRoom> collect = rooms.stream().flatMap(room ->
//                reservations.values().stream().flatMap(List::stream)
//                        .filter(r -> {
//                            Date rCheckInDate = r.getCheckInDate();
//                            Date rCheckOutDate = r.getGetCheckOutDate();
//                            boolean datesDontOverlap = isBefore(checkInDate, rCheckOutDate)
//                                    || isAfter(checkOutDate, rCheckInDate);
//                            if (datesDontOverlap == false) {
//                                return false;
//                            }
//                            return true;
//                        }).map(r -> {
//                            return room;
//                        })).collect(toList());
//        return collect;

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

     boolean isAfter(Date checkOutDate, Date reservationCheckInDate) {
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


    public class DateUtil
    {
        public static Date addDays(Date date, int days)
        {
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            cal.add(Calendar.DATE, days); //minus number would decrement the days
            return cal.getTime();
        }
    }
}
