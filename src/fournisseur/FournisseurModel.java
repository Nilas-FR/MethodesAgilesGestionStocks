package fournisseur;

import java.util.Vector;

public class FournisseurModel{

    private final FournisseurDAO fournisseurDAO;

    public FournisseurModel() {
    	fournisseurDAO = new FournisseurDAO();
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
