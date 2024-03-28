package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class test {

    @Test
    public void testAlgorithmeDijkstra() {
        // Générer une carte
        GenerateurCarte generateur = new GenerateurCarte();
        Carte carte = generateur.genererCarte(10, 10);

        // Définir le point de départ et d'arrivée
        int xDepart = 0;
        int yDepart = 0;
        int xArrivee = 9;
        int yArrivee = 9;

        // Utiliser AlgorithmeDijkstra pour trouver le chemin le plus court
        AlgorithmeDijkstra<Case> algorithme = new AlgorithmeDijkstra<>();
        Chemin chemin = AdaptateurAlgorithme.trouverChemin(algorithme, carte, xDepart, yDepart, xArrivee, yArrivee);

        // Vérifier si le chemin retourné est non null
        assertNotNull(chemin, "Le chemin ne devrait pas être null");

        // Ici, vous pouvez ajouter d'autres assertions pour vérifier si le chemin retourné est correct
    }

    @Test
public void testAlgorithmeAEtoile() {
    // Générer une carte
    GenerateurCarte generateur = new GenerateurCarte();
    Carte carte = generateur.genererCarte(10, 10);

    // Définir les points de départ et d'arrivée
    int xDepart = 0;
    int yDepart = 0;
    int xArrivee = 9;
    int yArrivee = 9;

    // Utiliser AlgorithmeAEtoile pour trouver le chemin le plus court
    AlgorithmeAEtoile<Case> algorithme = new AlgorithmeAEtoile<>();
    Chemin chemin = AdaptateurAlgorithme.trouverChemin(algorithme, carte, xDepart, yDepart, xArrivee, yArrivee);

    // Vérifier si un chemin a été trouvé
    assertNotNull(chemin, "Le chemin ne doit pas être null");
}

 @Test
public void testCreerGraphe() {
    // Créer une carte
    GenerateurCarte generateur = new GenerateurCarte();
    Carte carte = generateur.genererCarte(5, 5); // Créer une carte de taille 5x5

    // Appeler creerGraphe
    Graphe<Case> graphe = AdaptateurAlgorithme.getCreerGraphe(carte);

    // Vérifier que chaque nœud a au moins un voisin (sauf les nœuds sur les bords de la carte)
    for (Noeud<Case> noeud : graphe.getNoeuds()) {
        Case caseValue = noeud.getValeur();
        if (caseValue.getX() > 0 && caseValue.getX() < 4 && caseValue.getY() > 0 && caseValue.getY() < 4) {
            assertTrue(noeud.getVoisins().size() > 0);
        }
    }
}

    @Test
public void testCalculerCout() {
    // Créer deux instances de Case avec des valeurs de Tuile connues
    Case from = new Case(Tuile.DESERT, 0, 0);
    Case to = new Case(Tuile.FORET, 1, 1);

    // Appeler la méthode calculerCout
    double cost = AdaptateurAlgorithme.getCalculerCout(from, to);

    // Vérifier que le coût retourné est comme prévu
    // Le coût attendu dépend de l'implémentation de votre méthode calculerCout
    // Ici, nous supposons que le coût est la somme des pénalités des deux tuiles
    double expectedCost = from.getTuile().getPenalite() + to.getTuile().getPenalite();
    assertEquals(expectedCost, cost, "Le coût doit être la somme des pénalités des deux tuiles");
}

    @Test
    public void testTrouverChemin() {
        // Générer une carte
        Carte carte = GenerateurCarte.genererCarte(10, 10);

        // Définir le point de départ et d'arrivée
        int xDepart = 0;
        int yDepart = 0;
        int xArrivee = 9;
        int yArrivee = 9;

        // Utiliser la méthode trouverChemin
        Chemin chemin = AdaptateurAlgorithme.trouverChemin(new AlgorithmeDijkstra<>(), carte, xDepart, yDepart, xArrivee, yArrivee);

        // Vérifier si un chemin a été trouvé
        assertNotNull(chemin, "Le chemin ne doit pas être null");

        // Vérifier si le premier élément du chemin est le point de départ
        Case caseDepart = chemin.getCases().get(0);
        assertEquals(xDepart, caseDepart.getX(), "Le premier élément du chemin doit être le point de départ (x)");
        assertEquals(yDepart, caseDepart.getY(), "Le premier élément du chemin doit être le point de départ (y)");

        // Vérifier si le dernier élément du chemin est le point d'arrivée
        Case caseArrivee = chemin.getCases().get(chemin.getCases().size() - 1);
        assertEquals(xArrivee, caseArrivee.getX(), "Le dernier élément du chemin doit être le point d'arrivée (x)");
        assertEquals(yArrivee, caseArrivee.getY(), "Le dernier élément du chemin doit être le point d'arrivée (y)");
    }

@Test
public void testAjouterAretesVoisines() {
    // Générer une carte
    Carte carte = GenerateurCarte.genererCarte(5, 5);

    // Créer un graphe à partir de la carte
    Graphe<Case> graphe = AdaptateurAlgorithme.getCreerGraphe(carte);

    // Récupérer un nœud du graphe
    Noeud<Case> noeud = graphe.getNoeud(2, 2);

    // Ajouter des arêtes aux voisins du nœud
    AdaptateurAlgorithme.getAjouterAretesVoisines(graphe, noeud, 2, 2, 5, 5);

    // Vérifier si le nombre de voisins du nœud est correct
    // Les mouvements diagonaux sont autorisés, un nœud peut avoir jusqu'à 8 voisins (haut, bas, gauche, droite, supérieur gauche, supérieur droit, inférieur gauche, inférieur droit).
    assertEquals(8, noeud.getVoisins().size(), "Le nœud doit avoir 8 voisins");

    // Vérifier si les coûts des arêtes sont corrects
    for (Noeud<Case> voisin : noeud.getVoisins()) {
        double cout = AdaptateurAlgorithme.getCalculerCout(noeud.getValeur(), voisin.getValeur());
        assertEquals(cout, graphe.getCout(noeud, voisin), "Le coût de l'arête doit être la somme des pénalités des deux tuiles");
    }
}

}