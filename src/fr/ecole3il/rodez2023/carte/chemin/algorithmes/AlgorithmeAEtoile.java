package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation
        Map<Noeud<E>, Double> couts = new HashMap<>(); // Coût total estimé pour chaque nœud
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>(); // Prédécesseur pour chaque nœud
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get)); // File de priorité basée sur les coûts
        Set<Noeud<E>> explores = new HashSet<>(); // Ensemble des nœuds déjà explorés

        // Initialisation des coûts
        for (Noeud noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY); // Coûts des autres nœuds mis à l'infini
            predecesseurs.put(noeud, null); // Prédécesseur initialisé à null
            filePriorite.offer(noeud); // Ajout du nœud à la file de priorité
        }

        couts.put(depart, 0.0);

        // Boucle principale
        while (!filePriorite.isEmpty()) {
            Noeud<E> noeudCourant = filePriorite.poll(); // Récupération du nœud ayant le coût minimum
            if (noeudCourant.equals(arrivee)) {
                break; // Si on a atteint le nœud d'arrivée, on arrête
            }

            explores.add(noeudCourant); // Marquer le nœud comme exploré

            // Parcourir les voisins du nœud courant
            for (Noeud<E> voisin : graphe.getVoisins(noeudCourant)) {
                if (explores.contains(voisin)) {
                    continue; // Ignorer les voisins déjà explorés
                }

                double nouveauCout = couts.get(noeudCourant) + graphe.getCout(noeudCourant, voisin);
                if (nouveauCout < couts.get(voisin)) {
                    couts.put(voisin, nouveauCout); // Mise à jour du coût
                    predecesseurs.put(voisin, noeudCourant); // Mise à jour du prédécesseur
                    filePriorite.remove(voisin); // Retrait et réinsertion pour mettre à jour la file de priorité
                    filePriorite.offer(voisin);
                }
            }
        }

        // Reconstruction du chemin
        List<Noeud<E>> chemin = new ArrayList<>();
        Noeud<E> noeud = arrivee;
        while (noeud != null) {
            chemin.add(noeud);
            noeud = predecesseurs.get(noeud);
        }
        Collections.reverse(chemin); // Inversion du chemin pour avoir le bon sens

        return chemin;
    }
}