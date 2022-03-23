package seedu.trackbeau.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.trackbeau.commons.core.index.Index;
import seedu.trackbeau.commons.util.StringUtil;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.customer.Address;
import seedu.trackbeau.model.customer.Email;
import seedu.trackbeau.model.customer.HairType;
import seedu.trackbeau.model.customer.Name;
import seedu.trackbeau.model.customer.Phone;
import seedu.trackbeau.model.customer.SkinType;
import seedu.trackbeau.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String skinType } into an {@code SkinType}.
     * Leading and trailing whitespaces will be trimmed.
     * Skin type input must fit existing categories.
     * @throws ParseException if the given {@code SkinType} is invalid.
     */
    public static SkinType parseSkinType(String skinType) throws ParseException {
        requireNonNull(skinType);
        String trimmedSkinType = skinType.trim();
        if (!SkinType.isValidSkinType(trimmedSkinType)) {
            throw new ParseException(SkinType.MESSAGE_CONSTRAINTS);
        }
        return new SkinType(trimmedSkinType);
    }
    /**
     * Parses a {@code String hairType} into an {@code HairType}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code HairType} is invalid.
     */
    public static HairType parseHairType(String hairType) throws ParseException {
        requireNonNull(hairType);
        String trimmedHairType = hairType.trim();
        if (!HairType.isValidHairType(trimmedHairType)) {
            throw new ParseException(HairType.MESSAGE_CONSTRAINTS);
        }
        return new HairType(trimmedHairType);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String targetIndex} into an {@code targetIndex}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code targetIndex} is invalid.
     */
    public static Integer parseCustomerID(String targetIndex) throws ParseException {
        requireNonNull(targetIndex);
        String trimmedIndex = targetIndex.trim();
        Integer targetIndexInt;
        try {
            targetIndexInt = Integer.parseInt(trimmedIndex);
        } catch (NumberFormatException e) {
            throw e;
        }
        return targetIndexInt;
    }

    /**
     * Parses a {@code String startTime} into an {@code startTime}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startTime} is invalid.
     */
    public static LocalDateTime parseStartTime(String startTime) throws DateTimeException {
        requireNonNull(startTime);
        String trimmedAddress = startTime.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH");
        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(trimmedAddress, formatter);
        } catch (DateTimeException e) {
            throw e;
        }

        return dateTime;
    }
}
