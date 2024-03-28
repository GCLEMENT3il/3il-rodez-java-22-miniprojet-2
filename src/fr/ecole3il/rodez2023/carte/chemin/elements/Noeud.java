package fr.ecole3il.rodez2023.carte.chemin.elements;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe Noeud représente un noeud dans un graphe.
 * Chaque noeud a une valeur de type générique E et une liste de noeuds voisins.
 *
 * @param <E> Le type d'élément contenu dans le noeud.
 */
public class Noeud<E> {
    private E valeur; // La valeur contenue dans le noeud
    private List<Noeud<E>> voisins; // La liste des noeuds voisins

    /**
     * Construit un nouveau noeud avec la valeur spécifiée.
     * Initialise la liste des voisins comme une nouvelle liste vide.
     *
     * @param valeur La valeur à stocker dans le noeud.
     */
    public Noeud(E valeur) {
        this.valeur = valeur;
        this.voisins = new ArrayList<>();
    }

    /**
     * Récupère la valeur contenue dans le noeud.
     *
     * @return La valeur contenue dans le noeud.
     */
    public E getValeur() {
        return valeur;
    }

    /**
     * Récupère la liste des noeuds voisins.
     *
     * @return La liste des noeuds voisins.
     */
    public List<Noeud<E>> getVoisins() {
        return voisins;
    }

    /**
     * Ajoute un noeud à la liste des voisins.
     *
     * @param voisin Le noeud à ajouter à la liste des voisins.
     */
    public void ajouterVoisin(Noeud<E> voisin) {
        this.voisins.add(voisin);
    }
}