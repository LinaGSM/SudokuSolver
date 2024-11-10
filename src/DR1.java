public class DR1 extends DeductionRule{
// Pas Testé
    public boolean removed = false;
    @Override
    public SudokuBoard processRule(SudokuBoard board) {
        board.iterator.remettreLesCompteursAZero();

        int i=0;
        System.out.println("Sudoku Initial :");
        board.show();
        while(/*!isFilled &&*/ hasChanged) {
            System.out.println("\nPassage: "+i++);
            hasChanged = false;
            board.iterator.remettreLesCompteursAZero();
            /*isFilled = true;*/
            while (board.iterator.hasNext()) {
                Cell cell = board.iterator.next();
                if (emptyCell(cell)) {
                    /*isFilled = false;*/
                    //On retire des valeurs possibles de la cellule toutes les valeurs de la ligne
                    CollectionIterator rowIterator = board.createIterator(IteratorType.ROW, cell.rowNumber);
                    while (rowIterator.hasNext()) {
                        Cell rowCell = rowIterator.next();  // traiter le cas où la cellule est comparée à elle meme dans le parcours

                        removeValue(cell, rowCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        /*if (cell.rowNumber==7 && cell.columnNumber==1 && removed){
                            System.out.println("Removed possible value d'une LIGNE from: "+cell.rowNumber+":"+cell.columnNumber+" : "+rowCell.realValue);
                        }*/
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs de la colonne
                    CollectionIterator columnIterator = board.createIterator(IteratorType.COLUMN, cell.columnNumber);
                    while (columnIterator.hasNext()) {
                        Cell columnCell= columnIterator.next();
                        removeValue(cell, columnCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        /*if (cell.rowNumber==7 && cell.columnNumber==1 && removed){
                            System.out.println("Removed possible value d'une COLONNE from: "+cell.rowNumber+":"+cell.columnNumber+" : "+columnCell.realValue);
                        }*/
                        removed = false;
                    }

                    // On retire des valeurs possibles de la cellule toutes les valeurs du bloc
                    CollectionIterator blockIterator = board.createIterator(IteratorType.BLOCK, cell.blockNumber);
                    int indice=0;
                    while (blockIterator.hasNext()) {
                        Cell blockCell= blockIterator.next();

                        removeValue(cell, blockCell.realValue); // A améliorer pour quitter dès que realValue!=0
                        /*if (cell.rowNumber==7 && cell.columnNumber==1){
                            if(removed){
                                System.out.println("Removed possible value d'un BLOC from: "+cell.rowNumber+":"+cell.columnNumber+" : "+blockCell.realValue);
                                System.out.println("Bloc: "+cell.blockNumber+" : "+indice);
                            }
                            System.out.println(blockCell.realValue);

                        }*/
                        removed = false;
                        indice++;
                    }

                }
            }
            //board.show();
        }
        return board;
    }


// Pas Testé
    public boolean emptyCell(Cell cell){
        return cell.realValue==0;
    }

// Pas Testé
    public void removeValue(Cell cell,int value){   // Retire les valeur value des possibleValues de cell
        /*if(cell.possibleValue.size()==1){
            cell.realValue = cell.possibleValue.get(0);
            System.out.print(cell.realValue);
            System.out.println(cell.possibleValue.get(0));
            System.out.println("ligne: "+cell.rowNumber+" colonne: "+cell.columnNumber);
        }*/
        if (cell.possibleValue.contains(value) && !noMorePossibleVal(cell)){

            cell.possibleValue.remove(Integer.valueOf(value));
            removed=true;
            /*if (cell.rowNumber==7 && cell.columnNumber==1){
                System.out.println("Removed possible value: "+value+" from: "+cell.rowNumber+":"+cell.columnNumber);
            }*/

            hasChanged = true;
            noMorePossibleVal(cell);
            /*if (cell.rowNumber==2 && cell.columnNumber==4){
                System.out.println("Removed possible value: "+value+" from: "+cell.rowNumber+":"+cell.columnNumber);
            }*/
        }
    }

    public boolean noMorePossibleVal(Cell cell){
        if(cell.possibleValue.size()==1){
            cell.realValue = cell.possibleValue.get(0);
            hasChanged = true;

             System.out.println("CHANGEMENT  "+cell.rowNumber+":"+cell.columnNumber+"    Valeur: "+cell.realValue);
            /*System.out.print("Ma valeur: "+cell.realValue+" ");
            System.out.println("Mes valeurs possibles: "+cell.possibleValue);*/
        }
        return cell.possibleValue.size()==1;
    }
}
