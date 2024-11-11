public class BoardItrator implements CollectionIterator {
    private Cell[][] grille;
    private int prochainIndice = 0; // On commence à la première case de la ligne
    private int row = 0;    // On commence à la première ligne

    public BoardItrator(Cell[][] grille) {
        this.grille = grille;
    }

    public void remettreLesCompteursAZero() {
        prochainIndice = 0;
        row = 0;
    }

    @Override
    public int getIndex() {
        return prochainIndice;
    }


    @Override
    public boolean hasNext() {
        return (row < grille.length);
    }

    @Override
    public Cell next() {
        if (hasNext()) {
            if (prochainIndice == grille.length - 1) {
                int j = prochainIndice;
                row++;
                prochainIndice = 0;
                return grille[row - 1][j];
            }
            return grille[row][prochainIndice++];
        }
        return grille[row - 1][prochainIndice]; //trouver quoi renvoyer au cas où pas de next
    }
}
