package tankGame3;

import java.util.Vector;

public class EnemyTank extends Tank implements Runnable {
    //敌人坦克类，使用Vector保存多个Shot
    boolean isLive = true;
    Vector<Shot> shots = new Vector<>();
    //增加成员：EnemyTank可以得到敌人坦克的Vector
    Vector<EnemyTank> enemyTanks = new Vector<>();
    Vector<Metal> metals=new Vector<>();
    Vector<Wood> woods=new Vector<>();

    public EnemyTank(int x, int y) {
        super(x, y);
    }


    //提供方法，将MyPanel的属性设置到EnemyTank
    public void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        this.enemyTanks = enemyTanks;
    }
    public void setMetals(Vector<Metal> metals) {this.metals = metals;}
    public void setWoods(Vector<Wood> woods) {this.woods = woods;}

    //编写方法，判断当前敌人坦克和enemyTanks中其他坦克是否碰撞
    public boolean isTouchEnemyTank(){
        switch (this.getDirect()){
            case 0://上,判断所有的敌人坦克,当前敌人坦克和其他敌人坦克比较
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank et = enemyTanks.get(i);
                    //不和自己比较
                    if(et!=this){
                        //如果敌人坦克上下
                        //敌人坦克坐标范围：[x,x+40][y,y+60]
                        //当前坦克左上右上坐标：[x,y] [x+40,y]
                        if(et.getDirect()==0||et.getDirect()==2){
                            //左上角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+40
                                    &&this.getY()>=et.getY()
                                    &&this.getY()<=et.getY()+60){
                                return true;
                            }
                            //右上角
                            if(this.getX()+40>=et.getX()
                                    &&this.getX()+40<=et.getX()+40
                                    &&this.getY()>=et.getY()
                                    &&this.getY()<=et.getY()+60){
                                return true;
                            }
                        }
                        //敌人坦克是左右
                        //敌人坦克坐标范围：[x,x+60][y,y+40]
                        //当前坦克左上右上坐标：[x,y] [x+40,y]
                        if(et.getDirect()==1||et.getDirect()==3){
                            //左上角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+60
                                    &&this.getY()>=et.getY()&&
                                    this.getY()<=et.getY()+40){
                                return true;
                            }
                            //右上角
                            if(this.getX()+40>=et.getX()
                                    &&this.getX()+40<=et.getX()+60
                                    &&this.getY()>=et.getY()&&
                                    this.getY()<=et.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank et = enemyTanks.get(i);
                    //不和自己比较
                    if(et!=this){
                        //如果敌人坦克上下
                        //敌人坦克坐标范围：[x,x+40][y,y+60]
                        //当前坦克左上右上坐标：[x+60,y] [x+60,y+40]
                        if(et.getDirect()==0||et.getDirect()==2){
                            //右上角
                            if(this.getX()+60>=et.getX()
                                    &&this.getX()+60<=et.getX()+40
                                    &&this.getY()>=et.getY()
                                    &&this.getY()<=et.getY()+60){
                                return true;
                            }
                            //右下角
                            if(this.getX()+60>=et.getX()
                                    &&this.getX()+60<=et.getX()+40
                                    &&this.getY()+40>=et.getY()
                                    &&this.getY()+40<=et.getY()+60){
                                return true;
                            }
                        }
                        //敌人坦克是左右
                        //敌人坦克坐标范围：[x,x+60][y,y+40]
                        //当前坦克左上右上坐标：[x+60,y] [x+60,y+40]
                        if(et.getDirect()==1||et.getDirect()==3){
                            //右上角
                            if(this.getX()+60>=et.getX()
                                    &&this.getX()+60<=et.getX()+60
                                    &&this.getY()>=et.getY()&&
                                    this.getY()<=et.getY()+40){
                                return true;
                            }
                            //右下角
                            if(this.getX()+60>=et.getX()
                                    &&this.getX()+60<=et.getX()+60
                                    &&this.getY()+40>=et.getY()&&
                                    this.getY()+40<=et.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank et = enemyTanks.get(i);
                    //不和自己比较
                    if(et!=this){
                        //如果敌人坦克上下
                        //敌人坦克坐标范围：[x,x+40][y,y+60]
                        //当前坦克左上右上坐标：[x,y+60] [x+40,y+60]
                        if(et.getDirect()==0||et.getDirect()==2){
                            //左下角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+40
                                    &&this.getY()+60>=et.getY()
                                    &&this.getY()+60<=et.getY()+60){
                                return true;
                            }
                            //右下角
                            if(this.getX()+40>=et.getX()
                                    &&this.getX()+40<=et.getX()+40
                                    &&this.getY()+60>=et.getY()
                                    &&this.getY()+60<=et.getY()+60){
                                return true;
                            }
                        }
                        //敌人坦克是左右
                        //敌人坦克坐标范围：[x,x+60][y,y+40]
                        //当前坦克左上右上坐标：[x,y+60] [x+40,y+60]
                        if(et.getDirect()==1||et.getDirect()==3){
                            //左下角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+60
                                    &&this.getY()+60>=et.getY()&&
                                    this.getY()+60<=et.getY()+40){
                                return true;
                            }
                            //右下角
                            if(this.getX()+40>=et.getX()
                                    &&this.getX()+40<=et.getX()+60
                                    &&this.getY()+60>=et.getY()&&
                                    this.getY()+60<=et.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for(int i=0;i<enemyTanks.size();i++){
                    EnemyTank et = enemyTanks.get(i);
                    //不和自己比较
                    if(et!=this){
                        //如果敌人坦克上下
                        //敌人坦克坐标范围：[x,x+40][y,y+60]
                        //当前坦克左上右上坐标：[x,y] [x,y+40]
                        if(et.getDirect()==0||et.getDirect()==2){
                            //左上角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+40
                                    &&this.getY()>=et.getY()
                                    &&this.getY()<=et.getY()+60){
                                return true;
                            }
                            //左下角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+40
                                    &&this.getY()+40>=et.getY()
                                    &&this.getY()+40<=et.getY()+60){
                                return true;
                            }
                        }
                        //敌人坦克是左右
                        //敌人坦克坐标范围：[x,x+60][y,y+40]
                        //当前坦克左上右上坐标：[x,y] [x+40,y]
                        if(et.getDirect()==1||et.getDirect()==3){
                            //左上角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+60
                                    &&this.getY()>=et.getY()&&
                                    this.getY()<=et.getY()+40){
                                return true;
                            }
                            //左下角
                            if(this.getX()>=et.getX()
                                    &&this.getX()<=et.getX()+60
                                    &&this.getY()+40>=et.getY()&&
                                    this.getY()+40<=et.getY()+40){
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return false;
    }

    public boolean isTouchMetal(){
        switch (this.getDirect()){
            case 0:
                for(int i=0;i<metals.size();i++){
                    Metal metal = metals.get(i);
                    if(this.getX()>=metal.getX()
                            &&this.getX()<=metal.getX()+40
                            &&this.getY()>=metal.getY()
                            &&this.getY()<=metal.getY()+40){
                        return true;
                    }
                    if(this.getX()+40>=metal.getX()
                            &&this.getX()+40<=metal.getX()+40
                            &&this.getY()>=metal.getY()
                            &&this.getY()<=metal.getY()+40){
                        return true;
                    }
                }
                break;
            case 1:
                for(int i=0;i<metals.size();i++){
                    Metal metal = metals.get(i);
                    if(this.getX()+60>=metal.getX()
                            &&this.getX()+60<=metal.getX()+40
                            &&this.getY()>=metal.getY()
                            &&this.getY()<=metal.getY()+40){
                        return true;
                    }
                    if(this.getX()+60>=metal.getX()
                            &&this.getX()+60<=metal.getX()+40
                            &&this.getY()+40>=metal.getY()
                            &&this.getY()+40<=metal.getY()+40){
                        return true;
                    }
                }
                break;
            case 2:
                for(int i=0;i<metals.size();i++){
                    Metal metal = metals.get(i);
                    if(this.getX()>=metal.getX()
                            &&this.getX()<=metal.getX()+40
                            &&this.getY()+60>=metal.getY()
                            &&this.getY()+60<=metal.getY()+40){
                        return true;
                    }
                    if(this.getX()+40>=metal.getX()
                            &&this.getX()+40<=metal.getX()+40
                            &&this.getY()+60>=metal.getY()
                            &&this.getY()+60<=metal.getY()+40){
                        return true;
                    }
                }
                break;
            case 3:
                for(int i=0;i<metals.size();i++){
                    Metal metal = metals.get(i);
                    if(this.getX()>=metal.getX()
                            &&this.getX()<=metal.getX()+40
                            &&this.getY()>=metal.getY()
                            &&this.getY()<=metal.getY()+40){
                        return true;
                    }
                    if(this.getX()>=metal.getX()
                            &&this.getX()<=metal.getX()+40
                            &&this.getY()+40>=metal.getY()
                            &&this.getY()+40<=metal.getY()+40){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    public boolean isTouchWood(){
        switch (this.getDirect()){
            case 0:
                for(int i=0;i<woods.size();i++){
                    Wood wood = woods.get(i);
                    if(this.getX()>=wood.getX()
                            &&this.getX()<=wood.getX()+40
                            &&this.getY()>=wood.getY()
                            &&this.getY()<=wood.getY()+40){
                        return true;
                    }
                    if(this.getX()+40>=wood.getX()
                            &&this.getX()+40<=wood.getX()+40
                            &&this.getY()>=wood.getY()
                            &&this.getY()<=wood.getY()+40){
                        return true;
                    }
                }
                break;
            case 1:
                for(int i=0;i<woods.size();i++){
                    Wood wood = woods.get(i);
                    if(this.getX()+60>=wood.getX()
                            &&this.getX()+60<=wood.getX()+40
                            &&this.getY()>=wood.getY()
                            &&this.getY()<=wood.getY()+40){
                        return true;
                    }
                    if(this.getX()+60>=wood.getX()
                            &&this.getX()+60<=wood.getX()+40
                            &&this.getY()+40>=wood.getY()
                            &&this.getY()+40<=wood.getY()+40){
                        return true;
                    }
                }
                break;
            case 2:
                for(int i=0;i<woods.size();i++){
                    Wood wood = woods.get(i);
                    if(this.getX()>=wood.getX()
                            &&this.getX()<=wood.getX()+40
                            &&this.getY()+60>=wood.getY()
                            &&this.getY()+60<=wood.getY()+40){
                        return true;
                    }
                    if(this.getX()+40>=wood.getX()
                            &&this.getX()+40<=wood.getX()+40
                            &&this.getY()+60>=wood.getY()
                            &&this.getY()+60<=wood.getY()+40){
                        return true;
                    }
                }
                break;
            case 3:
                for(int i=0;i<woods.size();i++){
                    Wood wood = woods.get(i);
                    if(this.getX()>=wood.getX()
                            &&this.getX()<=wood.getX()+40
                            &&this.getY()>=wood.getY()
                            &&this.getY()<=wood.getY()+40){
                        return true;
                    }
                    if(this.getX()>=wood.getX()
                            &&this.getX()<=wood.getX()+40
                            &&this.getY()+40>=wood.getY()
                            &&this.getY()+40<=wood.getY()+40){
                        return true;
                    }
                }
                break;
        }
        return false;
    }

    @Override
    public void run() {
        while(true){
            //判断集合是否为空，为空就是没有子弹
            //创建一颗子弹，放入到shots集合中
            Shot s = null;
            if(isLive&&shots.size()==0){
                //想要发射多颗子弹，shots.size()<5
                //看坦克方向，再创建对应的子弹
                switch (getDirect()){
                    case 0:
                        s = new Shot(getX()+20,getY(),0);
                        break;
                    case 1:
                        s = new Shot(getX()+60,getY()+20,1);
                        break;
                    case 2:
                        s = new Shot(getX()+20,getY()+60,2);
                        break;
                    case 3:
                        s = new Shot(getX(),getY()+20,3);
                        break;
                }
                //加入到集合并启动子弹
                shots.add(s);
                new Thread(s).start();
            }
            //根据坦克的方向继续移动
            switch (getDirect()){
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if(getY()>0&&!isTouchEnemyTank()&&!isTouchMetal()&&!isTouchWood()){
                            moveUp();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if(getX()+60<1000&&!isTouchEnemyTank()&&!isTouchMetal()&&!isTouchWood()){
                            moveRight();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if(getY()+60<750&&!isTouchEnemyTank()&&!isTouchMetal()&&!isTouchWood()){
                            moveDown();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if(getX()>0&&!isTouchEnemyTank()&&!isTouchMetal()&&!isTouchWood()){
                            moveLeft();
                        }

                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    break;
            }
            //休眠

            //随机改变方向获得0-3随机整数
            setDirect((int)(Math.random()*4));
            if(!isLive){
                break;
            }
        }
    }
}
