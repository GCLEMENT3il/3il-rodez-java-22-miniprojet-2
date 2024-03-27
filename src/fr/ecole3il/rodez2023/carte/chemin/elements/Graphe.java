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
    for (Noeud<E> noeud : noeuds) {
        if (noeud.getValeur() instanceof Case) {
            Case caseValue = (Case) noeud.getValeur();
            if (caseValue.getX() == nx && caseValue.getY() == ny) {
                return noeud;
            }
        }
    }
    return null;
}

 public Double getCout(Noeud<E> noeudCourant, Noeud<E> voisin) {
    if (adjacence.containsKey(noeudCourant) && adjacence.get(noeudCourant).containsKey(voisin)) {
        return adjacence.get(noeudCourant).get(voisin);
    } else {
        throw new IllegalArgumentException("No edge exists between the provided nodes.");
    }
}
}