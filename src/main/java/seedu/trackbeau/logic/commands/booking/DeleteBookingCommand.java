package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.Booking;

/**
 * Deletes a Booking identified using it's displayed index from trackBeau.
 */
public class DeleteBookingCommand extends Command {

    public static final String COMMAND_WORD = "deletebooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the Booking identified by the index number used in the displayed Booking list.\n"
            + "Parameters: INDEXES (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_DELETE_BOOKING_SUCCESS = "Deleted Booking(s):\n%1$s";

    private final ArrayList<Index> targetIndexes;

    public DeleteBookingCommand(ArrayList<Index> targetIndexes) {
        this.targetIndexes = targetIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Booking> lastShownList = model.getFilteredBookingList();

        ArrayList<Booking> bookingsToDelete = new ArrayList<>();
        for (Index targetIndex : targetIndexes) {
            if (targetIndex.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_BOOKING_DISPLAYED_INDEX);
            }
            bookingsToDelete.add(lastShownList.get(targetIndex.getZeroBased()));
        }

        StringBuilder sb = new StringBuilder();
        for (Booking bookingToDelete : bookingsToDelete) {
            model.deleteBooking(bookingToDelete);
            sb.append(bookingToDelete).append("\n");
        }

        return new CommandResult(String.format(MESSAGE_DELETE_BOOKING_SUCCESS, sb));
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
