package principale;

import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Vue extends JPanel {
	
	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
	}
	
	/**On ajoute le panel à la fenêtre principale
	 * @param JF est la fenêtre principale
	 */
	public void setActive(JFrame JF) {
		JF.add(this);
	}
	
	/**On enlève le panel de la fenêtre principale
	 * @param JF est la fenêtre principale
	 */
	public void setUnactive(JFrame JF) {
		JF.remove(this);
	}
}
