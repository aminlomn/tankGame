package tankGame3;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Scanner;

public class MyTankGrame03 extends JFrame {
    //定义一个MyPanel
    MyPannel mp = null;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        MyTankGrame03 myTankGrame01 = new MyTankGrame03();



    }
    public MyTankGrame03() {
        System.out.println("请输入选择1：新游戏   2：继续游戏");
        String key = sc.next();
        mp = new MyPannel(key);
        //将mp放入Thread并启动
        Thread thread = new Thread(mp);
        thread.start();
        this.add(mp);
        this.setSize(1300,950);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        //在JFrame中增加相应关闭窗口的处理
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                //System.out.println("关闭窗口");
                //记录信息
                Recorder.keepRecorder();
                System.exit(0);
            }
        });
    }

}
