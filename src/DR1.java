public class DR1 extends DeductionRule{
    public boolean removed = false;
    @Override
    public SudokuBoard processRule(SudokuBoard board) {
        board.iterator.remettreLesCompteursAZero();

        while(hasChanged) {
            hasChanged = false;
            board.iterator.remettreLesCompteursAZero();
            while (board.iterator.hasNext()) {
                Cell cell = board.iterator.next();
                if (emptyCell(cell)) {
                    //On retire des valeurs possibles de la cellule toutes les valeurs de la ligne
                    CollectionIterator rowIterator = board.createIterator(IteratorType.ROW, cell.rowNumber);
                    while (rowIterator.hasNext()) {
                        Cell rowCell = rowIterator.next();  // traiter le cas où la cellule est comparée à elle meme dans le parcours
                        removeValue(cell, rowCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs de la colonne
                    CollectionIterator columnIterator = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
                    while (columnIterator.hasNext()) {
                        Cell columnCell= columnIterator.next();
                        removeValue(cell, columnCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs du bloc
                    CollectionIterator blockIterator = board.createIterator(IteratorType.BLOCK, cell.blockNumber);
                    int indice=0;
                    while (blockIterator.hasNext()) {
                        Cell blockCell= blockIterator.next();

                        removeValue(cell, blockCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        removed = false;
                        indice++;
                    }

                }
            }
        }
        return board;
    }

    public boolean emptyCell(Cell cell){
        return cell.realValue==0;
    }

    public void removeValue(Cell cell,int value){   // Retire les valeur value des possibleValues de cell
        if (cell.possibleValue.contains(value) && !noMorePossibleVal(cell)){

            cell.possibleValue.remove(Integer.valueOf(value));
            removed=true;
            hasChanged = true;
            noMorePossibleVal(cell);
        }
    }

    public boolean noMorePossibleVal(Cell cell){
        if(cell.possibleValue.size()==1){
            cell.realValue = cell.possibleValue.get(0);
            hasChanged = true;
        }
        return cell.possibleValue.size()==1;
    }
}
