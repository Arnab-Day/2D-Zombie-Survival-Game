package pkg2dzombiesurvivalgame;

import java.awt.Color;
import java.awt.Graphics;

public class Player {
    int x = 400;
    int y = 300;
    int health = 100;
    int speed = 5;
    int width = 40;
    int height = 40;

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
    }
}
