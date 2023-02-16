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
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();
		
		// drawBackground(g);

		drawGrid(g);

		drawScore(g);
		
		drawPlayer(x, y, width, height, texture,g);

		gameworld.getLettuceBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g);	 
	    }); 

		gameworld.getLettuce().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g);	 
	    }); 

		gameworld.getBins().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g);	 
	    }); 

		gameworld.getCounters().forEach((temp) -> {
			drawAsset((int) temp.getCentre().getX(), (int) temp.getCentre().getY(), 100, 100, temp.getTexture(), g);	 
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

	private void drawAsset(int x, int y, int width, int height, String texture, Graphics g){
		File TextureToLoad = new File(texture);  //should work okay on OSX and Linux but check if you have issues depending your eclipse install or if your running this without an IDE 
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//64 by 128 
			 g.drawImage(myImage, x,y, x+width, y+height, 0 , 0, 100, 100, null); 
			
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
			int currentPositionInAnimation= /*((int) ((CurrentAnimationTime%40)/10))**/0; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public void drawGrid (Graphics g){
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 12; j++){
				g.setColor(Color.BLACK);
				g.fillRect(j * Model.widthAndHeight,i * Model.widthAndHeight, Model.widthAndHeight, Model.widthAndHeight);
				g.setColor(Color.RED);
				g.drawRect(j * Model.widthAndHeight,i * Model.widthAndHeight, Model.widthAndHeight, Model.widthAndHeight);
				g.drawString("[ " + j + " , " + i + " ]", (j * Model.widthAndHeight) + 25, (i * Model.widthAndHeight) + 25);
			}
		}
	}

	public void drawScore (Graphics g){
		g.setColor(Color.GREEN);
		g.fillRect(1200,700, 300, 100);
		g.setColor(Color.BLUE);
		g.drawRect(1200,700, 300, 100);
		g.drawString(Integer.toString(Model.Timer), 1300, 750);
	}
}