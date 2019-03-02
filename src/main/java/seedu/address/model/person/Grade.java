package seedu.address.model.person;

/**
 * Represents grade of a module taken.
 */
public enum Grade {

    A_Plus(5, true, true, "A+"),
    A(5, true, true, "A"),
    A_Minus(4.5, true, true, "A-"),
    B_Plus(4, true, true, "B+"),
    B(3.5, true, true, "B"),
    B_Minus(3, true, true, "B-"),
    C_Plus(2.5, true, true, "C+"),
    C(2, true, true, "C"),
    D_Plus(1.5, true, true, "D+"),
    D(1, true, true, "D"),
    F(0, false, true, "F"),
    CS(0, true, false, "CS"),
    CU(0, false, false, "CU"),
    EXE(0, true, false, "EXE"),
    IC(0, false, false, "IC"),
    S(0, true, false, "S"),
    U(0, false, false, "U"),
    W(0, false, false, "W");

    public static final String MESSAGE_CONSTRAINTS = "Grade must be from A+ to F. or S/U";
    private final double gradePoint;
    private final boolean isPass;
    private final boolean isCounted;
    private final String printedGrade;

    Grade(double gradePoint, boolean isPass, boolean isCounted, String printedGrade) {
        this.gradePoint = gradePoint;
        this.isPass = isPass;
        this.isCounted = isCounted;
        this.printedGrade = printedGrade;
    }

    double getGradePoint() {
        return this.gradePoint;
    }

    boolean isPassingGrade() {
        return this.isPass;
    }

    boolean isCountedInCap() {
        return this.isCounted;
    }

    Grade getGrade(String grade) {
        switch (grade) {
        case "A+":
            return A_Plus;
        case "A":
            return A;
        case "A-":
            return A_Minus;
        case "B+":
            return B_Plus;
        case "B":
            return B;
        case "B-":
            return B_Minus;
        case "C+":
            return C_Plus;
        case "C":
            return C;
        case "D+":
            return D_Plus;
        case "D":
            return D;
        case "F":
            return F;
        case "CS":
            return CS;
        case "CU":
            return CU;
        case "W":
            return W;
        case "IC":
            return IC;
        case "EXE":
        default:
            return EXE;
        }
    }

    /**
     * Returns if a given string is a valid grade.
     */
    public static boolean isValidGrade(String test) {
        switch (test) {
        case "A+":
        case "A":
        case "A-":
        case "B+":
        case "B":
        case "B-":
        case "C+":
        case "C":
        case "D+":
        case "D":
        case "F":
        case "CS":
        case "CU":
        case "W":
        case "IC":
        case "EXE":
            return true;
        default:
            return false;
        }
    }

    @Override
    public String toString() {
        return this.printedGrade;
    }
}
