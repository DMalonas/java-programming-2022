package model;

/**
 * @author DMalonas
 */
public interface IRoom {
    public String getRoomNumber();
    public Double getRoomPrice();
    public RoomType getRoomType();

    public int getId();
    public boolean isFree();


}
