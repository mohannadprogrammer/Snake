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
		
		do{
			
			//detect the location of the apple randomly .
			this.x=((int)(Math.random()*19))*ContentSize.getInfo().getPoint();
			this.y=((int)(Math.random()*19))*ContentSize.getInfo().getPoint();
			
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

	public  void drawApple(Graphics g){
		g.setColor(Color.blue);
		g.fillOval(x,y,ContentSize.getInfo().getPoint() , ContentSize.getInfo().getPoint());
	}
	
	
	
	
	
}
