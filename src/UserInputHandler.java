import java.util.Scanner;

public class UserInputHandler {
    private static UserInputHandler instance; // pour definir le UserInputHandler en tant que singleton

    private UserInputHandler() {
    }

    public static UserInputHandler getInstance() {
        if (instance == null) {
            return instance = new UserInputHandler();
        }
        return instance;
    }

    public void notifyUserToEnterValue(SudokuBoard board) {
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        int col;
        int l;

        System.out.println("La resolution du sudoku est bloque , veuillez debloquez sa resolution en entrant une valeur valide dans l'une des cellules non résolue : ");
        board.show();
        System.out.println();

        while (true) {
            System.out.println("Veuillez indiquer les indices de la cellule que vous souhaitez modifier");
            // Recupération des entrées de l'utilisateur
            System.out.print("\tn° de line : ");
            l = myObj.nextInt();

            System.out.print("\tn° de colonne : ");
            col = myObj.nextInt();

            // vérification des entrées de l'utilisateur
            if (l < 0 || l > 8) {
                System.out.println("\tErreur : L'indice de la ligne entrée n'est pas comprise entre 0 et 8 ");
                System.out.println();

            } else if (col < 0 || col > 8) {
                System.out.println("\tErreur : L'indice de la colonne entrée n'est pas comprise entre 0 et 8 ");
                System.out.println();
            } else {
                Cell cell = board.board[l][col];
                if (cell.realValue != 0) {
                    System.out.println("\tLa cellule choisie est deja resolue : veuillez choisir une autre cellule");
                    System.out.println();
                } else {
                    System.out.println("\tLa cellule choisie peut etre modifiee");
                    System.out.println();

                    // Affichage des valeurs possibles pour la cellule choisie
                    System.out.println("Veuillez entrer la valeur de la cellule : ");
                    System.out.println("\tVous avez la possibilite de choisir parmi ces valeur : " + cell.possibleValue);

                    // recupere la valeur de la cellule à resoudre
                    System.out.print("\tvaleur de la cellule : ");
                    int val = myObj.nextInt();
                    System.out.println();

                    // Verifie si la valeur est valide
                    if ((val < 1) || (val > 9) || (!cell.possibleValue.contains(val))) {
                        System.out.println("\tLa valeur entrée n'est pas valide");
                        System.out.println();
                    } else {
                        System.out.println("\tLa valeur entree est valide. Nous procedons à la modification");
                        cell.possibleValue.clear();
                        cell.possibleValue.add(val);
                        cell.realValue = val;
                        System.out.println();
                        break;
                    }


                }
            }
        }
        // Affichage du Sudoku apres modification
        System.out.println("Affichage du Sudoku apres modification : ");
        board.show();
        System.out.println();

    }


}
