package principale2;

import variables.Variables;
import login2.LoginController;

public class PrincipaleController {
	
	public final PrincipaleVue JF = new PrincipaleVue();
	
	public final LoginController Login = new LoginController(this);
	
	public PrincipaleController() {
		refreshActive();
	}
	
	public void refreshActive() {
		if (Variables.VueActive == 0) Login.setActive();
		else Login.setUnactive();
		JF.refresh();
	}

	public static void main(String[] args) {
		new PrincipaleController();
	}

}
