package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    int x=400; int y=300;
    int health=100; int speed=5;
    int width=40; int height=40;

    public void move(boolean up, boolean down, boolean left, boolean right) {
        if (up) y-=speed;
        if (down) y+=speed;
        if (left) x-=speed;
        if (right) x+=speed;
        if (x<0) x=0;
        if (y<0) y=0;
        if (x>800-width) x=800-width;
        if (y>600-height) y=600-height;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x,y,width,height);
    }

    public Rectangle getBounds() { return new Rectangle(x,y,width,height); }
}
