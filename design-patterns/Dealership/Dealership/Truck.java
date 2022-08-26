import java.util.LinkedList;

public class Truck {

    Make make;
    Model model;
    LinkedList<AddOns> addOns = new LinkedList<>();
    Customer customer;
    double basePrice;
    double totalPrice;


    Truck(Make make, Model model, Customer cust) {
        this.make = make;
        this.model = model;
        this.customer = cust;
        totalPrice = make.getPrice() + model.getPrice();

    }

    public void addAddon(AddOns addon) {
        addOns.add(addon);
        totalPrice += addon.getPrice();
    }


    public void printTicket() {
        System.out.println("Customer: " + customer.name);
        System.out.printf("Make: %s Model %s\n",make, model);
        System.out.println("Addons ");
        for(AddOns addon : addOns) {
            System.out.printf("Name: %s Price: %s\n",addon.getName(),addon.price);
        }

        System.out.printf("Total Price: %.2f\n", totalPrice);
        System.out.println("Please take this slip to the dealership to begin process of buying vehicle\n\n");


    }

    enum Make{
        Toyota(50000), Nissan(30000),Ford(10000),Tesla(50000);

        private double price;

        Make(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }
    enum Model{
        Tundra(5000), Titan(10000),F150(1200),CyberTruck(200000);

        private double price;
        Model(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }



}


