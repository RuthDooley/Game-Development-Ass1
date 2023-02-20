import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.GameObject;

//For the grid drawing
import java.awt.Dimension;
import javax.swing.*;
import java.awt.BasicStroke;

public class Viewer extends JPanel {
	private int spaceshipDirection=0;
	private long CurrentAnimationTime= 0; 
	
	Model gameworld =new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout) {
		super(layout);
		// TODO Auto-generated constructor stub
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
		// TODO Auto-generated constructor stub
	}

	public void updateview() {
		
		this.repaint();
		// TODO Auto-generated method stub
		
	}

	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		drawGrid(g);

		drawScore(g);

		drawTimer(g);

		drawOrders(g);
		
		drawPlayer((int) gameworld.getPlayer().getCentre().getX(), (int) gameworld.getPlayer().getCentre().getY(), gameworld.getPlayer().getWidth(), gameworld.getPlayer().getHeight(), gameworld.getPlayer().getTexture(), g); 

		gameworld.getLettuceBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getTomatoBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getCucumberBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getCounters().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		//Drop off
		drawAsset((int) gameworld.getDeliveryDropOff().getCentre().getX(), (int) gameworld.getDeliveryDropOff().getCentre().getY(), 100, 100, gameworld.getDeliveryDropOff().getTexture(), g, 3, 100, 100);	

		gameworld.getPlates().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getLettuce().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getTomato().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

		gameworld.getCucumber().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 
	}

	private void drawAsset(int x, int y, int width, int height, String texture, Graphics g, int animationFrames, int actualPixelWidth, int actualPixelheight){
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 

			if (animationFrames == 0){
				g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, actualPixelWidth, actualPixelheight, null); 
			} else {
				int currentPositionInAnimation= ((int) ((CurrentAnimationTime%(animationFrames * 10)))/10)*32;
				g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation , 0, currentPositionInAnimation + 32 , 32, null); 
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			int currentPositionInAnimation= 0;
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void drawGrid (Graphics g){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 12; j++){
				if ((j % 2 == 0 && i % 2 != 0) || (j % 2 != 0 && i % 2 == 0) ){
					g.setColor(Color.WHITE);
					g.fillRect(j * Model.widthAndHeight,i * Model.widthAndHeight, Model.widthAndHeight, Model.widthAndHeight);
				} else {
					g.setColor(Color.decode("#ebc17f"));
					g.fillRect(j * Model.widthAndHeight,i * Model.widthAndHeight, Model.widthAndHeight, Model.widthAndHeight);
				}
				//g.drawString("[ " + j + " , " + i + " ]", (j * Model.widthAndHeight) + 25, (i * Model.widthAndHeight) + 25);
			}
		}
	}

	public void drawTimer (Graphics g){
		drawAsset(1200, 800, 240, 100,"res/timerBorder.png", g, 0, 240, 100);
		g.setColor(Color.GREEN);
		g.drawString("Timer " + Integer.toString(Model.Timer/1000), 1300, 850);
	}

	public void drawScore (Graphics g){
		drawAsset(1200, 700, 240, 100,"res/score.png", g, 0, 240, 100);
		g.setColor(Color.GREEN);
		g.drawString("Score " + Integer.toString(Model.Score), 1200, 750);
	}

	public void drawOrders(Graphics g){
		drawAsset(1200, 0, 240, 100,"res/orders.png", g, 0, 240, 100);

		int counter = 100;
		for (Integer temp : Model.OrderNameList){
			drawAsset(1200, counter, 150, 100,"res/orderBoard.png", g, 0, 150, 100);
			switch (temp){
				case 0:
					g.drawString("Lettuce Salad", 1200, counter + 50);
					break;
				case 1:
					g.drawString("Tomato Salad", 1200, counter + 50);
					break;
				case 2:
					g.drawString("Cucumber Salad", 1200, counter + 50);
					break;
				case 3:
					g.drawString("Lettuce and Tomato Salad", 1200, counter + 50);
					break;
				case 4:
					g.drawString("Lettuce and Cucumber Salad", 1200, counter + 50);
					break;
				case 5:
					g.drawString("Tomato and Cucumber Salad", 1200, counter + 50);
					break;
				case 6:
					g.drawString("Lettuce and Tomato and Cucumber Salad", 1200, counter + 50);
					break;
				default:
					System.out.println("Error in Viewer.drawOrders()");
			}
			counter += 100;
		}

		counter = 100;
		for (Integer temp : Model.OrderTimeList){
			drawAsset(1350, counter - 5, 100, 100,"res/time.png", g, 0, 100, 100);
			g.drawString(Integer.toString(Model.orderTimeBeforeExpiry/1000 - ((int)System.currentTimeMillis() - temp)/1000), 1350, counter + 50);
			counter += 100;
		}
	}
}