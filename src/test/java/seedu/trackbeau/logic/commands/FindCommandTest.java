package seedu.trackbeau.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.commons.core.Messages.MESSAGE_CUSTOMERS_LISTED_OVERVIEW;
import static seedu.trackbeau.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.trackbeau.testutil.TypicalCustomers.CARL;
import static seedu.trackbeau.testutil.TypicalCustomers.ELLE;
import static seedu.trackbeau.testutil.TypicalCustomers.FIONA;
import static seedu.trackbeau.testutil.TypicalCustomers.getTypicalTrackBeau;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ModelManager;
import seedu.trackbeau.model.UserPrefs;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalTrackBeau(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTrackBeau(), new UserPrefs());

    @Test
    public void equals() {
        ArrayList<List<String>> firstPrefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        ArrayList<List<String>> secondPrefixArr2 = new ArrayList<List<String>>(Collections.nCopies(9, null));
        firstPrefixArr.add(0, Collections.singletonList("first"));
        SearchContainsKeywordsPredicate firstPredicate =
                new SearchContainsKeywordsPredicate(firstPrefixArr);
        secondPrefixArr2.add(0, Collections.singletonList("second"));
        SearchContainsKeywordsPredicate secondPredicate =
                new SearchContainsKeywordsPredicate(secondPrefixArr2);

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different customer -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noCustomerFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 0);
        SearchContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredCustomerList());
    }

    @Test
    public void execute_multipleKeywords_multipleCustomersFound() {
        String expectedMessage = String.format(MESSAGE_CUSTOMERS_LISTED_OVERVIEW, 3);
        SearchContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCustomerList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredCustomerList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private SearchContainsKeywordsPredicate preparePredicate(String userInput) {
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, Arrays.asList(userInput.split("\\s+")));
        return new SearchContainsKeywordsPredicate(prefixArr);
    }
}
