public class RuleManager {
    private static RuleManager ruleManagerInstance;
    private UserInputHandler userInputHandler;
    private DeductiveRuleHandler firstRule;

    //constructeur
    private RuleManager(){
        userInputHandler = UserInputHandler.getInstance();
        firstRule = new DR1();
        // definit DR2 comme la suite de DR1
        firstRule.setNext(new DR2());
        // definit DR3 comme la suite de DR2
        firstRule.getNext().setNext(new DR3());
    }

    //getters et setters
    public static RuleManager getRuleManagerInstance() {
        if (ruleManagerInstance == null){
            ruleManagerInstance = new RuleManager();
        }
        return ruleManagerInstance;
    }

    // methods

    // applique les differentes regles pour resoudre le sudoku
    public void applyRules(SudokuBoard board){
        boolean dr3WasApplied = false;
        while (!isFilled(board)){
            DeductiveRuleHandler currentRule = firstRule;

            while(currentRule != null){

                if (currentRule instanceof DR1) {
                    System.out.println("Application de DR1 ...");
                    currentRule.processRule(board);
                    board.setDifficulty(Difficulty.EASY);
                } else if (currentRule instanceof DR2) {
                    System.out.println("Application de DR2 ...");
                    currentRule.processRule(board);
                    board.setDifficulty(Difficulty.MEDIUM);

                } else if (currentRule instanceof DR3) {
                    System.out.println("Application de DR3 ...");
                    currentRule.processRule(board);
                    dr3WasApplied = true;
                    board.setDifficulty(Difficulty.HARD);
                }

                currentRule = currentRule.getNext();

                // si le tableau est rempli plus besoin d'appliquer de regle
                if(isFilled(board)){
                    break;
                }
            }

            // Demande de l'aide à l'utilisateur si le sudoku n'est pas remplie apres la suite de DR
            if (!isFilled(board)) {
                notifyUser(board);
            }
        }

        // Oblige le niveau de difficulté à rester à HARD si on réapplique la chaine de DR plus d'une fois
        if (dr3WasApplied){
            board.setDifficulty(Difficulty.HARD);
        }
    }

    // verifie si le sudoku est remplie
    boolean isFilled(SudokuBoard board){
        boolean isFilled = true;
        while (board.iterator.hasNext()){
            Cell cell = board.iterator.next();
            if (cell.realValue == 0){
                isFilled = false;
            }
        }
        return isFilled;
    }

    // notifie l'utilsateur de debloqué la resolution du sudoku
    public void notifyUser(SudokuBoard board){
        userInputHandler.notifyUserToEnterValue(board);
    }
}
