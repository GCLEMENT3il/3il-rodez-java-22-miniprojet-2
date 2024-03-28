package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

/**
 * La classe Graphe représente un graphe générique avec des nœuds de type E.
 * Elle contient une liste de nœuds et une carte d'adjacence pour stocker les arêtes entre les nœuds et leurs coûts.
 *
 * @param <E> Le type d'élément contenu dans les nœuds du graphe.
 */
public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> adjacence;

    /**
     * Récupère la pénalité associée à un nœud.
     * Si la valeur du nœud est une instance de Case, retourne la pénalité de la tuile de la case.
     * Sinon, retourne 0.
     *
     * @param noeud Le nœud dont on veut obtenir la pénalité.
     * @return La pénalité du nœud.
     */
    public int getPenalite(Noeud<E> noeud) {
        if (noeud.getValeur() instanceof Case) {
            Case caseValue = (Case) noeud.getValeur();
            return caseValue.getTuile().getPenalite();
        }
        return 0;
    }

    /**
     * Constructeur par défaut de la classe Graphe.
     * Initialise la liste des nœuds et la carte d'adjacence.
     */
    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new HashMap<>();
    }

    /**
     * Ajoute un nœud au graphe.
     * Si le nœud n'est pas déjà présent dans le graphe, il est ajouté à la liste des nœuds et à la carte d'adjacence.
     *
     * @param noeud Le nœud à ajouter au graphe.
     */
    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            adjacence.put(noeud, new HashMap<>());
        }
    }

    /**
     * Ajoute une arête au graphe entre deux nœuds avec un coût spécifié.
     * Les nœuds de départ et d'arrivée sont ajoutés au graphe si nécessaire.
     * Le coût de l'arête est stocké dans la carte d'adjacence.
     *
     * @param depart Le nœud de départ de l'arête.
     * @param arrivee Le nœud d'arrivée de l'arête.
     * @param cout Le coût de l'arête.
     */
    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).put(arrivee, cout);
    }

    /**
     * Récupère le coût d'une arête entre deux nœuds.
     *
     * @param depart Le nœud de départ de l'arête.
     * @param arrivee Le nœud d'arrivée de l'arête.
     * @return Le coût de l'arête.
     */
    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return adjacence.get(depart).get(arrivee);
    }

    /**
     * Récupère la liste des nœuds du graphe.
     *
     * @return La liste des nœuds du graphe.
     */
    public List<Noeud<E>> getNoeuds() {
        return noeuds;
    }

    /**
     * Récupère la liste des voisins d'un nœud.
     * Si le nœud n'est pas présent dans le graphe, retourne une liste vide.
     *
     * @param noeud Le nœud dont on veut obtenir les voisins.
     * @return La liste des voisins du nœud.
     */
    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adjacence.get(noeud).keySet());
    }

    /**
     * Récupère un nœud du graphe à partir de ses coordonnées.
     * Si aucun nœud correspondant n'est trouvé, retourne null.
     *
     * @param nx La coordonnée x du nœud.
     * @param ny La coordonnée y du nœud.
     * @return Le nœud correspondant aux coordonnées fournies, ou null si aucun nœud correspondant n'est trouvé.
     */
    public Noeud<E> getNoeud(int nx, int ny) {
        // Parcourir tous les nœuds dans le graphe
        for (Noeud<E> noeud : noeuds) {
            // Vérifier si la valeur du nœud est une instance de Case
            if (noeud.getValeur() instanceof Case) {
                // Si oui, récupérer cette valeur et la convertir en Case
                Case caseValue = (Case) noeud.getValeur();
                // Vérifier si les coordonnées de la case correspondent aux coordonnées fournies
                if (caseValue.getX() == nx && caseValue.getY() == ny) {
                    // Si oui, retourner ce nœud
                    return noeud;
                }
            }
        }
        // Si aucun nœud correspondant n'est trouvé, retourner null
        return null;
    }

    /**
     * Récupère le coût d'une arête entre deux nœuds.
     * Si le nœud courant n'existe pas dans la liste d'adjacence ou s'il n'y a pas d'arête entre le nœud courant et le voisin, une exception est lancée.
     *
     * @param noeudCourant Le nœud de départ de l'arête.
     * @param voisin Le nœud d'arrivée de l'arête.
     * @return Le coût de l'arête.
     * @throws IllegalArgumentException Si aucune arête n'existe entre les nœuds fournis.
     */
    public Double getCout(Noeud<E> noeudCourant, Noeud<E> voisin) {
        // Vérifier si le nœud courant existe dans la liste d'adjacence
        if (adjacence.containsKey(noeudCourant) && adjacence.get(noeudCourant).containsKey(voisin)) {
            // Si oui, retourner le coût de l'arête entre le nœud courant et le voisin
            return adjacence.get(noeudCourant).get(voisin);
        } else {
            // Si non, lancer une exception indiquant qu'il n'y a pas d'arête entre les nœuds fournis
            throw new IllegalArgumentException("Aucune arête n'existe entre les nœuds fournis.");
        }
    }
}