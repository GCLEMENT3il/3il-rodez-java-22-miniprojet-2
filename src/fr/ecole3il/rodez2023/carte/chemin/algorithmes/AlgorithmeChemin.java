package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.List;

/**
 * L'interface AlgorithmeChemin définit le contrat pour les algorithmes de recherche de chemin.
 * Elle spécifie une méthode pour trouver un chemin entre deux nœuds dans un graphe.
 *
 * @param <E> Le type d'élément contenu dans les nœuds du graphe.
 */
public interface AlgorithmeChemin<E> {

    /**
     * Trouve un chemin entre le nœud de départ et le nœud d'arrivée dans le graphe spécifié.
     *
     * @param graphe Le graphe dans lequel chercher le chemin.
     * @param depart Le nœud de départ du chemin.
     * @param arrivee Le nœud d'arrivée du chemin.
     * @return Une liste de nœuds représentant le chemin du nœud de départ au nœud d'arrivée, ou null si aucun chemin n'est trouvé.
     */
    List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee);
}