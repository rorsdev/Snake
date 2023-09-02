import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Block extends Tile {

    public Block() {
        super();
        try {
            img = ImageIO.read(new File("src/Tiles/Block_Scaled.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
