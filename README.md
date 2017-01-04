# Connectr : Jeu Puissance 4

## Getting Started

### Dépendances

   Les dépendances nécessaires :

   ```
   Java SE Runtime Environment 8 ou OpenJDK 8
   ```

cf. car quelques fonctionnalités de Java 8 (fonctions lambdas, approche fonctionnelle)

### Installing

- décompresser l'archive

- executer la commande :

```
 java -jar jeu.jar
```

# Fonctionnalités implémentées

Fonctionnalités de base implémentées :
* interface terminal
* gestionnaire de partie
* joueurs
* historique de partie
* gestion des erreurs

Fonctionnalités supplémentaires implémentées
* grille de taille arbitraire
* nombre de manche à gagner afin de gagner la partie
* interface en mode fenêtre (avec Swing)
* architecture de code permettant de mettre un nombre de joueurs arbitraire par la suite (structure liste circulaire qui nous permet de faire le tour de plusieurs joueurs à la suite, tokenSet qui est l'ensemble de token disponibles...etc), non implémentée totalement car puissance 4 se joue à 2 joueurs sur la grille.

On pourrait permettre les joueurs d'intéragir avec le terminal pour les activer. Cependant, les spécifications du sujet ne mentionnaient pas les entrées terminal pour y accéder, ainsi afin de pas fausser les tests automatiques, on les active manuellement par :
le changement des initialisations de départ dans "game.java" (par exemple pour l'interface graphique : décommenter "this.ui = new GUI()" dans le constructeur de game, idem pour la configuration du nombre de manches...etc)

Quelques remarques en plus :
- utilisation de java8 : streams, fonctions lambda, approche fonctionnel (par exemple pour l'attribution des évènements dans le GUI : parcours stream de la grid pour pouvoir appliquer des fonctions anonymes lambdas)
- utilisation du design pattern "Strategy Pattern" pour la définition des IAs/Behaviors. En effet, cela permet de définir des comportements et pouvoir changer de comportements en direct dans le jeu par exemple.
Cette architecture à long terme permet ainsi d'étendre plus tard très facilement le catalogue des IAs (par exemple rajouter une IA basée sur le minimax algorithme...etc) et de faire du "hotswapping" de comportements.


## Construit avec :

* [IntelliJ IDEA the Java IDE](https://www.jetbrains.com/idea/) - IDE