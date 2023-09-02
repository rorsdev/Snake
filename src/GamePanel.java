import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int ORIGINAL_TILE_SIZE = 16; // 16x16 tile
    final int SCALE = 3;
    final int TILE_SIZE = ORIGINAL_TILE_SIZE * SCALE;
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = TILE_SIZE * MAX_SCREEN_COL; // pixels
    final int SCREEN_HEIGHT = TILE_SIZE * MAX_SCREEN_ROW; // pixels

    KeyHandler keyH = new KeyHandler();
    LevelMap levelMap = new LevelMap();
    Thread gameThread;

    int currentLevelInt;

    public GamePanel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // With this GamePanel can be focused to receive key input.

        currentLevelInt = 0;
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {

        while (gameThread != null) {
            // Update information
             update();
            // Draw screen with updated information
            repaint(); // This calls paintComponent()
        }
    }

    public void update() {
        if (keyH.upPressed) {
            levelMap.movement("UP");
            controlOfMovementsPorSecond();
        } else if (keyH.downPressed) {
            levelMap.movement("DOWN");
            controlOfMovementsPorSecond();
        } else if (keyH.leftPressed) {
            levelMap.movement("LEFT");
            controlOfMovementsPorSecond();
        } else if (keyH.rightPressed) {
            levelMap.movement("RIGHT");
            controlOfMovementsPorSecond();
        }

        if (levelMap.snake.getPosX() == levelMap.apple.getPosX() && levelMap.snake.getPosY() == levelMap.apple.getPosY()) {
            currentLevelInt ++;
            levelMap.loadNewLevel(currentLevelInt);
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        int xPosition = 0, yPosition = 0;

        for (int i = 0; i < levelMap.currentLevel.length; i++) {
            for (int j = 0; j < levelMap.currentLevel[i].length(); j++) {
                switch (levelMap.currentLevel[i].charAt(j)) {
                    case 'A' -> {
                        g2.drawImage(levelMap.apple.img, xPosition, yPosition, this);
                        xPosition += TILE_SIZE;
                    }
                    case 'B' -> {
                        g2.drawImage(levelMap.block.img, xPosition, yPosition, this);
                        xPosition += TILE_SIZE;
                    }
                    case 'S' -> {
                        g2.drawImage(levelMap.snake.img, xPosition, yPosition, this);
                        xPosition += TILE_SIZE;
                    }
                    case 'T' -> {
                        g2.drawImage(levelMap.snakeTorso.img, xPosition, yPosition, this);
                        xPosition += TILE_SIZE;
                    }
                    case '/'-> {
                        yPosition += TILE_SIZE;
                        xPosition = 0;
                    }
                    case '-' -> {
                        g2.setColor(Color.BLACK);
                        xPosition += TILE_SIZE;
                    }
                }
            }
        }
        g2.dispose();
    }

    public void controlOfMovementsPorSecond() {
        // Which in normal games would be considered as FPS
        try {
            Thread.sleep(200);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
