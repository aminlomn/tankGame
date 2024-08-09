package tankGame3;

import java.util.Vector;

public class Hero extends Tank {
    //创建shot对象
    Shot shot = null;
    Vector<Shot> shots = new Vector<>();
    public Hero(int x, int y){
        super(x,y);
    }

    //射击
    //最多只能发射五颗
    public void shotEnemyTank(){
        //创建shot对象，根据当前hero位置和方向来创建Shot
        if(shots.size()==5){
            return;
        }
        switch (getDirect()){
            case 0:
                shot = new Shot(getX()+20,getY(),0);
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,1);
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,2);
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,3);
                break;
        }
        //将子弹放入集合中
        shots.add(shot);
        //启动射击线程
        new Thread(shot).start();
    }
}
