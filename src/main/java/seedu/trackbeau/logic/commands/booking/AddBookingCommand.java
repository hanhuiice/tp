package seedu.trackbeau.logic.commands.booking;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_CUSTOMER;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STARTTIME;

import java.time.LocalDateTime;
import java.util.List;

import seedu.trackbeau.commons.core.Messages;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;

/**
 * Adds a booking to trackBeau.
 */
public class AddBookingCommand extends Command {

    public static final String COMMAND_WORD = "addbooking";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Booking to TrackBeau. "
            + "Parameters: "
            + PREFIX_CUSTOMER + "NAME "
            + PREFIX_STARTTIME + "NAME "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CUSTOMER + "1 "
            + PREFIX_STARTTIME + "10-10-2022 10 ";

    public static final String MESSAGE_SUCCESS = "New Booking added: \n%1$s";

    private final LocalDateTime startTime;
    private final Integer customerID;

    /**
     * Creates an AddBookingCommand to add the specified booking
     */
    public AddBookingCommand(Integer customerID, LocalDateTime startTime) {
        requireNonNull(customerID);
        requireNonNull(startTime);
        this.customerID = customerID;
        this.startTime = startTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Customer> lastShownList = model.getFilteredCustomerList();
        if (customerID >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CUSTOMER_DISPLAYED_INDEX);
        }
        Customer customer = lastShownList.get(customerID);
        Booking booking = new Booking(customer, startTime);
        model.addBooking(booking);
        return new CommandResult(String.format(MESSAGE_SUCCESS, booking));
    }

    @Override
    public boolean equals(Object other) {
        return other == this;
    }
}
