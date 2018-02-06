package article;

import javax.swing.*;

import java.awt.event.*;
import java.util.List;
import principale.Controller;
import principale.PrincipaleController;

public class ArticleController extends Controller {

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
            fenetreCreationModification = new ArticleCreerOuModifier(null, this);
            PC.JF.setContentPane(fenetreCreationModification);
            PC.JF.refresh();
            return;
        }

        // recherche de client selon son nom
        if (source == vue.boutonRecherche) {
            Vue.afficherListe(Model.chercher(vue.getStringRecherche()), this);
            PC.JF.refresh();
            return;
        }

        // ouvre la fenêtre de modification de client (parcours boutons modification)
        List<JButton> boutonsModif = vue.getListBoutonsModification();
        for (int i = 0; i < boutonsModif.size(); i++) {
            if(source == boutonsModif.get(i)) {
                fenetreCreationModification = new ArticleCreerOuModifier(model.recupererListe().get(i), this);
                PC.JF.setContentPane(fenetreCreationModification);
                PC.JF.refresh();
                return;
            }
        }

        // vérifie si l'event est sur un bouton supprimer
        if (verifierEventBoutonsSupprimer(source)) return;

        // vérifie si l'event est dans la fenêtre d'ajout/modification
        verifierEventFenetreAjoutModification(source);
    }
}


