package fr.ecole3il.rodez2023.carte;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe AdaptateurAlgorithme fournit des méthodes pour adapter les algorithmes de recherche de chemin à la structure de données de la carte.
 */
public class AdaptateurAlgorithme {

    /**
     * Trouve un chemin entre deux points sur une carte en utilisant un algorithme de recherche de chemin.
     *
     * @param algorithme L'algorithme de recherche de chemin à utiliser.
     * @param carte La carte sur laquelle chercher le chemin.
     * @param xDepart La coordonnée x du point de départ.
     * @param yDepart La coordonnée y du point de départ.
     * @param xArrivee La coordonnée x du point d'arrivée.
     * @param yArrivee La coordonnée y du point d'arrivée.
     * @return Un chemin entre le point de départ et le point d'arrivée, ou un chemin vide si aucun chemin n'a été trouvé.
     */
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

    /**
     * Crée un graphe à partir d'une carte.
     *
     * @param carte La carte à partir de laquelle créer le graphe.
     * @return Un graphe représentant la carte.
     */
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

    /**
     * Ajoute des arêtes entre un nœud et ses voisins dans un graphe.
     *
     * @param graphe Le graphe dans lequel ajouter les arêtes.
     * @param currentNoeud Le nœud pour lequel ajouter les arêtes.
     * @param x La coordonnée x du nœud.
     * @param y La coordonnée y du nœud.
     * @param largeur La largeur du graphe.
     * @param hauteur La hauteur du graphe.
     */
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

    /**
     * Calcule le coût pour se déplacer d'une case à une autre.
     *
     * @param from La case de départ.
     * @param to La case d'arrivée.
     * @return Le coût pour se déplacer de la case de départ à la case d'arrivée.
     */
    private static double calculerCout(Case from, Case to) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Les cases 'de' et 'a' doivent être non nulles");
        }
        return from.getTuile().getPenalite() + to.getTuile().getPenalite();
    }

    /**
     * Affiche un chemin en convertissant une liste de nœuds en une liste de cases.
     *
     * @param noeudsChemin La liste de nœuds représentant le chemin.
     * @return Une liste de cases représentant le chemin.
     */
    private static List<Case> afficherChemin(List<Noeud<Case>> noeudsChemin) {
        List<Case> chemin = new ArrayList<>();
        for (Noeud<Case> noeud : noeudsChemin) {
            chemin.add(noeud.getValeur());
        }
        return chemin;
    }



    public static double getCalculerCout(Case from, Case to) {
        return calculerCout(from, to);
    }

    public static void getAjouterAretesVoisines(Graphe<Case> graphe, Noeud<Case> noeud, int x, int y, int largeur, int hauteur) {
        ajouterAretesVoisines(graphe, noeud, x, y, largeur, hauteur);
    }

    public static Graphe<Case> getCreerGraphe(Carte carte) {
        return creerGraphe(carte);
    }
}