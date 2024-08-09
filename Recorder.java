package tankGame3;

import java.io.*;
import java.util.Vector;

/**
 * @author min
 * @version 1.0.0
 * 该类记录相关信息，和文件交互
 * 该方法在继续上句游戏时候调用
 */
public class Recorder {
    //定义变量，记录击毁信息
    private static int allEnemyTankNum = 0;
    //定义IO对象
    private static BufferedWriter bw = null;

    private static BufferedReader br = null;

    private static String recordFileName = "src\\tankGame3\\myRecord.txt";

    //定义一个Node Vector用于保存敌人信息
    private static Vector<Node> nodes = new Vector<>();

    //定义Vector，指向MyPanel的EnemyTank Vector
    private static Vector<EnemyTank> enemyTanks = null;

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {
        Recorder.enemyTanks = enemyTanks;
    }

    public static int getAllEnemyTankNum() {
        return allEnemyTankNum;
    }

    public static void setAllEnemyTankNum(int allEnemyTankNum) {
        Recorder.allEnemyTankNum = allEnemyTankNum;
    }

    //当我方坦克击毁一个敌人坦克，就allEnemyTankNum++
    public static void addallEnemyTankNum(){
        Recorder.allEnemyTankNum++;
    }
    //游戏退出，将addallEnemyTankNum保存到文件中
    //保存敌人坦克的坐标和方向，一行一个敌人坦克
    //遍历敌人坦克Vector，然后根据情况保存
    //oop:Mypanel类有敌人坦克的Vector，定义一个属性，然后通过set方法得到敌人的Vector
    public static void keepRecorder(){
        try {
            bw = new BufferedWriter(new FileWriter(recordFileName));
            bw.write(allEnemyTankNum+"\r\n");

            for(int i=0;i<enemyTanks.size();i++){
                EnemyTank enemyTank = enemyTanks.get(i);
                if(enemyTank.isLive){
                    //保存信息
                    String record = enemyTank.getX()+" "+enemyTank.getY()+" "+enemyTank.getDirect();
                    bw.write(record+"\r\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(bw!=null){
                    bw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    //增加一个方法用于读取文件，恢复相关信息,生成一个Node集合
    public static Vector<Node> getNodesAddEnemyTankRec(){
        try {
            br = new BufferedReader(new FileReader(recordFileName));
            allEnemyTankNum = Integer.parseInt(br.readLine());//读取击败坦克数量
            //循环读取坦克信息，生成Nodes
            String line = "";//255 50 1
            while((line=br.readLine())!=null){
                String[] temp = line.split(" ");
                Node node = new Node(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]));
                nodes.add(node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(br!=null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return nodes;
    }
    //返回记录文件地址的函数
    public static String getRecordFileName(){
        return recordFileName;
    }
}
