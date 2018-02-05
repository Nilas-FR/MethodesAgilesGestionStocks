package login;

public class LoginModel {

    private final LoginDAO loginDAO;

    /** Constructeur */
    public LoginModel() {
    	loginDAO = new LoginDAO();
    }

    /**Vérifie si l'utilisateur existe dans la base de données
	 * @param login Identifiant de l'utilisateur
	 * @param pswd Mot de passe de l'utilisateur
	 * @return vrai si l'utilisateur existe dans la base de données
	 */
    public boolean VerificationConnection(String login, String pswd) {
        return loginDAO.VerificationConnection(login, pswd);
    }
}
