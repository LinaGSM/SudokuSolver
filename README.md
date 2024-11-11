# SudokuSolver

## Table of Contents
1. [Introduction](#introduction)
2. [Fonctionnalités](#fonctionnalités)
3. [Structure du Projet](#structure-du-projet)
4. [Installation](#installation)
5. [Format du fichier d'entrée](#format-du-fichier-dentrée)
6. [Compilation](#compilation)
7. [Autrices](#autrices)

## Introduction

L'objectif du **SudokuSolver** est de développer un solveur de Sudoku qui tente de résoudre une grille de Sudoku en appliquant un ensemble de règles déductives. Au moins trois règles de déduction doivent être définies et utilisées. Ces trois règles sont DR1, DR2 et DR3.

L'algorithme de déduction utilisé fonctionne comme suit :
  - La première règle (**DR1**) est appliquée à la grille.
  - Si la grille n'est pas encore complètement remplie, la deuxième règle (**DR2**) est appliquée.
  - Si la grille reste incomplète, la troisième règle (**DR3**) est appliquée.
  - Si la grille n'est toujours pas remplie, l'utilisateur est invité à entrer une valeur dans une cellule vide pour débloquer la situation.
  - Le processus est répété jusqu'à ce que la grille soit entièrement résolue. Si une incohérence est détectée lors de la résolution, un message demandant de redémarrer le solveur est affiché.

## Fonctionnalités
- Résolution automatique d'une grille de Sudoku à l'aide de règles de déduction.
- Interaction avec l'utilisateur pour compléter manuellement les cellules en cas de blocage.
- Évaluation de la difficulté d'un Sudoku en fonction des règles utilisées :
  - **Facile** : Résolu uniquement avec **DR1**.
  - **Moyen** : Résolu avec **DR2** après **DR1**.
  - **Difficile** : Résolu avec **DR3** après **DR2**.

## Structure du Projet

Le code principal du projet se trouve dans le répertoire src. La structure est la suivante :

```plaintext
src/
├── BlockIterator.java
├── BoardIterator.java
├── Cell.java
├── CollectionIterator.java
├── ColumnIterator.java
├── DeductionRule.java
├── DeductiveRuleHandler.java
├── Difficulty.java
├── DR1.java
├── DR2.java
├── DR3.java
├── IteratorType.java
├── Main.java
├── RowIterator.java
├── RuleManager.java
├── SudokuBoard.java
├── test (fichier texte contenant le Sudoku initial)
└── UserInputHandler.java
```



Chaque fichier Java a une fonction spécifique dans le projet :

- **Main.java** : Le point d'entrée de l'application, responsable de charger le Sudoku et de lancer la résolution.
- **SudokuBoard.java** : Modélise la grille de Sudoku, incluant les méthodes pour gérer et afficher la grille.
- **DeductionRule.java** : Classe abstraite pour définir le comportement général des règles de déduction.
- **DR1.java**, **DR2.java**, **DR3.java** : Les trois implémentations des règles de déduction, chacune représentant une technique différente pour résoudre le Sudoku.
- **RuleManager.java** : Coordonne l'application des règles de déduction et gère les interactions avec l'utilisateur si le Sudoku n'est pas résolu automatiquement.
- **UserInputHandler.java** : Gère les saisies de l'utilisateur lorsqu'il est nécessaire de débloquer la résolution en entrant manuellement une valeur.
- **BlockIterator.java**, **RowIterator.java**, **ColumnIterator.java**, **BoardIterator.java** : Itérateurs utilisés pour parcourir respectivement les blocs, les lignes, les colonnes et la grille de Sudoku.


## Installation

### Prerequis
- **Java** (JDK 8 ou supérieur) doit être installé sur votre système.
- **Git**
- Un terminal ou un IDE comme IntelliJ IDEA pour exécuter le programme.
- Un fichier d'entrée contenant une grille de Sudoku dans le format spécifié (voir section "Format du fichier d'entrée").

### Cloner le dépôt
Pour cloner le projet depuis GitHub, utilisez la commande suivante :
```bash
git clone https://github.com/LinaGSM/SudokuSolver
```

## Format du fichier d'entrée
Le fichier d'entrée est un fichier texte nommé "test" situé dans le répertoire "src". Vous pouvez y écrire la grille de Sudoku à résoudre selon le format suivant :
- Chaque ligne représente une ligne de la grille.
- Les valeurs sont séparées par des virgules, sans espaces.
- 0 représente une cellule vide.

**Exemple de contenu pour le fichier test** :
```
3,8,0,1,0,0,5,9,0
0,0,5,0,9,0,0,0,1
1,0,9,0,0,5,6,0,3
0,0,0,9,0,0,0,0,0
0,0,3,0,5,0,9,0,0
0,0,0,0,0,8,0,0,0
2,0,1,8,0,0,3,0,9
9,0,0,0,3,0,1,0,0
0,3,8,0,0,1,0,5,7
```

## Instructions d'utilisation
### Compilation
Pour compiler le projet, suivez ces étapes dans un terminal :
1. Ouvrir le répertoire `src` :
   ```bash
   cd src
   ```
2. Supprimer les fichiers .class existants :
   ```bash
   rm *.class
   ```
3. Compiler le programme
   ```bash
   javac Main.java
   ```
4. Exécuter le programme
   ```bash
   java Main "chemin_absolu/vers/le/fichier/test"
   ```
**Exemple de chemin** : java Main C:/Users/magiq/Documents/SudokuSolver/src/test

## Autrices
  - Lina GASMI
  - Ramlat MAOULANA CHARIF
