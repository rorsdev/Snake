import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeHead extends Tile {

    public SnakeHead() {
        super();
        try {
            img = ImageIO.read(new File("src/Tiles/Snake_Head_Scaled.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private boolean isFlying;

    public boolean isFlying() {
        return isFlying;
    }

    public void setFlying(boolean flying) {
        isFlying = flying;
    }

    public void moveUp() {
        setPosY(posY - 1);
    }

    public void moveDown() {
        setPosY(posY + 1);
    }

    public void moveLeft() {
        setPosX(posX - 1);
    }

    public void moveRight() {
        setPosX(posX + 1);
    }

}
