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

    public static HashSet<Cell> getCellsAccessibleByACell(Cell pincer2, SudokuBoard board) {
        HashSet<Cell> listOfCells = new HashSet<>();
        CollectionIterator blockIterator;
        CollectionIterator columnIterator;
        CollectionIterator rowIterator;
        rowIterator = board.createIterator(IteratorType.ROW, pincer2.rowNumber);
        columnIterator = board.createIterator(IteratorType.COLUMN, pincer2.columnNumber);
        blockIterator = board.createIterator(IteratorType.BLOCK, pincer2.blockNumber);

        while (rowIterator.hasNext()){
            Cell currentCell = rowIterator.next();
            listOfCells.add(currentCell);
        }

        while (columnIterator.hasNext()){
            Cell currentCell = columnIterator.next();
            listOfCells.add(currentCell);

        }

        while (blockIterator.hasNext()){
            Cell currentCell = blockIterator.next();
            listOfCells.add(currentCell);
        }

        listOfCells.remove(pincer2);
        // Debug
//        for (Cell cell : listOfCells){
//            System.out.println("Cellule : " + cell.rowNumber + ":" + cell.columnNumber + "value: " + cell.realValue + " -> " + cell.possibleValue);
//        }
        return listOfCells;
    }

}
