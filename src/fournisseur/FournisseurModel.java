package fournisseur;

import java.util.Vector;

import Model.Model;

public class FournisseurModel extends Model {

    private final FournisseurDAO fournisseurDAO;

    public FournisseurModel() {
    	fournisseurDAO = new FournisseurDAO();
    }

    public Vector<Fournisseur> getListeFournisseurs() {
        return fournisseurDAO.getListeFournisseurs();
    }
}
