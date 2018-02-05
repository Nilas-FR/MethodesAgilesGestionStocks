package Article;

import javax.swing.*;

import fournisseur.FournisseurVue;

import java.awt.event.*;
import java.util.List;
import principale.Controller;
import principale.PrincipaleController;

public class ArticleController extends Controller implements ActionListener {

	private ModelArticle Model = new ModelArticle();
	
    /**
     * Créé le controleur des articles
     * @param modelArticle model gérant les articles
     * @param vue JFrame globale de l'application
     */
    public ArticleController(PrincipaleController PC) {
    	super(PC);
    	this.Vue = new ArticleVue();
    	Vue.ajouterListener(this);
    }

    /**
     * Cherche les actions à effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évènement
     */
    public void actionPerformed(ActionEvent e) {
        ModelArticle modelArticle = (ModelArticle) model;
        Object source = e.getSource();

        // ajouter un article dans la base de données
        if(source == vue.getVueArticles().boutonAjouter) {
            vue.getVueArticles().afficherVueNouvelArticle(this);
            return;
        }
        if (source == vue.getVueArticles().boutonRecherche) {
            vue.getVueArticles().afficherListeArticles(modelArticle.chercher(vue.getVueArticles().getDesignationRecherche()), this);
            return;
        }

        /*
         * Vérifie l'écouteur des boutons de modification de la liste des articles
         */
        List<JButton> boutonsModifArticle = vue.getVueArticles().getListBoutonsModificationArticles();
        for (int i = 0; i < boutonsModifArticle.size(); i++) {
            if(source == boutonsModifArticle.get(i)) {
                System.out.println("Clic sur le bouton de modification " + i + " aka " + modelArticle.recupererListeArticles().get(i).getDesignation());
                vue.getVueArticles().afficherVueModifierArticle(this, modelArticle.recupererListeArticles().get(i));
                return;
            }
        }

        /*
         * Vérifie l'écouteur des boutons de suppression de la liste des articles
         */
        List<JButton> boutonsSupprArticle = vue.getVueArticles().getListBoutonsSuppressionArticles();
        for (int i = 0; i < boutonsSupprArticle.size(); i++) {
            if(source == boutonsSupprArticle.get(i)) {
                System.out.println("Clic sur le bouton de suppression " + i + " aka " + modelArticle.recupererListeArticles().get(i).getDesignation());
                modelArticle.supprimerArticle(modelArticle.recupererListeArticles().get(i));
                vue.getVueArticles().afficherListeArticles(modelArticle.recupererListeArticles(), this);
                return;
            }
        }

        // boutons de la fenêtre d'ajout/modification d'article
        if (vue.getVueArticles().getFenetreCreationOuModificationArticle() != null) {
            // validation de l'ajout d'un nouvel article
            if (source == vue.getVueArticles().getFenetreCreationOuModificationArticle().boutonAjouter) {
                modelArticle.ajouterArticle(vue.getVueArticles().validerCreation());
                vue.getVueArticles().afficherListeArticles(modelArticle.recupererListeArticles(), this);
                return;
            }
            // annuler l'ajout/la modification d'un article
            if (source == vue.getVueArticles().getFenetreCreationOuModificationArticle().boutonAnnulerModification) {
                vue.getVueArticles().fermerFenetreCreationModification();
                return;
            }
            // valider la modification d'un article
            if (source == vue.getVueArticles().getFenetreCreationOuModificationArticle().boutonValiderModification) {
                Article article = vue.getVueArticles().getFenetreCreationOuModificationArticle().validerModification();
                System.out.println("Modifier article : " + article);
                modelArticle.modifierArticle(article);
                vue.getVueArticles().afficherListeArticles(modelArticle.recupererListeArticles(), this);
                vue.getVueArticles().fermerFenetreCreationModification();
                return;
            }
        }

        vue.repaint();
    }
}


