package principale2;

import javax.swing.JFrame;

import variables.Variables;

public class PrincipaleVue extends JFrame {
	
	public PrincipaleVue() {
		this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(Variables.EcranLargeurDefaut,Variables.EcranHauteurDefaut);
        this.setVisible(true);
	}
	
	public void setActionListener() {
		
	}
	
	public void refresh() {
		invalidate();
		validate();
		repaint();
	}

}
