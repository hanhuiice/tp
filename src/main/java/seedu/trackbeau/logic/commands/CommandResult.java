package seedu.trackbeau.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Objects;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    private final String feedbackToUser;

    /** Help information should be shown to the user. */
    private final boolean showHelp;

    /** The application should exit. */
    private final boolean exit;

    /** plot staff chart */
    private final boolean plotStaffChart;

    /** plot service chart */
    private final boolean plotServiceChart;

    /** plot allergy chart */
    private final boolean plotAllergyChart;

    /** plot skin chart */
    private final boolean plotSkinChart;

    /** plot hair chart */
    private final boolean plotHairChart;

    /**
     * Constructs a {@code CommandResult} with the specified fields.
     */
    public CommandResult(String feedbackToUser, boolean showHelp,
                         boolean exit, boolean plotStaffChart,
                         boolean plotServiceChart, boolean plotAllergyChart,
                         boolean plotSkinChart, boolean plotHairChart) {
        this.feedbackToUser = requireNonNull(feedbackToUser);
        this.showHelp = showHelp;
        this.exit = exit;
        this.plotStaffChart = plotStaffChart;
        this.plotServiceChart = plotServiceChart;
        this.plotAllergyChart = plotAllergyChart;
        this.plotSkinChart = plotSkinChart;
        this.plotHairChart = plotHairChart;
    }

    /**
     * Constructs a {@code CommandResult} with the specified {@code feedbackToUser},
     * and other fields set to their default value.
     */
    public CommandResult(String feedbackToUser) {
        this(feedbackToUser, false,
                false, false, false, false,
                false, false);
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }

    public boolean isShowHelp() {
        return showHelp;
    }

    public boolean isExit() {
        return exit;
    }

    public boolean isPlotStaffChart() {
        return plotStaffChart;
    }

    public boolean isPlotServiceChart() {
        return plotServiceChart;
    }

    public boolean isPlotAllergyChart() {
        return plotAllergyChart;
    }

    public boolean isPlotSkinChart() {
        return plotSkinChart;
    }

    public boolean isPlotHairChart() {
        return plotHairChart;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CommandResult)) {
            return false;
        }

        CommandResult otherCommandResult = (CommandResult) other;
        return feedbackToUser.equals(otherCommandResult.feedbackToUser)
                && showHelp == otherCommandResult.showHelp
                && exit == otherCommandResult.exit
                && plotStaffChart == otherCommandResult.plotStaffChart
                && plotServiceChart == otherCommandResult.plotServiceChart
                && plotAllergyChart == otherCommandResult.plotAllergyChart
                && plotSkinChart == otherCommandResult.plotSkinChart
                && plotHairChart == otherCommandResult.plotHairChart;
    }

    @Override
    public int hashCode() {
        return Objects.hash(feedbackToUser, showHelp, exit,
                plotStaffChart, plotServiceChart, plotAllergyChart,
                plotSkinChart, plotHairChart);
    }

}
