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
            vue.getVueCommandes().afficherVueNouvelleCommande(this, modelCommande.recupererListeClients());
            return;
        }

        /*
         * Vérifie l'écouteur des boutons de modification de la liste des commandes
         */
        List<JButton> boutonsModifCommande = vue.getVueCommandes().getListBoutonsModificationCommandes();
        for (int i = 0; i < boutonsModifCommande.size(); i++) {
            if(source == boutonsModifCommande.get(i)) {
                vue.getVueCommandes().afficherVueModifierCommande(this, modelCommande.recupererListeCommandes().get(i), modelCommande.recupererListeClients());
                return;
            }
        }

        /*
         * Vérifie l'écouteur des boutons de suppression de la liste des commandes
         */
        List<JButton> boutonsSupprCommande = vue.getVueCommandes().getListBoutonsSuppressionCommandes();
        for (int i = 0; i < boutonsSupprCommande.size(); i++) {
            if(source == boutonsSupprCommande.get(i)) {
                modelCommande.supprimerCommande(modelCommande.recupererListeCommandes().get(i));
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                return;
            }
        }

        /*
         * écouteurs de la fenêtre de modification/création de commande
         */
        if (vue.getVueCommandes().getFenetreCreationOuModificationCommande() != null) {
            // validation de l'ajout d'une nouvelle commande
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonValider) {
                modelCommande.ajouterCommande(vue.getVueCommandes().getFenetreCreationOuModificationCommande().validerCreation());
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                vue.getVueCommandes().fermerFenetreCreationModification();
                return;
            }
            // annuler l'ajout/la modification d'une commande
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonAnnulerModification) {
                modelCommande.actualiserListeCommandes();
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                vue.getVueCommandes().fermerFenetreCreationModification();
                return;
            }
            // valider la modification de la commande
            if (source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonValiderModification) {
                Commande commande = vue.getVueCommandes().getFenetreCreationOuModificationCommande().validerModification();
                modelCommande.modifierCommande(commande, vue.getVueCommandes().getFenetreCreationOuModificationCommande().changementDateActive());
                vue.getVueCommandes().afficherListeCommandes(modelCommande.recupererListeCommandes(), this);
                vue.getVueCommandes().fermerFenetreCreationModification();
                return;
            }

            // ajouter un article dans la base de données
            if(source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().boutonAjout) {
                vue.getVueCommandes().getFenetreCreationOuModificationCommande().afficherFenetreAjouterArticle(this, modelCommande.recupererListeArticles());
                return;
            }

            /*
             * Vérifie l'écouteur des boutons de suppression d'un article de la liste d'une commande
             */
            List<JButton> boutonsSupprArticleDansCommande = vue.getVueCommandes().getFenetreCreationOuModificationCommande().getListeBoutonsSupprimerArticles();
            for (int i = 0; i < boutonsSupprArticleDansCommande.size(); i++) {
                if(source == boutonsSupprArticleDansCommande.get(i)) {
                    vue.getVueCommandes().getFenetreCreationOuModificationCommande().supprimerArticleCommande(i, this);
                    return;
                }
            }

            /*
             * écouteurs de la fenêtre de création d'article d'une commande
             */
            if (vue.getVueCommandes().getFenetreCreationOuModificationCommande().getAjouterArticle() != null) {
                // valider l'ajout de l'article à la commande
                if(source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().getAjouterArticle().boutonValider) {
                    vue.getVueCommandes().getFenetreCreationOuModificationCommande().validerAjoutArticle();
                    vue.getVueCommandes().getFenetreCreationOuModificationCommande().afficherListeArticles(this);
                    return;
                }
                // annuler l'ajout de l'article à la commande
                if(source == vue.getVueCommandes().getFenetreCreationOuModificationCommande().getAjouterArticle().boutonAnnuler) {
                    vue.getVueCommandes().getFenetreCreationOuModificationCommande().annulerAjoutArticle();
                    return;
                }
            }
        }

        vue.repaint();
    }
}


