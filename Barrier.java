package tankGame3;

/**
 * @author min
 * @version 1.0.0
 * 障碍物类
 */
public class Barrier {
    private int x;
    private int y;
    private int size=40;

    public Barrier(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
