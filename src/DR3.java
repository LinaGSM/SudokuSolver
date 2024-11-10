import java.util.*;
import java.util.stream.Collectors;

public class DR3 extends DeductionRule{
    //identification de toute les cellules ayant exactement 2 possibilités
    ArrayList<Cell> findCellsWithTwoCandidates(SudokuBoard board) {
        ArrayList<Cell> listOfCellWithTwoCandidates = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            CollectionIterator rowIterator = board.createIterator(IteratorType.ROW, i);
            while (rowIterator.hasNext()) {
                Cell currentCell = rowIterator.next();
                if ((currentCell.realValue == 0) && (currentCell.possibleValue.size() == 2)) {
                    listOfCellWithTwoCandidates.add(currentCell); // Ajoute la cellule
                }
            }
        }
        return listOfCellWithTwoCandidates;
    }

}
