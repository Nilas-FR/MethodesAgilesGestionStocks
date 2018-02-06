package principale;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public abstract class Vue extends JPanel {
	
	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public abstract void ajouterListener(ActionListener listener);
	
	/**On ajoute le panel � la fen�tre principale
	 * @param Titre est le titre de la fen�tre principale
	 * @param JF est la fen�tre principale
	 */
	public void setActive(JFrame JF, String Titre) {
		JF.setContentPane(this);
		JF.setTitle(Titre);
	}

	public abstract void afficherListe(List liste, ActionListener listener);
}
