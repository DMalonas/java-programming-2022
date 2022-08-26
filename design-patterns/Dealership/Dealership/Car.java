import java.util.LinkedList;

public class Car {


    Make make;
    Model model;
    LinkedList<AddOns> addOns = new LinkedList<>();
    Customer customer;

    double totalPrice;

    Car(Make make, Model model, Customer cust) {
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
        Toyota(5000), Nissan(3000),Ford(1000);

        private double price;

        Make(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }
    enum Model{
        Camry(5000), Altima(10000),Mustang(1200);

        private double price;
        Model(double price) {
            this.price = price;
        }

        public double getPrice() {
            return price;
        }
    }
}


