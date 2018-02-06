package principale;

import javax.swing.*;
import java.awt.event.ActionListener;

public abstract class FenetreCreationModification extends JPanel {

    /**
     * numero de version pour classe serialisable
     * Permet d'eviter le warning "The serializable class CommandeVue does not declare a static final serialVersionUID field of type long"
     */
    protected static final long serialVersionUID = 1L;

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonAjouter = new JButton("Ajouter");

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonValiderModification = new JButton("Modifier");

    /**
     * bouton d'envoi de l'article
     */
    public final JButton boutonAnnuler = new JButton("Annuler");

    /**
     * Ajoute des écouteurs sur les boutons du panel
     */
    protected void ajouterListener(ActionListener listener) {
        boutonAjouter.addActionListener(listener);
        boutonAnnuler.addActionListener(listener);
        boutonValiderModification.addActionListener(listener);
    }

    /**
     * Rempli automatiquement les champs text field si un article est à modifier
     */
    protected abstract void remplirChampsModification();

    /**
     * Renvoie l'article à modifier avec les nouveaux paramètres
     * @return article sujet à la modification
     */
    public abstract Object validerModification();

    /**
     * Renvoie le nouvel article avec ses paramètres
     * @return article à ajouter
     */
    public abstract Object validerCreation();
}
