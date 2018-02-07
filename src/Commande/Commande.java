package commande;

import article.Article;
import client.Client;

import java.util.HashMap;
import java.util.Map;

/**
 * Classe commande
 */

public class Commande {

	/** 
	 * identifiant de la commande
	 */
	private int identifiant;

	/**
	 * Liste des articles avec leur quantit�
	 */
	private HashMap<Article, Integer> articles;

	/**
	 * Client de la commande
	 */
	private Client client;

	/**
	 * Date de la commande
	 */
	private java.sql.Timestamp date;

	/**
	 * Constructeur
	 * @param identifiant r�f�rence de la commande
	 * @param client client de la commande
	 * @param articles articles de la commande
	 */
	public Commande(int identifiant, Client client, HashMap<Article, Integer> articles) {
		this.identifiant = identifiant;
		this.client = client;
		this.articles = articles;
	}

	/**
	 * Constructeur
	 * @param identifiant r�f�rence de la commande
	 * @param client client de la commande
	 * @param date date de la commande
	 */
	public Commande(int identifiant, Client client, java.sql.Timestamp date) {
		this.identifiant = identifiant;
		this.client = client;
		this.date = date;
		articles = new HashMap<>();
	}

	/**
	 * Constructeur - la r�f�rence n'est pas fix�e dans le programme
	 * @param client client de la commande
	 * @param articles articles de la commande
	 */
	public Commande(Client client, HashMap<Article, Integer> articles) {
		identifiant = 0;
		this.client = client;
		this.articles = articles;
	}

	/**
	 * Constructeur - la r�f�rence n'est pas fix�e dans le programme
	 * @param client client de la commande
	 */
	public Commande(Client client) {
		identifiant = 0;
		this.client = client;
		articles = new HashMap<>();
	}

	/**
	 * Constructeur - seul l'identifiant est enregistr�, il sert pour supprimer les commandes d'un client que l'on veut lui m�me supprimer
	 * @param identifiant identifiant de la commande
	 */
	public Commande(int identifiant) {
		this.identifiant = identifiant;
	}

	/**
	 * Constructeur - commande vide
	 */
	public Commande() {
		identifiant = 0;
		this.client = null;
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
	 * @return liste des articles avec leur quantit�
	 */
	public HashMap<Article, Integer> getArticles() {
		return articles;
	}

	/**
	 * getter pour l'attribut client
	 * @return valeur de l'id du client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * setter pour l'attribut client
	 * @param client nouvelle valeur de l'id du client
	 */
	public void setClient(Client client) {
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
	 * Ajoute quantite article � la commande, �crase l'ancience valeur si d�ja existante
	 * @param article article de la commande
	 * @param quantite quantite d'article
	 */
	public void ajouterArticle(Article article, int quantite) {
		for(Map.Entry<Article, Integer> art : articles.entrySet()) {
			// si article d�ja dans le hashmap
			if (article.getReference() == art.getKey().getReference()) {
				articles.put(art.getKey(), quantite);
				return;
			}
		}
		articles.put(article, quantite);
	}

	/**
	 * Supprime l'article de la liste
	 * @param article article � supprimer
	 */
	public void supprimerArticle(Article article) {
		articles.remove(article);
	}

	/**
	 * Red�finition de la m�thode toString permettant de d�finir la traduction de l'objet en String
	 * pour l'affichage par exemple
	 */
	@Override
	public String toString() {
		StringBuilder com = new StringBuilder("commande [r�f : " + Integer.toString(identifiant) + " - client " + client + " - articles :");
		for(Map.Entry<Article, Integer> article : articles.entrySet()) {
			com.append("\t ").append(article.getValue()).append(" ").append(article.getKey());
		}

		return com.toString();
	}
}
