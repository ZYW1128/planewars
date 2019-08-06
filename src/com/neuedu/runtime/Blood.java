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


public class Blood extends BaseSprite implements Moveable, Drawable {

    private Image image;

    private Image image1;

    private int speed = FrameConstant.GAME_SPEED * 2;

    private Random random = new Random();

    public Blood() {
        this(0, 0, ImageMap.get("blood01"));
    }

    public Blood(int x, int y, Image image) {
        super(x, y);
        this.image = image;
//        this.image1 = ImageMap.get("blood02");
    }

    @Override
    public void draw(Graphics g) {
        move();
        borderTesting();
//        GameFrame gameFrame = DataStore.get("gameFrame");
//        if (gameFrame.hp < 100) {
            g.drawImage(image, getX(), getY(), image.getWidth(null),
                    image.getHeight(null), null);
//        }
//        if (gameFrame.hp > 100) {
//            g.drawImage(image1, getX(), getY(), image1.getWidth(null),
//                    image1.getHeight(null), null);
//        }
    }

    @Override
    public void move() {
        setY(getY() + speed);

    }

    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bloodList.remove(this);
        }
    }

    @Override
    public Rectangle getRectangle() {
//        GameFrame gameFrame = DataStore.get("gameFrame");
//        if (gameFrame.hp < 100) {
            return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
//        }
//        else {
//            return new Rectangle(getX(), getY(), image1.getWidth(null), image1.getHeight(null));
//        }
    }


    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.bloodList.remove(this);
            if (gameFrame.hp <100){
                gameFrame.hp += 10;
            }
        }
    }
}
