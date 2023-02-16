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
	private static CopyOnWriteArrayList<GameObject> LettuceList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> BinList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> CounterList  = new CopyOnWriteArrayList<GameObject>();
	private int Score=0; 

	public static int widthAndHeight = 100;

	public Model() {
		Player= new GameObject("res/LightningUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(200,300, "player"));
	}

	public static void gameDesignSetup (int levelNumber){
		System.out.println("triggeredd");
		switch(levelNumber) {
			case 1:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,0, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/UFO.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,500, "bin")));
				break;
			case 2:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,300, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300, "lettuceBin")));
				break;
			case 3:
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(0,300, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,0, "lettuceBin")));
				break;
			default:
				System.out.println("Somehting wrong here. Var = " + MainWindow.levelNumberSelected);
		}	
	}
	
	public void gamelogic() {
		playerLogic(); 
	}

	public static String directPlayerfacing = "up";
	private void turningPlayer (String direction){
		//direction = up, down, left right
		directPlayerfacing = direction;
		String location = "res/Lightning" + direction.substring(0, 1).toUpperCase() + direction.substring(1) + ".png";
		Player.setTextureLocation(location);
	}

	public static int gridSpace;
	public static String objectPlayerHolding = "none";
	private void playerLogic() {
		if(Controller.getInstance().isKeyAPressed()){
			turningPlayer("left");
			gridSpace = Point3f.getGridValue(Player.getCentre());

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace - 100_000)){
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(-widthAndHeight,0,0));
				Controller.getInstance().setKeyAPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				Point3f.addCollider(gridSpace);
			}
		}
		
		if(Controller.getInstance().isKeyDPressed()){
			turningPlayer("right");
			gridSpace = Point3f.getGridValue(Player.getCentre());

			if (/*gridSpace/100_000 != 11 &&*/ !Point3f.spacesOccupied.contains(gridSpace + 100_000)){
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(widthAndHeight,0,0));
				Controller.getInstance().setKeyDPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				Point3f.addCollider(gridSpace);
			}

		// 	//Could make an else here to do sound effects no move
		}
			
		if(Controller.getInstance().isKeyWPressed()){
			turningPlayer("up");
			gridSpace = Point3f.getGridValue(Player.getCentre());

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace - 100)){
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(0,widthAndHeight,0));
				Controller.getInstance().setKeyWPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				Point3f.addCollider(gridSpace);
			}
		}
		
		if(Controller.getInstance().isKeySPressed()){
			turningPlayer("down");
			gridSpace = Point3f.getGridValue(Player.getCentre());

			if (/*gridSpace/100_000 d!= 0 && */!Point3f.spacesOccupied.contains(gridSpace + 100)){
				Point3f.removeCollider(gridSpace);
				Player.getCentre().ApplyVector( new Vector3f(0,-widthAndHeight,0));
				Controller.getInstance().setKeySPressed(false);	
				gridSpace = Point3f.getGridValue(Player.getCentre());
				Point3f.addCollider(gridSpace);
			}
		}
		
		if(Controller.getInstance().isKeySpacePressed()){
			gridSpace = spaceInfrontOfPlayer();

			if (Point3f.binSpacesOccupied.contains(gridSpace) && objectPlayerHolding != "none"){
				System.out.println("here this is a bin");
				objectPlayerHolding = "none";
			} else if (objectPlayerHolding != "none"){ //If player is holding something ie. lettuce
				//Implement later can only place down on certain objects and also where there are no other lettuces

				//Add new lettuce poistion to the counter space ie. the space infront of the player 
				LettuceList.add(new GameObject("res/bullet.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "lettuce")));
				objectPlayerHolding = "none";
			} else if (Point3f.lettuceSpacesOccupied.contains(gridSpace)){
				objectPlayerHolding = "lettuce";

				//Lettuce position removed from the lettuce array and removed from the lettuce collisions
				for (GameObject temp : LettuceList){
					if (temp.getCentre().getX() == gridSpace/1000 && temp.getCentre().getY() == gridSpace%1000){
						LettuceList.remove(temp);
					}
				}
				Point3f.lettuceSpacesOccupied.remove(Point3f.lettuceSpacesOccupied.indexOf(gridSpace));
				
				//Change player sprite to player with lettuce
			} else if (Point3f.lettuceBinSpacesOccupied.contains(gridSpace)){
				LettuceList.add(new GameObject("res/bullet.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "lettuce")));
				System.out.println("lettuce list " + LettuceList.get(0).getCentre().getX() + LettuceList.get(0).getCentre().getY());
				System.out.println("lettuce list cooliders" + Point3f.lettuceSpacesOccupied.toString());
			}

			Controller.getInstance().setKeySpacePressed(false);
		}
	}

	private int spaceInfrontOfPlayer (){
		//Find the position of the player
		gridSpace = Point3f.getGridValue(Player.getCentre());
		//Adjust to find the space infront of them

		switch (directPlayerfacing){
			case "up":
				gridSpace -= 100;
				break;
			case "down":
				gridSpace += 100;
				break;
			case "left":
				gridSpace -= 100_000;
				break;
			case "right":
				gridSpace += 100_000;
				break;
			default:
				System.out.println("error");
				break;
		}
		return gridSpace;
	}

	public GameObject getPlayer() {
		return Player;
	}

	public CopyOnWriteArrayList<GameObject> getLettuceBins() {
		return LettuceBinList;
	}

	public CopyOnWriteArrayList<GameObject> getLettuce() {
		return LettuceList;
	}

	public CopyOnWriteArrayList<GameObject> getBins() {
		return BinList;
	}

	public int getScore() { 
		return Score;
	}
}