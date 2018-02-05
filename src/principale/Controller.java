package principale;

public class Controller {
	
	/** Un pointeur vers le controller principale */
	protected PrincipaleController PC;
	
	/** Un pointeur vers la vue du module */
	protected Vue Vue;
	
	/**Constructeur
	 * @param PC controller principale
	 */
	public Controller(PrincipaleController PC) {
		this.PC = PC;
	}
	
	/** Ce module est maintenant actif */
	public void setActive() {
		Vue.setActive(PC.JF);
	}
	
	/** Ce module est maintenant inactif */
	public void setUnactive() {
		Vue.setUnactive(PC.JF);
	}

}
