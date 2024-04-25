import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
public class Form extends JFrame implements KeyListener, ActionListener {
    private int count = 5;
    private final BufferedImage img = new BufferedImage(Object.sSize.width, Object.sSize.height, BufferedImage.TYPE_INT_RGB);
    private final Color BG = new Color(200, 200, 200, 255);
    private final Timer timer = new Timer(100, this);
    Form() {
        this.setUndecorated(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    @Override
    public void paint(Graphics g) {
        Graphics2D g2D = img.createGraphics();
        g2D.setColor(BG);
        g2D.fillRect(0, 0, Object.sSize.width, Object.sSize.height);
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        for (Object object : Object.getObjects()) {
            g2D.setPaint(object.colorObject());
            g2D.fillOval((int)object.posX,  (int)object.posY, object.sizeObject(), object.sizeObject());
        }
        ((Graphics2D)g).drawImage(img, null, 0, 0);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        Object.cycle(count);
        this.repaint();
    }
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:
                System.exit(0);
                break;
            case KeyEvent.VK_1:
                Bacterium_1.Init(5);
                break;
            case KeyEvent.VK_2:
                Bacterium_2.Init(5);
                break;
            case KeyEvent.VK_3:
                Bacterium_3.Init(1);
                break;
            case KeyEvent.VK_4:
                Parasite.Init(5);
                break;
            case KeyEvent.VK_5:
                Mushroom.Init(1);
                break;
            case KeyEvent.VK_ADD:
                if (count < 10)
                    count++;
                break;
            case KeyEvent.VK_SUBTRACT:
                if (count > 0)
                    count--;
                break;
            case KeyEvent.VK_SPACE:
                if (timer.isRunning())
                    timer.stop();
                else
                    timer.restart();
                break;
        }
        this.repaint();
    }
    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
}