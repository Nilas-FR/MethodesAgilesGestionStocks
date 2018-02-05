package principale;

import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

import variables.Variables;

public class PrincipaleVue extends JFrame {

    public final JMenu menuArticles = new JMenu("Articles");
    public final JMenu menuFournisseurs = new JMenu("Fournisseurs");
    public final JMenu menuClients = new JMenu("Clients");
    public final JMenu menuCommandes = new JMenu("Commandes");
    
	public PrincipaleVue() {
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(menuArticles);
        menuBar.add(menuFournisseurs);
        menuBar.add(menuClients);
        menuBar.add(menuCommandes);
        setJMenuBar(menuBar);
	}
	
	public void setActionListener(MouseListener listener) {
		menuArticles.addMouseListener(listener);
		menuFournisseurs.addMouseListener(listener);
		menuClients.addMouseListener(listener);
		menuCommandes.addMouseListener(listener);
	}
	
	public void refresh() {
		invalidate();
		validate();
		repaint();
		pack();
	}

}
