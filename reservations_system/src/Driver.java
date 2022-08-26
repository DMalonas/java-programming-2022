import api.AdminResource;
import api.HotelResource;
import model.*;
import ui.AdminMenu;
import ui.MainMenu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author DMalonas
 */
public class Driver {
    /**
     * User Scenarios
      The application provides four user scenarios:
     *      Creating a customer account. The user needs to first create
            a customer account before they can create a reservation.
     * Searching for rooms. The app should allow the user to search
        for available rooms based on provided checkin and checkout dates.
        If the application has available rooms for the specified date range,
     a list of the corresponding rooms will be displayed to the user for choosing.
     * Booking a room. Once the user has chosen a room, the app will allow
        them to book the room and create a reservation.
     * Viewing reservations. After booking a room, the app allows
        customers to view a list of all their reservations.


     * Admin Scenarios
     * The application provides four administrative scenarios:*
     * Displaying all customers accounts.
     * Viewing all of the rooms in the hotel.
     * Viewing all of the hotel reservations.
     * Adding a room to the hotel application.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelResource hotelResource = new HotelResource();
        AdminResource adminResource = new AdminResource();
        AdminMenu adminMenu = new AdminMenu();
        MainMenu mainMenu = new MainMenu();


        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

        while (true) {
            try {
                System.out.println("Choose mode [1=Admin, 2=Hotel, 3=Exit-Program]");
                int input = sc.nextInt();
                if (input == 1) {
                    while (true) {
                        adminMenu.printAdminMenu();
                        input = sc.nextInt();
                        if (input == 1) {
                            displayCustomers(adminResource);
                        } else if (input == 2) {
                            displayRooms(adminResource);
                        } else if (input == 3) {
                            displayReservations(adminResource);
                        } else if (input == 4) {
                            addRoom(sc, adminResource);
                        } else if (input == 5) {
                            break;
                        }
                    }
                } else if (input == 2) {
                    while (true) {
                        mainMenu.printMainMenu();
                        input = sc.nextInt();
                        if (input == 1) {
                            findAndReserveARoom(sc, hotelResource, formatter);
                        } else if (input == 2) {
                            seeMyReservations(sc, hotelResource);
                        } else if (input == 3) {
                            createCustomerAccount(sc, hotelResource);
                        } else if (input == 4) {
                            break;
                        } else if (input == 5) {
                            exit("\nThe program will now exit");
                        }
                    }
                } else if (input == 3) {
                    return;
                }
            } catch (Exception e) {
                System.out.println("\nBad input");
            }
        }
    }

    private static void createCustomerAccount(Scanner sc, HotelResource hotelResource) {
        // Creating a customer account.
        System.out.println("\nCustomer first name: ");
        String firstName = sc.next();
        System.out.println("Customer last name: ");
        String secondName = sc.next();
        System.out.println("Customer email: ");
        String email = sc.next();
        hotelResource.createACustomer(email, firstName, secondName);
    }

    private static void seeMyReservations(Scanner sc, HotelResource hotelResource) {
        System.out.println("\nEnter email: ");
        String email = sc.next();
        List<Reservation> customersReservations = (List<Reservation>) hotelResource.getCustomersReservations(email);
        if (customersReservations != null) {
            customersReservations.forEach(customersReservation -> System.out.println(customersReservation.toString()));
        }
    }

    private static void findAndReserveARoom(Scanner sc, HotelResource hotelResource, SimpleDateFormat formatter) {
        int input;
        try {
            System.out.println("\nProvide email to search for account: ");
            String email = sc.next();
            Customer customer = hotelResource.getCustomer(email);
            if (customer != null) {
                System.out.println("Checkin date [format=> 7-Jun-2013]: ");
                String date = sc.next();
                Date checkInDate = formatter.parse(date);
                System.out.println("Checkout date [format=> 7-Jun-2013]: ");
                date = sc.next();
                Date checkOutDate = formatter.parse(date);
                List<IRoom> availableRooms = (List<IRoom>) hotelResource.findARoom(checkInDate, checkOutDate);
                if (availableRooms != null && availableRooms.size()!= 0) {
                    int size = availableRooms.size();
                    for (int i = 0; i < size; i++) {
                        System.out.print("\n" + i + " " + availableRooms.get(i).toString());
                    }
                    input = sc.nextInt();
                    if (input >= 0 && input < size) {
                        Reservation reservation = hotelResource.bookARoom(email, availableRooms.get(input),
                                checkInDate, checkOutDate);
                        System.out.println("Reservation details: " + reservation.toString());
                    }
                } else {
                    System.out.println("\nNo rooms available");
                }
            } else {
                System.out.println("\nCustomer doesn't exist");
            }
            //                            hotelResource.findARoom()
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void displayCustomers(AdminResource adminResource) {
        List<Customer> allCustomers = (List<Customer>) adminResource.getAllCustomers();
        allCustomers.forEach(customer -> System.out.println(customer.toString()));
    }

    private static void displayRooms(AdminResource adminResource) {
        System.out.println("\nRooms:");
        adminResource.getAllRooms().forEach(room -> System.out.println(room.toString()));
    }

    private static void displayReservations(AdminResource adminResource) {
        adminResource.displayAllReservations();
    }

    private static void addRoom(Scanner sc, AdminResource adminResource) {
        System.out.println("Room number: ");
        String roomNumber = sc.next();
        Collection<IRoom> rooms = adminResource.getAllRooms();
        Optional<IRoom> roomWithProvidedNumberExists = rooms.stream().filter(room -> room.getRoomNumber().equalsIgnoreCase(roomNumber)).findAny();
        if (roomWithProvidedNumberExists.isPresent()) {
            exit("Room with room number " + roomNumber + " already exists");
        }
        System.out.println("Room price: ");
        double price = sc.nextDouble();
        System.out.println("Room type: " + Stream.of(RoomType.values()).collect(Collectors.toList()));
        String roomTypeChoice = sc.next();
        Optional<RoomType> first = Arrays.stream(RoomType.values()).findFirst();
        if (!first.isPresent()) {
            exit("Wrong enum value");
        }
        IRoom room = new Room(roomNumber, price, RoomType.valueOf(roomTypeChoice));
        List<IRoom> allRooms = (List<IRoom>) Optional.ofNullable(adminResource.getAllRooms()).orElse(new ArrayList<IRoom>());
        allRooms.add(room);
        adminResource.addRoom((List<IRoom>) allRooms);
        System.out.println("Room " + room.toString() + " added");
    }

    private static void exit(String roomNumber) {
        System.out.println(roomNumber);
        System.exit(0);
    }
}
