import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 

import java.lang.Math;

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
public class Model {
	
	private GameObject Player;
	private Controller controller = Controller.getInstance();
	private CopyOnWriteArrayList<GameObject> EnemiesList  = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> LettuceBinList  = new CopyOnWriteArrayList<GameObject>();
	private int Score=0; 

	public static int widthAndHeight = 100;

	public Model() {
		// fillGridWithZeros();
		Player= new GameObject("res/Lightning.png",widthAndHeight,widthAndHeight, new Point3f(200,300, 0));
		// Player= new GameObject("res/Lightning.png",widthAndHeight,widthAndHeight, new Point3f(200,100,0));
	}

	public static void gameDesignSetup (int levelNumber){
		switch(levelNumber) {
			case 1:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, new Point3f(100,100, 0)));
				break;
			case 2:
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(0,0,1)));
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(7,14,1)));
				break;
			case 3:
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(0,0,1)));
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(7,14,1)));
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(1,1,1)));
				// LettuceBinList.add(new GameObject("res/lettuceBin.png",widthAndHeight,widthAndHeight, setGridSpace(6,6,1)));
				break;
			default:
				System.out.println("Somehting wrong here. Var = " + MainWindow.levelNumberSelected);
		}	
	}

	// public static int[][] grid = new int[9][12];
	// public static int[][] grid = {
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// 	{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
	// };

	public static int[][] grid = {
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
		{0, 0, 0, 0, 0, 0, 0, 0, 0},
	};


	// public static Point3f gridspaceToPoint3f (int xGridSpace, int yGridSpace){
	// 	setGridPoint(xGridSpace, yGridSpace, 1);
	// 	return new Point3f (xGridSpace * widthAndHeight, yGridSpace * widthAndHeight, 0);
	// }

	// public static void setGridPoint (int xGridSpace, int yGridSpace, int valueToSet){
	// 	grid[yGridSpace][xGridSpace] = valueToSet;
	// 	// printGrid();
	// }

	// public static int[] getGridPoint (Point3f coord){
	// 	return new int[] {((int)coord.getX())/widthAndHeight, ((int)coord.getY())/widthAndHeight};
	// }

	// public static void printGrid(){
	// 	for (int i = 0; i < 9; i++){
	// 		for (int j = 0; j < 12; j++){
	// 			if (grid[i][j] == 0)
	// 				System.out.print("O ");
	// 			else 
	// 				System.out.print("X ");
	// 		}
	// 		System.out.println("");
	// 	}
	// 	System.out.println("");
	// }
	
	// This is the heart of the game , where the model takes in all the inputs ,decides the outcomes and then changes the model accordingly. 
	public void gamelogic() 
	{
		// Player Logic first 
		playerLogic(); 
		// Enemy Logic next
		// enemyLogic();
		// Bullets move next 
		// bulletLogic();
		// interactions between objects 
		gameLogic(); 

		// lettuceLogic();
	   
	}

	private void gameLogic() { 
		for (GameObject temp : EnemiesList) 
		{
		for (GameObject Bullet : BulletList) 
		{
			if ( Math.abs(temp.getCentre().getX()- Bullet.getCentre().getX())< temp.getWidth() 
				&& Math.abs(temp.getCentre().getY()- Bullet.getCentre().getY()) < temp.getHeight())
			{
				EnemiesList.remove(temp);
				BulletList.remove(Bullet);
				Score++;
			}  
		}
		}
		
	}

	public static int gridSpace[];
	private void playerLogic() {
		
		if(Controller.getInstance().isKeyAPressed()){
		// 	gridSpace = getGridPoint(Player.getCentre());
			// setGridPoint(gridSpace[0], gridSpace[1], 0);


				// if (grid[(gridSpace[0] - 1)][gridSpace[1]] == 0){
					Player.getCentre().ApplyVector( new Vector3f(-widthAndHeight,0,0));
					// gridSpace = getGridPoint(Player.getCentre());
					// setGridPoint(gridSpace[0], gridSpace[1], 1);
					Controller.getInstance().setKeyAPressed(false);	

			// }

		}
		
		if(Controller.getInstance().isKeyDPressed()){

			// if (grid[(gridSpace[0])][(gridSpace[1] + 1)] == 0){
				Player.getCentre().ApplyVector( new Vector3f(widthAndHeight,0,0));
				// gridSpace = getGridPoint(Player.getCentre());
				// setGridPoint(gridSpace[0], gridSpace[1], 1);
				Controller.getInstance().setKeyDPressed(false);	
			// }

		// 	//Could make an else here to do sound effects no move
		}
			
		if(Controller.getInstance().isKeyWPressed()){
			// gridSpace = getGridPoint(Player.getCentre());
			// setGridPoint(gridSpace[0], gridSpace[1], 0);

			// if (gridSpace[1] != 0){
			// 	if (grid[(gridSpace[0] - 1)][gridSpace[1]] == 0){
					Player.getCentre().ApplyVector( new Vector3f(0,widthAndHeight,0));
					// gridSpace = getGridPoint(Player.getCentre());
					// setGridPoint(gridSpace[0], gridSpace[1], 1);
					Controller.getInstance().setKeyWPressed(false);	
			// 	}
			// }
		}
		
		if(Controller.getInstance().isKeySPressed()){
			// gridSpace = getGridPoint(Player.getCentre());
			// setGridPoint(gridSpace[0], gridSpace[1], 0);

			// if (grid[(gridSpace[0] - 1)][gridSpace[1]] == 0){
				Player.getCentre().ApplyVector( new Vector3f(0,-widthAndHeight,0));
				// gridSpace = getGridPoint(Player.getCentre());
				// setGridPoint(gridSpace[0], gridSpace[1], 1);
				Controller.getInstance().setKeySPressed(false);	
			// }
		}
		
		if(Controller.getInstance().isKeySpacePressed())
{
			CreateBullet();
			Controller.getInstance().setKeySpacePressed(false);
		} 
		
	}

	private void CreateBullet() {
		BulletList.add(new GameObject("res/Bullet.png",32,64,new Point3f(Player.getCentre().getX(),Player.getCentre().getY(),0.0f)));
		
	}

	public GameObject getPlayer() {
		return Player;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return EnemiesList;
	}
	
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}

	public CopyOnWriteArrayList<GameObject> getLettuceBins() {
		return LettuceBinList;
	}

	public int getScore() { 
		return Score;
	}
}