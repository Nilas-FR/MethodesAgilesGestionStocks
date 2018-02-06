package principale;

import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import variables.Variables;

public class PrincipaleVue extends JFrame {

	/** Option qui compose le menu */
    public final JMenu menuLogin = new JMenu("Se déconnecter");
    /** Option qui compose le menu */
    public final JMenu menuArticles = new JMenu("Articles");
    /** Option qui compose le menu */
    public final JMenu menuFournisseurs = new JMenu("Fournisseurs");
    /** Option qui compose le menu */
    public final JMenu menuClients = new JMenu("Clients");
    /** Option qui compose le menu */
    public final JMenu menuCommandes = new JMenu("Commandes");
    
    /** Constructeur du panel principal*/
	public PrincipaleVue() {
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        //On compose le menu avec les options puis on l'ajoute
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuLogin);
        menuBar.add(menuArticles);
        menuBar.add(menuFournisseurs);
        menuBar.add(menuClients);
        menuBar.add(menuCommandes);
        setJMenuBar(menuBar);
	}
	
	/**On ajoute les listener
	 * @param listener
	 */
	public void setActionListener(MouseListener listener) {
		menuLogin.addMouseListener(listener);
		menuArticles.addMouseListener(listener);
		menuFournisseurs.addMouseListener(listener);
		menuClients.addMouseListener(listener);
		menuCommandes.addMouseListener(listener);
	}
	
	/** On réaffiche la page */
	public void refresh() {
		invalidate();
		validate();
		repaint();
		pack();
	}

}
