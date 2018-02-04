package fournisseur;

import java.util.Vector;

import Model.Model;

public class FournisseurModel extends Model {

    private final FournisseurDAO loginDAO;

    public FournisseurModel() {
    	loginDAO = new FournisseurDAO();
    }

    public Vector<Fournisseur> getListeFournisseurs() {
        return loginDAO.getListeFournisseurs();
    }
}
