import java.util.List;

/* Un Cellule possède:

une valeur qui est la valeur actuelle (ou vrai valeur) de la case : realValue
une liste de valeurs qui sont les potentielles valeurs que peut avoir la case : possibleValue
  (elle est de taille 1 et comporte realValue si on a trouvé la vraie valeur de la case)

La Cellule appartient à une ligne, à une colonne et à un bloc*/

public class Cell {
    public int realValue;   //valeur actuelle
    public List<Integer> possibleValue = List.of(1,2,3,4,5,6,7,8,9);    // liste de potentielles valeurs
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
}