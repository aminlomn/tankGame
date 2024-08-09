package tankGame3;

import java.util.Vector;

/**
 * @author min
 * @version 1.0.0
 */
public class Wood extends Barrier{
    Vector<Wood> woods = new Vector();
    boolean isLive = true;
    public Wood(int x,int y){
        super(x,y);
    }

    public Vector<Wood> getWoods() {
        return woods;
    }

    public void setWoods(Vector<Wood> woods) {
        this.woods = woods;
    }

    public boolean getLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

}
