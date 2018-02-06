package client;

/**
 * Classe client
 */

public class Client {

	/** 
	 * identifiant du client
	 */
	private int identifiant;

	/**
	 * nom
	 */
	private String nom;

	/**
	 * prénom
	 */
	private String prenom;

	/**
	 * adresse
	 */
	private String adresse;

	/**
	 * numéro de téléphone
	 */
	private String telephone;

	/**
	 * adresse email
	 */
	private String email;

	/**
	 * Constructeur
	 * @param identifiant identifiant du client
	 * @param nom nom du client
	 * @param prenom prénom du client
	 * @param adresse adresse du client
	 * @param telephone numéro de téléphone du client
	 * @param email adresse email du client
	 */
	public Client(int identifiant, String nom, String prenom, String adresse, String telephone, String email) {
		this.identifiant=identifiant;
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
	}

	/**
	 * Constructeur - l'identifiant n'est pas fixé dans le programme
	 * @param nom nom du client
	 * @param prenom prénom du client
	 * @param adresse adresse du client
	 * @param telephone numéro de téléphone du client
	 * @param email adresse email du client
	 */
	public Client(String nom, String prenom, String adresse, String telephone, String email) {
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.telephone = telephone;
		this.email = email;
	}

	/**
	 * getter pour l'attribut identifiant
	 * @return valeur de l'identifiant client
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * getter pour l'attribut nom
	 * @return valeur du nom du client
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * setter  pour l'attribut nom
	 * @param nom : nouvelle valeur du nom du client
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * getter pour l'attribut prenom
	 * @return valeur du prenom du client
	 */
	public String getPrenom() {
		return prenom;
	}

	/**
	 * setter  pour l'attribut prenom
	 * @param prenom : nouvelle valeur du prenom du client
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * getter pour l'attribut adresse
	 * @return valeur de l'adresse du client
	 */
	public String getAdresse() {
		return adresse;
	}

	/**
	 * setter  pour l'attribut adresse
	 * @param adresse : nouvelle valeur de l'adresse du client
	 */
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	/**
	 * getter pour l'attribut telephone
	 * @return valeur du telephone du client
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * setter  pour l'attribut telephone
	 * @param telephone : nouvelle valeur du telephone du client
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * getter pour l'attribut email
	 * @return valeur de l'email du client
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * setter  pour l'attribut email
	 * @param email : nouvelle valeur de l'email du client
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		return nom + " " + prenom;
	}
}
