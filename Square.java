import javax.swing.*;
import java.awt.*;
public class Square{
    
    private JButton button = new JButton();
    private ImageIcon icon;
    private String iconName;
    private int xCoordinate;
    private int yCoordinate; 
    
    public Square(String iconName, int xCoordinate, int yCoordinate){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.iconName = iconName;
        icon = new ImageIcon(this.iconName);     
    }

    public int[] getXCoordinate(){
        int[] arr = {xCoordinate,yCoordinate};
        return arr;
    }

    public int[] getYCoordinate(){
        int[] arr = {xCoordinate, yCoordinate};
        return arr;
    }

    public JButton getButton(){
        button.setIcon(icon);
        return button;
    }
}

