package client;

import javax.swing.*;

import principale.Vue;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class ClientVue extends Vue {

	/**
	 * Zone de texte pour afficher les clients
	 */
	private final JPanel pan = new JPanel();

	/**
	 * Constructeur
	 * Définit la fenêtre et ses composants - affiche la fenêtre
	 */
	public ClientVue(ActionListener listener) {
		//choix du Layout pour ce conteneur
		//il permet de gérer la position des éléments
		//il autorisera un retaillage de la fenêtre en conservant la présentation
		//BoxLayout permet par exemple de positionner les élements sur une colonne ( PAGE_AXIS )
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		
		//choix de la couleur pour le conteneur
        setBackground(Color.LIGHT_GRAY);
        
        
		//instantiation des  composants graphiques
		textFieldRecherche=new JTextField();

		JScrollPane zoneDefilement = new JScrollPane(pan);
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

		listeBoutonsModifier = new ArrayList<>();
		listeBoutonsSupprimer = new ArrayList<>();

		// ajoute les écouteurs sur les boutons
		boutonAjouter.addActionListener(listener);
		boutonRecherche.addActionListener(listener);

        boutonRecherche.setText("Recherche un client selon son nom");
	}

	/**
	 * Affiche la liste des clients avec leur paramètres ainsi qu'un bouton pour les modifier
	 * @param liste liste des clients à  afficher
	 * @param listener écouteurs à  placer sur les boutons de la fenêtre
	 */
	@Override
	public void afficherListe(List liste, ActionListener listener) {
		List<Client> clients = liste;
		pan.removeAll();
		listeBoutonsModifier.clear();
		listeBoutonsSupprimer.clear();

		if (clients.isEmpty()) {
			pan.setLayout(new GridLayout(1,1));
			pan.add(creerLabelListe("Il n'y a aucun client dans la base de données"));
			return;
		}

		pan.setLayout(new GridLayout(clients.size()+1,1));
		pan.setBorder(BorderFactory.createEmptyBorder(1,1,1,1));

		// créé tous les labels avec à  chaque fois une lineBorder et un texte aligné au centre
		pan.add(creerLabelListe("Nom"));
		pan.add(creerLabelListe("Prénom"));
		pan.add(creerLabelListe("Adresse"));
		pan.add(creerLabelListe("Téléphone"));
		pan.add(creerLabelListe("Email"));
		pan.add(creerLabelListe("Actions"));

		for (Client client : clients) {
			pan.add(creerLabelListe(client.getNom()));
			pan.add(creerLabelListe(client.getPrenom()));
			pan.add(creerLabelListe(client.getAdresse()));
			pan.add(creerLabelListe(client.getTelephone()));
			pan.add(creerLabelListe(client.getEmail()));

			JPanel conteneurActions = new JPanel();
			conteneurActions.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			JButton boutonModif = new JButton("Modifier");
			boutonModif.addActionListener(listener);
			JButton boutonSuppr = new JButton("Supprimer");
			boutonSuppr.addActionListener(listener);
			listeBoutonsModifier.add(boutonModif);
			listeBoutonsSupprimer.add(boutonSuppr);
			conteneurActions.add(boutonModif);
			conteneurActions.add(boutonSuppr);
			pan.add(conteneurActions);
		}
	}
}
