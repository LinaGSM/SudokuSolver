public abstract class DeductionRule implements DeductiveRuleHandler{
    public boolean hasChanged=true;

    private DeductiveRuleHandler next;

    public abstract SudokuBoard processRule(SudokuBoard board);

    public void setNext(DeductiveRuleHandler rule){
        this.next = rule;
    }

    public DeductiveRuleHandler getNext() { return this.next; }

}
