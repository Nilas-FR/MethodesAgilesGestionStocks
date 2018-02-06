package principale;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Commande.CommandeController;
import article.ArticleController;
import client.ClientController;
import fournisseur.FournisseurController;
import login.LoginController;
import variables.Variables;

import javax.swing.*;

public class PrincipaleController implements MouseListener {
	
	/** Pointeur vers la vue */
	public final PrincipaleVue JF = new PrincipaleVue();

    /** Pointeur vers le module de connection */
    public final LoginController Login = new LoginController(this);

	/** Pointeur vers le module des fournisseurs */
	public final FournisseurController Fournisseur = new FournisseurController(this);
	/** Pointeur vers le module des articles */
	public final ArticleController Article = new ArticleController(this);
	/** Pointeur vers le module des clients */
	public final ClientController Client = new ClientController(this);
    /** Pointeur vers le module de commandes */
    public final CommandeController Commande = new CommandeController(this);
	
	/** Constructeur */
	public PrincipaleController() {
		JF.setActionListener(this);
		refreshActive();
	}
	
	/** Actualise le panel sélectionné*/
	public void refreshActive() {
		if (Variables.VueActive == 0) Login.setActive("Login");
		if (Variables.VueActive == 1) Article.setActive("Article");
		if (Variables.VueActive == 2) Fournisseur.setActive("Fournisseur");
        if (Variables.VueActive == 3) Client.setActive("Client");
        if (Variables.VueActive == 4) Commande.setActive("Commande");
		
		JF.refresh();
	}
	
	/**Gère l'action une option du menu est cliquée */
	@Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        
        if (Variables.VueActive == 0) {
        	//Message.MessageSimple("Connection requise", "Veuillez d'abord vous connecter.");
        	//return; //TODO Enlever pour la version livrable
        }

        if (source == JF.menuLogin) {
        	Variables.VueActive = 0;
        	Variables.Droit = -1;
        } else if (source == JF.menuArticles) {
        	Variables.VueActive = 1;
        } else if (source == JF.menuFournisseurs) {
        	Variables.VueActive = 2;
        } else if (source == JF.menuClients) {
        	Variables.VueActive = 3;
        } else if (source == JF.menuCommandes) {
        	Variables.VueActive = 4;
        }
        refreshActive();
    }

    public void afficherJPanel(JPanel panel) {
        JF.setContentPane(panel);
        JF.refresh();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    /** Lanceur de l'application : Main principal*/
	public static void main(String[] args) {
		new PrincipaleController();
	}

}
