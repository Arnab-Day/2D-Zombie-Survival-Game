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
    final int screenWidth=800, screenHeight=600;
    Player player;
    ArrayList<Zombie> zombies=new ArrayList<>();
    ArrayList<Bullet> bullets=new ArrayList<>();
    boolean up,down,left,right;
    int score=0;
    Random random=new Random();

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(Color.BLACK); setFocusable(true); player=new Player();
        addKeyListener(this); requestFocusInWindow();
        for(int i=0;i<5;i++){
            int x=random.nextInt(screenWidth-40); int y=random.nextInt(screenHeight-40);
            zombies.add(new Zombie(x,y));
        }
        Timer timer=new Timer(16,e->updateGame()); timer.start();
    }
    private void updateGame(){
        player.move(up,down,left,right);
        for(Zombie zombie:zombies) zombie.moveToward(player);
        for(Bullet bullet:bullets) bullet.move();
        for(int i=bullets.size()-1;i>=0;i--){
            Bullet bullet=bullets.get(i);
            for(int j=zombies.size()-1;j>=0;j--){
                Zombie zombie=zombies.get(j);
                if(bullet.getBounds().intersects(zombie.getBounds())){
                    bullets.remove(i); zombies.remove(j); score++; break;
                }
            }
        }
        bullets.removeIf(b->b.x>screenWidth); repaint();
    }
    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE); g.drawString("Zombie Survival Game",330,30);
        g.drawString("Score: "+score,20,30);
        player.draw(g); for(Zombie zombie:zombies) zombie.draw(g); for(Bullet bullet:bullets) bullet.draw(g);
    }
    @Override public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: up=true; break; case KeyEvent.VK_S: down=true; break;
            case KeyEvent.VK_A: left=true; break; case KeyEvent.VK_D: right=true; break;
            case KeyEvent.VK_SPACE: bullets.add(new Bullet(player.x+player.width,player.y+player.height/2)); break;
        }
    }
    @Override public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: up=false; break; case KeyEvent.VK_S: down=false; break;
            case KeyEvent.VK_A: left=false; break; case KeyEvent.VK_D: right=false; break;
        }
    }
    @Override public void keyTyped(KeyEvent e){}
}
