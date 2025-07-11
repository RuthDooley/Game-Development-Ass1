//Ruth Dooley 19300753

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.LayoutManager;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

//For the grid drawing
import java.awt.Dimension;
import javax.swing.*;
import java.awt.BasicStroke;

import java.awt.Font;

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
 */ 

public class Viewer extends JPanel {
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
		int starNum;
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		drawGrid(g);

		drawScore(g);

		drawTimer(g);

		//Causing an error otherwise
		if (Model.OrderNameList.size() > 0)
			drawOrders(g);
		
		if (!Model.gameFinished){
			//Players
			drawAsset((int) gameworld.getPlayer().getCentre().getX(), (int) gameworld.getPlayer().getCentre().getY(),  100, 100, gameworld.getPlayer().getTexture(), g, 3, 32, 32);	
			drawAsset((int) gameworld.getPlayer1().getCentre().getX(), (int) gameworld.getPlayer1().getCentre().getY(),  100, 100, gameworld.getPlayer1().getTexture(), g, 3, 32, 32);	
			//Drop off
			drawAsset((int) gameworld.getDeliveryDropOff().getCentre().getX(), (int) gameworld.getDeliveryDropOff().getCentre().getY(), 100, 100, gameworld.getDeliveryDropOff().getTexture(), g, 3, 100, 100);	
		} else {
			if (Model.Score > Model.scoreStars[2])
				starNum = 3;
			else if (Model.Score > Model.scoreStars[1])
				starNum = 2;
			else if (Model.Score > Model.scoreStars[0])
				starNum = 1;
			else 
				starNum = 0;

			//Score stars
			drawAsset(450, 390, 300, 120, "res/" + starNum + "Star.png", g, 0, 100, 40);
		}

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

		gameworld.getHoles().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g, 0, 100, 100);	 
	    }); 

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
				// g.setColor(Color.BLACK);
				// g.drawString("[ " + j + " , " + i + " ]", (j * Model.widthAndHeight) + 25, (i * Model.widthAndHeight) + 25);
			}
		}
	}

	public void drawTimer (Graphics g){
		drawAsset(1200, 800, 240, 100,"res/timerBorder.png", g, 0, 240, 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20)); 
		drawAsset(1325, 800, 100, 100, "res/timersmall.png", g, 0, 100, 100);	 
		g.drawString(Integer.toString(Model.Timer/1000), 1300, 857);
	}

	public void drawScore (Graphics g){
		drawAsset(1200, 700, 240, 100,"res/score.png", g, 0, 240, 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("Arial", Font.BOLD, 20)); 
		drawAsset(1325, 700, 100, 100, "res/coin.png", g, 0, 100, 100);	 
		g.drawString(Integer.toString(Model.Score), 1305, 757);
	}

	public void drawOrders(Graphics g){
		drawAsset(1200, 0, 240, 100,"res/orders.png", g, 0, 240, 100);

		int counter = 100;
		g.setColor(Color.BLACK);
		for (Integer temp : Model.OrderNameList){
			drawAsset(1200, counter, 150, 100,"res/orderBoard.png", g, 0, 150, 100);
			switch (temp){
				case 0:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1280, counter + 25, 50, 50, "res/lettuce.png", g, 0, 100, 100);	 
					break;
				case 1:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1280, counter + 25, 50, 50, "res/tomato.png", g, 0, 100, 100);	 
					break;
				case 2:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1280, counter + 25, 50, 50, "res/cucumber.png", g, 0, 100, 100);	 
					break;
				case 3:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1252, counter + 25, 50, 50, "res/lettuce.png", g, 0, 100, 100);
					drawAsset(1280, counter + 25, 50, 50, "res/tomato.png", g, 0, 100, 100);	 	 
					break;
				case 4:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1252, counter + 25, 50, 50, "res/lettuce.png", g, 0, 100, 100);
					drawAsset(1280, counter + 25, 50, 50, "res/cucumber.png", g, 0, 100, 100);	 
					break;
				case 5:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1250, counter + 25, 50, 50, "res/tomato.png", g, 0, 100, 100);
					drawAsset(1280, counter + 25, 50, 50, "res/cucumber.png", g, 0, 100, 100);	 
					break;
				case 6:
					drawAsset(1225, counter + 25, 50, 50, "res/plate.png", g, 0, 100, 100);	 
					drawAsset(1245, counter + 25, 50, 50, "res/lettuce.png", g, 0, 100, 100);
					drawAsset(1265, counter + 25, 50, 50, "res/tomato.png", g, 0, 100, 100);
					drawAsset(1285, counter + 25, 50, 50, "res/cucumber.png", g, 0, 100, 100);	 
					break;
				default:
					System.out.println("Error in Viewer.drawOrders()");
			}
			counter += 100;
		}

		counter = 100;
		for (Integer temp : Model.OrderTimeList){
			g.setColor(Color.BLACK);
			g.setFont(new Font("Arial", Font.BOLD, 14)); 
			drawAsset(1350, counter - 5, 100, 100,"res/time.png", g, 0, 100, 100);
			String timeRemaining = Integer.toString(Model.orderTimeBeforeExpiry/1000 - ((int)System.currentTimeMillis() - temp)/1000);
			if (Integer.valueOf(timeRemaining) >= 10){
				g.drawString(timeRemaining, 1392, counter + 62);
			} else {
				g.drawString(timeRemaining, 1397, counter + 62);
			}
			counter += 100;
		}
	}
}