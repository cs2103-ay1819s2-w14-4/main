package seedu.address.model.moduletaken;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.moduletaken.exceptions.DuplicateModuleTakenException;
import seedu.address.model.moduletaken.exceptions.ModuleTakenNotFoundException;

/**
 * A list of persons that enforces uniqueness between its elements and does not allow nulls.
 * A moduleTaken is considered unique by comparing using {@code ModuleTaken#isSameModuleTaken(ModuleTaken)}.
 * As such, adding and updating of persons uses ModuleTaken#isSameModuleTaken(ModuleTaken) for equality so as to ensure
 * that the moduleTaken being added or updated is unique in terms of identity in the UniqueModuleTakenList.
 * However, the removal of a moduleTaken uses ModuleTaken#equals(Object) so
 * as to ensure that the moduleTaken with exactly the same fields will be removed.
 *
 * Supports a minimal set of list operations.
 *
 * @see ModuleTaken#isSameModuleTaken(ModuleTaken)
 */
public class UniqueModuleTakenList implements Iterable<ModuleTaken> {

    private final ObservableList<ModuleTaken> internalList = FXCollections.observableArrayList();
    private final ObservableList<ModuleTaken> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Returns true if the list contains an equivalent moduleTaken as the given argument.
     */
    public boolean contains(ModuleTaken toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::isSameModuleTaken);
    }

    /**
     * Adds a moduleTaken to the list.
     * The moduleTaken must not already exist in the list.
     */
    public void add(ModuleTaken toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateModuleTakenException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the moduleTaken {@code target} in the list with {@code editedModuleTaken}.
     * {@code target} must exist in the list.
     * The moduleTaken identity of {@code editedModuleTaken} must not be the same as
     * another existing moduleTaken in the list.
     */
    public void setModuleTaken(ModuleTaken target, ModuleTaken editedModuleTaken) {
        requireAllNonNull(target, editedModuleTaken);

        int index = internalList.indexOf(target);
        if (index == -1) {
            throw new ModuleTakenNotFoundException();
        }

        if (!target.isSameModuleTaken(editedModuleTaken) && contains(editedModuleTaken)) {
            throw new DuplicateModuleTakenException();
        }

        internalList.set(index, editedModuleTaken);
    }

    /**
     * Removes the equivalent moduleTaken from the list.
     * The moduleTaken must exist in the list.
     */
    public void remove(ModuleTaken toRemove) {
        requireNonNull(toRemove);
        if (!internalList.remove(toRemove)) {
            throw new ModuleTakenNotFoundException();
        }
    }

    public void setModulesTaken(UniqueModuleTakenList replacement) {
        requireNonNull(replacement);
        internalList.setAll(replacement.internalList);
    }

    /**
     * Replaces the contents of this list with {@code moduleTakens}.
     * {@code moduleTakens} must not contain duplicate moduleTakens.
     */
    public void setModulesTaken(List<ModuleTaken> moduleTakens) {
        requireAllNonNull(moduleTakens);
        if (!modulesTakenAreUnique(moduleTakens)) {
            throw new DuplicateModuleTakenException();
        }

        internalList.setAll(moduleTakens);
    }

    /**
     * Returns the backing list as an unmodifiable {@code ObservableList}.
     */
    public ObservableList<ModuleTaken> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    @Override
    public Iterator<ModuleTaken> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueModuleTakenList // instanceof handles nulls
                        && internalList.equals(((UniqueModuleTakenList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    /**
     * Returns true if {@code moduleTakens} contains only unique moduleTakens.
     */
    private boolean modulesTakenAreUnique(List<ModuleTaken> moduleTakens) {
        for (int i = 0; i < moduleTakens.size() - 1; i++) {
            for (int j = i + 1; j < moduleTakens.size(); j++) {
                if (moduleTakens.get(i).isSameModuleTaken(moduleTakens.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }
}
