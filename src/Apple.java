import java.awt.*;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

/**
 * this Class describe the apple food that snake eat it .
 * 
 * @author fat-man
 *
 */
public  class Apple  {
	
	int x, y ;//this attribute describe the postion of apple should by there . 
	
	
	public Apple (ArrayList <Integer> Xpoints,ArrayList<Integer>  Ypoints){
		boolean test = false;
		// the rang of the Apple Xs values .
		int point_rang =19;
		
		//getting the point values .
		ContentSize sizes =ContentSize.getInfo();
		int point = sizes.getPoint();
		
		do{
			
			//detect the location of the apple randomly .
			this.x=((int)(Math.random() * point_rang )) * point;
			this.y=((int)(Math.random() * point_rang )) * point;
			
			//check if the loaction contontaint of any of snake location or not . 
			for (int i = 0 ; i < Xpoints.size() ; i++ ){
				
				if (x == Xpoints.get(i) && y == Ypoints.get(i) ){
					
					test=true;
					break;
				
				}else{
					
					test=false;
				
				}
			}
			
		}while (test);
		
		
	}
	
	/**
	 * @return the x
	 */
	public int getX() {
		
		return x;
	
	}

	/**
	 * @param x the x to set
	 */
	
	public void setX(int x) {
		
		this.x = x;
	
	}

	/**
	 * @return the y
	 */
	public int getY() {
		
		return y;
	
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		
		this.y = y;
	
	}

	/**
	 * @param Graphics the graphics to draw .
	 */
	public  void drawApple(Graphics g){
		
		//getting the point values .
		ContentSize sizes =ContentSize.getInfo();
		int point = sizes.getPoint();
				
		g.setColor(Color.blue);
		g.fillOval(x,y,point , point );
	
	}
	
	
	
	
	
}
