package Controller;

import Article.ControllerArticle;
import Article.ModelArticle;
import Commande.ControllerCommande;
import Commande.ModelCommande;
import Menu.ControllerMenu;
import Vue.Vue;
import login.LoginUtilisateur;
import login.LoginModel;

public class ControlGroup {

    private Vue vue;

    public final ControllerArticle controllerArticle;
    public final LoginUtilisateur controllerUtilisateur;
    public final ControllerCommande controllerCommande;
    public final ControllerMenu controllerMenu;

    private ModelArticle modelArticle;
    private LoginModel modelUtilisateur;
    private ModelCommande modelCommande;

    //public ControllerXXX controllerXXX;
    //public ModelXXX modelXXX;

    public ControlGroup() {
    	vue = new Vue();

        modelArticle = new ModelArticle();
        controllerArticle = new ControllerArticle(modelArticle, vue);

        modelUtilisateur = new LoginModel();
        controllerUtilisateur = new LoginUtilisateur(modelUtilisateur, vue);

        modelCommande = new ModelCommande();
        controllerCommande = new ControllerCommande(modelCommande, vue);

        controllerMenu = new ControllerMenu(vue);
    	//controllerXXX = new ControllerXXX(modelXXX, vue);

    	vue.setVisible(true);
    }
}