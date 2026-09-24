package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player {
    int x = 400;
    int y = 300;
    int health = 100;
    int speed = 5;
    int width = 40;
    int height = 40;

    public void move(boolean up, boolean down, boolean left, boolean right) {
        if (up) y -= speed;
        if (down) y += speed;
        if (left) x -= speed;
        if (right) x += speed;
        if (x < 0) x = 0;
        if (y < 55) y = 55;
        if (x > 800 - width) x = 800 - width;
        if (y > 600 - height) y = 600 - height;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(40, 120, 220));
        g.fillRoundRect(x, y + 10, width, height - 10, 10, 10);
        g.setColor(new Color(255, 200, 150));
        g.fillOval(x + 8, y, 24, 24);
        g.setColor(Color.BLACK);
        g.fillArc(x + 8, y - 2, 24, 15, 0, 180);
        g.setColor(Color.BLACK);
        g.fillOval(x + 14, y + 9, 3, 3);
        g.fillOval(x + 23, y + 9, 3, 3);
        g.setColor(Color.WHITE);
        g.fillRect(x + 17, y + 25, 6, 10);
    }
    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
}
