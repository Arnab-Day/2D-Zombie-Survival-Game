package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
    int x,y; int speed=8;
    public Bullet(int x,y){this.x=x;this.y=y;}
    public void move(){x+=speed;}
    public void draw(Graphics g){g.setColor(Color.YELLOW);g.fillRect(x,y,10,5);}
    public Rectangle getBounds(){return new Rectangle(x,y,10,5);}
}
