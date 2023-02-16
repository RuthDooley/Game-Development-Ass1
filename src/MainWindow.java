import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import util.Point3f;
import util.UnitTests;

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

public class MainWindow {
	private static  JFrame frame = new JFrame("Game");   // Change to the name of your game 
	private static   Model gameworld= new Model();
	private static   Viewer canvas = new  Viewer( gameworld);
	private static KeyListener Controller =new Controller()  ; 
	private static   int TargetFPS = 100;
	public static boolean startGame= false; 
	private   JLabel BackgroundImageForStartMenu ;
	public static int startTime = 0;
	public static int levelNum = 1;

	public static void main(String[] args) {
		frame.setSize(2000, 1500);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		frame.setLayout(null);
		frame.setVisible(true);  

		while (true){

			canvas.setBounds(0, 0, 2000, 1500); 
			canvas.setBackground(new Color(255,255,255)); 
			frame.add(canvas);  

			canvas.addKeyListener(Controller);    
			canvas.requestFocusInWindow();   

			startTime = (int)(System.currentTimeMillis()/1000);



			gameloop(levelNum);		
			
			frame.getContentPane().removeAll();
			frame.repaint();

			levelNum ++;
		}

	} 

	//Basic Model-View-Controller pattern 
	private static void gameloop(int levelNumberSelected) { 
		Model.gameDesignSetup(levelNumberSelected);
		while(true){
			//Need to keep this in
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
		 	while (FrameCheck > System.currentTimeMillis()){} 
			
			gameworld.gamelogic();
			canvas.updateview(); 
			frame.setTitle("Score =  " + gameworld.getScore()); 

			if (Model.gameFinished){
				//Can put in how many stars you got and then trigger this on event listener
				levelNumberSelected += 1;
				break;
			}
		}
	}
}

