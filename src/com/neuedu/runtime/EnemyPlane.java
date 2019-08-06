package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.Random;

public class EnemyPlane extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private Image image2;

    private Image image3;

    private int speed = FrameConstant.GAME_SPEED * 2;

    private Random random = new Random();

    private int type;

    public int getType() {
        return type;
    }

    public EnemyPlane() {
        this(0, 0, 1);

    }

    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("ep01");
        this.image2 = ImageMap.get("ep02");
        this.image3 = ImageMap.get("ep03");
    }


    @Override
    public void draw(Graphics g) {

        move();
        borderTesting();
        fire();
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        }
        if (type == 2) {
            g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getHeight(null), null);
        }
        if (type == 5) {
            g.drawImage(image3, getX(), getY(), image3.getWidth(null), image3.getHeight(null), null);
        }


    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (type == 1) {
            if (random.nextInt(1000) > 985) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image.getWidth(null) / 2) -
                        (ImageMap.get("epb01").getWidth(null) / 2),
                        getY() + image.getHeight(null),
                        ImageMap.get("epb01")));
            }
        } else if (type == 2) {
            if (random.nextInt(1000) > 990) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image2.getWidth(null) / 2) -
                        (ImageMap.get("epb02").getWidth(null) / 2 + 10),
                        getY() + image2.getHeight(null),
                        ImageMap.get("epb02")));
            }
        } else if (type == 5) {
            if (random.nextInt(1000) > 995) {
                gameFrame.enemyBulletList.add(new EnemyBullet(getX() + (image3.getWidth(null) / 2) -
                        (ImageMap.get("epb03").getWidth(null) / 2 + 20),
                        getY() + image3.getHeight(null),
                        ImageMap.get("epb03")));
            }
        }
    }


    private boolean right = true;

    @Override
    public void move() {
        if (type == 1) {
            setY(getY() + speed);

        } else if (type == 2) {
            if (right) {
                setX(getX() + speed);
                setY(getY() + speed);
            } else {
                setX(getX() - speed);
            }
        } else {
            setY(getY() + speed);
        }
    }

    public void borderTesting() {

        if (type == 1) {

            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);

            }
        } else if (type == 2) {
            if (getX() + image2.getWidth(null) > FrameConstant.FRAME_WIDTH) {
                right = false;
            } else if (getX() < 0) {
                right = true;
            }
        } else {
            if (getY() > FrameConstant.FRAME_HEIGHT) {
                GameFrame gameFrame = DataStore.get("gameFrame");
                gameFrame.enemyPlaneList.remove(this);
            }
        }
    }

    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(),
                image.getWidth(null), image.getHeight(null));
    }
}
