import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Square implements ActionListener{
    
    private JButton button = new JButton();
    private int imageType;
    private ImageIcon imageIcon;
    private int xCoordinate;
    private int yCoordinate;
    private String ROOT_PATH = "resources/images/";
    private String[] fileNames = {"LilyPad.png", "Water.png", 
                         "GreenFrog.png", "RedFrog.png",
                         "RedFrog2.png", "GreenFrog2.png"}; 

    
    public Square(int i, int xCoordinate, int yCoordinate){
        this.imageType = i;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        imageIcon = new ImageIcon(ROOT_PATH + fileNames[i]); 
        button.addActionListener(this);  
    }

    public int[] getCoordinate(){
        int[] arr = {xCoordinate,yCoordinate};
        return arr;
    }

    public JButton getButton(){
        button.setIcon(imageIcon);
        return button;
    }

    private void setImage(int i) {
        imageIcon = new ImageIcon(ROOT_PATH + fileNames[i]); 
        imageType = i;
        button.setIcon(imageIcon);
    }

    public void actionPerformed(ActionEvent e){
        if (imageType == 2) {
            setImage(5);
            System.out.println("Image Changed");
        }
    }

	





}

