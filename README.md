# Ce que vous avez réalisé du projet ;

Le projet est une application de cartographie qui génère des cartes aléatoires et trouve le chemin le plus court entre deux points sur la carte. Les cartes sont générées à partir de tuiles qui représentent différents types de terrains, chacun ayant une pénalité de déplacement associée.  

Les éléments clés du projet sont :  
Génération de la carte : La classe GenerateurCarte est utilisée pour générer des cartes aléatoires. Chaque carte est une grille bidimensionnelle de tuiles, où chaque tuile représente un type de terrain (désert, montagnes, plaine, forêt) avec une pénalité de déplacement associée.  
Représentation de la carte : La classe Carte représente une carte, qui est une grille bidimensionnelle de tuiles. La classe Case représente une case sur la carte, qui est caractérisée par une tuile et des coordonnées (x, y).  
Représentation du graphe : La classe Graphe représente un graphe, qui est utilisé pour représenter la carte sous une forme qui peut être utilisée par les algorithmes de recherche de chemin. Chaque nœud du graphe est une case sur la carte.  
Recherche de chemin : Les classes AlgorithmeDijkstra et AlgorithmeAEtoile implémentent les algorithmes de Dijkstra et A* pour trouver le chemin le plus court entre deux points sur la carte. Ces classes implémentent l'interface AlgorithmeChemin, qui définit une méthode trouverChemin.  
Adaptation de l'algorithme : La classe AdaptateurAlgorithme est utilisée pour adapter les algorithmes de recherche de chemin à la représentation de la carte. Elle fournit une méthode trouverChemin qui prend une carte et des coordonnées de départ et d'arrivée, et renvoie un chemin.  
Interface utilisateur : Les classes ExempleCLI et CarteGUI fournissent une interface utilisateur en ligne de commande et graphique, respectivement, pour interagir avec l'application. L'utilisateur peut générer une carte, sélectionner des points de départ et d'arrivée, et trouver le chemin le plus court entre ces points.


# Les réponses aux diverses questions du sujet s'il y en a ;

Quelle structure de données pourrait être utilisée pour stocker les relations entre les nœuds du graphe et les informations associées à ces relations, comme les coûts des arêtes ?

- Nous pouvons utiliser une list (stocker des objet de même type avec des doublon) , une ensemble (la même chose que la list sauf sans doublon) ou association pour stocker les relations entre les nœuds du graphe et les informations associées.

Pourquoi pensez-vous que les classes Noeud et Graphe ont été définies avec des paramètres génériques ?

- On a définie les classes Noeud et Graphe avec des paramètres génériques pour pouvoir les utiliser avec n'importe quel type de données.

Question : Pourquoi pensez-vous que la création d'une interface est une bonne pratique dans ce contexte ?

- La création d'une interface est une bonne pratique dans ce contexte car elle permet de définir un contrat que les classes doivent respecter quand elle les utilises.

# Un retour personnel sur les points qui vous ont paru difficiles ;

Le défi principal résidait dans la reprise du code déjà existant afin de le compléter par la suite. De plus, il était difficile de comprendre les différentes étapes nécessaires pour parvenir à la fin du processus.

J'ai également rencontré un bug qui limitait mes déplacements aux itinéraires en diagonale vers le haut à gauche. Pour déboguer, je ne savais pas d'où pouvait provenir cette erreur. Avec de l'aide, j'ai pu constater qu'une méthode était appelée trop tôt.

# Tout autre commentaire que vous jugerez utile...

NONE