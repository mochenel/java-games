
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Game extends JPanel implements ActionListener,MouseMotionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int balx=1,baly=1,vx=7,vy=5,rec=0;
    Timer t = new Timer(20,this);
    BufferedImage image;
    public int count;
    JButton button;
   ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
           balx=1;
           baly=1;
           vx=7;
           vy=5;
           rec=0;
           count=0;
           repaint();
        }
};
Font f;
   public Game(){
	   f= new Font("TimesRoman",Font.BOLD,50);
       count=0;
       button = new JButton("Start over"); 
       button.setBackground(Color.blue);
       button.setForeground(Color.black);
       addButton(button);
      
   }
   public JButton addButton(JButton b){
        return  (JButton) add(b);
       
   }
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        g.setColor(Color.yellow);
        g.fillRect(0, 0, 700, 700);
        g.setColor(Color.red);
        g.fillOval(balx, baly, 75, 75);
        g.setColor(Color.blue);
        g.fillRect(rec, 500, 160, 20);
        
                t.start();
           
        addMouseMotionListener(this);
        if(baly>500){
            g.setColor(Color.red);
            try {
                // g.drawString("you lost", 350, 300);
                image = ImageIO.read(getClass().getResource("over.png"));
                  g.drawImage(image, 0, 0, 700, 700, Color.red, this);
                    g.setFont(f);
                  g.drawString("your score: "+ this.toString(count), 200, 70);
                
                button.addActionListener(listener);
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
           
           
            
            
        }
        
    }


    public static void main(String[] args) {
       JFrame f = new JFrame("my first ball game");
       f.setSize(700,700);
       f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       f.setLocationRelativeTo(null);
       Game g = new Game();
       f.add(g);
       f.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        balx+=vx;
        baly+=vy;
        repaint();
       if(balx>=rec &&balx<=rec+140 && baly>=420 && baly<=520){
            vy=-10;
            count++;
        }
        if(balx<=0){
            vx=10;
        }
        if(baly<=0){
            vy=10;
        }if(balx>=500){
            vx=-10;
        }
      
        
     
        
    }

    @Override
    public void mouseDragged(MouseEvent me) {
 
    }

    @Override
    public void mouseMoved(MouseEvent me) {
        rec = me.getX();

        if(rec>=520){
            rec =480;
        }
        repaint();
    }

    public int toString(int count) {
        return count;
        
    }
    
}
