public interface CollectionIterator {
    //Vérifie que la collection n'est pas entierement parcourru
    boolean hasNext();

    //Renvoit le prochain element de la Collection
    Cell next();

    //Initialise la collection pour pouvoir la parcourrir à nouveau
    void remettreLesCompteursAZero();

    int getIndex();

}