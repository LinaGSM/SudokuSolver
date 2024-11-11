public class RowIterator implements CollectionIterator {
    private Cell[][] grille;
    private int rowNb;  // le numéro de la ligne
    public int prochainIndice = 0;

    //Crée la ligne iterable numéro "rowNb" de la grille
    public RowIterator(Cell[][] grille, int rowNb) {
        this.grille = grille;
        this.rowNb = rowNb;
    }

    @Override
    public boolean hasNext() {
        return prochainIndice < grille[rowNb].length;
    }

    @Override
    public Cell next() {
        if (hasNext()) {
            return grille[rowNb][prochainIndice++];
        }
        return grille[rowNb][prochainIndice];
    }

    @Override
    public void remettreLesCompteursAZero() {
        prochainIndice = 0;
    }

    @Override
    public int getIndex() {
        return prochainIndice;
    }

}
