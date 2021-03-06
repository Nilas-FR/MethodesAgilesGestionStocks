package principale;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import accueil.AccueilController;
import commande.CommandeController;
import article.ArticleController;
import client.ClientController;
import fournisseur.FournisseurController;
import login.LoginController;
import message.Message;
import variables.Variables;

import javax.swing.*;

public class PrincipaleController implements MouseListener {
	
	/** Pointeur vers la vue */
	public final PrincipaleVue JF = new PrincipaleVue();

    /** Pointeur vers le module de connection */
    public final LoginController Login = new LoginController(this);
    /** Pointeur vers le module de connection */
    public final AccueilController Accueil = new AccueilController(this);

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
        updateMenuConnecte();
		refreshActive();
        Accueil.updateTousLesChamps();
	}
	
	/** Actualise le panel s�lectionn�*/
	public void refreshActive() {
		if (Variables.VueActive == 0) Login.setActive("Login");
		if (Variables.VueActive == 1) Article.setActive("Accueil");
		if (Variables.VueActive == 2) Fournisseur.setActive("Fournisseur");
        if (Variables.VueActive == 3) Client.setActive("Client");
        if (Variables.VueActive == 4) Commande.setActive("Commande");
        if (Variables.VueActive == 5) Accueil.setActive("Accueil");
		
		JF.refresh();
	}

    /**
     * Change le texte du menu de connexion en fonction de si l'utilisateur est connect� ou non
     */
	public void updateMenuConnecte() {
        if (Variables.VueActive == 0 || Variables.Droit < 1) {
            JF.menuLogin.setText("Se connecter");
        } else {
            JF.menuLogin.setText("Se d�connecter");
        }
    }

    public void updateStats() {
        Accueil.updateTousLesChamps();
    }

    /**
     * Met � jour les listes impact�es par la suppression d'un client ou d'un article :
     * article et commande pour article
     * client et commande pour client
     */
    public void updateListeSuppression() {
        Client.Vue.afficherListe(Client.Model.recupererListe(), Client);
        Article.Vue.afficherListe(Article.Model.recupererListe(), Article);
        Commande.Vue.afficherListe(Commande.Model.recupererListe(), Commande);
    }

    /**
     * Met � jour la fen�tre article pour actualiser les stock apr�s une commande
     */
    public void updateListeDeductionStockCommande() {
        Article.Vue.afficherListe(Article.Model.recupererListe(), Article);
    }
	
	/**G�re l'action une option du menu est cliqu�e */
	@Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        
        if (Variables.VueActive == 0) {
        	Message.MessageSimple("Connection requise", "Veuillez d'abord vous connecter.");
        	return;
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
        } else if (source == JF.menuAccueil) {
            Variables.VueActive = 5;
        }
        updateMenuConnecte();
        refreshActive();
    }

    /**
     * Affiche le panel pass� en param�tre
     * @param panel
     */
    public void afficherJPanel(JPanel panel) {
        JF.setContentPane(panel);
        JF.refresh();
    }

    /**
     * R�cup�ration des �v�nements li�s � la souris
     * @param e
     */
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
