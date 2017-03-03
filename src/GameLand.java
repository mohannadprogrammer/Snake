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
	//died points .
	private ArrayList <Integer> diedPointX = new ArrayList <Integer> ();
	private ArrayList <Integer> diedPointY = new ArrayList <Integer> ();
	
	private Snake snake ;//snake object . 
	
	private JLabel gameOver;//label to show game over if the snake die .
	
	public GameLand (){
		snake = new Snake(diedPointX ,  diedPointY );
		setBounds(0,0,ContentSize.getInfo().getLandWidth(),ContentSize.getInfo().getLandHeight());
		
		setBackground(Color.BLACK);
		gameOver = new JLabel();
		add(gameOver);
		
		//Timer re-draw every 0.1 secend .
		Timer sankeMove = new Timer (100,new ActionListener (){
			
			public void actionPerformed(ActionEvent e ){
				//check if snake is alive or not .
				if (snake.isAlive()){
					//check if snake is in stop mode or not 
					if (!snake.isPause()){
						repaint();
					}
					
				gameOver.setText("");
				
				}else{
					
					gameOver.setText("<html><Font size=7 color=red>"
							+ " <br><br><br>Game Over</html>");
				}		
			}
			
		});
		sankeMove.start();//snake start move .
	}
	
	public void setSnake(){
		
		this.snake=new Snake( this.diedPointX , this.diedPointY);
	}

	/**
	 * @return the  snake .
	 */
	public Snake getSnake (){	
		return this.snake;
	}

	/**
	 * @param Graphics the Graphics  to draw .
	 */
	@Override
	public void paintComponent (Graphics g){
		
		super.paintComponent(g  );
		
		snake.drawSnake(g , diedPointX , diedPointY);//snake draw in the land .
		snake.SnakeBehave(diedPointX , diedPointY);//snake make normal behave .
	
	}
	
	
	
}

