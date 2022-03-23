package seedu.trackbeau.logic.parser.booking;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.ArrayList;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.booking.DeleteBookingCommand;
import seedu.trackbeau.logic.parser.Parser;
import seedu.trackbeau.logic.parser.ParserUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteBookingCommandParser implements Parser<DeleteBookingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteBookingCommand parse(String args) throws ParseException {
        try {
            String[] split = args.split(",");
            ArrayList<Index> indexes = new ArrayList<>();
            for (String s : split) {
                indexes.add(ParserUtil.parseIndex(s));
            }
            return new DeleteBookingCommand(indexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteBookingCommand.MESSAGE_USAGE), pe);
        }
    }

}
