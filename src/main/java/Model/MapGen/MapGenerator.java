package Model.MapGen;

import Model.Util;

import java.awt.image.BufferedImage;

public class MapGenerator {

    private SimpleLandGen landGen;
    private Tileset tileset;
    private Tile[][] tiles;
    private int tile_countX;
    private int tile_countY;

    public MapGenerator(Tileset tileset, int tile_countX, int tile_countY) {
        this.tileset = tileset;
        this.tile_countX = tile_countX;
        this.tile_countY = tile_countY;
    }

    public void init() {

        tiles = new Tile[tile_countX][tile_countY];

        landGen = new SimpleLandGen(tile_countX, tile_countY);
        int[][] binary_map = landGen.getBinary_map();
        Util.printArray(binary_map);

        AutoTiling at = new AutoTiling();
        int [][] tile_map = at.generateAutoTiledMap(binary_map);
        //int[][] tile_map  = binary_map;
        Util.printArray(tile_map);

        BufferedImage[][] sprites = tileset.get_sprites();

        for(int y=0; y<tile_countY;y++){
            for (int x = 0; x <tile_countX; x++ ){

                BufferedImage sprite;
                if(binary_map[x][y]==0){
                    sprite = sprites[tile_map[x][y]][0];
                }else{
                    sprite = sprites[tile_map[x][y]][0];
                }

                tiles[x][y] = new Tile(sprite);
            }
        }
    }



    public Tile[][] getTiles() {
        return tiles;
    }
}
