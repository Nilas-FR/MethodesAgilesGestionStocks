package fournisseur;

import principale.Model;

import java.util.List;
import java.util.Vector;

public class FournisseurModel extends Model {

    private final FournisseurDAO fournisseurDAO;

    /**
     * Constructeur
     */
    public FournisseurModel() {
    	fournisseurDAO = new FournisseurDAO();
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    @Override
    public int ajouter(Object object) {
        return 0;
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    @Override
    public int modifier(Object object) {
        return 0;
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    @Override
    public int supprimer(Object object) {
        return 0;
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    @Override
    public List chercher(String pattern) {
        return null;
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    @Override
    public List recupererListe() {
        return null;
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    public Vector<Fournisseur> getListeFournisseurs() {
        return fournisseurDAO.getListeFournisseurs();
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    public void supprimerFournisseur(String Siret) {
    	fournisseurDAO.supprimerFournisseur(Siret);
    }

    /**
     * Héritage, non implémenté pour Fournisseur
     */
    public void ajouterFournisseur(Fournisseur F) {
    	fournisseurDAO.ajouterFournisseur(F);
    }
}
