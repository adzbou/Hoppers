import javax.swing.*;
import java.awt.*;
import java.lang.Math; 
public class Board {
    /**
     * Initliaises and encapsualates the instance variables
     * Encapsulation prevents the variables from being accessed outside the class
     */

    private JFrame guiWindow = new JFrame();
    private JFrame winMessage;
    private JPanel guiPanel = new JPanel();
    private GridLayout hopperBoard = new GridLayout(5,5);
    private Square[][] currentLevelBoard = new Square[5][5];
    LevelConfig levels = new LevelConfig();
    private int levelNumber;
    private int frogCount =0;
    private Square selectedSquare = null;
    private Square moveToSquare;
    
    
    //Sets the GUI & playspace for the board   
    public Board(){
        guiWindow.setTitle("Hoppers Jumping Game");
        guiWindow.setSize(760, 780);   //height & width 
        guiWindow.setContentPane(guiPanel);
        guiWindow.setVisible(true);
        guiWindow.setResizable(false);
        guiPanel.setLayout(hopperBoard);
        guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ///DISPLAYS THE LEVEL TO THE BOARD
        
        
        levelNumber = 5;
        drawBoard(levels.getLevel(levelNumber));
        
        //Displays the message if the user WINS
        
        //System.out.println(currentFrogs);
    
        
    
    
    }

    /**
     * drawBoard method takes a 2D array from the LevelConfig class
     * and then comapres the index's of the array against conditions 
     * to draw the level 
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
           
            int middleSquareX = (selectedSquare.getXCoordinate() +moveToSquare.getXCoordinate()) /2;
            int middleSquareY = (selectedSquare.getYCoordinate() +moveToSquare.getYCoordinate()) /2;
            currentLevelBoard[middleSquareX][middleSquareY].deleteFrog();
            selectedSquare.moveTo(moveToSquare);
            selectedSquare = moveToSquare;
            moveToSquare = null;
            frogCount = frogCount + 1;
            //System.out.println(a);
            if (frogCount == levels.getFrogs(levelNumber)) {
                winMessage = new JFrame();
                JOptionPane.showMessageDialog(winMessage, "Congratulations you have won!");
                frogCount = 0;
            }
            
            else{
                System.out.println("YOLO");
            }
        
        
        }
        else{
            System.out.println("Illegal MOVE");
        }
    }
    
    private boolean validMove(){
        int sourceX = selectedSquare.getXCoordinate();
        int sourceY = selectedSquare.getYCoordinate();
        int destinationX = moveToSquare.getXCoordinate();
        int destinationY = moveToSquare.getYCoordinate();
         
        if(Math.abs(sourceX-destinationX) ==2 || Math.abs(sourceY-destinationY) == 4 || Math.abs(sourceX-destinationX) == 4 || Math.abs(sourceY-destinationY) == 2){
            if(!(Math.abs(sourceY-destinationY) == 4 && Math.abs(sourceX-destinationX) == 4)){
                if(currentLevelBoard[(sourceX + destinationX)/2][(sourceY + destinationY)/2].checkFrog())
                    return true;
            }
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

    public int returnFrog(){
        int currentFrogs = levels.getFrogs(levelNumber); 
        return currentFrogs; 
    }
    
    /*
    public void winningMessage(int levelArray[][]){
        winMessage = new JFrame();
        int currentFrogs =0;
        int frogCount=0;
    
        for (int i =0; i<5; i++){
            for (int j = 0; j<5; j++){
                if (levelArray[i][j] == 2){
                    frogCount += 1;
                }

            }
        }
        
        JOptionPane.showMessageDialog(winMessage, "Congratulations you have won!");
    }
   
    */











}

