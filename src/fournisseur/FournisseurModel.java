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
     * H�ritage, non impl�ment� pour Fournisseur
     */
    @Override
    public int ajouter(Object object) {
        return 0;
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    @Override
    public int modifier(Object object) {
        return 0;
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    @Override
    public int supprimer(Object object) {
        return 0;
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    @Override
    public List chercher(String pattern) {
        return null;
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    @Override
    public List recupererListe() {
        return null;
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    public Vector<Fournisseur> getListeFournisseurs() {
        return fournisseurDAO.getListeFournisseurs();
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    public void supprimerFournisseur(String Siret) {
    	fournisseurDAO.supprimerFournisseur(Siret);
    }

    /**
     * H�ritage, non impl�ment� pour Fournisseur
     */
    public void ajouterFournisseur(Fournisseur F) {
    	fournisseurDAO.ajouterFournisseur(F);
    }
}
