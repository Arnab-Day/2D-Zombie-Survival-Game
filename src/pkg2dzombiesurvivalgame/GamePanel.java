package pkg2dzombiesurvivalgame;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

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

        setBackground(new Color(20, 45, 25));

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

        if (gameOver)
            return;

        int x = random.nextInt(screenWidth - 40);

        int y = 70 + random.nextInt(screenHeight - 110);

        zombies.add(new Zombie(x, y));
    }

    private void updateGame() {

        if (gameOver)
            return;

        player.move(up, down, left, right);

        for (Zombie zombie : zombies)
            zombie.moveToward(player);

        for (Bullet bullet : bullets)
            bullet.move();

        // Bullet-Zombie collision
        for (int i = bullets.size() - 1; i >= 0; i--) {

            Bullet bullet = bullets.get(i);

            for (int j = zombies.size() - 1; j >= 0; j--) {

                Zombie zombie = zombies.get(j);

                if (bullet.getBounds().intersects(zombie.getBounds())) {

                    bullets.remove(i);
                    zombies.remove(j);

                    score++;

                    if (score % 10 == 0)
                        increaseDifficulty();

                    break;
                }
            }
        }

        // Zombie-Player collision
        for (Zombie zombie : zombies) {

            if (zombie.getBounds().intersects(player.getBounds())) {

                player.health -= 1;
            }
        }

        // Game Over
        if (player.health <= 0) {

            player.health = 0;

            gameOver = true;

            spawnTimer.stop();
        }

        // Remove bullets outside screen
        bullets.removeIf(bullet -> bullet.x > screenWidth);

        repaint();
    }

    private void increaseDifficulty() {

        spawnDelay -= 300;

        if (spawnDelay < 500)
            spawnDelay = 500;

        spawnTimer.setDelay(spawnDelay);
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Smooth graphics
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        drawBackground(g2);

        drawHUD(g2);

        // Draw zombies
        for (Zombie zombie : zombies)
            zombie.draw(g2);

        // Draw player
        player.draw(g2);

        // Draw bullets
        for (Bullet bullet : bullets)
            bullet.draw(g2);

        if (gameOver)
            drawGameOver(g2);
    }

    private void drawBackground(Graphics2D g) {

        // Main background
        g.setColor(new Color(25, 55, 30));

        g.fillRect(0, 0, screenWidth, screenHeight);

        // Ground grid
        g.setColor(new Color(35, 70, 40));

        for (int x = 0; x < screenWidth; x += 40) {

            g.drawLine(x, 55, x, screenHeight);
        }

        for (int y = 55; y < screenHeight; y += 40) {

            g.drawLine(0, y, screenWidth, y);
        }

        // Game border
        g.setColor(new Color(100, 160, 100));

        g.drawRect(
                0,
                55,
                screenWidth - 1,
                screenHeight - 56
        );
    }

    private void drawHUD(Graphics2D g) {

        // HUD background
        g.setColor(new Color(15, 20, 25));

        g.fillRect(0, 0, screenWidth, 55);

        // Title
        g.setColor(Color.WHITE);

        g.setFont(new Font("Arial", Font.BOLD, 18));

        g.drawString(
                "ZOMBIE SURVIVAL",
                20,
                23
        );

        // Score
        g.setFont(new Font("Arial", Font.BOLD, 14));

        g.drawString(
                "Score: " + score,
                300,
                22
        );

        // Health text
        g.drawString(
                "Health",
                520,
                22
        );

        // Health bar background
        g.setColor(Color.DARK_GRAY);

        g.fillRoundRect(
                575,
                10,
                180,
                20,
                10,
                10
        );

        // Health bar
        int healthWidth =
                (int) (180 * (player.health / 100.0));

        if (player.health > 60) {

            g.setColor(new Color(40, 200, 70));

        } else if (player.health > 30) {

            g.setColor(new Color(240, 180, 40));

        } else {

            g.setColor(new Color(220, 50, 50));
        }

        g.fillRoundRect(
                575,
                10,
                healthWidth,
                20,
                10,
                10
        );

        // Health value
        g.setColor(Color.WHITE);

        g.drawString(
                player.health + "/100",
                635,
                25
        );
    }

    private void drawGameOver(Graphics2D g) {

        // Dark transparent overlay
        g.setColor(new Color(0, 0, 0, 180));

        g.fillRect(
                0,
                0,
                screenWidth,
                screenHeight
        );

        // Game Over title
        g.setColor(new Color(240, 60, 60));

        g.setFont(
                new Font("Arial", Font.BOLD, 48)
        );

        g.drawString(
                "GAME OVER",
                270,
                260
        );

        // Final score
        g.setColor(Color.WHITE);

        g.setFont(
                new Font("Arial", Font.BOLD, 20)
        );

        g.drawString(
                "Final Score: " + score,
                325,
                305
        );

        // Restart instruction
        g.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        g.drawString(
                "Press R to Restart",
                330,
                345
        );
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                up = true;
                break;

            case KeyEvent.VK_S:
                down = true;
                break;

            case KeyEvent.VK_A:
                left = true;
                break;

            case KeyEvent.VK_D:
                right = true;
                break;

            case KeyEvent.VK_SPACE:

                if (!gameOver) {

                    bullets.add(
                            new Bullet(
                                    player.x + player.width,
                                    player.y + player.height / 2
                            )
                    );
                }

                break;

            case KeyEvent.VK_R:

                if (gameOver)
                    restartGame();

                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {

            case KeyEvent.VK_W:
                up = false;
                break;

            case KeyEvent.VK_S:
                down = false;
                break;

            case KeyEvent.VK_A:
                left = false;
                break;

            case KeyEvent.VK_D:
                right = false;
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void restartGame() {

        player = new Player();

        zombies.clear();

        bullets.clear();

        score = 0;

        gameOver = false;

        spawnDelay = 2000;

        spawnTimer.setDelay(spawnDelay);

        spawnTimer.start();
    }
}