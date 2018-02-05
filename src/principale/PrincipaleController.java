package principale;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import Client.ClientController;
import Client.ClientVue;
import fournisseur.FournisseurController;
import login.LoginController;
import message.Message;
import variables.Variables;

public class PrincipaleController implements MouseListener {
	
	public final PrincipaleVue JF = new PrincipaleVue();
	
	public final LoginController Login = new LoginController(this);
	public final FournisseurController Fournisseur = new FournisseurController(this);
	public final ClientController Client = new ClientController(this);
	
	public PrincipaleController() {
		JF.setActionListener(this);
		refreshActive();
	}
	
	public void refreshActive() {
		if (Variables.VueActive == 0) Login.setActive();
		else Login.setUnactive();
		if (Variables.VueActive == 2) Fournisseur.setActive();
		else Fournisseur.setUnactive();
		if (Variables.VueActive == 3) Client.setActive();
		else Client.setUnactive();
		
		JF.refresh();
	}
	
	@Override
    public void mouseClicked(MouseEvent e) {
        Object source = e.getSource();
        
        if (Variables.VueActive == 0) {
        	Message.MessageSimple("Connection requise", "Veuillez d'abord vous connecter.");
        	return;
        }

        if (source == JF.menuArticles) {
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

	public static void main(String[] args) {
		new PrincipaleController();
	}

}
