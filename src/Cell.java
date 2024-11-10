import java.util.ArrayList;
import java.util.List;

/* Un Cellule possède:

une valeur qui est la valeur actuelle (ou vrai valeur) de la case : realValue
une liste de valeurs qui sont les potentielles valeurs que peut avoir la case : possibleValue
  (elle est de taille 1 et comporte realValue si on a trouvé la vraie valeur de la case)

La Cellule appartient à une ligne, à une colonne et à un bloc*/

public class Cell {
    public int realValue;   //valeur actuelle
    public List<Integer> possibleValue = new ArrayList<>(List.of(1,2,3,4,5,6,7,8,9));    // liste de potentielles valeurs
    public int rowNumber;   // ligne à laquelle appartient la valeur
    public int columnNumber;    // colonne à laquelle appartient la valeur
    public int blockNumber;    // bloc auquel appartient la valeur

    public Cell(int realValue, int rowNumber, int columnNumber) {
        this.realValue = realValue;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.blockNumber = block(rowNumber, columnNumber);

        if (realValue != 0) {
            possibleValue = List.of(realValue);
        }

    }

    private int block(int i, int j) {   // Calcul du block à la ligne i colonne j
        return ( (i/3) * 3 + j/3 ); // Somme des divisions entières par 3 des lignes et colonnes
    }

    public void removeFromPossibleValue(int value) {
        if(!isValid() && (possibleValue.contains(value))) {
            //System.out.println(possibleValue);
            possibleValue.remove(Integer.valueOf(value));
            //System.out.println("On a retire de la cellule "+rowNumber+":"+columnNumber+"  :   " + value);
        }
        verifyNoMorePossibilities();
    }

    public boolean isInSameRow(Cell cell){
        return cell.rowNumber == this.rowNumber;
    }

    public void verifyNoMorePossibilities(){
        if (possibleValue.size()==1) {
            realValue = possibleValue.get(0);
        }
    }

    public boolean isValid() {
        return realValue != 0;
    }

    public boolean isInSameColumn(Cell cell){
        return cell.columnNumber == this.columnNumber;
    }

    public boolean isInSameBloc(Cell cell){
        return cell.blockNumber == this.blockNumber;
    }

    public boolean equals(Cell cell) {
        return cell != null && isInSameRow(cell) && isInSameColumn(cell);
    }


//isItersectionOf renvoit true si la cellule est dans l'intersection entre le bloc numBloc et l'area/type (ligne ou colonne) numType
    //  -intersection d'un bloc et d'une ligne
    //  ou
    //  -intersection d'un bloc et d'une colonne

    public boolean isIntersectionOf(int numBloc, IteratorType type, int numType ) {
        // Pas besoin de regarder l'intersection entre une ligne et une colonne: ce sera une unique cellule
        boolean inSameArea;

        switch (type) {
            case ROW:
                inSameArea = numType == this.rowNumber;
            case COLUMN:
                inSameArea = numType == this.columnNumber;
            default:
                inSameArea = false;
        }
        return (this.blockNumber==numBloc && inSameArea);
    }
}