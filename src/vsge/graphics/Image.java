package vsge.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class Image {

	private BufferedImage mImage;
	
	public BufferedImage getImage() {
		return mImage;
	}
	
	public void loadImage(int imageId)  
    {  
        BufferedImage image = null;
        String fileName = null;
        if (imageId >= 0 && imageId < ImageConstants.NUM_IMAGES) {
        	fileName = ImageConstants.paths[imageId];
        	System.out.println("load: " + fileName);
        }
        try  
        {  
            URL url = getClass().getResource(fileName); 
            image = ImageIO.read(url);  
        }  
        catch(MalformedURLException mue)  
        {  
            System.err.println("url: " + mue.getMessage());  
        }  
        catch(IllegalArgumentException iae)  
        {  
            System.err.println("arg: " + iae.getMessage());  
        }  
        catch(IOException ioe)  
        {  
            System.err.println("read: " + ioe.getMessage());  
        }  
        if(image == null)  
        {  
            image = new BufferedImage(10, 10, BufferedImage.TYPE_INT_RGB);  
            System.out.println("unable to load image, returning default");  
        }  
        mImage = image;  
    }  
}
