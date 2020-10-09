Dougèce Prophète 
Matricule : 20063146
Yifu Zhou 
Matricule :20092235


Pour utiliser notre JAR , veuillez suivre les étapes suivantes: 

1.	Créer un répertoire "test" au même niveau où le JAR se trouve. 
2.	Ajouter les dossiers à tester dans le répertoire "test".
3.	Lancer le JAR. 

Le programme traite les documents .java se trouvant dans le répertoire et les sous-répertoires puis génère 4 fichiers csv suivants :

"classes.csv"
"methodes.csv"
"3_BC_classes.csv"  
"3_BC_methods.csv"   

Les deux derniers fichiers contiennent "les 3 classes et les 3 méthodes les moins bien commentées".
On propose les améliorations suivantes :



Pour classe 1 :\test\src\main\java\org\jfree\chart\plot\XYPlot.java

Cette classe contient hashCode, donc il y a beaucoup de ligne de code sans besoin de commentaire. C'est correct qu'elle n'a pas beaucoup de commentaire. 

Pour classe 2 :\test\src\main\java\org\jfree\chart\plot\CategoryPlot.java

La même raison que classe XYPlot.java, cette classe contient hashCode, donc il y a beaucoup de ligne de code sans besoin de commentaire. C'est correct qu'elle n'a pas beaucoup de commentaire. 

Pour classe 3 :\test\src\main\java\org\jfree\data\general\DatasetUtils.java




Pour methode 1 : 


private double calculateValueNoINF(double log) {
        double result = calculateValue(log);
        if (Double.isInfinite(result)) {
            result = Double.MAX_VALUE;
        }
        if (result <= 0.0) {
            result = Double.MIN_VALUE;
        }
        return result;
    }

Ajouter commentaire "//If result is negative, return smallest POSITIVE nonzero value of type double; if result is infinite, return the maximum value a double can represent." 

Pour methode 2 : 

test\src\main\java\org\jfree\chart\axis\LogAxis.java


    @Override
    public void resizeRange(double percent, double anchorValue) {
        resizeRange2(percent, anchorValue);
    }

Ajouter commentaire "fonction to override resizeRange by resizeRange2" 



Pour methode 3 : 

\test\src\main\java\org\jfree\chart\axis\NumberTickUnitSource.java

    
@Override
    public TickUnit getLargerTickUnit(TickUnit unit) {
        TickUnit t = getCeilingTickUnit(unit);
        if (t.equals(unit)) {
            next();
            t = new NumberTickUnit(getTickSize(), getTickLabelFormat(), 
                    getMinorTickCount());
        }
        return t; 
    }

Ajouter commentaire "return the TickUnit with the max Ceiling." 

