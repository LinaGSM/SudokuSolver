public class TestMain {
    public static void main(String[] args){
        String path =args[0];//"C:\\Users\\33767\\IdeaProjects\\Pour Tester Les Connaissances\\LireFichier\\Sudokutest.txt"
        SudokuBoard grille = new SudokuBoard(path);

        grille.show();

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
    }
}