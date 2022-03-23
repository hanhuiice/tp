package seedu.trackbeau.model.booking;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.trackbeau.model.booking.exceptions.BookingNotFoundException;
import seedu.trackbeau.model.booking.exceptions.DuplicateBookingException;

/**
 * A list of Bookings that enforces uniqueness between its elements and does not allow nulls.
 * A Booking is considered unique by comparing using {@code Booking#isSameBooking(Booking)}. As such, adding and
 * updating of Bookings uses Booking#isSameBooking(Booking) for equality so as to ensure that the Booking being
 * added or updated is unique in terms of identity in the UniqueBookingList. However, the removal of a Booking uses
 * Booking#equals(Object) so as to ensure that the Booking with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see Booking#isSameBooking(Booking)
 */
public class UniqueBookingList implements Iterable<Booking> {

    private final ObservableList<Booking> internalList = FXCollections.observableArrayList();
    private final ObservableList<Booking> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent Booking as the given argument.
     */
    public boolean contains(Booking toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameBooking);
    }

    /**
     * Adds a booking to the list.
     * The booking must not already exist in the list.
     */
    public void add(Booking toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateBookingException();
        }
        internalList.add(toAdd);
    }

    /**
     * Removes the equivalent Booking from the list.
     * The Booking must exist in the list.
     */
    public void remove(Booking toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new BookingNotFoundException();
        }
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<Booking> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<Booking> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueBookingList // instanceof handles nulls
                && internalList.equals(((UniqueBookingList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }
}
