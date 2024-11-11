import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int realValue;   //valeur actuelle
    public List<Integer> possibleValue = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));    // liste de potentielles valeurs
    public int rowNumber;   // ligne à laquelle appartient la valeur
    public int columnNumber;    // colonne à laquelle appartient la valeur
    public int blockNumber;    // bloc auquel appartient la valeur

    public Cell(int realValue, int rowNumber, int columnNumber) {
        this.realValue = realValue;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.blockNumber = calculateBlockNumber(rowNumber, columnNumber);

        if (realValue != 0) {
            possibleValue = List.of(realValue);
        }

    }

    private int calculateBlockNumber(int i, int j) {   // Calcul du block à la ligne i colonne j
        return ((i / 3) * 3 + j / 3); // Somme des divisions entières par 3 des lignes et colonnes
    }

    public void removeFromPossibleValue(int value) {
        if (!isValid() && (possibleValue.contains(value))) {
            possibleValue.remove(Integer.valueOf(value));
        }
        verifyNoMorePossibilities();
    }

    public boolean isInSameRow(Cell cell) {
        return cell.rowNumber == this.rowNumber;
    }

    public void verifyNoMorePossibilities() {
        if (possibleValue.size() == 1) {
            realValue = possibleValue.get(0);
        }
    }

    public boolean isValid() {
        return realValue != 0;
    }

    public boolean isInSameColumn(Cell cell) {
        return cell.columnNumber == this.columnNumber;
    }

    public boolean isInSameBloc(Cell cell) {
        return cell.blockNumber == this.blockNumber;
    }

    public boolean equals(Cell cell) {
        return cell != null && isInSameRow(cell) && isInSameColumn(cell);
    }

}