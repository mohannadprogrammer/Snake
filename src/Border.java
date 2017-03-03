import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Border extends JPanel {

	private ArrayList <Integer> diedPointX = new ArrayList <Integer> ();
	private ArrayList <Integer> diedPointY = new ArrayList <Integer> ();
	
	private Snake snake ;
	private JLabel gameOver;
	
	public Border (){
		snake = new Snake(diedPointX ,  diedPointY );
		setBounds(0,0,ContentSize.getInfo().getLandWidth(),ContentSize.getInfo().getLandHeight());
		
		setBackground(Color.BLACK);
		gameOver = new JLabel();
		add(gameOver);
		
		Timer sankeMove = new Timer (100,new ActionListener (){
			
			public void actionPerformed(ActionEvent e ){
				if (snake.alive){
				if (!snake.pause){
					repaint();
				}else{
					
				}
				//gameOver.setText("");
				gameOver.setIcon(null);
				}else{
					
					//gameOver.setText("<html><Font size=7 color=red> <br><br><br>Game Over</html>");
					gameOver.setIcon(new ImageIcon ("image\\gameOver.jpg"));
					
					
				}		
			}
			
		});
		sankeMove.start();
	}
	
	public void setSnake(){
		
		this.snake=new Snake( this.diedPointX , this.diedPointY);
	}
	
	public Snake getSnake (){	
		return this.snake;
	}
	
	@Override
	public void paintComponent (Graphics g){
		
		super.paintComponent(g  );
		snake.drawSnake(g , diedPointX , diedPointY);
		snake.SnakeBehave(diedPointX , diedPointY);
	
	}
	
	
	
}

