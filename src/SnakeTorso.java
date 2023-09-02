import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SnakeTorso extends Tile {

    public SnakeTorso() {
        super();
        try {
            img = ImageIO.read(new File("src/Tiles/Snake_Torso_Scaled.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
