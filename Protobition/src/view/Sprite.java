package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage{

	private double xAnchor;
	private double yAnchor;
	
	public Sprite(int x, int y, double xAnchor, double yAnchor) {
		super(x, y, BufferedImage.TYPE_4BYTE_ABGR);
		this.xAnchor = xAnchor;
		this.yAnchor = yAnchor;
	}

	public void drawOnto(Graphics2D g, int x, int y, double scale){
		double width = getWidth() * scale;
		double height = getHeight() * scale;
		
		g.drawImage(this, (int)(x - (width * xAnchor)), (int)(y - (height * yAnchor)), (int)width, (int)height, null);
	}
}
