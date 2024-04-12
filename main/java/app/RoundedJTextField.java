package app;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class RoundedJTextField extends JTextField {
	 private int cornerRadius = 18; // Độ bo tròn bạn muốn

	    public RoundedJTextField () {
	        super();
	        setOpaque(false);
	    }

	    @Override
	    protected void paintComponent(Graphics g) {
	        if (!isOpaque()) {
	            g.setColor(Color.white);
	            g.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);
	        }
	        super.paintComponent(g);
	    }

	    @Override
	    protected void paintBorder(Graphics g) {
	        g.setColor(Color.white);
	        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
	    }

	    public void setCornerRadius(int radius) {
	        this.cornerRadius = radius;
	        repaint();
	    }

	    public int getCornerRadius() {
	        return cornerRadius;
	    }
	}

