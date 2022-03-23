package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.trackbeau.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // Find name with no leading and trailing whitespaces
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, Arrays.asList(new String[]{"alex", "yeoh"}));
        prefixArr.add(1, Arrays.asList(new String[]{"87438807"}));
        prefixArr.add(2, Arrays.asList(new String[]{"alex@example.com"}));
        prefixArr.add(3, Arrays.asList(new String[]{"geylang"}));
        prefixArr.add(4, Arrays.asList(new String[]{"oily"}));
        prefixArr.add(5, Arrays.asList(new String[]{"dry"}));
        prefixArr.add(6, Arrays.asList(new String[]{"jason"}));
        prefixArr.add(7, Arrays.asList(new String[]{"acne"}));
        prefixArr.add(8, Arrays.asList(new String[]{"nickel"}));
        FindCommand expectedFindNameCommand =
                new FindCommand(new SearchContainsKeywordsPredicate(prefixArr));
        assertParseSuccess(parser, "name n/alex yeoh p/87438807 e/alex@example.com"
                + " a/geylang s/oily h/dry stp/jason sep/acne al/nickel", expectedFindNameCommand);

        // Find name with multiple whitespaces between keywords
        assertParseSuccess(parser, "name \n n/alex \n \t yeoh p/87438807 e/alex@example.com\" +"
                + " a/geylang s/oily h/dry stp/jason sep/acne al/nickel\t", expectedFindNameCommand);

    }

}
