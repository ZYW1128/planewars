package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.base.Moveable;
import com.neuedu.util.ImageMap;
import com.neuedu.util.ImageUtil;

import java.awt.*;

public class Background extends BaseSprite implements Moveable, Drawable {

    private Image Image;

    public Background() {

        this(0,0, ImageMap.get("bg01"));
    }

    public Background(int x, int y, Image image) {
        super(x, y);
        this.Image = image;
    }

    @Override
    public void move() {
        setY(getY()-1);
    }

    @Override
    public void draw(Graphics g) {
    g.drawImage(Image,getX(),getY(),Image.getWidth(null),Image.getHeight(null),null);
    move();
    }
}
