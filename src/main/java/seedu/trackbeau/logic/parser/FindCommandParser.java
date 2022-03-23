package seedu.trackbeau.logic.parser;

import static seedu.trackbeau.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_ALLERGIES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_HAIRTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SERVICES;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_SKINTYPE;
import static seedu.trackbeau.logic.parser.CliSyntax.PREFIX_STAFFS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import seedu.trackbeau.logic.commands.FindCommand;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String userInput) throws ParseException {
        Integer attributeCount = 9;

        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(userInput, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
                PREFIX_SKINTYPE, PREFIX_HAIRTYPE, PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES);
        Prefix[] prefixList = {PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS,
            PREFIX_SKINTYPE, PREFIX_HAIRTYPE, PREFIX_STAFFS, PREFIX_SERVICES, PREFIX_ALLERGIES};

        if (userInput.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(attributeCount, null));

        for (int i = 0; i < attributeCount; i++) {
            if (argMultimap.getValue(prefixList[i]).isPresent() && argMultimap.getPreamble().isEmpty()) {
                prefixArr.add(i,
                    Arrays.asList(ParserUtil
                        .parseName(argMultimap.getValue(prefixList[i]).get()).toString().split(" ")));
            }
        }

        return new FindCommand(new SearchContainsKeywordsPredicate(prefixArr));

    }
}
