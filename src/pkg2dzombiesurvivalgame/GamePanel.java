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
    boolean up,down,left,right;
    Random random=new Random();

    public GamePanel(){
        setPreferredSize(new Dimension(screenWidth,screenHeight));
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        setFocusable(true);
        player=new Player();
        addKeyListener(this);
        requestFocusInWindow();
        for(int i=0;i<5;i++){
            int x=random.nextInt(screenWidth-40);
            int y=random.nextInt(screenHeight-40);
            zombies.add(new Zombie(x,y));
        }
        Timer timer=new Timer(16,e->updateGame());
        timer.start();
    }

    private void updateGame(){
        player.move(up,down,left,right);
        for(Zombie zombie:zombies) zombie.moveToward(player);
        repaint();
    }

    @Override protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawString("Zombie Survival Game",330,30);
        player.draw(g);
        for(Zombie zombie:zombies) zombie.draw(g);
    }

    @Override public void keyPressed(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: up=true; break;
            case KeyEvent.VK_S: down=true; break;
            case KeyEvent.VK_A: left=true; break;
            case KeyEvent.VK_D: right=true; break;
        }
    }
    @Override public void keyReleased(KeyEvent e){
        switch(e.getKeyCode()){
            case KeyEvent.VK_W: up=false; break;
            case KeyEvent.VK_S: down=false; break;
            case KeyEvent.VK_A: left=false; break;
            case KeyEvent.VK_D: right=false; break;
        }
    }
    @Override public void keyTyped(KeyEvent e){}
}
