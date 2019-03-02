package seedu.address.model.person;

/**
 * Represents semester of a module taken.
 */
public enum Semester {

    Y1S1(1, 1, "Y1S1"),
    Y1S2(1, 2, "Y1S2"),
    Y2S1(2, 1, "Y2S1"),
    Y2S2(2, 2, "Y2S2"),
    Y3S1(3, 1, "Y3S1"),
    Y3S2(3, 2, "Y3S2"),
    Y4S1(4, 1, "Y4S1"),
    Y4S2(4, 2, "Y4S2"),
    Y5S1(5, 1, "Y5S1"),
    Y5S2(5, 2, "Y5S2");

    public static final String MESSAGE_CONSTRAINTS = "Semester is Y{1-5}S{1-2}";
    private final int year;
    private final int semesterOfYear;
    private final String printedSemester;

    Semester(int year, int semesterOfYear, String printedSemester) {
        this.year = year;
        this.semesterOfYear = semesterOfYear;
        this.printedSemester = printedSemester;
    }


    int getYear() {
        return this.year;
    }

    int getSemesterOfYear() {
        return this.semesterOfYear;
    }

    /**
     * Returns if a given string is a valid semester.
     */
    public static boolean isValidSemester(String test) {
        switch (test) {
        case "Y1S1":
        case "Y1S2":
        case "Y2S1":
        case "Y2S2":
        case "Y3S1":
        case "Y3S2":
        case "Y4S1":
        case "Y4S2":
        case "Y5S1":
        case "Y5S2":
            return true;
        default:
            return false;
        }
    }

    @Override
    public String toString() {
        return printedSemester;
    }
}
