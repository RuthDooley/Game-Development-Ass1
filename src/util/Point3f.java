package util;
/*
 * Modified by Abraham Campbell on 15/01/2020.
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
//Modified from Graphics 3033J course point class  by Abey Campbell 

import java.util.ArrayList;

public class Point3f {

	private float x;
	private float y;
	private float z;
	
	private float xBoundary=1100;
	private float yBoundary=800;
	
	
	// default constructor
	public Point3f() { 
		setX(0.0f);
		setY(0.0f);
		setZ(0.0f);
	}
	
	//initializing constructor
	public Point3f(float x, float y, float z) { 
		this.setX(x);
		this.setY(y);
		this.setZ(z); 
	}
	
	private void setBoundary(float xBoundary, float yBoundary) {
		this.xBoundary = xBoundary;
		this.yBoundary = yBoundary;
	}

	// sometimes for different algorithms we will need to address the point using positions 0 1 2 
	public float getPostion(int postion)
	{
		switch(postion)
		{
		case 0: return getX();
		case 1: return getY();
		case 2: return getZ(); 
		default: return Float.NaN;  
		} 
	}
	
	public String toString(){
		return ("(" + getX() +"," + getY() +"," + getZ() +")");
    }

	 //implement Point plus a Vector and comment what the method does 
	public Point3f PlusVector(Vector3f Additonal) { 
		return new Point3f(this.getX()+Additonal.getX(), this.getY()+Additonal.getY(), this.getZ()+Additonal.getZ());
	} 
	
	 //implement Point minus a Vector and comment what the method does 
	public Point3f MinusVector(Vector3f Minus) { 
		return new Point3f(this.getX()-Minus.getX(), this.getY()-Minus.getY(), this.getZ()-Minus.getZ());
	}
	
	
	/// implement Point - Point  and comment what the method does  
	public Vector3f MinusPoint(Point3f Minus) { 
		return new Vector3f(this.getX()-Minus.getX(), this.getY()-Minus.getY(), this.getZ()-Minus.getZ());
	}

	public static ArrayList<Integer> spacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> lettuceBinSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> tomatoBinSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> cucumberBinSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> lettuceSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> tomatoSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> cucumberSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> binSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> counterSpacesOccupied = new ArrayList<Integer>();
	public static ArrayList<Integer> plateSpacesOccupied = new ArrayList<Integer>();
	//Gets added to the list of all colliders, also gets added to the list of specific assets give its type
	public static Point3f setPointInit (int x, int y, String type){
		int spaceID = 0;
		if (y == 0)
			spaceID = Integer.parseInt("" + x + "000");
		else
			spaceID = Integer.parseInt("" + x + y);
		switch (type){
			case "lettuceBin":
				if (!lettuceBinSpacesOccupied.contains(spaceID)) 
					lettuceBinSpacesOccupied.add(spaceID);
					addCollider(spaceID);
				break;
			case "tomatoBin":
				if (!tomatoBinSpacesOccupied.contains(spaceID)) 
					tomatoBinSpacesOccupied.add(spaceID);
					addCollider(spaceID);
				break;
			case "cucumberBin":
				if (!cucumberBinSpacesOccupied.contains(spaceID)) 
					cucumberBinSpacesOccupied.add(spaceID);
					addCollider(spaceID);
				break;
			case "bin":
				if (!binSpacesOccupied.contains(spaceID)) 
					binSpacesOccupied.add(spaceID);
					addCollider(spaceID);
				break;
			case "counter":
				if (!counterSpacesOccupied.contains(spaceID)) 
					counterSpacesOccupied.add(spaceID);
					addCollider(spaceID);
				break;
			case "dropoff":
				addCollider(spaceID);
				break;
			case "player":
				addCollider(spaceID);
				break;
			case "lettuce":
				if (!lettuceSpacesOccupied.contains(spaceID)) 
					lettuceSpacesOccupied.add(spaceID);
				break;
			case "tomato":
				if (!tomatoSpacesOccupied.contains(spaceID)) 
					tomatoSpacesOccupied.add(spaceID);
				break;

			case "cucumber":
				if (!cucumberSpacesOccupied.contains(spaceID)) 
					cucumberSpacesOccupied.add(spaceID);
				break;
			case "plate":
				if (!plateSpacesOccupied.contains(spaceID)) 
					plateSpacesOccupied.add(spaceID);
					break;
			default:

				break;
		}
		return new Point3f(x, y, 0);
	}

	public static void addCollider (int spaceID){
		if (!spacesOccupied.contains(spaceID))
			spacesOccupied.add(spaceID);
		printColliderArrayList();
	}

	public static void removeCollider (int spaceID){
		if (spacesOccupied.contains(spaceID))
			spacesOccupied.remove(spacesOccupied.indexOf(spaceID));
		printColliderArrayList();
	}

	public static void printColliderArrayList (){
		// System.out.println(spacesOccupied.toString());
	}

	public static int getGridValue (Point3f coord){
		if ((int)coord.getY() == 0)
			return Integer.parseInt("" + ((int)coord.getX()) + "000");
		return Integer.parseInt("" + ((int)coord.getX()) + ((int)coord.getY()));
	}

	// public static int valueNextToInput (int value, int difference){
	// 	int result = 0;
	// }
	
	 //Use for direct application of a Vector 
	public void ApplyVector(Vector3f vector) { 
		 setX(CheckBoundary(this.getX()+vector.getX(), "x"));
		 setY(CheckBoundary(this.getY()-vector.getY(), "y"));
		 setZ(CheckBoundary(this.getZ()-vector.getZ(), "z")); 
	}

	private float CheckBoundary(float f, String direction) {
		if (f<0) f=0.0f;

		if (f > yBoundary && (direction == "y" || direction == "z")){
			f = yBoundary;
		}

		if (f > xBoundary && direction == "x"){
			f = xBoundary;
		}

		return f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getZ() {
		return z;
	}

	public void setZ(float z) {
		this.z = z;
	}
	
	
	  
	  
	 // Remember point + point  is not defined so we not write a method for it.  
}
