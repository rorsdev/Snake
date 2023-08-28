public class Snake {

    private int posX, posY;
    private boolean isFlying;

    public Snake() {
        this.isFlying = false;
    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }
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
