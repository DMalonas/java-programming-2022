package model;

import java.util.Date;
import java.util.Objects;

/**
 * @author DMalonas
 */
public class Reservation {
    private final Customer customer;
    private final IRoom room;
    private final Date checkInDate;
    private final Date getCheckOutDate;


    public Reservation(Customer customer, IRoom room, Date checkInDate, Date getCheckOutDate) {
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkInDate;
        this.getCheckOutDate = getCheckOutDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public IRoom getRoom() {
        return room;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public Date getGetCheckOutDate() {
        return getCheckOutDate;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Reservation that))
            return false;
        return Objects.equals(getCustomer(), that.getCustomer()) && Objects.equals(getRoom(), that.getRoom()) && Objects.equals(getCheckInDate(), that.getCheckInDate())
                && Objects.equals(getGetCheckOutDate(), that.getGetCheckOutDate());
    }

    @Override public int hashCode() {
        return Objects.hash(getCustomer(), getRoom(), getCheckInDate(), getGetCheckOutDate());
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
