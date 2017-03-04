import javax.swing.JFrame;

/**
 * main java method begin the execution from it 
 * @author fat-man
 *
 */
public class Main {
	
	public static void main (String [] arge ){
		//create the main window object .
		MainWindow main_window = new MainWindow();
		
		//set exit mode if the client pressed the close operation in window .
		main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// make window visible .
		main_window.setVisible(true);
		
	}
}
