/*

    Ett program som extraherar ett fält ur ett flerdimensionellt fält för att sedan sortera och fördela det över två
    andra.

    Syftet är att räkna ut en acceptabel fördelning av resurser mellan två karaktärer i ett spel. Programmet är inte
    riktigt fullständigt, och jag utelämnar en klass Champion.java vilken gör ett försök att söka i JSONobjekt efter
    data, eftersom den ser rätt taskig ut för närvarande

 */

public class JungleCalcMain {

    public static void main(String [] args){
        // Do stuff
        JungleCalculator.run();
    }

    void addEmptyLine(int amount){ while(amount > 0) { System.out.println(); amount--;}}

    void addEmptyLine(){
        System.out.println();
    }

    void print(String str){
        System.out.println(str);
    } // Prints strings with easier method call

    void print(int i){
        System.out.println(i);
    }        // prINTS :D

    void debug(boolean bool, String s){
        if (bool) print(s);
    }

}