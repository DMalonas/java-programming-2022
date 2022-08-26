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

    public Room(int id, Double price, String roomNumber,
            RoomType roomType, boolean isFree) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.price = price;
        this.roomType = roomType;
        this.isFree = isFree;
    }

    @Override public String getRoomNumber() {
        return roomNumber;
    }

    @Override public Double getRoomPrice() {
        return price;
    }

    @Override public RoomType getRoomType() {
        return roomType;
    }

    @Override public boolean isFree() {
        return isFree;
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
