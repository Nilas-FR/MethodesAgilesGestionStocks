package variables;

public class Variables {

	/** Information localisation de la base de données */
	public final static String URL		= "jdbc:mysql://localhost/stocks";
	/** Information login de la base de données */
	public final static String LOGIN	= "root";
	/** Information mot de passe de la base de données */
	public final static String PASS		= "";
	
	/** Niveau de droit de l'utilisateur courant
	 *  -1, non connecté
	 *   0, spectateur
	 *   1, utilisateur*/
	public static int Droit = -1;
	
	/** Vue actuelle
	 *  -1, Aucune
	 *   0, Login
	 *	 1, Article   
	 *   */
	public static int VueActive = 0;
}
