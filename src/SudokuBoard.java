import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SudokuBoard {

    public static Cell[][] board = new Cell[9][9];
    public static BoardItrator iterator = new BoardItrator(board);

    // A FAIRE: Traiter le cas où l'utilisateur rentre plus de 9 lignes et 9 colonnes
    // A FAIRE: Il reste à comprendre cette ligne: e.printStackTrace();  ligne 48
    //  A FAIRE: Modifier l'affichage pour le rendre esthétique

    public SudokuBoard(String chemin) {
        //J'ai testé avec ce chemin
        //String chemin = "C:\\Users\\33767\\IdeaProjects\\Pour Tester Les Connaissances\\LireFichier\\Sudokutest.txt";
        int i = 0; // compteur de lignes
        try {

            File myFile = new File(chemin); //on stock le fichier dans myFile
            Scanner myReader = new Scanner(myFile); // On lance la lecture
            while (myReader.hasNextLine()) { // on avance jusqu'à ce qu'il n'y ait pls de ligne
                String[] data = myReader.nextLine().split(","); // On stock temporairement chaque chiffre
                                                                      // de la ligne parcourue dans un tableau de
                                                                      // chaîne de caractères data

                for (int j = 0; j < data.length; j++) {     // On va remplir le board
                    board[i][j]= new Cell(Integer.parseInt(data[j]),i,j);   // On créé chaque Cell du tableau avec son constructeur
                                                                        // dont le paramètre est: le chiffre de la ligne parcourue
                                                                        // transformé en int.
                                                                        // On affecte cette Cell directement dans la bonne case du tableau
                }
// Les 2 lignes en dessous sont pour vérifier qu'on a bien des Cell
                /*System.out.println("RealValue de Cellule " + (i+1) + ", 1:  " + board[i][0].realValue );
                System.out.println("PossibleValue de Cellule:" + (i+1) + ", 1:" + board[i][0].possibleValue );*/

                i++;    // On va lire la ligne d'en dessous, compteur de ligne augmente
            }

// Les lignes suivantes sont pour vérifier l'entiereté du tableau
/*
            for (int k = 0; k < board.length; k++) {
                for (int l = 0; l < board[k].length; l++) {
                    System.out.print(board[k][l].realValue + ", ");
                }
                System.out.println();
            }
*/
        } catch (IOException e) {
            e.printStackTrace();    // A FAIRE: Il reste à comprendre cette ligne
        }
    }
    // getters et setters
    public Difficulty getDifficulty(){
        return this.difficulty;
    }

    public void setDifficulty(Difficulty d){
        this.difficulty = d;
    }

    public CollectionIterator createIterator(IteratorType type, int index) {
        switch (type) {
            case ROW:
                return new RowIterator(board, index);
            case COLUMN:
                return new ColumnIterator(board, index);
            case BLOCK:
                return new BlockIterator(board, index);
            default:
                throw new IllegalArgumentException("Type d'itérateur non supporté");
        }
    }

    public void show(){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].realValue + " ");
                if (j%3==2){
                    System.out.print("  ");
                }
            }
            System.out.println();
            if (i%3==2){
                System.out.println();
            }
        }
    }

}