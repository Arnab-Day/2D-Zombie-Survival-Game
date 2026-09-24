package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Zombie {
    int x, y;
    int speed = 2;
    int width = 35;
    int height = 35;
    public Zombie(int x, int y) { this.x = x; this.y = y; }
    public void moveToward(Player player) {
        if (x < player.x) x += speed;
        if (x > player.x) x -= speed;
        if (y < player.y) y += speed;
        if (y > player.y) y -= speed;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(70, 170, 70));
        g.fillRoundRect(x, y + 10, width, height - 10, 8, 8);
        g.setColor(new Color(90, 190, 90));
        g.fillOval(x + 3, y, 29, 27);
        g.setColor(Color.RED);
        g.fillOval(x + 9, y + 8, 6, 6);
        g.fillOval(x + 21, y + 8, 6, 6);
        g.setColor(Color.BLACK);
        g.fillRect(x + 10, y + 19, 16, 4);
        g.setColor(Color.WHITE);
        g.fillRect(x + 12, y + 19, 3, 4);
        g.fillRect(x + 20, y + 19, 3, 4);
    }
    public Rectangle getBounds() { return new Rectangle(x, y, width, height); }
}
