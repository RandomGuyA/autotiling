package Model.MapGen;

import Model.Coordinates;
import Model.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class SimpleLandGen {

    private int WIDTH;
    private int HEIGHT;
    private int overallAverage, difference;
    private int maximumHeight, minimumHeight, range;
    private int[][] binary_map;

    public SimpleLandGen(int tile_countX, int tile_countY) {

        WIDTH = tile_countX;
        HEIGHT = tile_countY;
        range = 2;
        binary_map = new int[WIDTH][HEIGHT];
        int loops = 4;
        binary_map = generateRandomSeeds(binary_map);
        binary_map = generateHeightmap(binary_map, loops);
        setAverageAndMaximumValues(binary_map);
        difference = maximumHeight - minimumHeight;
        reduceArrayToZero(binary_map);
    }

    private void reduceArrayToZero(int[][] array) {
        for (int a = 0; a < array.length; a++) {
            for (int b = 0; b < array[a].length; b++) {
                int loweredValue = (array[a][b] - minimumHeight);
                double factor = ((double) loweredValue) / difference * range;
                array[a][b] = (int) Math.floor(factor);
                if(array[a][b]>1){
                    array[a][b]=1;
                }
            }
        }
    }

    private void setAverageAndMaximumValues(int[][] array) {

        ArrayList<Integer> tempArray = new ArrayList<Integer>();
        int sum = 0;
        for (int a = 0; a < array.length; a++) {
            for (int b = 0; b < array[a].length; b++) {

                sum += array[a][b];
                tempArray.add(array[a][b]);
            }
        }

        setMaximumHeight(Collections.max(tempArray));
        setMinimumHeight(Collections.min(tempArray));
        double avg = sum / (double) (array.length * array.length);
        setOverallAverage((int) (avg));
    }

    private int[][] generateHeightmap(int[][] array, int loops) {

        for (int i = 0; i < loops; i++) {
            for (int a = 0; a < array.length; a++) {
                for (int b = 0; b < array[a].length; b++) {

                    ArrayList<Cell> neighbourCells = new ArrayList<Cell>();
                    neighbourCells = getNeighbouringCells(array, neighbourCells, a, b);

                    //calculate average of neighbouring cells
                    int sum = 0;
                    for (int c = 0; c < neighbourCells.size(); c++) {
                        sum += neighbourCells.get(c).getValue();
                    }
                    double avg = sum / (double) neighbourCells.size();
                    int average = (int) (avg);

                    //change target cell to average value
                    array[a][b] = average;
                }
            }
        }
        return array;
    }

    //takes 2D array and x and y coordinates, returns arraylist of surrounding cells
    private ArrayList<Cell> getNeighbouringCells(int[][] array, ArrayList<Cell> arrayList, int a, int b) {

        int x = a, y = b, row = 3, col = 3;

        //if not zero set a and b to the left outer corner of the 3x3 block
        if (a == 0) {
            row = row - 1;
        } else {
            a = a - 1;
        }
        if (b == 0) {
            col = col - 1;
        } else {
            b = b - 1;
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                if ((a + i) == x && (b + j) == y) {

                } else {
                    try {
                        arrayList.add(new Cell(new Coordinates((a + i), (b + j)), new Coordinates(x, y), array[a + i][b + j]));
                    } catch (Exception e) {
                    }
                }
            }
        }
        return arrayList;
    }

    private int[][] generateRandomSeeds(int[][] array) {

        for (int a = 0; a < array.length; a++) {
            for (int b = 0; b < array[a].length; b++) {
                int seed = Util.randInt(0, 255);
                array[a][b] = seed;
            }
        }
        return array;

    }


    public int getOverallAverage() {
        return overallAverage;
    }

    public void setOverallAverage(int overallAverage) {
        this.overallAverage = overallAverage;
    }

    public int getMaximumHeight() {
        return maximumHeight;
    }

    public void setMaximumHeight(int maximumHeight) {
        this.maximumHeight = maximumHeight;
    }

    public int getMinimumHeight() {
        return minimumHeight;
    }

    public void setMinimumHeight(int minimumHeight) {
        this.minimumHeight = minimumHeight;
    }

    public int[][] getBinary_map() {
        return binary_map;
    }

    public void setBinary_map(int[][] binary_map) {
        this.binary_map = binary_map;
    }
}