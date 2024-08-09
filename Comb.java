package tankGame3;

public class Comb {
    //炸弹坐标
    int x;
    int y;
    int life = 9;//生命周期
    boolean isLive = true;

    public Comb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //减少生命值,配合出现图片的爆炸效果
    public void lifeDown(){
        if(life>0){
            life--;
        }else{
            isLive = false;
        }
    }

}
