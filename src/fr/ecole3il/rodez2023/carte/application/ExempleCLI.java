package fr.ecole3il.rodez2023.carte.application;

import fr.ecole3il.rodez2023.carte.AdaptateurAlgorithme;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeChemin;
import fr.ecole3il.rodez2023.carte.chemin.algorithmes.AlgorithmeDijkstra;
import fr.ecole3il.rodez2023.carte.elements.Carte;
import fr.ecole3il.rodez2023.carte.elements.Chemin;
import fr.ecole3il.rodez2023.carte.manipulateurs.GenerateurCarte;

/**
 * Classe ExempleCLI pour démontrer l'utilisation de l'algorithme de chemin.
 */
public class ExempleCLI {

 /**
  * Méthode principale pour exécuter l'exemple.
  *
  * @param args Les arguments de la ligne de commande (non utilisés dans cette application).
  */
 public static void main(String[] args) {
  // Création d'un générateur de carte
  GenerateurCarte generateur = new GenerateurCarte();

  // Génération d'une carte de test
  Carte test = generateur.genererCarte(100,100);

  // Création d'un algorithme de chemin
  AlgorithmeChemin algoChemin = new AlgorithmeDijkstra();

  // Trouver un chemin dans la carte de test
  Chemin chemin = AdaptateurAlgorithme.trouverChemin(algoChemin, test, 0, 0, 50, 50);

  // Afficher le chemin trouvé
  chemin.afficherChemin();

  // Trouver un autre chemin dans la carte de test
  chemin = AdaptateurAlgorithme.trouverChemin(algoChemin, test, 0, 0, 50, 50);

  // Afficher le nouveau chemin trouvé
  chemin.afficherChemin();
 }
}