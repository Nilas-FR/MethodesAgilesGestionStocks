package principale;

import java.util.List;

public abstract class Model {

    /**
     * Constructeur Model
     */
    public Model() { }

    /**
     * Ajoute un objet dans la base de données
     * @param object objet à ajouter à la base
     * @return le succès de l'opération
     */
    public abstract int ajouter(Object object);

    /**
     * Modifie l'object passé en paramètre dans la base de données
     * @param object object à modifier
     * @return le succès de l'opération
     */
    public abstract int modifier(Object object);

    /**
     * Supprime l'object passé en paramètre dans la base de données
     * @param object object à supprimer
     * @return le succès de l'opération
     */
    public abstract int supprimer(Object object);

    /**
     * Recherche les objets dont l'attribut recherché contient le paramètre
     * @param pattern texte contenu dans les objets à chercher
     * @return liste des objets correspondants
     */
    public abstract List chercher(String pattern);

    /**
     * Renvoie la liste des objets de la table associée
     * @return liste des objets
     */
    public abstract List recupererListe();
}

