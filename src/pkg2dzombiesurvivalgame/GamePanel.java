package pkg2dzombiesurvivalgame;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class GamePanel extends JPanel implements KeyListener {
    final int screenWidth = 800;
    final int screenHeight = 600;
    Player player;
    ArrayList<Zombie> zombies = new ArrayList<>();
    ArrayList<Bullet> bullets = new ArrayList<>();
    boolean up, down, left, right;
    int score = 0;
    boolean gameOver = false;
    Random random = new Random();
    Timer gameTimer;
    Timer spawnTimer;
    int spawnDelay = 2000;

    public GamePanel() {
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        setBackground(Color.BLACK);
        setFocusable(true);
        player = new Player();
        addKeyListener(this);
        requestFocusInWindow();
        gameTimer = new Timer(16, e -> updateGame());
        gameTimer.start();
        spawnTimer = new Timer(spawnDelay, e -> spawnZombie());
        spawnTimer.start();
    }
    private void spawnZombie() {
        if (gameOver) return;
        int x = random.nextInt(screenWidth - 40);
        int y = random.nextInt(screenHeight - 40);
        zombies.add(new Zombie(x, y));
    }
    private void updateGame() {
        if (gameOver) return;
        player.move(up, down, left, right);
        for (Zombie zombie : zombies) zombie.moveToward(player);
        for (Bullet bullet : bullets) bullet.move();
        for (int i = bullets.size() - 1; i >= 0; i--) {
            Bullet bullet = bullets.get(i);
            for (int j = zombies.size() - 1; j >= 0; j--) {
                Zombie zombie = zombies.get(j);
                if (bullet.getBounds().intersects(zombie.getBounds())) {
                    bullets.remove(i); zombies.remove(j); score++;
                    if (score % 10 == 0) increaseDifficulty();
                    break;
                }
            }
        }
        for (Zombie zombie : zombies) {
            if (zombie.getBounds().intersects(player.getBounds())) player.health -= 1;
        }
        if (player.health <= 0) {
            player.health = 0; gameOver = true; spawnTimer.stop();
        }
        bullets.removeIf(bullet -> bullet.x > screenWidth);
        repaint();
    }
    private void increaseDifficulty() {
        spawnDelay -= 300;
        if (spawnDelay < 500) spawnDelay = 500;
        spawnTimer.setDelay(spawnDelay);
    }
    @Override protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("Zombie Survival Game", 330, 30);
        g.drawString("Score: " + score, 20, 30);
        g.drawString("Health: " + player.health, 20, 50);
        for (Zombie zombie : zombies) zombie.draw(g);
        player.draw(g); for (Bullet bullet : bullets) bullet.draw(g);
        if (gameOver) {
            g.setColor(Color.RED); g.drawString("GAME OVER", 350, 280);
            g.setColor(Color.WHITE); g.drawString("Final Score: " + score, 345, 310);
            g.drawString("Press R to Restart", 330, 340);
        }
    }
    @Override public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = true; break; case KeyEvent.VK_S: down = true; break;
            case KeyEvent.VK_A: left = true; break; case KeyEvent.VK_D: right = true; break;
            case KeyEvent.VK_SPACE: if (!gameOver) bullets.add(new Bullet(player.x + player.width, player.y + player.height / 2)); break;
            case KeyEvent.VK_R: if (gameOver) restartGame(); break;
        }
    }
    @Override public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W: up = false; break; case KeyEvent.VK_S: down = false; break;
            case KeyEvent.VK_A: left = false; break; case KeyEvent.VK_D: right = false; break;
        }
    }
    @Override public void keyTyped(KeyEvent e) {}
    private void restartGame() {
        player = new Player(); zombies.clear(); bullets.clear(); score = 0; gameOver = false;
        spawnDelay = 2000; spawnTimer.setDelay(spawnDelay); spawnTimer.start();
    }
}
