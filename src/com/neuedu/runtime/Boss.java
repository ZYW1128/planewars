package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.main.GameFrame;
import com.neuedu.util.DataStore;
import com.neuedu.util.ImageMap;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Boss extends BaseSprite implements Drawable, Moveable {

    private List<Image> imageList = new ArrayList<>();

    private int speed = FrameConstant.GAME_SPEED * 2;

    private boolean right = true;

    private Random random = new Random();

    public Boss() {
//        for (int i = 1; i <10 ; i++) {
//            imageList.add(ImageMap.get("index" + i));
//
//        }
        imageList.add(ImageMap.get("boss1"));
        imageList.add(ImageMap.get("boss2"));
        imageList.add(ImageMap.get("boss3"));
        imageList.add(ImageMap.get("boss4"));
        imageList.add(ImageMap.get("boss5"));
        imageList.add(ImageMap.get("boss6"));
        imageList.add(ImageMap.get("boss7"));
        imageList.add(ImageMap.get("boss8"));
        imageList.add(ImageMap.get("boss9"));
    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        GameFrame gameFrame = DataStore.get("gameFrame");
            move();
            fire();
            borderTesting();
            g.drawImage(imageList.get(index++ / 8), getX(), getY(), imageList.get(index++ / 8).getWidth(null),
                    imageList.get(index++ / 8).getHeight(null), null);

            if (index >= 72) {
                index = 0;
            }
    }

    @Override
    public void move() {
        if (right) {
            setX(getX() + speed);
        } else {
            setX(getX() - speed);
        }
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 985) {
            gameFrame.enemyBulletList.add(
                    new EnemyBullet(getX() + imageList.get(index / 8).getWidth(null) - ImageMap.get("epb04").getWidth(null),
                            getY() + imageList.get(index / 8).getHeight(null), ImageMap.get("epb04")));

        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(index / 8).getWidth(null),
                imageList.get(index / 8).getHeight(null));
    }

    public void borderTesting() {
        if (getX() + imageList.get(index / 8).getWidth(null) > FrameConstant.FRAME_WIDTH) {
            right = false;
        } else if (getX() < 0) {
            right = true;
        }
    }
}
