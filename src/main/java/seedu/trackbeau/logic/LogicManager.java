package seedu.trackbeau.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.trackbeau.commons.core.GuiSettings;
import seedu.trackbeau.commons.core.LogsCenter;
import seedu.trackbeau.logic.commands.Command;
import seedu.trackbeau.logic.commands.CommandResult;
import seedu.trackbeau.logic.commands.exceptions.CommandException;
import seedu.trackbeau.logic.parser.TrackBeauParser;
import seedu.trackbeau.logic.parser.exceptions.ParseException;
import seedu.trackbeau.model.Model;
import seedu.trackbeau.model.ReadOnlyTrackBeau;
import seedu.trackbeau.model.booking.Booking;
import seedu.trackbeau.model.customer.Customer;
import seedu.trackbeau.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final TrackBeauParser trackBeauParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        trackBeauParser = new TrackBeauParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = trackBeauParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveTrackBeau(model.getTrackBeau());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyTrackBeau getTrackBeau() {
        return model.getTrackBeau();
    }

    @Override
    public ObservableList<Customer> getFilteredCustomerList() {
        return model.getFilteredCustomerList();
    }

    @Override
    public ObservableList<Booking> getFilteredBookingList() {
        return model.getFilteredBookingList();
    }

    @Override
    public Path getTrackBeauFilePath() {
        return model.getTrackBeauFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
