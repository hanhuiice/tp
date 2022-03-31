package seedu.trackbeau.model.customer;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

import seedu.trackbeau.commons.util.StringUtil;
import seedu.trackbeau.model.tag.Tag;

/**
 * Tests that a {@code Customer's}'s {@code Data} matches any of the keywords given.
 */
public class CustomerSearchContainsKeywordsPredicate implements Predicate<Customer> {
    public static final int FIND_ATTRIBUTE_COUNT = 11;
    private static final int NON_TAG_ATTRIBUTE_COUNT = 8;
    private final ArrayList<List<String>> keywordsList;
    /**
     * Constructs a {@code Predicate}.
     *
     * @param keywordsList User input keywords to search with.
     */
    public CustomerSearchContainsKeywordsPredicate(ArrayList<List<String>> keywordsList) {
        this.keywordsList = keywordsList;
        assert keywordsList.size() == FIND_ATTRIBUTE_COUNT
                : String.format("Keyword list is wrong size: %s", keywordsList.toString());
    }

    @Override
    public boolean test(Customer customer) {
        String[] find = {"getName", "getPhone", "getEmail", "getAddress", "getSkinType",
            "getHairType", "getBirthdate", "getRegDate", "getStaffs", "getServices", "getAllergies"};
        String searchString = "";

        try {
            for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
                List<String> keywords = keywordsList.get(i);
                if (i < NON_TAG_ATTRIBUTE_COUNT) {
                    searchString = customer.getClass().getDeclaredMethod(find[i]).invoke(customer).toString();
                } else {
                    // Will always return type Set<Tag> from the 3 possible methods in the Customer class.
                    @SuppressWarnings("unchecked")
                    Set<Tag> tagList = (Set<Tag>) customer.getClass().getDeclaredMethod(find[i]).invoke(customer);
                    for (Tag tag : tagList) {
                        searchString = searchString + tag.tagName + " ";
                    }
                }

                if (keywords == null) {
                    continue;
                }

                for (String keyword : keywords) {
                    if (StringUtil.containsWordIgnoreCase(searchString, keyword)) {
                        return true;
                    }
                }
            }
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (!(other instanceof CustomerSearchContainsKeywordsPredicate)) {
            return false;
        }

        for (int i = 0; i < FIND_ATTRIBUTE_COUNT; i++) {
            List<String> thisKeywords = this.keywordsList.get(i);
            List<String> otherKeywords = ((CustomerSearchContainsKeywordsPredicate) other).keywordsList.get(i);
            if ((thisKeywords != null) && (otherKeywords != null)) {
                if (!thisKeywords.equals(otherKeywords)) {
                    return false;
                }
            }
        }
        return true;
    }
}