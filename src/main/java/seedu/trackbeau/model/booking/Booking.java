package seedu.trackbeau.model.booking;

import java.time.LocalDateTime;

import seedu.trackbeau.model.customer.Customer;

public class Booking {
    //private final Service Service;
    private final Customer customer;
    private final LocalDateTime startDateTime;

    /**
     * Every field must be present and not null.
     */
    public Booking(Customer customer, LocalDateTime startDateTime) {
        this.customer = customer;
        this.startDateTime = startDateTime;
    }

    public String getCustomerName() {
        return customer.getName().toString();
    }

    public String getDateTime() {
        return startDateTime.toString();
    }

    /**
     * Returns true if both bookings have the same name.
     * This defines a weaker notion of equality between two bookings.
     */
    public boolean isSameBooking(Booking otherBooking) {
        return otherBooking == this;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Name: ")
                .append(getCustomerName())
                .append("; Appointment Time: ")
                .append(getDateTime())
                .append(";");

        return builder.toString();
    }
}
