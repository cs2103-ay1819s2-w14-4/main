package seedu.address.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalModuleTaken.CS1010S;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS1010;
import static seedu.address.testutil.TypicalModuleTaken.DEFAULT_MODULE_CS2103T;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.address.testutil.GradTrakBuilder;

public class VersionedGradTrakTest {

    private final ReadOnlyGradTrak gradTrakWithCS2103T = new GradTrakBuilder()
            .withModuleTaken(DEFAULT_MODULE_CS2103T).build();
    private final ReadOnlyGradTrak gradTrakWtihCS1010 = new GradTrakBuilder()
            .withModuleTaken(DEFAULT_MODULE_CS1010).build();
    private final ReadOnlyGradTrak gradTrakWithCS1010S = new GradTrakBuilder()
            .withModuleTaken(CS1010S).build();
    private final ReadOnlyGradTrak emptyGradTrak = new GradTrakBuilder().build();

    @Test
    public void commit_singleAddressBook_noStatesRemovedCurrentStateSaved() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(emptyGradTrak);

        versionedGradTrak.commit();
        assertGradTrakStatus(versionedGradTrak,
                Collections.singletonList(emptyGradTrak),
                emptyGradTrak,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleAddressBookPointerAtEndOfStateList_noStatesRemovedCurrentStateSaved() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);

        versionedGradTrak.commit();
        assertGradTrakStatus(versionedGradTrak,
                Arrays.asList(emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010),
                gradTrakWtihCS1010,
                Collections.emptyList());
    }

    @Test
    public void commit_multipleAddressBookPointerNotAtEndOfStateList_statesAfterPointerRemovedCurrentStateSaved() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 2);

        versionedGradTrak.commit();
        assertGradTrakStatus(versionedGradTrak,
                Collections.singletonList(emptyGradTrak),
                emptyGradTrak,
                Collections.emptyList());
    }

    @Test
    public void canUndo_multipleAddressBookPointerAtEndOfStateList_returnsTrue() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);

        assertTrue(versionedGradTrak.canUndo());
    }

    @Test
    public void canUndo_multipleAddressBookPointerAtStartOfStateList_returnsTrue() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 1);

        assertTrue(versionedGradTrak.canUndo());
    }

    @Test
    public void canUndo_singleAddressBook_returnsFalse() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(emptyGradTrak);

        assertFalse(versionedGradTrak.canUndo());
    }

    @Test
    public void canUndo_multipleAddressBookPointerAtStartOfStateList_returnsFalse() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 2);

        assertFalse(versionedGradTrak.canUndo());
    }

    @Test
    public void canRedo_multipleAddressBookPointerNotAtEndOfStateList_returnsTrue() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 1);

        assertTrue(versionedGradTrak.canRedo());
    }

    @Test
    public void canRedo_multipleAddressBookPointerAtStartOfStateList_returnsTrue() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 2);

        assertTrue(versionedGradTrak.canRedo());
    }

    @Test
    public void canRedo_singleAddressBook_returnsFalse() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(emptyGradTrak);

        assertFalse(versionedGradTrak.canRedo());
    }

    @Test
    public void canRedo_multipleAddressBookPointerAtEndOfStateList_returnsFalse() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);

        assertFalse(versionedGradTrak.canRedo());
    }

    @Test
    public void undo_multipleAddressBookPointerAtEndOfStateList_success() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);

        versionedGradTrak.undo();
        assertGradTrakStatus(versionedGradTrak,
                Collections.singletonList(emptyGradTrak),
                gradTrakWithCS2103T,
                Collections.singletonList(gradTrakWtihCS1010));
    }

    @Test
    public void undo_multipleAddressBookPointerNotAtStartOfStateList_success() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 1);

        versionedGradTrak.undo();
        assertGradTrakStatus(versionedGradTrak,
                Collections.emptyList(),
                emptyGradTrak,
                Arrays.asList(gradTrakWithCS2103T, gradTrakWtihCS1010));
    }

    @Test
    public void undo_singleAddressBook_throwsNoUndoableStateException() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(emptyGradTrak);

        assertThrows(VersionedGradTrak.NoUndoableStateException.class, versionedGradTrak::undo);
    }

    @Test
    public void undo_multipleAddressBookPointerAtStartOfStateList_throwsNoUndoableStateException() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 2);

        assertThrows(VersionedGradTrak.NoUndoableStateException.class, versionedGradTrak::undo);
    }

    @Test
    public void redo_multipleAddressBookPointerNotAtEndOfStateList_success() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 1);

        versionedGradTrak.redo();
        assertGradTrakStatus(versionedGradTrak,
                Arrays.asList(emptyGradTrak, gradTrakWithCS2103T),
                gradTrakWtihCS1010,
                Collections.emptyList());
    }

    @Test
    public void redo_multipleAddressBookPointerAtStartOfStateList_success() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 2);

        versionedGradTrak.redo();
        assertGradTrakStatus(versionedGradTrak,
                Collections.singletonList(emptyGradTrak),
                gradTrakWithCS2103T,
                Collections.singletonList(gradTrakWtihCS1010));
    }

    @Test
    public void redo_singleAddressBook_throwsNoRedoableStateException() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(emptyGradTrak);

        assertThrows(VersionedGradTrak.NoRedoableStateException.class, versionedGradTrak::redo);
    }

    @Test
    public void redo_multipleAddressBookPointerAtEndOfStateList_throwsNoRedoableStateException() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(
                emptyGradTrak, gradTrakWithCS2103T, gradTrakWtihCS1010);

        assertThrows(VersionedGradTrak.NoRedoableStateException.class, versionedGradTrak::redo);
    }

    @Test
    public void equals() {
        VersionedGradTrak versionedGradTrak = prepareGradTrakList(gradTrakWithCS2103T, gradTrakWtihCS1010);

        // same values -> returns true
        VersionedGradTrak copy = prepareGradTrakList(gradTrakWithCS2103T, gradTrakWtihCS1010);
        assertTrue(versionedGradTrak.equals(copy));

        // same object -> returns true
        assertTrue(versionedGradTrak.equals(versionedGradTrak
        ));

        // null -> returns false
        assertFalse(versionedGradTrak.equals(null));

        // different types -> returns false
        assertFalse(versionedGradTrak.equals(1));

        // different state list -> returns false
        VersionedGradTrak differentAddressBookList = prepareGradTrakList(gradTrakWtihCS1010, gradTrakWithCS1010S);
        assertFalse(versionedGradTrak.equals(differentAddressBookList));

        // different current pointer index -> returns false
        VersionedGradTrak differentCurrentStatePointer = prepareGradTrakList(
                gradTrakWithCS2103T, gradTrakWtihCS1010);
        shiftCurrentStatePointerLeftwards(versionedGradTrak, 1);
        assertFalse(versionedGradTrak.equals(differentCurrentStatePointer));
    }

    /**
     * Asserts that {@code versionedGradTrak
     *} is currently pointing at {@code expectedCurrentState},
     * states before {@code versionedGradTrak
     *#currentStatePointer} is equal to {@code expectedStatesBeforePointer},
     * and states after {@code versionedGradTrak
     *#currentStatePointer} is equal to {@code expectedStatesAfterPointer}.
     */
    private void assertGradTrakStatus(VersionedGradTrak versionedGradTrak,
                                      List<ReadOnlyGradTrak> expectedStatesBeforePointer,
                                      ReadOnlyGradTrak expectedCurrentState,
                                      List<ReadOnlyGradTrak> expectedStatesAfterPointer) {
        // check state currently pointing at is correct
        assertEquals(new GradTrak(versionedGradTrak), expectedCurrentState);

        // shift pointer to start of state list
        while (versionedGradTrak.canUndo()) {
            versionedGradTrak.undo();
        }

        // check states before pointer are correct
        for (ReadOnlyGradTrak expectedAddressBook : expectedStatesBeforePointer) {
            assertEquals(expectedAddressBook, new GradTrak(versionedGradTrak));
            versionedGradTrak.redo();
        }

        // check states after pointer are correct
        for (ReadOnlyGradTrak expectedAddressBook : expectedStatesAfterPointer) {
            versionedGradTrak.redo();
            assertEquals(expectedAddressBook, new GradTrak(versionedGradTrak));
        }

        // check that there are no more states after pointer
        assertFalse(versionedGradTrak.canRedo());

        // revert pointer to original position
        expectedStatesAfterPointer.forEach(unused -> versionedGradTrak.undo());
    }

    /**
     * Creates and returns a {@code VersionedGradTrak} with the {@code gradTrakStates} added into it, and the
     * {@code VersionedGradTrak#currentStatePointer} at the end of list.
     */
    private VersionedGradTrak prepareGradTrakList(ReadOnlyGradTrak... gradTrakStates) {
        assertFalse(gradTrakStates.length == 0);

        VersionedGradTrak versionedGradTrak = new VersionedGradTrak(gradTrakStates[0]);
        for (int i = 1; i < gradTrakStates.length; i++) {
            versionedGradTrak.resetData(gradTrakStates[i]);
            versionedGradTrak.commit();
        }

        return versionedGradTrak;
    }

    /**
     * Shifts the {@code versionedGradTrak
     *#currentStatePointer} by {@code count} to the left of its list.
     */
    private void shiftCurrentStatePointerLeftwards(VersionedGradTrak versionedGradTrak, int count) {
        for (int i = 0; i < count; i++) {
            versionedGradTrak.undo();
        }
    }
}
