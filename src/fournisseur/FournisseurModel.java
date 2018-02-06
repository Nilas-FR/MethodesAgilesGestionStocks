package fournisseur;

import principale.Model;

import java.util.List;
import java.util.Vector;

public class FournisseurModel extends Model {

    private final FournisseurDAO fournisseurDAO;

    public FournisseurModel() {
    	fournisseurDAO = new FournisseurDAO();
    }

    @Override
    public int ajouter(Object object) {
        return 0;
    }

    @Override
    public int modifier(Object object) {
        return 0;
    }

    @Override
    public int supprimer(Object object) {
        return 0;
    }

    @Override
    public List chercher(String pattern) {
        return null;
    }

    @Override
    public List recupererListe() {
        return null;
    }

    @Override
    protected void actualiserListe() {

    }

    public Vector<Fournisseur> getListeFournisseurs() {
        return fournisseurDAO.getListeFournisseurs();
    }

    public void supprimerFournisseur(String Siret) {
    	fournisseurDAO.supprimerFournisseur(Siret);
    }

    public void ajouterFournisseur(Fournisseur F) {
    	fournisseurDAO.ajouterFournisseur(F);
    }
}
