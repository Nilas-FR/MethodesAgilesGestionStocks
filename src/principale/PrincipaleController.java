package principale;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Client.ClientController;
import fournisseur.FournisseurController;
import message.Message;
import variables.Variables;

public class PrincipaleController implements MouseListener {
	
	/** Pointeur vers la vue */
	public final PrincipaleVue JF = new PrincipaleVue();

	/** Pointeur vers le module des fournisseurs */
	public final FournisseurController Fournisseur = new FournisseurController(this);
	/** Pointeur vers le module des clients */
	public final ClientController Client = new ClientController(this);
	
	/** Constructeur */
	public PrincipaleController() {
		JF.setActionListener(this);
		refreshActive();
	}
	
	/** Actualise le panel s�lectionn�*/
	public void refreshActive() {
		if (Variables.VueActive == 2) Fournisseur.setActive();
		else Fournisseur.setUnactive();
		if (Variables.VueActive == 3) Client.setActive();
		else Client.setUnactive();
		
		JF.refresh();
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
        }
        refreshActive();
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
