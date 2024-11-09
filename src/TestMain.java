public class TestMain {
    public static void main(String[] args){
        String path =/*args[0];*/"C:\\Users\\33767\\IdeaProjects\\Pour Tester Les Connaissances\\LireFichier\\Sudokutest2.txt";
        SudokuBoard grille = new SudokuBoard(path);
        DeductionRule DR1 = new DR1();
/*
        System.out.println("Sudoku Initial :");
        grille.show();
*/
        SudokuBoard nouvelleGrille = DR1.processRule(grille);
/*
        System.out.println("\nSudoku final :");
        grille.show();
*/
        /*for(int i =0; i < 9;i++){
        System.out.println("Ligne " + i);
            CollectionIterator rowIterator = grille.createIterator(IteratorType.ROW, i);
            while (rowIterator.hasNext()) {
                Cell next = rowIterator.next();
                if(next.realValue==0){
                    System.out.println("Valeur "+next.realValue+":   Valeurs possibles: "+next.possibleValue + " ");
                }
            }
            System.out.println();

        }*/
/*
        System.out.println("Nouvelle Grille :");
        novelleGrille.show();
*/

        //System.out.println("Valeur Possible de 0:7 : "+grille.board[0][7].possibleValue);

        //System.out.println("Valeur Possible de 0:8 : "+grille.board[0][8].possibleValue);

/*
        Cell cell1 = grille.board[0][2];
        Cell cell2 = grille.board[0][0];

        cell1.possibleValue.remove(Integer.valueOf(5));
        System.out.println(cell1.possibleValue);

        //cell2.possibleValue.remove(Integer.valueOf(5));
        System.out.println(cell2.possibleValue);


        // Parcours d'une ligne
        System.out.println("Ligne 0:");
        CollectionIterator rowIterator = grille.createIterator(IteratorType.ROW, 0);
        while (rowIterator.hasNext()) {
            System.out.print(rowIterator.next().realValue + " ");
        }
        System.out.println();


        // Parcours d'une colonne
        System.out.println("\nColonne 0:");
        CollectionIterator colIterator = grille.createIterator(IteratorType.COLUMN, 0);
        while (colIterator.hasNext()) {
            System.out.print(colIterator.next().realValue + " ");
        }
        System.out.println();


        // Parcours d'un block
        System.out.println("\nBlock 0:");
        CollectionIterator blockIterator = grille.createIterator(IteratorType.BLOCK, 0);
        while (blockIterator.hasNext()) {
            System.out.print(blockIterator.next().realValue + " " );
        }
        System.out.println();

        // Parcours du Board
        System.out.println("\nSudoku Entier:");
        while (grille.Iterator.hasNext()) {
            System.out.print(grille.Iterator.next().realValue + " " );
            if(grille.Iterator.affihceIndex()==0){
                System.out.println();
            }
        }
     */   System.out.println();
    }
}