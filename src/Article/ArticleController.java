package article;

import javax.swing.*;

import java.awt.event.*;
import java.util.List;
import principale.Controller;
import principale.PrincipaleController;

public class ArticleController extends Controller implements ActionListener {

    private ArticleCreerOuModifier fenetreCreerOuModifierArticle = null;

    /**
     * Créé le controleur des articles
     * @param PC controleur principal
     */
    public ArticleController(PrincipaleController PC) {
    	super(PC);
    	this.Vue = new ArticleVue(this);
    	Model = new ArticleModel();
    	Vue.afficherListe(Model.recupererListe(), this);
    }

    /**
     * Cherche les actions à effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évènement
     */
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        ArticleVue vue = (ArticleVue) Vue;
        ArticleModel model = (ArticleModel) Model;

        // ouvre la fenêtre d'ajout de nouveau client
        if (source == vue.boutonAjouter) {
            fenetreCreerOuModifierArticle = new ArticleCreerOuModifier(null, this);
            PC.JF.setContentPane(fenetreCreerOuModifierArticle);
            PC.JF.refresh();
            return;
        }

        // recherche de client selon son nom
        if (source == vue.boutonRecherche) {
            Vue.afficherListe(Model.chercher(vue.getDesignationRecherche()), this);
            PC.JF.refresh();
            return;
        }

        // ouvre la fenêtre de modification de client (parcours boutons modification)
        List<JButton> boutonsModifArticle = vue.getListBoutonsModificationArticles();
        for (int i = 0; i < boutonsModifArticle.size(); i++) {
            if(source == boutonsModifArticle.get(i)) {
                fenetreCreerOuModifierArticle = new ArticleCreerOuModifier(model.recupererListe().get(i), this);
                PC.JF.setContentPane(fenetreCreerOuModifierArticle);
                PC.JF.refresh();
                return;
            }
        }

        /*
         * Vérifie l'écouteur des boutons de suppression de la liste des articles
         */
        List<JButton> boutonsSupprArticle = vue.getListBoutonsSuppressionArticles();
        for (int i = 0; i < boutonsSupprArticle.size(); i++) {
            if(source == boutonsSupprArticle.get(i)) {
                model.supprimer(model.recupererListe().get(i));
                Vue.afficherListe(Model.recupererListe(), this);
                PC.JF.refresh();
                return;
            }
        }

        // l'évènement a été délenché sur la page de modification/création de client
        if (fenetreCreerOuModifierArticle != null) {
            // valider l'ajout d'un client
            if (source == fenetreCreerOuModifierArticle.boutonAjouter) {
                model.ajouter(fenetreCreerOuModifierArticle.validerCreation());
            }
            // valider la modification d'un client
            if (source == fenetreCreerOuModifierArticle.boutonValiderModification) {
                model.modifier(fenetreCreerOuModifierArticle.validerModification());
            }
            // Annuler la modification/création d'un client
            // Aucune action n'est requise pour l'annulation

            // ferme la fenêtre de modification/ajout
            Vue.afficherListe(Model.recupererListe(), this);
            PC.JF.setContentPane(Vue);
            PC.JF.refresh();
        }
    }
}


