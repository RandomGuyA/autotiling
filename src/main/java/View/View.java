package View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;

import Model.Model;

public class View extends JPanel{

    private int DELAY = 10;
    private Timer timer;
    private Model theModel;
    private int WIDTH = 32;
    private int HEIGHT = 32;

    public View(){}

    public void init(Model theModel) {

        this.theModel=theModel;

        //Swing Timer
        timer = new Timer(DELAY, new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                update();
                repaint();
                revalidate();
            }
        });
        timer.start();
    }

    private void update() {

    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        setBackground(Color.black);
        theModel.getMap().draw(g);
    }
}
