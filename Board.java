import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.lang.Math;

public class Board implements ActionListener{
    /**
     * Initliaises and encapsualates the instance variables Encapsulation prevents
     * the variables from being accessed outside the class
     */

    private JFrame guiWindow = new JFrame();
    private JFrame winMessage;

    private JPanel guiPanel = new JPanel();
    private JPanel newPanel = new JPanel();

    private GridLayout hopperBoard = new GridLayout(5, 5);

    private Square[][] currentLevelBoard = new Square[5][5];
    private Square selectedSquare = null;
    private Square moveToSquare;

    private LevelConfig levels = new LevelConfig();

    private int levelNumber;
    private int frogCount;
    private int currentFrogs;
   
    private JButton resetButton = new JButton("Reset Level");

    
    // Sets the GUI & playspace for the board
    public Board(int levelNumber) {
        guiWindow.setTitle("Hoppers Jumping Game");
        //guiWindow.setSize(760, 780); // height & width
        guiWindow.setSize(760, 810); // height & width
      
        
        //guiWindow.setResizable(false);
        guiPanel.setLayout(hopperBoard);
        guiWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //resetButton.setBounds(50,100,95,30);  
        //guiWindow.add(resetButton);
        /// DISPLAYS THE LEVEL TO THE BOARD
        
        newPanel.setLayout(new BorderLayout());
        newPanel.add("Center", guiPanel);
        newPanel.add("South", resetButton);
        resetButton.addActionListener(this);
    
        //guiWindow.setContentPane(guiPanel)
        guiWindow.setContentPane(newPanel);
      
      

       guiWindow.setVisible(true);
       newPanel.setVisible(true);



        //playSound("resources/sounds/background1.wav");
        
        
        this.levelNumber = levelNumber;
        drawBoard(levels.getLevel(levelNumber));
        currentFrogs = levels.getFrogs(levelNumber);
    
        

    }

    /**
     * drawBoard method takes a 2D array from the LevelConfig class and then
     * comapres the index's of the array against conditions to draw the level
     */
    public void drawBoard(int levelArray[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                int imageType = levelArray[i][j]; // Takes the value at the coordinates and stores its image type
                currentLevelBoard[i][j] = new Square(imageType, i, j, this); // Parse the image type and its corindates
                                                                             // and create a Square object
                guiPanel.add(currentLevelBoard[i][j].getButton()); // Add the Square object & convert to a button before
                                                                   // adding to the panel
            }
            guiWindow.setVisible(true);
        }
    }

    public void moveFrog() {

        // if a validMove returns TRUE then do this otherwise not valid and don't move
        
        if (validMove()) {
            ///Users/adam/Documents/NEW/SCC110_Hoppers/Board.java
            int middleSquareX = (selectedSquare.getXCoordinate() + moveToSquare.getXCoordinate()) / 2;
            int middleSquareY = (selectedSquare.getYCoordinate() + moveToSquare.getYCoordinate()) / 2;
            playSound("resources/sounds/water.wav");
            currentLevelBoard[middleSquareX][middleSquareY].deleteFrog();
            selectedSquare.moveTo(moveToSquare);
            selectedSquare = moveToSquare;
            moveToSquare = null;
            frogCount = frogCount + 1;

            
            // System.out.println(a);
            if (frogCount == currentFrogs) {
                playSound("resources/sounds/win.wav");
                winner(levelNumber);
                System.out.println(levels.levelLength());
                levelNumber = levelNumber + 1;
                frogCount = levels.getFrogs(levelNumber);
                guiWindow.dispose();
                System.out.println("THIS IS LEVEL NUMBER");
                System.out.println(levelNumber);
                new Board(levelNumber);
                
              
                
                // if (levelNumber < levels.levelLength()){
                //     new Board(levelNumber);
                // }
                // else{
                //     System.out.println("GAME OVER");
                // }    
            }
        } 

        else {
            playSound("resources/sounds/error.wav");
        }               
    }

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

    public Square getSelected() {
        return selectedSquare;
    }

    // Stores the slected sqaure parsed into the function to selectedSquare
    public void setSelected(Square sq) {
        selectedSquare = sq;
    }

    public void setMove(Square sq) {
        moveToSquare = sq;
        moveFrog();
    }

    public boolean isSelected() {
        if (selectedSquare == null) {
            return false;
        }
        return true;
    }

    public int returnFrog() {
        int currentFrogs = levels.getFrogs(levelNumber);
        return currentFrogs;
    }

    public void winner(int levelNumber) {
        winMessage = new JFrame();
        javax.swing.UIManager.put("OptionPane.messageFont", new Font("Espresso Shack", Font.PLAIN, 25));
        JOptionPane.showMessageDialog(winMessage, "Level " + levelNumber + " complete");
    }

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



    public void actionPerformed(ActionEvent e){
        new Board(levelNumber);
        guiWindow.dispose();
        
    }




}
