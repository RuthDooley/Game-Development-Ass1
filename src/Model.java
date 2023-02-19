import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 

import java.lang.Math;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit; //For sleep 


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
	
	public static GameObject Player;
	private Controller controller = Controller.getInstance();
	private static CopyOnWriteArrayList<GameObject> LettuceBinList  = new CopyOnWriteArrayList<GameObject>();	
	private static CopyOnWriteArrayList<GameObject> LettuceList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> TomatoBinList  = new CopyOnWriteArrayList<GameObject>();	
	private static CopyOnWriteArrayList<GameObject> TomatoList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> CucumberBinList  = new CopyOnWriteArrayList<GameObject>();	
	private static CopyOnWriteArrayList<GameObject> CucumberList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> BinList  = new CopyOnWriteArrayList<GameObject>();
	private static CopyOnWriteArrayList<GameObject> CounterList  = new CopyOnWriteArrayList<GameObject>();	
	private static CopyOnWriteArrayList<GameObject> PlateList  = new CopyOnWriteArrayList<GameObject>();	
	private static GameObject deliveryDropOff;
	// public static CopyOnWriteArrayList<GameObject> OrderList  = new CopyOnWriteArrayList<GameObject>();

	public static int Score=0; 
	public static int Timer=0; 
	public static int timerStart = 60;
	public static Boolean gameFinished = false;

	public static int widthAndHeight = 100;

	public Model() {
		Player= new GameObject("res/LightningUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(200,300, "player"));
		// deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(10,1, "dropoff"));
	}

	public static void gameDesignSetup (int levelNumber){
		gameFinished = false;
		//TODO: Reset all of the array lists maybe here
		switch(levelNumber) {
			case 1:
				deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(0,100, "dropoff"));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,0, "lettuceBin")));
				TomatoBinList.add(new GameObject("res/tomatoBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,0, "tomatoBin")));
				CucumberBinList.add(new GameObject("res/cucumberBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,0, "cucumberBin")));
				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,500, "bin")));
				CounterList.add(new GameObject("res/blankSprite.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,500, "counter")));
				PlateList.add(new GameObject("res/plate.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,500, "plate")));
				PlateList.add(new GameObject("res/plate.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,700, "plate")));
				timerStart = 10_000; //This is the time for the level
				break;
			case 2:
				deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(0,200, "dropoff"));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,300, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300, "lettuceBin")));
				// deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(10,1, "dropoff"));
				break;
			case 3:
				deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(0,100, "dropoff"));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(0,300, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,0, "lettuceBin")));
				break;
			default:
				System.out.println("Error in gameDesignSetup()");
		}	
	}

	public static void resetTheGame(){
		//Clear all the arraylists in Model and Point3f
		LettuceBinList.clear();
		LettuceList.clear();
		TomatoBinList.clear();
		TomatoList.clear();
		CucumberBinList.clear();
		CucumberList.clear();
		BinList.clear();
		CounterList.clear();
		PlateList.clear();
		// deliveryDropOff = null;

		Point3f.spacesOccupied.clear();
		Point3f.lettuceBinSpacesOccupied.clear();
		Point3f.tomatoBinSpacesOccupied.clear();
		Point3f.cucumberBinSpacesOccupied = new ArrayList<Integer>();
		Point3f.lettuceSpacesOccupied = new ArrayList<Integer>();
		Point3f.tomatoSpacesOccupied = new ArrayList<Integer>();
		Point3f.cucumberSpacesOccupied = new ArrayList<Integer>();
		Point3f.binSpacesOccupied = new ArrayList<Integer>();
		Point3f.counterSpacesOccupied = new ArrayList<Integer>();
		Point3f.plateSpacesOccupied = new ArrayList<Integer>();

		//Remove orders
		OrderNameList.clear();
		OrderTimeList.clear();

		//Remove the object the player is holding
		objectPlayerHolding.clear();

		//Reset Score
		Score = 0;

	}
	
	public void gamelogic() throws InterruptedException {
		playerLogic(); 
		timerLogic();
		orderLogic();
	}

	private void timerLogic (){
		Timer = (timerStart - ((int)(System.currentTimeMillis()) - MainWindow.startTime));
		Timer = Math.round(Timer/10) * 10;
		if (Timer == 0){
			gameFinished = true;
		}

		//Reduce the timer on all of the orders 
        System.out.println(Timer);
	}

	public static ArrayList<Integer> OrderNameList  = new ArrayList<Integer>();
	public static ArrayList<Integer> OrderTimeList  = new ArrayList<Integer>();
	public static int orderTimeBeforeExpiry = 10_000; //The amount of time you have before the order expires in milliseconds
	public static void orderLogic(){
		//Handle adding the orders to the list
		if (OrderNameList.size() < 3){
			getRandomOrder();
		} else if (Timer % 10_000 == 0 && OrderNameList.size() < 6){ //This dictates the freq of the orders coming in can change
			System.out.println("timer here");
			getRandomOrder();
		}

		//Handeling removing the orders from the list if expired
		for (Integer temp : OrderTimeList){
			if (((int)System.currentTimeMillis() - temp) > orderTimeBeforeExpiry){
				removeOrderFromList(OrderTimeList.indexOf(temp));
				break;
			}
		}
	}

	public static void removeOrderFromList (int indexToRemove){
		OrderNameList.remove(indexToRemove);
		OrderTimeList.remove(indexToRemove);
	}

	public static void getRandomOrder (){
		OrderNameList.add((int)Math.floor(Math.random() * 6));
		OrderTimeList.add((int)(System.currentTimeMillis()));
	}

	public static int gridSpace;
	private void playerLogic() throws InterruptedException{
		if(Controller.getInstance().isKeyAPressed()){
			actionOnDirectionKeyStroke("left");
		}
		
		if(Controller.getInstance().isKeyDPressed()){
			actionOnDirectionKeyStroke("right");
		}
			
		if(Controller.getInstance().isKeyWPressed()){
			actionOnDirectionKeyStroke("up");
		}
		
		if(Controller.getInstance().isKeySPressed()){
			actionOnDirectionKeyStroke("down");
		}
		
		if(Controller.getInstance().isKeySpacePressed()){
			actionOnSpaceStroke();
		}
	}

	private void actionOnDirectionKeyStroke (String direction){
		//Turn the player
		turningPlayer(direction);

		//Get the position of the player
		gridSpace = Point3f.getGridValue(Player.getCentre());

		int spaceMoveInto = 0;
		switch (direction){
			case "up":
				spaceMoveInto = gridSpace - 100;
				break;
			case "down":
				spaceMoveInto = gridSpace + 100;
				break;
			case "left":
				spaceMoveInto = gridSpace - 100_000;
				break;
			case "right":
				spaceMoveInto = gridSpace + 100_000;
				break;
			default:
				System.out.println("Error Model.actionOnDirectionKeyStroke() 1.0");
		}
		
		if (!Point3f.spacesOccupied.contains(spaceMoveInto)){
			Point3f.removeCollider(gridSpace);

			switch (direction){
				case "up":
					Controller.getInstance().setKeyWPressed(false);	
					Player.getCentre().ApplyVector( new Vector3f(0,widthAndHeight,0));
					break;
				case "down":
					Controller.getInstance().setKeySPressed(false);	
					Player.getCentre().ApplyVector( new Vector3f(0,-widthAndHeight,0));
					break;
				case "left":
					Controller.getInstance().setKeyAPressed(false);	
					Player.getCentre().ApplyVector( new Vector3f(-widthAndHeight,0,0));
					break;
				case "right":
					Controller.getInstance().setKeyDPressed(false);	
					Player.getCentre().ApplyVector( new Vector3f(widthAndHeight,0,0));
					break;
				default:
					System.out.println("Error Model.actionOnDirectionKeyStroke() 1.0");
			}
			
			gridSpace = Point3f.getGridValue(Player.getCentre());
			Point3f.addCollider(gridSpace);
		}
	}

	public static ArrayList<String> objectPlayerHolding  = new ArrayList<String>();
	private void actionOnSpaceStroke() throws InterruptedException{
		gridSpace = spaceInfrontOfPlayer();

		//If player is holding something ie. lettuce
		if (objectPlayerHolding.size() > 0){ 
			//and they are in front of a bin
			if (Point3f.binSpacesOccupied.contains(gridSpace)){
				System.out.println("here this is a bin");
				if (objectPlayerHolding.contains("plate")){
					objectPlayerHolding.clear();
					objectPlayerHolding.add("plate");
				} else {
					objectPlayerHolding.clear();
				}
			}
			//and they are in front of a counter or lettuce bin
			else if (Point3f.counterSpacesOccupied.contains(gridSpace) || Point3f.lettuceBinSpacesOccupied.contains(gridSpace) || Point3f.cucumberBinSpacesOccupied.contains(gridSpace) || Point3f.tomatoBinSpacesOccupied.contains(gridSpace)){			
				//Add new lettuce poistion to the counter space ie. the space infront of the player 
				if (objectPlayerHolding.contains("lettuce") && !Point3f.lettuceSpacesOccupied.contains(gridSpace)){ //Check that they are holding a lettuce and not going to put it on another lettuce
					LettuceList.add(new GameObject("res/lettuce.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "lettuce")));
					objectPlayerHolding.remove(objectPlayerHolding.indexOf("lettuce"));
				}

				if (objectPlayerHolding.contains("tomato") && !Point3f.tomatoSpacesOccupied.contains(gridSpace)){
					TomatoList.add(new GameObject("res/tomato.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "tomato")));
					objectPlayerHolding.remove(objectPlayerHolding.indexOf("tomato"));
				}

				if (objectPlayerHolding.contains("cucumber") && !Point3f.cucumberSpacesOccupied.contains(gridSpace)){
					CucumberList.add(new GameObject("res/cucumber.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "cucumber")));
					objectPlayerHolding.remove(objectPlayerHolding.indexOf("cucumber"));
				}

				if (objectPlayerHolding.contains("plate") && !Point3f.plateSpacesOccupied.contains(gridSpace)){
					PlateList.add(new GameObject("res/plate.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "plate")));
					objectPlayerHolding.remove(objectPlayerHolding.indexOf("plate"));
				}
			}
			//and theyre infront of the delivery zone
			else if (gridSpace/1000 == deliveryDropOff.getCentre().getX() && gridSpace % 1_000 == deliveryDropOff.getCentre().getY()){
				System.out.println("delivery");
				objectPlayerHolding.clear();
				Score += 4;
			}


		//Not holding soemthing ...
		} else {
			//and there is a lettuce or plate etc. infront of them
			if (Point3f.lettuceSpacesOccupied.contains(gridSpace) || Point3f.plateSpacesOccupied.contains(gridSpace) || Point3f.tomatoSpacesOccupied.contains(gridSpace) || Point3f.cucumberSpacesOccupied.contains(gridSpace)){
				if (Point3f.lettuceSpacesOccupied.contains(gridSpace)){
					objectPlayerHolding.add("lettuce");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : LettuceList){
						if (temp.getCentre().getX() == gridSpace/1000 && temp.getCentre().getY() == gridSpace%1000){
							LettuceList.remove(temp);
						}
					}
					Point3f.lettuceSpacesOccupied.remove(Point3f.lettuceSpacesOccupied.indexOf(gridSpace));
				}	
				
				if (Point3f.tomatoSpacesOccupied.contains(gridSpace)){
					objectPlayerHolding.add("tomato");
					for (GameObject temp : TomatoList){
						if (temp.getCentre().getX() == gridSpace/1000 && temp.getCentre().getY() == gridSpace%1000){
							TomatoList.remove(temp);
						}
					}
					Point3f.tomatoSpacesOccupied.remove(Point3f.tomatoSpacesOccupied.indexOf(gridSpace));
				}	

				if (Point3f.cucumberSpacesOccupied.contains(gridSpace)){
					objectPlayerHolding.add("cucumber");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : CucumberList){
						if (temp.getCentre().getX() == gridSpace/1000 && temp.getCentre().getY() == gridSpace%1000){
							CucumberList.remove(temp);
						}
					}
					Point3f.cucumberSpacesOccupied.remove(Point3f.cucumberSpacesOccupied.indexOf(gridSpace));
				}	

				if (Point3f.plateSpacesOccupied.contains(gridSpace)){
					objectPlayerHolding.add("plate");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : PlateList){
						if (temp.getCentre().getX() == gridSpace/1000 && temp.getCentre().getY() == gridSpace%1000){
							PlateList.remove(temp);
						}
					}
					Point3f.plateSpacesOccupied.remove(Point3f.plateSpacesOccupied.indexOf(gridSpace));
				}
				//If theres not those but lettuce bin or other veg bin instead
			} else if (Point3f.lettuceBinSpacesOccupied.contains(gridSpace) || Point3f.tomatoBinSpacesOccupied.contains(gridSpace) || Point3f.cucumberBinSpacesOccupied.contains(gridSpace)){
				if (Point3f.lettuceBinSpacesOccupied.contains(gridSpace)){
					LettuceList.add(new GameObject("res/lettuce.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "lettuce")));
				} else if (Point3f.tomatoBinSpacesOccupied.contains(gridSpace)){
					TomatoList.add(new GameObject("res/tomato.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "tomato")));
				} else if (Point3f.cucumberBinSpacesOccupied.contains(gridSpace)){
					System.out.println("triggered cucumber");
					CucumberList.add(new GameObject("res/cucumber.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "cucumber")));

					System.out.println(CucumberList.toArray().toString());
				}
				
				// System.out.println("lettuce list " + LettuceList.get(0).getCentre().getX() + LettuceList.get(0).getCentre().getY());
				// System.out.println("lettuce list cooliders" + Point3f.lettuceSpacesOccupied.toString());
			}		
		}			
		Controller.getInstance().setKeySpacePressed(false);
	}

	public static String directPlayerfacing = "up";
	private void turningPlayer (String direction){
		//direction = up, down, left right
		directPlayerfacing = direction;
		String location = "res/Lightning" + direction.substring(0, 1).toUpperCase() + direction.substring(1) + ".png";
		Player.setTextureLocation(location);
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

	public GameObject getDeliveryDropOff() {
		return deliveryDropOff;
	}

	public CopyOnWriteArrayList<GameObject> getLettuceBins() {
		return LettuceBinList;
	}

	public CopyOnWriteArrayList<GameObject> getLettuce() {
		return LettuceList;
	}

	public CopyOnWriteArrayList<GameObject> getTomatoBins() { //Tomato
		return TomatoBinList;
	}

	public CopyOnWriteArrayList<GameObject> getTomato() {
		return TomatoList;
	}

	public CopyOnWriteArrayList<GameObject> getCucumberBins() { //Cucumber
		return CucumberBinList;
	}

	public CopyOnWriteArrayList<GameObject> getCucumber() {
		return CucumberList;
	}

	public CopyOnWriteArrayList<GameObject> getBins() {
		return BinList;
	}

	public CopyOnWriteArrayList<GameObject> getCounters() {
		return CounterList;
	}

	public CopyOnWriteArrayList<GameObject> getPlates() {
		return PlateList;
	}

	public int getScore() { 
		return Score;
	}
}