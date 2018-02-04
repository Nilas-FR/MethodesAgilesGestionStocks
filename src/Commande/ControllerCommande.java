package Commande;

import Controller.Control;
import Vue.Vue;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ControllerCommande extends Control implements ActionListener {

    /**
     * Créé le controleur des articles
     * @param modelCommande model gérant les articles
     * @param vue JFrame globale de l'application
     */
    public ControllerCommande(ModelCommande modelCommande, Vue vue) {
		super(modelCommande, vue);
		vue.setArticleController(this);

		vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
    }

    /**
     * Cherche les actions à effectuer en fonction du bouton qui a été déclenché
     * @param e bouton qui a déclenché l'évènement
     */
    public void actionPerformed(ActionEvent e) {
        ModelCommande modelCommande = (ModelCommande) model;
        Object source = e.getSource();

        // ajouter un article dans la base de données
        if(source == vue.getVueCommandes().boutonAjouter) {
            vue.getVueArticles().afficherVueNouvelArticle(this);
            return;
        }

        /*
         * Vérifie l'écouteur des boutons de modification de la liste des articles
         */
        List<JButton> boutonsModifCommande = vue.getVueCommandes().getListBoutonsModificationCommandes();
        for (int i = 0; i < boutonsModifCommande.size(); i++) {
            if(source == boutonsModifCommande.get(i)) {
                System.out.println("Clic sur le bouton de modification " + i + " aka " + modelCommande.recupererListeCommandes().get(i).getIdentifiant());
                vue.getVueCommandes().afficherVueModifierCommande(this, modelCommande.recupererListeCommandes().get(i));
                return;
            }
        }

        /*
         * Vérifie l'écouteur des boutons de suppression de la liste des articles
         */
        List<JButton> boutonsSupprCommande = vue.getVueCommandes().getListBoutonsSuppressionCommandes();
        for (int i = 0; i < boutonsSupprCommande.size(); i++) {
            if(source == boutonsSupprCommande.get(i)) {
                System.out.println("Clic sur le bouton de suppression " + i + " aka " + modelCommande.recupererListeCommandes().get(i).getIdentifiant());
                modelCommande.supprimerCommande(modelCommande.recupererListeCommandes().get(i));
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                return;
            }
        }

        // boutons de la fenêtre d'ajout/modification d'article
        if (vue.getVueCommandes().getFenetreCreationOuModificationCommande() != null) {
            // validation de l'ajout d'un nouvel article
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonAjouter) {
                //modelCommande.ajouterArticle(vue.getVueArticles().validerCreation());
                //vue.getVueArticles().afficherListeArticles(modelCommande.recupererListeArticles(), this);
                return;
            }
            // annuler l'ajout/la modification d'un article
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonAnnulerModification) {
                vue.getVueCommandes().fermerFenetreCreationModification();
                return;
            }
            // valider la modification d'un article
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonValiderModification) {
                Commande commande = vue.getVueCommandes().getFenetreCreationOuModificationCommande().validerModification();
                //System.out.println("Modifier commande : " + commande);
                modelCommande.modifierCommande(commande);
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                vue.getVueCommandes().fermerFenetreCreationModification();
                return;
            }
        }

        vue.repaint();
    }
}


