public class DR1 extends DeductionRule {
    public boolean removed = false;

    @Override
    public SudokuBoard processRule(SudokuBoard board) {
        board.iterator.remettreLesCompteursAZero();
        while (hasChanged) {
            hasChanged = false;
            board.iterator.remettreLesCompteursAZero();
            while (board.iterator.hasNext()) {
                Cell cell = board.iterator.next();
                if (emptyCell(cell)) {

                    //On retire des valeurs possibles de la cellule, toutes les valeurs de la ligne
                    CollectionIterator rowIterator = board.createIterator(IteratorType.ROW, cell.rowNumber);
                    while (rowIterator.hasNext()) {
                        Cell rowCell = rowIterator.next();  // traiter le cas où la cellule est comparée à elle meme dans le parcours
                        removeValue(cell, rowCell.realValue);
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs de la colonne
                    CollectionIterator columnIterator = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
                    while (columnIterator.hasNext()) {
                        Cell columnCell = columnIterator.next();
                        removeValue(cell, columnCell.realValue);
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs du bloc
                    CollectionIterator blockIterator = board.createIterator(IteratorType.BLOCK, cell.blockNumber);
                    while (blockIterator.hasNext()) {
                        Cell blockCell = blockIterator.next();
                        removeValue(cell, blockCell.realValue);
                        removed = false;
                    }

                }
            }
        }
        return board;
    }

    //Vérifie si cell est une case vide
    public boolean emptyCell(Cell cell) {
        return cell.realValue == 0;
    }

    //Retire la valeur value des possibilités de la Cellule
    public void removeValue(Cell cell, int value) {   // Retire les valeurs value des possibilités de cell
        if (cell.possibleValue.contains(value) && !noMorePossibleVal(cell)) {

            cell.possibleValue.remove(Integer.valueOf(value));
            removed = true;
            hasChanged = true;
            noMorePossibleVal(cell);
        }
    }

    //Vérifie s'il ne reste plus qu'une possibilité et l'assigne à la Cellule
    public boolean noMorePossibleVal(Cell cell) {
        if (cell.possibleValue.size() == 1) {
            cell.realValue = cell.possibleValue.get(0);
            return true;
        }
        return false;
    }
}
