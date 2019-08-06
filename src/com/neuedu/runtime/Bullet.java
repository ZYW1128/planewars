package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.util.List;
import java.awt.*;

public class Bullet extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private Image image1;

    private int speed = FrameConstant.GAME_SPEED * 5;

    public Bullet() {
        this(0, 0, ImageMap.get("mb02"));

    }


    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
        this.image1 = ImageMap.get("mb03");
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();

        GameFrame gameFrame = DataStore.get("gameFrame");
        if (gameFrame.count <17) {
            g.drawImage(image, getX() -image.getWidth(null)/2 +10, getY(), image.getWidth(null)/2  ,
                    image.getHeight(null) , null);
        } else {
            g.drawImage(image1, getX() -image1.getWidth(null)/2 +50, getY(), image1.getWidth(null) / 2,
                    image1.getHeight(null), null);
        }
    }

    @Override
    public void move() {
        setY(getY() - speed);

    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
        if (getY() < 30 - image1.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }
    }


    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //我方子弹击中敌方飞机
    public void collisionTesting(List<EnemyPlane> enemyPlaneList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlaneList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                enemyPlaneList.remove(enemyPlane);
                gameFrame.bulletList.remove(this);
                gameFrame.score += enemyPlane.getType() * 5;
                gameFrame.hp += 10;
                gameFrame.count++;

            }
        }

    }
    //我方子弹对boss的伤害

    public void collisionTesting1(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {

            gameFrame.bulletList.remove(this);
            gameFrame.bosshp -= 20;
        }

    }

}
