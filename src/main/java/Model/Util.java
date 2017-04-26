package Model;


import java.util.Random;

public class Util {

    public static void printArray(int[][] array){

        for(int b=0; b<array.length; b++){
            System.out.println();
            for(int a=0; a<array[b].length; a++){
                System.out.print(array[a][b]+"\t");
            }
        }
        System.out.println();
    }

    public static int randInt(int min, int max) {

        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
}
