import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Math;

/**
 * Board class for the Hoppers Game 
 * 
 * @author Adam Boudjemaa
 * @version 1.0
 * @since 1.0
 */
public class Board implements ActionListener{
    /**
     * Initliaises and encapsualates the instance variables Encapsulation prevents
     * the variables from being accessed outside the class
     */

    private JFrame mainWindow = new JFrame();
    private JFrame winMessage;
    private JFrame gameOverMsg;

    private JPanel mainPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private GridLayout hopperBoard = new GridLayout(5, 5);

    private Square[][] currentLevelBoard = new Square[5][5];
    private Square selectedSquare = null;
    private Square moveToSquare;

    private LevelConfig levels = new LevelConfig();

    private int levelNumber;
    private int frogCount;
    private int currentFrogs;
   
    private JButton resetButton = new JButton("Reset Level");

    
    /**
     * Sets the GUI and playspace for the board
     *  @param levelNumber The level number to initiate the game from
     * */
    public Board(int levelNumber) {
        mainWindow.setTitle("Hoppers Jumping Game");
        mainWindow.setSize(760, 810); // height & width
        mainWindow.setResizable(false);
        mainPanel.setLayout(hopperBoard);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        bottomPanel.setLayout(new BorderLayout());
        bottomPanel.add("Center", mainPanel);
        bottomPanel.add("South", resetButton);
        resetButton.addActionListener(this);

        mainWindow.setContentPane(bottomPanel);
        mainWindow.setVisible(true);
        bottomPanel.setVisible(true);
   
        this.levelNumber = levelNumber;
        drawBoard(levels.getLevel(levelNumber));
        currentFrogs = levels.getFrogs(levelNumber);
    }

    /**
     * Takes a 2D array from the LevelConfig class and then
     * comapres the index's of the array against conditions to draw the level
     * 
     * @param levelArray Each level containing the frogs, stored as Matrices in levelConfig 
     */
    public void drawBoard(int levelArray[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int imageType = levelArray[i][j]; // Takes the value at the coordinates and stores its image type
                currentLevelBoard[i][j] = new Square(imageType, i, j, this); // Parse the image type and its corindates and create a Square object                                                               
                mainPanel.add(currentLevelBoard[i][j].getButton()); // Add the Square object & convert to a button before adding to the panel                                                        
            }
            mainWindow.setVisible(true);
        }
    }

    /**
     * Moves the frog if the valid move (allows other methods/functions to be placed inside when the frog moves)
     */
    public void moveFrog() {
       
        // if a validMove returns TRUE then do this otherwise not valid and don't move
        
        if (validMove()) {
            
            int middleSquareX = (selectedSquare.getXCoordinate() + moveToSquare.getXCoordinate()) / 2; 
            int middleSquareY = (selectedSquare.getYCoordinate() + moveToSquare.getYCoordinate()) / 2;
            playSound("resources/sounds/water.wav");
            currentLevelBoard[middleSquareX][middleSquareY].deleteFrog();
            selectedSquare.moveTo(moveToSquare);
            selectedSquare = moveToSquare;
            selectedSquare.changePiece();
            moveToSquare = null;
            frogCount = frogCount + 1;

            if (frogCount == currentFrogs) {
                playSound("resources/sounds/win.wav");
                winner(levelNumber);

                if (levelNumber == levels.levelLength() -1){
                    gameOver();
                    System.exit(0);
                }
                else{
                    levelNumber = levelNumber + 1;
                    frogCount = levels.getFrogs(levelNumber);
                    mainWindow.dispose();     
                    new Board(levelNumber);         
                }
            }             
        } 

        else {
            playSound("resources/sounds/error.wav");
        }               
    }

    /**
     * Calcualtes if the frog is able to move on the squares/buttons selected by the player
     * @return Will return True if the move is valid or False if the move is invalid 
     */
    private boolean validMove() {
        int sourceX = selectedSquare.getXCoordinate();
        int sourceY = selectedSquare.getYCoordinate();
        int destinationX = moveToSquare.getXCoordinate();
        int destinationY = moveToSquare.getYCoordinate();

        if (Math.abs(sourceX - destinationX) == 2 || Math.abs(sourceY - destinationY) == 4
                || Math.abs(sourceX - destinationX) == 4 || Math.abs(sourceY - destinationY) == 2) {
            if (!(Math.abs(sourceY - destinationY) == 4 && Math.abs(sourceX - destinationX) == 4)) {
                if (currentLevelBoard[(sourceX + destinationX) / 2][(sourceY + destinationY) / 2].checkFrog())
                    return true;
            }
        }
        return false;
    }

    /**
     * 
     * @return Returns the square selected by the player
     */
    public Square getSelected() {
        return selectedSquare;
    }

    /**
     * Stores the slected sqaure parsed into the function to selectedSquare
     * @param sq takes a square object and sets it equal to selecedSquare
     *  */ 
    public void setSelected(Square sq) {
        selectedSquare = sq;
    }

    /**
     * Takes another Square as a parameter and moves it
     * @param sq sets moveToSquare to sqaure object parsed
     * 
     */
    public void setMove(Square sq) {
        moveToSquare = sq;
        moveFrog();
    }

    /**
     * Check to see if square is currently selected or deslected by the player
     * @return True or False
     */
    public boolean isSelected() {
        if (selectedSquare == null) {
            return false;
        }
        return true;
    }

    /**
     * Takes the level number inputted by the user via the game GUI and returns current number of frogs on that level
     * @return currentFrogs
     */
    public int returnFrog() {
        int currentFrogs = levels.getFrogs(levelNumber);
        return currentFrogs;
    }

    /**
     * Displays a message stating the current level the user has played is complete 
     * @param levelNumber takes the level number to display which level the user has just completed
     */


    public void winner(int levelNumber) {
        winMessage = new JFrame();
        javax.swing.UIManager.put("OptionPane.messageFont", new Font("Espresso Shack", Font.PLAIN, 25));
        JOptionPane.showMessageDialog(winMessage, "Level " + levelNumber + " complete");
    }

    public void gameOver(){
        gameOverMsg = new JFrame();
        javax.swing.UIManager.put("OptionPane.messageFont", new Font("Espresso Shack", Font.PLAIN, 25));
        JOptionPane.showMessageDialog(gameOverMsg, "Congratulations All Levels Complete :)");
    }


    /**
     * Plays background music and sound fx for the frogs
     * @param soundName The file path to the audio 
     */
    public void playSound(String soundName){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        }
        catch(Exception ex){
            System.out.println("ERROR: COULD NOT PLAY SOUND !!!");
            ex.printStackTrace( );
        }
    }

    /**
     * Resets the current level when the player clicks "Reset Button"
     */
    public void actionPerformed(ActionEvent e){
        new Board(levelNumber);
        mainWindow.dispose();
        
    }


}
