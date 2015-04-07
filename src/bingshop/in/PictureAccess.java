package bingshop.in;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Server.BingUtil;

public class PictureAccess {
	
	private final String extension = ".png";
	private String fileName;
	
	public PictureAccess(String key){
		fileName = BingUtil.PictureDirectory + key + extension;
		//TODO delete below
		/*String fileN1 = key.substring(4, 7);
		String finalS;
		if(fileN1.startsWith("00"))
			finalS = "C (" + fileN1.substring(2, 3) + ").PNG";
		else if(fileN1.startsWith("0"))
			finalS = "C (" + fileN1.substring(1, 3) + ").PNG";
		else
			finalS = "C (" + fileN1 + ").PNG";
		fileName = "C:\\Users\\Prabakaran\\Desktop\\dummy\\" + finalS;*/
		
	}
	
	public BufferedImage getImage() throws IOException{		
		return ImageIO.read(new File(fileName));
	}	
}
