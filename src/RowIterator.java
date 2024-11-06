/*
public class RowIterable extends CollectionIterator {
    public Cell[] row;
// A MODIFIER
    // es-ce qu'il faut un "premier"
    // Vérifier que c'est pas pareil partout pour next() et hasNext()

    public RowIterable(Cell[][] board, int numRow) {
        row = board[numRow];
    }

    public Cell next(Cell cell) {
        index++; // A VERIFIER
        return row[index];
    }

    public boolean hasNext(Cell cell) {
        return index < row.length;
    }

}*/
public class RowIterator implements CollectionIterator{
    private Cell[][] grille;
    private int rowNb;  // le numéro de la ligne
    public int prochainIndice = 0;

    public RowIterator(Cell[][] grille, int rowNb) {
        this.grille = grille;
        this.rowNb = rowNb;
    }

    @Override
    public int getIndex(){
        return prochainIndice;
    }

    public void affihceIndex(){
        System.out.println(this.getIndex());
    }

    @Override
    public boolean hasNext() {
        return prochainIndice < grille[rowNb].length;
    }

    @Override
    public Cell next() {
        if(hasNext()){
//            int i = prochainIndice;
//            prochainIndice++;
            return grille[rowNb][prochainIndice++];
        }
        return grille[rowNb][prochainIndice]; //trouver quoi retourner comme Cell
    }
}
