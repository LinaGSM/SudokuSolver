public class BlockIterator implements CollectionIterator{
    private Cell [][] grille;
    private int blockNb;  // le numéro de la ligne
    public int valInit;
    public int i;
    public int j;


    public BlockIterator(Cell[][] grille, int blockNb) {
        this.grille = grille;
        this.blockNb = blockNb;
        remettreLesCompteursAZero();
    }

    @Override
    public int getIndex(){  // A retirer
        return 0;
    }

    public int affihceIndex(){
        System.out.println(this.getIndex());
        return 0;
    }

    @Override
    public boolean hasNext() {
        return (i < valInit+3);
    }

    @Override
    public Cell next() {
        if(hasNext()){
            if(j%3 == 2){
                int temp = j;
                j-=2;
                return grille[i++][temp];
            }
            else{

                return grille[i][j++];
            }
        }
        return grille[i][j]; //trouver quoi retourner comme Cell;
    }

    public void remettreLesCompteursAZero(){
        j = (this.blockNb%3)*3;  // On initialise toujours j à la première colonne du block
        switch (this.blockNb){
            case 0,1,2:     // Les 3 premiers Blocks on commence ligne 0
                i = 0;
                break;
            case 3,4,5:     // Les 3 suivants ligne 3
                i = 3;
                break;
            case 6,7,8:     // Et les trois derniers ligne 6
                i = 6;
                break;
            default:
                break;
        }
        valInit = i;

    }
}