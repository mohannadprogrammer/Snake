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
	
	private JLabel gameContent;// label to hold the game content .
	private GameLand land;// object of the land game .
	
	private String explenation;//explenation string explene the score and describe how to play.
	private JLabel showScore;//lable hold the explanation string .
	
	private Container container;
	
	private int score =0;

	private JButton[] button = new JButton[3];
	
	public MainWindow() {
		super("java snake game ");
		//set the bound of main window .
		setBounds(0,
				0,
				ContentSize.getInfo().getTootal_width()
				,ContentSize.getInfo().getTootal_height());
		
		container = getContentPane();//set container object .
		land = new GameLand();//set land of the game ..
		
		startWindow();

	}


	/**
	 * 
	 * method draw start window hold the main menu .
	 * */
	  void startWindow() { 
		  gameContent = new JLabel();
		  
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
	  		gameContent.add(button[i]); 
		  } 
		  container.add(gameContent);
	  
	  }
	  /*
	   * inner class to handl the action of start window button (main menu )
	   */
	  private class Handler implements ActionListener {
	  
	  @Override 
	  
	  public void actionPerformed(ActionEvent e) { // TODOAuto-generated method stub
		 if (e.getSource() == button[0]) {
			//begen new game . 
			 gameContent.setVisible(false);
			 setFocusable(false);
			 land = new GameLand();
			 gameWindow(); 
			 requestFocusInWindow();
	  		} 
		  if(e.getSource() == button[1]) { 
			  
			  gameContent.setVisible(false);//removet the start windiw 
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
		gameContent = new JLabel();
		container.add(gameContent);
		gameContent.setVisible(false);
		gameContent = new JLabel(new ImageIcon("image" + "\\gameback.jpg"));
		
		explenation = "<html>score :" + score + "<br>move the snake : (use diracton keys )"
				+ "<br> press space to pause and resume</html> ";
		showScore = new JLabel(explenation);

		// show Score
		showScore.setLocation(550, 200);
		showScore.setSize(120, 120);
		showScore.setForeground(Color.WHITE);
		
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
						
						land.getSnake().stop();//set stop mode to snake .
						//check if snake Alive to reset new game .
						if (!land.getSnake().isAlive()) {
							
							// to start over >>
							land.setSnake();
						
						}else{
							
							//back to main meun.
							gameContent.setVisible(false);
							startWindow();
						
						}
						break;
				}

			}
		});
		// timer show the score .
		Timer reShow = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explenation = "<html>score :" + score + "<per><br>move the snake:(use diracton keys )"
						+ "<br> press space to pause and resume</html> ";
				showScore.setText(explenation);
			}
		});

		reShow.start();// start show the explanation string .
		
		//add the set content .
		gameContent.add(showScore);
		gameContent.add(land);
		//add the game content to the window 
		container.add(gameContent);
	}

}
