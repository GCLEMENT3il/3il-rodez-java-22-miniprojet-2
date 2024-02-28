package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Graphe<E> {
    private Map<Noeud<E>, List<Noeud<E>>> matriceAdjacence;

    public Graphe() {
        matriceAdjacence = new HashMap<Noeud<E>, List<Noeud<E>>>();
    }

    public void ajouterNoeud(Noeud<E> noeud) {
        if (!matriceAdjacence.containsKey(noeud)) {
            matriceAdjacence.put(noeud, new ArrayList<Noeud<E>>());
        }
    }

    public void ajouterArete(Noeud<E> depart, Noeud<E> arrivee, double cout) {
        ajouterNoeud(depart);
        ajouterNoeud(arrivee);
        matriceAdjacence.get(depart).add(arrivee);
        // Faire l'ajoutez pour stocker le coût de l'arête, par exemple dans une autre matrice ou une map.
    }

    public double getCoutArete(Noeud<E> depart, Noeud<E> arrivee) {
        return 0.0; //Remplacez par la écupérer du coût de l'arête.
    }

    public List<Noeud<E>> getNoeuds() {
        return new ArrayList<Noeud<E>>(matriceAdjacence.keySet());
    }

    public List<Noeud<E>> getVoisins(Noeud<E> noeud) {
        if (matriceAdjacence.containsKey(noeud)) {
            return matriceAdjacence.get(noeud);
        } else {
            return new ArrayList<Noeud<E>>();
        }
    }
}