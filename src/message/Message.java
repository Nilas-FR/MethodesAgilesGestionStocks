package message;

import javax.swing.JOptionPane;

public class Message {

	/** Affiche une fen�tre d'erreur avec un message et un bouton OK
	 * @param titre le titre de la fen�tre � afficher
	 * @param texte le texte de la fen�tre � afficher
	 */
	public static void MessageErreur(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.ERROR_MESSAGE);
	}

	/** Affiche une fen�tre d'alerte avec un message et un bouton OK
	 * @param titre le titre de la fen�tre � afficher
	 * @param texte le texte de la fen�tre � afficher
	 */
	public static void MessageAlerte(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.WARNING_MESSAGE);
	}

	/** Affiche une fen�tre avec un message et un bouton OK
	 * @param titre le titre de la fen�tre � afficher
	 * @param texte le texte de la fen�tre � afficher
	 */
	public static void MessageSimple(String titre, String texte) {
		JOptionPane.showMessageDialog(null, texte, titre, JOptionPane.DEFAULT_OPTION);
	}

	/** Affiche une fen�tre � choix multiple
	 * @param titre le titre de la fen�tre � afficher
	 * @param texte le texte de la fen�tre � afficher
	 * @param options les diff�rents choix disponibles
	 * @return le num�ro de l'option choisie, -1 en cas d'erreur
	 */
	public static int MessageChoix(String titre, String texte, String...options) {
		if (options.length == 0) return -1;
		return JOptionPane.showOptionDialog(null, texte, titre, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, 0);
	}

}