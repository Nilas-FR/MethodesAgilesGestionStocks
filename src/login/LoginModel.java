package login;

import Model.Model;

public class LoginModel extends Model {

    private final LoginDAO loginDAO;

    public LoginModel() {
    	loginDAO = new LoginDAO();
    }

    public boolean VerificationConnection(String login, String pswd) {
        return true;
        //return utilisateurDAO.VerificationConnection(login, pswd); //TODO
    }
}
