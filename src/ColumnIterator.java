public class ColumnIterator implements CollectionIterator {
    private Cell[][] grille;
    private int colNb;  // le numéro de la ligne
    private int prochainIndice = 0;

    public ColumnIterator(Cell[][] grille, int colNb) {
        this.grille = grille;
        this.colNb = colNb;
    }

    @Override
    public int getIndex() {
        return prochainIndice;
    }

    @Override
    public boolean hasNext() {
        return prochainIndice < grille.length;
    }

    @Override
    public Cell next() {
        if (hasNext()) {
            return grille[prochainIndice++][colNb];
        }
        return grille[prochainIndice][colNb]; //trouver quoi renvoyer au cas où pas de next
    }

    public void remettreLesCompteursAZero() {
        prochainIndice = 0;
    }
}