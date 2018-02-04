package message;

import javax.swing.JOptionPane;

public class Message {

	/** Affiche une fenêtre d'erreur avec un message et un bouton OK
	 * @param titre le titre de la fenêtre à  afficher
	 * @param texte le texte de la fenêtre à  afficher
	 */
	public static void MessageErreur(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.ERROR_MESSAGE);
	}

	/** Affiche une fenêtre d'alerte avec un message et un bouton OK
	 * @param titre le titre de la fenêtre à  afficher
	 * @param texte le texte de la fenêtre à  afficher
	 */
	public static void MessageAlerte(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.WARNING_MESSAGE);
	}

	/** Affiche une fenêtre avec un message et un bouton OK
	 * @param titre le titre de la fenêtre à  afficher
	 * @param texte le texte de la fenêtre à  afficher
	 */
	public static void MessageSimple(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.DEFAULT_OPTION);
	}

	/** Affiche une fenêtre à  choix multiple
	 * @param titre le titre de la fenêtre à  afficher
	 * @param texte le texte de la fenêtre à  afficher
	 * @param options les différents choix disponibles
	 * @return le numéro de l'option choisie, -1 en cas d'erreur
	 */
	public static int MessageChoix(String titre, String texte, String...options) {
		if (options.length == 0) return -1;
		return JOptionPane.showOptionDialog(null, texte, titre, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
	}

}