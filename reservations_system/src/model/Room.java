package model;

import java.util.Objects;

/**
 * @author DMalonas
 */
public class Room implements IRoom{
    private static int ID = 0;

    private int id;
    private final String roomNumber;
    private final Double price;
    private final RoomType roomType;

    private boolean isFree;


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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Room room))
            return false;
        return getId() == room.getId() && isFree() == room.isFree() && Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(price, room.price)
                && getRoomType() == room.getRoomType();
    }

    @Override public int hashCode() {
        return Objects.hash(getId(), getRoomNumber(), price, getRoomType(), isFree());
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
