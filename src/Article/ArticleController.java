package article;

import javax.swing.*;

import java.awt.event.*;
import java.util.List;

import message.Message;
import principale.Controller;
import principale.PrincipaleController;
import variables.Variables;

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
        if (Variables.Droit < 1) {
            Message.MessageAlerte("Droit insuffisant", "Vous n'avez pas les droits de modification, veuillez vous connecter.");
            return;
        }

        Object source = e.getSource();

        // ouvre la fenêtre d'ajout de nouveau client
        if (source == Vue.boutonAjouter) {
            fenetreCreationModification = new ArticleCreerOuModifier(null, this);
            PC.JF.setContentPane(fenetreCreationModification);
            PC.JF.refresh();
            return;
        }

        // recherche de client selon son nom
        if (source == Vue.boutonRecherche) {
            Vue.afficherListe(Model.chercher(Vue.getStringRecherche()), this);
            PC.JF.refresh();
            return;
        }

        // ouvre la fenêtre de modification de client (parcours boutons modification)
        List<JButton> boutonsModif = Vue.getListBoutonsModification();
        for (int i = 0; i < boutonsModif.size(); i++) {
            if(source == boutonsModif.get(i)) {
                fenetreCreationModification = new ArticleCreerOuModifier(((ArticleModel)Model).recupererListe().get(i), this);
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


