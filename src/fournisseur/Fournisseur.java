package fournisseur;

public class Fournisseur {

	/** Numéro SIRET du fournisseur, unique */
	public final String Siret;
	/** Nom du fournisseur */
	public final String Nom;
	/** Adresse du fournisseur */
	public final String Adresse;
	
	/** Constructeur d'un fournisseur
	 * @param Siret Numéro SIRET du fournisseur, unique
	 * @param Nom Nom du fournisseur
	 * @param Adresse Adresse du fournisseur
	 */
	public Fournisseur(String Siret, String Nom, String Adresse) {
		this.Siret 	= Siret;
		this.Nom 	= Nom;
		this.Adresse= Adresse;
	}
}
