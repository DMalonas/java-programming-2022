package model;

import java.util.Date;

/**
 * @author DMalonas
 */
public class Reservation {
    Customer customer;
    IRoom room;
    Date checkInDate;
    Date getCheckOutDate;

    public Reservation() {}

    public Reservation(Customer customer, IRoom room, Date checkInDate, Date getCheckOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.getCheckOutDate = getCheckOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public void setRoom(IRoom room) {
        this.room = room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getGetCheckOutDate() {
        return getCheckOutDate;
    }

    public void setGetCheckOutDate(Date getCheckOutDate) {
        this.getCheckOutDate = getCheckOutDate;
    }

    @Override public String toString() {
        return "Reservation{" +
                "customer=" + customer.toString() +
                ", room=" + room.toString() +
                ", checkInDate=" + checkInDate.toString() +
                ", getCheckOutDate=" + getCheckOutDate.toString() +
                '}';
    }
}
