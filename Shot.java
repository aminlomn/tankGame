package tankGame3;

public class Shot implements Runnable{
    int x;//子弹的坐标
    int y;//y坐标
    int direct = 0;//方向
    int speed = 2;//速度
    boolean isLive = true;//子弹是否存活

    //构造器初始化，子弹坐标和方向
    public Shot(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {//射击
        while(true){
            //休眠
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            //根据方向改变坐标
            switch (direct){
                case 0:
                    y-=speed;
                    break;
                case 1:
                    x+=speed;
                    break;
                case 2:
                    y+=speed;
                    break;
                case 3:
                    x-=speed;
                    break;
            }

            System.out.println("x: " + x + " y: " + y);
            //碰到边界，子弹退出线程
            if(!(x>=0&&x<=1000&&y>=0&&y<=750&&isLive)){
                isLive=false;//子弹销毁
                break;
            }
        }
    }
}
