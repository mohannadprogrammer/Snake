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
	private ArrayList<Integer> pointx = new ArrayList<Integer>();
	private ArrayList<Integer> pointy = new ArrayList<Integer>();
	
	// detect snake movement direction .
	private boolean isMoveUp , isMoveDown , isMoveRight , isMoveLeft ;
	
	//snake pause mode and die mode .
	private boolean alive  , pause;
	
	//the maximum x , and y that thw sanke can retch to . 
	private  final int BIGX = ContentSize.getInfo().getPoint() * 20;
	private  final int BIGY = ContentSize.getInfo().getPoint() * 18;
	
	// apple object : snake should eat .  
	private Apple apple;
	
	
	
	public Snake (ArrayList <Integer> diedPointX ,
				  ArrayList <Integer> diedPointY){
		
		this.alive=true;
		this.pause=false;
		
		//set the default movement 
		this.isMoveDown = false ;
		this.isMoveUp = false ;
		this.isMoveLeft = false ;
		this.isMoveRight =true ;
		
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
	/*
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}
	
	//method set the default location of snake in the game .
	public void getDefaultLocation (){
		
		pointx=new ArrayList<Integer>();
		pointy= new ArrayList<Integer>();
		
		//set Xs default location .
		ContentSize sizes =ContentSize.getInfo();
		int number_point_default=10; 
		int x_default_valuse = sizes.getPoint() * number_point_default;
		
		for (int i = 0 ; i < number_point_default ; i++ ){
			int y_point_default =sizes.getPoint() * 4;
			pointx.add(x_default_valuse);
			pointy.add(y_point_default);
			
			x_default_valuse -= sizes.getPoint() ;
			
		}
	}
	
	// method draw snake in the game window . 
	public void drawSnake (Graphics g , 
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY
			){
		
		
		//LOOP TO DROW THE BODY..>>
		for (int i = 1 ; i < this.pointx.size() ; i++){
			
			g.setColor(new Color(77,195,77));
			g.fillOval(pointx.get(i) ,
					pointy.get(i) ,
					ContentSize.getInfo().getPoint() ,
					ContentSize.getInfo().getPoint()
					);
			
		}
		
		//draw the head ..>>
		g.setColor(Color.RED);
		g.fillOval(pointx.get(0),
				pointy.get(0) ,
				ContentSize.getInfo().getPoint() ,
				ContentSize.getInfo().getPoint()
				);
		
		apple.drawApple(g);
		//draw the body of the snake . 
		for (int i = 0 ; i < diedPointX.size() ; i++ ){
			ContentSize sizes = ContentSize.getInfo();
			g.setColor(Color.orange);
			g.fillRect(diedPointX.get(i),
					diedPointY.get(i) ,
					sizes.getPoint() ,
					sizes.getPoint()
					);
			
		}
	}
	// set new Apple depend on snake location . 
	private void setApple(
							ArrayList <Integer >diedPointX ,
							ArrayList <Integer >diedPointY){
		//clone the array list of rhte body int Xpoint and Ypoint Variable .
		ArrayList <Integer> Xpoints =
					(ArrayList<Integer>) this.pointx.clone();
		ArrayList <Integer> Ypoints =	
				(ArrayList<Integer>) this.pointy.clone();
		
		for (int i=0;i<diedPointX.size();i++){
			Xpoints.add(diedPointX.get(i));
			Ypoints.add(diedPointY.get(i));
		}
		apple = new Apple(Xpoints,Ypoints);
	}
	//the Algrithom of new step in snake movement .
	private void step (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		//check if the snake die or not 
		die(diedPointX , diedPointX  );
		
		//check if the snake eat the apple or not .
		if (this.pointx.get(0) == apple.getX() &&
			this.pointy.get(0) == apple.getY() ){
			
			eat();
			setApple(diedPointX ,diedPointY);//set new apple .
		}
		
		for(int i = pointx.size() - 1 ; i > 0 ; i-- ){
			pointx.set(i,pointx.get(i-1));
			pointy.set(i,pointy.get(i-1));
			
		}
		
	}
	
	/**
	 * the method made to describe the snake move up movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */

	public void moveUp(
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		//made step to the snake .
		step(diedPointX,diedPointY);
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int y_move = pointy.get(0) - sizes.getPoint() ;
		
		//check if this step make my snake in the highest point or not 
		if (pointy.get(0) < sizes.getPoint())
			
			pointy.set(0,BIGY);//if true set the head to the lowest point .
		
		else
			
			pointy.set(0,y_move);//add step to the head to up .
	}

	/**
	 * the method made to describe the snake move Down movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public void moveDown(
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		
		step(diedPointX,diedPointY);//made step to the snake .
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int x_move = pointy.get(0) + sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (pointy.get(0) >= BIGY)
			
			pointy.set(0,0);//set to the begin of up side
		
		else
			
			pointy.set(0,x_move );// move head down .
		
	}

	/**
	 * the method made to describe the snake move Right movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public void moveRight (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		
		step(diedPointX,diedPointY);//made step to the snake .
		
		ContentSize sizes = ContentSize.getInfo();//size to used in get information about point value.
		int y_move =pointx.get(0) + sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (pointx.get(0)>=BIGX)
			
			pointx.set(0,0);//set to the begin of left side 
		
		else
			
			pointx.set(0,y_move);// move head Right .
			
		
	}

	/**
	 * the method made to describe the snake move left movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public void moveLeft (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){

		step(diedPointX,diedPointY);//made step to the snake .
		
		//size to used in get information about point value.
		ContentSize sizes = ContentSize.getInfo();
		int x_move =pointx.get(0) - sizes.getPoint();

		//check if this step make my snake in the lowest point or not
		if (pointx.get(0)<10)
			
			pointx.set(0,BIGX);//set to the begin of Right side 
		
		else
			
			pointx.set(0,x_move);//move head left .
		
	}

	/**
	 * eat the apple .
	 */
	private void eat (){
		//add the apple point to the snake points
		
		pointx.add(0 , apple.getX());
		pointy.add(0 , apple.getY());
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
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		//use in test the head in die point or not
		int testx=pointx.get(0);
		int testy=pointy.get(0);
		
		//checking diepoint 
		for(int i = 1 ; i < pointx.size() ; i++ ){
			if (testx==pointx.get(i)&&testy==pointy.get(i)){
				alive=false;
				break;
				
			}
			
		}
		
		for(int i = 1 ; i < diedPointX.size() ; i++ ){
			if (testx==diedPointX.get(i)&&testy==diedPointY.get(i)){
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
		
		if(!this.isMoveUp){
			
			this.isMoveDown=true;
			this.isMoveLeft=false;
			this.isMoveRight=false;
			this.isMoveUp=false;
		
		}
	}

	/**
	 * make snake move Up
	 */
	public void  setUp(){
		this.pause=false;
		
		if(!this.isMoveDown){
			
			this.isMoveDown=false;
			this.isMoveLeft=false;
			this.isMoveRight=false;
			this.isMoveUp=true;
		
		}
	}

	/**
	 * make snake move Right
	 */
	public void  setRight(){
		this.pause=false;
		
		if(!this.isMoveLeft){
		
			this.isMoveDown=false;
			this.isMoveLeft=false;
			this.isMoveRight=true;
			this.isMoveUp=false;
		
		}
	
	}

	/**
	 * make snake move Left
	 */
	public void  setLeft(){
		
		this.pause=false;
		
		if(!this.isMoveRight){
			
			this.isMoveDown=false;
			this.isMoveLeft=true;
			this.isMoveRight=false;
			this.isMoveUp=false;
		
		}
	}

	/**
	 * the method made to describe the snake movement  .
	 * 
	 * @param deidPointX  and diedpointY to check if the step in die point or 
	 * eat point . 
	 */
	public void  SnakeBehave    (ArrayList <Integer >diedPointX ,
								ArrayList <Integer >diedPointY   ){
		
			if (isMoveDown)
				this.moveDown(diedPointX , diedPointY );
			if (isMoveUp)
				this.moveUp(diedPointX , diedPointY );
			if (isMoveLeft)
				this.moveLeft(diedPointX , diedPointY );
			if(isMoveRight)
				this.moveRight(diedPointX , diedPointY );
	}
}
