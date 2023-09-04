import java.util.Arrays;
import java.util.HashMap;

public class LevelMap {

    private static final int LEVEL_LENGTH = 29;
    private static final int H_LENGTH = 5;
    private static final int V_LENGTH = 5;

    public String[] currentLevel = {};

    private HashMap<Integer, String[]> levelList =  new HashMap<>();

    SnakeHead snake = new SnakeHead();
    SnakeTorso snakeTorso = new SnakeTorso();
    Block block = new Block();

    Apple apple = new Apple();

    public LevelMap() {
        saveAllLevelsOnMemory();
        loadNewLevel(0);
    }

    public void loadNewLevel(int newLevelNumber) {
        if (levelList.containsKey(newLevelNumber)) {
            String[] auxCopy = levelList.get(newLevelNumber);
            currentLevel = Arrays.copyOf(auxCopy, auxCopy.length);
            researchElements();
        }
    }

    private void searchSnakeInitialPosition() {
        for (int i = 0; i < currentLevel.length; i++) {
            for (int j = 0; j < currentLevel[i].length(); j++) {
                if (currentLevel[i].charAt(j) == 'S') {
                    snake.setPosX(j);
                    snake.setPosY(i);
                }
            }
        }
    }

    private void searchAppleInitialPosition() {
        boolean check = false;
        for (int i = 0; i < currentLevel.length; i++) {
            for (int j = 0; j < currentLevel[i].length(); j++) {
                if (currentLevel[i].charAt(j) == 'A') {
                    apple.setPosX(j);
                    apple.setPosY(i);
                    check = true;
                }
            }
        }
        if (!check) {
            apple.setPosX(-1);
            apple.setPosY(-1);
        }
    }

    public boolean movement(String move) {
        boolean isMovementAvailable = false;
        switch (move) {
            case "UP" -> {
                if (checkIfMovementIsPossible(0, -1)) {
                    updateMap(0, -1);
                    snake.moveUp();
                }
            }
            case "DOWN" -> {
                if (checkIfMovementIsPossible(0, 1)) {
                    updateMap(0, 1);
                    snake.moveDown();
                }
            }
            case "LEFT" -> {
                if (checkIfMovementIsPossible(-1, 0)) {
                    updateMap(-1, 0);
                    snake.moveLeft();
                }
            }
            case "RIGHT" -> {
                if (checkIfMovementIsPossible(1, 0)) {
                    updateMap(1, 0);
                    snake.moveRight();
                }
            }
        }
        //TODO: different sound depending on if the movement can be done or not, this is why returns boolean, the sound will be made by GamePanel
        return false;
    }

    public boolean checkIfMovementIsPossible(int moveX, int moveY) {
        if (snake.getPosY() + moveX >= 0 && snake.getPosY() + moveY < currentLevel.length && snake.getPosY() + moveY >= 0 && snake.getPosX() + moveX < currentLevel[0].length()) {
            if (currentLevel[snake.getPosY() + moveY].charAt(snake.getPosX() + moveX) == '-' || currentLevel[snake.getPosY() + moveY].charAt(snake.getPosX() + moveX) == 'A') {
                return true;
            }
        }
        return false;
    }

    private void updateMap(int moveX, int moveY) {

        int newPosX = snake.getPosX() + moveX;
        int newPosY = snake.getPosY() + moveY;

        if ((snake.isFlying() && moveX != 0) || currentLevel[snake.getPosY() + 1].charAt(snake.getPosX() + moveX) == '-') {
            gravityEffect(moveX);
        } else {
            createTorsoInMap(snake.posX, snake.posY);
            createHeadInMap(newPosX, newPosY);
        }

        snake.setFlying(moveY == -1);
    }

    private void saveAllLevelsOnMemory() {
        levelList.put(0, new String[] {
                "BBBBBBBBBBBBBBBB/",
                "B--------------B/",
                "B--------------B/",
                "B--B--B-BBBBB--B/",
                "B--B--B---B----B/",
                "B--BBBB---B----B/",
                "B--B--B---B----B/",
                "B--B--B-BBBBB--B/",
                "B--------------B/",
                "B--------------B/",
                "B--S--------A--B/",
                "BBBBBBBBBBBBBBBB/"});

        levelList.put(1, new String[] {
                "B--------------B/",
                "B-BBBBB-BB---B-B/",
                "B-B-----B-B--B-B/",
                "B-BBB---B--B-B-B/",
                "B-B-----B---BB-B/",
                "B-BBBBB--------B/",
                "B-------BBBBB--B/",
                "B-------B----B-B/",
                "B-------B----B-B/",
                "B-------BBBBB--B/",
                "B--------------B/",
                "BBBBBBBBBBBBBBBB/",});
    }

    private void createHeadInMap(int posX, int posY) {
        char[] charAux = currentLevel[posY].toCharArray();
        charAux[posX] = 'S';
        currentLevel[posY] = String.valueOf(charAux);
    }
    private void createTorsoInMap(int posX, int posY) {
        char[] charAux = currentLevel[posY].toCharArray();
        charAux[posX] = 'T';
        currentLevel[posY] = String.valueOf(charAux);
    }

    public void researchElements() {
        searchSnakeInitialPosition();
        searchAppleInitialPosition();
    }

    private void gravityEffect(int moveX) {
        int newPosY = snake.getPosY();
        int newPosX = snake.getPosX() + moveX;

        createTorsoInMap(snake.getPosX(), snake.getPosY());

        do {
            createTorsoInMap(newPosX, newPosY);
            newPosY++;
        } while (currentLevel[newPosY].charAt(newPosX) == '-');

        createHeadInMap(newPosX, newPosY - 1);
        snake.setPosY(newPosY - 1);
    }
}
