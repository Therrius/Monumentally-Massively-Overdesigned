package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite extends BufferedImage{

	private double xAnchor;
	private double yAnchor;

	private double lastScale = 1;
	private BufferedImage lastImage;

	public Sprite(int x, int y, double xAnchor, double yAnchor) {
		super(x, y, BufferedImage.TYPE_4BYTE_ABGR);
		this.xAnchor = xAnchor;
		this.yAnchor = yAnchor;
		lastImage = this;
	}

	public void drawOnto(Graphics2D g, int x, int y, double scale){
		if(Math.abs(scale - lastScale)>0.001){
			lastImage = new BufferedImage((int)(getWidth()*scale), (int)(getHeight()*scale), BufferedImage.TYPE_4BYTE_ABGR);
			lastImage.getGraphics().drawImage(this, 0, 0, lastImage.getWidth(), lastImage.getHeight(), null);
			lastScale = scale;
		}

		g.drawImage(lastImage, (int)(x - (lastImage.getWidth() * xAnchor)), (int)(y - (lastImage.getHeight() * yAnchor)), lastImage.getWidth(), lastImage.getHeight(), null);
	}
}
