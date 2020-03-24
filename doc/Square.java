import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * Square represents each image on the board 
 * There are a total of 25 squares on the game board
 *  
 * @author Adam Boudjemaa
 * @version 1.0
 * @since 1.0
 * 
 */

public class Square implements ActionListener{
    
    private JButton button = new JButton();
    private int imageType;
    private ImageIcon imageIcon;
    private int xCoordinate;
    private int yCoordinate;
    private Board board;
    private String ROOT_PATH = "resources/images/";
    private String[] fileNames = {"LilyPad.png", "Water.png", 
                         "GreenFrog.png", "RedFrog.png",
                         "RedFrog2.png", "GreenFrog2.png"}; 
    /**
     * @param imageType The type of image i.e Frog, Lilypad, Water
     * @param xCoordinate The X coordinate of the where the square shoudld be stored 
     * @param yCoordinate The Y coordinate of the where the square shoudld be stored 
     * @param board The board of where to store the squares
     */                
    public Square(int imageType, int xCoordinate, int yCoordinate, Board board){
        this.imageType = imageType;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.board = board;
        imageIcon = new ImageIcon(ROOT_PATH + fileNames[imageType]); 
        button.addActionListener(this);  
    }

    public void actionPerformed(ActionEvent e){
        changePiece();
        
    }

    /** Gets the X coordinate 
     * @return the value of the X coordinate 
     */
    public int getXCoordinate(){
        return xCoordinate;
    }

    /** Gets the Y coordinate 
     * @return the value of the Y coordinate 
     */
    public int getYCoordinate(){
        return yCoordinate;
    }

    /**
     * Sets the button to the type specified by imageIcon
     * @return the button with the set icon
     */
    public JButton getButton(){
        button.setIcon(imageIcon);
        return button;
    }

    /**
     * Sets the icon for the botton from an array of images
     * @param i The image type i.e frog, lilypad, water etc
     */
    private void setImage(int i){
        imageIcon = new ImageIcon(ROOT_PATH + fileNames[i]); 
        imageType = i;
        button.setIcon(imageIcon);
    }
    
    /**
     *Checks to see if the is a frog on the the square
     * @return return True if there is otherwise return False
     */

    public boolean checkFrog(){
        if (imageType == 2){
            return true;
        }
        return false;
    }

    /**
     * Deletes the frog by replacing it with a lilypad
     * @return True after execution 
     */
    public boolean deleteFrog(){
        imageType = 0; 
        button.setIcon(new ImageIcon(ROOT_PATH + fileNames[imageType]));
        return true;
    }

    /**
     * 
     * @param moveTo The destination the player wants to move to
     */
    public void moveTo(Square moveTo){
        moveTo.setImage(this.imageType);
        this.setImage(0);

    }
    
    /**
     * Selects and deslects according to what the player clicks on
     * 
     */
    public void changePiece(){
           if(board.getSelected() == this){
            if (imageType == 5){
                setImage(2);
            }
            if (imageType == 4){
                setImage(3);
            }
            board.setSelected(null);
        }
         //If selected == null  
         else if (!board.isSelected()){
            if (imageType == 2){
                setImage(5);
                board.setSelected(this);
            }
            if (imageType == 3){
                setImage(4);
                board.setSelected(this);
            }
        }else if (imageType == 0){
            board.setMove(this);
        }      
    }
}

