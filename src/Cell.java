import java.util.*;

public class Cell {
    public int realValue;
    public List<Integer> possibleValue = List.of(1,2,3,4,5,6,7,8,9);
    public Integer block;               //  à initialiser

    public Cell(int realValue,Integer block) {
        this.realValue = realValue;
        possibleValue = new ArrayList<>();
        if (realValue != 0) {
            possibleValue = List.of(realValue);
        }
        this.block = block;
    }
}
