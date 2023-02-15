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
		Player= new GameObject("res/LightningUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(200,300));
		// Player= new GameObject("res/Lightning.png",widthAndHeight,widthAndHeight, new Point3f(200,100,0));
	}

	public static void gameDesignSetup (int levelNumber){
		System.out.println("triggeredd");
		switch(levelNumber) {
			case 1:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,300)));
				break;
			case 2:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,300)));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300)));
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

	public static String directPlayerfacing = "up";
	private void turningPlayer (String direction){
		//direction = up, down, left right
		directPlayerfacing = direction;
		String location = "res/Lightning" + direction.substring(0, 1).toUpperCase() + direction.substring(1) + ".png";
		Player.setTextureLocation(location);
	}

	public static int gridSpace;
	private void playerLogic() {

		
		if(Controller.getInstance().isKeyAPressed()){
			gridSpace = Point3f.getGridValue(Player.getCentre());
			System.out.println("before" + gridSpace);

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace - 100_000)){
				System.out.println("here");
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(-widthAndHeight,0,0));
				Controller.getInstance().setKeyAPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				System.out.println("after" + gridSpace);
				Point3f.addCollider(gridSpace);
				turningPlayer("left");
			}
		}
		
		if(Controller.getInstance().isKeyDPressed()){
			gridSpace = Point3f.getGridValue(Player.getCentre());
			System.out.println("before" + gridSpace);

			if (/*gridSpace/100_000 != 11 &&*/ !Point3f.spacesOccupied.contains(gridSpace + 100_000)){
				System.out.println("here");
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(widthAndHeight,0,0));
				Controller.getInstance().setKeyDPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				System.out.println("after" + gridSpace);
				Point3f.addCollider(gridSpace);
				turningPlayer("right");
			}

		// 	//Could make an else here to do sound effects no move
		}
			
		if(Controller.getInstance().isKeyWPressed()){
			gridSpace = Point3f.getGridValue(Player.getCentre());
			System.out.println("before" + gridSpace);

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace - 100)){
				System.out.println("here");
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(0,widthAndHeight,0));
				Controller.getInstance().setKeyWPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				System.out.println("after" + gridSpace);
				Point3f.addCollider(gridSpace);
				turningPlayer("up");
			}
		}
		
		if(Controller.getInstance().isKeySPressed()){
			gridSpace = Point3f.getGridValue(Player.getCentre());
			System.out.println("before" + gridSpace);

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace + 100)){
				System.out.println("here");
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(0,-widthAndHeight,0));
				Controller.getInstance().setKeySPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				System.out.println("after" + gridSpace);
				Point3f.addCollider(gridSpace);
				turningPlayer("down");
			}
		}
		
		if(Controller.getInstance().isKeySpacePressed()){
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