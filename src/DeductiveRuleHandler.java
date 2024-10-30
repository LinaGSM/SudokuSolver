public interface DeductiveRuleHandler {
    // La regle applique sa logique sur un tableau et retourne le resulat
    public SudokuBoard processRule(SudokuBoard board);
}
