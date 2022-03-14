package seedu.trackbeau.testutil;

import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_ALLERGY_NICKEL;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_HAIR_TYPE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_HAIR_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SERVICE_CHEMICAL_PEEL;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SKIN_TYPE_AMY;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_SKIN_TYPE_BOB;
import static seedu.trackbeau.logic.commands.CommandTestUtil.VALID_STAFF_JANE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.trackbeau.model.TrackBeau;
import seedu.trackbeau.model.customer.Customer;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Customer ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253")
            .withStaffs("John").build();
    public static final Customer BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withStaffs("Jason").withServices("Acne treatment").build();
    public static final Customer CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withEmail("heinz@example.com").withAddress("wall street").build();
    public static final Customer DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withEmail("cornelia@example.com").withAddress("10th street").withAllergies("Cocoa Butter").build();
    public static final Customer ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withEmail("werner@example.com").withAddress("michegan ave").build();
    public static final Customer FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withEmail("lydia@example.com").withAddress("little tokyo").build();
    public static final Customer GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withEmail("anna@example.com").withAddress("4th street").build();

    // Manually added
    public static final Customer HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").build();
    public static final Customer IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Customer AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withSkinType(VALID_SKIN_TYPE_AMY)
            .withHairType(VALID_HAIR_TYPE_AMY)
            .withStaffs(VALID_STAFF_JANE)
            .withServices(VALID_SERVICE_CHEMICAL_PEEL)
            .withAllergies(VALID_ALLERGY_NICKEL)
            .build();

    public static final Customer BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAddress(VALID_ADDRESS_BOB)
            .withSkinType(VALID_SKIN_TYPE_BOB)
            .withHairType(VALID_HAIR_TYPE_BOB)
            .withStaffs(VALID_STAFF_JANE)
            .withServices(VALID_SERVICE_CHEMICAL_PEEL)
            .withAllergies(VALID_ALLERGY_NICKEL)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code TrackBeau} with all the typical persons.
     */
    public static TrackBeau getTypicalAddressBook() {
        TrackBeau ab = new TrackBeau();
        for (Customer customer : getTypicalPersons()) {
            ab.addCustomer(customer);
        }
        return ab;
    }

    public static List<Customer> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}