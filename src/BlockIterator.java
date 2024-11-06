public class BlockIterator implements CollectionIterator{
    private Cell [][] grille;
    //private int blockNb;  // le numéro de la ligne
    public int valInit;
    public int i;
    public int j;


    public BlockIterator(Cell[][] grille, int blockNb) {
        this.grille = grille;
        //this.blockNb = blockNb;
        switch (blockNb){
            case 0:
                valInit=0;
                i = 0;
                j = 0;
                break;
            case 1:
                valInit=0;
                i = 0;
                j = 3;
                break;
            case 2:
                valInit=0;
                i = 0;
                j = 6;
                break;
            case 3:
                valInit=3;
                i = 3;
                j = 0;
                break;
            case 4:
                valInit=3;
                i = 3;
                j = 3;
                break;
            case 5:
                valInit=3;
                i = 3;
                j = 6;
                break;
            case 6:
                valInit=6;
                i = 6;
                j = 0;
                break;
            case 7:
                valInit=6;
                i = 6;
                j = 3;
                break;
            case 8:
                valInit=6;
                i = 6;
                j = 6;
        }
    }

    @Override
    public int getIndex(){  // A retirer
        return 0;
    }

    public void affihceIndex(){     // A retirer
        System.out.println(this.getIndex());
    }

    @Override
    public boolean hasNext() {
        return (i < valInit+3);

        //return ((j%3!=2)||(i%3!=2));
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
}