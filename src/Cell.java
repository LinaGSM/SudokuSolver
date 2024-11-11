import java.util.ArrayList;
import java.util.List;

public class Cell {
    public int realValue;   //valeur actuelle de la case
    public List<Integer> possibleValue = new ArrayList<>(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));    // liste de potentielles valeurs
    public int rowNumber;   // ligne à laquelle appartient la valeur
    public int columnNumber;    // colonne à laquelle appartient la valeur
    public int blockNumber;    // bloc auquel appartient la valeur

    //Crée la Cell à partir de sa vrai valeur dans le tableau, de sa ligne et de sa colonne
    public Cell(int realValue, int rowNumber, int columnNumber) {
        this.realValue = realValue;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.blockNumber = calculateBlockNumber(rowNumber, columnNumber);

        if (realValue != 0) {
            possibleValue = List.of(realValue);
        }

    }

    //Calcul le numéro de bloc de la case à partir de sa psition ligne/colonne
    private int calculateBlockNumber(int i, int j) {
        return ((i / 3) * 3 + j / 3);
    }

    //Retire la valeur value des possibilités de la case
    public void removeFromPossibleValue(int value) {
        if (!isValid() && (possibleValue.contains(value))) {
            possibleValue.remove(Integer.valueOf(value));
        }
        verifyNoMorePossibilities();
    }

    //Vérifie si la Cell est dans la même ligne/colonne ou le même bloc qu'une autre

    public boolean isInSameRow(Cell cell) {
        return cell.rowNumber == this.rowNumber;
    }

    public boolean isInSameColumn(Cell cell) {
        return cell.columnNumber == this.columnNumber;
    }

    public boolean isInSameBloc(Cell cell) {
        return cell.blockNumber == this.blockNumber;
    }

    //Assigne la valeur possible restant s'il ne reste qu'une possibilité
    public void verifyNoMorePossibilities() {
        if (possibleValue.size() == 1) {
            realValue = possibleValue.get(0);
        }
    }

    //Vérifie si la case a une valeur (pas vide)
    public boolean isValid() {
        return realValue != 0;
    }

    //Vérifie si deux cases sont une seule et même
    public boolean equals(Cell cell) {
        return cell != null && isInSameRow(cell) && isInSameColumn(cell);
    }

}