package at.ac.fhcampuswien;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        System.out.println("Task 1: One Month Calendar");
        oneMonthCalendar(31,7);

        System.out.println("Task 2: Pseudo Random Number");
        //lcg(4789098765434567890L);
        System.out.println(Arrays.toString(lcg(4789098765434567890L)));
        System.out.println();

        System.out.println("Task 3: Guessing Game");
        guessingGame(randomNumberBetweenOneAndHundred());

        System.out.println("Task 4: Swap Arrays");
        int[] myarray1={1,2,3,4,5};
        int[] myarray2={9,8,7,6,0};
        swapArrays( myarray1, myarray2);

        System.out.println("Task 5: Camel Case");
        System.out.println(camelCase( "AnY noise annoYs an oYster but a noisY noise annoYs an oYster more." ));

        System.out.println("Task 6: Check Digit");
        int[] myarray3={3, 9, 1, 5, 8};
        System.out.println(checkDigit( myarray3));

        // test your method implementations here
        // make method calls
        // print their results
        // etc.
    }

    // Implement all methods as public static
    public static void oneMonthCalendar(int monatslaenge, int monatserster){

        int i;
        int tage=0;
       // int[] kalender=new int[7,7];

        for (i=1; i<monatserster;i++){
            System.out.format("   ");
        }

            while(tage<monatslaenge) {
                for (i = monatserster; i < 8; i++) {
                    tage++;
                    if (tage < 10) {
                        //     System.out.format("%2d", tage);
                        //    System.out.print(" ");
                        System.out.print(" " + tage + " ");
                        //    System.out.print(" ");
                    } else {
                        System.out.print(tage + " ");
                        //    System.out.print(" ");
                    }
                    if (i == 7) {
                        System.out.println();
                        i = 0;
                    }
                    if (tage == monatslaenge) {
                        System.out.println();
                        break;
                    }
                }
            }




    }
    public static long[] lcg(long seed){
        int a=1103515245, c=12345;
        long m=2147483648L;
        long [] pseudoRandom = new long[10];

        for (int i=0; i<10;i++){
            seed=(a*seed+c)%m;
            pseudoRandom[i]=seed;
        }
        return pseudoRandom;
    }

    public static int randomNumberBetweenOneAndHundred(){
        Random random= new Random();
        int zufallszahl=random.nextInt(99)+1;
        return zufallszahl;
    }

    public static void guessingGame(int numberToGuess){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Guess number 1: ");
        int number=scanner.nextInt();
        int zaehler=1;

        while (zaehler<10 && number!=numberToGuess){
            if (number<1 || number>100){
                return;
            }
            if (number < numberToGuess) {
                System.out.println("The number AI picked is higher than your guess.");
            } else if (number>numberToGuess) {
                System.out.println("The number AI picked is lower than your guess.");
            }
            zaehler++;
            System.out.print("Guess number "+zaehler+": ");
            number = scanner.nextInt();
        }

        if (number==numberToGuess) {
            System.out.println("You won wisenheimer!");
            return;
        }

        if (zaehler==10){
            System.out.println("You lost! Have you ever heard of divide & conquer?");
        }
    }

    public static boolean swapArrays(int [] a, int [] b) {

        int aLenght= a.length;
        int bLenght= b.length;

        if (aLenght!=bLenght){
            return false;
        } else {
            for (int i=0; i<aLenght; i++){
                int[] c=new int[aLenght];
                c[i]=a[i];
                a[i]=b[i];
                b[i]=c[i];
            }
            return true;
        }
    }

    public static char[] toCharArray(String umzuwandeln){
        int stringLaenge = umzuwandeln.length();
        char[] zeichenfolge= new char[stringLaenge];

        for(int i=0; i<stringLaenge; i++){
            zeichenfolge[i]=umzuwandeln.charAt(i);
        }
        return zeichenfolge;
    }
    public static String camelCase( String satz){
        char[] ordnungsArray=toCharArray(satz);
        int zaehler=1, i;

       //replace Großbuchstaben
        for (i=0; i<ordnungsArray.length; i++){
            if (64 < ordnungsArray[i] && ordnungsArray[i]< 91){
             //   ordnungsArray [i] = c;
                ordnungsArray[i] = (char) (ordnungsArray[i]+32);
            }
        }

        //Wortanfang auf Anfangsbuchstaben
        ordnungsArray[0] = (char) (ordnungsArray[0] - 32);

        for (i=0; i<ordnungsArray.length; i++) {
            if (32 == ordnungsArray[i]) {
                ordnungsArray[i + 1] = (char) (ordnungsArray[i + 1] - 32);
            }
        }

        //filtern
        for (i=0; i<ordnungsArray.length; i++) {
            if (65 > ordnungsArray[i] || ordnungsArray[i] > 122 || (90 < ordnungsArray[i] && ordnungsArray[i] < 97)) {
                for (int j = i; j < ordnungsArray.length-1; j++) {
                    ordnungsArray[j] = (ordnungsArray[j + 1]);
                }
                zaehler++;
            }
        }

        int neueLaenge=ordnungsArray.length-zaehler/2;
        char[] fertigArr = new char[neueLaenge];

        for (i=0; i<neueLaenge; i++){
            fertigArr[i]=ordnungsArray[i];
        }
        String ausgabe=String.valueOf(fertigArr);
        return ausgabe;
    }
    public static int checkDigit(int[] code){

        int[] gewichtung=new int[code.length];
        int[] produkte=new int[code.length];

        int i, sum=0, pruefzi;

        for (i=0; i< code.length; i++) {
            gewichtung[i] = 2 + i;
            produkte[i]=gewichtung[i]* code[i];
            sum=sum+produkte[i];
        }

        pruefzi=sum%11;
        pruefzi=11-pruefzi;

        if (pruefzi==10){
            pruefzi=0;
        }
        if (pruefzi==11){
            pruefzi=5;
        }

        return pruefzi;
    }

}