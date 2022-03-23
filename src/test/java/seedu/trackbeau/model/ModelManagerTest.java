package seedu.trackbeau.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackbeau.model.Model.PREDICATE_SHOW_ALL_CUSTOMERS;
import static seedu.trackbeau.testutil.Assert.assertThrows;
import static seedu.trackbeau.testutil.TypicalCustomers.ALICE;
import static seedu.trackbeau.testutil.TypicalCustomers.BENSON;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.model.customer.SearchContainsKeywordsPredicate;
import seedu.trackbeau.testutil.TrackBeauBuilder;

public class ModelManagerTest {

    private ModelManager modelManager = new ModelManager();

    @Test
    public void constructor() {
        assertEquals(new UserPrefs(), modelManager.getUserPrefs());
        assertEquals(new GuiSettings(), modelManager.getGuiSettings());
        assertEquals(new TrackBeau(), new TrackBeau(modelManager.getTrackBeau()));
    }

    @Test
    public void setUserPrefs_nullUserPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setUserPrefs(null));
    }

    @Test
    public void setUserPrefs_validUserPrefs_copiesUserPrefs() {
        UserPrefs userPrefs = new UserPrefs();
        userPrefs.setTrackBeauFilePath(Paths.get("trackbeau/book/file/path"));
        userPrefs.setGuiSettings(new GuiSettings(1, 2, 3, 4));
        modelManager.setUserPrefs(userPrefs);
        assertEquals(userPrefs, modelManager.getUserPrefs());

        // Modifying userPrefs should not modify modelManager's userPrefs
        UserPrefs oldUserPrefs = new UserPrefs(userPrefs);
        userPrefs.setTrackBeauFilePath(Paths.get("new/trackbeau/book/file/path"));
        assertEquals(oldUserPrefs, modelManager.getUserPrefs());
    }

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setGuiSettings(null));
    }

    @Test
    public void setGuiSettings_validGuiSettings_setsGuiSettings() {
        GuiSettings guiSettings = new GuiSettings(1, 2, 3, 4);
        modelManager.setGuiSettings(guiSettings);
        assertEquals(guiSettings, modelManager.getGuiSettings());
    }

    @Test
    public void setTrackBeauFilePath_nullPath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.setTrackBeauFilePath(null));
    }

    @Test
    public void setTrackBeauFilePath_validPath_setsTrackBeauFilePath() {
        Path path = Paths.get("trackbeau/book/file/path");
        modelManager.setTrackBeauFilePath(path);
        assertEquals(path, modelManager.getTrackBeauFilePath());
    }

    @Test
    public void hasCustomer_nullCustomer_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> modelManager.hasCustomer(null));
    }

    @Test
    public void hasCustomer_customerNotInTrackBeau_returnsFalse() {
        assertFalse(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void hasCustomer_customerInTrackBeau_returnsTrue() {
        modelManager.addCustomer(ALICE);
        assertTrue(modelManager.hasCustomer(ALICE));
    }

    @Test
    public void getFilteredCustomerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> modelManager.getFilteredCustomerList().remove(0));
    }

    @Test
    public void equals() {
        TrackBeau trackBeau = new TrackBeauBuilder().withCustomer(ALICE).withCustomer(BENSON).build();
        TrackBeau differentTrackBeau = new TrackBeau();
        UserPrefs userPrefs = new UserPrefs();

        // same values -> returns true
        modelManager = new ModelManager(trackBeau, userPrefs);
        ModelManager modelManagerCopy = new ModelManager(trackBeau, userPrefs);
        assertTrue(modelManager.equals(modelManagerCopy));

        // same object -> returns true
        assertTrue(modelManager.equals(modelManager));

        // null -> returns false
        assertFalse(modelManager.equals(null));

        // different types -> returns false
        assertFalse(modelManager.equals(5));

        // different trackBeau -> returns false
        assertFalse(modelManager.equals(new ModelManager(differentTrackBeau, userPrefs)));

        // different filteredList -> returns false
        String[] keywords = ALICE.getName().fullName.split("\\s+");
        ArrayList<List<String>> prefixArr = new ArrayList<List<String>>(Collections.nCopies(9, null));
        prefixArr.add(0, Arrays.asList(keywords));
        modelManager.updateFilteredCustomerList(new SearchContainsKeywordsPredicate(prefixArr));
        assertFalse(modelManager.equals(new ModelManager(trackBeau, userPrefs)));

        // resets modelManager to initial state for upcoming tests
        modelManager.updateFilteredCustomerList(PREDICATE_SHOW_ALL_CUSTOMERS);

        // different userPrefs -> returns false
        UserPrefs differentUserPrefs = new UserPrefs();
        differentUserPrefs.setTrackBeauFilePath(Paths.get("differentFilePath"));
        assertFalse(modelManager.equals(new ModelManager(trackBeau, differentUserPrefs)));
    }
}
