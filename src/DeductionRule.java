public abstract class DeductionRule implements DeductiveRuleHandler{
    private DeductiveRuleHandler next;

    public abstract SudokuBoard processRule(SudokuBoard board);

    public void setNext(DeductiveRuleHandler rule){
        this.next = rule;
    }

    public DeductiveRuleHandler getNext() { return this.next; }

}
