import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.util.Arrays.sort;

public class JungleCalculator
{

    private JungleCalculator(){

    }

    private static boolean debug = false;

    static JungleCalcMain jcm = new JungleCalcMain(); // object to call on main methods
    static String table;

    // Values per single creep
    static int [] [] creeps = {{175, 70, 1},  // 0 AncientKrug
                                {45, 10, 3},   // 1 krug
                        {15, 10, 6},   // 2 miniKrug
                        {65, 62, 1},   // 3 crimsonRaptor
                        {15, 9, 5},    // 4 raptor
                        {150, 68, 1},  // 5 bigWolf
                        {40, 16, 2},   // 6 smallWolf
                        {260, 86, 1},  // 7 gromp
                        {260, 100, 1}, // 8 blue
                        {260, 100, 1}};// 9 red

    static ArrayList<String> getCreepNames(){
        ArrayList<String> names = new ArrayList<String>();
        names.add("Ancient Krug");
        names.add("Normal krug");
        names.add("Minikrug");
        names.add("Crimson Raptor");
        names.add("Small Raptors");
        names.add("Big wolf");
        names.add("Small wolf");
        names.add("Gromp");
        names.add("Blue");
        names.add("Red");
        return names;
    }

    static int amt[] = new int[10];

    //Scanner sc = new Scanner(System.in);
    static int [] [] maxPerGroup = new int [10][2];
    // All camps combined exp and gold reward. FILLED

    static int [] max = new int[2];
    // Store distributed values in objects

    static int [][] junglers = new int [2][2];

    static int [][] creepsBlueSide()
    {
        return  new int[][]{
                {150, 68, 1},  // 0 bigWolf
                {40, 16, 2},   // 1 smallWolf
                {260, 86, 1},  // 2 gromp
                {260, 100, 1}};// 3 blue
    }

    static int [][] creepsRedSide()
    {
        return  new int[][]{
                {175, 70, 1},  // 0 AncientKrug
                {45, 10, 3},   // 1 krug
                {15, 10, 6},   // 2 miniKrug
                {65, 62, 1},   // 3 crimsonRaptor
                {15, 9, 5},    // 4 raptor
                {260, 100, 1}};// 5 red
    }

    private static void calcMax () // throws NumberFormatException
    {
        // Set variables creepIndex and amount of creeps in a camp to be calculated
        int ci = 0, amt, sel = 0;

        while (sel < 2)
        {
            while (Array.getLength(creeps) > ci)
            {
                amt = creeps[ci][2];
                // jcm.print(amt + " = " + creeps[ci][2]);
                jcm.debug(debug, "max[sel](" + max[sel] + ") = " + "max[sel](" + max[sel] + ")" +
                        "creeps[ci][sel](" + creeps[ci][sel] + ") * " + "amt(" + amt + ")");

                max[sel] += creeps[ci][sel] * amt;
                maxPerGroup[ci][sel] = creeps[ci][sel] * amt;
                int tmp[] = {ci, amt, max[sel], maxPerGroup[ci][sel], creeps[ci][sel]};

                jcm.debug(debug,
                        "Current numbers are: \n" + tmp[0] +
                                " - Creep Index \n" + tmp[1] +
                                " - Amount of creeps to be calculated" + tmp[2] +
                                "\n - Array Max current value " + tmp[3] +
                                "\n - Max per group: " + tmp[4] + "\n");
                ci++;
            }

            ci = 0;
            sel++;
            jcm.debug(debug, "If sel is now 1 then second slot of arrays will be filled now\n");

            if (sel == 2)
            {
                jcm.print("Now choose whether to calculate xp (0) or gold (1)");
                distributor(extractAndSortArray(creeps, new Scanner(System.in).nextInt()));
            }
        }
    }


    private static void distributor(int [] i)
    {
        jcm.print(i.toString());
        //StringBuilder takes = new StringBuilder("Both junglers take one buff each. Then:\n");
        System.out.println("Enter jungler names one by one");
        String jglrA = new Scanner(System.in).next(), jglrB = new Scanner(System.in).next();
        System.out.println("Both junglers take one buff each. Then:\n");
        int a = i[i.length-1], b = i[i.length-2] , x = i.length-3;
        jcm.debug(debug, a + " and " + b + " and " + x);

        if(a == 0){ jcm.print("Error, extracted list is 0"); System.exit(1);}
        while (x >= 0){
                jcm.debug(debug,"Inne i första while i distributor");
            while (a <= b && x >= 0){
                a += i[x];
                jcm.debug(debug,"Inne i andra while i distributor");
                //takes.append(getCreepNames().get(x).concat("\t\t goes to jungler A\n"));
                System.out.printf("%-15s goes to %s%n", getCreepNames().get(x), jglrA);
                x--;
            }
            while (a >= b && x >= 0){
                b += i[x];
                jcm.debug(debug, "Inne i tredje while i distributor");
                //takes.append(getCreepNames().get(x).concat("\t\t goes to jungler B\n"));
                System.out.printf("%-15s goes to %s%n", getCreepNames().get(x), jglrB);
                x--;
            }
        }
        //System.out.println(takes);
    }

    private static int[] extractAndSortArray(int[][] extractee, int valueType){
        int index = 0;
        int [] arr = new int[extractee.length];
        if (valueType > 1) jcm.print("Oops, cannot calculate amount using this method yet, retry using 0 or 1 as" +
                                                                                                              " value");
        for(int [] values : extractee)
        {
            arr[index] = values[valueType] * creeps[index][2];
            //jcm.print(arr[cnt] + " added to place" + cnt);
            index++;
        }
        sort(arr);
        jcm.print(Arrays.toString(arr));
        return arr;
    }

    static void run(){
        calcMax();
        //amt = extractAndSortArray(creeps, 2);
    }
}
