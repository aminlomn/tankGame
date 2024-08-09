package tankGame3;

/**
 * @author min
 * @version 1.0.0
 * 保存敌人坦克信息
 */
public class Node {
    private  int x;
    private  int y;
    private int direct;

    public Node(int y, int x, int direct) {
        this.y = y;
        this.x = x;
        this.direct = direct;
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

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }
}
