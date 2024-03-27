package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

public class AdaptateurAlgorithme {

public static Chemin trouverChemin(AlgorithmeChemin<Case> algorithme, Carte carte, int xDepart, int yDepart, int xArrivee, int yArrivee) {
    // Crée un graphe à partir de la carte donnée
    Graphe<Case> graphe = creerGraphe(carte);

    // Récupère le nœud de départ à partir des coordonnées de départ fournies
    Noeud<Case> depart = graphe.getNoeud(xDepart, yDepart);

    // Récupère le nœud d'arrivée à partir des coordonnées d'arrivée fournies
    Noeud<Case> arrivee = graphe.getNoeud(xArrivee, yArrivee);

    // Utilise l'algorithme fourni pour trouver le chemin entre le nœud de départ et le nœud d'arrivée
    List<Noeud<Case>> noeudsChemin = algorithme.trouverChemin(graphe, depart, arrivee);

    // Si aucun chemin n'a été trouvé (c'est-à-dire si la liste des nœuds du chemin est vide ou null), retourne un chemin vide
    if (noeudsChemin == null || noeudsChemin.isEmpty()) {
        return new Chemin(new ArrayList<>());
    }

    // Sinon, convertit la liste des nœuds du chemin en une liste de cases et retourne le chemin
    return new Chemin(afficherChemin(noeudsChemin));
}

    private static Graphe<Case> creerGraphe(Carte carte) {
        Graphe<Case> graphe = new Graphe<>();
        int largeur = carte.getLargeur();
        int hauteur = carte.getHauteur();
        for (int x = 0; x < largeur; x++) { // Crée un nœud pour chaque case de la carte
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = new Noeud<>(carte.getCase(x, y));
                graphe.ajouterNoeud(currentNoeud);
            }
        }
        for (int x = 0; x < largeur; x++) { // Ajoute des arêtes entre les nœuds voisins
            for (int y = 0; y < hauteur; y++) {
                Noeud<Case> currentNoeud = graphe.getNoeud(x, y);
                ajouterAretesVoisines(graphe, currentNoeud, x, y, largeur, hauteur); // Appelle la méthode pour ajouter les arêtes voisines
            }
        }
        return graphe;
    }

    private static void ajouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> currentNoeud, int x, int y, int largeur, int hauteur) {
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < largeur && ny >= 0 && ny < hauteur) {
                Noeud<Case> voisinNoeud = graphe.getNoeud(nx, ny);
                if (voisinNoeud != null) {
                    double cout = calculerCout(currentNoeud.getValeur(), voisinNoeud.getValeur());
                    graphe.ajouterArete(currentNoeud, voisinNoeud, cout);
                    currentNoeud.ajouterVoisin(voisinNoeud);
                }
            }
        }
    }

    private static double calculerCout(Case from, Case to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les cases 'de' et 'a' doivent être non nulles");
        }
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    private static List<Case> afficherChemin(List<Noeud<Case>> noeudsChemin) {
        List<Case> chemin = new ArrayList<>();
        for (Noeud<Case> noeud : noeudsChemin) {
            chemin.add(noeud.getValeur());
        }
        return chemin;
    }
}