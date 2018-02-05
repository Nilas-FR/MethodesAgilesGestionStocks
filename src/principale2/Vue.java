package principale2;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import variables.Variables;

public class Vue extends JPanel {
	
	/**Ajoute le listener sur les boutons
	 * @param listener Le listener des boutons */
	public void ajouterListener(ActionListener listener) {
	}
	
	/**On ajoute le panel � la fen�tre principale
	 * @param JF est la fen�tre principale
	 */
	public void setActive(PrincipaleVue JF) {
		JF.add(this);
	}
	
	/**On enl�ve le panel de la fen�tre principale
	 * @param JF est la fen�tre principale
	 */
	public void setUnactive(PrincipaleVue JF) {
		JF.remove(this);
	}
}
