import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SudokuBoard {

    public static Cell[][] board = new Cell[9][9];
    public static BoardItrator iterator = new BoardItrator(board);
    private Difficulty difficulty;

    //Crée le Tableau de Cellules à partir d'un fichier
    public SudokuBoard(String chemin) {
        int i = 0; // compteur de lignes
        try {

            // On récupère le fichier
            File myFile = new File(chemin);
            Scanner myReader = new Scanner(myFile);

            // On remplit le tableau lignes par lignes
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(",");

                // On remplit le tableau en créant chaque cellules dans sa case du tableau
                for (int j = 0; j < data.length; j++) {
                    board[i][j] = new Cell(Integer.parseInt(data[j]), i, j);

                }

                // On passe à la ligne suivante
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // getters et setters
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    public void setDifficulty(Difficulty d) {
        this.difficulty = d;
    }

    //Créé la ligne, colonne ou le bloc à parcourrir
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

    //Affiche le tableau actuel
    public void show() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].realValue + " ");
                if (j % 3 == 2) {
                    System.out.print("  ");
                }
            }
            System.out.println();
            if (i % 3 == 2) {
                System.out.println();
            }
        }
    }

}