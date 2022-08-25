package model;

/**
 * @author DMalonas
 */
public class FreeRoom extends Room {

    public FreeRoom(String roomNumber, Double price, RoomType roomType) {
        super(roomNumber, (double) 0, roomType);
    }

    @Override public String toString() {
        return "FreeRoom{ Room number: " + super.getRoomNumber()
                + ", room price: " + super.getRoomPrice()
                + ", room type: " + super.getRoomType() + "}\n";
    }
}
