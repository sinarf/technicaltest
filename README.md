# Test Technique OUI.sncf 


## Utilisation

Pré-requis : avoir un JRE 7 ou supérieur. 

Pour traiter un lot, exécuter la commande suivante : 

```
java -jar XspeedIt-jar-with-dependencies.jar --batch 163841689525773
```
Remplacer **163841689525773** par votre liste d'articles. 

Un fichier de log xspeedit.log est produit dans le répertoire log, qui est créé automatiquement dans le répertoire où vous avez lancer la commande. 


## Construction depuis les sources

Pour construire le livrable, vous devez avoir les pré-requis suivant : 
  * JDK7 ou supérieur
  * maven 3 installé. 
  
Le livrable est produit de la manière suivante : 

```
mvn clean package
```

Dans le répertoire target un jar contenant toutes les dépendances est produit et porte le nom XspeedIt-jar-with-dependencies.jar


## Explication des choix


### Outils de construction

J'ai utilisé maven parce que c'est l'outil le plus répandu aujourd'hui et celui que je maitrise le mieux. 
Utiliser maven permet : 
  * Une utilisation facile des outils d'intégration continue
  * Un format standard pour les projets
  
Gradle aurait été un choix équivalent, qui apporte les mêmes avantages.  

### Utilisation des bibliothèques 

J'utilise deux bibliothèques apache commons : 
  * commons-cli : Vu le nombre failble d'options, j'aurais pu coder à la la main, mais  
  * commons-lang3 : Uniquement pour la classe StringUtils, juste parce que j'aime pas réinventer la roue. 
  
Pour le reste, des choix assez classique : 
  * Junit pour les tests parce que c'est le framework sur lequel j'ai le plus d'expérience. 
  * Log4j / slf4j pour les logs, l'usage de slf4j permet de pas être lié à log4j. 

### coding style

J'utilise mon propre 'coding style', qui est une somme de choix, souvent subjectif, acquis au fil du temps. C'est ma préférence à moi, comme dit la chanson, mais il est évident que, dans une équipe, on suit le style commun. Le code appartenant à l'équipe, tous les membres doivent pourvoir retrouver leurs petits. 

Mon style est très inspiré de "Clean Code" de Uncle Bob Martin. 

