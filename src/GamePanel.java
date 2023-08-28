import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    // Screen Settings
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;
    final int tileSize = originalTileSize * scale;
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // pixels
    final int screenHeight = tileSize * maxScreenRow; // pixels

    KeyHandler keyH = new KeyHandler();
    LevelMap levelMap = new LevelMap();
    Thread gameThread;
    Snake snake = new Snake();
    Apple apple = new Apple();

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true); // With this GamePanel can be focused to receive key input.
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        //TODO: check if thread loop autorefreshing has too much iterations x second, in 2nd Video from RyiSnow explains it
        while (gameThread != null) {
//            System.out.println("Game Loop is Running");

            // Update information
            update();
            // Draw screen with updated information
            repaint(); // This calls paintComponent
        }
    }

    public void update() {
        if (keyH.upPressed) {
            levelMap.movement("UP");
            System.out.println("W");
        } else if (keyH.downPressed) {
            levelMap.movement("DOWN");
            System.out.println("S");
        } else if (keyH.leftPressed) {
            levelMap.movement("LEFT");
            System.out.println("A");
        } else if (keyH.rightPressed) {
            levelMap.movement("RIGHT");
            System.out.println("D");
        }

        if (snake.getPosX() == apple.getPosX() && snake.getPosY() == apple.getPosY()) {
            //TODO: next level
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
                        g2.setColor(Color.red);
                        g2.fillRect(xPosition, yPosition, tileSize, tileSize);
                        xPosition += tileSize;
                    }
                    case 'B' -> {
                        g2.setColor(Color.CYAN);
                        g2.fillRect(xPosition, yPosition, tileSize, tileSize);
                        xPosition += tileSize;
                    }
                    case 'S' -> {
                        g2.setColor(Color.green);
                        g2.fillRect(xPosition, yPosition, tileSize, tileSize);
                        xPosition += tileSize;
                    }
                    case '/'-> {
                        yPosition += tileSize;
                        xPosition = 0;
                    }
                    case '-' -> {
                        g2.setColor(Color.BLACK);
                        xPosition += tileSize;
                    }
                }
            }
        }
        g2.dispose();
    }
}
