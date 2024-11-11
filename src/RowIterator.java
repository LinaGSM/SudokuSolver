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


    @Override
    public boolean hasNext() {
        return prochainIndice < grille[rowNb].length;
    }

    @Override
    public Cell next() {
        if(hasNext()){
            return grille[rowNb][prochainIndice++];
        }
        return grille[rowNb][prochainIndice]; //trouver quoi retourner comme Cell
    }

    public void remettreLesCompteursAZero(){
        prochainIndice=0;
    }
}
