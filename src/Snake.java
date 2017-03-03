import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;

public class Snake {
	//this array list is 
	
	private ArrayList<Integer> pointx = new ArrayList<Integer>();
	private ArrayList<Integer> pointy = new ArrayList<Integer>();
	
	private boolean isMoveUp = false;
	private boolean isMoveDown = false;
	private boolean isMoveRight = true;
	private boolean isMoveLeft = false;
	
	public boolean alive =true;
	public boolean pause=false;
	
	private  final int BIGX = ContentSize.getInfo().getPoint() * 20;
	private  final int BIGY = ContentSize.getInfo().getPoint() * 18;
	
	private Apple apple;
	
	public int speed = 100;
	
		// the start snake points .
	public Snake (ArrayList <Integer> diedPointX ,
			ArrayList <Integer> diedPointY){
		
		getDefaultLocation();
		setApple( diedPointX , diedPointY );
		
	}
	
	public void getDefaultLocation (){
		
		pointx=new ArrayList<Integer>();
		pointy= new ArrayList<Integer>();
		
		int temp=ContentSize.getInfo().getPoint() * 10;
		
		for (int i = 0 ; i < 10 ; i++ ){
			
			pointx.add(temp);
			pointy.add(ContentSize.getInfo().getPoint() * 4);
			
			temp-=ContentSize.getInfo().getPoint() ;
			
		}
	}
	
	
	public void drawSnake (Graphics g , 
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY
			){
		
		
		//LOOP TO DROW THE BODY..>>
		for (int i = 1 ; i < this.pointx.size() ; i++){
			
			g.setColor(new Color(77,195,77));
			g.fillOval(pointx.get(i), pointy.get(i),ContentSize.getInfo().getPoint(),ContentSize.getInfo().getPoint());
			
		}
		
		//draw the head ..>>
		g.setColor(Color.RED);
		g.fillOval(pointx.get(0), pointy.get(0),ContentSize.getInfo().getPoint(),ContentSize.getInfo().getPoint());
		
		apple.drawApple(g);
		
		for (int i = 0 ; i < diedPointX.size() ; i++ ){
			
			g.setColor(Color.orange);
			g.fillRect(diedPointX.get(i), diedPointY.get(i), ContentSize.getInfo().getPoint(), ContentSize.getInfo().getPoint());
			
		}
	}
	private void setApple(
							ArrayList <Integer >diedPointX ,
							ArrayList <Integer >diedPointY){

		ArrayList <Integer> Xpoints =(ArrayList<Integer>) this.pointx.clone();
		ArrayList <Integer> Ypoints =(ArrayList<Integer>) this.pointy.clone();
		
		for (int i=0;i<diedPointX.size();i++){
			Xpoints.add(diedPointX.get(i));
			Ypoints.add(diedPointY.get(i));
		}
		apple = new Apple(Xpoints,Ypoints);
	}
	private void step (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		die(diedPointX , diedPointX  );
		if (this.pointx.get(0)==apple.getX()&&this.pointy.get(0)==apple.getY()){
			eat();
			setApple(diedPointX ,diedPointY);
		}
		
		for(int i =pointx.size()-1;i>0;i--){
			pointx.set(i,pointx.get(i-1));
			pointy.set(i,pointy.get(i-1));
			
		}
		
	}
	
	
	
	public void moveUp(
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		step(diedPointX,diedPointY);
		
		if (pointy.get(0)<ContentSize.getInfo().getPoint())
			pointy.set(0,BIGY);
		else
			pointy.set(0,pointy.get(0)-ContentSize.getInfo().getPoint());
	}
	
	public void moveDown(
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		
		step(diedPointX , diedPointY );
		
		if (pointy.get(0)>=BIGY)
			pointy.set(0,0);
		else
			pointy.set(0,pointy.get(0)+ContentSize.getInfo().getPoint());
		
	}
	
	public void moveRight (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		step(diedPointX , diedPointY );
		
		if (pointx.get(0)>=BIGX)
			pointx.set(0,0);
		else
			pointx.set(0,pointx.get(0)+ContentSize.getInfo().getPoint());
			
		
	}
	
	public void moveLeft (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		step(diedPointX , diedPointX );
		
		if (pointx.get(0)<10)
			pointx.set(0,BIGX);
		else
			pointx.set(0,pointx.get(0)-ContentSize.getInfo().getPoint());
		
	}

	private void eat (){
		
		pointx.add(0,apple.getX());
		pointy.add(0,apple.getY());
	}
	public void stop(){
		this.pause=true;
	}
	public	void die (
			ArrayList <Integer >diedPointX ,
			ArrayList <Integer >diedPointY){
		int testx=pointx.get(0);
		int testy=pointy.get(0);
		for(int i =1;i<pointx.size();i++){
			if (testx==pointx.get(i)&&testy==pointy.get(i)){
				alive=false;
				break;
				
			}
			
		}
		
		for(int i =1;i<diedPointX.size();i++){
			if (testx==diedPointX.get(i)&&testy==diedPointY.get(i)){
				alive=false;
				break;
				
			}
			
		}
		
	
	}
	
	public void  setDown(){
		this.pause=false;
		
		if(!this.isMoveUp){
			
			this.isMoveDown=true;
			this.isMoveLeft=false;
			this.isMoveRight=false;
			this.isMoveUp=false;
		
		}
	}
	
	public void  setUp(){
		this.pause=false;
		
		if(!this.isMoveDown){
			
			this.isMoveDown=false;
			this.isMoveLeft=false;
			this.isMoveRight=false;
			this.isMoveUp=true;
		
		}
	}
	
	public void  setRight(){
		this.pause=false;
		
		if(!this.isMoveLeft){
		
			this.isMoveDown=false;
			this.isMoveLeft=false;
			this.isMoveRight=true;
			this.isMoveUp=false;
		
		}
	
	}
	
	public void  setLeft(){
		
		this.pause=false;
		
		if(!this.isMoveRight){
			
			this.isMoveDown=false;
			this.isMoveLeft=true;
			this.isMoveRight=false;
			this.isMoveUp=false;
		
		}
	}
	
	public void  SnakeBehave    (ArrayList <Integer >diedPointX 
								,
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
