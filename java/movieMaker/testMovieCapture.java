package movieMaker;


import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.Robot;


public class testMovieCapture {
	
    public static void captureMovie() {
		Rectangle region = new Rectangle(0,0,500,500);
        boolean done = false;
        long startTime = 0;
        long endTime = 0;
        int count = 0;
        
        Thread current = Thread.currentThread();
        
		
        while (count<1000){
			startTime = System.currentTimeMillis();
			
			try{
				BufferedImage bi = new Robot().createScreenCapture(region);
				count++;
				
				
				Picture picture = new Picture(bi);
				String fileName =  "C://frames//image"+count+".jpg";
			    
			    picture.setFileName(fileName);
			    
			    // write out this frame
			    picture.write(fileName);
				
			}
			catch(Exception e){
			}
			
        }
	}
	
	public static void main(String[] args){
		
		long start = System.currentTimeMillis();
		captureMovie();
		long end = System.currentTimeMillis();
		System.out.print(java.util.ResourceBundle.getBundle("movieMaker/testMovieCapture").getString("DONE_IN_")+" " + (end-start)/1000 + "seconds");
		
	}


}


