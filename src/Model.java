//Ruth Dooley 19300753

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 

import java.lang.Math;

//For sound
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//Overall music from https://www.fesliyanstudios.com/royalty-free-music/downloads-c/8-bit-music/6  
//Failure, coin, crumple, error, failure and victory sound effects from https://pixabay.com/sound-effects/search/failure/?manual_search=1&order=None 

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
	
	private static GameObject Player;
	private static GameObject Player1;
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
	private static CopyOnWriteArrayList<GameObject> HoleList  = new CopyOnWriteArrayList<GameObject>();	
	private static GameObject deliveryDropOff;
	private static int numberOfPlatesLevel;
	public static ArrayList<Integer> plateSpawnLocations  = new ArrayList<Integer>();

	public static int Score=0; 
	public static int Timer=0; 
	public static int timerStart = 0;
	public static int orderTimeBeforeExpiry = 0; //The amount of time you have before the order expires in milliseconds
	public static int deliveryList = 7; //The number of options in the delivery list
	public static Boolean gameFinished = false;

	public static int widthAndHeight = 100;
	public static int[] scoreStars = new int[3];

	public Model() {
		// Player = new GameObject("res/playerUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,400, "player"));
	}

	public static void gameDesignSetup (int levelNumber) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		gameFinished = false;
		switch(levelNumber) {
			case 1:
				Player = new GameObject("res/playerUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,500, "player"));
				Player1 = new GameObject("res/player1Up.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(700,300, "player"));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,500, "counter")));;
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,600, "counter")));
				
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,600, "counter")));
				CucumberBinList.add(new GameObject("res/cucumberBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,600, "cucumberBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,600, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,600, "counter")));
				TomatoBinList.add(new GameObject("res/tomatoBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,500, "tomatoBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,400, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,400, "counter")));

				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,300, "bin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,200, "counter")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,200, "lettuceBin")));
				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(700,200, "dropoff"));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,300, "counter")));

				numberOfPlatesLevel = 1;
				plateSpawnLocations.add(400600);

				//This is the time for the level
				timerStart = 120_000;
				orderTimeBeforeExpiry = 20_000;
				deliveryList = 4;

				//This dictates how many stars you get for each level
				scoreStars[0] = 100;
				scoreStars[1] = 200;
				scoreStars[2] = 300;
				break;
			case 2:
				Player = new GameObject("res/playerUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,400, "player"));
				Player1 = new GameObject("res/player1Up.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(700,400, "player"));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,300, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,500, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,600, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,600, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,600, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,200, "counter")));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,300, "lettuceBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,400, "counter")));
				TomatoBinList.add(new GameObject("res/tomatoBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,500, "tomatoBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,600, "counter")));

				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(200,200, "dropoff"));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,200, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,200, "counter")));

				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,400, "counter")));
				CucumberBinList.add(new GameObject("res/cucumberBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,400, "cucumberBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,300, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,500, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,400, "counter")));
				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,400, "bin")));

				numberOfPlatesLevel = 1;
				plateSpawnLocations.add(100500);

				//This is the time for the level
				timerStart = 120_000;
				orderTimeBeforeExpiry = 25_000;
				deliveryList = 7;

				//This dictates how many stars you get for each level
				scoreStars[0] = -100;
				scoreStars[1] = 200;
				scoreStars[2] = 250;
				break;
			case 3:
				Player = new GameObject("res/playerUp.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(400,400, "player"));
				Player1 = new GameObject("res/player1Up.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(700,400, "player"));
				deliveryDropOff = new GameObject("res/deliveryZone.png",widthAndHeight,widthAndHeight, Point3f.setPointInit(1000,100, "dropoff"));
				LettuceBinList.add(new GameObject("res/lettuceBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,100, "lettuceBin")));
				TomatoBinList.add(new GameObject("res/tomatoBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(100,700, "tomatoBin")));
				CucumberBinList.add(new GameObject("res/cucumberBin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(1000,700, "cucumberBin")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(500,400, "counter")));
				CounterList.add(new GameObject("res/counter.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(600,400, "counter")));
				numberOfPlatesLevel = 2;
				plateSpawnLocations.add(500400);
				plateSpawnLocations.add(600400);

				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,500, "bin")));
				BinList.add(new GameObject("res/bin.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,300, "bin")));

				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,300, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,400, "hole")));
				// HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(200,500, "counter")));

				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,600, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,600, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,600, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,600, "hole")));

				// HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,300, "counter")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,400, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(900,500, "hole")));

				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(300,200, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(400,200, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(700,200, "hole")));
				HoleList.add(new GameObject("res/hole.png", widthAndHeight, widthAndHeight, Point3f.setPointInit(800,200, "hole")));

				
				//This is the time for the level
				timerStart = 120_000;
				orderTimeBeforeExpiry = 30_000;
				deliveryList = 7;

				//This dictates how many stars you get for each level
				scoreStars[0] = 100;
				scoreStars[1] = 200;
				scoreStars[2] = 250;
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
		objectPlayer1Holding.clear();

		//Remove the plate spawn locations 
		plateSpawnLocations.clear();

	}
	
	public void gamelogic() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException {
		plateSpawn();
		playerLogic(); 
		player1Logic(); 
		timerLogic();
		orderLogic();
	}

	private void plateSpawn(){
		int plateCount = 0;
		if (objectPlayerHolding.contains("plate")) plateCount++;
		if (objectPlayer1Holding.contains("plate")) plateCount++;
		if (PlateList.size() < numberOfPlatesLevel - plateCount){
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
	public static void orderLogic() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		//Handle adding the orders to the list
		if (OrderNameList.size() < 2){
			getRandomOrder();
		} else if (Timer % 10_000 == 0 && OrderNameList.size() < 6){ //This dictates the freq of the orders coming in can change
			getRandomOrder();
		}

		//Handeling removing the orders from the list if expired
		for (Integer temp : OrderTimeList){
			if (((int)System.currentTimeMillis() - temp) > orderTimeBeforeExpiry){
				musicPlayer("res/loseOrder.wav", false);
				removeOrderFromList(OrderTimeList.indexOf(temp));
				Score -= 10;
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
	public static int gridSpace1;
	private void playerLogic() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{
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
		
		if(Controller.getInstance().isKeyFPressed()){
			actionOnSpaceStroke();
		}
	}

	private void player1Logic() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{
		if(Controller.getInstance().isKeyHPressed()){
			actionOnDirectionKeyStroke1("left");
		}		
		
		if(Controller.getInstance().isKeyKPressed()){
			actionOnDirectionKeyStroke1("right");
		}
		
		if(Controller.getInstance().isKeyJPressed()){
			actionOnDirectionKeyStroke1("down");
		}
			
		if(Controller.getInstance().isKeyUPressed()){
			actionOnDirectionKeyStroke1("up");
		}
		
		if(Controller.getInstance().isKeyLPressed()){
			System.out.println("here5");
			actionOnSpaceStroke1();
		}
	}

	public static String directPlayerfacing = "up";
	public static String directPlayer1facing = "up";
	private void turningPlayer (String direction, int playerNum){
		String location;
		//direction = up, down, left right
		if (playerNum == 0){
			directPlayerfacing = direction;
			location = "res/player" + direction.substring(0, 1).toUpperCase() + direction.substring(1) + ".png";
			Player.setTextureLocation(location);
		} else if (playerNum == 1){
			directPlayer1facing = direction;
			location = "res/player1" + direction.substring(0, 1).toUpperCase() + direction.substring(1) + ".png";
			Player1.setTextureLocation(location);
		}		
	}

	private void actionOnDirectionKeyStroke (String direction) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		//Turn the player
		turningPlayer(direction, 0);

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
					System.out.println("Error Model.actionOnDirectionKeyStroke() 2.0");
			}
			
			gridSpace = Point3f.getGridValue(Player.getCentre());
			Point3f.addCollider(gridSpace);
		} 
		// Can add this in but I don't like how it runs
		// else {
		// 	//Invalid move
		// 	musicPlayer("res/error.wav", false);
		// }
	}

	private void actionOnDirectionKeyStroke1 (String direction) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		//Turn the player
		turningPlayer(direction, 1);

		//Get the position of the player
		gridSpace1 = Point3f.getGridValue(Player1.getCentre());

		int spaceMoveInto = 0;
		switch (direction){
			case "up":
				spaceMoveInto = gridSpace1 - 100;
				break;
			case "down":
				spaceMoveInto = gridSpace1 + 100;
				break;
			case "left":
				spaceMoveInto = gridSpace1 - 100_000;
				break;
			case "right":
				spaceMoveInto = gridSpace1 + 100_000;
				break;
			default:
				System.out.println("Error Model.actionOnDirectionKeyStroke1() 1.0");
		}
		
		if (!Point3f.spacesOccupied.contains(spaceMoveInto)){
			Point3f.removeCollider(gridSpace1);

			switch (direction){
				case "up":
					Controller.getInstance().setKeyUPressed(false);	
					Player1.getCentre().ApplyVector( new Vector3f(0,widthAndHeight,0));
					break;
				case "down":
					Controller.getInstance().setKeyJPressed(false);	
					Player1.getCentre().ApplyVector( new Vector3f(0,-widthAndHeight,0));
					break;
				case "left":
					Controller.getInstance().setKeyHPressed(false);	
					Player1.getCentre().ApplyVector( new Vector3f(-widthAndHeight,0,0));
					break;
				case "right":
					Controller.getInstance().setKeyKPressed(false);	
					Player1.getCentre().ApplyVector( new Vector3f(widthAndHeight,0,0));
					break;
				default:
					System.out.println("Error Model.actionOnDirectionKeyStroke1() 2.0");
			}
			
			gridSpace1 = Point3f.getGridValue(Player1.getCentre());
			Point3f.addCollider(gridSpace1);
		} 
		// Can add this in but I don't like how it runs
		// else {
		// 	//Invalid move
		// 	musicPlayer("res/error.wav", false);
		// }
	}

	public static ArrayList<String> objectPlayerHolding  = new ArrayList<String>();
	private void actionOnSpaceStroke() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{
		gridSpace = spaceInfrontOfPlayer(0);

		//If player is holding something ie. lettuce
		if (objectPlayerHolding.size() > 0){ 
			//and they are in front of a bin
			if (Point3f.binSpacesOccupied.contains(gridSpace)){
				musicPlayer("res/crumple.wav", false);
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
					checkOrderExists(6, 0);
				} else if (objectPlayerHolding.contains("lettuce") && objectPlayerHolding.contains("tomato") && !objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(3, 0);
				} else if (objectPlayerHolding.contains("lettuce") && !objectPlayerHolding.contains("tomato") && objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(4, 0);
				} else if (!objectPlayerHolding.contains("lettuce") && objectPlayerHolding.contains("tomato") && objectPlayerHolding.contains("cucumber") && objectPlayerHolding.contains("plate")) {
					checkOrderExists(5, 0);
				} else if (objectPlayerHolding.contains("lettuce") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(0, 0);
				} else if (objectPlayerHolding.contains("tomato") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(1, 0);
				} else if (objectPlayerHolding.contains("cucumber") &&  objectPlayerHolding.contains("plate")) {
					checkOrderExists(2, 0);
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
		Controller.getInstance().setKeyFPressed(false);
	}

	public static ArrayList<String> objectPlayer1Holding  = new ArrayList<String>();
	private void actionOnSpaceStroke1() throws InterruptedException, LineUnavailableException, IOException, UnsupportedAudioFileException{
		gridSpace1 = spaceInfrontOfPlayer(1);

		//If player is holding something ie. lettuce
		if (objectPlayer1Holding.size() > 0){ 
			//and they are in front of a bin
			if (Point3f.binSpacesOccupied.contains(gridSpace1)){
				musicPlayer("res/crumple.wav", false);
				System.out.println("here this is a bin");
				if (objectPlayer1Holding.contains("plate")){
					objectPlayer1Holding.clear();
					objectPlayer1Holding.add("plate");
				} else {
					objectPlayer1Holding.clear();
				}
			}
			//and they are in front of a counter or lettuce bin
			else if (Point3f.counterSpacesOccupied.contains(gridSpace1) || Point3f.lettuceBinSpacesOccupied.contains(gridSpace1) || Point3f.cucumberBinSpacesOccupied.contains(gridSpace1) || Point3f.tomatoBinSpacesOccupied.contains(gridSpace1)){			
				//Add new lettuce poistion to the counter space ie. the space infront of the player 
				if (objectPlayer1Holding.contains("lettuce") && !Point3f.lettuceSpacesOccupied.contains(gridSpace1)){ //Check that they are holding a lettuce and not going to put it on another lettuce
					LettuceList.add(new GameObject("res/lettuce.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "lettuce")));
					objectPlayer1Holding.remove(objectPlayer1Holding.indexOf("lettuce"));
				}

				if (objectPlayer1Holding.contains("tomato") && !Point3f.tomatoSpacesOccupied.contains(gridSpace1)){
					TomatoList.add(new GameObject("res/tomato.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "tomato")));
					objectPlayer1Holding.remove(objectPlayer1Holding.indexOf("tomato"));
				}

				if (objectPlayer1Holding.contains("cucumber") && !Point3f.cucumberSpacesOccupied.contains(gridSpace1)){
					CucumberList.add(new GameObject("res/cucumber.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "cucumber")));
					objectPlayer1Holding.remove(objectPlayer1Holding.indexOf("cucumber"));
				}

				if (objectPlayer1Holding.contains("plate") && !Point3f.plateSpacesOccupied.contains(gridSpace1)){
					PlateList.add(new GameObject("res/plate.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "plate")));
					objectPlayer1Holding.remove(objectPlayer1Holding.indexOf("plate"));
				}
			}
			//and theyre infront of the delivery zone
			else if (gridSpace1/1000 == deliveryDropOff.getCentre().getX() && gridSpace1 % 1_000 == deliveryDropOff.getCentre().getY()){
				if (objectPlayer1Holding.contains("lettuce") && objectPlayer1Holding.contains("tomato") && objectPlayer1Holding.contains("cucumber") && objectPlayer1Holding.contains("plate")){
					checkOrderExists(6, 1);
				} else if (objectPlayer1Holding.contains("lettuce") && objectPlayer1Holding.contains("tomato") && !objectPlayer1Holding.contains("cucumber") && objectPlayer1Holding.contains("plate")) {
					checkOrderExists(3, 1);
				} else if (objectPlayer1Holding.contains("lettuce") && !objectPlayer1Holding.contains("tomato") && objectPlayer1Holding.contains("cucumber") && objectPlayer1Holding.contains("plate")) {
					checkOrderExists(4, 1);
				} else if (!objectPlayer1Holding.contains("lettuce") && objectPlayer1Holding.contains("tomato") && objectPlayer1Holding.contains("cucumber") && objectPlayer1Holding.contains("plate")) {
					checkOrderExists(5, 1);
				} else if (objectPlayer1Holding.contains("lettuce") &&  objectPlayer1Holding.contains("plate")) {
					checkOrderExists(0, 1);
				} else if (objectPlayer1Holding.contains("tomato") &&  objectPlayer1Holding.contains("plate")) {
					checkOrderExists(1, 1);
				} else if (objectPlayer1Holding.contains("cucumber") &&  objectPlayer1Holding.contains("plate")) {
					checkOrderExists(2, 1);
				} else {
					System.out.println("invalid order");
				}
			}


		//Not holding soemthing ...
		} else {
			//and there is a lettuce or plate etc. infront of them
			if (Point3f.lettuceSpacesOccupied.contains(gridSpace1) || Point3f.plateSpacesOccupied.contains(gridSpace1) || Point3f.tomatoSpacesOccupied.contains(gridSpace1) || Point3f.cucumberSpacesOccupied.contains(gridSpace1)){
				if (Point3f.lettuceSpacesOccupied.contains(gridSpace1)){
					objectPlayer1Holding.add("lettuce");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : LettuceList){
						if (temp.getCentre().getX() == gridSpace1/1000 && temp.getCentre().getY() == gridSpace1%1000){
							LettuceList.remove(temp);
						}
					}
					Point3f.lettuceSpacesOccupied.remove(Point3f.lettuceSpacesOccupied.indexOf(gridSpace1));
				}	
				
				if (Point3f.tomatoSpacesOccupied.contains(gridSpace1)){
					objectPlayer1Holding.add("tomato");
					for (GameObject temp : TomatoList){
						if (temp.getCentre().getX() == gridSpace1/1000 && temp.getCentre().getY() == gridSpace1%1000){
							TomatoList.remove(temp);
						}
					}
					Point3f.tomatoSpacesOccupied.remove(Point3f.tomatoSpacesOccupied.indexOf(gridSpace1));
				}	

				if (Point3f.cucumberSpacesOccupied.contains(gridSpace1)){
					objectPlayer1Holding.add("cucumber");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : CucumberList){
						if (temp.getCentre().getX() == gridSpace1/1000 && temp.getCentre().getY() == gridSpace1%1000){
							CucumberList.remove(temp);
						}
					}
					Point3f.cucumberSpacesOccupied.remove(Point3f.cucumberSpacesOccupied.indexOf(gridSpace1));
				}	

				if (Point3f.plateSpacesOccupied.contains(gridSpace1)){
					objectPlayer1Holding.add("plate");

					//Lettuce position removed from the lettuce array and removed from the lettuce collisions
					for (GameObject temp : PlateList){
						if (temp.getCentre().getX() == gridSpace1/1000 && temp.getCentre().getY() == gridSpace1%1000){
							PlateList.remove(temp);
						}
					}
					Point3f.plateSpacesOccupied.remove(Point3f.plateSpacesOccupied.indexOf(gridSpace1));
				}
				//If theres not those but lettuce bin or other veg bin instead
			} else if (Point3f.lettuceBinSpacesOccupied.contains(gridSpace1) || Point3f.tomatoBinSpacesOccupied.contains(gridSpace1) || Point3f.cucumberBinSpacesOccupied.contains(gridSpace1)){
				if (Point3f.lettuceBinSpacesOccupied.contains(gridSpace1)){
					LettuceList.add(new GameObject("res/lettuce.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "lettuce")));
				} else if (Point3f.tomatoBinSpacesOccupied.contains(gridSpace1)){
					TomatoList.add(new GameObject("res/tomato.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "tomato")));
				} else if (Point3f.cucumberBinSpacesOccupied.contains(gridSpace1)){
					CucumberList.add(new GameObject("res/cucumber.png",100,100,Point3f.setPointInit(gridSpace1/1000,gridSpace1 % 1_000, "cucumber")));
				}
			}		
		}			
		Controller.getInstance().setKeyLPressed(false);
	}

	public static void checkOrderExists (int value, int playerNum) throws LineUnavailableException, IOException, UnsupportedAudioFileException, InterruptedException{
		if (OrderNameList.contains(value)){
			if (playerNum == 0)
				objectPlayerHolding.clear();
			else 
				objectPlayer1Holding.clear();

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

			//Play the sound
			musicPlayer("res/coin.wav", false);
		} else {
			musicPlayer("res/wrong.wav", false);
			System.out.println("order does not exist");
		}
	}

	private int spaceInfrontOfPlayer (int playerNum){
		//Find the position of the player
		int gridSpace; String switchCase;
		if (playerNum == 0){
			gridSpace = Point3f.getGridValue(Player.getCentre());
			switchCase = directPlayerfacing;
		} else {
			gridSpace = Point3f.getGridValue(Player1.getCentre());
			switchCase = directPlayer1facing;
		}
		//Adjust to find the space infront of them

		switch (switchCase){
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

	public GameObject getPlayer1() {
		return Player1;
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

	public CopyOnWriteArrayList<GameObject> getHoles() {
		return HoleList;
	}

	public int getScore() { 
		return Score;
	}

	public static Clip backgroundClip;
	public static Clip clip;
	public static void musicPlayer(String pathName, Boolean keepLooping) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathName).getAbsoluteFile());
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        clip.open(audioInputStream);
		if (keepLooping)
        	clip.loop(Clip.LOOP_CONTINUOUSLY);

        clip.start();
	}

	public static void startBackgroundMusic () throws UnsupportedAudioFileException, IOException, LineUnavailableException{
		AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("res/overallmusic.wav").getAbsoluteFile());
        try {
            backgroundClip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        backgroundClip.open(audioInputStream);
        backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        backgroundClip.start();
	}

	public static void musicStop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }

	public static void overallMusicStop() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        clip.stop();
        clip.close();
    }
}
