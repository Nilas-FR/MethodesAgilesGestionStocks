package principale;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

public abstract class Vue extends JPanel {

	/**
	 * numero de version pour classe serialisable
	 * Permet d'eviter le warning "The serializable class CommandeVue does not declare a static final serialVersionUID field of type long"
	 */
	protected static final long serialVersionUID = 1L;

	/**
	 * Liste des boutons associ�s aux objets pour leur modification
	 */
	protected List<JButton> listeBoutonsModifier;

	/**
	 * Liste des boutons associ�s aux objets pour leur suppression
	 */
	protected List<JButton> listeBoutonsSupprimer;
	
	/**On ajoute le panel � la fen�tre principale
	 * @param Titre est le titre de la fen�tre principale
	 * @param JF est la fen�tre principale
	 */
	public void setActive(JFrame JF, String Titre) {
		JF.setContentPane(this);
		JF.setTitle(Titre);
	}

	public abstract void afficherListe(List liste, ActionListener listener);



	/**
	 * Cr�� un JLabel avec le texte pass� en param�tre avec une bordure noire et le texte align� au centre
	 * @param texte texte qui sera plac� dans le JLabel
	 * @return JLabel cr��
	 */
	protected JLabel creerLabelListe(String texte) {
		JLabel label = new JLabel(texte);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des objets
	 * @return liste des boutons de modification
	 */
	public List<JButton> getListBoutonsModification() {
		return listeBoutonsModifier;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des objets
	 * @return liste des boutons de suppression
	 */
	public List<JButton> getListBoutonsSuppression() {
		return listeBoutonsSupprimer;
	}
}
