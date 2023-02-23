//Ruth Dooley 19300753

import java.awt.Color;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

import util.Point3f;
import util.UnitTests;


public class MainWindow {
	private static JFrame frame = new JFrame("Game");   // Change to the name of your game 
	private static Model gameworld= new Model();
	private static Viewer canvas = new  Viewer( gameworld);
	private static KeyListener controller = new Controller()  ; 
	private static int TargetFPS = 100;
	public static boolean startGame = false; 
	public static int startTime = 0;
	public static int levelNum = 1;

	private static Boolean terminatGame = false;
	public static void main(String[] args) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException {
		frame.setSize(2000, 1500);  
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		frame.setLayout(null);
		frame.setVisible(true);  

		while (!terminatGame){
			startGame();
		}
	} 

	public static void startGame () throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException{
		canvas.setBounds(0, 0, 2000, 1500); 
		canvas.setBackground(new Color(255,255,255)); 
		frame.add(canvas);  

		canvas.addKeyListener(controller);    
		canvas.requestFocusInWindow();   

		startTime = (int)System.currentTimeMillis();
		gameloop(levelNum);	

		frame.getContentPane().removeAll();
		frame.repaint();
	}

	private static void gameloop(int levelNumberSelected) throws InterruptedException, UnsupportedAudioFileException, IOException, LineUnavailableException { 
		Model.Score = 0;
		if (levelNum == 4){
			//Game end
			System.exit(0);
			Model.backgroundClip.stop();
			Model.backgroundClip.close();
		}

		Model.startBackgroundMusic();
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
				Model.resetTheGame();
				Model.backgroundClip.stop();
				Model.backgroundClip.close();
				if (Model.Score > Model.scoreStars[0]){
					levelNum ++;
					Model.musicPlayer("res/victory.wav", false);
				} else {
					Model.musicPlayer("res/failure.wav", false);
				}
				Thread.sleep(5_000);
				break;
			}
		}
	}
}

