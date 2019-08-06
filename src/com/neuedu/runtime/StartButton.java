package com.neuedu.runtime;

import com.neuedu.base.BaseSprite;
import com.neuedu.base.Drawable;
import com.neuedu.util.ImageMap;

import java.awt.*;

public class StartButton extends BaseSprite implements Drawable {

    private  Image image;

    public StartButton() {
        this(320,400, ImageMap.get("start"));
    }

    public StartButton(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image,getX(),getY(),image.getWidth(null),
                image.getHeight(null),null);
    }
}
