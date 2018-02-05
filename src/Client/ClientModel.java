package Client;

import Model.Model;

import java.util.List;

public class ClientModel extends Model {

    /**
     * Gestionnaire des clients de la base de données
     */
    private final ClientDAO clientDAO;

    /**
     * Liste des clients
     */
    private List<Client> listeClients;

    /**
     * ClientDAO de gestion des clients
     */
    public ClientModel() {
        clientDAO = new ClientDAO();

        actualiserListeClients();
    }

    /**
     * Ajoute un client dans la base de données
     * @param client client à ajouter à la base
     * @return le succès de l'opération
     */
    public int ajouterClient(Client client) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.ajouter(client);
        // affichage du nombre de lignes ajoutées dans la bdd pour vérification
        System.out.println(retour + " ligne ajoutée");

        actualiserListeClients();
        return retour;
    }

    /**
     * Modifie le client passé en paramètre dans la base de données
     * @param client client à modifier
     * @return le succès de l'opération
     */
    public int modifierClient(Client client) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.modifier(client);
        // affichage du nombre de lignes modifiées dans la bdd pour vérification
        System.out.println(retour + " ligne modifiée");

        actualiserListeClients();
        return retour;
    }

    /**
     * Supprime le client passé en paramètre dans la base de données
     * @param client client à supprimer
     * @return le succès de l'opération
     */
    public int supprimerClient(Client client) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        int retour = clientDAO.supprimer(client);
        // affichage du nombre de lignes supprimées dans la bdd pour vérification
        System.out.println(retour + " ligne supprimée");

        actualiserListeClients();
        return retour;
    }

    /**
     * Recherche les clients dont le nom contient le paramètre
     * @param nom texte contenu dans les articles à chercher
     * @return les clients correspondants
     */
    public List<Client> chercher(String nom) {
        //on demande à la classe de communication d'envoyer l'article dans la table article
        listeClients = clientDAO.rechercherClients(nom);
        // affichage du nombre de lignes récupérées dans la bdd pour vérification
        System.out.println(listeClients.size() + " lignes récupérées");

        return listeClients;
    }

    /**
     * Renvoie la liste des clients de la table associée
     * @return liste des clients
     */
    public List<Client> recupererListeClients() {
        return listeClients;
    }

    /**
     * Permet d'actualiser la liste des clients, à appeler à chaque changement dans la table des clients
     */
    private void actualiserListeClients() {
        listeClients = clientDAO.getListeClients();
    }
}

