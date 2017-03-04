import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


/**
 * 
 * this class describe the main window of the game .
 * @author fat-man
 *
 */
public class MainWindow extends JFrame {
	
	private JLabel game_content;// label to hold the game content .
	private GameLand land;// object of the land game .
	
	private String explenation;//explenation string explene the score and describe how to play.
	private JLabel show_score;//lable hold the explanation string .
	
	private Container container;
	

	private JButton[] button = new JButton[3];
	
	public MainWindow() {
		super("java snake game ");
		
		//getting the height and width  values 
		ContentSize sizes =ContentSize.getInfo();
		int height = sizes.getTootalHeight();
		int width = sizes.getTootalWidth();
				
				
		//set the bound of main window .
		setBounds(0,0,width,height);
		
		container = getContentPane();//set container object .
		land = new GameLand();//set land of the game ..
		
		startWindow();

	}


	/**
	 * 
	 * method draw start window hold the main menu .
	 * */
	  void startWindow() { 
		  game_content = new JLabel();
		  
		  //set the buttons .
		  button[0] = new JButton("new Game");
		  button[1]=new JButton("Load Game");
		  button[2] = new JButton("Exit");
		  
		  int y = getWidth() /3;//the y location
		  //draw the buttons 
		  for (int i = 0 ; i < button.length ; i++)
		  { 
			button[i].setLocation(getHeight() / 3, y);
		  	button[i].setSize(120, 30);
	  		y += 35;
	  		button[i].addActionListener(new Handler());
	  		game_content.add(button[i]); 
		  } 
		  container.add(game_content);
	  
	  }
	  /*
	   * inner class to handl the action of start window button (main menu )
	   */
	  private class Handler implements ActionListener {
	  
	  @Override 
	  
	  public void actionPerformed(ActionEvent e) { // TODOAuto-generated method stub
		 if (e.getSource() == button[0]) {
			//begen new game . 
			 game_content.setVisible(false);
			 setFocusable(false);
			 land = new GameLand();
			 gameWindow(); 
			 requestFocusInWindow();
	  		} 
		  if(e.getSource() == button[1]) { 
			  
			  game_content.setVisible(false);//removet the start windiw 
			  //load the game window .
			  setFocusable(false);
			  gameWindow();
			  requestFocusInWindow();			  
		  }
		  if (e.getSource() == button[2]) {
			  
			  System.exit(0);//exit from game . 
		  }
		  
	  }
	  
	  }
	 /*
	  * method draw game window . 
	  * 
	  */
	void gameWindow() {
		setFocusable(true);
		container = getContentPane();
		game_content = new JLabel();
		container.add(game_content);
		game_content.setVisible(false);
		game_content = new JLabel(new ImageIcon("image" + "\\gameback.jpg"));
		
		explenation = "<html>score :" 
						+ land.getScore() 
						+ "<br>move the snake : (use diracton keys )"
						+ "<br> press space to pause and resume</html> ";
		show_score = new JLabel(explenation);

		// show Score
		show_score.setLocation(950, 500);
		show_score.setSize(120, 120);
		
		//handle the keyboard action .
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				
				switch (e.getKeyCode()) {
				
					case KeyEvent.VK_DOWN://in case you pressed down
						
						land.getSnake().setDown();//set direction down to snake movement
						break;
					
					case KeyEvent.VK_LEFT://in case you pressed Left
						
						land.getSnake().setLeft();//set direction left to snake movement
						break;
					
					case KeyEvent.VK_RIGHT://in case you pressed Right
						
						land.getSnake().setRight();//set direction Right to snake movement
						break;
					
					case KeyEvent.VK_UP://in case you pressed up
						
						land.getSnake().setUp();
						break;
					
					case KeyEvent.VK_SPACE://in case you pressed space .
						
						//set stop mode to snake .
						Snake snake =land.getSnake();
						snake.stop();
						
						//check if snake Alive to reset new game .
						if (!land.getSnake().isAlive()) {
							
							// to start over >>
							land.setSnake();
						
						}else{
							
							//back to main meun.
							game_content.setVisible(false);
							startWindow();
						
						}
						break;
				}

			}
		});
		// timer show the score .
		Timer reShow = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explenation = "<html>score :" 
							+ land.getScore() 
							+ "<per><br>move the snake:(use diracton keys )"
							+ "<br> press space to pause and resume</html> ";
				show_score.setText(explenation);
			}
		});

		reShow.start();// start show the explanation string .
		
		//add the set content .
		game_content.add(show_score);
		game_content.add(land);
		//add the game content to the window 
		container.add(game_content);
	}

}
