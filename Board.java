import javax.swing.*;
import java.awt.*;
public class Board {
    /**
     * Initliaises and encapsualates the instance variables
     * Encapsulation prevents the variables from being accessed outside the class
     */
    private JFrame guiWindow = new JFrame();
    private JPanel guiPanel = new JPanel();
    private GridLayout hopperBoard = new GridLayout(5,5);
    private Square[][] currentLevelBoard = new Square[5][5];
    private Square selectedSquare = null;
    private Square moveToSquare;
    private int sourceX; 
    private int sourceY;
    private int destinationX;
    private int destinationY;
    
    //Sets the GUI & playspace for the board 
    public Board(){
        guiWindow.setTitle("Hoppers Jumping Game");
        guiWindow.setSize(760, 780);   //height & width 
        guiWindow.setContentPane(guiPanel);
        guiWindow.setVisible(true);
        guiWindow.setResizable(false);
        guiPanel.setLayout(hopperBoard);
        guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        LevelConfig levels = new LevelConfig();
        drawBoard(levels.getLevel(1));
    }

    /**
     * drawBoard method takes a 2D array from the LevelConfig class
     * and then comapres the index's of the array against conditions 
     * to draw the level 
     */

/*
    Square getFileNames = new Square(null, 0, 0);
    String[] fileNames = getFileNames.getFileArray();

*/

    public void drawBoard(int levelArray [][]){
        for (int i = 0; i< 5; i++){
            for(int j =0; j<5; j++){      
            int imageType = levelArray[i][j]; // Takes the value at the coordinates and stores its image type
            currentLevelBoard[i][j] = new Square(imageType,i,j, this); //Parse the image type and its corindates and create a Square object
            guiPanel.add(currentLevelBoard[i][j].getButton());  //Add the Square object & convert to a button before adding to the panel 
            }
            guiWindow.setVisible(true);
        }
    }

    public void moveFrog(){
        //if a validMove returns TRUE then do this otherwise not valid and don't move
        if(validMove()){
        selectedSquare.moveTo(moveToSquare);
        selectedSquare = moveToSquare;
        moveToSquare = null;
        }
        else{
            System.out.println("Illegal MOVE");
        }
    }
    
    private boolean validMove() {
        int sourceX = selectedSquare.getXCoordinate();
        int sourceY = selectedSquare.getYCoordinate();
        int destinationX = moveToSquare.getXCoordinate();
        int destinationY = moveToSquare.getYCoordinate();
        System.out.println("\nTHIS IS SOURCE");
        System.out.println(sourceX);
        System.out.println(sourceY);
        System.out.println("\nTHIS IS NOW DEST");
        System.out.println(destinationX);
        System.out.println(destinationY);


        if(Math.abs(sourceX-destinationX) ==2 && Math.abs(sourceY-destinationY) == 1) {
            return true;
        }
        else if(Math.abs(sourceY-destinationY)==2 && Math.abs(sourceX-destinationX)==1) {
            return true;
        }
    
    return false;
    }

    public Square getSelected() {
        return selectedSquare;
    }

    //Stores the slected sqaure parsed into the function to selectedSquare
    public void setSelected(Square sq){
        selectedSquare = sq;
    }

    public void setMove(Square sq){
        moveToSquare = sq;
        moveFrog();
    }



    public boolean isSelected(){
            if (selectedSquare == null){
             return false;
            }
            return true;
    


    }


}

