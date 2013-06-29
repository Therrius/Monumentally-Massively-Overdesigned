package view;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Sprite{

	private double xAnchor;
	private double yAnchor;

	private int lastScale = 0;
	private double sizeMod;
	private BufferedImage originalImage;
	private BufferedImage lastImage;

	
	public Sprite(BufferedImage image, double sizePercent, double xAnchor, double yAnchor) {
		this.sizeMod = 1.0/sizePercent;
		this.xAnchor = xAnchor;
		this.yAnchor = yAnchor;
		originalImage = image;
	}

	public void drawOnto(Graphics2D g, int x, int y, int scale){
		//change scale
		if(scale != lastScale){
			lastImage = new BufferedImage((int)(originalImage.getWidth()*sizeMod*scale), (int)(originalImage.getHeight()*sizeMod*scale), BufferedImage.TYPE_4BYTE_ABGR);
			lastImage.getGraphics().drawImage(originalImage, 0, 0, lastImage.getWidth(), lastImage.getHeight(), null);
			lastScale = scale;
		}

		g.drawImage(lastImage, (int)(x - (lastImage.getWidth() * xAnchor)), (int)(y - (lastImage.getHeight() * yAnchor)), lastImage.getWidth(), lastImage.getHeight(), null);
	}
}
