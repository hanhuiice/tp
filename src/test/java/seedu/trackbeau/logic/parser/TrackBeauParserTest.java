package seedu.trackbeau.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalIndexes.INDEX_FIRST_CUSTOMER;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.logic.commands.AddCommand;
import seedu.trackbeau.logic.commands.ClearCommand;
import seedu.trackbeau.logic.commands.DeleteCommand;
import seedu.trackbeau.logic.commands.ExitCommand;
import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.logic.commands.HelpCommand;
import seedu.trackbeau.logic.commands.ListCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;
import seedu.trackbeau.testutil.CustomerBuilder;
import seedu.trackbeau.testutil.CustomerUtil;

public class TrackBeauParserTest {

    private final TrackBeauParser parser = new TrackBeauParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_CUSTOMER.getOneBased());
        ArrayList<Index> firstCustomer = new ArrayList<>() {
            {
                add(INDEX_FIRST_CUSTOMER);
            }
        };
        assertEquals(new DeleteCommand(firstCustomer), command);
    }

    /*ToDo uncomment when edit command is fixed
    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + CustomerUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }
    */

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, keywords);
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " /n" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new SearchContainsKeywordsPredicate(prefixArr)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
