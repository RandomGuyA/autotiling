package Model.MapGen;

import Model.Util;

public class AutoTiling {


    private int FACTOR = 2;

    public int[][] generateAutoTiledMap(int[][] binary_map) {


        Util.printArray(binary_map);
        int[][] exploded_map = explode_array(binary_map);
        System.out.println("exploded");
        Util.printArray(exploded_map);

        exploded_map = array_shift(exploded_map);
        Util.printArray(exploded_map);

        int[][] imploded_map = implode_array(exploded_map);

        Util.printArray(imploded_map);
        /*
        for (int y = 0; y < binary_map[0].length; y++) {
            System.out.println();
            for (int x = 0; x < binary_map.length; x++) {
                //System.out.print("\t"+binary_map[x][y]);

                if (binary_map[x][y] == 0) {//if you find water check for intersection

                    int[][] tile_map = grab_area_around_tile(binary_map, x, y);


                    if (array_contains(tile_map, 1)) {
                        Util.printArray(tile_map);

                        String string_value = "";
                        string_value += adjacent_tile_check(tile_map, 1, 1);
                        string_value += adjacent_tile_check(tile_map, 0, 1);
                        string_value += adjacent_tile_check(tile_map, 1, 0);
                        string_value += adjacent_tile_check(tile_map, 0, 0);


                        System.out.println(string_value);
                        int value = Integer.parseInt(string_value , 2);
                        System.out.println(value);
                        binary_map[x][y] = value;

                    }
                }
            }
        }*/
        return imploded_map;
    }

    private int[][] implode_array(int[][] explode_map) {

        int new_width = explode_map.length / FACTOR;
        int new_height = explode_map[0].length / FACTOR;
        int[][] implode_map = new int[new_width][new_height];

        for (int y = 0; y < implode_map.length; y++) {
            for (int x = 0; x < implode_map[y].length; x++) {

                String binary_string = "";

                for (int iy = FACTOR - 1; iy >= 0; iy--) {
                    for (int ix = FACTOR - 1; ix >= 0; ix--) {

                        binary_string += explode_map[(x * FACTOR) + ix][(y * FACTOR) + iy];
                    }
                }
                implode_map[x][y] = Integer.parseInt(binary_string, 2);
            }
        }
        return implode_map;
    }


    private int[][] array_shift(int[][] exploded_map) {

        for (int y = exploded_map.length - 1; y >= 0; y--) {
            for (int x = exploded_map[y].length - 1; x >= 0; x--) {

                //System.out.println(("("+x+","+y+")"));
                if (x == 0 || y == 0) {
                    exploded_map[x][y] = 0;
                } else {
                    exploded_map[x][y] = exploded_map[x - 1][y - 1];
                }
            }
        }
        return exploded_map;
    }

    private int[][] explode_array(int[][] binary_map) {

        int new_width = binary_map.length * FACTOR;
        int new_height = binary_map[0].length * FACTOR;


        int[][] exploded_array = new int[new_width][new_height];

        for (int y = 0; y < binary_map.length; y++) {
            for (int x = 0; x < binary_map[y].length; x++) {
                for (int iy = 0; iy < FACTOR; iy++) {
                    for (int ix = 0; ix < FACTOR; ix++) {
                        exploded_array[(x * FACTOR) + ix][(y * FACTOR) + iy] = binary_map[x][y];
                    }
                }
            }
        }
        return exploded_array;
    }


    private byte adjacent_tile_check(int[][] tiles, int dx, int dy) {
        System.out.println("(" + dx + "," + dy + ")");
        for (int y = 0; y < 2; y++) {
            System.out.println();
            for (int x = 0; x < 2; x++) {
                System.out.print(tiles[x + dx][y + dy] + "\t");
                if (tiles[x + dx][y + dy] == 1) {
                    return 1;
                }
            }
        }
        return 0;
    }

    private boolean array_contains(int[][] tile_map, int i) {

        for (int k = 0; k < tile_map.length; k++) {
            // System.out.println();
            for (int j = 0; j < tile_map[k].length; j++) {
                if (tile_map[j][k] == i) {
                    //System.out.print(tile_map[j][k]+"\t");

                    return true;
                }
            }
        }
        return false;
    }


    /**
     * @param binary_map
     * @param a
     * @param b
     * @return
     */
    private int[][] grab_area_around_tile(int[][] binary_map, int a, int b) {

        int x = a, y = b, row = 3, col = 3;
        a = a - 1;
        b = b - 1;

        int[][] tile_map = new int[row][col];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {

                if ((a + i) == x && (b + j) == y) {  //Centre Tile
                    tile_map[i][j] = -1;
                } else {

                    try {
                        tile_map[i][j] = binary_map[a + i][b + j];

                    } catch (ArrayIndexOutOfBoundsException e) {
                        tile_map[i][j] = -1;
                    }
                }
            }
        }

        return tile_map;
    }

}
