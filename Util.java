import java.awt.Image;

import javax.swing.ImageIcon;

public class Util {
    
    public static ImageIcon createImageIcon(Object o, String path, String description) {
        //get the url of an image
        java.net.URL imgURL = o.getClass().getResource(path);
        if (imgURL != null) {
            //create an image icon object if the image file was found
            ImageIcon ii = new ImageIcon(imgURL, description);
            return ii;
        } else {
            //print out an error if the file could not be found
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static ImageIcon scaleImageIcon(ImageIcon ii, double buttonSize) {
        //get the image
        Image image = ii.getImage();
        //scale the image to the button size
        Image scaledImage = image.getScaledInstance((int)buttonSize, (int)buttonSize, java.awt.Image.SCALE_SMOOTH);
        //return a new image icon object with the newly scaled image
        return new ImageIcon(scaledImage); 
    }
}
