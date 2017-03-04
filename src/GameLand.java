import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * 
 * class describe the land of game .
 * @author fat-man
 *
 */
public class GameLand extends JPanel {
	//died points the detect  .
	private ArrayList <Integer> died_point_x ,  died_point_y ;
	
	private Snake snake ;//snake object . 
	
	private JLabel game_over;//label to show game over if the snake die .
	private int score ;
	
	public GameLand (){
		
		//set the initial score .
		this.score = 0;
		
		//set the died points .
		died_point_x = new ArrayList <Integer> ();
		died_point_y = new ArrayList <Integer> ();
		
		//getting the height and width  values .
		ContentSize sizes =ContentSize.getInfo();
		int height = sizes.getLandHeight();
		int width = sizes.getLandWidth();
		
		snake = new Snake(died_point_x ,  died_point_y );//set snake object .
		
		setBounds(0,0,width,height);//set the land bounds .
		
		setBackground(Color.BLACK);
		game_over = new JLabel();
		add(game_over);
		
		//Timer re-draw every 0.1 secend .
		Timer sankeMove = new Timer (100,new ActionListener (){
			
			public void actionPerformed(ActionEvent e ){
				//check if snake is alive or not .
				if (snake.isAlive()){
					//check if snake is in stop mode or not 
					if (!snake.isPause()){
						repaint();
					}
					
				game_over.setText("");
				
				}else{
					
					game_over.setText("<html><Font size=7 color=red>"
							+ " <br><br><br>Game Over</html>");
				}		
			}
			
		});
		sankeMove.start();//snake start move .
	}

	/**
	 * set new Snake .
	 */	
	public void setSnake(){
		
		this.snake=new Snake( this.died_point_x , this.died_point_y);
	}

	/**
	 * @return the  snake .
	 */
	public Snake getSnake (){	
		return this.snake;
	}
	
	/**
	 * @return the  score of the game  .
	 */
	public int getScore (){	
		return this.score;
	}

	/**
	 * @param Graphics the Graphics  to draw .
	 */
	@Override
	public void paintComponent (Graphics g){
		
		super.paintComponent(g  );
		
		snake.drawSnake(g , died_point_x , died_point_y);//snake draw in the land .
		
		boolean is_eat = snake.SnakeBehave(died_point_x , died_point_y);//snake make normal behave .
		
		//add to score if the snake is eat . 
		if (is_eat){
			this.score++;
		}
	}
	
	
	
}

