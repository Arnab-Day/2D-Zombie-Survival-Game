package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Zombie {
    int x,y;
    int speed=2;
    int width=35,height=35;
    public Zombie(int x,int y){this.x=x;this.y=y;}
    public void moveToward(Player player){
        if(x<player.x) x+=speed;
        if(x>player.x) x-=speed;
        if(y<player.y) y+=speed;
        if(y>player.y) y-=speed;
    }
    public void draw(Graphics g){g.setColor(Color.GREEN);g.fillRect(x,y,width,height);}
    public Rectangle getBounds(){return new Rectangle(x,y,width,height);}
}
