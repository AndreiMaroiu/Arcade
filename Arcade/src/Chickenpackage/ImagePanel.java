package Chickenpackage;

import javax.swing.*;
import java.awt.*;

class ImagePanel extends JPanel{
    private Image image;
    private int w;
    private int h;

    public ImagePanel(Image image, Dimension d) {
        this.w = d.width;
        this.h = d.height;
        this.image = image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, w, h, this);
    }
}