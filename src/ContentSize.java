import java.awt.Toolkit;

/**
 * this class describe and content the singleton object form its . 
 * 
 * 
 * @author fat-man
 *
 */
public class ContentSize {
	private static ContentSize info ;// singlton of this class 
	
	//the tootal height and width of the computer that the game run on . 
	private int tootal_height;
	private int tootal_width;
	
	
	private  int point;//describe the point height and width  of snake body point and apply size  .
	
	//the land object height and width . 
	private int land_height;
	private int land_width;
	
	private ContentSize (){
		Toolkit location = Toolkit.getDefaultToolkit();//getting the computer screen information .
		
		tootal_height = (int)location.getScreenSize().getHeight();//detect the height of screen .
		tootal_width = (int)location.getScreenSize().getWidth();//detect the width of screen .
		
		this.point = tootal_height / 20;//the point is %200 percent form the  screen .
		
		land_width = tootal_height + point;//the land width should equals the screen height plus .
		land_height = tootal_height ;//the land height should equal the screen height . 
		
		
		
	}

	/**
	 * static method return the object .
	 * 
	 * @return the info .
	 */
	public static ContentSize getInfo (){
		
		info=new ContentSize();
		return info;
		
	}
	/**
	 * @return the tootal_height
	 */
	public int getTootalHeight() {
		
		return tootal_height;
	
	}
	
	/**
	 * @return the tootal_width
	 */
	public int getTootalWidth() {
		
		return tootal_width;
	
	}
	/**
	 * @return the point
	 */
	public int getPoint() {
		
		return point;
	
	}
	
	/**
	 * @return the land_height
	 */
	public int getLandHeight() {
		
		return land_height;
	
	}
	/**
	 * @param land_height the land_height to set
	 */
	public void setLandHeight(int land_height) {
		
		this.land_height = land_height;
	
	}
	/**
	 * @return the land_width
	 */
	public int getLandWidth() {
		
		return land_width;
	
	}
	/**
	 * @param land_width the land_width to set
	 */
	public void setLandWidth(int land_width) {
		
		this.land_width = land_width;
	
	}
	
}
