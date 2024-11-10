import java.util.*;
import java.util.stream.Collectors;

public class DR3 extends DeductionRule{
    public SudokuBoard processRule(SudokuBoard board) {
        boolean hasChanged = true;

        // Continue tant qu'il y a des modifications sur le board
        while (hasChanged) {
            hasChanged = false;

            // Mise à jour de la liste des cellules avec exactement 2 candidats
            ArrayList<Cell> listOfCellWithTwoCandidates = new ArrayList<>(findCellsWithTwoCandidates(board));

            // Pour chaque celluleA ayant exactement 2 candidats/possibilités
            for (Cell cellA : listOfCellWithTwoCandidates) {
                // On récupère les candidats X et Y
                int x = cellA.possibleValue.get(0);
                int y = cellA.possibleValue.get(1);

                // Mise à jour de la liste des cellules potentielles B
                ArrayList<Cell> listOfPotentialCellB = new ArrayList<>(findSecondCell(cellA, board));

                // Pour chaque celluleB possible ayant exactement 2 candidats et exactement 1 candidat en commun avec cellA
                for (Cell cellB : listOfPotentialCellB) {
                    if (hasOneCommonCandidate(cellA, cellB)) {

                        // On récupère le candidat en commun et les deux candidats non communs
                        int commonCandidate = getTheCommonCandidate(cellA, cellB);
                        int firstUncommonCandidate = (cellA.possibleValue.get(0) == commonCandidate) ? cellB.possibleValue.get(1) : cellB.possibleValue.get(0);
                        int secondUncommonCandidate = (cellB.possibleValue.get(0) == commonCandidate) ? cellB.possibleValue.get(1) : cellB.possibleValue.get(0);

                        // Mise à jour de la liste des cellules potentielles C
                        ArrayList<Cell> listOfPotentialCellC = new ArrayList<>(findThirdCell(cellA, cellB, board, firstUncommonCandidate, secondUncommonCandidate));

                        // Pour chaque cellule ayant exactement 2 candidats qui sont les candidats non communs entre cellA et cellB
                        for (Cell cellC : listOfPotentialCellC) {
                            if (isXYWingConfiguration(cellA, cellB, cellC)) {
                                int z = getTheCommonCandidate(cellB, cellC);

                                // Applique les modifications et vérifie si le board a changé
                                boolean modification = removeCandidateFromIntersectingPincers(cellB, cellC, z, board);
                                verifyBoard(board);
                                if (modification) {
                                    hasChanged = true;
                                    break; // Quitte la boucle C pour redémarrer
                                }
                            }
                        }

                        // Si une modification a été détectée, on quitte la boucle B pour redémarrer
                        if (hasChanged) {
                            break;
                        }
                    }
                }

                // Si une modification a été détectée, on quitte la boucle A pour redémarrer
                if (hasChanged) {
                    break;
                }
            }
        }

        return board;
    }


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


    // Cherche la deuxieme celllule de la configuration XY-Wing
    HashSet<Cell> findSecondCell(Cell cellA, SudokuBoard board) {
        HashSet<Cell> listOfCells = new HashSet<>(getCellsAccessibleByACell(cellA, board)); // recupere la liste des cellules sur la meme ligne, colone ou block que cellA
        HashSet<Cell> listOfCellsWithTwoCandidates = listOfCells.stream()
                .filter(x -> x.possibleValue.size() == 2)         // filtre la liste pour obtenir les cellules avec exactement 2 candidats
                .collect(Collectors.toCollection(HashSet::new));  // collecte le resultat dans un set/ ensemble
        HashSet<Cell> listOfPotentialCells = listOfCellsWithTwoCandidates.stream()
                .filter(x-> hasOneCommonCandidate(cellA, x))     //  filtre la liste pour obtenir les cellules avec exactement 2 candidats et exactement 1 candidats en commun avec la celluleA
                .collect(Collectors.toCollection(HashSet::new));
        return listOfPotentialCells;
    }


    // Cherche la troixieme cellule de la configuration XY-Wing
    HashSet<Cell> findThirdCell(Cell cellA, Cell cellB, SudokuBoard board, int uncommonCandidate1, int uncommonCandidate2) {
        HashSet<Cell> listOfThirdCells = findSecondCell(cellA, board);
        if (listOfThirdCells.contains(cellB)) {
            listOfThirdCells.remove(cellB); // Retire la cellule B
            // recherche les cellules qui ont exactement pour Candidat uncommonCandidate 1 et uncommonCandidate 1
            for (Cell cell : new HashSet<>(listOfThirdCells)) {
                if (!cell.possibleValue.contains(uncommonCandidate1) && !cell.possibleValue.contains(uncommonCandidate2)) {
                    listOfThirdCells.remove(cell);
                }
            }
            return listOfThirdCells;


        } else {
            return new HashSet<>(); // Retourne un ensemble vide
        }


    }


    // determine si il existe exactement un candidat en commun entre 2 cellules
    boolean hasOneCommonCandidate(Cell cellA, Cell cellB){
        ArrayList<Integer> listOfCommonCandidates = new ArrayList<>(cellA.possibleValue);
        listOfCommonCandidates.retainAll(cellB.possibleValue);
        return (listOfCommonCandidates.size() == 1);
    }

    int getTheCommonCandidate(Cell cell1, Cell cell2){
        ArrayList<Integer> listOfCommonCandidates = new ArrayList<>(cell1.possibleValue);
        listOfCommonCandidates.retainAll(cell2.possibleValue);
        return listOfCommonCandidates.get(0);
    }

    // verifie si 3 cellules respectent la configuration XY-Wing
    boolean isXYWingConfiguration(Cell pivot, Cell pincer1, Cell pincer2) {
        // Vérifie que le pivot a exactement deux candidats
        if (pivot.possibleValue.size() != 2) {
            return false;
        }

        // Identifie les candidats de la cellule pivot
        int X = pivot.possibleValue.get(0);
        int Y = pivot.possibleValue.get(1);

        // Vérifie que les pincettes ont exactement deux candidats
        if (pincer1.possibleValue.size() != 2 || pincer2.possibleValue.size() != 2) {
            return false;
        }

        // Trouve la valeur commune Z entre les pincettes
        ArrayList<Integer> commonCandidates = new ArrayList<>(pincer1.possibleValue);
        commonCandidates.retainAll(pincer2.possibleValue);

        // Si on trouve une seule valeur commune Z
        if (commonCandidates.size() == 1) {
            int Z = commonCandidates.get(0);

            // Vérifie que Z n'est pas dans les candidats du pivot
            if (!pivot.possibleValue.contains(Z)) {
                // Vérifie que pincer1 a X et Z, et pincer2 a Y et Z
                boolean validPincer1 = pincer1.possibleValue.contains(X) && pincer1.possibleValue.contains(Z);
                boolean validPincer2 = pincer2.possibleValue.contains(Y) && pincer2.possibleValue.contains(Z);

                return validPincer1 && validPincer2;
            }
        }

        return false;
    }


    // recupere la liste des cellules qui sont dans l'intersection des pincettes
    ArrayList<Cell> getIntersectingCells(Cell pincer1, Cell pincer2 , SudokuBoard board){
        HashSet<Cell> listOfCellVisibleByPincer1 = new HashSet<>(getCellsAccessibleByACell(pincer1, board));
        HashSet<Cell> listOfCellVisibleByPincer2 = new HashSet<>(getCellsAccessibleByACell(pincer2, board));
        ArrayList<Cell> listOfIntersectingCell = new ArrayList<>(listOfCellVisibleByPincer1);
        listOfIntersectingCell.retainAll(listOfCellVisibleByPincer2);

        return listOfIntersectingCell;
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
        return listOfCells;
    }

    // retire le Candidat z des intersections des pincers
    boolean removeCandidateFromIntersectingPincers(Cell pincer1, Cell pincer2, int value, SudokuBoard board){
        boolean modifiedBoard = false;
        ArrayList<Cell> listOfIntersectingCells = new ArrayList<>(getIntersectingCells(pincer1, pincer2, board));
        for (Cell cell : listOfIntersectingCells) {
            if (cell.possibleValue.size() > 1 && cell.possibleValue.contains(value)) {
                cell.possibleValue.remove(Integer.valueOf(value));
                modifiedBoard = true;
            }
        }
        return modifiedBoard;
    }

    // fonction pour verifier si il existe des single value dans les tests
    void verifyBoard(SudokuBoard board){
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                Cell cell = SudokuBoard.board[i][j];
                if (cell.possibleValue.size() == 1 ) {
                    cell.realValue = cell.possibleValue.get(0);
                    ArrayList<Cell> list = new ArrayList<>(getCellsAccessibleByACell(cell, board));
                    for (Cell elt : list){

                        if (!(elt.equals(cell)) && elt.possibleValue.size() > 1 && elt.possibleValue.contains(cell.realValue)){
                            elt.possibleValue.remove(Integer.valueOf(cell.realValue));
                            for (int k = 0; k < 9; k++) {
                                for (int l = 0; l < 9; l++) {
                                    Cell c = SudokuBoard.board[k][l];
                                    if (c.possibleValue.size() == 1 && c.realValue == 0) {
                                        c.realValue = c.possibleValue.get(0);
                                        verifyBoard(board); // Vérifie et met à jour immédiatement
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
    }

}
