package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.constant.FrameConstant;
import com.neuedu.util.ImageMap;


import java.awt.*;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image Image;

    private int speed = FrameConstant.GAME_SPEED;

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Background() {

        this(0, FrameConstant.FRAME_HEIGHT -ImageMap.get("bg01").getHeight(null), ImageMap.get("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.Image = image;
    }

    @Override
    public void move() {
        setY(getY() + speed);
    }

    @Override
    public void draw(Graphics g) {
        move();
        g.drawImage(Image,getX(),getY(),Image.getWidth(null),Image.getHeight(null),null);

    }
}
