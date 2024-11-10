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
            System.out.println("Debug function \"findThirdCell\", line 62:");
            System.out.println("\tCellB n'appartient pas à la liste des deuxieme cellules");
            return new HashSet<>(); // Retourne un ensemble vide au lieu de null
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
        // Debug
//        for (Cell cell : listOfCells){
//            System.out.println("Cellule : " + cell.rowNumber + ":" + cell.columnNumber + "value: " + cell.realValue + " -> " + cell.possibleValue);
//        }
        return listOfCells;
    }

    // retire le Candidat z des intersections des pincers
    boolean removeCandidateFromIntersectingPincers(Cell pincer1, Cell pincer2, int value, SudokuBoard board){
        boolean modifiedBoard = false;
        ArrayList<Cell> listOfIntersectingCells = new ArrayList<>(getIntersectingCells(pincer1, pincer2, board));
        for (Cell cell : listOfIntersectingCells) {
            if (cell.possibleValue.size() > 1 && cell.possibleValue.contains(value)) {
                System.out.println("Retrait du candidat " + value + " de la cellule : " + cell.rowNumber + ":" + cell.columnNumber);
                cell.possibleValue.remove(Integer.valueOf(value));
                modifiedBoard = true;
            }
        }
        for (Cell cell : listOfIntersectingCells ){
            System.out.println("Cellule : " + cell.rowNumber + ":" + cell.columnNumber + "value:" + cell.realValue + "->" + cell.possibleValue);
        }
        return modifiedBoard;
    }

}
