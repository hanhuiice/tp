package seedu.trackbeau.model.customer;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.testutil.CustomerBuilder;

public class SearchContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ArrayList<List<String>> firstPrefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        ArrayList<List<String>> secondPrefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        firstPrefixArr.add(0, firstPredicateKeywordList);
        SearchContainsKeywordsPredicate firstPredicate = new SearchContainsKeywordsPredicate(firstPrefixArr);
        secondPrefixArr.add(0, secondPredicateKeywordList);
        SearchContainsKeywordsPredicate secondPredicate = new SearchContainsKeywordsPredicate(secondPrefixArr);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        SearchContainsKeywordsPredicate firstPredicateCopy = new SearchContainsKeywordsPredicate(firstPrefixArr);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different customer -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nameContainsKeywords_returnsTrue() {
        // One keyword
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, Collections.singletonList("Alice"));
        SearchContainsKeywordsPredicate predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Multiple keywords
        prefixArr.add(0, Arrays.asList("Alice", "Bob"));
        predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Only one matching keyword
        prefixArr.add(0, Arrays.asList("Bob", "Carol"));
        predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Carol").build()));

        // Mixed-case keywords
        prefixArr.add(0, Arrays.asList("aLIce", "bOB"));
        predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertTrue(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainKeywords_returnsFalse() {
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));

        // Zero keywords
        SearchContainsKeywordsPredicate predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").build()));

        // Non-matching keyword
        prefixArr.add(0, Arrays.asList("Carol"));
        predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and trackbeau, but does not match name
        prefixArr.add(0, Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicate = new SearchContainsKeywordsPredicate(prefixArr);
        assertFalse(predicate.test(new CustomerBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").build()));
    }
}
