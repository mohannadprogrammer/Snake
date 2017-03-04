import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * snake class describe the snake in the game .
 * @author fat-man
 *
 */

public class Snake {
	//this array list content the location of snake body points .
	private ArrayList<Integer> point_x , point_y ;
	
	// detect snake movement direction .
	private boolean is_move_up , is_move_down , is_move_right , is_move_left ;
	
	//snake pause mode and die mode .
	private boolean alive  , pause;
	
	//the maximum x , and y that thw sanke can retch to . 
	private  final int BIG_X = ContentSize.getInfo().getPoint() * 20;
	private  final int BIG_Y = ContentSize.getInfo().getPoint() * 18;
	
	// apple object : snake should eat .  
	private Apple apple;
	
	
	
	public Snake (ArrayList <Integer> diedPointX ,
				  ArrayList <Integer> diedPointY){
		
		this.alive=true;
		this.pause=false;
		
		//set the default movement 
		this.is_move_down = false ;
		this.is_move_up = false ;
		this.is_move_left = false ;
		this.is_move_right =true ;
		
		//set the default location of the snake .
		getDefaultLocation();
		setApple( diedPointX , diedPointY );
		
	}
	
	/**
	 * @return the alive
	 */
	public boolean isAlive() {
		return alive;
	}
	
	/**
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}
	
	//method set the default location of snake in the game .
	public void getDefaultLocation (){
		
		//initial the program
		point_x=new ArrayList<Integer>();
		point_y= new ArrayList<Integer>();
		
		//set Xs default location .
		ContentSize sizes =ContentSize.getInfo();
		int number_point_default=10; 
		int x_default_valuse = sizes.getPoint() * number_point_default;
		
		for (int i = 0 ; i < number_point_default ; i++ ){
			int y_point_default =sizes.getPoint() * 4;
			point_x.add(x_default_valuse);
			point_y.add(y_point_default);
			
			x_default_valuse -= sizes.getPoint() ;
			
		}
	}
	
	// method draw snake in the game window . 
	public void drawSnake (Graphics graphic , 
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y
			){
		
		//getting point size .
		ContentSize sizes =ContentSize.getInfo();
		int point =sizes.getPoint();
		
		//LOOP TO DROW THE BODY..>>
		for (int i = 1 ; i < this.point_x.size() ; i++){
			
			graphic.setColor(new Color(77,195,77));
			graphic.fillOval(point_x.get(i) ,
					point_y.get(i) ,
					point ,
					point
					);
			
		}
		
		//draw the head ..>>
		graphic.setColor(Color.RED);
		graphic.fillOval(point_x.get(0),
				point_y.get(0) ,
				point ,
				point
				);
		
		
		//draw the body of the snake . 
		for (int i = 0 ; i < died_point_x.size() ; i++ ){
			graphic.setColor(Color.orange);
			graphic.fillRect(died_point_x.get(i),
					died_point_y.get(i) ,
					point ,
					point
					);
			
		}
		
		apple.drawApple(graphic);//draw apple .
	}
	// set new Apple depend on snake location . 
	private void setApple(	ArrayList <Integer >died_point_x ,
							ArrayList <Integer >died_point_y){
		
		
		//clone the array list of rhte body int Xpoint and Ypoint Variable . before change values
		ArrayList <Integer> x_points =
					(ArrayList<Integer>) this.point_x.clone();
		ArrayList <Integer> y_points =	
				(ArrayList<Integer>) this.point_y.clone();
		
		
		for (int i=0;i<died_point_x.size();i++){
			x_points.add(died_point_x.get(i));
			y_points.add(died_point_y.get(i));
		}
		apple = new Apple(x_points,y_points);
	}
	/*
	 * the Algrithom of new step in snake movement .
	 *@return isEat or not .
	 */
	private boolean step (
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){
		
		boolean is_eat = false ;
		
		//check if the snake die or not 
		die(died_point_x , died_point_y  );
		
		//check if the snake eat the apple or not .
		if (this.point_x.get(0) == apple.getX() &&
			this.point_y.get(0) == apple.getY() ){
			
			is_eat =true ;
			eat();
			setApple(died_point_x ,died_point_y);//set new apple .
		}
		
		for(int i = point_x.size() - 1 ; i > 0 ; i-- ){
			point_x.set(i,point_x.get(i-1));
			point_y.set(i,point_y.get(i-1));
			
		}
		return is_eat;
	}
	
	/**
	 * the method made to describe the snake move up movement  .
	 * @return isEat or not 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */

	public boolean moveUp(
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){
		
		//made step to the snake .
		boolean is_eat = step(died_point_x,died_point_y);
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int y_move = point_y.get(0) - sizes.getPoint() ;
		
		//check if this step make my snake in the highest point or not 
		if (point_y.get(0) < sizes.getPoint())
			
			point_y.set(0,BIG_Y);//if true set the head to the lowest point .
		
		else
			
			point_y.set(0,y_move);//add step to the head to up .
		return is_eat ;
	}

	/**
	 * the method made to describe the snake move Down movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public boolean moveDown(
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){
		
		
		boolean is_eat = step(died_point_x,died_point_y);//made step to the snake .
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int x_move = point_y.get(0) + sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (point_y.get(0) >= BIG_Y)
			
			point_y.set(0,0);//set to the begin of up side
		
		else
			
			point_y.set(0,x_move );// move head down .
		
		return is_eat ;
	}

	/**
	 * the method made to describe the snake move Right movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public boolean moveRight (
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){
		
		
		boolean is_eat = step(died_point_x,died_point_y);//made step to the snake .
		
		ContentSize sizes = ContentSize.getInfo();//size to used in get information about point value.
		int y_move =point_x.get(0) + sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (point_x.get(0)>=BIG_X)
			
			point_x.set(0,0);//set to the begin of left side 
		
		else
			
			point_x.set(0,y_move);// move head Right .
			
		return is_eat;
	}

	/**
	 * the method made to describe the snake move left movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public boolean  moveLeft (
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){

		boolean is_eat = step(died_point_x,died_point_y);//made step to the snake .
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int x_move =point_x.get(0) - sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (point_x.get(0)<10)
			
			point_x.set(0,BIG_X);//set to the begin of Right side 
		
		else
			
			point_x.set(0,x_move);//move head left .
		
		return is_eat ;
	}

	/**
	 * eat the apple .
	 */
	private void eat (){
		//add the apple point to the snake points
		
		point_x.add(0 , apple.getX());
		point_y.add(0 , apple.getY());
	}

	/**
	 * 
	 *  make change the snake mode to stop .
	 */
	public void stop(){
		
		this.pause=true;
	}

	/**
	 * the method made to describe the snake move up movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public	void die (
			ArrayList <Integer >died_point_x ,
			ArrayList <Integer >died_point_y){
		
		//use in test the head in die point or not
		int testx=point_x.get(0);
		int testy=point_y.get(0);
		
		//checking diepoint 
		for(int i = 1 ; i < point_x.size() ; i++ ){
			if (testx==point_x.get(i)&&testy==point_y.get(i)){
				alive=false;
				break;
				
			}
			
		}
		
		for(int i = 1 ; i < died_point_x.size() ; i++ ){
			if (testx==died_point_x.get(i)&&testy==died_point_y.get(i)){
				alive=false;
				break;
				
			}
			
		}
		
	
	}

	/**
	 * make snake move down
	 */
	public void  setDown(){
		this.pause=false;
		
		if(!this.is_move_up){
			
			this.is_move_down=true;
			this.is_move_left=false;
			this.is_move_right=false;
			this.is_move_up=false;
		
		}
	}

	/**
	 * make snake move Up
	 */
	public void  setUp(){
		this.pause=false;
		
		if(!this.is_move_down){
			
			this.is_move_down=false;
			this.is_move_left=false;
			this.is_move_right=false;
			this.is_move_up=true;
		
		}
	}

	/**
	 * make snake move Right
	 */
	public void  setRight(){
		this.pause=false;
		
		if(!this.is_move_left){
		
			this.is_move_down=false;
			this.is_move_left=false;
			this.is_move_right=true;
			this.is_move_up=false;
		
		}
	
	}

	/**
	 * make snake move Left
	 */
	public void  setLeft(){
		
		this.pause=false;
		
		if(!this.is_move_right){
			
			this.is_move_down=false;
			this.is_move_left=true;
			this.is_move_right=false;
			this.is_move_up=false;
		
		}
	}

	/**
	 * the method made to describe the snake movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public boolean  SnakeBehave    (ArrayList <Integer >died_point_x ,
								ArrayList <Integer >died_point_y   ){
		boolean is_eat =false ;
		if (is_move_down)
			is_eat = this.moveDown(died_point_x , died_point_y );
		if (is_move_up)
			is_eat = this.moveUp(died_point_x , died_point_y );
		if (is_move_left)
			is_eat = this.moveLeft(died_point_x , died_point_y );
		if(is_move_right)
			is_eat = this.moveRight(died_point_x , died_point_y );
		return is_eat;
	}
}
