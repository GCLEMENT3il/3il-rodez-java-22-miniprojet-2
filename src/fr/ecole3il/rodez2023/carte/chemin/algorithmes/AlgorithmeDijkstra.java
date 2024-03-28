package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.*;

/**
 * La classe AlgorithmeDijkstra implémente l'interface AlgorithmeChemin.
 * Elle utilise l'algorithme de Dijkstra pour trouver le chemin le plus court entre deux nœuds dans un graphe.
 *
 * @param <E> Le type d'élément contenu dans les nœuds du graphe.
 */
public class AlgorithmeDijkstra<E> implements AlgorithmeChemin<E> {

    /**
     * Méthode principale pour trouver le chemin le plus court entre deux nœuds dans un graphe.
     *
     * @param graphe Le graphe dans lequel chercher le chemin.
     * @param depart Le nœud de départ du chemin.
     * @param arrivee Le nœud d'arrivée du chemin.
     * @return Une liste de nœuds représentant le chemin du nœud de départ au nœud d'arrivée, ou null si aucun chemin n'est trouvé.
     */
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> couts = new HashMap<>(); // actuel pour chaque nœud
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>(); // Prédécesseur pour chaque nœud
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get)); // File de priorité basée sur les coûts

        // Étape 1: Initialisation des coûts et des prédécesseurs
        for (Noeud noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.MAX_VALUE); // Coûts des autres nœuds mis à Max Value
            predecesseurs.put(noeud, null); // Prédécesseur initialisé à null
        }
        filePriorite.offer(depart); // Ajout du nœud de départ à la file de priorité

        couts.put(depart, 0.0);

        // Étape 2: Exploration des nœuds
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudCourant = filePriorite.poll(); // Récupération du nœud ayant le coût minimum
            Case CaseCourant = (Case) noeudCourant.getValeur();
            Case CaseArrivee = (Case) arrivee.getValeur();
            if (CaseCourant.getX() == CaseArrivee.getX() && CaseCourant.getY() == CaseArrivee.getY()){
                break; // Si on a atteint le nœud d'arrivée, on arrête
            }

            // Parcourir les voisins du nœud courant
            for (Noeud<E> voisin : noeudCourant.getVoisins()) {
                double nouveauCout = couts.get(noeudCourant) + graphe.getCout(noeudCourant, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout); // Mise à jour du coût
                    predecesseurs.put(voisin, noeudCourant); // Mise à jour du prédécesseur
                    filePriorite.remove(voisin); // Retrait et réinsertion pour mettre à jour la file de priorité
                    filePriorite.offer(voisin);
                }
            }
        }

        // Étape 3: Reconstruction du chemin le plus court
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin); // Inversion du chemin pour avoir le bon sens

        // Création et retour de l'objet Chemin
        return chemin;
    }
}
