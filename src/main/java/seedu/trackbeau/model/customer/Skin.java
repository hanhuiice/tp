package seedu.trackbeau.model.customer;

import static java.util.Objects.requireNonNull;
import static seedu.trackbeau.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's skin type in the trackbeau book.
 * Guarantees: immutable; is valid as declared in {@link #isValidSkinType(String)}
 */
public class Skin {

    public static final String MESSAGE_CONSTRAINTS = "SkinTypes can be any values and it should not be blank";

    /*
     * The first character of the trackbeau must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code SkinType}.
     *
     * @param skinType a valid skin type.
     */
    public Skin(String skinType) {
        requireNonNull(skinType);
        checkArgument(isValidSkinType(skinType), MESSAGE_CONSTRAINTS);
        value = skinType;
    }

    /**
     * Returns true if a given string is a valid skin type.
     */
    public static boolean isValidSkinType(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Skin // instanceof handles nulls
                && value.equals(((Skin) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
