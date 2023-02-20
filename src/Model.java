import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 

import java.lang.Math;

 //For sound
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


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
	private static int numberOfPlatesLevel;
	public static ArrayList<Integer> plateSpawnLocations  = new ArrayList<Integer>();
	// public static CopyOnWriteArrayList<GameObject> OrderList  = new CopyOnWriteArrayList<GameObject>();

	public static int Score=0; 
	public static int Timer=0; 
	public static int timerStart = 0;
	public static int orderTimeBeforeExpiry = 0; //The amount of time you have before the order expires in milliseconds
	public static int deliveryList = 7; //The number of options in the delivery list
	public static Boolean gameFinished = false;

	public static int widthAndHeight = 100;
  

	public Model() {
		Player = new GameObject("res/LightningUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,400, "player"));
	}

	public static void gameDesignSetup (int levelNumber) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		gameFinished = false;
		switch(levelNumber) {
			case 1:
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300, "counter")));
				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,400, "bin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,500, "counter")));
				
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,500, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,500, "counter")));
				CucumberBinList.add(new GameObject("res/cucumberBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,500, "cucumberBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,500, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,500, "counter")));
				TomatoBinList.add(new GameObject("res/tomatoBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,400, "tomatoBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,300, "counter")));

				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,300, "dropoff"));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,300, "counter")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,300, "lettuceBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,300, "counter")));

				numberOfPlatesLevel = 1;
				plateSpawnLocations.add(400500);

				//This is the time for the level
				timerStart = 120_000;
				orderTimeBeforeExpiry = 20_000;
				deliveryList = 4;
				break;
			case 2:

								// BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,400, "bin")));
				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(0,200, "dropoff"));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,300, "lettuceBin")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300, "lettuceBin")));
				// deliveryDropOff = new GameObject("res/Ninja.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(10,1, "dropoff"));
				break;
			case 3:
				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(0,100, "dropoff"));
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
		plateSpawn();
		playerLogic(); 
		timerLogic();
		orderLogic();
	}

	private void plateSpawn(){
		if (PlateList.size() < numberOfPlatesLevel && !objectPlayerHolding.contains("plate")){
			for (Integer temp : plateSpawnLocations){
				if (!Point3f.plateSpacesOccupied.contains(temp)){
					PlateList.add(new GameObject("res/plate.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(temp/1000, temp % 1_000, "plate")));
					break;
				}
			}
		}
	}

	private void timerLogic (){
		Timer = (timerStart - ((int)(System.currentTimeMillis()) - MainWindow.startTime));
		Timer = Math.round(Timer/10) * 10;
		if (Timer == 0){
			gameFinished = true;
		}
	}

	public static ArrayList<Integer> OrderNameList  = new ArrayList<Integer>();
	public static ArrayList<Integer> OrderTimeList  = new ArrayList<Integer>();
	public static void orderLogic(){
		//Handle adding the orders to the list
		if (OrderNameList.size() < 2){
			getRandomOrder();
		} else if (Timer % 10_000 == 0 && OrderNameList.size() < 6){ //This dictates the freq of the orders coming in can change
			getRandomOrder();
		}

		//Handeling removing the orders from the list if expired
		for (Integer temp : OrderTimeList){
			if (((int)System.currentTimeMillis() - temp) > orderTimeBeforeExpiry){
				removeOrderFromList(OrderTimeList.indexOf(temp));
				Score -= 5;
				break;
			}
		}
	}

	public static void removeOrderFromList (int indexToRemove){
		OrderNameList.remove(indexToRemove);
		OrderTimeList.remove(indexToRemove);
	}

	public static void getRandomOrder (){
		OrderNameList.add((int)Math.floor(Math.random() * deliveryList));
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
				if (objectPlayerHolding.contains("lettuce") && objectPlayerHolding.contains("tomato") && objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")){
					checkOrderExists(6);
				} else if (objectPlayerHolding.contains("lettuce") && objectPlayerHolding.contains("tomato") && !objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(3);
				} else if (objectPlayerHolding.contains("lettuce") && !objectPlayerHolding.contains("tomato") && objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(4);
				} else if (!objectPlayerHolding.contains("lettuce") && objectPlayerHolding.contains("tomato") && objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(5);
				} else if (objectPlayerHolding.contains("lettuce") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(0);
				} else if (objectPlayerHolding.contains("tomato") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(1);
				} else if (objectPlayerHolding.contains("cucumber") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(2);
				} else {
					System.out.println("invalid order");
				}
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
					CucumberList.add(new GameObject("res/cucumber.png",100,100,Point3f.setPointInit(gridSpace/1000,gridSpace % 1_000, "cucumber")));
				}
			}		
		}			
		Controller.getInstance().setKeySpacePressed(false);
	}

	public static void checkOrderExists (int value){
		if (OrderNameList.contains(value)){
			objectPlayerHolding.clear();

			//Scoring system
			if (value == 6) {
				Score += 15;
			} else if (value >= 3 && value <=5) {
				Score += 10;
			} else {
				Score += 5;
			}

			//Add the time remaining as well --> Time beofore expiry - (difference between start and end time of the delivery) /1000 and rounded down
			Score += (Math.round(orderTimeBeforeExpiry - ((int)System.currentTimeMillis() - OrderTimeList.get(OrderNameList.indexOf(value)))) / 1000);

			//remove the order closest to the front from the list
			removeOrderFromList(OrderNameList.indexOf(value));
		} else {
			System.out.println("order does not exist");
			//TODO update viewer
		}
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