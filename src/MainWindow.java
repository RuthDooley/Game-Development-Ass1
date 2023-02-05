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
	  
	public MainWindow() {
		frame.setSize(1000, 1000);  // you can customise this later and adapt it to change on size.  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //If exit // you can modify with your way of quitting , just is a template.
		frame.setLayout(null);
				
		//All button handelling
		JButton startMenuButton = new JButton("Start Game");  // start button 
		startMenuButton.setBackground(Color.RED);
		startMenuButton.setForeground(Color.RED);
		startMenuButton.setBounds(400, 500, 200, 40);  
		frame.add(startMenuButton);  
		frame.setVisible(true);   

		startMenuButton.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				//Main menu to level selector menu
				levelSelectorWindow();
			}});  
	}

	public static int levelNumberSelected = 0; 
	// public static  Boolean allowButtonsClear = false; //Turn this to true when the user has made the selection and the buttons will dissapear
	public static void levelSelectorWindow (){
		repaintJFrame();

		JButton levelSelect1 = new JButton("Start Game");  // start button 
		levelSelect1.setBackground(Color.BLACK);
		levelSelect1.setForeground(Color.GRAY);
		levelSelect1.setBounds(400, 200, 200, 50);  
		frame.add(levelSelect1);  

		levelSelect1.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				levelNumberSelected = 1;
				startGame = true;

				gameSetupCanvas();
			}});  

		JButton levelSelect2 = new JButton("Start Game");  // start button 
		levelSelect2.setBackground(Color.BLACK);
		levelSelect2.setForeground(Color.GRAY);
		levelSelect2.setBounds(400, 300, 200, 50);  
		frame.add(levelSelect2);  
		levelSelect2.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				levelNumberSelected = 2;
				startGame = true;

				gameSetupCanvas();
			}}); 

		JButton levelSelect3 = new JButton("Start Game");  // start button 
		levelSelect3.setBackground(Color.BLACK);
		levelSelect3.setForeground(Color.GRAY);
		levelSelect3.setBounds(400, 400, 200, 50);  
		frame.add(levelSelect3);  
		levelSelect3.addActionListener(new ActionListener(){ 
			@Override
			public void actionPerformed(ActionEvent e) { 
				levelNumberSelected = 3;
				startGame = true;

				gameSetupCanvas();
			}}); 
	}

	//Remove all of the children of jframe and all of the elements 
	public static void repaintJFrame () {
		frame.getContentPane().removeAll();
		frame.repaint();
	}

	//Setting up the elements for the game steup canvas as shown in template
	public static void gameSetupCanvas (){
		repaintJFrame();

		canvas.setBounds(0, 0, 1000, 1000); 
		canvas.setBackground(new Color(255,255,255)); //white background  replaced by Space background but if you remove the background method this will draw a white screen 
		frame.add(canvas);  

		canvas.addKeyListener(Controller);    //adding the controller to the Canvas  
		canvas.requestFocusInWindow();   // making sure that the Canvas is in focus so keyboard input will be taking in .
	}

	public static void main(String[] args) {
		MainWindow hello = new MainWindow();  //sets up environment 
		gameloop(levelNumberSelected);
	} 

	//Basic Model-View-Controller pattern 
	private static void gameloop(int levelNumberSelected) { 


		while(true){
			//Need to keep this in
			int TimeBetweenFrames =  1000 / TargetFPS;
			long FrameCheck = System.currentTimeMillis() + (long) TimeBetweenFrames; 
		 	while (FrameCheck > System.currentTimeMillis()){} 
			
			
			// model update   
			gameworld.gamelogic();
			// view update 
			
			canvas.updateview(); 
			
			// Both these calls could be setup as  a thread but we want to simplify the game logic for you.  
			//score update  
			frame.setTitle("Score =  "+ gameworld.getScore()); 

			if (gameworld.getScore() == 4){
				startGame = false;
				levelNumberSelected = 4;
				break;
			}
			  
		}

		if (!startGame){
			levelSelectorWindow();
		}

	
		//wait until a selection has been made. This needs to be worked on
		if (startGame){
			gameworld = null;
			canvas = null;
			gameloop(levelNumberSelected);
		}
		
	}
}

