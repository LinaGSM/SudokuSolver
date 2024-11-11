public class Main {
    public static void main(String[] args) {
        // Creation du sudoku a partir d'un fichier
        String path = args[0];
        SudokuBoard board = new SudokuBoard(path);

        // creation de l'instance unique de RuleManager
        RuleManager ruleManager = RuleManager.getRuleManagerInstance();

        // Applique les regles pour resoudre le sudoku
        System.out.println("Debut de la resolution du sudoku : ");
        board.show(); // Affichage de la grille
        ruleManager.applyRules(board); // application des regles

        // Affichage de fin
        System.out.println("Fin de la resolution du sudoku : ");
        board.show(); // Affiche de la grille résolu
        System.out.println("Niveau de difficulté de votre sudoku : " + board.getDifficulty());
    }
}
