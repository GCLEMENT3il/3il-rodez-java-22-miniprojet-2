package fr.ecole3il.rodez2023.carte.chemin.algorithmes;

import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;

import java.util.*;

public class AlgorithmeAEtoile<E> implements AlgorithmeChemin<E> {
    @Override
    public List<Noeud<E>> trouverChemin(Graphe<E> graphe, Noeud<E> depart, Noeud<E> arrivee) {
        // Initialisation des structures de données
        Map<Noeud<E>, Double> couts = new HashMap<>(); // Coût total estimé pour chaque nœud
        Map<Noeud<E>, Noeud<E>> predecesseurs = new HashMap<>(); // Nœud prédécesseur pour chaque nœud
        PriorityQueue<Noeud<E>> filePriorite = new PriorityQueue<>(Comparator.comparingDouble(couts::get)); // File de priorité des nœuds à explorer
        Set<Noeud<E>> explores = new HashSet<>(); // Ensemble des nœuds déjà explorés

        // Initialisation des coûts et des prédécesseurs
        for (Noeud noeud : graphe.getNoeuds()) {
            couts.put(noeud, Double.POSITIVE_INFINITY); // Coût total estimé initialisé à l'infini
            predecesseurs.put(noeud, null); // Prédécesseur initialisé à null
        }
        filePriorite.offer(depart); // Ajout du nœud à la file de priorité


        // Le coût total estimé du nœud de départ est défini à 0
        couts.put(depart, 0.0);

        // Boucle principale de l'algorithme
        while (!filePriorite.isEmpty()) {
            // Sélection du nœud avec le coût total estimé le plus bas
            Noeud<E> noeudCourant = filePriorite.poll();

            // Si le nœud sélectionné est le nœud d'arrivée, arrêtez l'algorithme
            if (noeudCourant.equals(arrivee)) {
                break;
            }

            // Marquage du nœud comme exploré
            explores.add(noeudCourant);

            // Examen des voisins du nœud sélectionné
            for (Noeud<E> voisin : graphe.getVoisins(noeudCourant)) {
                // Si le voisin a déjà été exploré, passez au suivant
                if (explores.contains(voisin)) {
                    continue;
                }

                // Calcul du coût réel pour atteindre ce voisin depuis le nœud sélectionné
                double nouveauCout = couts.get(noeudCourant) + graphe.getCout(noeudCourant, voisin);

                // Si le nouveau coût est inférieur au coût total estimé actuel pour ce voisin
                if (nouveauCout < couts.get(voisin)) {
                    // Mise à jour des coûts et du nœud prédécesseur
                    couts.put(voisin, nouveauCout);
                    predecesseurs.put(voisin, noeudCourant);

                    // Mise à jour de la file de priorité
                    filePriorite.remove(voisin);
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

        // Création et retour de l'objet Chemin
        return chemin;
    }
}