import java.util.List;
import java.util.ArrayList;

public class Cell {
    public int realValue;
    public List<Integer> possibleValue = List.of(1,2,3,4,5,6,7,8,9);
    public int rowNumber;
    public int columnNumber;
    public int blockNumber;

    public Cell(int realValue) {
        this.realValue = realValue;
        //possibleValue = new ArrayList<>();

        if (realValue != 0) {
            possibleValue = List.of(realValue);
        }

    }
}