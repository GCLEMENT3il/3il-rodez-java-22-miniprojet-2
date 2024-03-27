package fr.ecole3il.rodez2023.carte.chemin.elements;

import fr.ecole3il.rodez2023.carte.elements.Case;

import java.util.*;

public class Graphe<E> {
    private List<Noeud<E>> noeuds;
    private Map<Noeud<E>, Map<Noeud<E>, Double>> adjacence;

    public int getPenalite(Noeud<E> noeud) {
        if (noeud.getValeur() instanceof Case) {
            Case caseValue = (Case) noeud.getValeur();
            return caseValue.getTuile().getPenalite();
        }
        return 0;
    }

    public Graphe() {
        this.noeuds = new ArrayList<>();
        this.adjacence = new HashMap<>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!noeuds.contains(noeud)) {
            noeuds.add(noeud);
            adjacence.put(noeud, new HashMap<>());
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        adjacence.get(depart).put(arrivee, cout);
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return adjacence.get(depart).get(arrivee);
    }

    public List<Noeud<E>> getNoeuds() {
        return noeuds;
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (!adjacence.containsKey(noeud)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(adjacence.get(noeud).keySet());
    }

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