package fr.ecole3il.rodez2023.carte.test;

import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeAEtoile;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.chemin.elements.Graphe;
import fr.ecole3il.rodez2023.carte.chemin.elements.Noeud;
import fr.ecole3il.rodez2023.carte.elements.Case;
import fr.ecole3il.rodez2023.carte.elements.Tuile;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class test {

@Test
public void testTrouverCheminDijkstra() {
    // Étape 1 : Créer une instance de Graphe et ajouter des nœuds
    Graphe<Case> graphe = new Graphe<>();
    Noeud<Case> nodeA = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
    Noeud<Case> nodeB = new Noeud<>(new Case(Tuile.PLAINE, 1, 0));
    Noeud<Case> nodeC = new Noeud<>(new Case(Tuile.MONTAGNES, 0, 1));
    Noeud<Case> nodeD = new Noeud<>(new Case(Tuile.FORET, 1, 1));

    graphe.ajouterNoeud(nodeA);
    graphe.ajouterNoeud(nodeB);
    graphe.ajouterNoeud(nodeC);
    graphe.ajouterNoeud(nodeD);

    // Étape 2 : Ajouter des arêtes entre les nœuds
    graphe.ajouterArete(nodeA, nodeB, 1.0);
    graphe.ajouterArete(nodeA, nodeC, 1.0);
    graphe.ajouterArete(nodeB, nodeD, 1.0);
    graphe.ajouterArete(nodeC, nodeD, 1.0);

    // Étape 3 : Créer une instance de AlgorithmeDijkstra
    AlgorithmeDijkstra<Case> algorithmeDijkstra = new AlgorithmeDijkstra<>();

    // Étape 4 : Utiliser la méthode trouverChemin pour trouver le chemin le plus court
    List<Noeud<Case>> chemin = algorithmeDijkstra.trouverChemin(graphe, nodeA, nodeD);

    // Étape 5 : Vérifier les propriétés spécifiques du chemin
    assertNotNull(chemin, "Le chemin ne devrait pas être null");
    assertFalse(chemin.isEmpty(), "Le chemin ne devrait pas être vide");
    assertEquals(nodeA, chemin.get(0), "Le premier noeud devrait être le noeud de départ");
    assertEquals(nodeD, chemin.get(chemin.size() - 1), "Le dernier noeud devrait être le noeud d'arrivée");

    // Vérifier que le chemin ne contient pas de nœuds null
    assertFalse(chemin.contains(null), "Le chemin ne devrait pas contenir de nœuds null");

    // Vérifier que le chemin ne contient pas de nœuds répétés
    assertEquals(chemin.size(), new HashSet<>(chemin).size(), "Le chemin ne devrait pas contenir de nœuds répétés");
}

@Test
public void testTrouverCheminAEtoile() {
    // Étape 1 : Créer une instance de Graphe et ajouter des nœuds
    Graphe<Case> graphe = new Graphe<>();
    Noeud<Case> nodeA = new Noeud<>(new Case(Tuile.DESERT, 0, 0));
    Noeud<Case> nodeB = new Noeud<>(new Case(Tuile.PLAINE, 1, 0));
    Noeud<Case> nodeC = new Noeud<>(new Case(Tuile.MONTAGNES, 0, 1));
    Noeud<Case> nodeD = new Noeud<>(new Case(Tuile.FORET, 1, 1));

    graphe.ajouterNoeud(nodeA);
    graphe.ajouterNoeud(nodeB);
    graphe.ajouterNoeud(nodeC);
    graphe.ajouterNoeud(nodeD);

    // Étape 2 : Ajouter des arêtes entre les nœuds
    graphe.ajouterArete(nodeA, nodeB, 1.0);
    graphe.ajouterArete(nodeA, nodeC, 1.0);
    graphe.ajouterArete(nodeB, nodeD, 1.0);
    graphe.ajouterArete(nodeC, nodeD, 1.0);

    // Étape 3 : Créer une instance de AlgorithmeAEtoile
    AlgorithmeAEtoile<Case> algorithmeAEtoile = new AlgorithmeAEtoile<>();

    // Étape 4 : Utiliser la méthode trouverChemin pour trouver le chemin le plus court
    List<Noeud<Case>> chemin = algorithmeAEtoile.trouverChemin(graphe, nodeA, nodeD);

    // Étape 5 : Vérifier les propriétés spécifiques du chemin
    assertNotNull(chemin, "Le chemin ne devrait pas être null");
    assertFalse(chemin.isEmpty(), "Le chemin ne devrait pas être vide");
    assertEquals(nodeA, chemin.get(0), "Le premier noeud devrait être le noeud de départ");
    assertEquals(nodeD, chemin.get(chemin.size() - 1), "Le dernier noeud devrait être le noeud d'arrivée");

    // Vérifier que le chemin ne contient pas de nœuds null
    assertFalse(chemin.contains(null), "Le chemin ne devrait pas contenir de nœuds null");

    // Vérifier que le chemin ne contient pas de nœuds répétés
    assertEquals(chemin.size(), new HashSet<>(chemin).size(), "Le chemin ne devrait pas contenir de nœuds répétés");
}
}