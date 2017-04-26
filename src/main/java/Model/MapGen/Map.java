package Model.MapGen;

import java.awt.*;

public class Map {

    private Tileset tileset;
    private int tile_countX = 50;
    private int tile_countY = 50;
    private int tile_width = 32;
    private int tile_height = 32;
    private Tile[][] tiles;

    public Map() {

        //tileset = new Tileset("rogue_like_tile_set.png", 16, 16, 57, 31, 1, 1);
        tileset = new Tileset("cube-set-32.png", tile_width, tile_height, 16, 1, 0, 0);
        MapGenerator mg = new MapGenerator(tileset, tile_countX, tile_countY);
        mg.init();
        tiles = mg.getTiles();
    }

    public void draw(Graphics g) {
        for (int y = 0; y < tile_countY; y++) {
            for (int x = 0; x < tile_countX; x++) {
                tiles[x][y].draw(g, x * tile_width, y * tile_height);
            }
        }
    }
}
