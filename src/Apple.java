import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Apple extends Tile {

    public Apple() {
        super();
        try {
            img = ImageIO.read(new File("src/Tiles/Apple2_Scaled.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
