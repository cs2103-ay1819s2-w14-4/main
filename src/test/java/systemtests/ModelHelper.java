package systemtests;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.model.Model;
import seedu.address.model.moduletaken.ModuleTaken;

/**
 * Contains helper methods to set up {@code Model} for testing.
 */
public class ModelHelper {
    private static final Predicate<ModuleTaken> PREDICATE_MATCHING_NO_MODULES_TAKEN = unused -> false;

    /**
     * Updates {@code model}'s filtered list to display only {@code toDisplay}.
     */
    public static void setFilteredList(Model model, List<ModuleTaken> toDisplay) {
        Optional<Predicate<ModuleTaken>> predicate =
                toDisplay.stream().map(ModelHelper::getPredicateMatching).reduce(Predicate::or);
        model.updateFilteredModulesTakenList(predicate.orElse(PREDICATE_MATCHING_NO_MODULES_TAKEN));
    }

    /**
     * @see ModelHelper#setFilteredList(Model, List)
     */
    public static void setFilteredList(Model model, ModuleTaken... toDisplay) {
        setFilteredList(model, Arrays.asList(toDisplay));
    }

    /**
     * Returns a predicate that evaluates to true if this {@code ModuleTaken} equals to {@code other}.
     */
    private static Predicate<ModuleTaken> getPredicateMatching(ModuleTaken other) {
        return person -> person.equals(other);
    }
}
