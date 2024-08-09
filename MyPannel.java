package tankGame3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;

//游戏的绘图区域
//为了让Panel不停的重绘子弹，需要将MyPannel实现Runnable，当做一个线程使用
public class MyPannel extends JPanel implements KeyListener,Runnable {
    //定义我们的坦克
    Hero hero = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    //存放炸弹,当子弹击中坦克时，就加入一个Comb对象到combs
    Vector<Comb> combs = new Vector<>();
    int enemySize = 10;

    //定义三张炸弹图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    //带你一个存放Node的VEctor,恢复敌人信息
    Vector<Node> nodes = new Vector<>();

    Vector<Metal> metals = new Vector<>();
    Vector<Wood> woods = new Vector<>();

    public MyPannel(String key){
        //判断记录文件是否存在，否则只能开启新游戏，key=1
        initMetal();
        initWood();
        File file = new File(Recorder.getRecordFileName());
        if(file.exists()){
            nodes = Recorder.getNodesAddEnemyTankRec();
        }else{
            System.out.println("记录不存在，开始新游戏");
            key = "1";
        }

        //将Vector设置给Record
        Recorder.setEnemyTanks(enemyTanks);



        hero = new Hero(100,300);//初始化自己的坦克
        hero.setSpeed(10);
        switch(key){
            case "1"://新游戏
                for(int i=0;i<enemySize;i++){
                    Recorder.setAllEnemyTankNum(0);
                    EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 100);
                    //防止碰撞
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setMetals(metals);
                    enemyTank.setWoods(woods);
                    enemyTank.setDirect(2);
                    //启动敌人坦克进程
                    Thread thread = new Thread(enemyTank);
                    thread.start();
                    //给该敌人加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //加入到enemyTank的Vector
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);


                }
                break;
            case "2"://继续上一局游戏
                for(int i=0;i<nodes.size();i++){
                    Node node = nodes.get(i);

                    EnemyTank enemyTank = new EnemyTank(node.getX(), node.getY());
                    //防止碰撞
                    enemyTank.setEnemyTanks(enemyTanks);
                    enemyTank.setMetals(metals);
                    enemyTank.setWoods(woods);
                    enemyTank.setDirect(node.getDirect());
                    //启动敌人坦克进程
                    Thread thread = new Thread(enemyTank);
                    thread.start();
                    //给该敌人加入一颗子弹
                    Shot shot = new Shot(enemyTank.getX()+20,enemyTank.getY()+60,enemyTank.getDirect());
                    //加入到enemyTank的Vector
                    enemyTank.shots.add(shot);
                    //启动shot对象
                    new Thread(shot).start();
                    enemyTanks.add(enemyTank);

                }
                break;
        }

        //初始化图片对象
        image1 = Toolkit.getDefaultToolkit().getImage(MyPannel.class.getResource("tank.png"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPannel.class.getResource("tank1.png"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPannel.class.getResource("tank2.png"));



        //播放音乐
        //new AePlayWave("src\\tankGame3\\Tank01.wav").start();

    }

    //显示我方击毁坦克的信息
    public void showInfo(Graphics g){
        g.setColor(Color.black);
        Font font = new Font("宋体", Font.BOLD, 25);
        g.setFont(font);

        g.drawString("您击毁敌方坦克：",1020,30);
        drawTank(1020,60,g,0,0);//画一个敌方坦克
        //画笔颜色重置，因为画坦克改变了颜色
        g.setColor(Color.black);
        g.drawString(Recorder.getAllEnemyTankNum()+"",1080,100);
    }

    //初始化铁板
    public void initMetal(){
        for(int i=0;i<2;i++){
            Metal metal = new Metal(260+40*i,340);
            metals.add(metal);
            Metal metal2 = new Metal(560+40*i,340);
            metals.add(metal2);
        }
    }

    //初始化木板
    public void initWood(){
        for(int i=0;i<6;i++){
            Wood wood = new Wood(120,80+i*40);
            woods.add(wood);
            Wood wood1 = new Wood(120,400+i*40);
            woods.add(wood1);
            Wood wood2 = new Wood(420,80+i*40);
            woods.add(wood2);
            Wood wood3 = new Wood(420,400+i*40);
            woods.add(wood3);
            Wood wood4 = new Wood(720,80+i*40);
            woods.add(wood4);
            Wood wood5 = new Wood(720,400+i*40);
            woods.add(wood5);
        }
    }

    public void drawMetal(Vector<Metal> metals, Graphics g){
        g.setColor(Color.gray);
        for(int i=0;i<metals.size();i++){
            Metal metal = metals.get(i);
            g.fill3DRect(metal.getX(),metal.getY(),40,40,false);
        }
    }

    public void drawWood(Vector<Wood> woods, Graphics g){
        g.setColor(Color.pink);
        for (int i = 0; i < woods.size(); i++) {
            Wood wood = woods.get(i);
            if(wood.isLive){
                g.fill3DRect(wood.getX(),wood.getY(),40,40,false);
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0,0,1000,750);//填充矩形，默认黑色
        drawMetal(metals,g);
        drawWood(woods,g);
        showInfo(g);
       //画出自己的坦克
        if(hero!=null&&hero.isLive){
            drawTank(hero.getX(),hero.getY(),g,hero.getDirect(),0);
        }

        //画出子弹，遍历集合
        for(int i=0;i<hero.shots.size();i++){
            Shot shot = hero.shots.get(i);
            if(shot!=null&&shot.isLive==true){
                g.fill3DRect(shot.x,shot.y,5,5,false);
            }else{
                //子弹消亡了，就移出去
                hero.shots.remove(shot);
            }
        }


        //如果combs有对象就画出来
        for(int i=0;i<combs.size();i++){
            Comb comb = combs.get(i);
            if(comb.life>6){
                g.drawImage(image1,comb.x,comb.y,60,60,this);
            } else if (comb.life>3) {
                g.drawImage(image2,comb.x,comb.y,60,60,this);
            } else {
                g.drawImage(image3,comb.x,comb.y,60,60,this);
            }
            //让炸弹的生命值减少
            comb.lifeDown();
            //如果bomb life为0，就从combs的集合中删除
            if(!comb.isLive){
                combs.remove(comb);
            }
        }

        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemy = enemyTanks.get(i);
            //只有敌人坦克是或者的才画
            if(enemy.isLive){

                drawTank(enemy.getX(),enemy.getY(),g,enemy.getDirect(),1);


                //画出敌人子弹
                for(int j=0;j<enemy.shots.size();j++){
                    Shot shot = enemy.shots.get(j);
                    if(shot.isLive==true){
                        g.fill3DRect(shot.x,shot.y,5,5,false);
                    }else{
                        //移除
                        enemy.shots.remove(shot);
                    }
                }
            }

        }
    }

    //编写方法，画出坦克

    /**
     * @param x 坦克左上角x坐标
     * @param y 坦克右上角y坐标
     * @param g 画笔
     * @param direct 坦克方向，上下左右
     * @param type 坦克类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type){
        //根据坦克类型设置颜色
        switch(type){
            case 0://我们坦克
                g.setColor(Color.cyan);
                break;
            case 1://敌人
                g.setColor(Color.yellow);
                break;
        }

        //根据坦克方向，绘制坦克
        //0上1右边2下3左
        switch(direct){
            case 0://向上
                g.fill3DRect(x,y,10,60,false);//左边轮子
                g.fill3DRect(x+30,y,10,60,false);//右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10,y+20,20,20);//圆形盖子
                g.drawLine(x+20,y,x+20,y+30);//炮筒
                break;
            case 1://向右
                g.fill3DRect(x,y,60,10,false);//左边轮子
                g.fill3DRect(x,y+30,60,10,false);//右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20,y+10,20,20);//圆形盖子
                g.drawLine(x+30,y+20,x+60,y+20);//炮筒
                break;
            case 2:
                g.fill3DRect(x,y,10,60,false);//左边轮子
                g.fill3DRect(x+30,y,10,60,false);//右边轮子
                g.fill3DRect(x+10,y+10,20,40,false);//坦克盖子
                g.fillOval(x+10,y+20,20,20);//圆形盖子
                g.drawLine(x+20,y+60,x+20,y+30);//炮筒
                break;
            case 3:
                g.fill3DRect(x,y,60,10,false);//左边轮子
                g.fill3DRect(x,y+30,60,10,false);//右边轮子
                g.fill3DRect(x+10,y+10,40,20,false);//坦克盖子
                g.fillOval(x+20,y+10,20,20);//圆形盖子
                g.drawLine(x+30,y+20,x,y+20);//炮筒
                break;
            default:
                System.out.println("暂时没有");
        }
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_W){
            hero.setDirect(0);//设置向上的方向
            if(hero.getY()>0){
                hero.moveUp();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_S){
            hero.setDirect(2);
            if(hero.getY()+60<750){
                hero.moveDown();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_A){
            hero.setDirect(3);
            if(hero.getX()>0){
                hero.moveLeft();
            }

        }else if(e.getKeyCode()==KeyEvent.VK_D){
            hero.setDirect(1);
            if(hero.getX()+60<1000){
                hero.moveRight();
            }

        }
        //如果按下J，射击
        else if(e.getKeyCode()==KeyEvent.VK_J){
            System.out.println("按下了J");
            //发射一颗子弹
//            //判断当前hero的子弹是否消亡
//            //只判断为null,线程消亡了，但是对象没有消亡
//            if(hero.shot==null||!hero.shot.isLive){
//                hero.shotEnemyTank();
//            }
            //发射多颗子弹
            hero.shotEnemyTank();

        }

        this.repaint();

    }

    public void hitEnemyTank(){
        //多颗子弹
        for(int i=0;i<hero.shots.size();i++){
            Shot shot = hero.shots.get(i);
            if(shot!=null&&shot.isLive){
                //遍历敌人所有坦克
                for (int j = 0;j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    hitTank(hero.shot,enemyTank);
                }
            }
        }
        //判断是否击中敌人

        //单颗子弹
        if(hero.shot!=null&&hero.shot.isLive){
            //遍历敌人所有坦克
            for (int j = 0;j < enemyTanks.size(); j++) {
                EnemyTank enemyTank = enemyTanks.get(j);
                hitTank(hero.shot,enemyTank);
            }
        }

    }
    
    //击中我方坦克
    public void hitHero(){
        //遍历所有敌人坦克
        for (int i = 0; i < enemyTanks.size(); i++) {
            //取出敌人坦克
            EnemyTank enemyTank = enemyTanks.get(i);
            //遍历敌人坦克的所有子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中
                if(hero.isLive&&shot.isLive){
                    hitTank(shot,hero);
                }

            }
        }
    }

    //方法：判断我方子弹是否击中敌人坦克
    //方法变为子弹是否
    public  void hitTank(Shot s, Tank tank){
        switch (tank.getDirect()){
            case 0:
            case 2:
                if(s.x>tank.getX()&&s.x<tank.getX()+40
                            &&s.y>tank.getY()&&s.y<tank.getY()+60) {
                    s.isLive = false;//子弹死了
                    tank.isLive = false;
                    enemyTanks.remove(tank);
                    //当我的子弹击中敌人，将敌人移除
                    //当我方击毁坦克，allEnemyTankNum++
                    //判断传进来是enemytank，才对数量增加
                    if(tank instanceof EnemyTank){
                        Recorder.addallEnemyTankNum();
                    }

                    //创建Comb对象，加入到combs集合
                    Comb comb = new Comb(tank.getX(), tank.getY());
                    combs.add(comb);
                }
                break;
            case 1:
            case 3:
                if(s.x>tank.getX()&&s.x<tank.getX()+60
                    &&s.y>tank.getY()&&s.y<tank.getY()+40) {
                    s.isLive = false;
                    tank.isLive = false;
                    enemyTanks.remove(tank);
                    if(tank instanceof EnemyTank){
                        Recorder.addallEnemyTankNum();
                    }
                    //当我的子弹击中敌人，将敌人移除
                    //创建Comb对象，加入到combs集合
                    Comb comb = new Comb(tank.getX(), tank.getY());
                    combs.add(comb);
                }
                break;
        }
    }

    public void hitMetal(Shot s, Tank tank){
        for(int i=0;i<metals.size();i++){
            Metal metal = metals.get(i);
            if(s.x>=metal.getX()
                    &&s.x<=metal.getX()+40
                    &&s.y>=metal.getY()
                    &&s.y<=metal.getY()+40){
                s.isLive = false;
            }

        }
    }

    public void hitWood(Shot s, Tank tank){
        for(int i=0;i<woods.size();i++){
            Wood wood = woods.get(i);
            if(s.x>=wood.getX()
                    &&s.x<=wood.getX()+40
                    &&s.y>=wood.getY()
                    &&s.y<=wood.getY()+40){
                s.isLive = false;
                wood.isLive = false;
                woods.remove(wood);
            }
        }
    }

    public void heroHitBarrier(){
        for(int i=0;i<hero.shots.size();i++){
            Shot shot = hero.shots.get(i);
            hitMetal(shot,hero);
            hitWood(shot,hero);
        }
    }

    public void enemyHitBarreir(){
        for(int i=0;i<enemyTanks.size();i++){
            EnemyTank enemyTank = enemyTanks.get(i);
            for(int j=0;j<enemyTank.shots.size();j++){
                Shot shot = enemyTank.shots.get(j);
                hitMetal(shot,enemyTank);
                hitWood(shot,enemyTank);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            hitEnemyTank();
            hitHero();
            heroHitBarrier();
            enemyHitBarreir();
            this.repaint();
        }

    }
}

