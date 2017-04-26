package Model.MapGen;

import java.awt.*;
import java.awt.image.BufferedImage;


public class Tile {

    private BufferedImage image;

    public Tile(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }
    public void draw(Graphics g, int x, int y){
        g.drawImage(image, x, y, null);
    }
}
