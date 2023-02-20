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


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
   
   (MIT LICENSE ) e.g do what you want with this :-) 
 
 * Credits: Kelly Charles (2020)
 */ 
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
		
		// drawBackground(g);

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

	private void drawBackground(Graphics g){
		File TextureToLoad = new File("res/spacebackground.png");  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 12000, 12000, 0 , 0, 12000, 12000, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			//int currentPositionInAnimation= ((int) ((CurrentAnimationTime%40)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms 
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
		g.setColor(Color.GREEN);
		g.fillRect(1200,800, 250, 100);
		g.setColor(Color.BLUE);
		g.drawRect(1200,800, 250, 100);
		g.drawString("Timer " + Integer.toString(Model.Timer/1000), 1300, 850);
	}

	public void drawScore (Graphics g){
		g.setColor(Color.PINK);
		g.fillRect(1200,700, 250, 100);
		g.setColor(Color.GREEN);
		g.drawRect(1200,700, 250, 100);
		g.drawString("Score " + Integer.toString(Model.Score), 1300, 750);
	}

	public void drawOrders(Graphics g){
		g.setColor(Color.ORANGE);
		g.fillRect(1200,0, 235, 100);
		g.setColor(Color.GREEN);
		g.drawRect(1200,0, 235, 100);
		g.drawString("Orders", 1300, 50);

		int counter = 100;
		for (Integer temp : Model.OrderNameList){
			g.setColor(Color.BLACK);
			g.fillRect(1200,counter, 150, 100);
			g.setColor(Color.GREEN);
			g.drawRect(1200,counter, 150, 100);
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
			g.setColor(Color.BLACK);
			g.fillRect(1350,counter, 100, 100);
			g.setColor(Color.GREEN);
			g.drawRect(1350,counter, 100, 100);
			g.drawString(Integer.toString(Model.orderTimeBeforeExpiry/1000 - ((int)System.currentTimeMillis() - temp)/1000), 1350, counter + 50);
			counter += 100;
		}
	}
}