public interface DeductiveRuleHandler {
    // La regle applique sa logique sur un tableau et retourne le resulat
    SudokuBoard processRule(SudokuBoard board);

    void setNext(DeductiveRuleHandler rule);

    DeductiveRuleHandler getNext();
}