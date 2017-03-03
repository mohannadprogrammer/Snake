import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class Game extends JFrame {
	
	private JLabel gameContent;
	private Border land;
	
	private String explenation;
	private JLabel showScore;
	
	private Container container;
	
	private int score =0;

	private JButton[] button = new JButton[4];
	
	public Game() {
		super("java snake game ");
		setBounds(0,0,ContentSize.getInfo().getTootal_width(),ContentSize.getInfo().getTootal_height());
		
		container = getContentPane();
		land = new Border();
		startWindow();

	}

	
	  void startWindow() { 
		  gameContent = new JLabel();
		  
		  button[0] = new JButton("new Game");
		  button[1]=new JButton("Load Game");
		  button[2] = new JButton("setting");
		  button[3] = new JButton("Exit");
		  
		  int y = getWidth() /3;
		  for (int i = 0;i < button.length; i++)
		  { 
			button[i].setLocation(getHeight() / 3, y);
		  	button[i].setSize(120, 30);
	  		y += 35;
	  		button[i].addActionListener(new Handler());
	  		gameContent.add(button[i]); 
		  } 
		  container.add(gameContent);
	  
	  }
	  
	  private class Handler implements ActionListener {
	  
	  @Override 
	  public void actionPerformed(ActionEvent e) { // TODOAuto-generated method stub
		 if (e.getSource() == button[0]) {
			
			 gameContent.setVisible(false);
			 setFocusable(false);
			 land = new Border();
			 gameWindow(); 
			 requestFocusInWindow();
	  		} 
		  if(e.getSource() == button[1]) { 
			  gameContent.setVisible(false);
				 setFocusable(false);
				 gameWindow();
				 requestFocusInWindow();			  
		  }
		  if (e.getSource() == button[3]) {
			  System.exit(0); 
		  }
		  
	  }
	  
	  }
	 
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

		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_DOWN:
					land.getSnake().setDown();
					break;
				case KeyEvent.VK_LEFT:
					land.getSnake().setLeft();
					break;
				case KeyEvent.VK_RIGHT:
					land.getSnake().setRight();
					break;
				case KeyEvent.VK_UP:
					land.getSnake().setUp();
					break;
				case KeyEvent.VK_SPACE:
					land.getSnake().stop();
					if (!land.getSnake().alive) {
						// to start over >>
						land.setSnake();
					}else{
						gameContent.setVisible(false);
						startWindow();
					}
					break;
				}

			}
		});

		Timer reShow = new Timer(10, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				explenation = "<html>score :" + score + "<per><br>move the snake:(use diracton keys )"
						+ "<br> press space to pause and resume</html> ";
				showScore.setText(explenation);
			}
		});

		reShow.start();
		gameContent.add(showScore);
		gameContent.add(land);

		container.add(gameContent);
	}

}
