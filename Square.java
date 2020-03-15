import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
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

    public int getXCoordinate(){
        return xCoordinate;
    }

    public int getYCoordinate(){
        return yCoordinate;
    }

    public JButton getButton(){
        button.setIcon(imageIcon);
        return button;
    }

    private void setImage(int i){
        imageIcon = new ImageIcon(ROOT_PATH + fileNames[i]); 
        imageType = i;
        button.setIcon(imageIcon);
    }

    public boolean checkFrog(){
        if (imageType == 2){
            return true;
        }
        return false;
    }

    public void deleteFrog(){
        imageType = 0;
        button.setIcon(new ImageIcon(ROOT_PATH + fileNames[imageType]));
    }

    public void moveTo(Square moveTo){
        moveTo.setImage(this.imageType);
        this.setImage(0);
    }
    
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

