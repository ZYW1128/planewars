package com.neuedu.main;

import com.neuedu.constant.FrameConstant;
import com.neuedu.runtime.*;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {

    //创建背景图片
    private Background background = new Background();

    //创建开始按钮图标
    private StartButton startButton = new StartButton();

    //创建开始数组
    public final List<StartButton> start = new CopyOnWriteArrayList<>();

    //创建飞机图片
    private Plane plane = new Plane();

    //创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();

    //创建敌军飞机集合
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();

    //创建敌方子弹集合
    public final List<EnemyBullet> enemyBulletList = new CopyOnWriteArrayList<>();

    //创建道具集合
    public final List<Blood> bloodList = new CopyOnWriteArrayList<>();

    //创建boss子弹集合
    public final List<BossBullet> bossBulletList = new CopyOnWriteArrayList<>();

    public boolean gameOver = false;
    public boolean drawBoss = true;
    public boolean pause = true;

    //开始
    public boolean gameStart = false;

    private Boss boss = new Boss();

    //得分
    public int score = 0;

    //生命
    public int hp = 100;

    public int count = 0;

    private Random random = new Random();

    //boss生命
    public int bosshp = 1000;


    @Override
    public void paint(Graphics g) {
        background.draw(g);
        for (StartButton startButton : start) {
            startButton.draw(g);
        }
        if (!gameOver && gameStart ) {

            plane.draw(g);
            if (count > 25) {
                if(drawBoss){
                    boss.draw(g);
                    g.setFont(new Font("楷体", Font.BOLD, 23));
                    g.setColor(Color.RED);
                    g.drawRect(650, 110, 80, 17);
                    g.fillRect(650, 110, bosshp / 10, 17);
                    g.drawString("boss生命值:" + bosshp, 600, 150);
                    background.setSpeed(0);
                    for (Bullet bullet : bulletList) {
                        bullet.collisionTesting1(boss);

                    }
                }
                if(bosshp<=0){
                    gameOver = true;
                    drawBoss = false;
                    background.setSpeed(FrameConstant.GAME_SPEED);
                }

            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);

            }
            for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.draw(g);
            }
            for (Blood blood : bloodList) {
                blood.draw(g);
            }


            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);

            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.draw(g);
            }
           for (EnemyBullet enemyBullet : enemyBulletList) {
                enemyBullet.collisionxeTesting(plane);
           }
            for (Blood blood : bloodList) {
                blood.collisionTesting(plane);
            }
            for (BossBullet bossBullet : bossBulletList) {
                bossBullet.collisionTesting(plane);
            }
            for (StartButton startButton : start) {
                start.remove(startButton);
            }
            g.setFont(new Font("宋体", Font.BOLD, 25));
            g.setColor(new Color(0x2FFFD6));
            g.drawString("得分：" + score, 40, 80);
            g.setColor(new Color(0x2FFFD6));
            g.drawString("生命值" + hp, 40, 180);
            g.setColor(new Color(0x2FFFD6));
            g.drawString("击中个数" + count, 40, 280);
            //boss血条



            //随机生成飞机
            if (!gameOver) {
                if (random.nextInt(1000) > 985) {
                    if (count < 6) {
                        enemyPlaneList.add(new EnemyPlane(random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep01").getWidth(null)),
                                30 - ImageMap.get("ep01").getHeight(null), 1));

                    }
                    if (count >= 6 && count < 12) {
                        enemyPlaneList.add(new EnemyPlane(random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep02").getWidth(null)),
                                30 - ImageMap.get("ep02").getHeight(null), 2));

                    }
                    if (count >= 12) {
                        enemyPlaneList.add(new EnemyPlane(random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("ep03").getWidth(null)),
                                30 - ImageMap.get("ep03").getHeight(null), 5));

                    }
                }

            }


            if (!gameOver) {
                if (random.nextInt(1000) > 990) {
                    if (hp < 100) {
                        bloodList.add(new Blood(
                                random.nextInt(FrameConstant.FRAME_WIDTH - ImageMap.get("blood01").getWidth(null)),
                                30 - ImageMap.get("blood01").getHeight(null), ImageMap.get("blood01")));
                    }
                }

            }


        }
        if (gameOver) {
            g.setFont(new Font("宋体", Font.BOLD, 50));
            g.setColor(new Color(0xFF2415));
            g.drawString("游戏结束！", 300, 400);
        }
    }

    /**
     * 使用这个方法初始化窗口
     */


    public void init() {

        //设置好尺寸
        setSize(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        //不让启动窗口时有图片
        enableInputMethods(false);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        addKeyListener(new KeyAdapter() {

            //添加键盘监听
            @Override
            public void keyPressed(KeyEvent e) {
                plane.keyPressed(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }


        });
//        addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
//            }
//        });
        start.add(new StartButton());
        //设置鼠标监听
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getX() > startButton.getX() &&
                        e.getX() < startButton.getX() + ImageMap.get("start").getWidth(null) &&
                        e.getY() < ImageMap.get("start").getHeight(null) + startButton.getY() &&
                        e.getY() > startButton.getY()) {
                    gameStart = true;
                }
            }
        });

        new Thread() {
            @Override
            public void run() {

                while (true) {
                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();


        //游戏初始前添加一些敌军飞机,道具 固定
//            enemyPlaneList.add(new EnemyPlane(50,30, 1));
//            enemyPlaneList.add(new EnemyPlane(150,30, 1));
//            enemyPlaneList.add(new EnemyPlane(250,70, 1));
//            enemyPlaneList.add(new EnemyPlane(50,150, 2));
//            enemyPlaneList.add(new EnemyPlane(455,-900, 3));
//            bloodList.add(new Blood(0,0, ImageMap.get("blood01")));
//            bloodList.add(new Blood(150,-100, ImageMap.get("blood01")));
//            bloodList.add(new Blood(350,-500, ImageMap.get("blood01")));
//            bloodList.add(new Blood(450,-700, ImageMap.get("blood01")));

        setVisible(true);
    }

    private Image offScreenImage = null;//创建缓冲区

    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDTH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics gOff = offScreenImage.getGraphics();//在图片缓冲区绘图
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲图片绘制到窗口目标
    }

}
