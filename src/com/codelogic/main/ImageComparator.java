package com.codelogic.main;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 *credits to GeeksForGeeks https://www.geeksforgeeks.org/image-processing/
 * 
 * @author roberi
 *
 */
public class ImageComparator {
	public static void main(String[] args) {
//		try {
//			BufferedImage source = ImageIO.read(new File("C:\\images\\captcha1-stripped2.jpg"));
//			BufferedImage source = ImageIO.read(new File("C:\\BIDV Captcha\\BIDV9.jpg"));
//			System.out.println(source.getWidth());
//			System.out.println(source.getHeight());
//			ImageIO.write(source.getSubimage(13, 0,12,20), "jpg", new File("C:\\images\\test2test.jpg"));
//			ImageIO.write(source.getSubimage(10,36,12,20), "jpg", new File("C:\\BIDV Captcha\\bidv_s1.jpg"));
//			ImageIO.write(source.getSubimage(22,36,12,20), "jpg", new File("C:\\BIDV Captcha\\bidv_s2.jpg"));
//			ImageIO.write(source.getSubimage(37,36,12,20), "jpg", new File("C:\\BIDV Captcha\\bidv_s3.jpg"));
//			ImageIO.write(source.getSubimage(50,36,12,20), "jpg", new File("C:\\BIDV Captcha\\bidv_s4.jpg"));
//			ImageIO.write(source.getSubimage(62,36,12,20), "jpg", new File("C:\\BIDV Captcha\\bidv_s5.jpg"));
			
			//2nd set
//			0, 36,13,20
//			13,36,13,20
//			23,36,13,20
//			33,36,13,20
//			48,36,13,20
			
			//1st set
			//0,  0, 13, 20
			//13, 0, 13, 20
			//25, 0, 13, 20
			//38, 0, 13, 20
			//50, 0, 13, 20
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		compareimage();
		int y = 3;
//		System.out.println(y>>1);
		System.out.println(Integer.toBinaryString(Integer.MIN_VALUE>>31));
	}
	
	public static void compareimage() {
		BufferedImage imgA = null; 
        BufferedImage imgB = null; 
  
        try
        { 
            File fileA = new File("c:\\BIDV Captcha\\bidv1_sliced\\bidv_s1.jpg");
            
            File fileB = new File("c:\\BIDV Captcha\\bidv9_sliced\\bidv_s5.jpg"); 
  
            imgA = ImageIO.read(fileA); 
            imgB = ImageIO.read(fileB); 
        } 
        catch (IOException e) 
        { 
            System.out.println(e); 
        } 
        int width1 = imgA.getWidth(); 
        int width2 = imgB.getWidth(); 
        int height1 = imgA.getHeight(); 
        int height2 = imgB.getHeight(); 
  
        if ((width1 != width2) || (height1 != height2)) 
            System.out.println("Error: Images dimensions"+ 
                                             " mismatch"); 
        else
        { 
            long difference = 0; 
            for (int y = 0; y < height1; y++) 
            { 
                for (int x = 0; x < width1; x++) 
                { 
                    int rgbA = imgA.getRGB(x, y); 
                    int rgbB = imgB.getRGB(x, y); 
                    int redA = (rgbA >> 16) & 0xff; 
                    int greenA = (rgbA >> 8) & 0xff; 
                    int blueA = (rgbA) & 0xff; 
                    int redB = (rgbB >> 16) & 0xff; 
                    int greenB = (rgbB >> 8) & 0xff; 
                    int blueB = (rgbB) & 0xff; 
                    difference += Math.abs(redA - redB); 
                    difference += Math.abs(greenA - greenB); 
                    difference += Math.abs(blueA - blueB); 
                } 
            } 
  
            // Total number of red pixels = width * height 
            // Total number of blue pixels = width * height 
            // Total number of green pixels = width * height 
            // So total number of pixels = width * height * 3 
            double total_pixels = width1 * height1 * 3; 
  
            // Normalizing the value of different pixels 
            // for accuracy(average pixels per color 
            // component) 
            double avg_different_pixels = difference / 
                                          total_pixels; 
  
            // There are 255 values of pixels in total 
            double percentage = (avg_different_pixels / 
                                            255) * 100; 
  
            System.out.println("Difference Percentage-->" + 
                                                percentage); 
        } 
	}
}
