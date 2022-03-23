package seedu.trackbeau.model;

import javafx.collections.ObservableList;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;

/**
 * Unmodifiable view of trackBeau.
 */
public interface ReadOnlyTrackBeau {

    /**
     * Returns an unmodifiable view of the customers list.
     * This list will not contain any duplicate customers.
     */
    ObservableList<Customer> getCustomerList();

    ObservableList<Booking> getBookingList();
}
