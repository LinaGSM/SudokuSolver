import java.util.ArrayList;
import java.util.List;

public class DR2 extends DeductionRule {
    @Override
    public SudokuBoard processRule(SudokuBoard board) {

        board.iterator.remettreLesCompteursAZero();
        while (board.iterator.hasNext()) {
            Cell cell = board.iterator.next();
            parcourtLigneDR2(cell, board);
            parcourtBlocDR2(cell, board);
            parcourtColonneDR2(cell, board);
        }
        return null;
    }

    // Applique la Rule Locked Candidate en vérifiant la présence des possibilités de cell dans sa ligne
    public void parcourtLigneDR2(Cell cell, SudokuBoard board) {
        CollectionIterator ligne = board.createIterator(IteratorType.ROW, cell.rowNumber);
        CollectionIterator colonne = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
        CollectionIterator block = board.createIterator(IteratorType.BLOCK, cell.blockNumber);

        //Les listes d'élements qu'on va retirer de la ligne/ du bloc selon la règle
        List<Integer> possibleValueARetirerDuBloc = new ArrayList<Integer>();
        List<Integer> possibleValueARetirerDeLaColonne = new ArrayList<Integer>();


        //Parcourt les possible value de notre Cell
        for (int i = 0; i < cell.possibleValue.size(); i++) {
            boolean valueFoundOutOfBlock = false;
            boolean valueFoundInBlock = false;

            ligne.remettreLesCompteursAZero();
            while (ligne.hasNext()) {
                Cell cellLigne = ligne.next();
                if (cellLigne.equals(cell)) {
                    continue; //On Saute la cellule sur laquelle on se base
                }
                ;

                if (cellLigne.possibleValue.contains(cell.possibleValue.get(i))) {
                    if (!cellLigne.isInSameBloc(cell)) { // on saura si on doit enlever la valeur de la ligne ET du bloc
                        valueFoundOutOfBlock = true;
                        break;// Je veux arreter la boucle While
                    }
                    valueFoundInBlock = true;   // on saura si on doit enlever la valeur de la ligne
                }
            }

            if (!valueFoundOutOfBlock) {
                possibleValueARetirerDuBloc.add(cell.possibleValue.get(i));
                if (!valueFoundInBlock) {
                    possibleValueARetirerDeLaColonne.add(cell.possibleValue.get(i));
                }
            }
        }

        // Retire toutes les valeurs récoltées des possibleValues de Cellules concernées
        removeTravesingRow(block, cell, possibleValueARetirerDuBloc);
        removeTravesingRow(colonne, cell, possibleValueARetirerDeLaColonne);
    }

    // Applique la Rule Locked Candidate en vérifiant la présence des possibilités de cell dans sa colonne
    public void parcourtColonneDR2(Cell cell, SudokuBoard board) {
        CollectionIterator ligne = board.createIterator(IteratorType.ROW, cell.rowNumber);
        CollectionIterator colonne = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
        CollectionIterator block = board.createIterator(IteratorType.BLOCK, cell.blockNumber);

        //Les listes d'élements qu'on va retirer de la ligne/ du bloc selon la règle
        List<Integer> possibleValueARetirerDuBloc = new ArrayList<Integer>();
        List<Integer> possibleValueARetirerDeLaLigne = new ArrayList<Integer>();


        //Parcourt les possible value de notre Cell
        for (int i = 0; i < cell.possibleValue.size(); i++) {
            boolean valueFoundOutOfBlock = false;
            boolean valueFoundInBlock = false;

            colonne.remettreLesCompteursAZero();
            while (colonne.hasNext()) {
                Cell cellColonne = colonne.next();
                if (cellColonne.equals(cell)) {
                    continue; //On Saute la cellule sur laquelle on se base
                }
                ;

                if (cellColonne.possibleValue.contains(cell.possibleValue.get(i))) {
                    if (!cellColonne.isInSameBloc(cell)) {
                        valueFoundOutOfBlock = true;
                        break;
                    }
                    valueFoundInBlock = true;
                }
            }

            if (!valueFoundOutOfBlock) {
                possibleValueARetirerDuBloc.add(cell.possibleValue.get(i));
                if (!valueFoundInBlock) {
                    possibleValueARetirerDeLaLigne.add(cell.possibleValue.get(i));
                }
            }
        }

        // Retire toutes les valeurs récoltées des possibleValues de Cellules concernées
        removeTravesingColumn(block, cell, possibleValueARetirerDuBloc);
        removeTravesingColumn(ligne, cell, possibleValueARetirerDeLaLigne);

    }

    // Applique la Rule Locked Candidate en vérifiant la présence des possibilités de cell dans son bloc
    public void parcourtBlocDR2(Cell cell, SudokuBoard board) {
        CollectionIterator ligne = board.createIterator(IteratorType.ROW, cell.rowNumber);
        CollectionIterator colonne = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
        CollectionIterator block = board.createIterator(IteratorType.BLOCK, cell.blockNumber);

        //Les listes d'élements qu'on va retirer de la ligne/ du bloc selon la règle
        List<Integer> possibleValueARetirerDeLaLigne = new ArrayList<Integer>();
        List<Integer> possibleValueARetirerDeLaColonne = new ArrayList<Integer>();


        //Parcourt les possible value de notre Cell
        for (int i = 0; i < cell.possibleValue.size(); i++) {
            boolean valueFoundOutOfLine = false;
            boolean valueFoundInLine = false;

            boolean valueFoundOutOfColumn = false;
            boolean valueFoundInColumn = false;

            block.remettreLesCompteursAZero();
            while (block.hasNext()) {
                Cell cellBlock = block.next();
                if (cellBlock.equals(cell)) {
                    continue; //On Saute la cellule sur laquelle on se base
                }
                ;

                if (cellBlock.possibleValue.contains(cell.possibleValue.get(i))) {

                    if (cellBlock.isInSameRow(cell)) { // Valeur trouvé dans la même Ligne
                        valueFoundInLine = true;
                    } else {                             // Valeur trouvé Hors de la Ligne
                        valueFoundOutOfLine = true;
                    }

                    if (cellBlock.isInSameColumn(cell)) { // Valeur trouvé dans la même Colonne
                        valueFoundInColumn = true;
                    } else {                             // Valeur trouvé Hors de la Colonne
                        valueFoundOutOfColumn = true;
                    }
                }
            }

            if (valueFoundInLine && !valueFoundOutOfLine) {       // On a trouvé la valeur Uniquement dans la Ligne
                possibleValueARetirerDeLaLigne.add(cell.possibleValue.get(i));
            }

            if (valueFoundInColumn && !valueFoundOutOfColumn) {       // On a trouvé la valeur Uniquement dans la Colonne
                possibleValueARetirerDeLaColonne.add(cell.possibleValue.get(i));
            }

        }
        // Retire toutes les valeurs récoltées des possibleValues de Cellules concernées
        removeTravesingBlock(ligne, cell, possibleValueARetirerDeLaLigne);
        removeTravesingBlock(colonne, cell, possibleValueARetirerDeLaColonne);

    }

    //Retire des possibilits des cellules de la collection area: les valeurs de la liste
    //Sauf pour les cellules qui intersectent le bloc
    public void removeTravesingBlock(CollectionIterator area, Cell cell, List<Integer> liste) {
        while (area.hasNext()) {
            Cell cellArea = area.next();
            boolean isInIntersection = (cellArea.blockNumber == cell.blockNumber);
            if (isInIntersection) {
                continue;
            }
            removeListFromCell(liste, cellArea);
        }
    }

    //Retire des possibilits des cellules de la collection area: les valeurs de la liste
    //Sauf pour les cellules qui intersectent la ligne
    public void removeTravesingRow(CollectionIterator area, Cell cell, List<Integer> liste) {
        while (area.hasNext()) {
            Cell cellArea = area.next();
            boolean isInIntersection = (cellArea.rowNumber == cell.rowNumber);
            if (isInIntersection) {
                continue;
            }
            removeListFromCell(liste, cellArea);

        }
    }

    //Retire des possibilits des cellules de la collection area: les valeurs de la liste
    //Sauf pour les cellules qui intersectent la colonne
    public void removeTravesingColumn(CollectionIterator area, Cell cell, List<Integer> liste) {
        while (area.hasNext()) {
            Cell cellArea = area.next();
            boolean isInIntersection = (cellArea.columnNumber == cell.columnNumber);
            if (isInIntersection) {
                continue;
            }
            removeListFromCell(liste, cellArea);

        }
    }

    //Retire les éléments de liste des possibilités de la cellule
    public void removeListFromCell(List<Integer> liste, Cell cell) {
        for (int i = 0; i < liste.size(); i++) {
            cell.removeFromPossibleValue(liste.get(i));
        }
    }

}
