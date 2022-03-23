package seedu.trackbeau.model.booking.exceptions;

/**
 * Signals that the operation will result in duplicate Customers (Customers are considered duplicates if they have the
 * same identity).
 */
public class DuplicateBookingException extends RuntimeException {
    public DuplicateBookingException() {
        super("Operation would result in duplicate Booking");
    }
}
