package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Bullet {
    int x, y;
    int speed = 8;
    public Bullet(int x, int y) { this.x = x; this.y = y; }
    public void move() { x += speed; }
    public void draw(Graphics g) {
        g.setColor(new Color(255, 180, 0));
        g.fillOval(x - 2, y - 2, 14, 9);
        g.setColor(Color.YELLOW);
        g.fillOval(x, y, 10, 5);
    }
    public Rectangle getBounds() { return new Rectangle(x, y, 10, 5); }
}
