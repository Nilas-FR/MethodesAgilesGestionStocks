package Commande;

import Article.Article;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe Commande
 * @version 1.1
 * */

public class Commande {

	/** 
	 * référence de la commande
	 */
	private int identifiant;

	/**
	 * Liste des articles avec leur quantité
	 */
	private HashMap<Article, Integer> articles;

	/**
	 * Client de la commande
	 */
	//private Client client;
	private int client;

	/**
	 * Date de la commande
	 */
	private java.sql.Timestamp date;

	/**
	 * Constructeur
	 * @param identifiant référence de la commande
	 * @param client client de la commande
	 * @param articles articles de la commande
	 */
	public Commande(int identifiant, int client, HashMap<Article, Integer> articles) {
		this.identifiant = identifiant;
		this.client = client;
		this.articles = articles;
	}

	/**
	 * Constructeur
	 * @param identifiant référence de la commande
	 * @param client client de la commande
	 * @param date date de la commande
	 */
	public Commande(int identifiant, int client, java.sql.Timestamp date) {
		this.identifiant = identifiant;
		this.client = client;
		this.date = date;
		articles = new HashMap<>();
	}

	/**
	 * Constructeur - la référence n'est pas fixée dans le programme
	 * @param client client de la commande
	 * @param articles articles de la commande
	 */
	public Commande(int client, HashMap<Article, Integer> articles) {
		identifiant = 0;
		this.client = client;
		this.articles = articles;
	}

	/**
	 * Constructeur - la référence n'est pas fixée dans le programme
	 * @param client client de la commande
	 */
	public Commande(int client) {
		identifiant = 0;
		this.client = client;
		articles = new HashMap<>();
	}

	/**
	 * getter pour l'attribut identifiant
	 * @return valeur de la identifiant article
	 */
	public int getIdentifiant() {
		return identifiant;
	}

	/**
	 * setter pour l'attribut identifiant
	 * @param identifiant nouvelle valeur de identifiant
	 */
	public void setIdentifiant(int identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * getter pour l'attribut articles
	 * @return liste des articles
	 */
	public HashMap<Article, Integer> getArticles() {
		return articles;
	}

	/**
	 * getter pour l'attribut client
	 * @return valeur de l'id du client
	 */
	public int getClient() {
		return client;
	}

	/**
	 * setter pour l'attribut client
	 * @param client nouvelle valeur de l'id du client
	 */
	public void setClient(int client) {
		this.client = client;
	}

	/**
	 * getter pour l'attribut date
	 * @return valeur de l'id du client
	 */
	public java.sql.Timestamp getDate() {
		return date;
	}

	/**
	 * setter pour l'attribut client
	 * @param client nouvelle valeur de l'id du client
	 */
	public void setDate(java.sql.Timestamp client) {
		this.date = date;
	}

	/**
	 * Ajoute quantite article à la commande, écrase l'ancience valeur si déja existante
	 * @param article article de la commande
	 * @param quantite quantite d'article
	 */
	public void ajouterArticle(Article article, int quantite) {
		articles.put(article, quantite);
	}

	/**
	 * Redéfinition de la méthode toString permettant de définir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		StringBuilder com = new StringBuilder("Commande [réf : " + Integer.toString(identifiant) + " - client " + client + " - articles :");
		for(Map.Entry<Article, Integer> article : articles.entrySet()) {
			com.append("\t ").append(article.getValue()).append(" ").append(article.getKey());
		}

		return com.toString();
	}
}
