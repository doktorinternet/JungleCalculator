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