package Client;

import javax.swing.*;

import principale.PrincipaleVue;
import principale.Vue;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


/**
 * Classe CommandeFenetre
 * Définit et ouvre une fenetre qui :
 *    - Permet l'insertion d'un nouvel article dans la table article via
 * la saisie des valeurs de désignation, prix et quantité en stock
 *    - Permet l'affichage de tous les articles une zone de texte
 *    
 *    Pour aller plus loin : 
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/frame.html
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/panel.html
 *    Différents types de composants graphiques sont disponibles
 *    http://docs.oracle.com/javase/tutorial/uiswing/components/componentlist.html
 *    Sans oublier la référence d'ouvrage utilisée dans le cours "programmer avec Java"
 *    
 * @version 1.2
 * */


public class ClientVue extends Vue {

	/**
	 * numero de version pour classe serialisable
	 * Permet d'eviter le warning "The serializable class CommandeFenetre does not declare a static final serialVersionUID field of type long"
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * zone de texte pour la recherche de client
	 */
	private JTextField textFieldRecherche;

	/**
	 * bouton d'envoi du client
	 */
	public final JButton boutonAjouter = new JButton("Ajouter");

	/**
	 * bouton de recherche du client
	 */
	public final JButton boutonRecherche = new JButton("Rechercher un client selon son nom");

	/**
	 * Zone de texte pour afficher les clients
	 */
	private JPanel pan;

	/**
	 * Liste des boutons associés aux clients pour leur modification
	 */
	private List<JButton> listeBoutonsModifierClient;

	/**
	 * Liste des boutons associés aux clients pour leur suppression
	 */
	private List<JButton> listeBoutonsSupprimerClient;

	/**
	 * Zone de défilement pour la zone de texte
	 */
	private JScrollPane zoneDefilement;

	/**
	 * Panel de modification/ajout du client
	 */
	private ClientCreerOuModifier fenetreCreerOuModifierClient;

	/**
	 * Constructeur
	 * Définit la fenêtre et ses composants - affiche la fenêtre
	 * @param JF JFrame globale de l'application
	 */
	public ClientVue() {
		
		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);
        
        
		//instantiation des  composants graphiques
		textFieldRecherche=new JTextField();

		pan = new JPanel();
		zoneDefilement = new JScrollPane(pan);
		zoneDefilement.setPreferredSize(new Dimension(500, 250));
		
		//ajout des composants sur le container
		JPanel panelRecherche = new JPanel();
		panelRecherche.setOpaque(false);
		add(panelRecherche);

		panelRecherche.setLayout(new GridBagLayout());
		GridBagConstraints left = new GridBagConstraints();
		left.anchor = GridBagConstraints.EAST;
		GridBagConstraints right = new GridBagConstraints();
		right.weightx = 2.0;
		right.fill = GridBagConstraints.HORIZONTAL;
		right.gridwidth = GridBagConstraints.REMAINDER;

		panelRecherche.add(boutonRecherche, left);
		panelRecherche.add(textFieldRecherche, right);

		add(Box.createRigidArea(new Dimension(0,20)));
		add(zoneDefilement);

		add(boutonAjouter);
		add(Box.createRigidArea(new Dimension(0,5)));

		//ajouter une bordure vide de taille constante autour de l'ensemble des composants
		setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

		listeBoutonsModifierClient = new ArrayList<>();
		listeBoutonsSupprimerClient = new ArrayList<>();
	}

	

	/**
	 * Ajoute des écouteurs sur les boutons de la liste des clients
	 * @return valeur du champ de recherche
	 */
	public String getDesignationRecherche() {
		return textFieldRecherche.getText();
	}

	/**
	 * Affiche la vue de modification du client
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 * @param client client sujet à la modification
	 */
	//TODO
	//public void afficherVueModifierClient(ActionListener listener, Client client) {
	//	fenetreCreerOuModifierClient = new ClientCreerOuModifier(JF, client);
	//	fenetreCreerOuModifierClient.ajouterListener(listener);
	//}

	/**
	 * Valider la création d'un client
	 * @return client à entrer dans la base
	 */
	public Client validerCreation() {
		Client client = fenetreCreerOuModifierClient.validerCreation();
		fenetreCreerOuModifierClient = null;
		//fermerFenetreCreationModification();
		return client;
	}

	/**
	 * ferme la fenêtre de modification/création du client et revient sur la page générale des clients
	 */
	//TODO
	//public void fermerFenetreCreationModification() {
	//	fenetreCreerOuModifierClient = null;
	//	JF.setContentPane(this);
	//	JF.pack();
	//}

	/**
	 * Affiche la liste des clients avec leur paramètres ainsi qu'un bouton pour les modifier
	 * @param clients liste des clients à afficher
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void afficherListeClients(List<Client> clients, ActionListener listener) {
		pan.removeAll();
		listeBoutonsModifierClient.clear();
		listeBoutonsSupprimerClient.clear();

		if (clients.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListeClients("Il n'y a aucun client dans la base de données"));
			return;
		}

		pan.setLayout(new GridLayout(clients.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à chaque fois une lineBorder et un texte aligné au centre
		pan.add(creerLabelListeClients("Nom"));
		pan.add(creerLabelListeClients("Prénom"));
		pan.add(creerLabelListeClients("Adresse"));
		pan.add(creerLabelListeClients("Téléphone"));
		pan.add(creerLabelListeClients("Email"));
		pan.add(creerLabelListeClients("Actions"));

		for (Client client : clients) {
			pan.add(creerLabelListeClients(client.getNom()));
			pan.add(creerLabelListeClients(client.getPrenom()));
			pan.add(creerLabelListeClients(client.getAdresse()));
			pan.add(creerLabelListeClients(client.getTelephone()));
			pan.add(creerLabelListeClients(client.getEmail()));

			JPanel conteneurActions = new JPanel();
			conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JButton boutonModif = new JButton("Modifier");
			JButton boutonSuppr = new JButton("Supprimer");
			listeBoutonsModifierClient.add(boutonModif);
			listeBoutonsSupprimerClient.add(boutonSuppr);
			conteneurActions.add(boutonModif);
			conteneurActions.add(boutonSuppr);
			pan.add(conteneurActions);
		}

		ajouterListenerListeClients(listener);
	}

	/**
	 * Créé un JLabel avec le texte passé en paramètre avec une bordure noire et le texte aligné au centre
	 * @param texte texte qui sera placé dans le JLabel
	 * @return JLabel créé
	 */
	public JLabel creerLabelListeClients(String texte) {
		JLabel label = new JLabel(texte);
		label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		label.setHorizontalAlignment(JLabel.CENTER);
		return label;
	}

	/**
	 * Ajoute des écouteurs sur les boutons de modification et de suppression des clients
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void ajouterListenerListeClients(ActionListener listener) {
		for (JButton bouton : listeBoutonsModifierClient) {
			bouton.addActionListener(listener);
		}
		for (JButton bouton : listeBoutonsSupprimerClient) {
			bouton.addActionListener(listener);
		}
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des clients
	 * @return liste des boutons de modification
	 */
	public List<JButton> getListBoutonsModificationClients() {
		return listeBoutonsModifierClient;
	}

	/**
	 * Renvoie la liste des boutons correspondant aux modification des clients
	 * @return liste des boutons de suppression
	 */
	public List<JButton> getListBoutonsSuppressionClients() {
		return listeBoutonsSupprimerClient;
	}

	/**
	 * renvoie la fenêtre de modification/ajout du clien
	 * @return fenêtre de modification/ajout du client
	 */
	public ClientCreerOuModifier getFenetreCreationOuModificationClient() {
		return fenetreCreerOuModifierClient;
	}
	
	/**
	 * Ajoute des écouteurs sur les boutons du panel
	 * @param listener écouteurs à placer sur les boutons de la fenêtre
	 */
	public void ajouterListener(ActionListener listener) {
		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);
	}
	
	public void setActive(JFrame JF) {
		JF.setTitle("Client");
		JF.add(this);
	}
}
