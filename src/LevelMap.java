import java.util.HashMap;

public class LevelMap {

    private static final int LEVEL_LENGTH = 29;
    private static final int H_LENGTH = 5;
    private static final int V_LENGTH = 5;

//    public static Cube[][] LEVEL = new Cube[H_LENGTH][V_LENGTH];
    public String[] currentLevel = {};

    private HashMap<Integer, String[]> levelList =  new HashMap<>();

    Snake snake = new Snake();

    Apple apple = new Apple();

    public LevelMap() {
        saveAllLevelsOnMemory();
        currentLevel = levelList.get(0);
        searchSnakeInitialPosition();
        searchAppleInitialPosition();
    }

    public void loadNewLevel(int newLevelNumber) {
        if (levelList.containsKey(newLevelNumber)) {
            currentLevel = levelList.get(newLevelNumber);
            searchSnakeInitialPosition();
            searchAppleInitialPosition();
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
        for (int i = 0; i < currentLevel.length; i++) {
            for (int j = 0; j < currentLevel[i].length(); j++) {
                if (currentLevel[i].charAt(j) == 'A') {
                    apple.setPosX(j);
                    apple.setPosY(i);
                }
            }
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
                if (moveX == -1) {
                    return !snake.isFlying();
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    private void updateMap(int moveX, int moveY) {
        //TODO: fell down logic
        int newPosX = snake.getPosX() + moveX;
        int newPosY = snake.getPosY() + moveY;

        char[] charAux = currentLevel[newPosY].toCharArray();
        charAux[newPosX] = 'S';
        currentLevel[newPosY] = String.valueOf(charAux);

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
                "B--------------B/",});
    }
}
