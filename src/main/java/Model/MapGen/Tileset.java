package Model.MapGen;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Tileset {

    private BufferedImage[] tiles;
    private BufferedImage[][] sprites;
    private int tileCountX, tileCountY, tileWidth, tileHeight, marginX, marginY;
    private BufferedImage tileset;
    private String DIRECTORY = "/";

    public Tileset(String fileName, int tileWidth, int tileHeight, int tileCountX, int tileCountY, int marginX, int marginY) {

        this.tileCountX = tileCountX;
        this.tileCountY = tileCountY;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.marginX = marginX;
        this.marginY = marginY;


        tileset = load_image(fileName);
        sprites = split_tileset_into_tiles(tileset);

    }

    private BufferedImage[][] split_tileset_into_tiles(BufferedImage tileset) {

        BufferedImage[][] sprites = new BufferedImage[tileCountX][tileCountY];

        for (int i = 0; i < tileCountX; i++) {
            for (int j = 0; j < tileCountY; j++) {
                sprites[i][j] = tileset.getSubimage(i * (tileWidth + marginX), j * (tileHeight + marginY), tileWidth, tileHeight);
            }
        }
        return sprites;
    }

    private BufferedImage load_image(String fileName) {

        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResourceAsStream(DIRECTORY + fileName));
        } catch (IOException e) {
            System.out.println("failed to load asset");
        }

        return img;
    }
    public void draw(Graphics g){

        g.drawImage(tileset,0,0,null);

    }

    public BufferedImage[][] get_sprites() {
        return sprites;
    }

    public void set_sprites(BufferedImage[][] sprites) {
        this.sprites = sprites;
    }
}
