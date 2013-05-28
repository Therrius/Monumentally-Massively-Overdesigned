package view;

import java.awt.image.BufferedImage;

public class Drawer {

	DisplayWindow display;
	BufferedImage image;
	int currenWidth;
	int currentHeight;


	public void draw(){

		if(currenWidth != display.getDisplayWidth() || currentHeight != display.getDisplayHeight()){
			image = new BufferedImage(display.getDisplayWidth(), display.getDisplayHeight(), BufferedImage.TYPE_INT_ARGB);
			currenWidth = display.getDisplayWidth();
			currentHeight = display.getDisplayHeight();
		}





	}
}
