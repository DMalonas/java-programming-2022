package model;

/**
 * @author DMalonas
 */
public class Room implements IRoom{
    private static int ID = 0;

    private int id;
    private String roomNumber;
    private Double price;
    private RoomType roomType;

    private boolean isFree;

    public Room() {}

    public Room(String roomNumber, Double price, RoomType roomType) {
        this.id = ID++;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        isFree = true;
    }

    @Override public String getRoomNumber() {
        return roomNumber;
    }

    @Override public Double getRoomPrice() {
        return null;
    }

    @Override public RoomType getRoomType() {
        return null;
    }

    @Override public boolean isFree() {
        return false;
    }

    public void setIsFree(boolean isFree) {
        this.isFree = isFree;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomNumber='" + roomNumber + '\'' +
                ", price=" + price +
                ", roomType=" + roomType +
                ", isFree=" + isFree +
                '}';
    }
}
